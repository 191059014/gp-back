package com.hb.web.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 实名认证请求参数 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.appvo.request.IdCardRealNameAuthRequestVO.java, v1.0
 * @date 2019年08月13日 23时39分
 */
public class IdCardRealNameAuthRequestVO implements Serializable {

    private static final long serialVersionUID = -9107520801278115201L;

    private String cardNo;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "IdCardRealNameAuthRequestVO{" +
                "cardNo='" + cardNo + '\'' +
                '}';
    }
}
