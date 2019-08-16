package com.hb.web.controller;

import com.hb.facade.entity.AgentDO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.EncryptUtils;
import com.hb.web.api.IAgentService;
import com.hb.web.base.BaseController;
import com.hb.web.common.ResponseData;
import com.hb.web.common.ResponseEnum;
import com.hb.web.constant.GeneralConst;
import com.hb.web.tool.RedisTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Set;

/**
 * ========== 登陆 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.LoginController.java, v1.0
 * @date 2019年06月06日 11时00分
 */
@ApiIgnore
@RestController
@RequestMapping("controller/login")
public class LoginController extends BaseController {

    @Autowired
    IAgentService iAgentService;

    @Autowired
    RedisTools redisTools;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public ResponseData login(@RequestBody AgentDO agentDO) {
        String mobile = agentDO.getMobile();
        String password = agentDO.getPassword();
        LOGGER.info("用户登陆，mobile：{}，password：{}", mobile, password);
        if (StringUtils.isBlank(mobile)) {
            return ResponseData.generateResponseData(ResponseEnum.USERNAME_NOT_NULL);
        }
        if (StringUtils.isBlank(password)) {
            return ResponseData.generateResponseData(ResponseEnum.PASSWORD_NOT_NULL);
        }
        String encodePassword = EncryptUtils.encode(password);
        LOGGER.info("加密后密码：{}", encodePassword);
        // 查询用户信息
        AgentDO query = new AgentDO();
        query.setMobile(mobile);
        AgentDO agent = iAgentService.findAgent(query);
        LOGGER.info("查询用户信息结果：{}", agent);
        if (agent == null) {
            return ResponseData.generateResponseData(ResponseEnum.USERNAME_WRONG);
        }
        if (!StringUtils.equals(encodePassword, agent.getPassword())) {
            return ResponseData.generateResponseData(ResponseEnum.PASSWORD_WRONG);
        }
        LOGGER.info("登陆成功，准备将用户信息放入缓存");
        redisTools.set(GeneralConst.USER_SESSION_KEY + agent.getAgentId(), agent, GeneralConst.USER_SESSION_EXIRE_TIME);
        LOGGER.info("将用户信息放入缓存成功，过期时间为：{}秒", GeneralConst.USER_SESSION_EXIRE_TIME);
        return ResponseData.generateResponseData(ResponseEnum.LOGIN_SUCCESS, agent);
    }

    @RequestMapping("/getPermissionSet")
    public ResponseData<Set<String>> getPermissionSet() {
        AgentDO agentCache = getAgentCache();
        Set<String> permissionSet = iAgentService.getPermissionSet(agentCache.getAgentId());
        LOGGER.info("代理商[{}]，对应的权限[{}]", agentCache.getAgentId(), permissionSet);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, permissionSet);
    }

}
