.class public HelloWorld
.super java/lang/Object
.field private static someInt I = 0
.field private static someFloat F = 0
.field private static someString Ljava/lang/String; = "Hello World"
.field private static someArray [I
.method public <init>()V

	aload_0
	invokenonvirtual    java/lang/Object/<init>()V
	return

.limit locals 20
.limit stack 20
.end method
	bipush 0
	bipush 0

.method public static someFunctionA(ILjava/lang/String;)Ljava/lang/String;

	aload_1
	areturn

.limit locals 32
.limit stack 32
.end method

.method public static someFunctionB(I)I

	getstatic	HelloWorld/someFloat F
	freturn

.limit locals 32
.limit stack 32
.end method

.method public static main([Ljava/lang/String;)V

	bipush 0
	ldc 0
	putstatic	HelloWorld/someInt I

	ldc 13.2
	putstatic	HelloWorld/someFloat F

	getstatic	HelloWorld/someInt I
	bipush 0
	if_icmpge	IfLabel0

	ldc 13.0
	putstatic	HelloWorld/someFloat F

	goto		ElseLabel0

IfLabel0:
	getstatic	HelloWorld/someInt I
	bipush 0
	if_icmple	IfLabel1

	ldc 13.1
	putstatic	HelloWorld/someFloat F

	goto		ElseLabel0

IfLabel1:
	ldc 13.2
	putstatic	HelloWorld/someFloat F

ElseLabel0:

WhileCompare2: 
	getstatic	HelloWorld/someInt I
	bipush 3
	if_icmpge	Label2
	goto		WhileLoop2

WhileLoop2: 
	getstatic	HelloWorld/someInt I
	iconst_1
	iadd
	putstatic	HelloWorld/someInt I
	getstatic	HelloWorld/someInt I
	lookupswitch
		0: WhenLabel1
		1: WhenLabel2
		default: WhenLabel3

WhenLabel1:
	ldc 13.0
	putstatic	HelloWorld/someFloat F

	goto		WhenEnd2

WhenLabel2:
	ldc 13.1
	putstatic	HelloWorld/someFloat F

	goto		WhenEnd2

WhenLabel3:
	ldc 13.2
	putstatic	HelloWorld/someFloat F

	goto		WhenEnd2

WhenEnd2:
	goto		WhileCompare2

Label2:
	getstatic	HelloWorld/someInt I
	getstatic	HelloWorld/someString Ljava/lang/String;
	invokestatic HelloWorld/someFunctionA(ILjava/lang/String;)Ljava/lang/String;

	ldc someFunctionA(someInt,someString)
	putstatic	HelloWorld/someString Ljava/lang/String;

	return

.limit locals 32
.limit stack 32
.end method
