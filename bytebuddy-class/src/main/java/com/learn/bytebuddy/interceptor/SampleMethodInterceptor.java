package com.learn.bytebuddy.interceptor;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @Author :lwy
 * @Date : 2018/10/19 11:17
 * @Description :
 */
public class SampleMethodInterceptor {
    //自定义方法拦截器
    //1.拦截实例方法
    /*@RuntimeType
    public Object interceptor(@This Object obj,
                              @AllArguments Object[] arguments,
                              @SuperCall Callable<?> callable,
                              @Origin Method method) throws Exception {
        //System.out.println("the obj: "+obj.toString());
        System.out.println("the arguments: "+ Arrays.toString(arguments));
        System.out.println("the callable: "+callable);
        System.out.println("the method: "+method.getName());

        System.out.println("---[BEGIN] SampleMethodInterceptor 实例方法拦截器");
        Object ret = null;
        try {
            System.out.println("    class name = " + obj.getClass().getName());
            System.out.println("    method name = " + method.getName());
            ret = callable.call();
        } catch (Throwable t) {
            throw t;
        } finally {
            System.out.println("---[END] SampleMethodInterceptor 实例方法拦截器");
        }
        return ret;
    }*/

    //2.拦截静态方法 ,自定义方法拦截
    @RuntimeType
    public Object interceptor(@Origin Class<?> clazz,
                              @AllArguments Object[] arguments,
                              @Origin Method method,
                              @SuperCall Callable<?> callable) throws Exception {
        System.out.println("the arguments: "+ Arrays.toString(arguments));
        System.out.println("the callable: "+callable);
        System.out.println("the method: "+method.getName());

        System.out.println("---[BEGIN] SampleMethodInterceptor 静态方法拦截器");
        Object ret = null;
        try {
            System.out.println("    class name = " + clazz.getName());
            System.out.println("    method name = " + method.getName());
            ret = callable.call();
        } catch (Throwable t) {
            throw t;
        } finally {
            System.out.println("---[END] SampleMethodInterceptor 静态方法拦截器");
        }
        return ret;
    }
}
