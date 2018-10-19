package com.learn.bytebuddy.interceptor;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.util.Arrays;

/**
 * @Author :lwy
 * @Date : 2018/10/19 13:12
 * @Description :
 */
public class SampleConstructorStringInterceptor {
    @RuntimeType
    public Object interceptor(@This Object obj,
                              @AllArguments Object[] arguments){

        System.out.println("arguments: "+ Arrays.toString(arguments));
        System.out.println("---[BEGIN] SimpleConstructorStringInterceptor 有参构造器[String]");
        try {
            System.out.println("    arguments number = " + arguments.length);
        } finally {
            System.out.println("---[END] SimpleConstructorStringInterceptor 有参构造器[String]");
        }
        return null;
    }
}
