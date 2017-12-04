.class public HelloWorld
.super java/lang/Object
.field private static someInt I = 0
.field private static someFloat F = 0
.field private static someString Ljava/lang/String; = "Hello World"

.method public <init>()V

	aload_0
	invokenonvirtual    java/lang/Object/<init>()V
	return

.limit locals 20
.limit stack 20
.end method

.method public static main([Ljava/lang/String;)V

	ldc 0
	putstatic	HelloWorld/someInt I

	ldc 13.2
	putstatic	HelloWorld/someFloat F

	getstatic	HelloWorld/someInt I
	ldc 0
	if_icmpge	IfLabel0

	ldc 13.0
	putstatic	HelloWorld/someFloat F

	goto		ElseLabel0

IfLabel0:
	getstatic	HelloWorld/someInt I
	ldc 0
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
	ldc 3
	if_icmpge	Label2
	goto		WhileLoop2

WhileLoop2: 
	getstatic	HelloWorld/someInt I
	ldc 1
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

	return

.limit locals 100
.limit stack 100
.end method
