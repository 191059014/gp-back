package com.hb.web;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.Work.java, v1.0
 * @date 2019年07月21日 23时55分
 */
public class Work {

    private static int num = 0;

    public void changeNum(int newNum) {
        num = newNum;
    }

    public void printNum() {
        System.out.println(num);
    }

}
