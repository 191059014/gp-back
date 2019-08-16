package com.hb.web.common;

import java.io.Serializable;

/**
 * ========== 公共返回数据模型 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.common.ResponseData.java, v1.0
 * @date 2019年06月05日 16时18分
 */
public class ResponseData<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3064854136143342618L;
    /**
     * code码
     */
    private String code;
    /**
     * 信息描述
     */
    private String msg;

    /**
     * 对象
     */
    private T obj;

    /**
     * 总条数
     */
    private Integer total;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                ", total=" + total +
                '}';
    }

    /**
     * ########## 根据ResponseEnum生成响应对象 ##########
     *
     * @param responseEnum 响应码枚举对象
     * @return 响应实体
     */
    public static <T> ResponseData<T> generateResponseData(ResponseEnum responseEnum) {
        return generateResponseData(responseEnum.getCode(), responseEnum.getMsg(), null, null);
    }

    /**
     * ########## 根据ResponseEnum和T生成响应对象 ##########
     *
     * @param responseEnum 响应码枚举对象
     * @param t            业务数据
     * @return 响应实体
     */
    public static <T> ResponseData<T> generateResponseData(ResponseEnum responseEnum, T t) {
        return generateResponseData(responseEnum.getCode(), responseEnum.getMsg(), t, null);
    }

    /**
     * ########## 根据ResponseEnum和T和pageNum和count生成分页的响应对象 ##########
     *
     * @param responseEnum 响应码枚举对象
     * @param t            业务数据
     * @param count        总条数
     * @return 响应实体
     */
    public static <T> ResponseData<T> generateResponseData(ResponseEnum responseEnum, T t, Integer count) {
        return generateResponseData(responseEnum.getCode(), responseEnum.getMsg(), t, count);
    }

    /**
     * ########## 生成响应类对象 ##########
     *
     * @param code    响应码
     * @param msg     响应信息
     * @param total   总条数
     * @return 响应实体
     */
    private static <T> ResponseData<T> generateResponseData(String code, String msg, T t, Integer total) {
        ResponseData<T> response = new ResponseData<T>();
        response.setCode(code);
        response.setMsg(msg);
        response.setObj(t);
        response.setTotal(total);
        return response;
    }

    /**
     * ########## 生成响应类对象 ##########
     *
     * @param code 响应码
     * @param msg  响应信息
     * @return 响应实体
     */
    public static <T> ResponseData<T> generateResponseData(String code, String msg) {
        return generateResponseData(code, msg, null, null);
    }

}
