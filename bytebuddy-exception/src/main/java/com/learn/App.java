package com.learn;

import com.learn.bytebuddy.sample.Hello;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
       // System.out.println("Hello World!");


        Hello hello=new Hello();
        //hello.setName("wade");
        hello.getHello("wade","bosh");
    }
}
