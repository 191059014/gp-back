package com.hb.web.vo.appvo.response;

import com.hb.web.model.OfflinePayChekDO;

import java.io.Serializable;
import java.util.List;

/**
 * ========== 线下支付查询响应信息 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.appvo.response.OfflinePayQueryResponseVO.java, v1.0
 * @date 2019年07月06日 17时44分
 */
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
