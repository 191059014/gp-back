package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.AppPages.java, v1.0
 * @date 2019年08月17日 11时20分
 */
public class AppPages implements Serializable {
    /**
     * 开始行数
     */
    protected Integer startRow;
    /**
     * 每页条数
     */
    protected Integer pageSize;

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "AppPages{" +
                "startRow=" + startRow +
                ", pageSize=" + pageSize +
                '}';
    }
}
