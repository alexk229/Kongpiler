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

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "integerOne = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	BasicArithmeticOp/integerOne I
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "integerTwo = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	BasicArithmeticOp/integerTwo I
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "integerThree = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	BasicArithmeticOp/integerThree I
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic	BasicArithmeticOp/integerOne I
	getstatic	BasicArithmeticOp/integerTwo I
	iadd
	getstatic	BasicArithmeticOp/integerThree I
	iadd
	istore_1

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "integerOne + integerTwo + integerThree = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	iload_1
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic	BasicArithmeticOp/integerThree I
	getstatic	BasicArithmeticOp/integerTwo I
	isub
	getstatic	BasicArithmeticOp/integerOne I
	isub
	istore_1

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "integerThree - integerTwo - integerOne = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	iload_1
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

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
	new java/lang/StringBuilder
	dup
	ldc "integerThree * integerTwo - integerOne + integerThree / integerOne = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	iload_1
	invokevirtual java/lang/StringBuilder/append(I)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	ldc2_w 0.0
	dstore_2
	ldc2_w 1.5
	putstatic	BasicArithmeticOp/doubleOne D

	ldc2_w 2.5
	putstatic	BasicArithmeticOp/doubleTwo D

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "doubleOne = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	BasicArithmeticOp/doubleOne D
	invokevirtual java/lang/StringBuilder/append(D)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "doubleTwo = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	getstatic	BasicArithmeticOp/doubleTwo D
	invokevirtual java/lang/StringBuilder/append(D)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic	BasicArithmeticOp/doubleOne D
	getstatic	BasicArithmeticOp/doubleTwo D
	dadd
	dstore_2

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "doubleOne + doubleTwo = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	dload_2
	invokevirtual java/lang/StringBuilder/append(D)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic	BasicArithmeticOp/doubleTwo D
	getstatic	BasicArithmeticOp/doubleOne D
	dsub
	dstore_2

	getstatic java/lang/System/out Ljava/io/PrintStream;
	new java/lang/StringBuilder
	dup
	ldc "doubleTwo - doubleOne = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	dload_2
	invokevirtual java/lang/StringBuilder/append(D)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

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
	new java/lang/StringBuilder
	dup
	ldc "doubleOne * 3.04 + 3.2 - 4.5 * 1.4 / 1.2 = "
	invokenonvirtual java/lang/StringBuilder/<init>(Ljava/lang/String;)V
	dload_2
	invokevirtual java/lang/StringBuilder/append(D)Ljava/lang/StringBuilder;
	invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	return

.limit locals 32
.limit stack 32
.end method
