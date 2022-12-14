// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DrlParser.g4 by ANTLR 4.9.1
package io.inceptor.drl.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DrlParser}.
 */
public interface DrlParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DrlParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(DrlParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(DrlParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#dialect}.
	 * @param ctx the parse tree
	 */
	void enterDialect(DrlParser.DialectContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#dialect}.
	 * @param ctx the parse tree
	 */
	void exitDialect(DrlParser.DialectContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#pack}.
	 * @param ctx the parse tree
	 */
	void enterPack(DrlParser.PackContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#pack}.
	 * @param ctx the parse tree
	 */
	void exitPack(DrlParser.PackContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#importDecls}.
	 * @param ctx the parse tree
	 */
	void enterImportDecls(DrlParser.ImportDeclsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#importDecls}.
	 * @param ctx the parse tree
	 */
	void exitImportDecls(DrlParser.ImportDeclsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#declares}.
	 * @param ctx the parse tree
	 */
	void enterDeclares(DrlParser.DeclaresContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#declares}.
	 * @param ctx the parse tree
	 */
	void exitDeclares(DrlParser.DeclaresContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#global}.
	 * @param ctx the parse tree
	 */
	void enterGlobal(DrlParser.GlobalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#global}.
	 * @param ctx the parse tree
	 */
	void exitGlobal(DrlParser.GlobalContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(DrlParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(DrlParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void enterImportDecl(DrlParser.ImportDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void exitImportDecl(DrlParser.ImportDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#importDrl}.
	 * @param ctx the parse tree
	 */
	void enterImportDrl(DrlParser.ImportDrlContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#importDrl}.
	 * @param ctx the parse tree
	 */
	void exitImportDrl(DrlParser.ImportDrlContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#importDatasource}.
	 * @param ctx the parse tree
	 */
	void enterImportDatasource(DrlParser.ImportDatasourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#importDatasource}.
	 * @param ctx the parse tree
	 */
	void exitImportDatasource(DrlParser.ImportDatasourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#importNormal}.
	 * @param ctx the parse tree
	 */
	void enterImportNormal(DrlParser.ImportNormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#importNormal}.
	 * @param ctx the parse tree
	 */
	void exitImportNormal(DrlParser.ImportNormalContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#importStatic}.
	 * @param ctx the parse tree
	 */
	void enterImportStatic(DrlParser.ImportStaticContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#importStatic}.
	 * @param ctx the parse tree
	 */
	void exitImportStatic(DrlParser.ImportStaticContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#importGlobal}.
	 * @param ctx the parse tree
	 */
	void enterImportGlobal(DrlParser.ImportGlobalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#importGlobal}.
	 * @param ctx the parse tree
	 */
	void exitImportGlobal(DrlParser.ImportGlobalContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#declare}.
	 * @param ctx the parse tree
	 */
	void enterDeclare(DrlParser.DeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#declare}.
	 * @param ctx the parse tree
	 */
	void exitDeclare(DrlParser.DeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#fields}.
	 * @param ctx the parse tree
	 */
	void enterFields(DrlParser.FieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#fields}.
	 * @param ctx the parse tree
	 */
	void exitFields(DrlParser.FieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(DrlParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(DrlParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(DrlParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(DrlParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePairs(DrlParser.ElementValuePairsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePairs(DrlParser.ElementValuePairsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePair(DrlParser.ElementValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePair(DrlParser.ElementValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(DrlParser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(DrlParser.ElementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#onerule}.
	 * @param ctx the parse tree
	 */
	void enterOnerule(DrlParser.OneruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#onerule}.
	 * @param ctx the parse tree
	 */
	void exitOnerule(DrlParser.OneruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#ruleFlags}.
	 * @param ctx the parse tree
	 */
	void enterRuleFlags(DrlParser.RuleFlagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#ruleFlags}.
	 * @param ctx the parse tree
	 */
	void exitRuleFlags(DrlParser.RuleFlagsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#ruleFlag}.
	 * @param ctx the parse tree
	 */
	void enterRuleFlag(DrlParser.RuleFlagContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#ruleFlag}.
	 * @param ctx the parse tree
	 */
	void exitRuleFlag(DrlParser.RuleFlagContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#whenClauses}.
	 * @param ctx the parse tree
	 */
	void enterWhenClauses(DrlParser.WhenClausesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#whenClauses}.
	 * @param ctx the parse tree
	 */
	void exitWhenClauses(DrlParser.WhenClausesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fromMethodCall}
	 * labeled alternative in {@link DrlParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void enterFromMethodCall(DrlParser.FromMethodCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fromMethodCall}
	 * labeled alternative in {@link DrlParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void exitFromMethodCall(DrlParser.FromMethodCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fromStream}
	 * labeled alternative in {@link DrlParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void enterFromStream(DrlParser.FromStreamContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fromStream}
	 * labeled alternative in {@link DrlParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void exitFromStream(DrlParser.FromStreamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fromDs}
	 * labeled alternative in {@link DrlParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void enterFromDs(DrlParser.FromDsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fromDs}
	 * labeled alternative in {@link DrlParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void exitFromDs(DrlParser.FromDsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#conditions}.
	 * @param ctx the parse tree
	 */
	void enterConditions(DrlParser.ConditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#conditions}.
	 * @param ctx the parse tree
	 */
	void exitConditions(DrlParser.ConditionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExistsCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterExistsCondition(DrlParser.ExistsConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExistsCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitExistsCondition(DrlParser.ExistsConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterMethodCondition(DrlParser.MethodConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitMethodCondition(DrlParser.MethodConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContainCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterContainCondition(DrlParser.ContainConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContainCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitContainCondition(DrlParser.ContainConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndOrCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterAndOrCondition(DrlParser.AndOrConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndOrCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitAndOrCondition(DrlParser.AndOrConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContainsCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterContainsCondition(DrlParser.ContainsConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContainsCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitContainsCondition(DrlParser.ContainsConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompareCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCompareCondition(DrlParser.CompareConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompareCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCompareCondition(DrlParser.CompareConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionSelf}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterConditionSelf(DrlParser.ConditionSelfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionSelf}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitConditionSelf(DrlParser.ConditionSelfContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#compareClause}.
	 * @param ctx the parse tree
	 */
	void enterCompareClause(DrlParser.CompareClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#compareClause}.
	 * @param ctx the parse tree
	 */
	void exitCompareClause(DrlParser.CompareClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#containClause}.
	 * @param ctx the parse tree
	 */
	void enterContainClause(DrlParser.ContainClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#containClause}.
	 * @param ctx the parse tree
	 */
	void exitContainClause(DrlParser.ContainClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#containsClause}.
	 * @param ctx the parse tree
	 */
	void enterContainsClause(DrlParser.ContainsClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#containsClause}.
	 * @param ctx the parse tree
	 */
	void exitContainsClause(DrlParser.ContainsClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#existsClause}.
	 * @param ctx the parse tree
	 */
	void enterExistsClause(DrlParser.ExistsClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#existsClause}.
	 * @param ctx the parse tree
	 */
	void exitExistsClause(DrlParser.ExistsClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterCompare(DrlParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitCompare(DrlParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNumberBranch(DrlParser.NumberBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNumberBranch(DrlParser.NumberBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FloatBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterFloatBranch(DrlParser.FloatBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FloatBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitFloatBranch(DrlParser.FloatBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterStringBranch(DrlParser.StringBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitStringBranch(DrlParser.StringBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBooleanBranch(DrlParser.BooleanBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBooleanBranch(DrlParser.BooleanBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NullBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNullBranch(DrlParser.NullBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NullBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNullBranch(DrlParser.NullBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterMethodBranch(DrlParser.MethodBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitMethodBranch(DrlParser.MethodBranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(DrlParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(DrlParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#methodParams}.
	 * @param ctx the parse tree
	 */
	void enterMethodParams(DrlParser.MethodParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#methodParams}.
	 * @param ctx the parse tree
	 */
	void exitMethodParams(DrlParser.MethodParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#methodParam}.
	 * @param ctx the parse tree
	 */
	void enterMethodParam(DrlParser.MethodParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#methodParam}.
	 * @param ctx the parse tree
	 */
	void exitMethodParam(DrlParser.MethodParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#mapParams}.
	 * @param ctx the parse tree
	 */
	void enterMapParams(DrlParser.MapParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#mapParams}.
	 * @param ctx the parse tree
	 */
	void exitMapParams(DrlParser.MapParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#mapParam}.
	 * @param ctx the parse tree
	 */
	void enterMapParam(DrlParser.MapParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#mapParam}.
	 * @param ctx the parse tree
	 */
	void exitMapParam(DrlParser.MapParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#mapkey}.
	 * @param ctx the parse tree
	 */
	void enterMapkey(DrlParser.MapkeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#mapkey}.
	 * @param ctx the parse tree
	 */
	void exitMapkey(DrlParser.MapkeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#mapvalue}.
	 * @param ctx the parse tree
	 */
	void enterMapvalue(DrlParser.MapvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#mapvalue}.
	 * @param ctx the parse tree
	 */
	void exitMapvalue(DrlParser.MapvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#functions}.
	 * @param ctx the parse tree
	 */
	void enterFunctions(DrlParser.FunctionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#functions}.
	 * @param ctx the parse tree
	 */
	void exitFunctions(DrlParser.FunctionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(DrlParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(DrlParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#fbody}.
	 * @param ctx the parse tree
	 */
	void enterFbody(DrlParser.FbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#fbody}.
	 * @param ctx the parse tree
	 */
	void exitFbody(DrlParser.FbodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(DrlParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(DrlParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(DrlParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(DrlParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(DrlParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(DrlParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(DrlParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(DrlParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(DrlParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(DrlParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(DrlParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(DrlParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#jsonPath}.
	 * @param ctx the parse tree
	 */
	void enterJsonPath(DrlParser.JsonPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#jsonPath}.
	 * @param ctx the parse tree
	 */
	void exitJsonPath(DrlParser.JsonPathContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(DrlParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(DrlParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#codelines}.
	 * @param ctx the parse tree
	 */
	void enterCodelines(DrlParser.CodelinesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#codelines}.
	 * @param ctx the parse tree
	 */
	void exitCodelines(DrlParser.CodelinesContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#codeline}.
	 * @param ctx the parse tree
	 */
	void enterCodeline(DrlParser.CodelineContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#codeline}.
	 * @param ctx the parse tree
	 */
	void exitCodeline(DrlParser.CodelineContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(DrlParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(DrlParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrlParser#genericity}.
	 * @param ctx the parse tree
	 */
	void enterGenericity(DrlParser.GenericityContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrlParser#genericity}.
	 * @param ctx the parse tree
	 */
	void exitGenericity(DrlParser.GenericityContext ctx);
}