.class public HelloWorld
.super java/lang/Object

; varsomeInt:Int;

.field private static someInt I

; varsomeDouble:Double;

.field private static someDouble D

.method public <init>()V

	aload_0
	invokenonvirtual    java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

	ldc	12
	putstatic	HelloWorld/someInt I

	ldc	13.2
	putstatic	HelloWorld/someDouble D

whenLabel0:
	ldc	13
	putstatic	HelloWorld/someInt I

	ldc	13.3
	putstatic	HelloWorld/someDouble D

whenLabel1:
	ldc	13
	putstatic	HelloWorld/someInt I

	ldc	13.3
	putstatic	HelloWorld/someDouble D

	getstatic	HelloWorld/someInt I
	ldc	12
	if_icmpeq whenLabel0


	return

.limit locals 16
.limit stack 16
.end method
