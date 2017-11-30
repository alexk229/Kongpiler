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
	putstatic	HelloWorld/someInt ?
	ldc	13.2
	putstatic	HelloWorld/someDouble ?
	getstatic	HelloWorld/someInt I
	ldc	12
	if_icmpeq

	return

.limit locals 16
.limit stack 16
.end method
