package com.TeamAlexKong.compiler;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.TeamAlexKong.parser.HelloBaseVisitor;
import com.TeamAlexKong.parser.HelloParser;
import com.TeamAlexKong.parser.HelloParser.ClassDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.CompilationUnitContext;
import com.TeamAlexKong.parser.HelloParser.ConstructorDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.ExpressionContext;
import com.TeamAlexKong.parser.HelloParser.FieldDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.FloatingPointConstContext;
import com.TeamAlexKong.parser.HelloParser.IntegerConstContext;
import com.TeamAlexKong.parser.HelloParser.IntegerLiteralContext;
import com.TeamAlexKong.parser.HelloParser.LiteralContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorIdContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorsContext;
import com.TeamAlexKong.parser.HelloParser.VariableTypeContext;
import com.pcl2.parser.Pcl2Parser;

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
    	
        Integer value = visitChildren(ctx); 
        
        // Emit the class constructor.
        jFile.println();
        jFile.println(".method public <init>()V");
        jFile.println();
        jFile.println("\taload_0");
        jFile.println("\tinvokenonvirtual    java/lang/Object/<init>()V");
        jFile.println("\treturn");
        jFile.println();
        jFile.println(".limit locals 1");
        jFile.println(".limit stack 1");
        jFile.println(".end method");
        
        return value;
    }
    
    @Override
    public Integer visitFieldDeclaration(FieldDeclarationContext ctx) {
    	jFile.println("\n; " + ctx.getText() + "\n");
        return visitChildren(ctx); 
    }
    
    @Override
    public Integer visitVariableDeclarators(VariableDeclaratorsContext ctx) {
    	variableIdList = new ArrayList<SymTabEntry>();
    	return visitChildren(ctx);  
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
    public Integer visitVariableType(VariableTypeContext ctx) {
    	String typeName = ctx.getText();
    	
        TypeSpec type;
        String   typeIndicator;
        
        if (typeName.equalsIgnoreCase("int")) {
            type = Predefined.integerType;
            typeIndicator = "I";
        }
        else if (typeName.equalsIgnoreCase("double")) {
            type = Predefined.realType;
            typeIndicator = "D";
        } 
        else {
            type = null;
            typeIndicator = "?";
        }
                    
        for (SymTabEntry id : variableIdList) {
            id.setTypeSpec(type);
            
            // Emit a field declaration.
            jFile.println(".field private static " +
                               id.getName() + " " + typeIndicator);
        }
        return visitChildren(ctx);
    }
    
    @Override
    public Integer visitIntegerConst(IntegerConstContext ctx) {
        ctx.typeLiteral = Predefined.integerType;
        return visitChildren(ctx); 
    }
    
    @Override
    public Integer visitFloatingPointConst(FloatingPointConstContext ctx) {
    	ctx.typeLiteral = Predefined.realType;
    	return visitChildren(ctx);
    }
}

