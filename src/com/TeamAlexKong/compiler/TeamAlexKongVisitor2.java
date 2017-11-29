package com.TeamAlexKong.compiler;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.TeamAlexKong.parser.HelloBaseVisitor;
import com.TeamAlexKong.parser.HelloParser;
import com.TeamAlexKong.parser.HelloParser.ClassDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.CompilationUnitContext;
import com.TeamAlexKong.parser.HelloParser.ConstructorDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.ForControlContext;
import com.TeamAlexKong.parser.HelloParser.LocalVariableDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.VariableAssignmentContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorContext;
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
            jFile.println("\t.limit stack 2");
            jFile.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream");
            jFile.println("\tldc " + "'c'");
            jFile.println("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        }
        jFile.println("\treturn");
        jFile.println(".end method");
        
        return visitChildren(ctx);
	}
	
//	@Override
//	public Integer visitVariableAssignment(VariableAssignmentContext ctx) {
//		Integer value = visit(ctx.variableDeclaratorId());
//		
//		String typeIndicator = (ctx.variableInitializer().getClass().getName().equalsIgnoreCase("int")) ? "I"
//							 : (ctx.variableInitializer().getClass().getName().equalsIgnoreCase("double")) ? "D"
//							 :	"?";
//		
//		System.out.println("\tputstatic\t" + className
//                +  "/" + ctx.variableDeclaratorId().Identifier().toString()
//                + " " + typeIndicator);
//								 
//        // Emit a field put instruction.
//        jFile.println("\tputstatic\t" + className
//                           +  "/" + ctx.variableDeclaratorId().Identifier().toString()
//                           + " " + typeIndicator);
//								 
//		return value;
//	}
	
	@Override
	public Integer visitWhenStatment(WhenStatmentContext ctx) {
		
		return super.visitWhenStatment(ctx);
	}
}
