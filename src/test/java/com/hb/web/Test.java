package com.hb.web;

import java.util.Random;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.Test.java, v1.0
 * @date 2019年07月14日 00时04分
 */
public class Test {

    public static void main(String[] args) {
        String orderId = "OD190716231411999000100001";
        String substring = orderId.substring(21);
        System.out.println(Integer.parseInt(substring));
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    random();
                }
            }).start();
        }
    }

    public static void random() {
        System.out.println(Thread.currentThread().getName() + "" + new Random().nextInt(1000));
    }

}
