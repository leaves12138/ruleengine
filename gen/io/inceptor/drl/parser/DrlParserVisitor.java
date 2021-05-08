// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DrlParser.g4 by ANTLR 4.9.1
package io.inceptor.drl.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DrlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DrlParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DrlParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(DrlParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#pack}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPack(DrlParser.PackContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#importDecls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDecls(DrlParser.ImportDeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#declares}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclares(DrlParser.DeclaresContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal(DrlParser.GlobalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules(DrlParser.RulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#importDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDecl(DrlParser.ImportDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#importDrl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDrl(DrlParser.ImportDrlContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#importDatasource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDatasource(DrlParser.ImportDatasourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#importNormal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportNormal(DrlParser.ImportNormalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#importStatic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportStatic(DrlParser.ImportStaticContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#importGlobal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportGlobal(DrlParser.ImportGlobalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#declare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare(DrlParser.DeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFields(DrlParser.FieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(DrlParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(DrlParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#elementValuePairs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValuePairs(DrlParser.ElementValuePairsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#elementValuePair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValuePair(DrlParser.ElementValuePairContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#elementValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValue(DrlParser.ElementValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#onerule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnerule(DrlParser.OneruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#ruleFlags}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleFlags(DrlParser.RuleFlagsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#ruleFlag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleFlag(DrlParser.RuleFlagContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#whenClauses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenClauses(DrlParser.WhenClausesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fromStream}
	 * labeled alternative in {@link DrlParser#whenClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromStream(DrlParser.FromStreamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fromDs}
	 * labeled alternative in {@link DrlParser#whenClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromDs(DrlParser.FromDsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fromMethodCall}
	 * labeled alternative in {@link DrlParser#whenClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromMethodCall(DrlParser.FromMethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#conditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditions(DrlParser.ConditionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExistsCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistsCondition(DrlParser.ExistsConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCondition(DrlParser.MethodConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ContainCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainCondition(DrlParser.ContainConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndOrCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrCondition(DrlParser.AndOrConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompareCondition}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareCondition(DrlParser.CompareConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionSelf}
	 * labeled alternative in {@link DrlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionSelf(DrlParser.ConditionSelfContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#compareClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareClause(DrlParser.CompareClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#containClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainClause(DrlParser.ContainClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#existsClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistsClause(DrlParser.ExistsClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(DrlParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberBranch(DrlParser.NumberBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatBranch(DrlParser.FloatBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringBranch(DrlParser.StringBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanBranch(DrlParser.BooleanBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NullBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullBranch(DrlParser.NullBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodBranch}
	 * labeled alternative in {@link DrlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBranch(DrlParser.MethodBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(DrlParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#methodParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodParams(DrlParser.MethodParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#methodParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodParam(DrlParser.MethodParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#mapParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapParams(DrlParser.MapParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#mapParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapParam(DrlParser.MapParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#functions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctions(DrlParser.FunctionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(DrlParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#fbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFbody(DrlParser.FbodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(DrlParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(DrlParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(DrlParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(DrlParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(DrlParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(DrlParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#jsonPath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonPath(DrlParser.JsonPathContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(DrlParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#codelines}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodelines(DrlParser.CodelinesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#codeline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeline(DrlParser.CodelineContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(DrlParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrlParser#genericity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericity(DrlParser.GenericityContext ctx);
}