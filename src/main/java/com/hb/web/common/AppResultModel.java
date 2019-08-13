package com.hb.web.common;

import java.io.Serializable;

/**
 * ========== 返回app端标准实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.model.AppResultModel.java, v1.0
 * @date 2019年06月13日 23时01分
 */
public class AppResultModel<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3499621305689434392L;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public AppResultModel(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * ########## 根据AppResponseCodeEnum生成响应对象 ##########
     *
     * @param code    响应码
     * @param message 响应码信息
     * @return 响应实体
     */
    public static <T> AppResultModel<T> generateResponseData(Integer code, String message) {
        return generateResponseData(code, message, null);
    }

    /**
     * ########## 根据AppResponseCodeEnum生成响应对象 ##########
     *
     * @param code    响应码
     * @param message 响应码信息
     * @param t       业务参数
     * @return 响应实体
     */
    public static <T> AppResultModel<T> generateResponseData(Integer code, String message, T t) {
        return new AppResultModel<T>(code, message, t);
    }

    /**
     * ########## 根据AppResponseCodeEnum生成响应对象 ##########
     *
     * @param appResponseCodeEnum 响应码枚举对象
     * @return 响应实体
     */
    public static <T> AppResultModel<T> generateResponseData(AppResponseCodeEnum appResponseCodeEnum) {
        return generateResponseData(appResponseCodeEnum, null);
    }

    /**
     * ########## 根据AppResponseCodeEnum和T生成响应对象 ##########
     *
     * @param appResponseCodeEnum 响应码枚举对象
     * @param t                   业务数据
     * @return 响应实体
     */
    public static <T> AppResultModel<T> generateResponseData(AppResponseCodeEnum appResponseCodeEnum, T t) {
        return generateResponseData(appResponseCodeEnum.getCode(), appResponseCodeEnum.getMessage(), t);
    }

    @Override
    public String toString() {
        return "AppResultModel{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + String.valueOf(data) +
                '}';
    }
}
