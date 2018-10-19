package com.learn.bytebuddy.sample;

/**
 * @Author :lwy
 * @Date : 2018/10/19 15:48
 * @Description :
 */
public class Hello {

    private String name = "name";

    public Hello() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHello(String hello, String world) {
        this.name = hello + ":" + world;
        return hello + world;
    }
}
