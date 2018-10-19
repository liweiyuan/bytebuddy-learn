package com.learn.bytebuddy.interceptor;

import net.bytebuddy.asm.Advice;

/**
 * @Author :lwy
 * @Date : 2018/10/19 16:58
 * @Description :
 */
public class ConstructorInterceptor {

    @Advice.OnMethodEnter
    public static void enter(@Advice.AllArguments Object[] arguments,
                             @Advice.Origin("#t") String className,
                             @Advice.Origin("#m") String methodName,
                             @Advice.Origin("#s") String signature) throws Throwable{
        System.out.println("---[BEGIN] ConstructorInterceptor");
        System.out.println("  --------className="+className);
        System.out.println("  --------methodName="+methodName);
        System.out.println("  --------signature="+signature);
    }
}
