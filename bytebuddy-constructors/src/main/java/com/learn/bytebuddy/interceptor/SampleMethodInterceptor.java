package com.learn.bytebuddy.interceptor;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @Author :lwy
 * @Date : 2018/10/19 13:27
 * @Description :
 */
public class SampleMethodInterceptor {

    @RuntimeType
    public Object interceptor(@Origin Class<?> clazz,
                              @AllArguments Object[] arguments,
                              @Origin Method method,
                              @SuperCall Callable<?> callable) throws Exception {
        System.out.println("the arguments: "+ Arrays.toString(arguments));
        System.out.println("the callable: "+callable);
        System.out.println("the method: "+method.getName());

        System.out.println("---[BEGIN] SampleMethodInterceptor 方法拦截器");
        Object ret = null;
        try {
            System.out.println("    class name = " + clazz.getName());
            System.out.println("    method name = " + method.getName());
            ret = callable.call();
        } catch (Throwable t) {
            throw t;
        } finally {
            System.out.println("---[END] SampleMethodInterceptor 方法拦截器");
        }
        return ret;
    }
}
