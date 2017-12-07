package com.TeamAlexKong.compiler;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.TeamAlexKong.parser.HelloBaseVisitor;
import com.TeamAlexKong.parser.HelloParser.BooleanConstContext;
import com.TeamAlexKong.parser.HelloParser.CharacterConstContext;
import com.TeamAlexKong.parser.HelloParser.ClassDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.CompilationUnitContext;
import com.TeamAlexKong.parser.HelloParser.ConstructorDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.FloatingPointConstContext;
import com.TeamAlexKong.parser.HelloParser.FormalParameterContext;
import com.TeamAlexKong.parser.HelloParser.FormalParameterDeclsContext;
import com.TeamAlexKong.parser.HelloParser.FunctionExprContext;
import com.TeamAlexKong.parser.HelloParser.IntegerConstContext;
import com.TeamAlexKong.parser.HelloParser.MethodContext;
import com.TeamAlexKong.parser.HelloParser.MethodDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.ParameterVariableIdContext;
import com.TeamAlexKong.parser.HelloParser.PrimaryExpContext;
import com.TeamAlexKong.parser.HelloParser.ReturnStatementContext;
import com.TeamAlexKong.parser.HelloParser.StringConstContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorIdContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorsContext;
import com.TeamAlexKong.parser.HelloParser.VariableExprContext;
import com.TeamAlexKong.parser.HelloParser.VariableInitializerContext;
import com.TeamAlexKong.parser.HelloParser.VariableTypeContext;

import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;
import wci.util.*;

import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.symtabimpl.DefinitionImpl.*;

public class TeamAlexKongVisitor1 extends HelloBaseVisitor<Integer> {
    private SymTabStack symTabStack;
    private SymTabEntry programId;
    private ArrayList<SymTabEntry> variableIdList;
    private PrintWriter jFile;
    
    public TeamAlexKongVisitor1() {
    	symTabStack = SymTabFactory.createSymTabStack();
    	Predefined.initialize(symTabStack);
    }
    
    public PrintWriter getAssemblyFile() {
    	return jFile;
    }
    
    @Override
    public Integer visitCompilationUnit(CompilationUnitContext ctx) {
    	Integer value = visitChildren(ctx);
    	
        // Print the cross-reference table.
        CrossReferencer crossReferencer = new CrossReferencer();
        crossReferencer.print(symTabStack);
        
        return value;
    }
    
