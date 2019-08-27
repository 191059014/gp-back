package com.hb.facade.vo.appvo.response;

import java.io.Serializable;

/**
 * ========== 身份证信息 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.response.IDCardInfoResponseVO.java, v1.0
 * @date 2019年08月27日 08时21分
 */
public class IDCardInfoResponseVO implements Serializable {

    private static final long serialVersionUID = 7142950834207332359L;

    private String cardName;
    private String cardNo;
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IDCardInfoResponseVO{" +
                "cardName='" + cardName + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
