package com.hb.web;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.WorkTest.java, v1.0
 * @date 2019年07月21日 23时59分
 */
public class WorkTest {

    public static void main(String[] args) {
        Work work = new Work();
        work.printNum();
        work.changeNum(1);
        Work work1 = new Work();
        work.printNum();

        String c = "0";
        System.out.println(c.getBytes().length);

        for (int i = 0; i < 500; i++) {
            System.out.println(System.currentTimeMillis());
        }

        System.out.println(1 ^ 2);
    }

}
