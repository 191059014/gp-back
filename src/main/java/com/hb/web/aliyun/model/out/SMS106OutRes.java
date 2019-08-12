package com.hb.web.aliyun.model.out;

import java.io.Serializable;

/**
 * ========== 106发送短信响应体 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.model.out.SMS106OutRes.java, v1.0
 * @date 2019年08月12日 20时40分
 */
public class SMS106OutRes implements Serializable {

    private static final long serialVersionUID = 4182436981560843495L;

    private String ReturnStatus;
    private String Message;
    private String RemainPoint;
    private String TaskID;
    private String SuccessCounts;

    public String getReturnStatus() {
        return ReturnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        ReturnStatus = returnStatus;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRemainPoint() {
        return RemainPoint;
    }

    public void setRemainPoint(String remainPoint) {
        RemainPoint = remainPoint;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getSuccessCounts() {
        return SuccessCounts;
    }

    public void setSuccessCounts(String successCounts) {
        SuccessCounts = successCounts;
    }

    @Override
    public String toString() {
        return "SMS106OutRes{" +
                "ReturnStatus='" + ReturnStatus + '\'' +
                ", Message='" + Message + '\'' +
                ", RemainPoint='" + RemainPoint + '\'' +
                ", TaskID='" + TaskID + '\'' +
                ", SuccessCounts='" + SuccessCounts + '\'' +
                '}';
    }
}
