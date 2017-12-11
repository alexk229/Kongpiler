.class public FunctionCalls
.super java/lang/Object
.field private static integerOne I = 12
.field private static integerTwo I = 24


.method public static calculateSum(II)V

	iload_0
	iload_1
	iadd
	ireturn

.limit locals 100
.limit stack 100
.end method

.method public static main([Ljava/lang/String;)V

	bipush 0
	istore_1
	getstatic	FunctionCalls/integerOne I
	getstatic	FunctionCalls/integerTwo I
	invokestatic FunctionCalls/calculateSum(II)null
	istore_1

	return

.limit locals 100
.limit stack 100
.end method
