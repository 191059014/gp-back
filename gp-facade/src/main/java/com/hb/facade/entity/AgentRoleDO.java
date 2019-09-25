package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;

/**
 * ========== 代理商角色关联表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.model.sys.AgentRoleDO.java, v1.0
 * @date 2019年06月03日 11时33分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@SelfTableClass(value = "t_agent_role", comment = "代理商角色关联表")
public class AgentRoleDO extends BaseDO {
    // serialVersionUID
    private static final long serialVersionUID = -2147404515536143699L;
    // 主键
    @SelfTableColumn(value = "id", id = true, length = 10, comment = "主键")
    private Integer id;
    // 代理商ID
    @SelfTableColumn(value = "agentId", comment = "代理商ID")
    private String agentId;
    // 角色ID
    @SelfTableColumn(value = "roleId", length = 10, comment = "角色ID")
    private Integer roleId;

    public AgentRoleDO() {
    }

    public AgentRoleDO(String agentId, Integer roleId) {
        this.agentId = agentId;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "AgentRoleDO{" +
                "id=" + id +
                ", agentId=" + agentId +
                ", roleId=" + roleId +
                '}' + "," + super.toString();
    }

}
