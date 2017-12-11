package com.TeamAlexKong.compiler;

import java.io.PrintWriter;
import java.util.ArrayList;

import com.TeamAlexKong.parser.HelloBaseVisitor;
import com.TeamAlexKong.parser.HelloParser;
import com.TeamAlexKong.parser.HelloParser.AddSubOneExprContext;
import com.TeamAlexKong.parser.HelloParser.AdditiveExprContext;
import com.TeamAlexKong.parser.HelloParser.ClassDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.CompilationUnitContext;
import com.TeamAlexKong.parser.HelloParser.ElseStatementContext;
import com.TeamAlexKong.parser.HelloParser.EqualityExprContext;
import com.TeamAlexKong.parser.HelloParser.FloatingPointConstContext;
import com.TeamAlexKong.parser.HelloParser.ForControlContext;
import com.TeamAlexKong.parser.HelloParser.ForLoopStatementContext;
import com.TeamAlexKong.parser.HelloParser.FormalParameterContext;
import com.TeamAlexKong.parser.HelloParser.FunctionExprContext;
import com.TeamAlexKong.parser.HelloParser.IfStatementContext;
import com.TeamAlexKong.parser.HelloParser.IntegerConstContext;
import com.TeamAlexKong.parser.HelloParser.LocalVariableDeclarationContext;
import com.TeamAlexKong.parser.HelloParser.LocalVariableDeclarationStatementContext;
import com.TeamAlexKong.parser.HelloParser.MultiplicativeExprContext;
import com.TeamAlexKong.parser.HelloParser.RangeExprContext;
import com.TeamAlexKong.parser.HelloParser.RelationalExprContext;
import com.TeamAlexKong.parser.HelloParser.ReturnStatementContext;
import com.TeamAlexKong.parser.HelloParser.StringConstContext;
import com.TeamAlexKong.parser.HelloParser.VariableAssignmentContext;
import com.TeamAlexKong.parser.HelloParser.VariableDeclaratorContext;
import com.TeamAlexKong.parser.HelloParser.VariableExprContext;
import com.TeamAlexKong.parser.HelloParser.VariableInitializerContext;
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
    private int forLoopLabelNum;
    private boolean containsReturnType;
    private ArrayList<String> variableNameList;
    private boolean isDeclaringLocalVariable;
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
    	
    	variableNameList = new ArrayList<String>();
    	
    	return visitChildren(ctx);
    }
    
	@Override
	public Integer visitMethodDeclaration(HelloParser.MethodDeclarationContext ctx) { 
        String methodName = ctx.method().Identifier().getText();
        String type = "V";
        
        variableNameList = new ArrayList<String>();
        
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
        jFile.println(".limit locals 100");
        jFile.println(".limit stack 100");
        jFile.println(".end method");
        
        isDeclaringLocalVariable = false;
        
        return value;
	}
	
	@Override
	public Integer visitFormalParameter(FormalParameterContext ctx) {
		Integer value = visitChildren(ctx);
		String type = typeCheck(ctx.type().getText());
		
		variableNameList.add(ctx.parameterVariableId().getText());
		jFile.print(type);
		return value;
	}
	
	@Override
	public Integer visitFunctionExpr(FunctionExprContext ctx) {
		String methodName = ctx.expression().getText();
		String typeIndicator = typeCheckExpr(ctx.typeExpr);
		String parameterTypes = "";
		
		Integer value = null;
		
		if(ctx.expressionList() != null) {
			for(int i = 0; i < ctx.expressionList().expression().size(); i++) {
				if(ctx.expressionList().expression(i).getText().equals("i")) {
					ctx.expressionList().expression(i).typeExpr = Predefined.localIntegerType;
				}
				parameterTypes += typeCheckExpr(ctx.expressionList().expression(i).typeExpr);
			}
			if(methodName.equals("println")) {
				jFile.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
				value = visit(ctx.expressionList());
				jFile.println("\tinvokevirtual java/io/PrintStream/println(" + parameterTypes + ")V");
				jFile.println();
			} else {
				value = visit(ctx.expressionList());
			}
		}
		
		if(!methodName.equals("println")) {
			jFile.println("\tinvokestatic " + className + "/" + methodName + "(" +  parameterTypes + ")" + typeIndicator);
		}
		

		return value;
	}
	
	@Override
	public Integer visitReturnStatement(ReturnStatementContext ctx) {
		Integer value = visit(ctx.expression());
		String type = typeCheckForVariable(ctx.expression().typeExpr);
		
		containsReturnType = true;
		
		jFile.print("\t" + type + "return");
		jFile.println();
		
		return value;
	}
	
	@Override
	public Integer visitLocalVariableDeclaration(LocalVariableDeclarationContext ctx) {
		isDeclaringLocalVariable = true;
		Integer value = visitChildren(ctx);
		
		return value;
	}
	
	@Override
	public Integer visitVariableDeclarator(VariableDeclaratorContext ctx) {
		String variableName = ctx.variableDeclaratorId().Identifier().toString();
		String typeName = "";
		if(ctx.variableType() != null) {
			typeName = ctx.variableType().getText();
		}
		variableNameList.add(variableName);
		
		Integer value = null;
		
		if(isDeclaringLocalVariable) {
			if(ctx.variableInitializer() != null) {
				value = visit(ctx.variableInitializer());
				String type = typeCheckForVariable(typeName);
				jFile.println("\t" + type + "store_" + variableNameList.indexOf(variableName));
			}
		}
		
		return value;
	}
	
	@Override
	public Integer visitVariableAssignment(VariableAssignmentContext ctx) {
		String variableName = ctx.expression(0).getText();
		Integer value = visit(ctx.expression(1));
		
		String typeIndicator = typeCheckExpr(ctx.expression(0).typeExpr);
		
		if(isGlobalVariable(ctx.expression(0).typeExpr)) {
	        // Emit a field put instruction.
	        jFile.println("\tputstatic\t" + className
	                           +  "/" + ctx.expression(0).getText()
	                           + " " + typeIndicator);
		} else {
			String type = typeCheckForVariable(ctx.expression(0).typeExpr);
			jFile.println("\t" + type + "store_" + variableNameList.indexOf(variableName));
		}
								 
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
        	String type = typeCheckForVariable(ctx.typeExpr);
        	jFile.println("\t" + type + "load_" + variableNameList.indexOf(variableName));
        }
        
        return visitChildren(ctx); 
	}
	
	@Override
	public Integer visitEqualityExpr(EqualityExprContext ctx) {
        Integer value = visitChildren(ctx);
        
        TypeSpec type1 = ctx.expression(0).typeExpr;
        TypeSpec type2 = ctx.expression(1).typeExpr;
        
        boolean integerMode =    ((type1 == Predefined.integerType) || (type1 == Predefined.localIntegerType))
  			  		&& ((type2 == Predefined.integerType) || (type2 == Predefined.localIntegerType));
        boolean floatMode   =    ((type1 == Predefined.floatType) || (type1 == Predefined.localFloatType))
				  	&& ((type1 == Predefined.floatType) || (type1 == Predefined.localFloatType));
        boolean doubleMode   =    ((type1 == Predefined.doubleType) || (type1 == Predefined.localDoubleType))
				  	&& ((type1 == Predefined.doubleType) || (type1 == Predefined.localDoubleType));
        
        String op = ctx.equalityOp().getText();
        String opcode;
        
        if (op.equals("==")) {
            opcode = integerMode ? "if_icmpne"
                    : floatMode    ? "ifne"
                    : doubleMode ? "ifne"
                    :               "f???";
        } else {
            opcode = integerMode ? "if_icmpeq"
                    : floatMode    ? "ifeq"
                    : doubleMode ? "ifeq"
                    :               "f???";
        }
        
        if(doubleMode) {
        	jFile.println("\tdcmpg");
        } else if(floatMode) {
        	jFile.println("\tfcmpg");
        }
        
        // Emit an == or != instruction.
        jFile.print("\t" + opcode);
        
        return value; 
	}
	
	@Override
	public Integer visitRelationalExpr(RelationalExprContext ctx) {
        Integer value = visitChildren(ctx);
        
        TypeSpec type1 = ctx.expression(0).typeExpr;
        TypeSpec type2 = ctx.expression(1).typeExpr;
        
        boolean integerMode =    ((type1 == Predefined.integerType) || (type1 == Predefined.localIntegerType))
			  		&& ((type2 == Predefined.integerType) || (type2 == Predefined.localIntegerType));
        boolean floatMode   =    ((type1 == Predefined.floatType) || (type1 == Predefined.localFloatType))
			  		&& ((type1 == Predefined.floatType) || (type1 == Predefined.localFloatType));
        boolean doubleMode   =    ((type1 == Predefined.doubleType) || (type1 == Predefined.localDoubleType))
			  		&& ((type1 == Predefined.doubleType) || (type1 == Predefined.localDoubleType));
        
        String op = ctx.relationalOp().getText();
        String opcode;
        
        if (op.equals("<")) {
            opcode = integerMode ? "if_icmpge"
                    : floatMode    ? "if_icmpge"
                    : doubleMode	? "if_icmpge"
                    :               "f???";
        } else {
            opcode = integerMode ? "if_icmple"
                    : floatMode    ? "if_icmple"
                    : doubleMode ? "if_icmple"
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
		
        boolean integerMode = (type == Predefined.integerType) || (type == Predefined.localIntegerType);
        boolean floatMode = (type == Predefined.floatType) || (type == Predefined.localIntegerType);
        boolean doubleMode = (type == Predefined.doubleType) || (type == Predefined.localDoubleType);
        
        String typeIndicator = integerMode ? "I"
                				: floatMode ? "F"
                			    : doubleMode ? "D"
                				: 			 "?";
        
        String op = ctx.addSubOneOp().getText();
        String opcode;
        
        if (op.equals("++")) {
            opcode = integerMode ? "iadd"
            		: floatMode	 ? "fadd"
            		: doubleMode ? "dadd"
                    :               "f???";
        } else {
            opcode = integerMode ? "isub"
            		: floatMode	 ? "fsub"
            	    : doubleMode ? "dsub"
                    :               "f???";
        }
        
        // Emit an ++ or -- instruction.
        jFile.println("\t" + typeIndicator.toLowerCase() + "const_1");
        jFile.println("\t" + opcode);
        if(isGlobalVariable(type)) {
            jFile.println("\tputstatic\t" + className
                    +  "/" + ctx.expression().getText()
                    + " " + typeIndicator);
        } else {
        	jFile.println("\t" + typeIndicator.toLowerCase() + "store_" + variableNameList.indexOf(ctx.expression().getText()));
        }
        
		return value;
	}
	
	@Override
	public Integer visitAdditiveExpr(AdditiveExprContext ctx) {
		Integer value = visitChildren(ctx);
		
        TypeSpec type1 = ctx.expression(0).typeExpr;
        TypeSpec type2 = ctx.expression(1).typeExpr;
        
        boolean integerMode =    ((type1 == Predefined.integerType) || (type1 == Predefined.localIntegerType))
			  		&& ((type2 == Predefined.integerType) || (type2 == Predefined.localIntegerType));
        boolean floatMode   =    ((type1 == Predefined.floatType) || (type1 == Predefined.localFloatType))
			  		&& ((type1 == Predefined.floatType) || (type1 == Predefined.localFloatType));
        boolean doubleMode   =    ((type1 == Predefined.doubleType) || (type1 == Predefined.localDoubleType))
			  		&& ((type1 == Predefined.doubleType) || (type1 == Predefined.localDoubleType));
        
        String op = ctx.additiveOp().getText();
        String opcode;
        
        switch(op) {
        case "+":
            opcode = integerMode ? "iadd"
                    : floatMode  ? "fadd"
                    : doubleMode ? "dadd"
                    :              "f???";
        	break;
        case "-":
            opcode = integerMode ? "isub"
                    : floatMode  ? "fsub"
                    : doubleMode ? "dsub"
                    :              "f???";
        	break;
        default:
        	opcode = "f???";
        	break;
        }
        
        // Emit an +, - instruction.
        jFile.println("\t" + opcode);
        
        return value;
	}
	
	@Override
	public Integer visitMultiplicativeExpr(MultiplicativeExprContext ctx) {
		Integer value = visitChildren(ctx);
		
        TypeSpec type1 = ctx.expression(0).typeExpr;
        TypeSpec type2 = ctx.expression(1).typeExpr;
        
        boolean integerMode =    ((type1 == Predefined.integerType) || (type1 == Predefined.localIntegerType))
			  		&& ((type2 == Predefined.integerType) || (type2 == Predefined.localIntegerType));
        boolean floatMode   =    ((type1 == Predefined.floatType) || (type1 == Predefined.localFloatType))
			  		&& ((type1 == Predefined.floatType) || (type1 == Predefined.localFloatType));
        boolean doubleMode   =    ((type1 == Predefined.doubleType) || (type1 == Predefined.localDoubleType))
			  		&& ((type1 == Predefined.doubleType) || (type1 == Predefined.localDoubleType));
        
        String op = ctx.multiplicativeOp().getText();
        String opcode;
        
        switch(op) {
        case "*":
            opcode = integerMode ? "imul"
                    : floatMode  ? "fmul"
                    : doubleMode ? "dmul"
                    :              "f???";
        	break;
        case "/":
            opcode = integerMode ? "idiv"
                    : floatMode  ? "fdiv"
                    : doubleMode ? "ddiv"
                    :              "f???";
        	break;
        default:
        	opcode = "f???";
        	break;
        }
        
        // Emit an *, / instruction.
        jFile.println("\t" + opcode);
		
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
		if (ctx.expression() != null) {
			jFile.println(ctx.expression().getText() + ": " + Label.WHEN + whenLabelNum);
		}
		return null;
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
	
	@Override
	public Integer visitForLoopStatement(ForLoopStatementContext ctx) {
		Integer value = visitChildren(ctx);
		jFile.println("\tiinc " + (variableNameList.size() - 2) + " 1");
		jFile.println("\tgoto\t" + Label.FOR_LOOP + forLoopLabelNum);
		jFile.println(Label.FOR_LOOP_END.toString() + forLoopLabelNum + ":");
		forLoopLabelNum++;
		return value;
	}
	
	@Override
	public Integer visitForControl(ForControlContext ctx) {
		
		variableNameList.add(ctx.variableDeclarator().variableDeclaratorId().Identifier().toString());
		
		Integer value = visit(ctx.expression());
		
		return value;
	}
	
	@Override
	public Integer visitRangeExpr(RangeExprContext ctx) {
		
		Integer value = visit(ctx.expression(0));
	
		jFile.println("\tistore_" + (variableNameList.size() - 1));
		
		value = visit(ctx.expression(1));
		
		variableNameList.add("rangeExpr" + forLoopLabelNum);
		
		jFile.println("\tistore_" + (variableNameList.size() - 1));
		jFile.println(Label.FOR_LOOP.toString() + forLoopLabelNum + ":");
		jFile.println("\tiload_" + (variableNameList.size() - 2));
		jFile.println("\tiload_" + (variableNameList.size() - 1));
		jFile.println("\tif_icmpge " + Label.FOR_LOOP_END + forLoopLabelNum);
		
		return value;
	}
	
	@Override
	public Integer visitIntegerConst(IntegerConstContext ctx) {
		jFile.println("\tbipush " + ctx.getText());
		return visitChildren(ctx);
	}
	
	@Override
	public Integer visitFloatingPointConst(FloatingPointConstContext ctx) {
		if(ctx.getText().indexOf("F") >= 0) {
			jFile.println("\tldc " + ctx.getText());
		} else {
			jFile.println("\tldc2_w " + ctx.getText());
		}
		return visitChildren(ctx);
	}
	
	@Override
	public Integer visitStringConst(StringConstContext ctx) {
		jFile.println("\tldc " + ctx.getText());
		return visitChildren(ctx);
	}
	
	//Helper methods
    private String typeCheck(String typeName) {
    	String type = "";
        if (typeName.indexOf("Array") >= 0) {
        	type = "[";
        }
        
    	type += (typeName.indexOf(Literal.INT.toString()) >= 0) ? "I"
    			: (typeName.indexOf(Literal.FLOAT.toString()) >= 0) ? "F"
    			: (typeName.indexOf(Literal.DOUBLE.toString()) >= 0) ? "D"
    		    : (typeName.indexOf(Literal.STRING.toString()) >= 0) ? "Ljava/lang/String;"
    		    : "?";
    	return type;
    }
    
    private String typeCheckExpr(TypeSpec typeExpr) {
    	String type = (typeExpr == Predefined.integerType || typeExpr == Predefined.localIntegerType) ? "I"
				 : (typeExpr == Predefined.floatType || typeExpr == Predefined.localFloatType) ? "F"
				 : (typeExpr == Predefined.doubleType || typeExpr == Predefined.localDoubleType) ? "D" 
				 : (typeExpr == Predefined.stringType || typeExpr == Predefined.localStringType) ? "Ljava/lang/String;"		 
				 :	null;
    	return type;
    }
    
    private boolean isGlobalVariable(TypeSpec typeExpr) {
    	return (typeExpr == Predefined.integerType) 
    			|| (typeExpr == Predefined.floatType) 
    			|| (typeExpr == Predefined.doubleType)
    			|| (typeExpr == Predefined.stringType);
    }
    
    private boolean isLocalVariable(TypeSpec typeExpr) {
    	return (typeExpr == Predefined.localIntegerType)
    			|| (typeExpr == Predefined.localFloatType)
    			|| (typeExpr == Predefined.localDoubleType)
    			|| (typeExpr == Predefined.localStringType);
    }
    
    private String typeCheckForVariable(TypeSpec typeExpr) {
    	String type = (typeExpr == Predefined.integerType ||  typeExpr ==  Predefined.localIntegerType) ? "i"
				 : (typeExpr == Predefined.floatType ||  typeExpr ==  Predefined.localFloatType) ? "f"
				 : (typeExpr == Predefined.doubleType || typeExpr == Predefined.localDoubleType) ? "d"
				 : (typeExpr == Predefined.stringType ||  typeExpr ==  Predefined.localStringType) ? "a"
				 :	"?";
    	return type;
    }
    
    private String typeCheckForVariable(String typeName) {
    	String type = (typeName.indexOf(Literal.INT.toString()) >= 0) ? "i"
    				: (typeName.indexOf(Literal.FLOAT.toString()) >= 0) ? "f"
    				: (typeName.indexOf(Literal.DOUBLE.toString()) >= 0) ? "d"
    	    		: (typeName.indexOf(Literal.STRING.toString()) >= 0) ? "a"
    	    		: "?";
    	return type;
    }
	
}
