// Generated from Hello.g4 by ANTLR 4.7
package com.TeamAlexKong.parser;

    import wci.intermediate.*;
    import wci.intermediate.symtabimpl.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(HelloParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#packageDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDeclaration(HelloParser.PackageDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#importDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclaration(HelloParser.ImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(HelloParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(HelloParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#enumDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumDeclaration(HelloParser.EnumDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#modifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifiers(HelloParser.ModifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#typeParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameters(HelloParser.TypeParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#typeParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameter(HelloParser.TypeParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#typeBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBound(HelloParser.TypeBoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#enumBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumBody(HelloParser.EnumBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#enumConstants}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumConstants(HelloParser.EnumConstantsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#enumConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumConstant(HelloParser.EnumConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumBodyDeclarations(HelloParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#typeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeList(HelloParser.TypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(HelloParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBodyDeclaration(HelloParser.ClassBodyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#member}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember(HelloParser.MemberContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(HelloParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(HelloParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#methodDeclarationRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclarationRest(HelloParser.MethodDeclarationRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericMethodDeclaration(HelloParser.GenericMethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclaration(HelloParser.FieldDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(HelloParser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#voidMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidMethodDeclaratorRest(HelloParser.VoidMethodDeclaratorRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#constantDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclarator(HelloParser.ConstantDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variableDeclarators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarators(HelloParser.VariableDeclaratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variableDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(HelloParser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variableAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAssignment(HelloParser.VariableAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#constantDeclaratorsRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaratorsRest(HelloParser.ConstantDeclaratorsRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#constantDeclaratorRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaratorRest(HelloParser.ConstantDeclaratorRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorId(HelloParser.VariableDeclaratorIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(HelloParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableType(HelloParser.VariableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variableInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializer(HelloParser.VariableInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(HelloParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(HelloParser.ModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#enumConstantName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumConstantName(HelloParser.EnumConstantNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(HelloParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(HelloParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#classType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType(HelloParser.ClassTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(HelloParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variableModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableModifier(HelloParser.VariableModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#typeArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArguments(HelloParser.TypeArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#typeArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgument(HelloParser.TypeArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedNameList(HelloParser.QualifiedNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(HelloParser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#formalParameterDecls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterDecls(HelloParser.FormalParameterDeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(HelloParser.FormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLastFormalParameter(HelloParser.LastFormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#parameterVariableId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterVariableId(HelloParser.ParameterVariableIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#methodBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBody(HelloParser.MethodBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#constructorBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorBody(HelloParser.ConstructorBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#explicitConstructorInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplicitConstructorInvocation(HelloParser.ExplicitConstructorInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(HelloParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerConst}
	 * labeled alternative in {@link HelloParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerConst(HelloParser.IntegerConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatingPointConst}
	 * labeled alternative in {@link HelloParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatingPointConst(HelloParser.FloatingPointConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code characterConst}
	 * labeled alternative in {@link HelloParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacterConst(HelloParser.CharacterConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringConst}
	 * labeled alternative in {@link HelloParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringConst(HelloParser.StringConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanConst}
	 * labeled alternative in {@link HelloParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanConst(HelloParser.BooleanConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullConst}
	 * labeled alternative in {@link HelloParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullConst(HelloParser.NullConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(HelloParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(HelloParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(HelloParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(HelloParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVariableDeclarationStatement(HelloParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVariableDeclaration(HelloParser.LocalVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variableModifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableModifiers(HelloParser.VariableModifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(HelloParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#forLoopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoopStatement(HelloParser.ForLoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(HelloParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(HelloParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(HelloParser.ElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(HelloParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#whenStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenStatement(HelloParser.WhenStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#whenEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenEntry(HelloParser.WhenEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#whenCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenCondition(HelloParser.WhenConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#forControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForControl(HelloParser.ForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(HelloParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#enhancedForControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnhancedForControl(HelloParser.EnhancedForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(HelloParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#parExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(HelloParser.ParExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(HelloParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#statementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementExpression(HelloParser.StatementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(HelloParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignmentExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpr(HelloParser.AssignmentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(HelloParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisExpr(HelloParser.ThisExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code superIndentifierExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperIndentifierExpr(HelloParser.SuperIndentifierExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instanceOfExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceOfExpr(HelloParser.InstanceOfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseOrExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseOrExpr(HelloParser.BitwiseOrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubOneExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubOneExpr(HelloParser.AddSubOneExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseAndExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseAndExpr(HelloParser.BitwiseAndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(HelloParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionExpr(HelloParser.FunctionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpr(HelloParser.VariableExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsExpr(HelloParser.IsExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseXorExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseXorExpr(HelloParser.BitwiseXorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalAndExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpr(HelloParser.LogicalAndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpr(HelloParser.ArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeExpr(HelloParser.TypeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInExpr(HelloParser.InExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tenaryOpExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTenaryOpExpr(HelloParser.TenaryOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(HelloParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalOrExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpr(HelloParser.LogicalOrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryOpExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOpExpr(HelloParser.UnaryOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(HelloParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryExp}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(HelloParser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code superExprList}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperExprList(HelloParser.SuperExprListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicativeExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(HelloParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(HelloParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rangeExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeExpr(HelloParser.RangeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpr(HelloParser.IdentifierExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#rangeOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeOp(HelloParser.RangeOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#multiplicativeOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeOp(HelloParser.MultiplicativeOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#additiveOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveOp(HelloParser.AdditiveOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#isOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsOp(HelloParser.IsOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#addSubOneOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubOneOp(HelloParser.AddSubOneOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#relationalOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOp(HelloParser.RelationalOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#equalityOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityOp(HelloParser.EqualityOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(HelloParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonWildcardTypeArguments(HelloParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(HelloParser.ArgumentsContext ctx);
}