.class public ForLoopStatement
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
; for(int i=0; i<10; i++)
;================================

.method public static main([Ljava/lang/String;)V
    .limit locals 2

    bipush 0 ;i=0
    istore_1

    goto checkWhileCondition

    forLoop:
        iload_1 ; load i
        ldc 1
        iadd  ; i++
        istore_1

    checkWhileCondition:
        iload_1 ; load i
        bipush 10
        if_icmplt forLoop    ; loop

    return ; exit
.end method
