.class public HelloWorld
.super java/lang/Object

; varsomeInt:Int=12;

.field private static someInt I

; varsomeChar:Char='c';

.field private static someChar C

.method public <init>()V

	aload_0
	invokenonvirtual    java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V
	.limit stack 2
	getstatic java/lang/System/out Ljava/io/PrintStream
	ldc 'c'
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	return
.end method
