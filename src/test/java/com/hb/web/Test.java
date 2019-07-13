package com.hb.web;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.Test.java, v1.0
 * @date 2019年07月14日 00时04分
 */
public class Test {

    public static void main(String[] args) {
        String orderId = "A_1";
        Integer.parseInt(orderId.substring(orderId.indexOf("_") + 1));
    }

}
