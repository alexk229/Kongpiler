grammar Hello;

@lexer::members {
  protected boolean enumIsKeyword = true;
  protected boolean assertIsKeyword = true;
}

@header {
    import wci.intermediate.*;
    import wci.intermediate.symtabimpl.*;
}

// starting point for parsing a file


// starting point for parsing a java file
compilationUnit
    :   packageDeclaration? importDeclaration* typeDeclaration*
        EOF
    ;

packageDeclaration
    :   'package' qualifiedName ';'
    ;

importDeclaration
    :   'import' 'static'? qualifiedName ('.' '*')? ';'
    ;

typeDeclaration
    :   modifier*
        (   classDeclaration
        |   enumDeclaration
        )
    |   ';'
    ;

classDeclaration
    :   'class' Identifier typeParameters? ('extends' type)?
        (':' typeList)?
        classBody
    ;

enumDeclaration
    :   ENUM Identifier (':' typeList)? enumBody
    ;

modifiers
    :   modifier*
    ;

typeParameters
    :   '<' typeParameter (',' typeParameter)* '>'
    ;

typeParameter
    :   Identifier ('extends' typeBound)?
    ;

typeBound
    :   type ('&' type)*
    ;

enumBody
    :   '{' enumConstants? ','? enumBodyDeclarations? '}'
    ;

enumConstants
    :   enumConstant (',' enumConstant)*
    ;

enumConstant
    :   Identifier arguments? classBody?
    ;

enumBodyDeclarations
    :   ';' (classBodyDeclaration)*
    ;

typeList
    :   type (',' type)*
    ;

classBody
    :   '{' classBodyDeclaration* '}'
    ;

classBodyDeclaration
    :   ';'
    |   'static'? block
    |   modifiers member
    ;

member
    :   genericMethodDeclaration
    |   methodDeclaration
    |   fieldDeclaration
    |   constructorDeclaration
    |   classDeclaration
    ;


methodDeclaration
    :   method (':' type)? methodDeclarationRest
    ;
    
method
:	'fun' Identifier formalParameters
;

methodDeclarationRest
    :   ('throws' qualifiedNameList)?
        (   methodBody
        |   ';'
        )
    ;

genericMethodDeclaration
    :   typeParameters methodDeclaration
    ;

// Variable delaration 'var'
fieldDeclaration
    :   'var' variableDeclarators ';'
    ;
    
//Modified constructor as init
constructorDeclaration
:   'init' formalParameters
    ('throws' qualifiedNameList)? constructorBody
;

voidMethodDeclaratorRest
    :   formalParameters ('throws' qualifiedNameList)?
        (   methodBody
        |   ';'
        )
    ;

constantDeclarator
    :   Identifier constantDeclaratorRest
    ;


variableDeclarators
    :   variableDeclarator (',' variableDeclarator)*
    ;
    
variableDeclarator
    :   variableDeclaratorId (':' variableType)? ('=' variableInitializer)? ';'?
    ;
    
variableAssignment
	:	variable ('=' expression) ';'
	;

constantDeclaratorsRest
    :   constantDeclaratorRest (',' constantDeclarator)*
    ;

constantDeclaratorRest
    :   ('[' ']')* '=' variableInitializer
    ;

variableDeclaratorId locals [ TypeSpec typeVar = null ]
    :   Identifier
    ;
	
variable
	: 	Identifier
	;
    
variableType
	: type
	| ('[' type ']')
	;

variableInitializer 
    :   arrayInitializer
    |   expression
    ;

arrayInitializer
    :   '{' (variableInitializer (',' variableInitializer)* (',')? )? '}'
    ;

modifier
    :   'public'
    |   'protected'
    |   'private'
    |   'static'
    |   'abstract'
    |   'final'
    ;

enumConstantName
    :   Identifier
    ;

typeName
    :   qualifiedName
    ;

type   	locals [ TypeSpec typeExpr = null ]
	: 	classType ('[' ']')*
    |   primitiveType ('[' ']')*
    ;

classType
    :   Identifier typeArguments? ('.' Identifier typeArguments? )*
    ;

primitiveType
    :   'Bool'
    |   'Char'
    |   'Byte'
    |   'Short'
    |   'Int'
    |   'Long'
    |   'Float'
    |   'Double'
    ;

// Let variableModifier
variableModifier
    :   'let'
    ;

typeArguments
    :   '<' typeArgument (',' typeArgument)* '>'
    ;

typeArgument
    :   type
    |   '?' (('extends' | 'super') type)?
    ;

qualifiedNameList
    :   qualifiedName (',' qualifiedName)*
    ;

formalParameters
    :   '(' formalParameterDecls? ')'
    ;

formalParameterDecls
    : formalParameter (',' formalParameter)* (',' lastFormalParameter)?
    | lastFormalParameter
    ;
    
formalParameter
    :   variableModifiers variableDeclaratorId ':' type
    ;

lastFormalParameter
    : variableModifier* type '...' variableDeclaratorId
    ;

methodBody
    :   block
    ;
    
constructorBody
    :   '{' explicitConstructorInvocation? blockStatement* '}'
    ;
    
explicitConstructorInvocation
    :   nonWildcardTypeArguments? ('this' | 'super') arguments ';'
    |   primary '.' nonWildcardTypeArguments? 'super' arguments ';'
    ;

qualifiedName
    :   Identifier ('.' Identifier)*
    ;

literal locals [ TypeSpec typeExpr = null ]
    :   integerLiteral	# integerConst
    |   FloatingPointLiteral # floatingPointConst
    |   CharacterLiteral	# characterConst
    |   StringLiteral	# stringConst
    |   booleanLiteral # booleanConst
    |   'null'	# nullConst
    ;