    @Override
    public Integer visitClassDeclaration(ClassDeclarationContext ctx) {
    	String className = ctx.Identifier().toString();
    	
        programId = symTabStack.enterLocal(className);
        programId.setDefinition(DefinitionImpl.PROGRAM);
        programId.setAttribute(ROUTINE_SYMTAB, symTabStack.push());
        symTabStack.setProgramId(programId);
        
        // Create the assembly output file.
        try {
            jFile = new PrintWriter(new FileWriter(className + ".j"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
        
        // Emit the program header.
        jFile.println(".class public " + className);
        jFile.println(".super java/lang/Object");

        return visitChildren(ctx);
    }
    
    @Override
    public Integer visitConstructorDeclaration(ConstructorDeclarationContext ctx) {
        
        // Emit the class constructor.
        jFile.println();
        jFile.println(".method public <init>()V");
        jFile.println();
        jFile.println("\taload_0");
        jFile.println("\tinvokenonvirtual    java/lang/Object/<init>()V");
        
        Integer value = visit(ctx.constructorBody());
        
        jFile.println("\treturn");
        jFile.println();
        jFile.println(".limit locals 20");
        jFile.println(".limit stack 20");
        jFile.println(".end method");
        
        return value;
    }
    
    
    @Override
    public Integer visitVariableDeclarators(VariableDeclaratorsContext ctx) {
    	variableIdList = new ArrayList<SymTabEntry>();
    	return visitChildren(ctx);  
    }
    
    
    
    @Override
    public Integer visitVariableDeclarator(VariableDeclaratorContext ctx) {
    	Integer value = visit(ctx.variableDeclaratorId());
    	
    	value = visit(ctx.variableType());
    	
    	if(ctx.variableInitializer() != null) {
    		value = visit(ctx.variableInitializer());
    	}
    	
    	return value;
    }
    
    @Override
    public Integer visitFormalParameter(FormalParameterContext ctx) {
    	
    	String typeName = ctx.type().getText();
        TypeSpec type;
        String typeIndicator = "";
        
        if (typeName.indexOf("Array") >= 0) {
        	typeIndicator = "[";
        }
        
        if (typeName.indexOf("Int") >= 0) {
            type = Predefined.localIntegerType;
            typeIndicator += "I";
        }
        else if (typeName.indexOf("Float") >= 0) {
            type = Predefined.localFloatType;
            typeIndicator += "F";
        } 
        else if (typeName.indexOf("Bool") >= 0) {
            type = Predefined.localBoolType;
            typeIndicator += "Z";
        } else if (typeName.indexOf("String") >= 0) {
        	type = Predefined.localStringType;
        	typeIndicator += "Ljava/lang/String;";
        }
        else {
            type = null;
            typeIndicator = "?";
        }
        
        Integer value = visitChildren(ctx);
                    
        for (SymTabEntry id : variableIdList) {
            id.setTypeSpec(type);
        }
        
    	return value;
    }
    
    @Override
    public Integer visitVariableDeclaratorId(VariableDeclaratorIdContext ctx) {
        String variableName = ctx.Identifier().toString();
       
        SymTabEntry variableId = symTabStack.enterLocal(variableName);
        variableId.setDefinition(VARIABLE);
        
        variableIdList.add(variableId);
        
        return visitChildren(ctx); 
    }
    
    @Override
    public Integer visitParameterVariableId(ParameterVariableIdContext ctx) {
    	String variableName = ctx.Identifier().toString();
    	
        SymTabEntry variableId = symTabStack.enterLocal(variableName);
        variableId.setDefinition(VARIABLE);
        
        variableIdList.add(variableId);
        
    	return visitChildren(ctx);
    }
    
    @Override
    public Integer visitVariableType(VariableTypeContext ctx) {
    	String typeName = ctx.getText();
    	
        TypeSpec type;
        String   typeIndicator = "";
        
        if (typeName.indexOf("Array") >= 0) {
        	typeIndicator = "[";
        }
        
        if (typeName.indexOf("Int") >= 0) {
            type = Predefined.integerType;
            typeIndicator += "I";
        }
        else if (typeName.indexOf("Float") >= 0) {
            type = Predefined.floatType;
            typeIndicator += "F";
        } 
        else if (typeName.indexOf("Bool") >= 0) {
            type = Predefined.booleanType;
            typeIndicator += "Z";
        } else if (typeName.indexOf("String") >= 0) {
        	type = Predefined.stringType;
        	typeIndicator += "Ljava/lang/String;";
        }
        else {
            type = null;
            typeIndicator = "?";
        }
        
        for (SymTabEntry id : variableIdList) {
            id.setTypeSpec(type);
            
            // Emit a field declaration.
            jFile.print(".field private static " +
                               id.getName() + " " + typeIndicator);
        }
        
        return visitChildren(ctx);
    }
    
    @Override
    public Integer visitVariableInitializer(VariableInitializerContext ctx) {
    	Integer value = visitChildren(ctx);
    	
    	if(ctx.expression() != null) {
    		jFile.print(" = " + ctx.expression().getText());
    	}
    	
    	jFile.println();
    	
    	return value;
    }
    
    @Override
    public Integer visitVariableExpr(VariableExprContext ctx) {
    	String variableName = ctx.variable().Identifier().toString();
    	SymTabEntry variableId = symTabStack.lookup(variableName);
    	
    	ctx.typeExpr = variableId.getTypeSpec();
    	
    	return visitChildren(ctx); 
    }
    
    @Override
    public Integer visitMethodDeclaration(MethodDeclarationContext ctx) {
    	variableIdList = new ArrayList<SymTabEntry>();
    	
    	Integer value = visitChildren(ctx);
    	
    	String typeName = "";
    	
    	if(ctx.type() != null) {
    		typeName = ctx.type().getText();
    	}
    	
        TypeSpec type;
        String   typeIndicator = "";
        
        if (typeName.indexOf("Array") >= 0) {
        	typeIndicator = "[";
        }
        
        if (typeName.indexOf("Int") >= 0) {
            type = Predefined.integerType;
            typeIndicator += "I";
        }
        else if (typeName.indexOf("Float") >= 0) {
            type = Predefined.floatType;
            typeIndicator += "F";
        } 
        else if (typeName.indexOf("Bool") >= 0) {
            type = Predefined.booleanType;
            typeIndicator += "Z";
        } else if (typeName.indexOf("String") >= 0) {
        	type = Predefined.stringType;
        	typeIndicator += "Ljava/lang/String;";
        } else {
        	type = Predefined.voidType;
        	typeIndicator = "V";
        }
        
    	SymTabEntry variableId = symTabStack.lookup(ctx.method().Identifier().toString());
    	variableId.setTypeSpec(type);
    	
    	symTabStack.pop();
    	
        return value;
    }
    
    @Override
    public Integer visitMethod(MethodContext ctx) {
        String methodName = ctx.Identifier().toString();
        
        SymTabEntry methodId = symTabStack.enterLocal(methodName);
        methodId.setDefinition(DefinitionImpl.FUNCTION);
        methodId.setAttribute(ROUTINE_SYMTAB, symTabStack.push());
        
        return visitChildren(ctx); 
    }
    
    @Override
    public Integer visitIntegerConst(IntegerConstContext ctx) {
        ctx.typeExpr = Predefined.integerType;
        return visitChildren(ctx); 
    }
    
    @Override
    public Integer visitFloatingPointConst(FloatingPointConstContext ctx) {
    	ctx.typeExpr = Predefined.floatType;
    	return visitChildren(ctx);
    }
    
    @Override
    public Integer visitCharacterConst(CharacterConstContext ctx) {
    	ctx.typeExpr = Predefined.charType;
    	return visitChildren(ctx);
    }
    
    @Override
    public Integer visitStringConst(StringConstContext ctx) {
    	ctx.typeExpr = Predefined.stringType;
    	return visitChildren(ctx);
    }
    
    @Override
    public Integer visitBooleanConst(BooleanConstContext ctx) {
    	ctx.typeExpr = Predefined.booleanType;
    	return visitChildren(ctx);
    }
    
    @Override
    public Integer visitPrimaryExp(PrimaryExpContext ctx) {
    	Integer value = visit(ctx.primary());
    	ctx.typeExpr = ctx.primary().literal().typeExpr;
    	return value;
    }
    
    @Override
    public Integer visitFunctionExpr(FunctionExprContext ctx) {
    	Integer value = visit(ctx.expression());
    	SymTabEntry variableId = symTabStack.lookup(ctx.expression().getText());
    	ctx.typeExpr = variableId.getTypeSpec();
    	return visit(ctx.expressionList());
    }
    
    @Override
    public Integer visitReturnStatement(ReturnStatementContext ctx) {
    	Integer value = visitChildren(ctx.expression());
    	SymTabEntry variableId = symTabStack.lookup(ctx.expression().getText());
    	ctx.expression().typeExpr = variableId.getTypeSpec();
    	return value;
    }
}

