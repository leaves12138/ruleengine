// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DsParser.g4 by ANTLR 4.9.1
package io.inceptor.drl.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DsParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DsParser#declares}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclares(DsParser.DeclaresContext ctx);
	/**
	 * Visit a parse tree produced by {@link DsParser#declare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare(DsParser.DeclareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBranch(DsParser.TypeBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code URLBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitURLBranch(DsParser.URLBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UserNameBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserNameBranch(DsParser.UserNameBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PasswordBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasswordBranch(DsParser.PasswordBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PortBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPortBranch(DsParser.PortBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HostBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHostBranch(DsParser.HostBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IsClusterBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsClusterBranch(DsParser.IsClusterBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClusterIpsBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClusterIpsBranch(DsParser.ClusterIpsBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UserDefineBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserDefineBranch(DsParser.UserDefineBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link DsParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(DsParser.BooleanLiteralContext ctx);
}