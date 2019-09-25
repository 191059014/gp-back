package com.hb.facade.vo.appvo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.facade.entity.OfflinePayChekDO;

import java.io.Serializable;
import java.util.List;

/**
 * ========== 线下支付查询响应信息 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.response.OfflinePayQueryResponseVO.java, v1.0
 * @date 2019年07月06日 17时44分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfflinePayQueryResponseVO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5249161931107415402L;

    /**
     * 线下支付信息列表
     */
    private List<OfflinePayChekDO> offlinePayList;

    public List<OfflinePayChekDO> getOfflinePayList() {
        return offlinePayList;
    }

    public void setOfflinePayList(List<OfflinePayChekDO> offlinePayList) {
        this.offlinePayList = offlinePayList;
    }

    @Override
    public String toString() {
        return "OfflinePayQueryResponseVO{" +
                "offlinePayList=" + offlinePayList +
                '}';
    }

}
