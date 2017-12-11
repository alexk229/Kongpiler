.class public WhenStatement
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
	iload_1
	lookupswitch
		0: WhenLabel1
		2: WhenLabel2
		4: WhenLabel3
		6: WhenLabel4
		8: WhenLabel5
		10: WhenLabel6
		default: WhenLabel7

WhenLabel1:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is 0"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd0

WhenLabel2:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is 2"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd0

WhenLabel3:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is 4"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd0

WhenLabel4:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is 6"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd0

WhenLabel5:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is 8"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd0

WhenLabel6:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is 10"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd0

WhenLabel7:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "i is not an even number"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	goto		WhenEnd0

WhenEnd0:
	iload_1
	iconst_1
	iadd
	istore_1
	goto		WhileCompare0

Label0:
	return

.limit locals 100
.limit stack 100
.end method
