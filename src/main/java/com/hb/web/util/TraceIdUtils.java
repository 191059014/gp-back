package com.hb.web.util;

/**
 * ========== traceId工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.TraceIdUtils.java, v1.0
 * @date 2019年07月16日 10时09分
 */
public class TraceIdUtils {
    /**
     * traceId集合
     */
    private static final ThreadLocal<String> TRACE_ID = new InheritableThreadLocal();

    public static String getTraceId() {
        return (String) TRACE_ID.get();
    }

    public static void setTraceId(String traceId) {
        TRACE_ID.set(traceId);
    }

    public static void removeTraceId() {
        TRACE_ID.remove();
    }

}