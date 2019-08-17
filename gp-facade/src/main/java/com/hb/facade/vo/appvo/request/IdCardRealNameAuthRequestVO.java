package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 实名认证请求参数 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.IdCardRealNameAuthRequestVO.java, v1.0
 * @date 2019年08月13日 23时39分
 */
public class IdCardRealNameAuthRequestVO implements Serializable {

    private static final long serialVersionUID = -9107520801278115201L;

    private String cardName;

    private String cardNo;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "IdCardRealNameAuthRequestVO{" +
                "cardName='" + cardName + '\'' +
                "cardNo='" + cardNo + '\'' +
                '}';
    }
}
