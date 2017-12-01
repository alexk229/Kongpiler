package com.TeamAlexKong.compiler;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.TeamAlexKong.parser.HelloBaseVisitor;
import com.TeamAlexKong.parser.HelloParser;
import com.TeamAlexKong.parser.HelloParser.AddSubOneExprContext;
import com.TeamAlexKong.parser.HelloParser.BooleanConstContext;
import com.TeamAlexKong.parser.HelloParser.ClassDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.CompilationUnitContext;
import com.TeamAlexKong.parser.HelloParser.ConstructorDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.EqualityExprContext;
import com.TeamAlexKong.parser.HelloParser.FloatingPointConstContext;
import com.TeamAlexKong.parser.HelloParser.ForControlContext;
import com.TeamAlexKong.parser.HelloParser.IntegerConstContext;
import com.TeamAlexKong.parser.HelloParser.LocalVariableDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.RelationalExprContext;
import com.TeamAlexKong.parser.HelloParser.StringConstContext;
import com.TeamAlexKong.parser.HelloParser.VariableAssignmentContext;
import com.TeamAlexKong.parser.HelloParser.VariableContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorContext;
import com.TeamAlexKong.parser.HelloParser.VariableExprContext;
import com.TeamAlexKong.parser.HelloParser.VariableInitializerContext;
import com.TeamAlexKong.parser.HelloParser.WhenConditionContext;
import com.TeamAlexKong.parser.HelloParser.WhenEntryContext;
import com.TeamAlexKong.parser.HelloParser.WhenStatementContext;
import com.TeamAlexKong.parser.HelloParser.WhileStatementContext;
import com.pcl2.parser.Pcl2Parser;

import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;
import wci.util.*;

import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.symtabimpl.DefinitionImpl.*;

public class TeamAlexKongVisitor2 extends HelloBaseVisitor<Integer> {
	
