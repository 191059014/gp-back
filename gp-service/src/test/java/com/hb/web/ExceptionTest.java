package com.hb.web;

import com.hb.unic.base.exception.StandardRuntimeException;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.ExceptionTest.java, v1.0
 * @date 2019年09月05日 15时56分
 */
public class ExceptionTest {

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void test() {
        try {
            int i = 9 / 0;
        } catch (Exception e) {
            throw new StandardRuntimeException("tst occur exception");
        }
    }

}
