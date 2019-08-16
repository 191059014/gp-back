package com.hb.web.vo.webvo.request;

import java.io.Serializable;
import java.util.Date;

/**
 * ========== 代理商资金流水请求vo ==========
 *
 * @author Mr.huang
 * @version AgentFundDetailRequestVO.java, v1.0
 * @date 2019年06月13日 15时25分
 */
public class AgentFundDetailRequestVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3857346202720948540L;
    /**
     * 代理商姓名
     */
    private String agentName;
    /**
     * 资金类型
     */
    private String type;
    /**
     * 创建时间起期
     */
    private Date createTimeStart;
    /**
     * 创建时间止期
     */
    private Date createTimeEnd;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    @Override
    public String toString() {
        return "AgentFundDetailRequestVO{" +
                "agentName='" + agentName + '\'' +
                ", type='" + type + '\'' +
                ", createTimeStart=" + createTimeStart +
                ", createTimeEnd=" + createTimeEnd +
                '}';
    }
}
