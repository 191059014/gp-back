package com.hb.web.common;

/**
 * ========== 告警消息实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.common.Alarm.java, v1.0
 * @date 2019年07月15日 20时07分
 */
public class Alarm {

    /**
     * 模块名
     */
    private String moduleName;
    /**
     * 接口描述
     */
    private String apiDesc;
    /**
     * 信息
     */
    private String message;

    public Alarm(String moduleName, String apiDesc, String message) {
        this.moduleName = moduleName;
        this.apiDesc = apiDesc;
        this.message = message;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(String apiDesc) {
        this.apiDesc = apiDesc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "moduleName='" + moduleName + '\'' +
                ", apiDesc='" + apiDesc + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
