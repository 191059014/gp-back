package com.hb.web.android.api.noauth;

import com.hb.web.api.IAgentService;
import com.hb.web.api.IUserService;
import com.hb.web.constant.AppConstant;
import com.hb.web.model.AgentDO;
import com.hb.web.model.UserDO;
import com.hb.web.tool.*;
import com.hb.web.vo.appvo.request.LoginRequestVO;
import com.hb.web.vo.appvo.request.MobileVerifyRequestVO;
import com.hb.web.vo.appvo.request.RegisterRequestVO;
import com.hb.web.vo.appvo.response.LoginResponseVO;
import com.hb.web.util.EncryptUtils;
import com.hb.web.util.LogUtils;
import com.hb.web.android.base.BaseApp;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
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
        TokenTools.set(loginUser, token, redisTools);

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
        String password = registerRequestVO.getPassword();
        String mobile = registerRequestVO.getMobile();
        String mobileVerifyCode = registerRequestVO.getMobileVerifyCode();
        String inviterMobile = registerRequestVO.getInviterMobile();
        if (StringUtils.isAnyBlank(password, mobile, mobileVerifyCode, inviterMobile)) {
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
        String mobileVerifyCodeCache = redisTools.get(AppConstant.MOBILE_VERIFYCODE_CACHE_KEY + mobile);
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
        userDO.setPassword(password);
        userDO.setMobile(mobile);
        userDO.setInviterMobile(inviterMobile);
        userDO.setUnit(agentQuery.getUnit());
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
        TokenTools.set(userDO, token, redisTools);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, loginResponseVO);
    }

    @ApiOperation(value = "获取手机验证码（暂时模拟，后面调用短信平台）")
    @PostMapping("/getMobileVerifyCode")
    public AppResultModel<String> getMobileVerifyCode(@RequestBody MobileVerifyRequestVO mobileVerifyRequestVO) {
        LOGGER.info(LogUtils.appLog("获取手机验证码，入参：{}"), mobileVerifyRequestVO);
        if (mobileVerifyRequestVO == null || StringUtils.isBlank(mobileVerifyRequestVO.getMobile())) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        Integer mobileVerifyCode = VerifyCodeTools.generateMobileVerifyCode();
        // 将手机验证码放入缓存，默认时间
        redisTools.set(AppConstant.MOBILE_VERIFYCODE_CACHE_KEY + mobileVerifyRequestVO.getMobile(), mobileVerifyCode, AppConstant.MOBILE_VERIFYCODE_EXPIRE_TIME);
        LOGGER.info(LogUtils.appLog("获取手机验证码并将其放入缓存成功：{}"), mobileVerifyCode);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

}
