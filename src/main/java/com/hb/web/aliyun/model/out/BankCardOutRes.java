package com.hb.web.aliyun.model.out;

import java.io.Serializable;

/**
 * ========== 银行卡实名认证第三方返回结果 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.model.out.BankCardOutRes.java, v1.0
 * @date 2019年08月12日 21时57分
 */
public class BankCardOutRes implements Serializable {

    private static final long serialVersionUID = -6969233965703399742L;

    /*状态码:01 验证通过；02 验证不通过；详见状态码说明*/
    private String status;
    /*信息*/
    private String msg;
    /*身份证号*/
    private String idCard;
    /*银行卡号*/
    private String accountNo;
    /*姓名*/
    private String name;
    /*银行名称*/
    /*验证不通过时以下都为空*/
    private String bank;
    /*银行卡名称*/
    private String cardName;
    /*银行卡类型*/
    private String cardType;
    /*性别*/
    private String sex;
    /*身份证所在地址(参考)*/
    private String area;
    /*省(参考)*/
    private String province;
    /*市(参考)*/
    private String city;
    /*区县(参考)*/
    private String prefecture;
    /*出生年月*/
    private String birthday;
    /*地区代码*/
    private String addrCode;
    /*校验码*/
    private String lastCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddrCode() {
        return addrCode;
    }

    public void setAddrCode(String addrCode) {
        this.addrCode = addrCode;
    }

    public String getLastCode() {
        return lastCode;
    }

    public void setLastCode(String lastCode) {
        this.lastCode = lastCode;
    }

    @Override
    public String toString() {
        return "BankCardOutRes{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", idCard='" + idCard + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", name='" + name + '\'' +
                ", bank='" + bank + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", sex='" + sex + '\'' +
                ", area='" + area + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", prefecture='" + prefecture + '\'' +
                ", birthday='" + birthday + '\'' +
                ", addrCode='" + addrCode + '\'' +
                ", lastCode='" + lastCode + '\'' +
                '}';
    }
}
