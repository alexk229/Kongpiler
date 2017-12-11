.class public FunctionCalls
.super java/lang/Object
.field private static integerOne I = 12
.field private static integerTwo I = 24
.field private static floatOne F = 0.4F
.field private static floatTwo F = 0.8F
.field private static floatSum F


.method public static calculateSum(II)I

	iload_0
	iload_1
	iadd
	ireturn

.limit locals 100
.limit stack 100
.end method

.method public static multiplicationOfFloats(FF)F

	fload_0
	fload_1
	fmul
	ldc 1.6F
	fmul
	freturn

.limit locals 100
.limit stack 100
.end method

.method public static main([Ljava/lang/String;)V

	bipush 0
	istore_1
	getstatic	FunctionCalls/integerOne I
	getstatic	FunctionCalls/integerTwo I
	invokestatic FunctionCalls/calculateSum(II)I
	istore_1

	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload_1
	invokevirtual java/io/PrintStream/println(I)V

	iload_1
	getstatic	FunctionCalls/integerOne I
	invokestatic FunctionCalls/calculateSum(II)I
	istore_1

	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload_1
	invokevirtual java/io/PrintStream/println(I)V

	getstatic	FunctionCalls/floatOne F
	getstatic	FunctionCalls/floatTwo F
	invokestatic FunctionCalls/multiplicationOfFloats(FF)F
	putstatic	FunctionCalls/floatSum F

	getstatic java/lang/System/out Ljava/io/PrintStream;
	getstatic	FunctionCalls/floatSum F
	invokevirtual java/io/PrintStream/println(F)V

	return

.limit locals 100
.limit stack 100
.end method
