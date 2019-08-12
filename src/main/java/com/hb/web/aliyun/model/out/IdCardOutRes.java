package com.hb.web.aliyun.model.out;

import java.io.Serializable;

/**
 * ========== 身份证实名认证第三方响应结果 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.model.out.IdCardOutRes.java, v1.0
 * @date 2019年08月12日 22时18分
 */
public class IdCardOutRes implements Serializable {

    private static final long serialVersionUID = -7762315339894257201L;

    private String error_code;
    private String reason;
    private IdCardOutResResult result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public IdCardOutResResult getResult() {
        return result;
    }

    public void setResult(IdCardOutResResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "IdCardOutRes{" +
                "error_code='" + error_code + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + String.valueOf(result) +
                '}';
    }

}
