package io.inceptor.drl.ast;

import io.inceptor.drl.drl.*;
import io.inceptor.drl.drl.action.Action;
import io.inceptor.drl.drl.action.ActionImpl;
import io.inceptor.drl.drl.condition.*;
import io.inceptor.drl.drl.condition.inner.*;
import io.inceptor.drl.parser.DrlLexer;
import io.inceptor.drl.parser.DrlParser;
import io.inceptor.drl.parser.DrlParserBaseListener;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

import static io.inceptor.drl.drl.condition.inner.LeftValue.Type.*;

public class DrlTreeListener extends DrlParserBaseListener {
    private ParsedDrlFile parsedDrlFile;
    private DeclaredClass currentDeclaredClass;
    private Rule currentRule;
    private Condition currentClassCondition;

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
        int importDrl = 0,  importDatasource = 0;
        for (ParseTree child : ctx.children) {
            child = child.getChild(0);
            if (child instanceof DrlParser.ImportDrlContext) {
                importDrl++;
            }

            if (child instanceof DrlParser.ImportDatasourceContext) {
                importDatasource++;
            }
        }
        parsedDrlFile.setDrlImports(new String[importDrl]);
//        parsedDrlFile.setJavaImoprts(new JavaImportClass[importJava]);
        parsedDrlFile.setDatasourceImports(new String[importDatasource]);
        importDrl = importDatasource = 0;

