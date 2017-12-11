.class public ForLoop
.super java/lang/Object


.method public static main([Ljava/lang/String;)V

	bipush 5
	istore_1
	bipush 12
	istore_2
ForLoopLabel0:
	iload_1
	iload_2
	if_icmpge EndForLoopLabel0
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "HelloWorld! "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	iload_1
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	iinc 1 1
	goto	ForLoopLabel0
EndForLoopLabel0:
	return

.limit locals 32
.limit stack 32
.end method
