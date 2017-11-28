// Generated from Pcl2.g4 by ANTLR 4.4

    import wci.intermediate.*;
    import wci.intermediate.symtabimpl.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Pcl2Parser}.
 */
public interface Pcl2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(@NotNull Pcl2Parser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(@NotNull Pcl2Parser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerConst}
	 * labeled alternative in {@link Pcl2Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterIntegerConst(@NotNull Pcl2Parser.IntegerConstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerConst}
	 * labeled alternative in {@link Pcl2Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitIntegerConst(@NotNull Pcl2Parser.IntegerConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#stmtList}.
	 * @param ctx the parse tree
	 */
	void enterStmtList(@NotNull Pcl2Parser.StmtListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#stmtList}.
	 * @param ctx the parse tree
	 */
	void exitStmtList(@NotNull Pcl2Parser.StmtListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#sign}.
	 * @param ctx the parse tree
	 */
	void enterSign(@NotNull Pcl2Parser.SignContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#sign}.
	 * @param ctx the parse tree
	 */
	void exitSign(@NotNull Pcl2Parser.SignContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull Pcl2Parser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull Pcl2Parser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#varId}.
	 * @param ctx the parse tree
	 */
	void enterVarId(@NotNull Pcl2Parser.VarIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#varId}.
	 * @param ctx the parse tree
	 */
	void exitVarId(@NotNull Pcl2Parser.VarIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(@NotNull Pcl2Parser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(@NotNull Pcl2Parser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#addSubOp}.
	 * @param ctx the parse tree
	 */
	void enterAddSubOp(@NotNull Pcl2Parser.AddSubOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#addSubOp}.
	 * @param ctx the parse tree
	 */
	void exitAddSubOp(@NotNull Pcl2Parser.AddSubOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpr(@NotNull Pcl2Parser.VariableExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpr(@NotNull Pcl2Parser.VariableExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#signedNumber}.
	 * @param ctx the parse tree
	 */
	void enterSignedNumber(@NotNull Pcl2Parser.SignedNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#signedNumber}.
	 * @param ctx the parse tree
	 */
	void exitSignedNumber(@NotNull Pcl2Parser.SignedNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#compoundStmt}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStmt(@NotNull Pcl2Parser.CompoundStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#compoundStmt}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStmt(@NotNull Pcl2Parser.CompoundStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull Pcl2Parser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull Pcl2Parser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#assignmentStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStmt(@NotNull Pcl2Parser.AssignmentStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#assignmentStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStmt(@NotNull Pcl2Parser.AssignmentStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(@NotNull Pcl2Parser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(@NotNull Pcl2Parser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#declarations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarations(@NotNull Pcl2Parser.DeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#declarations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarations(@NotNull Pcl2Parser.DeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code floatConst}
	 * labeled alternative in {@link Pcl2Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterFloatConst(@NotNull Pcl2Parser.FloatConstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code floatConst}
	 * labeled alternative in {@link Pcl2Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitFloatConst(@NotNull Pcl2Parser.FloatConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#varList}.
	 * @param ctx the parse tree
	 */
	void enterVarList(@NotNull Pcl2Parser.VarListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#varList}.
	 * @param ctx the parse tree
	 */
	void exitVarList(@NotNull Pcl2Parser.VarListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#declList}.
	 * @param ctx the parse tree
	 */
	void enterDeclList(@NotNull Pcl2Parser.DeclListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#declList}.
	 * @param ctx the parse tree
	 */
	void exitDeclList(@NotNull Pcl2Parser.DeclListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull Pcl2Parser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull Pcl2Parser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(@NotNull Pcl2Parser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(@NotNull Pcl2Parser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#mainBlock}.
	 * @param ctx the parse tree
	 */
	void enterMainBlock(@NotNull Pcl2Parser.MainBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#mainBlock}.
	 * @param ctx the parse tree
	 */
	void exitMainBlock(@NotNull Pcl2Parser.MainBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#typeId}.
	 * @param ctx the parse tree
	 */
	void enterTypeId(@NotNull Pcl2Parser.TypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#typeId}.
	 * @param ctx the parse tree
	 */
	void exitTypeId(@NotNull Pcl2Parser.TypeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#mulDivOp}.
	 * @param ctx the parse tree
	 */
	void enterMulDivOp(@NotNull Pcl2Parser.MulDivOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#mulDivOp}.
	 * @param ctx the parse tree
	 */
	void exitMulDivOp(@NotNull Pcl2Parser.MulDivOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unsignedNumberExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedNumberExpr(@NotNull Pcl2Parser.UnsignedNumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unsignedNumberExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedNumberExpr(@NotNull Pcl2Parser.UnsignedNumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(@NotNull Pcl2Parser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(@NotNull Pcl2Parser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Pcl2Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull Pcl2Parser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Pcl2Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull Pcl2Parser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code signedNumberExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSignedNumberExpr(@NotNull Pcl2Parser.SignedNumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code signedNumberExpr}
	 * labeled alternative in {@link Pcl2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSignedNumberExpr(@NotNull Pcl2Parser.SignedNumberExprContext ctx);
}