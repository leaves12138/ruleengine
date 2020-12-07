// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DsParser.g4 by ANTLR 4.8
package io.inceptor.drl.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DsParser}.
 */
public interface DsParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DsParser#declares}.
	 * @param ctx the parse tree
	 */
	void enterDeclares(DsParser.DeclaresContext ctx);
	/**
	 * Exit a parse tree produced by {@link DsParser#declares}.
	 * @param ctx the parse tree
	 */
	void exitDeclares(DsParser.DeclaresContext ctx);
	/**
	 * Enter a parse tree produced by {@link DsParser#declare}.
	 * @param ctx the parse tree
	 */
	void enterDeclare(DsParser.DeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link DsParser#declare}.
	 * @param ctx the parse tree
	 */
	void exitDeclare(DsParser.DeclareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void enterTypeBranch(DsParser.TypeBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void exitTypeBranch(DsParser.TypeBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code URLBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void enterURLBranch(DsParser.URLBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code URLBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void exitURLBranch(DsParser.URLBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UserNameBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void enterUserNameBranch(DsParser.UserNameBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UserNameBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void exitUserNameBranch(DsParser.UserNameBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PasswordBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void enterPasswordBranch(DsParser.PasswordBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PasswordBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void exitPasswordBranch(DsParser.PasswordBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PortBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void enterPortBranch(DsParser.PortBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PortBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void exitPortBranch(DsParser.PortBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HostBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void enterHostBranch(DsParser.HostBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HostBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void exitHostBranch(DsParser.HostBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UserDefineBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void enterUserDefineBranch(DsParser.UserDefineBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UserDefineBranch}
	 * labeled alternative in {@link DsParser#field}.
	 * @param ctx the parse tree
	 */
	void exitUserDefineBranch(DsParser.UserDefineBranchContext ctx);
}