        for (ParseTree child : ctx.children) {
            child = child.getChild(0);
            if (child instanceof DrlParser.ImportDrlContext) {
                parsedDrlFile.getDrlImports()[importDrl] = ((DrlParser.ImportDrlContext) child).qualifiedName().getText();
                importDrl++;
                continue;
            }

            if (child instanceof DrlParser.ImportNormalContext) {
//                parsedDrlFile.getJavaImoprts()[importJava] = ((DrlParser.ImportNormalContext) child).qualifiedName().getText();
                JavaImportClass javaImportClass = new JavaImportClass(child.getText() , ((DrlParser.ImportNormalContext) child).qualifiedName().getText());
                parsedDrlFile.addImportClass(javaImportClass);
                continue;
            }

            if (child instanceof DrlParser.ImportDatasourceContext) {
                parsedDrlFile.getDatasourceImports()[importDatasource] = ((DrlParser.ImportDatasourceContext) child).qualifiedName().getText();
                importDatasource++;
                continue;
            }

            if (child instanceof DrlParser.ImportGlobalContext) {
                GlobalImport globalImport = new GlobalImport();
                globalImport.setGlobalType(((DrlParser.ImportGlobalContext) child).qualifiedName().getText());
                globalImport.setGlobalName(((DrlParser.ImportGlobalContext) child).name.getText());
                parsedDrlFile.addImportGlobal(globalImport);
                continue;
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

    @Override public void enterGlobal(DrlParser.GlobalContext ctx) {
        String expr = ctx.block().getText();
        this.parsedDrlFile.setGlobalExpr(expr.substring(1, expr.length()-1));
    }

    @Override public void enterFunctions(DrlParser.FunctionsContext ctx) {
        for(DrlParser.FunctionContext fctx : ctx.function()) {
            DrlParser.FbodyContext body = fctx.fbody();

            DefinedFunction definedFunction = new DefinedFunction();
            definedFunction.setReturnType(body.returnType().getText());
            definedFunction.setMethodName(body.fname.getText());
            for (DrlParser.ParamContext param : body.params().param()) {
                definedFunction.addParam(param.type().getText(), param.var().getText());
            }
            String bs = fctx.fbody().block().getText();
            definedFunction.setBody(bs.substring(1, bs.length()-1));

            parsedDrlFile.addDefinedFunction(definedFunction);
        }
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

        if (ctx.LIMIT() != null){
            classCondition.setLimit(true);
            classCondition.setLimitNum(ctx.NUMBER().getText());
        }
        currentRule.getClassCondition().add(classCondition);
        currentClassCondition = classCondition;
    }


    @Override
    public void enterFromMethodCall(DrlParser.FromMethodCallContext ctx) {
        MethodCondition methodCondition = new MethodCondition();
        methodCondition.setStatement(ctx.getText());
        currentRule.getClassCondition().add(methodCondition);
        currentClassCondition = methodCondition;
    }

    @Override
    public void enterConditions(DrlParser.ConditionsContext ctx) {
        for (DrlParser.ConditionContext conditionContext : ctx.condition()) {
            InnerCondition condition = getConditionFromCtx(conditionContext);
            ((ClassCondition)currentClassCondition).getConditionList().add(condition);
        }
    }

    @Override
    public void enterCodelines(DrlParser.CodelinesContext ctx) {
        Action action = new ActionImpl();
        action.setCode(ctx.getText());
        currentRule.setAction(action);
    }


    public InnerCondition getConditionFromCtx(DrlParser.ConditionContext ctx) {
        if (ctx instanceof DrlParser.CompareConditionContext) {
            DrlParser.CompareClauseContext compareClauseContext = ((DrlParser.CompareConditionContext) ctx).compareClause();
            CompareCondition compareCondition = new CompareCondition();

            LeftValue leftValue = new LeftValue();

            if (compareClauseContext.lm == 1) {
                leftValue.setType(JSON);
                leftValue.setContent(compareClauseContext.jsonPath().getText());
            }
            else if (compareClauseContext.lm == 0) {
                leftValue.setType(FIELD);
                leftValue.setContent(compareClauseContext.fieldName.getText());
            }
            else if (compareClauseContext.lm == 2) {
                leftValue.setType(METHODCALL);
                leftValue.setContent(compareClauseContext.methodCall().getText());
            }
            compareCondition.setLeftValue(leftValue);

            if (compareClauseContext.symbole != null)
                compareCondition.setSymbolName(compareClauseContext.symbole.getText());

            RightValue rightValue = new RightValue();
            rightValue.setType(getType(compareClauseContext.literal()));
            rightValue.setValue(compareClauseContext.literal().getText());
            compareCondition.setSymbol(compareClauseContext.compare().getText());
            compareCondition.setRight(rightValue);
            return compareCondition;

        } else if (ctx instanceof DrlParser.ContainConditionContext) {
            DrlParser.ContainClauseContext containClauseContext = ((DrlParser.ContainConditionContext) ctx).containClause();
            ContainCondition containCondition = new ContainCondition();

            LeftValue leftValue = new LeftValue();

            if (containClauseContext.lm == 1) {
                leftValue.setType(JSON);
                leftValue.setContent(containClauseContext.jsonPath().getText());
            }
            else if (containClauseContext.lm == 0) {
                leftValue.setType(FIELD);
                leftValue.setContent(containClauseContext.fieldName.getText());
            }
            else if (containClauseContext.lm == 2) {
                leftValue.setType(METHODCALL);
                leftValue.setContent(containClauseContext.methodCall().getText());
            }
            containCondition.setLeftValue(leftValue);

            if (containClauseContext.symbole != null)
                containCondition.setSymbolName(containClauseContext.symbole.getText());
            containCondition.setContain(containClauseContext.CONTAIN().getText());

            List<RightValue> rightValueList = new ArrayList();
            for (DrlParser.LiteralContext literalContext : containClauseContext.literal()) {
                RightValue rightValue = new RightValue();
                rightValue.setType(getType(literalContext));
                rightValue.setValue(literalContext.getText());
                rightValueList.add(rightValue);
            }
            containCondition.setRights(rightValueList);
            return containCondition;
        } else if (ctx instanceof DrlParser.ExistsConditionContext) {
            DrlParser.ExistsClauseContext existsClauseContext = ((DrlParser.ExistsConditionContext) ctx).existsClause();
            ExistCondition existsCondition = new ExistCondition();
            LeftValue leftValue = new LeftValue();

            if (existsClauseContext.lm == 1) {
                leftValue.setType(JSON);
                leftValue.setContent(existsClauseContext.jsonPath().getText());
            }
            else if (existsClauseContext.lm == 0) {
                leftValue.setType(FIELD);
                leftValue.setContent(existsClauseContext.fieldName.getText());
            }
            else if (existsClauseContext.lm == 2) {
                leftValue.setType(METHODCALL);
                leftValue.setContent(existsClauseContext.methodCall().getText());
            }
            existsCondition.setLeftValue(leftValue);

            if (existsClauseContext.symbole != null)
                existsCondition.setSymbolName(existsClauseContext.symbole.getText());
            return existsCondition;
        } else if (ctx instanceof DrlParser.ConditionSelfContext) {
            return getConditionFromCtx(((DrlParser.ConditionSelfContext) ctx).condition());

        } else if (ctx instanceof DrlParser.AndOrConditionContext) {
            if (((DrlParser.AndOrConditionContext) ctx).op.getType() == DrlLexer.AndAnd) {
                AndCondition andCondition = new AndCondition();
                InnerCondition left = getConditionFromCtx(((DrlParser.AndOrConditionContext) ctx).left);
                InnerCondition right = getConditionFromCtx(((DrlParser.AndOrConditionContext) ctx).right);
                andCondition.leftCondition = left;
                andCondition.rightCondition = right;
                return andCondition;
            } else {
                OrCondition orCondition = new OrCondition();
                InnerCondition left = getConditionFromCtx(((DrlParser.AndOrConditionContext) ctx).left);
                InnerCondition right = getConditionFromCtx(((DrlParser.AndOrConditionContext) ctx).right);
                orCondition.leftCondition = left;
                orCondition.rightCondition = right;
                return orCondition;
            }
        } else if (ctx instanceof DrlParser.MethodConditionContext) {
            InnerMethodCondition methodCondition = new InnerMethodCondition();
            methodCondition.setStatement(ctx.getText());
            return methodCondition;
        } else {
            return null;
        }
    }

    private RightValue.Type getType(DrlParser.LiteralContext ctx) {
        if (ctx instanceof DrlParser.NumberBranchContext) {
            return RightValue.Type.NUMBER;
        } else if (ctx instanceof DrlParser.FloatBranchContext) {
            return RightValue.Type.FLOAT;
        } else if (ctx instanceof DrlParser.StringBranchContext) {
            return RightValue.Type.STRING;
        } else if (ctx instanceof DrlParser.BooleanBranchContext) {
            return RightValue.Type.BOOLEAN;
        } else if (ctx instanceof DrlParser.NullBranchContext) {
            return RightValue.Type.NULL;
        } else {
            return RightValue.Type.METHODCALL;
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
