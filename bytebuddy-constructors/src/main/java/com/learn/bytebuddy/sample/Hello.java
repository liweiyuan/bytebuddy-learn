package com.learn.bytebuddy.sample;

/**
 * @Author :lwy
 * @Date : 2018/10/19 13:06
 * @Description :
 */
public class Hello {


    private String name;

    public Hello(String name) {
        this.name = name;
    }

    public Hello() {
    }

    public String sayHello(){
        return name;
    }
}
