package com.hb.facade.vo.webvo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.facade.entity.AgentDO;

import java.util.Set;

/**
 * ========== 代理商查询响应vo ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.webvo.response.AgentQueryResponseVO.java, v1.0
 * @date 2019年07月18日 20时21分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentQueryResponseVO extends AgentDO {

    private static final long serialVersionUID = 8560656342312918724L;

    private Set<Integer> roleIdSet;

    public Set<Integer> getRoleIdSet() {
        return roleIdSet;
    }

    public void setRoleIdSet(Set<Integer> roleIdSet) {
        this.roleIdSet = roleIdSet;
    }
}
