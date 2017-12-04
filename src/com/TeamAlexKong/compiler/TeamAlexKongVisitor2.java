package com.TeamAlexKong.compiler;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTree;

import com.TeamAlexKong.parser.HelloBaseVisitor;
import com.TeamAlexKong.parser.HelloParser;
import com.TeamAlexKong.parser.HelloParser.AddSubOneExprContext;
import com.TeamAlexKong.parser.HelloParser.BooleanConstContext;
import com.TeamAlexKong.parser.HelloParser.ClassDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.CompilationUnitContext;
import com.TeamAlexKong.parser.HelloParser.ConstructorDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.ElseStatementContext;
import com.TeamAlexKong.parser.HelloParser.EqualityExprContext;
import com.TeamAlexKong.parser.HelloParser.FloatingPointConstContext;
import com.TeamAlexKong.parser.HelloParser.ForControlContext;
import com.TeamAlexKong.parser.HelloParser.IfStatementContext;
import com.TeamAlexKong.parser.HelloParser.IntegerConstContext;
import com.TeamAlexKong.parser.HelloParser.IsExprContext;
import com.TeamAlexKong.parser.HelloParser.IsOpContext;
import com.TeamAlexKong.parser.HelloParser.LocalVariableDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.RelationalExprContext;
import com.TeamAlexKong.parser.HelloParser.StringConstContext;
import com.TeamAlexKong.parser.HelloParser.VariableAssignmentContext;
import com.TeamAlexKong.parser.HelloParser.VariableContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorIdContext;
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
    private int labelNum;
    private int endIfLabelNum;
    private int whenLabelNum;
    String className;
    
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
        jFile.println(".limit locals 100");
        jFile.println(".limit stack 100");
        jFile.println(".end method");
        
        return value;
	}
	
	@Override
	public Integer visitVariableAssignment(VariableAssignmentContext ctx) {
		
		Integer value = visit(ctx.expression());
		
		String typeIndicator = (ctx.expression().typeExpr == Predefined.integerType) ? "I"
							 : (ctx.expression().typeExpr == Predefined.realType) ? "F"
							 : (ctx.expression().typeExpr == Predefined.realType) ? "Ljava/lang/String;"		 
							 :	"?";
								 
        // Emit a field put instruction.
		jFile.println("\tldc " + ctx.expression().getText());
        jFile.println("\tputstatic\t" + className
                           +  "/" + ctx.variable().Identifier().toString()
                           + " " + typeIndicator);
		jFile.println();
		
		return value;
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
        value = visit(ctx.expression(1));
        
        jFile.println("\tldc " + ctx.expression(1).getText());
        
        TypeSpec type1 = ctx.expression(0).typeExpr;
        TypeSpec type2 = ctx.expression(1).typeExpr;
        
        boolean integerMode =    (type1 == Predefined.integerType)
                && (type2 == Predefined.integerType);
        boolean realMode    =    (type1 == Predefined.realType)
                && (type2 == Predefined.realType);
        
        String op = ctx.relationalOp().getText();
        String opcode;
        
        if (op.equals("<")) {
            opcode = integerMode ? "if_icmpge"
                    : realMode    ? "if_icmpge"
                    :               "f???";
        } else {
            opcode = integerMode ? "if_icmple"
                    : realMode    ? "if_icmple"
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
            		: realMode	 ? "fadd"
                    :               "f???";
        } else {
            opcode = integerMode ? "isub"
            		: realMode	 ? "fsub"
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
	public Integer visitIsExpr(IsExprContext ctx) {
		// TODO Auto-generated method stub
		return super.visitIsExpr(ctx);
	}
	
	@Override
	public Integer visitWhileStatement(WhileStatementContext ctx) {
		
		int currentLabelNum = labelNum;
		
		jFile.println();
		jFile.println(Label.WHILE_COMPARE.toString() + currentLabelNum + ": ");
		Integer value = visit(ctx.parExpression());
		jFile.println("\t" + Label.LABEL + currentLabelNum);
		
		jFile.println("\tgoto\t\t" + Label.WHILE_LOOP + currentLabelNum);
		jFile.println();
		jFile.println(Label.WHILE_LOOP.toString() + currentLabelNum + ": ");
		
		value = visit(ctx.statement());
		jFile.println("\tgoto\t\t" + Label.WHILE_COMPARE + currentLabelNum);
		jFile.println();
		jFile.println(Label.LABEL.toString() + currentLabelNum + ":");
		
		labelNum++;
		
		return value;
	}
	
	@Override
	public Integer visitWhenStatement(WhenStatementContext ctx) {
		
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
		int currentLabelNum = labelNum;
		int[] whenLabelNums = new int[entries];
		
		for (int i = 0; i < entries; i++) {
			whenLabelNum += 1;
			whenLabelNums[i] = whenLabelNum;
			visit(ctx.whenEntry(i));
		}
		
		for (int i = 0; i < entries; i++) {
			jFile.println(Label.WHEN.toString() + whenLabelNums[i] + ":");
			visit(ctx.whenEntry(i).statement());
			jFile.println("\tgoto\t\t" + Label.WHEN_END + currentLabelNum);
			jFile.println();
		}
		
        jFile.println(Label.WHEN_END.toString() + currentLabelNum + ":");
        
        labelNum++;

		return value;
	}
	
	@Override
	public Integer visitWhenEntry(WhenEntryContext ctx) {
		Integer value = null;
		
		if(ctx.whenCondition() != null) {
			value = visit(ctx.whenCondition());
		} else {
			jFile.println("\t\tdefault: " + Label.WHEN + whenLabelNum);
			jFile.println();
		}
		
		return value;
	}
	
	@Override
	public Integer visitWhenCondition(WhenConditionContext ctx) {
		jFile.print("\t\t");
		Integer value = visit(ctx.expression());
		if (ctx.expression() != null) {
			jFile.println(ctx.expression().getText() + ": " + Label.WHEN + whenLabelNum);
		}
		return value;
	}
	
	@Override
	public Integer visitIfStatement(IfStatementContext ctx) {
		Integer value = visit(ctx.parExpression());
		
		if(ctx.elseStatement() == null) {
			jFile.println("\t" + Label.ELSE + endIfLabelNum);
		} else {
			jFile.println("\t" + Label.IF + labelNum);
		}
		jFile.println();
		value = visit(ctx.statement());
		jFile.println("\tgoto\t\t" + Label.ELSE + endIfLabelNum);
		jFile.println();
		if(ctx.elseStatement() != null) {
			value = visit(ctx.elseStatement());
			if(ctx.elseStatement().statement().ifStatement() == null) {
				jFile.println(Label.ELSE.toString() + endIfLabelNum + ":");
				endIfLabelNum++;
			}
			
		}
		
		if(ctx.elseStatement() == null) {
			jFile.println(Label.ELSE.toString() + endIfLabelNum + ":");
			endIfLabelNum++;
		}
		
		return value;
	}
	
	@Override
	public Integer visitElseStatement(ElseStatementContext ctx) {
		jFile.println(Label.IF.toString() + labelNum + ":");
		labelNum++;
		Integer value = visit(ctx.statement());
		return value;
	}
}
