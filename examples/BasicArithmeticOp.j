.class public BasicArithmeticOp
.super java/lang/Object
.field private static integerOne I
.field private static integerTwo I
.field private static integerThree I
.field private static doubleOne D
.field private static doubleTwo D



.method public static main([Ljava/lang/String;)V

	bipush 0
	istore_1
	bipush 1
	putstatic	BasicArithmeticOp/integerOne I

	bipush 2
	putstatic	BasicArithmeticOp/integerTwo I

	bipush 3
	putstatic	BasicArithmeticOp/integerThree I

	getstatic	BasicArithmeticOp/integerOne I
	getstatic	BasicArithmeticOp/integerTwo I
	iadd
	getstatic	BasicArithmeticOp/integerThree I
	iadd
	istore_1

	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload_1
	invokevirtual java/io/PrintStream/println(I)V

	getstatic	BasicArithmeticOp/integerThree I
	getstatic	BasicArithmeticOp/integerTwo I
	isub
	getstatic	BasicArithmeticOp/integerOne I
	isub
	istore_1

	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload_1
	invokevirtual java/io/PrintStream/println(I)V

	getstatic	BasicArithmeticOp/integerThree I
	getstatic	BasicArithmeticOp/integerTwo I
	imul
	getstatic	BasicArithmeticOp/integerOne I
	isub
	getstatic	BasicArithmeticOp/integerThree I
	getstatic	BasicArithmeticOp/integerOne I
	idiv
	iadd
	istore_1

	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload_1
	invokevirtual java/io/PrintStream/println(I)V

	ldc2_w 0.0
	dstore_2
	ldc2_w 1.5
	putstatic	BasicArithmeticOp/doubleOne D

	ldc2_w 2.5
	putstatic	BasicArithmeticOp/doubleTwo D

	getstatic	BasicArithmeticOp/doubleOne D
	getstatic	BasicArithmeticOp/doubleTwo D
	dadd
	dstore_2

	getstatic java/lang/System/out Ljava/io/PrintStream;
	dload_2
	invokevirtual java/io/PrintStream/println(D)V

	getstatic	BasicArithmeticOp/doubleTwo D
	getstatic	BasicArithmeticOp/doubleOne D
	dsub
	dstore_2

	getstatic java/lang/System/out Ljava/io/PrintStream;
	dload_2
	invokevirtual java/io/PrintStream/println(D)V

	getstatic	BasicArithmeticOp/doubleOne D
	ldc2_w 3.04
	dmul
	ldc2_w 3.2
	dadd
	ldc2_w 4.5
	ldc2_w 1.4
	dmul
	ldc2_w 1.2
	ddiv
	dsub
	dstore_2

	getstatic java/lang/System/out Ljava/io/PrintStream;
	dload_2
	invokevirtual java/io/PrintStream/println(D)V

	return

.limit locals 100
.limit stack 100
.end method
