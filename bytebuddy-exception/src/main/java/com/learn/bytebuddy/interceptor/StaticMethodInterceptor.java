package com.learn.bytebuddy.interceptor;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * @Author :lwy
 * @Date : 2018/10/19 15:42
 * @Description : 获取异常处理  @Advice.OnMethodExit(onThrowable = Throwable.class)
 */
public class StaticMethodInterceptor{


    @Advice.OnMethodEnter
    public static void entry(@Advice.Local("startTime") Long startTime,
                             @Advice.Origin Method method,
                             @Advice.This Object object,
                             @Advice.AllArguments Object[] arguments,
                             @Advice.Origin("#t") String className,
                             @Advice.Origin("#m") String methodName,
                             @Advice.Origin("#s") String signature
    ) throws Throwable{

        System.out.println("---[BEGIN] InstanceMethodSpendAdviceInterceptor");
        System.out.println(StaticMethodInterceptor.class.getName());
        startTime = System.currentTimeMillis();
        System.out.println("  --------"+object.toString());
        System.out.println("  --------className="+className);
        System.out.println("  --------methodName="+methodName);
        System.out.println("  --------signature="+signature);

    }

    // 获取异常，需要添加onThrowable = Throwable.class 和@Advice.Thrown Throwable t，如果没有异常t==null

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Local("startTime") Long startTime,
                            //@Advice.Return(readOnly = false) String result,
                            @Advice.Thrown Throwable t){

        if(t != null){
            t.printStackTrace();
        }
        //System.out.println("------------"+result);
        //result = result + " @advice";
        System.out.println("  ==============>spend="+(System.currentTimeMillis()-startTime));
        System.out.println("---[END] InstanceMethodSpendAdviceInterceptor");

    }


}
