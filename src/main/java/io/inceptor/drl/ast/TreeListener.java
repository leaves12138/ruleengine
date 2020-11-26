package io.inceptor.drl.ast;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.ParsedDrlFile;
import io.inceptor.drl.drl.Rule;
import io.inceptor.drl.drl.action.Action;
import io.inceptor.drl.drl.action.ActionImpl;
import io.inceptor.drl.drl.condition.*;
import io.inceptor.drl.parser.DrlLexer;
import io.inceptor.drl.parser.DrlParser;
import io.inceptor.drl.parser.DrlParserBaseListener;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class TreeListener extends DrlParserBaseListener {
    private ParsedDrlFile parsedDrlFile;
    private DeclaredClass currentDeclaredClass;
    private Rule currentRule;
    private ClassCondition currentClassCondition;

    public ParsedDrlFile getParsedDrlFile() {
        return parsedDrlFile;
    }

    @Override
    public void enterFile(DrlParser.FileContext ctx) {
        parsedDrlFile = new ParsedDrlFile();
    }

    @Override
    public void enterPack(DrlParser.PackContext ctx) {
        parsedDrlFile.setLocation(ctx.qualifiedName().getText().replace(".", "/"));
    }

    @Override
    public void enterImportDecls(DrlParser.ImportDeclsContext ctx) {
        if (ctx.children == null)
            return;
        int importDrl = 0, importJava = 0, importDatasource = 0;
        for (ParseTree child : ctx.children) {
            child = child.getChild(0);
            if (child instanceof DrlParser.ImportDrlContext) {
                importDrl++;
            }
            if (child instanceof DrlParser.ImportNormalContext) {
                importJava++;
            }

            if (child instanceof DrlParser.ImportDatasourceContext) {
                importDatasource++;
            }
        }
        parsedDrlFile.setDrlImports(new String[importDrl]);
        parsedDrlFile.setJavaImoprts(new String[importJava]);
        parsedDrlFile.setDatasourceImports(new String[importDatasource]);
        importDrl = importJava = importDatasource = 0;

        for (ParseTree child : ctx.children) {
            child = child.getChild(0);
            if (child instanceof DrlParser.ImportDrlContext) {
                parsedDrlFile.getDrlImports()[importDrl] = ((DrlParser.ImportDrlContext) child).qualifiedName().getText();
                importDrl++;
            }

            if (child instanceof DrlParser.ImportNormalContext) {
                parsedDrlFile.getJavaImoprts()[importJava] = ((DrlParser.ImportNormalContext) child).qualifiedName().getText();
                importJava++;
            }

            if (child instanceof DrlParser.ImportDatasourceContext) {
                parsedDrlFile.getDatasourceImports()[importDatasource] = ((DrlParser.ImportDatasourceContext) child).qualifiedName().getText();
                importDatasource++;
            }
        }
    }

    @Override
    public void enterDeclares(DrlParser.DeclaresContext ctx) {
        parsedDrlFile.setDeclaredClasses(new ArrayList<DeclaredClass>());
    }

    @Override
    public void enterDeclare(DrlParser.DeclareContext ctx) {
        List<DrlParser.AnnotationContext> annotationContexts = ctx.annotation();

        DeclaredClass declaredClass = new DeclaredClass(parsedDrlFile.getLocation());
        declaredClass.setOriText(ctx.getText());
        declaredClass.setClassName(ctx.Identifier().getText());
        parsedDrlFile.getDeclaredClasses().add(declaredClass);
        currentDeclaredClass = declaredClass;
        currentDeclaredClass.setAnnotations(new ArrayList<>(annotationContexts.size()));
        for (DrlParser.AnnotationContext annotationContext : annotationContexts) {
            enterClassAnnotation(annotationContext, currentDeclaredClass);
        }
    }

    public void enterClassAnnotation(DrlParser.AnnotationContext ctx, DeclaredClass currentClass) {
        DeclaredClass.Annotation annotation = new DeclaredClass.Annotation();
        annotation.setName(ctx.Identifier().getText());
        if (ctx.hasValue) {
            if (ctx.isPairs) {
                DrlParser.ElementValuePairsContext pairsContext = ctx.elementValuePairs();
                for (DrlParser.ElementValuePairContext pairContext : pairsContext.elementValuePair()) {
                    annotation.setValue(pairContext.Identifier().getText(), getValue(pairContext.elementValue()));
                }
            } else {
                annotation.setValue("value", getValue(ctx.elementValue()));
            }
        }

        currentClass.getAnnotations().add(annotation);
    }

    @Override
    public void enterFields(DrlParser.FieldsContext ctx) {
        currentDeclaredClass.setFields(new ArrayList<DeclaredClass.Field>(ctx.children.size()));
    }

    @Override
    public void enterField(DrlParser.FieldContext ctx) {
        DeclaredClass.Field field = new DeclaredClass.Field();
        currentDeclaredClass.getFields().add(field);
        field.setAnnotations(new ArrayList<DeclaredClass.Annotation>(ctx.annotation().size()));
        field.setFieldName(ctx.Identifier().getText());
        field.setJavaType(ctx.qualifiedName().getText());
        List<DrlParser.AnnotationContext> annotationContexts = ctx.annotation();
        for (DrlParser.AnnotationContext annotationContext : annotationContexts) {
            enterFieldAnnotation(annotationContext, field);
        }
    }

    public void enterFieldAnnotation(DrlParser.AnnotationContext ctx, DeclaredClass.Field currentField) {
        DeclaredClass.Annotation annotation = new DeclaredClass.Annotation();
        annotation.setName(ctx.Identifier().getText());
        if (ctx.hasValue) {
            if (ctx.isPairs) {
                DrlParser.ElementValuePairsContext pairsContext = ctx.elementValuePairs();
                for (DrlParser.ElementValuePairContext pairContext : pairsContext.elementValuePair()) {
                    annotation.setValue(pairContext.Identifier().getText(), getValue(pairContext.elementValue()));
                }
            } else {
                annotation.setValue("value", getValue(ctx.elementValue()));
            }
        }

        currentField.getAnnotations().add(annotation);
    }

    @Override
    public void enterOnerule(DrlParser.OneruleContext ctx) {
        Rule rule = new Rule();
        if (parsedDrlFile.getRuleHead() == null) {
            parsedDrlFile.setRuleHead(rule);
            currentRule = rule;
        } else {
            currentRule.next = rule;
            currentRule = currentRule.next;
        }
        rule.setClassCondition(new ArrayList<>());
        rule.setName(ctx.ruleName.getText());
        rule.setText(ctx.getText());
    }

    @Override
    public void exitOnerule(DrlParser.OneruleContext ctx) {
        parsedDrlFile.setRuleTail(currentRule);
    }

    @Override
    public void enterFromStream(DrlParser.FromStreamContext ctx) {
        ClassCondition classCondition = new ClassCondition();
        if (ctx.symbole != null) {
            classCondition.setSymbolName(ctx.symbole.getText());
        }
        classCondition.setClassName(ctx.className.getText());
        classCondition.setConditionList(new ArrayList<>());
        currentRule.getClassCondition().add(classCondition);
        currentClassCondition = classCondition;
    }

    @Override
    public void enterFromDs(DrlParser.FromDsContext ctx) {
        DbClassCondition classCondition = new DbClassCondition();
        if (ctx.symbole != null) {
            classCondition.setSymbolName(ctx.symbole.getText());
        }
        classCondition.setClassName(ctx.className.getText());
        classCondition.setConditionList(new ArrayList<>());
        classCondition.setDatasourceName(ctx.dbName.getText());
        currentRule.getClassCondition().add(classCondition);
        currentClassCondition = classCondition;
    }

    @Override
    public void enterConditions(DrlParser.ConditionsContext ctx) {
        for (DrlParser.ConditionContext conditionContext : ctx.condition()) {
            Condition condition = getConditionFromCtx(conditionContext);
            currentClassCondition.getConditionList().add(condition);
        }
    }

    @Override
    public void enterCodelines(DrlParser.CodelinesContext ctx) {
        Action action = new ActionImpl();
        action.setCode(ctx.getText());
        currentRule.setAction(action);
    }


    public Condition getConditionFromCtx(DrlParser.ConditionContext ctx) {
        if (ctx instanceof DrlParser.CompareConditionContext) {
            DrlParser.CompareClauseContext compareClauseContext = ((DrlParser.CompareConditionContext) ctx).compareClause();
            CompareCondition compareCondition = new CompareCondition();
            compareCondition.setLeft(compareClauseContext.Identifier().getText());
            Value value = new Value();
            value.setType(getType(compareClauseContext.literal()));
            value.setValue(compareClauseContext.literal().getText());
            compareCondition.setSymbol(compareClauseContext.COMPARE().getText());
            compareCondition.setRight(value);
            return compareCondition;

        } else if (ctx instanceof DrlParser.ContainConditionContext) {
            DrlParser.ContainClauseContext containClauseContext = ((DrlParser.ContainConditionContext) ctx).containClause();
            ContainCondition containCondition = new ContainCondition();
            containCondition.setLeft(containClauseContext.Identifier().getText());
            containCondition.setContain(containClauseContext.CONTAIN().getText());
            List<Value> valueList = new ArrayList();
            for (DrlParser.LiteralContext literalContext : containClauseContext.literal()) {
                Value value = new Value();
                value.setType(getType(literalContext));
                value.setValue(literalContext.getText());
                valueList.add(value);
            }
            containCondition.setRights(valueList);
            return containCondition;

        } else if (ctx instanceof DrlParser.ConditionSelfContext) {
            return getConditionFromCtx(((DrlParser.ConditionSelfContext) ctx).condition());

        } else if (ctx instanceof DrlParser.AndOrConditionContext) {
            if (((DrlParser.AndOrConditionContext) ctx).op.getType() == DrlLexer.AndAnd) {
                AndCondition andCondition = new AndCondition();
                Condition left = getConditionFromCtx(((DrlParser.AndOrConditionContext) ctx).left);
                Condition right = getConditionFromCtx(((DrlParser.AndOrConditionContext) ctx).right);
                andCondition.leftCondition = left;
                andCondition.rightCondition = right;
                return andCondition;
            } else {
                OrCondition orCondition = new OrCondition();
                Condition left = getConditionFromCtx(((DrlParser.AndOrConditionContext) ctx).left);
                Condition right = getConditionFromCtx(((DrlParser.AndOrConditionContext) ctx).right);
                orCondition.leftCondition = left;
                orCondition.rightCondition = right;
                return orCondition;
            }
        } else {
            return null;
        }
    }

    private Value.Type getType(DrlParser.LiteralContext ctx) {
        if (ctx instanceof DrlParser.NumberBranchContext) {
            return Value.Type.NUMBER;
        } else if (ctx instanceof DrlParser.FloatBranchContext) {
            return Value.Type.FLOAT;
        } else if (ctx instanceof DrlParser.StringBranchContext) {
            return Value.Type.STRING;
        } else if (ctx instanceof DrlParser.BooleanBranchContext) {
            return Value.Type.BOOLEAN;
        } else if (ctx instanceof DrlParser.NullBranchContext) {
            return Value.Type.NULL;
        } else {
            return Value.Type.METHODCALL;
        }
    }

    private Object getValue(DrlParser.ElementValueContext ctx) {
        if (ctx.STRING() != null) {
            return ctx.STRING().getText();
        } else if (ctx.NUMBER() != null) {
            return Integer.valueOf(ctx.NUMBER().getText());
        } else if (ctx.booleanLiteral() != null) {
            return Boolean.valueOf(ctx.getText());
        } else {
            return Float.valueOf(ctx.FloatingPointLiteral().getText());
        }
    }
}
