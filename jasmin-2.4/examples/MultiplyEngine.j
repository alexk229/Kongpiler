.class public engines/MultiplyEngine
.super java/lang/Object 

.method public static multiply(II)I
    .limit stack  2
    .limit locals 2

    iload_0  ; push the local variable in slot #0 (1st arg)
    iload_1  ; push the local variable in slot #1 (2nd arg)
    imul     ; multiply
    ireturn  ; return the product on the stack

.end method