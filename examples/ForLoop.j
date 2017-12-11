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
	if_icmpge Label0
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload_1
	invokevirtual java/io/PrintStream/println(I)V

	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "Hello World!"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	iinc 1 1
	goto	ForLoopLabel0
Label0:
	return

.limit locals 100
.limit stack 100
.end method
