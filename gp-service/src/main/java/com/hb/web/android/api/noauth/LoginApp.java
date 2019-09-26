package com.hb.web.android.api.noauth;

import com.hb.facade.common.AppResponseCodeEnum;
import com.hb.facade.common.AppResultModel;
import com.hb.facade.common.RedisKeyFactory;
import com.hb.facade.constant.AppConstant;
import com.hb.facade.entity.AgentDO;
import com.hb.facade.entity.UserDO;
import com.hb.facade.enumutil.RealAuthStatusEnum;
import com.hb.facade.vo.appvo.request.LoginRequestVO;
import com.hb.facade.vo.appvo.request.MobileVerifyRequestVO;
import com.hb.facade.vo.appvo.request.RegisterRequestVO;
import com.hb.facade.vo.appvo.request.ResetPasswordRequestVO;
import com.hb.facade.vo.appvo.response.LoginResponseVO;
import com.hb.remote.constant.SMSTemplate;
import com.hb.remote.constant.enumutil.SMSResEnum;
import com.hb.remote.model.SMSSendResult;
import com.hb.remote.service.ISMS;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.EncryptUtils;
import com.hb.web.android.base.BaseApp;
import com.hb.web.api.IAgentService;
import com.hb.web.api.IUserService;
import com.hb.web.tool.CheckTools;
import com.hb.web.tool.SecretTools;
import com.hb.web.tool.TokenTools;
import com.hb.web.tool.VerifyCodeTools;
import com.hb.web.util.LogUtils;
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
 * ========== app登陆 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.api.auth.LoginApp.java, v1.0
 * @date 2019年06月13日 23时32分
 */
