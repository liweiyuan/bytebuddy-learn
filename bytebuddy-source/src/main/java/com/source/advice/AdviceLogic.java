package com.source.advice;

import net.bytebuddy.asm.Advice;

public class AdviceLogic {


    @Advice.OnMethodEnter
    public static void enter(@Advice.AllArguments Object[] args, @TestAnnotation  String name) {
        System.out.println("");
        System.out.println("- - - - - - - - - - -");
        System.out.println("enter！ TestAnnotation: "+ name);
    }
}
