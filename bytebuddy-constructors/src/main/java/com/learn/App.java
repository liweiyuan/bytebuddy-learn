package com.learn;

import com.learn.bytebuddy.sample.Hello;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //Hello hello=new Hello();

        Hello hello1=new Hello("wade");

        hello1.sayHello();
    }
}
