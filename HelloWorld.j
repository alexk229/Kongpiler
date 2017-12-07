.class public HelloWorld
.super java/lang/Object
.field private static number I = 12
.field private static aFloat F
.field private static aDouble D
.field private static text Ljava/lang/String; = "Hello"

.method public static getStr(I)Ljava/lang/String;

	getstatic	HelloWorld/number I
	bipush 0
	if_icmpge	IfLabel0

	ldc "Hello"
	areturn
	goto		ElseLabel0

IfLabel0:
	getstatic	HelloWorld/number I
	bipush 0
	if_icmple	ElseLabel0

	ldc "Hi"
	areturn
	goto		ElseLabel0

ElseLabel0:
	ldc "?"
	areturn

.limit locals 100
.limit stack 100
.end method

.method public static calculateNum()I

	bipush 1
	bipush 2
	iadd
	bipush 3
	iadd
	bipush 4
	bipush 5
	idiv
	iadd
	bipush 4
	isub
	ireturn

.limit locals 100
.limit stack 100
.end method

.method public static calculateDouble()D

	ldc2_w 1.2
	ldc2_w 1.3
	ldc2_w 1.4
	dmul
	dadd
	dreturn

.limit locals 100
.limit stack 100
.end method

.method public static main([Ljava/lang/String;)V

	invokestatic HelloWorld/calculateNum()I
	putstatic	HelloWorld/number I

	invokestatic HelloWorld/calculateDouble()D
	putstatic	HelloWorld/aDouble D

	getstatic java/lang/System/out Ljava/io/PrintStream;
	getstatic	HelloWorld/number I
	invokevirtual java/io/PrintStream/println(I)V

	getstatic java/lang/System/out Ljava/io/PrintStream;
	getstatic	HelloWorld/aDouble D
	invokevirtual java/io/PrintStream/println(D)V

	getstatic	HelloWorld/number I
	invokestatic HelloWorld/getStr(I)Ljava/lang/String;
	putstatic	HelloWorld/text Ljava/lang/String;

	getstatic java/lang/System/out Ljava/io/PrintStream;
	getstatic	HelloWorld/text Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic	HelloWorld/number I
	bipush 1
	isub
	putstatic	HelloWorld/number I


WhileCompare1: 
	getstatic	HelloWorld/number I
	bipush 3
	if_icmpge	Label1
	goto		WhileLoop1

WhileLoop1: 
	getstatic	HelloWorld/number I
	lookupswitch
		0: WhenLabel1
		1: WhenLabel2
		default: WhenLabel3

WhenLabel1:
	ldc 0.5F
	putstatic	HelloWorld/aFloat F

	goto		WhenEnd1

WhenLabel2:
	ldc 1.0F
	putstatic	HelloWorld/aFloat F

	goto		WhenEnd1

WhenLabel3:
	ldc 2.0F
	putstatic	HelloWorld/aFloat F

	goto		WhenEnd1

WhenEnd1:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	getstatic	HelloWorld/aFloat F
	invokevirtual java/io/PrintStream/println(F)V

	getstatic	HelloWorld/number I
	iconst_1
	iadd
	putstatic	HelloWorld/number I
	goto		WhileCompare1

Label1:
	return

.limit locals 100
.limit stack 100
.end method
