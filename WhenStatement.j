.class public WhenStatement
.super java/lang/Object

;================================
; default constructor
;================================
.method public <init>()V
   aload 0
   invokespecial java/lang/Object/<init>()V
   return
.end method


;================================
; when(x)
;================================

.method public static main([Ljava/lang/String;)V
    .limit locals 2

    bipush 5 ; x=5
    istore_1
    
    Case1:
        iload_1
        iconst_1
        if_icmpne Case2 ; (x=1)?
        getstatic java/lang/System/out Ljava/io/PrintStream;
        ldc "Case 1"
        invokevirtual java/io/PrintStream/println(I)V
        goto Exit
        
    Case2:
        iload_1
        iconst_2
        if_icmpne Case3 ; (x=2)?
        getstatic java/lang/System/out Ljava/io/PrintStream;
        ldc "Case 2"
        invokevirtual java/io/PrintStream/println(I)V
        goto Exit
    
    Case3:
        iload_1
        iconst_3
        if_icmpne Default ; (x=3)?
        getstatic java/lang/System/out Ljava/io/PrintStream;
        ldc "Case 3"
        invokevirtual java/io/PrintStream/println(I)V
        goto Exit

    Default:
        getstatic java/lang/System/out Ljava/io/PrintStream;
        ldc "Default Case"
        invokevirtual java/io/PrintStream/println(I)V

    Exit:

    return ; exit
.end method
