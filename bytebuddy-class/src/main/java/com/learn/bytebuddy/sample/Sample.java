package com.learn.bytebuddy.sample;

/**
 * @Author :lwy
 * @Date : 2018/10/19 11:38
 * @Description :
 */
public class Sample {

    //实例方法拦截
    public void app(String name){
        System.err.println("app name ："+name);
    }

    //静态方法拦截
    public static String staticMethod(){
        return "static";
    }

}
