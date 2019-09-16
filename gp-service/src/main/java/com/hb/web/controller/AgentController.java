package com.hb.web.controller;

import com.hb.facade.entity.AgentDO;
import com.hb.facade.vo.webvo.request.UpdatePasswordRequestVO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.helper.LogHelper;
import com.hb.unic.util.util.CloneUtils;
import com.hb.unic.util.util.EncryptUtils;
import com.hb.web.api.IAgentRoleService;
import com.hb.web.api.IAgentService;
import com.hb.web.base.BaseController;
import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.vo.webvo.response.AgentQueryResponseVO;
import com.hb.web.exception.BusinessException;
import com.hb.web.tool.CheckTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ========== 代理商controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.AgentController.java, v1.0
 * @date 2019年06月16日 12时30分
 */
@Api(tags = "[WEB]代理商")
@RestController
@RequestMapping("controller/agent")
public class AgentController extends BaseController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentController.class);

    @Autowired
    private IAgentService iAgentService;

    @Autowired
    private IAgentRoleService iAgentRoleService;

    @ApiOperation(value = "分页条件查询代理商列表")
    @PostMapping("/getAgentListPage")
    public ResponseData<List<AgentQueryResponseVO>> getAgentListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody AgentDO agentDO) {
        List<AgentDO> agentList = iAgentService.findAgentList(agentDO, pageNum, pageSize);
        Integer count = iAgentService.findCount(agentDO);
        List<AgentQueryResponseVO> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(agentList)) {
            for (AgentDO agent : agentList) {
                AgentQueryResponseVO clone = CloneUtils.clone(agent, AgentQueryResponseVO.class);
                Set<Integer> roleIdSet = iAgentRoleService.getRoleIdSetByAgentId(agent.getAgentId());
                clone.setRoleIdSet(roleIdSet);
                resultList.add(clone);
            }
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, resultList, count);
    }

    @ApiOperation(value = "添加代理商")
    @PostMapping("/addAgent")
    public ResponseData addAgent(@RequestBody AgentDO agentDO) {
        AgentDO query = new AgentDO();
        query.setMobile(agentDO.getMobile());
        AgentDO queryAgent = iAgentService.findAgent(query);
        if (StringUtils.isAnyBlank(agentDO.getAgentName(), agentDO.getMobile(), agentDO.getPassword())) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR_PARAM_VERIFY);
        }
        if (!CheckTools.isMobile(agentDO.getMobile())) {
            return ResponseData.generateResponseData(ResponseEnum.MOBILE_ERROR);
        }
        if (queryAgent != null) {
            return ResponseData.generateResponseData(ResponseEnum.MOBILE_ALREADY_EXIST);
        }
        AgentDO agentCache = getAgentCache();
        int result = iAgentService.addAgent(agentDO, agentCache);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询代理商")
    @PostMapping("/findAgent")
    public ResponseData findAgent(@RequestBody AgentDO agentDO) {
        AgentDO result = iAgentService.findAgent(agentDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "修改代理商")
    @PostMapping("/updateAgent")
    public ResponseData updateAgent(@RequestBody AgentDO agentDO) {
        int result = iAgentService.updateAgentById(agentDO.getAgentId(), agentDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/updatePasswrod")
    public ResponseData updatePasswrod(@RequestBody UpdatePasswordRequestVO requestVO) {
        String oldPassword = requestVO.getOldPassword();
        String newPassword = requestVO.getNewPassword();
        if (StringUtils.isAnyBlank(oldPassword, newPassword)) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR_PARAM_VERIFY);
        }
        String oldPasswordEncrypt = EncryptUtils.encode(oldPassword);
        AgentDO agentCache = getAgentCache();
        AgentDO agent = iAgentService.findAgent(new AgentDO(agentCache.getAgentId()));
        if (!StringUtils.equals(oldPasswordEncrypt, agent.getPassword())) {
            return ResponseData.generateResponseData(ResponseEnum.OLD_PASSWORD_WRONG);
        }
        String newPasswordEncrypt = EncryptUtils.encode(newPassword);
        AgentDO update = new AgentDO();
        update.setPassword(newPasswordEncrypt);
        int result = iAgentService.updateAgentById(agentCache.getAgentId(), update);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "实名认证")
    @PostMapping("/realNameAuth")
    public ResponseData realNameAuth(@RequestBody AgentDO agentDO) {
        LOGGER.info("实名认证入参：{}", agentDO);
        String idCardNo = agentDO.getIdCardNo();
        String realName = agentDO.getRealName();
        if (StringUtils.isAnyBlank(idCardNo, realName)) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR_PARAM_VERIFY);
        }
        AgentDO agentCache = getAgentCache();
        try {
            iAgentService.realNameAuth(idCardNo, realName, agentCache);
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.info("{}实名认证失败：{}", agentCache.getAgentName(), LogHelper.getStackTrace(e));
            return ResponseData.generateResponseData(e.getCode(), e.getMsg());
        }
    }

    @ApiOperation(value = "绑定银行卡")
    @PostMapping("/bindBankCard")
    public ResponseData bindBankCard(@RequestBody AgentDO agentDO) {
        LOGGER.info("绑定银行卡入参：{}", agentDO);
        String bankNo = agentDO.getBankNo();
        String bankName = agentDO.getBankName();
        if (StringUtils.isAnyBlank(bankNo, bankName)) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR_PARAM_VERIFY);
        }
        AgentDO agentCache = getAgentCache();
        try {
            iAgentService.bindBankCard(bankNo, bankName, agentCache);
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.info("{}绑定银行卡失败：{}", agentCache.getAgentName(), LogHelper.getStackTrace(e));
            return ResponseData.generateResponseData(e.getCode(), e.getMsg());
        }
    }

    @ApiOperation(value = "余额提取")
    @GetMapping("/balancesExtracted")
    public ResponseData balancesExtracted(@RequestParam("extractedMoney") String extractedMoney) {
        BigDecimal money = BigDecimal.ZERO;
        try {
            money = new BigDecimal(extractedMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return ResponseData.generateResponseData(ResponseEnum.PARAM_TYPE_ERROR);
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

    @ApiOperation(value = "删除代理商")
    @GetMapping("/deleteAgentById")
    public ResponseData deleteAgentById(String agentId) {
        int result = iAgentService.deleteAgentById(agentId);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "获取代理等级下拉框")
    @GetMapping("/getAgentLevelCombobox")
    public ResponseData<List<Map<String, Object>>> getAgentLevelCombobox() {
        List<Map<String, Object>> result = iAgentService.getAgentLevelList();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

}
