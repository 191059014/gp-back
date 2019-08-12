package com.hb.web.aliyun.model.out;

import java.io.Serializable;

/**
 * ========== 身份证实名认证-身份证基本信息 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.model.out.IdCardOutResResultIdCardInfo.java, v1.0
 * @date 2019年08月12日 23时18分
 */
public class IdCardOutResResultIdCardInfo implements Serializable {

    private static final long serialVersionUID = -1635467191782808388L;

    private String area;
    private String sex;
    private String birthday;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "IdCardInfo{" +
                "area='" + area + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