integerLiteral
    :   DecimalLiteral
    ;

booleanLiteral
    :   'true'
    |   'false'
    ;

// STATEMENTS / BLOCKS

block
    :   '{' blockStatement* '}'
    ;

blockStatement
    :   localVariableDeclarationStatement
    |   statement
    ;

localVariableDeclarationStatement
    :    localVariableDeclaration ';'*
    ;

localVariableDeclaration
    :   variableModifiers fieldDeclaration
    |	variableAssignment
    ;

variableModifiers
    :   variableModifier*
    ;


statement
    : block
    |   ifStatement
    |   'for' '(' forControl ')' statement
    |   whileStatement
    |   'do' statement 'while' parExpression ';'
    |	whenStatement
    |   returnStatement
    |   'break' Identifier? ';'
    |   ';'
    |   statementExpression ';'
    |	localVariableDeclarationStatement
    |   Identifier ':' statement
    ;
    
returnStatement
	:	'return' expression? ';'
;
    
ifStatement
	:	'if' parExpression statement elseStatement?
	;
	
elseStatement
	:	('else' statement)
	;
    
whileStatement
	:	'while' parExpression statement
	;
    
whenStatement
	:	'when' parExpression '{' whenEntry* '}'
	;
    
// When statement entry
whenEntry
  : whenCondition '->' statement
  | 'else' '->' statement
  ;
 
whenCondition
  : expression
  ;

// For loop statement
forControl
    :   enhancedForControl
    |   variableDeclarator? 'in' expression?
    ;

forInit
    :   localVariableDeclaration
    |   expressionList
    ;

enhancedForControl
    :   variableModifiers type Identifier 'in' expression
    ;

forUpdate
    :   expressionList
    ;

// EXPRESSIONS

parExpression
    :   '(' expression ')'
    ;

expressionList
    :   expression (',' expression)*
    ;

statementExpression
    :   expression
    ;

constantExpression
    :   expression
    ;

// Self expression
expression locals [ TypeSpec typeExpr = null ]
    :   primary		# primaryExp
    |   expression '.' Identifier	# identifierExpr
    |   'self' '.' expression	# selfExpr
    |   expression '.' 'super' '(' expressionList? ')'	# superExprList
    |   expression '.' 'new' Identifier '(' expressionList? ')'	# newExpr
    |   expression '.' 'super' '.' Identifier arguments?	# superIndentifierExpr
    |   expression '[' expression ']'	# arrayExpr
    |   expression '(' expressionList? ')'	# functionExpr
    |   expression addSubOneOp	# addSubOneExpr
    |   ('+'|'-'|'++'|'--') expression # unaryOpExpr
    |   ('~'|'!') expression	# notExpr
    |   '(' type ')' expression	# typeExpr
    |   expression ('*'|'/'|'%') expression	# multiplicativeExpr
    |   expression ('+'|'-') expression	# additiveExpr
    |   expression ('<' '<' | '>' '>' '>' | '>' '>') expression	# assignmentExpr
    |   expression relationalOp expression # relationalExpr
    |   expression 'instanceof' type	# instanceOfExpr
    |   expression equalityOp expression # equalityExpr
    |   expression '&' expression	# bitwiseAndExpr
    |   expression '^' expression	# bitwiseXorExpr
    |   expression '|' expression	# bitwiseOrExpr
    |   expression '&&' expression # logicalAndExpr
    |   expression '||' expression # logicalOrExpr
    |   expression '?' expression ':' expression # tenaryOpExpr
    |	expression ('..') expression # rangeExpr
    |	expression isOp expression	# isExpr
    |	('in' | '!in') expression	# inExpr
    |	variable	# variableExpr
    ;
   
isOp
	:	('is' | '!is')
;
    
addSubOneOp
	: ('++' | '--')
;
    
relationalOp
	: ('<' '=' | '>' '=' | '>' | '<')
;
    
equalityOp
	: ('==' | '!=')
;

primary
    :   '(' expression ')'
    |   'self'
    |   'super'
    |   literal
    |   type '.' 'class'
    |   'void' '.' 'class'
    ;
    
nonWildcardTypeArguments
    :   '<' typeList '>'
    ;

arguments
    :   '(' expressionList? ')'
    ;
    


// LEXER

DecimalLiteral : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

fragment 
IntegerTypeSuffix : ('l'|'L') ;

FloatingPointLiteral
    :   ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
    |   ('0'..'9')+ Exponent FloatTypeSuffix?
    |   ('0'..'9')+ FloatTypeSuffix
    ;

fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

CharacterLiteral
    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
    ;

StringLiteral
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    ;

ENUM:   'enum' {if (!enumIsKeyword) setType(Identifier);}
    ;

Identifier
    :   Letter (Letter)*
    ;

/**I found this char range in JavaCC's grammar, but Letter and Digit overlap.
   Still works, but...
 */
fragment
Letter
    :  '\u0024' |
       '\u0041'..'\u005a' |
       '\u005f' |
       '\u0061'..'\u007a' |
       '\u00c0'..'\u00d6' |
       '\u00d8'..'\u00f6' |
       '\u00f8'..'\u00ff' |
       '\u0100'..'\u1fff' |
       '\u3040'..'\u318f' |
       '\u3300'..'\u337f' |
       '\u3400'..'\u3d2d' |
       '\u4e00'..'\u9fff' |
       '\uf900'..'\ufaff'
    ;

COMMENT
    :   '/*' .*? '*/'    -> channel(HIDDEN) // match anything between /* and */
    ;
WS  :   [ \r\t\u000C\n]+ -> channel(HIDDEN)
    ;

LINE_COMMENT
    : '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN)
    ;
