package com.hb.web.aliyun.model.out;

import java.io.Serializable;

/**
 * ========== 身份证实名认证-验证结果 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.model.out.IdCardOutResResult.java, v1.0
 * @date 2019年08月12日 23时17分
 */
public class IdCardOutResResult implements Serializable {

    private static final long serialVersionUID = -1673679606799256580L;

    /*真实姓名*/
    private String realname;
    /*身份证号码*/
    private String idcard;
    /*true：匹配 false：不匹配*/
    private boolean isok;
    private IdCardOutResResultIdCardInfo IdCardInfor;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public boolean getIsok() {
        return isok;
    }

    public void setIsok(boolean isok) {
        this.isok = isok;
    }

    public IdCardOutResResultIdCardInfo getIdCardInfor() {
        return IdCardInfor;
    }

    public void setIdCardInfor(IdCardOutResResultIdCardInfo idCardInfor) {
        IdCardInfor = idCardInfor;
    }

    @Override
    public String toString() {
        return "Result{" +
                "realname='" + realname + '\'' +
                ", idcard='" + idcard + '\'' +
                ", isok='" + isok + '\'' +
                ", IdCardInfor=" + String.valueOf(IdCardInfor) +
                '}';
    }
}
