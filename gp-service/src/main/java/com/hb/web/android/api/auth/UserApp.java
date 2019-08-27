package com.hb.web.android.api.auth;

import com.hb.facade.entity.UserDO;
import com.hb.facade.enumutil.RealAuthStatusEnum;
import com.hb.facade.vo.appvo.request.ModifyPasswordRequestVO;
import com.hb.facade.vo.appvo.response.IDCardInfoResponseVO;
import com.hb.remote.constant.enumutil.BankCardAuthResEnum;
import com.hb.remote.constant.enumutil.IdCardAuthResEnum;
import com.hb.remote.model.BankCardAuthResult;
import com.hb.remote.model.IdCardAuthResult;
import com.hb.remote.service.IRealNameAuth;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.helper.LogHelper;
import com.hb.unic.util.util.CloneUtils;
import com.hb.web.android.api.noauth.LoginApp;
import com.hb.web.android.base.BaseApp;
import com.hb.web.api.IUserService;
import com.hb.facade.common.AppResponseCodeEnum;
import com.hb.facade.common.AppResultModel;
import com.hb.web.tool.TokenTools;
import com.hb.web.util.LogUtils;
import com.hb.facade.vo.appvo.request.BankCardRealNameAuthRequestVO;
import com.hb.facade.vo.appvo.request.BankCardRequestVO;
import com.hb.facade.vo.appvo.request.IdCardRealNameAuthRequestVO;
import com.hb.facade.vo.appvo.response.BankCardRealNameAuthResponseVO;
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

    @Autowired
    private IRealNameAuth realNameAuth;

    @ApiOperation(value = "获取用户信息")
    @PostMapping("/getUser")
    public AppResultModel<UserDO> getUser() {
        UserDO userCache = getUserCache();
        UserDO result = iUserService.findUser(new UserDO(userCache.getUserId()));
        if (result == null) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_EXIST_USER);
        }
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

    @ApiOperation(value = "身份证实名认证")
    @PostMapping("/check_cn_card_id")
    public AppResultModel idCardRealNameAuth(@RequestBody IdCardRealNameAuthRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("身份证实名认证，入参：{}"), String.valueOf(requestVO));
        if (StringUtils.isAnyBlank(requestVO.getCardName(), requestVO.getCardName())) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        UserDO userCache = getUserCache();
        try {
            IdCardAuthResult idCardAuthResult = realNameAuth.idCardAuth(requestVO.getCardNo(), requestVO.getCardName());
            if (idCardAuthResult == null || idCardAuthResult.getResult() == null) {
                return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
            }
            if (!StringUtils.equals(IdCardAuthResEnum.success.getCode(), idCardAuthResult.getCode())
                    || !idCardAuthResult.getResult().getResult().getIsok()) {
                return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
            }
            /**
             * 实名认证通过
             * 1.更新实名认证状态
             * 2.更新真实姓名
             * 3.更新证件号码
             */
            UserDO update = new UserDO();
            update.setRealAuthStatus(RealAuthStatusEnum.IS_AUTH.getValue());
            update.setRealName(requestVO.getCardName());
            update.setIdCardNo(requestVO.getCardNo());
            boolean success = iUserService.updateUserById(userCache.getUserId(), update);
            LOGGER.info(LogUtils.appLog("身份证实名认证，更新实名认证状态：{}"), success);
            if (!success) {
                return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
            }
            return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(LogUtils.appLog("身份证实名认证，异常：{}"), LogHelper.getStackTrace(e));
            alarmTools.alert("APP", "用户", "身份证实名认证", "异常：" + e.getMessage());
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
    }

    @ApiOperation(value = "银行卡实名认证")
    @PostMapping("/bankCardRealNameAuth")
    public AppResultModel bankCardRealNameAuth(@RequestBody BankCardRealNameAuthRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("银行卡实名认证，入参：{}"), String.valueOf(requestVO));
        if (StringUtils.isAnyBlank(requestVO.getBankNo())) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        UserDO userCache = getUserCache();
        UserDO currentUser = iUserService.findUser(new UserDO(userCache.getUserId()));
        if (RealAuthStatusEnum.IS_AUTH.getValue().equals(currentUser.getRealAuthStatus())) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_IDCARD_REALNAME_AUTH);
        }
        try {
            BankCardAuthResult bankCardAuthResult = realNameAuth.bankCardAuth(requestVO.getBankNo(), currentUser.getIdCardNo(), currentUser.getRealName());
            if (bankCardAuthResult == null || bankCardAuthResult.getResult() == null) {
                return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
            }
            if (!StringUtils.equals(BankCardAuthResEnum.success.getCode(), bankCardAuthResult.getCode())) {
                return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL.getCode(), bankCardAuthResult.getMessage());
            }
            /**
             * 实名认证通过
             * 1.更新银行卡实名认证状态
             * 2.更新银行卡号
             */
            UserDO update = new UserDO();
            update.setBankRealAuthStatus(RealAuthStatusEnum.IS_AUTH.getValue());
            update.setBankNo(requestVO.getBankNo());
            update.setBankName(bankCardAuthResult.getResult().getBank());
            boolean success = iUserService.updateUserById(userCache.getUserId(), update);
            LOGGER.info(LogUtils.appLog("银行卡实名认证，更新实名认证状态：{}"), success);
            if (!success) {
                return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
            }
            return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(LogUtils.appLog("银行卡实名认证，异常：{}"), LogHelper.getStackTrace(e));
            alarmTools.alert("APP", "用户", "银行卡实名认证", "异常：" + e.getMessage());
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/modify_user")
    public AppResultModel modifyUser(@RequestBody UserDO userDO) {
        LOGGER.info(LogUtils.appLog("修改用户信息,入参：{}"), userDO);
        UserDO userCache = getUserCache();
        UserDO clone = CloneUtils.clone(userDO, UserDO.class);
        boolean success = iUserService.updateUserById(userCache.getUserId(), clone);
        LOGGER.info(LogUtils.appLog("修改用户信息：{}"), success);
        if (!success) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/modify_password")
    public AppResultModel modifyPasswod(@RequestBody ModifyPasswordRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("修改密码,入参：{}"), requestVO);
        String newPassword = requestVO.getNewPassword();
        if (StringUtils.isBlank(newPassword)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        UserDO userCache = getUserCache();
        UserDO update = new UserDO();
        update.setPassword(newPassword);
        boolean success = iUserService.updateUserById(userCache.getUserId(), update);
        LOGGER.info(LogUtils.appLog("修改密码：{}"), success);
        if (!success) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "获取身份证信息")
    @PostMapping("/get_cn_card_id")
    public AppResultModel<IDCardInfoResponseVO> getIdCardInfo() {
        LOGGER.info(LogUtils.appLog("获取身份证信息"));
        UserDO userCache = getUserCache();
        UserDO query = new UserDO(userCache.getUserId());
        UserDO user = iUserService.findUser(query);
        LOGGER.info(LogUtils.appLog("获取身份证信息：{}"), user);
        IDCardInfoResponseVO responseVO = new IDCardInfoResponseVO();
        String realName = user.getRealName();
        if (StringUtils.isNotBlank(realName)) {
            realName = StringUtils.repeat("*", realName.length() - 1) + realName.substring(realName.length() - 1);
        }
        responseVO.setCardName(realName);
        String idCardNo = user.getIdCardNo();
        if (StringUtils.isNotBlank(idCardNo)) {
            idCardNo = idCardNo.substring(0, idCardNo.length() - 6) + StringUtils.repeat("*", 6);
        }
        responseVO.setCardNo(idCardNo);
        responseVO.setStatus(user.getRealAuthStatus());
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

}