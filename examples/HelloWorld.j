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

.limit locals 32
.limit stack 32
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

.limit locals 32
.limit stack 32
.end method

.method public static calculateDouble()D

	ldc2_w 1.2
	ldc2_w 1.3
	ldc2_w 1.4
	dmul
	dadd
	dreturn

.limit locals 32
.limit stack 32
.end method

.method public static main([Ljava/lang/String;)V

	bipush 12
	istore_1
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "num = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	iload_1
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	invokestatic HelloWorld/calculateNum()I
	putstatic	HelloWorld/number I

	invokestatic HelloWorld/calculateDouble()D
	putstatic	HelloWorld/aDouble D

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "num after calculateNum() = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	HelloWorld/number I
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "aDouble after calculateDouble() = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	HelloWorld/aDouble D
	invokevirtual java/lang/StringBuilder/append(D)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic	HelloWorld/number I
	invokestatic HelloWorld/getStr(I)Ljava/lang/String;
	putstatic	HelloWorld/text Ljava/lang/String;

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "text after getStr(number) = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	HelloWorld/text Ljava/lang/String;
	invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
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

	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "number == 0"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd1

WhenLabel2:
	ldc 1.0F
	putstatic	HelloWorld/aFloat F

	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "number == 1"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd1

WhenLabel3:
	ldc 2.0F
	putstatic	HelloWorld/aFloat F

	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "number is neither 0 nor 1"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd1

WhenEnd1:
	getstatic	HelloWorld/number I
	iconst_1
	iadd
	putstatic	HelloWorld/number I
	goto		WhileCompare1

Label1:
	bipush 5
	istore_2
	bipush 12
	istore_3
ForLoopLabel0:
	iload_2
	iload_3
	if_icmpge EndForLoopLabel0
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "ForLoop statement: "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	iload_2
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	iinc 2 1
	goto	ForLoopLabel0
EndForLoopLabel0:
	return

.limit locals 32
.limit stack 32
.end method
