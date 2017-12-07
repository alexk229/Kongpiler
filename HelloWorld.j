.class public HelloWorld
.super java/lang/Object
.field private static number I = 12
.field private static someFloat F
.field private static someString Ljava/lang/String; = "Hello World"
.field private static someArray [I


.method public static someFunctionA(ILjava/lang/String;)Ljava/lang/String;

	ldc "Hello"
	astore_2
	ldc "Hello"
	astore_1

	aload_2
	areturn

.limit locals 32
.limit stack 32
.end method

.method public static calculate(I)I

	bipush 1
	bipush 2
	iadd
	bipush 3
	iadd
	bipush 4
	bipush 5
	idiv
	iadd
	?return

.limit locals 32
.limit stack 32
.end method

.method public static main([Ljava/lang/String;)V

	bipush 0
	putstatic	HelloWorld/number I

	ldc 13.2F
	putstatic	HelloWorld/someFloat F

	getstatic	HelloWorld/number I
	bipush 0
	if_icmpge	IfLabel0

	ldc 13.0F
	putstatic	HelloWorld/someFloat F

	goto		ElseLabel0

IfLabel0:
	getstatic	HelloWorld/number I
	bipush 0
	if_icmple	IfLabel1

	ldc 13.1F
	putstatic	HelloWorld/someFloat F

	goto		ElseLabel0

IfLabel1:
	ldc 13.2F
	putstatic	HelloWorld/someFloat F

ElseLabel0:

WhileCompare2: 
	getstatic	HelloWorld/number I
	bipush 3
	if_icmpge	Label2
	goto		WhileLoop2

WhileLoop2: 
	getstatic	HelloWorld/number I
	iconst_1
	iadd
	putstatic	HelloWorld/number I
	getstatic	HelloWorld/number I
	lookupswitch
		0: WhenLabel1
		1: WhenLabel2
		default: WhenLabel3

WhenLabel1:
	ldc 13.0F
	putstatic	HelloWorld/someFloat F

	goto		WhenEnd2

WhenLabel2:
	ldc 13.1F
	putstatic	HelloWorld/someFloat F

	goto		WhenEnd2

WhenLabel3:
	ldc 13.2F
	putstatic	HelloWorld/someFloat F

	goto		WhenEnd2

WhenEnd2:
	goto		WhileCompare2

Label2:
	getstatic	HelloWorld/number I
	getstatic	HelloWorld/someString Ljava/lang/String;
	invokestatic HelloWorld/someFunctionA(ILjava/lang/String;)Ljava/lang/String;
	putstatic	HelloWorld/someString Ljava/lang/String;

	return

.limit locals 32
.limit stack 32
.end method