    private PrintWriter jFile;
    private int labelToJump;
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
            jFile.println();
        }
        
        Integer value = visitChildren(ctx);
        
        jFile.println();
        jFile.println("\treturn");
        jFile.println();
        jFile.println(".limit locals 32");
        jFile.println(".limit stack 32");
        jFile.println(".end method");
        
        return value;
	}
	
	@Override
	public Integer visitVariableAssignment(VariableAssignmentContext ctx) {
		Integer value = visit(ctx.variableInitializer());
		
		String typeIndicator = (ctx.variableInitializer().expression().typeExpr == Predefined.integerType) ? "I"
							 : (ctx.variableInitializer().expression().typeExpr == Predefined.realType) ? "F"
							 :	"?";
								 
        // Emit a field put instruction.
		jFile.println();
        jFile.println("\tputstatic\t" + className
                           +  "/" + ctx.variable().Identifier().toString()
                           + " " + typeIndicator);
		jFile.println();
		
        jFile.println("end:");
		jFile.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
		jFile.println("\tnew java/lang/StringBuilder");
		jFile.println("\tdup");
		jFile.println("\tldc " + '"' + ctx.variable().getText() + "= " + '"');
		jFile.println("\tinvokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V");
		jFile.println("\tgetstatic\t" + className + "/" + ctx.variable().getText()  + " " + typeIndicator);
		jFile.println("\tinvokevirtual java/lang/StringBuilder/append(" + typeIndicator + ")Ljava/lang/StringBuilder;");
		jFile.println("\tinvokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;");
		jFile.println("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		jFile.println();
		
		return value;
	}
	
	@Override
	public Integer visitVariableInitializer(VariableInitializerContext ctx) {
		jFile.print("\tldc ");
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
        
        TypeSpec type1 = ctx.expression(0).typeExpr;
        TypeSpec type2 = ctx.expression(1).typeExpr;
        
        boolean integerMode =    (type1 == Predefined.integerType)
                && (type2 == Predefined.integerType);
        boolean realMode    =    (type1 == Predefined.realType)
                && (type2 == Predefined.realType);
        
        String op = ctx.equalityOp().getText();
        String opcode;
        
        if (op.equals("==")) {
            opcode = integerMode ? "if_icmpeq"
                    : realMode    ? "if_icmpeq"
                    :               "f???";
        } else {
            opcode = integerMode ? "if_icmpne"
                    : realMode    ? "if_icmpne"
                    :               "f???";
        }
        
        // Emit an == or != instruction.
        jFile.println("\t" + opcode);
        jFile.println();
        
        return value; 
	}
	
	@Override
	public Integer visitRelationalExpr(RelationalExprContext ctx) {
        Integer value = visit(ctx.expression(0));
        
        jFile.print("\tldc ");
        value = visit(ctx.expression(1));
        jFile.println();
        
        TypeSpec type1 = ctx.expression(0).typeExpr;
        TypeSpec type2 = ctx.expression(1).typeExpr;
        
        boolean integerMode =    (type1 == Predefined.integerType)
                && (type2 == Predefined.integerType);
        boolean realMode    =    (type1 == Predefined.realType)
                && (type2 == Predefined.realType);
        
        String op = ctx.relationalOp().getText();
        String opcode;
        
        if (op.equals("<")) {
            opcode = integerMode ? "if_icmplt"
                    : realMode    ? "if_icmplt"
                    :               "f???";
        } else {
            opcode = integerMode ? "if_icmpgt"
                    : realMode    ? "if_icmpgt"
                    :               "f???";
        }
        
        // Emit an < instruction.
        jFile.print("\t" + opcode);
        
		return value;
	}
	
	@Override
	public Integer visitAddSubOneExpr(AddSubOneExprContext ctx) {
		Integer value = visit(ctx.expression());
		
        TypeSpec type = ctx.expression().typeExpr;
		
        boolean integerMode = (type == Predefined.integerType);
        boolean realMode = (type == Predefined.realType);
        
        String typeIndicator = integerMode ? "I"
                				: realMode ? "F"
                				: 			 "?";
        
        String op = ctx.addSubOneOp().getText();
        String opcode;
        
        if (op.equals("++")) {
            opcode = integerMode ? "iadd"
                    :               "f???";
        } else {
            opcode = integerMode ? "isub"
                    :               "f???";
        }
        
        // Emit an ++ or -- instruction.
        jFile.println("\tldc 1");
        jFile.println("\t" + opcode);
        jFile.println("\tputstatic\t" + className
                +  "/" + ctx.expression().getText()
                + " " + typeIndicator);
        
		return value;
	}
	
	@Override
	public Integer visitIntegerConst(IntegerConstContext ctx) {
        // Emit a load constant instruction.
        jFile.print(ctx.getText());
        
        return visitChildren(ctx);
	}
	
	@Override
	public Integer visitFloatingPointConst(FloatingPointConstContext ctx) {
        // Emit a load constant instruction.
        jFile.print(ctx.getText());
        return visitChildren(ctx); 
	}
	
	@Override
	public Integer visitStringConst(StringConstContext ctx) {
        // Emit a load constant instruction.
        jFile.print(ctx.getText());
        return visitChildren(ctx);
	}
	
	@Override
	public Integer visitBooleanConst(BooleanConstContext ctx) {
		
        // Emit a load constant instruction.
        jFile.print(ctx.getText());
        return visitChildren(ctx);
	}
	
	@Override
	public Integer visitWhileStatement(WhileStatementContext ctx) {
		jFile.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
		jFile.println("\t" + "ldc " + '"' + "While Loop..." + '"');
		jFile.println("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		jFile.println();
		jFile.println("\tgoto whileCompare");
		jFile.println("whileLoop:");
		Integer value = visit(ctx.statement());
		
		jFile.println("whileCompare: ");
		value = visit(ctx.parExpression());
		jFile.println(" whileLoop");
		
		return value;
	}
	
	@Override
	public Integer visitWhenStatement(WhenStatementContext ctx) {
		jFile.println();
		jFile.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
		jFile.println("\t" + "ldc " + '"' + "When statement..." + '"');
		jFile.println("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		jFile.println();
		
		Integer value = visit(ctx.parExpression());
		
		TypeSpec type = ctx.parExpression().expression().typeExpr;

		String variableName = ctx.parExpression().expression().getText();
        boolean integerMode = (type == Predefined.integerType);
        boolean realMode = (type == Predefined.realType);
        String typeIndicator = integerMode ? "I"
				: realMode ? "F"
				: 			 "?";
		
		jFile.println("\tlookupswitch");
		
		int entries = ctx.whenEntry().size();
		
		for (int i = 0; i < entries; i++) {
			visit(ctx.whenEntry(i));
		}
		
		for (int i = 0; i < ctx.whenEntry().size(); i++) {
			jFile.println("whenLabel" + (i + 1) + ":");
			visit(ctx.whenEntry(i).statement());
			jFile.println("\tgoto end");
			jFile.println();
		}
		
        jFile.println("end:");
		jFile.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
		jFile.println("\tnew java/lang/StringBuilder");
		jFile.println("\tdup");
		jFile.println("\tldc " + '"' + variableName + "= " + '"');
		jFile.println("\tinvokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V");
		jFile.println("\tgetstatic\t" + className + "/" + variableName + " " + typeIndicator);
		jFile.println("\tinvokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;");
		jFile.println("\tinvokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;");
		jFile.println("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		jFile.println();

		return value;
	}
	
	@Override
	public Integer visitWhenEntry(WhenEntryContext ctx) {
		Integer value = null;
		
		if(ctx.whenCondition() != null) {
			value = visit(ctx.whenCondition());
		} else {
			labelToJump++;
			jFile.println("\t\tdefault: whenLabel" + labelToJump);
			jFile.println();
		}
		
		return value;
	}
	
	@Override
	public Integer visitWhenCondition(WhenConditionContext ctx) {
		jFile.print("\t\t");
		Integer value = visit(ctx.expression());
		if (ctx.expression() != null) {
			labelToJump++;
			jFile.println(": " + "whenLabel" + labelToJump);
		}
		return value;
	}
}
