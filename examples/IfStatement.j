.class public IfStatement
.super java/lang/Object


.method public static main([Ljava/lang/String;)V

	bipush 0
	istore_1
	bipush 10
	istore_2
ForLoopLabel0:
	iload_1
	iload_2
	if_icmpge EndForLoopLabel0
	iload_1
	bipush 4
	if_icmpne	IfLabel0

	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is 4"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		ElseLabel0

IfLabel0:
	iload_1
	bipush 4
	if_icmpge	IfLabel1

	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is less than 4"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		ElseLabel0

IfLabel1:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is greater than 4"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

ElseLabel0:
	iinc 1 1
	goto	ForLoopLabel0
EndForLoopLabel0:
	return

.limit locals 100
.limit stack 100
.end method
