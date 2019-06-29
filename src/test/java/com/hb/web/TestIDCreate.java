package com.hb.web;

import com.hb.web.util.IDCreateUtils;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.TestIDCreate.java, v1.0
 * @date 2019年06月26日 00时26分
 */
public class TestIDCreate {

    public static void main(String[] args) {
        objLock();
    }

    public static void objLock() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String agentId = IDCreateUtils.agentIdCreator().generateAgentId();
                System.out.println(agentId);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = IDCreateUtils.userIdCreator().generateUserId();
                System.out.println(userId);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = IDCreateUtils.userIdCreator().generateUserId();
                System.out.println(userId);
            }
        }).start();
    }

}
