.class public WhileLoop
.super java/lang/Object


.method public static main([Ljava/lang/String;)V

	bipush 0
	istore_1

WhileCompare0: 
	iload_1
	bipush 12
	if_icmpge	Label0
	goto		WhileLoop0

WhileLoop0: 
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "Hello World!"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "i = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	iload_1
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	iload_1
	iconst_1
	iadd
	istore_1
	goto		WhileCompare0

Label0:
	return

.limit locals 2
.limit stack 16
.end method
