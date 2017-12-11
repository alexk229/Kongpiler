.class public IfStatement
.super java/lang/Object
.field private static isFour Ljava/lang/String; = "i is 4"
.field private static isLessThanFour Ljava/lang/String; = "i is less than 4"
.field private static isGreaterThanFour Ljava/lang/String; = "i is greater than 4"


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
	getstatic	IfStatement/isFour Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		ElseLabel0

IfLabel0:
	iload_1
	bipush 4
	if_icmpge	IfLabel1

	getstatic java/lang/System/out Ljava/io/PrintStream;
	getstatic	IfStatement/isLessThanFour Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		ElseLabel0

IfLabel1:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	getstatic	IfStatement/isGreaterThanFour Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

ElseLabel0:
	iinc 1 1
	goto	ForLoopLabel0
EndForLoopLabel0:
	return

.limit locals 32
.limit stack 32
.end method
