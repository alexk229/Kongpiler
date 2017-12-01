.class public HelloWorld
.super java/lang/Object

; varsomeInt:Int;

.field private static someInt I

; varsomeFloat:Float;

.field private static someFloat F

.method public <init>()V

	aload_0
	invokenonvirtual    java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

	ldc 0
	putstatic	HelloWorld/someInt I

end:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "someInt= "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	HelloWorld/someInt I
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	ldc 13.2
	putstatic	HelloWorld/someFloat F

end:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "someFloat= "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	HelloWorld/someFloat F
	invokevirtual java/lang/StringBuilder/append(F)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "While Loop..."
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto whileCompare
whileLoop:
	getstatic	HelloWorld/someInt I
	ldc 1
	iadd
	putstatic	HelloWorld/someInt I

	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "When statement..."
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic	HelloWorld/someInt I
	lookupswitch
		0: whenLabel1
		1: whenLabel2
		default: whenLabel3

whenLabel1:
	ldc 13.0
	putstatic	HelloWorld/someFloat F

end:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "someFloat= "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	HelloWorld/someFloat F
	invokevirtual java/lang/StringBuilder/append(F)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto end

whenLabel2:
	ldc 13.1
	putstatic	HelloWorld/someFloat F

end:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "someFloat= "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	HelloWorld/someFloat F
	invokevirtual java/lang/StringBuilder/append(F)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto end

whenLabel3:
	ldc 13.2
	putstatic	HelloWorld/someFloat F

end:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "someFloat= "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	HelloWorld/someFloat F
	invokevirtual java/lang/StringBuilder/append(F)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto end

end:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "someInt= "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	HelloWorld/someInt I
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

whileCompare: 
	getstatic	HelloWorld/someInt I
	ldc 3
	if_icmplt whileLoop

	return

.limit locals 32
.limit stack 32
.end method
