package com.learn;

import com.learn.bytebuddy.sample.Sample;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //System.out.println( "Hello World!" );

        //1.实例方法
        //Sample app=new Sample();
        //app.app("apple");

        //2.静态方法
        Sample.staticMethod();
    }

    public void app(String name){
        System.err.println("app name ："+name);
    }
}
