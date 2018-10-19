package com.learn.bytebuddy.interceptor;

/**
 * @Author :lwy
 * @Date : 2018/10/19 14:41
 * @Description :
 */

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * 注意：实例方法使用@Advice.This注解，静态方法使用@Advice.Origin 两者不能混用
 */
public class InstanceMethodSpendAdviceInterceptor {

    @Advice.OnMethodEnter
    public static void enter(@Advice.Local("startTime") long startTime,
                             @Advice.Origin Method method,
                             @Advice.This Object object) {
        System.out.println("---[BEGIN] InstanceMethodSpendAdviceInterceptor--" + object.getClass().getName() + "." + method.getName());
        startTime = System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    public static void exit(@Advice.Local("startTime") long startTime) {
        System.out.println("  ==============>spend=" + (System.currentTimeMillis() - startTime));
        System.out.println("---[END] InstanceMethodSpendAdviceInterceptor");
    }

}