@Api(tags = "[APP]登陆/注册")
@RestController
@RequestMapping("app/noauth/login")
public class LoginApp extends BaseApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApp.class);

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IAgentService iAgentService;

    @Autowired
    private ISMS sms_106;

    /**
     * ########## app登陆 ##########
     *
     * @return AppResultModel
     */
    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public AppResultModel<LoginResponseVO> login(@RequestBody LoginRequestVO loginRequestVO) {
        String mobile = loginRequestVO.getMobile();
        String password = loginRequestVO.getPassword();
        LOGGER.info(LogUtils.appLog("登陆，用户名：{}，密码：{}"), mobile, password);
        if (StringUtils.isBlank(mobile)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_USERNAME);
        }
        if (StringUtils.isBlank(password)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PASSWORD);
        }
        /**
         * 1. 查询用户名和密码是否匹配
         */
        UserDO userDO = new UserDO();
        userDO.setMobile(mobile);
        UserDO loginUser = iUserService.findUser(userDO);
        LOGGER.info(LogUtils.appLog("查询用户信息结果：{}"), loginUser);
        if (loginUser == null) {
            LOGGER.info(LogUtils.appLog("用户名不正确"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_USERNAME);
        }
        String encodePassword = EncryptUtils.encode(password);
        if (!StringUtils.equals(encodePassword, loginUser.getPassword())) {
            LOGGER.info(LogUtils.appLog("密码不正确"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PASSWORD);
        }
        LOGGER.info(LogUtils.appLog("登陆成功"));
        /**
         * 2. 生成toke和secret
         */
        String token = TokenTools.generate();
        String secret = SecretTools.generate();
        /**
         * 3. 放入data里面
         */
        LoginResponseVO loginResponseVO = new LoginResponseVO(token, secret);
        /**
         * 4. 将token和用户信息放到缓存
         */
        TokenTools.set(loginUser, token, redisCacheService);
        redisCacheManage.setUserCache(loginUser);
        LOGGER.info(LogUtils.appLog("登陆，响应信息：{}"), loginResponseVO);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, loginResponseVO);
    }

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel<LoginResponseVO> register(@RequestBody RegisterRequestVO registerRequestVO) {
        LOGGER.info(LogUtils.appLog("注册用户，入参：{}"), String.valueOf(registerRequestVO));
        if (registerRequestVO == null) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        String userName = registerRequestVO.getUserName();
        String password = registerRequestVO.getPassword();
        String mobile = registerRequestVO.getMobile();
        String mobileVerifyCode = registerRequestVO.getMobileVerifyCode();
        String inviterMobile = registerRequestVO.getInviterMobile();
        if (StringUtils.isAnyBlank(password, mobile, mobileVerifyCode, inviterMobile, userName)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        if (!CheckTools.isMobile(mobile)) {
            LOGGER.info(LogUtils.appLog("注册用户，手机号格式不正确"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_MOBILE_FORMAT);
        }
        if (!CheckTools.checkPassword(password)) {
            LOGGER.info(LogUtils.appLog("注册用户，密码格式不正确"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PASSWORD_FORMAT);
        }
        String mobileVerifyCodeCache = redisCacheService.get(RedisKeyFactory.getMobileVerifyKey(mobile));
        LOGGER.info(LogUtils.appLog("注册用户，查询缓存中的手机验证码：{}"), mobileVerifyCodeCache);
        if (StringUtils.isBlank(mobileVerifyCodeCache) || !StringUtils.equals(mobileVerifyCode, mobileVerifyCodeCache)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.INVALID_MOBILE_VERIFYCODE);
        }
        UserDO query = new UserDO();
        query.setMobile(mobile);
        UserDO alreadyUser = iUserService.findUser(query);
        if (alreadyUser != null) {
            LOGGER.info(LogUtils.appLog("该手机号已经注册过"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.MOBILE_ALREADY_EXIST);
        }
        AgentDO agentQuery = new AgentDO();
        agentQuery.setMobile(inviterMobile);
        AgentDO existAgent = iAgentService.findAgent(agentQuery);
        if (existAgent == null) {
            LOGGER.info(LogUtils.appLog("邀请人手机号错误"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_INVITER_MOBILE);
        }

        UserDO userDO = new UserDO();
        userDO.setUserName(userName);
        userDO.setPassword(password);
        userDO.setMobile(mobile);
        userDO.setInviterMobile(inviterMobile);
        userDO.setIconPath(registerRequestVO.getIconPath());
        userDO.setUnit(existAgent.getUnit());
        userDO.setRealAuthStatus(RealAuthStatusEnum.NO_AUTH.getValue());
        userDO.setBankRealAuthStatus(RealAuthStatusEnum.NO_AUTH.getValue());
        // 添加用户
        iUserService.addUser(userDO);
        LOGGER.info(LogUtils.appLog("注册用户成功"));
        /**
         * 2. 生成toke和secret
         */
        String token = TokenTools.generate();
        String secret = SecretTools.generate();
        /**
         * 3. 放入data里面
         */
        LoginResponseVO loginResponseVO = new LoginResponseVO(token, secret);
        /**
         * 4. 将token和用户信息放到缓存
         */
        TokenTools.set(userDO, token, redisCacheService);
        redisCacheManage.setUserCache(userDO);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, loginResponseVO);
    }

    @ApiOperation(value = "获取手机验证码")
    @PostMapping("/getMobileVerifyCode")
    public AppResultModel<String> getMobileVerifyCode(@RequestBody MobileVerifyRequestVO mobileVerifyRequestVO) {
        LOGGER.info(LogUtils.appLog("获取手机验证码，入参：{}"), mobileVerifyRequestVO);
        String mobile = mobileVerifyRequestVO == null ? "" : mobileVerifyRequestVO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }

        Integer mobileVerifyCode = VerifyCodeTools.generateMobileVerifyCode();
        SMSSendResult smsSendResult = sms_106.send(mobile, mobileVerifyCode, SMSTemplate.template_1, AppConstant.MOBILE_VERIFYCODE_EXPIRE_TIME);
        if (!StringUtils.equals(SMSResEnum.success.getCode(), smsSendResult.getCode())) {
            alarmTools.alert("APP", "注册", "发送短信验证码", "失败：" + mobile + "：" + smsSendResult.getMessage());
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        // 将手机验证码放入缓存，默认时间
        redisCacheService.set(RedisKeyFactory.getMobileVerifyKey(mobile), mobileVerifyCode, AppConstant.MOBILE_VERIFYCODE_EXPIRE_TIME * 60);
        LOGGER.info(LogUtils.appLog("获取手机验证码并将其放入缓存成功：{}"), mobileVerifyCode);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "重置密码")
    @PostMapping("/reset_password")
    public AppResultModel resetPassword(@RequestBody ResetPasswordRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("重置密码，入参：{}"), requestVO);
        String phoneNum = requestVO.getPhoneNum();
        String verify = requestVO.getVerify();
        String password = requestVO.getPassword();
        if (StringUtils.isAnyBlank(phoneNum, verify, password)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        String mobileVerifyKey = RedisKeyFactory.getMobileVerifyKey(phoneNum);
        String mobileVerify = redisCacheService.get(mobileVerifyKey);
        LOGGER.info(LogUtils.appLog("重置密码从缓存里获取验证码：{}"), mobileVerify);
        if (mobileVerify == null) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.INVALID_MOBILE_VERIFYCODE);
        }
        if (!StringUtils.equals(verify, mobileVerify)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_MOBILE_VERIFYCODE);
        }
        UserDO userCache = getCurrentUserCache();
        if (StringUtils.isNotBlank(password)) {
            userCache.setPassword(EncryptUtils.encode(password));
        }
        boolean success = iUserService.updateUserById(userCache.getUserId(), userCache);
        LOGGER.info(LogUtils.appLog("重置密码结果：{}"), success);
        if (!success) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        updateUserCache(userCache);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "重置支付密码")
    @PostMapping("/reset_pay_password")
    public AppResultModel resetPayPassword(@RequestBody ResetPasswordRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("重置支付密码，入参：{}"), requestVO);
        String phoneNum = requestVO.getPhoneNum();
        String verify = requestVO.getVerify();
        String payPassword = requestVO.getPayPassword();
        if (StringUtils.isAnyBlank(phoneNum, verify, payPassword)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        String mobileVerifyKey = RedisKeyFactory.getMobileVerifyKey(phoneNum);
        String mobileVerify = redisCacheService.get(mobileVerifyKey);
        LOGGER.info(LogUtils.appLog("重置支付密码从缓存里获取验证码：{}"), mobileVerify);
        if (mobileVerify == null) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.INVALID_MOBILE_VERIFYCODE);
        }
        if (!StringUtils.equals(verify, mobileVerify)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_MOBILE_VERIFYCODE);
        }
        UserDO userCache = getCurrentUserCache();
        if (StringUtils.isNotBlank(payPassword)) {
            userCache.setPayPassword(EncryptUtils.encode(payPassword));
        }
        boolean success = iUserService.updateUserById(userCache.getUserId(), userCache);
        LOGGER.info(LogUtils.appLog("重置支付密码结果：{}"), success);
        if (!success) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        updateUserCache(userCache);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

}
