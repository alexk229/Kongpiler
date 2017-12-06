package com.TeamAlexKong.compiler;

import java.io.PrintWriter;
import java.util.ArrayList;

import com.TeamAlexKong.parser.HelloBaseVisitor;
import com.TeamAlexKong.parser.HelloParser;
import com.TeamAlexKong.parser.HelloParser.AddSubOneExprContext;
import com.TeamAlexKong.parser.HelloParser.ClassDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.CompilationUnitContext;
import com.TeamAlexKong.parser.HelloParser.ElseStatementContext;
import com.TeamAlexKong.parser.HelloParser.EqualityExprContext;
import com.TeamAlexKong.parser.HelloParser.FormalParameterContext;
import com.TeamAlexKong.parser.HelloParser.FunctionExprContext;
import com.TeamAlexKong.parser.HelloParser.IfStatementContext;
import com.TeamAlexKong.parser.HelloParser.RelationalExprContext;
import com.TeamAlexKong.parser.HelloParser.ReturnStatementContext;
import com.TeamAlexKong.parser.HelloParser.VariableAssignmentContext;
import com.TeamAlexKong.parser.HelloParser.VariableExprContext;
import com.TeamAlexKong.parser.HelloParser.WhenConditionContext;
import com.TeamAlexKong.parser.HelloParser.WhenEntryContext;
import com.TeamAlexKong.parser.HelloParser.WhenStatementContext;
import com.TeamAlexKong.parser.HelloParser.WhileStatementContext;

import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;

public class TeamAlexKongVisitor2 extends HelloBaseVisitor<Integer> {
	
    private PrintWriter jFile;
    private int labelNum;
    private int endIfLabelNum;
    private int whenLabelNum;
    private boolean containsReturnType;
    private ArrayList<String> typeExprList;
    String className;
    
    private String typeCheck(String typeName) {
    	String type = "";
        if (typeName.indexOf("Array") >= 0) {
        	type = "[";
        }
        
    	type += (typeName.indexOf("Int") >= 0) ? "I"
    			: (typeName.indexOf("Float") >= 0) ? "F"
    		    : (typeName.indexOf("String") >= 0) ? "Ljava/lang/String;"
    		    : "?";
    	return type;
    }
    
    private String typeCheckExpr(TypeSpec typeExpr) {
    	String type = (typeExpr == Predefined.integerType || typeExpr == Predefined.localIntegerType) ? "I"
				 : (typeExpr == Predefined.realType || typeExpr == Predefined.localRealType) ? "F"
				 : (typeExpr == Predefined.stringType || typeExpr == Predefined.localStringType) ? "Ljava/lang/String;"		 
				 :	null;
    	return type;
    }
    
    private boolean isGlobalVariable(TypeSpec typeExpr) {
    	return (typeExpr == Predefined.integerType) 
    			|| (typeExpr == Predefined.realType) 
    			|| (typeExpr == Predefined.stringType);
    }
    
    private boolean isLocalVariable(TypeSpec typeExpr) {
    	return (typeExpr == Predefined.localIntegerType)
    			|| (typeExpr == Predefined.localRealType)
    			|| (typeExpr == Predefined.localStringType);
    }
    
    private String typeCheckForReturn(TypeSpec typeExpr) {
    	String type = (typeExpr == Predefined.integerType ||  typeExpr ==  Predefined.localIntegerType) ? "i"
				 : (typeExpr == Predefined.realType ||  typeExpr ==  Predefined.localRealType) ? "f"
				 : (typeExpr == Predefined.stringType ||  typeExpr ==  Predefined.localStringType) ? "a"
				 :	"?";
    	return type;
    }

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
        String type = "V";
        
        typeExprList = new ArrayList<String>();
        
        containsReturnType = false;
        
        if(ctx.type() != null) {
        	String typeName = ctx.type().getText();
        	type = typeCheck(typeName);
        }
        
        jFile.println();
        jFile.print(".method public static "+ methodName + "(");
        Integer value = visit(ctx.method());
        jFile.println(")" + type);
        jFile.println();
        
        value = visitChildren(ctx.methodDeclarationRest());
        
        if(!containsReturnType) {
        	jFile.println("\treturn");
        }
        
        jFile.println();
        jFile.println(".limit locals 32");
        jFile.println(".limit stack 32");
        jFile.println(".end method");
        
        return value;
	}
	
	@Override
	public Integer visitFormalParameter(FormalParameterContext ctx) {
		Integer value = visitChildren(ctx);
		String type = typeCheck(ctx.type().getText());
		typeExprList.add(type);
		jFile.print(type);
		return value;
	}
	
	@Override
	public Integer visitFunctionExpr(FunctionExprContext ctx) {
		String methodName = ctx.expression().getText();
		String typeIndicator = typeCheckExpr(ctx.typeExpr);
		String parameterTypes = "";
		
		for(int i = 0; i < ctx.expressionList().expression().size(); i++) {
			parameterTypes += typeCheck(ctx.expressionList().expression(i).getText());
		}
		
		Integer value = visit(ctx.expressionList());
		
		jFile.println("\tinvokestatic " + className + "/" + methodName + "(" +  parameterTypes + ")" + typeIndicator);
		jFile.println("\tpop");
		jFile.println();
		return value;
	}
	
	@Override
	public Integer visitReturnStatement(ReturnStatementContext ctx) {
		Integer value = visit(ctx.expression());
		String type = typeCheckForReturn(ctx.expression().typeExpr);
		
		containsReturnType = true;
		
		jFile.print("\t" + type + "return");
		jFile.println();
		
		return value;
	}
	
	@Override
	public Integer visitVariableAssignment(VariableAssignmentContext ctx) {
		
		Integer value = visit(ctx.expression());
		
		String typeIndicator = typeCheckExpr(ctx.expression().typeExpr);
								 
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
        
        String typeIndicator = typeCheckExpr(ctx.typeExpr);
       
        if(isGlobalVariable(ctx.typeExpr)) {
            // Emit a field get instruction.
            jFile.println("\tgetstatic\t" + className +
                          "/" + variableName + " " + typeIndicator);
        } else {
        	String type = typeCheckForReturn(ctx.typeExpr);
        	jFile.println("\t" + type + "load_" + typeExprList.indexOf(typeIndicator));
        }
        
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
