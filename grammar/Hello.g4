grammar Hello;

@lexer::members {
  protected boolean enumIsKeyword = true;
  protected boolean assertIsKeyword = true;
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
    :   variableDeclaratorId (':' variableType)? ('=' variableInitializer)?
    ;

constantDeclaratorsRest
    :   constantDeclaratorRest (',' constantDeclarator)*
    ;

constantDeclaratorRest
    :   ('[' ']')* '=' variableInitializer
    ;

variableDeclaratorId
    :   Identifier
    ;
    
variableAssignment
	:	variableDeclaratorId '=' variableInitializer
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

type:   classType ('[' ']')*
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
    :   variableModifiers variableDeclaratorId ':' formalParameterDeclsRest
    ;

formalParameterDeclsRest
    :   type (',' formalParameterDecls)?
    |   '...' variableDeclaratorId
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

literal
    :   integerLiteral
    |   FloatingPointLiteral
    |   CharacterLiteral
    |   StringLiteral
    |   booleanLiteral
    |   'null'
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
    :    localVariableDeclaration ';'
    ;

localVariableDeclaration
    :   variableModifiers type variableDeclarators
    ;

variableModifiers
    :   variableModifier*
    ;


statement
    : block
    |   'if' parExpression statement ('else' statement)?
    |   'for' '(' forControl ')' statement
    |   'while' parExpression statement
    |   'do' statement 'while' parExpression ';'
    |	'when' ('(' expression ')') '{' whenEntry* '}'
    |   'return' expression? ';'
    |   'break' Identifier? ';'
    |   ';'
    |   statementExpression ';'
    |   Identifier ':' statement
    ;
    
// When statement entry
whenEntry
  : expression (',' expression)* '->' expression ';'
  | 'else' '->' expression ';'
  ;

formalParameter
    :   variableModifiers type variableDeclaratorId
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
expression
    :   primary
    |   expression '.' Identifier
    |   'self' '.' expression
    |   expression '.' 'super' '(' expressionList? ')'
    |   expression '.' 'new' Identifier '(' expressionList? ')'
    |   expression '.' 'super' '.' Identifier arguments?
    |   expression '[' expression ']'
    |   expression '(' expressionList? ')'
    |   expression ('++' | '--')
    |   ('+'|'-'|'++'|'--') expression
    |   ('~'|'!') expression
    |   '(' type ')' expression
    |   expression ('*'|'/'|'%') expression
    |   expression ('+'|'-') expression
    |   expression ('<' '<' | '>' '>' '>' | '>' '>') expression
    |   expression ('<' '=' | '>' '=' | '>' | '<') expression
    |   expression 'instanceof' type
    |   expression ('==' | '!=') expression
    |   expression '&' expression
    |   expression '^' expression
    |   expression '|' expression
    |   expression '&&' expression
    |   expression '||' expression
    |   expression '?' expression ':' expression
    ;

primary
    :   '(' expression ')'
    |   'self'
    |   'super'
    |   literal
    |   Identifier
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
