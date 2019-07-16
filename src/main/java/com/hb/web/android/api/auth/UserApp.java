package com.hb.web.android.api.auth;

import com.hb.web.api.IUserService;
import com.hb.web.model.UserDO;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import com.hb.web.vo.appvo.request.BankCardRequestVO;
import com.hb.web.util.LogUtils;
import com.hb.web.android.api.noauth.LoginApp;
import com.hb.web.android.base.BaseApp;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import com.hb.web.tool.TokenTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ========== 用户 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.android.api.auth.UserApp.java, v1.0
 * @date 2019年06月15日 16时28分
 */
@Api(tags = "[APP]用户")
@RestController
@RequestMapping("app/auth/user")
public class UserApp extends BaseApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApp.class);

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "获取用户信息")
    @PostMapping("/getUser")
    public AppResultModel<UserDO> getUser() {
        UserDO userCache = getUserCache();
        UserDO result = iUserService.findUser(new UserDO(userCache.getUserId()));
        result.setPassword("XXXX");
        LOGGER.info(LogUtils.appLog("获取用户信息，响应结果：{}"), result);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, result);
    }

    @ApiOperation(value = "绑定银行卡")
    @PostMapping("/bindBankCard")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel bindBankCard(@RequestBody BankCardRequestVO bankCardRequestVO) {
        LOGGER.info(LogUtils.appLog("绑定银行卡，入参：{}"), String.valueOf(bankCardRequestVO));
        if (bankCardRequestVO == null || StringUtils.isBlank(bankCardRequestVO.getBankNo())) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        UserDO userCache = getUserCache();
        // 更新用户银行卡信息
        UserDO userDO = new UserDO(userCache.getUserId());
        userDO.setBankName(bankCardRequestVO.getBankName());
        userDO.setBankNo(bankCardRequestVO.getBankNo());
        boolean result = iUserService.updateUserById(userCache.getUserId(), userDO);
        if (result) {
            // 查询用户最新信息
            UserDO user = iUserService.findUser(new UserDO(userCache.getUserId()));
            // 更新缓存
            TokenTools.set(user, getToken(), redisTools);
            LOGGER.info(LogUtils.appLog("绑定银行卡成功"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
        } else {
            LOGGER.info(LogUtils.appLog("绑定银行卡失败"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
    }

}
