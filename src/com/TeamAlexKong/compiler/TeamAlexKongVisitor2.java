package com.TeamAlexKong.compiler;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.TeamAlexKong.parser.HelloBaseVisitor;
import com.TeamAlexKong.parser.HelloParser;
import com.TeamAlexKong.parser.HelloParser.ClassDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.CompilationUnitContext;
import com.TeamAlexKong.parser.HelloParser.ConstructorDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.EqualityExprContext;
import com.TeamAlexKong.parser.HelloParser.FloatingPointConstContext;
import com.TeamAlexKong.parser.HelloParser.ForControlContext;
import com.TeamAlexKong.parser.HelloParser.IntegerConstContext;
import com.TeamAlexKong.parser.HelloParser.LocalVariableDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.StringConstContext;
import com.TeamAlexKong.parser.HelloParser.VariableAssignmentContext;
import com.TeamAlexKong.parser.HelloParser.VariableContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorContext;
import com.TeamAlexKong.parser.HelloParser.VariableExprContext;
import com.TeamAlexKong.parser.HelloParser.VariableInitializerContext;
import com.TeamAlexKong.parser.HelloParser.WhenStatmentContext;
import com.pcl2.parser.Pcl2Parser;

import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;
import wci.util.*;

import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.symtabimpl.DefinitionImpl.*;

public class TeamAlexKongVisitor2 extends HelloBaseVisitor<Integer> {
	
    private SymTabStack symTabStack;
    private SymTabEntry programId;
    private ArrayList<SymTabEntry> variableIdList;
    private PrintWriter jFile;
    String className, classImplementationName;
    
    public TeamAlexKongVisitor2(PrintWriter jFile) {
    	this.jFile = jFile;
    }
    
    @Override
    public Integer visitCompilationUnit(CompilationUnitContext ctx) {
        Integer value = visitChildren(ctx); 
        jFile.close();
        return value;
    }
    
    @Override
    public Integer visitClassDeclaration(ClassDeclarationContext ctx) {
    	className = ctx.Identifier().toString();
    	return visitChildren(ctx);
    }
	
	@Override
	public Integer visitMethodDeclaration(HelloParser.MethodDeclarationContext ctx) { 
        String methodName = ctx.method().Identifier().getText();
        
        if (methodName.equals("main")) {
            jFile.println();
            jFile.println(".method public static "+ methodName + "([Ljava/lang/String;)V");
        }
        
        Integer value = visitChildren(ctx);
        
        jFile.println();
        jFile.println("\treturn");
        jFile.println();
        jFile.println(".limit locals 16");
        jFile.println(".limit stack 16");
        jFile.println(".end method");
        
        return value;
	}
	
	@Override
	public Integer visitVariableAssignment(VariableAssignmentContext ctx) {
		Integer value = visit(ctx.variableInitializer());
		
		String typeIndicator = (ctx.variableInitializer().expression().typeExpr == Predefined.integerType) ? "I"
							 : (ctx.variableInitializer().expression().typeExpr == Predefined.realType) ? "D"
							 :	"?";
								 
        // Emit a field put instruction.
        jFile.println("\tputstatic\t" + className
                           +  "/" + ctx.variable().Identifier().toString()
                           + " " + typeIndicator);
								 
		return value;
	}
	
	@Override
	public Integer visitVariableInitializer(VariableInitializerContext ctx) {
		String value = ctx.getText();
		System.out.println(value);
		return super.visitVariableInitializer(ctx);
	}
	
	@Override
	public Integer visitVariableExpr(VariableExprContext ctx) {
        String variableName = ctx.variable().Identifier().toString();
        TypeSpec type = ctx.typeExpr;
        
        String typeIndicator = (type == Predefined.integerType) ? "I"
                             : (type == Predefined.realType)    ? "F"
                             :                                    "?";
        
        // Emit a field get instruction.
        jFile.println("\tgetstatic\t" + className +
                      "/" + variableName + " " + typeIndicator);
        
        return visitChildren(ctx); 
	}
	
	@Override
	public Integer visitEqualityExpr(EqualityExprContext ctx) {
        Integer value = visitChildren(ctx);
        
        String op = ctx.equalityOp().getText();
        String opcode;
        
        if (op.equals("==")) {
            opcode = "if_icmpeq";
        } else {
        	opcode = "if_icmpne";
        }
        
        // Emit an add or subtract instruction.
        jFile.println("\t" + opcode);
        
        return value; 
	}
	
	@Override
	public Integer visitIntegerConst(IntegerConstContext ctx) {
        // Emit a load constant instruction.
        jFile.println("\tldc\t" + ctx.getText());
        
        return visitChildren(ctx);
	}
	
	@Override
	public Integer visitFloatingPointConst(FloatingPointConstContext ctx) {
        // Emit a load constant instruction.
        jFile.println("\tldc\t" + ctx.getText());
        return visitChildren(ctx); 
	}
	
	@Override
	public Integer visitStringConst(StringConstContext ctx) {
        // Emit a load constant instruction.
        jFile.println("\tldc\t" + ctx.getText());
        
		return super.visitStringConst(ctx);
	}
	
	@Override
	public Integer visitWhenStatment(WhenStatmentContext ctx) {
		Integer value = visitChildren(ctx);
		return value;
	}
	
}
