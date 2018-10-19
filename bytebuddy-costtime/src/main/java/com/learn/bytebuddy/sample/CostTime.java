package com.learn.bytebuddy.sample;

/**
 * @Author :lwy
 * @Date : 2018/10/19 14:52
 * @Description :
 */
public class CostTime {

    public void cost(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
