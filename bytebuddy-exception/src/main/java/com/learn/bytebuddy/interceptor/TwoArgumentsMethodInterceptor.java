package com.learn.bytebuddy.interceptor;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * @Author :lwy
 * @Date : 2018/10/19 16:15
 * @Description :拦截获取方法的参数
 */
public class TwoArgumentsMethodInterceptor {


    @Advice.OnMethodEnter
    public static void  enter(@Advice.Local("startTime") long startTime,
                              @Advice.Origin Method method,
                              @Advice.This Object object,
                              @Advice.AllArguments Object[] arguments,
                              @Advice.Origin("#t") String className,
                              @Advice.Origin("#m") String methodName,
                              @Advice.Origin("#s") String signature,
                              @Advice.FieldValue("name") String name) throws  Throwable{

        startTime=System.currentTimeMillis();

        System.err.println("The fieldVale : " +name);
    }


    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Local("startTime") long startTime,
                            @Advice.Return Object result,
                            @Advice.Origin("#r") String returnType,
                            @Advice.Thrown Throwable t){

        if(t != null){
            t.printStackTrace();
        }
        System.out.println("------------returnType>>>>>>>>>>>>>>>>>>>>"+returnType);
        System.out.println("------------result>>>>>>>>>>>>>>>>>>>>"+result);
        System.out.println("  ==============>spend="+(System.currentTimeMillis()-startTime));
        System.out.println("---[END] InstanceMethodSpendAdviceInterceptor");
    }
}
