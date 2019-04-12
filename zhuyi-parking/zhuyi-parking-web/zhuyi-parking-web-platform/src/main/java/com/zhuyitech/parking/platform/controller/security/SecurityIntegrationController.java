package com.zhuyitech.parking.platform.controller.security;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.scapegoat.infrastructure.session.JwtSessionHandler;
import com.scapegoat.infrastructure.session.SessionHandler;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.scapegoat.infrastructure.web.utils.ActionUtils;
import com.zhuyitech.parking.common.enums.TerminateType;
import com.zhuyitech.parking.platform.config.PlatformConfig;
import com.zhuyitech.parking.platform.config.SmsConfig;
import com.zhuyitech.parking.platform.contants.Constants;
import com.zhuyitech.parking.shiro.ShiroUtils;
import com.zhuyitech.parking.shiro.authc.AlipayUserIdToken;
import com.zhuyitech.parking.shiro.authc.VerifyCodeToken;
import com.zhuyitech.parking.shiro.authc.WeixinUnionIdToken;
import com.zhuyitech.parking.ucc.account.AccountService;
import com.zhuyitech.parking.ucc.account.request.*;
import com.zhuyitech.parking.ucc.car.UserCarInfoService;
import com.zhuyitech.parking.ucc.car.request.UserCarCountByIdRequestDto;
import com.zhuyitech.parking.ucc.car.result.UserCarCountResultDto;
import com.zhuyitech.parking.ucc.dto.request.visitlog.VisitLogAddRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserHasRegisteredResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserTagResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.*;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.enums.UserAccountTypeEnum;
import com.zhuyitech.parking.ucc.enums.UserLoginTypeEnum;
import com.zhuyitech.parking.ucc.service.api.SecurityService;
import com.zhuyitech.parking.ucc.service.api.UserService;
import com.zhuyitech.parking.ucc.service.api.VisitLogService;
import com.zhuyitech.parking.ucc.user.dto.*;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckRequestDto;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckResultDto;
import com.zhuyitech.sms.enums.SmsResultCodeEnum;
import com.zhuyitech.sms.service.api.SmsSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 安全认证控制器
 *
 * @author walkman
 */
@Api(tags = "登录注册", value = "登录注册Api", description = "登录注册Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/security")
@Slf4j
public class SecurityIntegrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private VisitLogService visitLogService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SmsSendService smsSendService;

    @Autowired
    private UserCarInfoService userCarInfoService;

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private PlatformConfig platformConfig;

    /**
     * 登录用户
     */
    @ApiOperation(value = "用户登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserLoginResultDto.class)
    @RequestMapping(value = "/login", name = "用户登录", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserLoginResultDto> signIn(UserSignInRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        if (requestDto.getLoginType().equals(UserLoginTypeEnum.LOGIN_PASSWORD.getValue())) {
            return login(requestDto, request, response);
        } else if (requestDto.getLoginType().equals(UserLoginTypeEnum.LOGIN_VERIFY_CODE.getValue())) {
            return verifyLogin(requestDto, request, response);
        } else if (requestDto.getLoginType().equals(UserLoginTypeEnum.LOGIN_WX.getValue())) {
            return wxLogin(requestDto, request, response);
        } else if (requestDto.getLoginType().equals(UserLoginTypeEnum.LOGIN_ALI.getValue())) {
            return aliLogin(requestDto, request, response);
        } else {
            return login(requestDto, request, response);
        }
    }

    /**
     * 用户登录
     *
     * @param requestDto          requestDto
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     */
    private ObjectResultDto<UserLoginResultDto> login(UserSignInRequestDto requestDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();

        if (StringUtils.isEmpty(requestDto.getAccount())) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_NAME_EMPTY.getValue(), UCenterResultEnum.USER_NAME_EMPTY.getComment());
        }

        if (StringUtils.isEmpty(requestDto.getCode())) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_PASSWORD_EMPTY.getValue(), UCenterResultEnum.USER_PASSWORD_EMPTY.getComment());
        }

        final Date now = new Date();
        UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
        userGetRequestDto.setUsername(requestDto.getAccount());
        ObjectResultDto<UserResultDto> userObjectResult = userService.getUser(userGetRequestDto);
        if (userObjectResult.isSuccess() && userObjectResult.getData() == null) {
            userGetRequestDto = new UserGetRequestDto();
            userGetRequestDto.setPhoneNumber(requestDto.getAccount());
            userObjectResult = userService.getUser(userGetRequestDto);
            if (userObjectResult.isSuccess() && userObjectResult.getData() == null) {
                userGetRequestDto = new UserGetRequestDto();
                userGetRequestDto.setEmailAddress(requestDto.getAccount());
                userObjectResult = userService.getUser(userGetRequestDto);
            }
        }
        if (userObjectResult.isFailed() || userObjectResult.getData() == null) {
            //账号不存在
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        UserResultDto userResultDto = userObjectResult.getData();
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
            usernamePasswordToken.setUsername(requestDto.getAccount());
            usernamePasswordToken.setPassword(requestDto.getCode().toCharArray());
            usernamePasswordToken.setRememberMe(false);
            subject.login(usernamePasswordToken);

            authenticate(subject, requestDto.getTerminateType(), httpServletRequest, httpServletResponse, userResultDto, objectResultDto, now);

        } catch (UnknownAccountException e) {
            //账号不存在
            objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        } catch (IncorrectCredentialsException e) {
            //密码错误
            objectResultDto.makeResult(UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
            //记录登录失败次数
            Integer errorCount = userResultDto.getAccessAttemptCount() == null ? Integer.valueOf(0) : userResultDto.getAccessAttemptCount();
            UserUpdateRequestDto updateRequestDto = new UserUpdateRequestDto();
            updateRequestDto.setAccessAttemptCount(errorCount + 1);
            updateRequestDto.setLastLoginTime(now);
            userService.updateUser(updateRequestDto);
            return objectResultDto;
        } catch (LockedAccountException e) {
            //账号锁定
            objectResultDto.makeResult(UCenterResultEnum.USER_LOCKED.getValue(), UCenterResultEnum.USER_LOCKED.getComment());
        } catch (AuthenticationException e) {
            //账户验证失败
            //密码错误
            objectResultDto.makeResult(UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
        } catch (Exception e) {
            objectResultDto.makeResult(UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
            log.error("用户登录失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 用户验证码登录
     *
     * @param requestDto          requestDto
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     */
    private ObjectResultDto<UserLoginResultDto> verifyLogin(UserSignInRequestDto requestDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();
        final Date now = new Date();

        if (!StringUtils.isChinaPhoneNumber(requestDto.getAccount())) {
            return objectResultDto.makeResult(UCenterResultEnum.MOBILE_PHONE_NUMBER_INVALID.getValue(), UCenterResultEnum.MOBILE_PHONE_NUMBER_INVALID.getComment());
        }
        if (StringUtils.isEmpty(requestDto.getCode())) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_VERIFY_CODE_EMPTY.getValue(), UCenterResultEnum.USER_VERIFY_CODE_EMPTY.getComment());
        }
        VerifyCodeCheckRequestDto verifyCodeCheckRequestDto = new VerifyCodeCheckRequestDto();
        verifyCodeCheckRequestDto.setClientId(smsConfig.getClientId());
        verifyCodeCheckRequestDto.setVerifyCode(requestDto.getCode());
        verifyCodeCheckRequestDto.setPhoneNumber(requestDto.getAccount());
        if (!StringUtils.isEmpty(requestDto.getSmsRequestId())) {
            verifyCodeCheckRequestDto.setSmsRequestId(requestDto.getSmsRequestId());
        }
        ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode = smsSendService.checkVerifyCode(verifyCodeCheckRequestDto);
        if (checkVerifyCode.isFailed() || null == checkVerifyCode.getData() || !checkVerifyCode.getData().getValid()) {
            return objectResultDto.makeResult(SmsResultCodeEnum.VERIFY_CODE_CHECK_FAIL.getCode(), SmsResultCodeEnum.VERIFY_CODE_CHECK_FAIL.getMessage());
        }
        UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
        userGetRequestDto.setUsername(requestDto.getAccount());
        ObjectResultDto<UserResultDto> userObjectResult = userService.getUser(userGetRequestDto);

        if (userObjectResult.isFailed() || userObjectResult.getData() == null) {
            //注册账号
            UserVerifyCodeLoginRequestDto userVerifyCodeLoginRequestDto = new UserVerifyCodeLoginRequestDto();
            userVerifyCodeLoginRequestDto.setPhoneNumber(requestDto.getAccount());
            userVerifyCodeLoginRequestDto.setTerminateType(requestDto.getTerminateType());
            userVerifyCodeLoginRequestDto.setVerifyCode(requestDto.getCode());
            ResultDto resultDto = securityService.signUpByVerifyCode(userVerifyCodeLoginRequestDto);
            if (resultDto.isFailed()) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_REGISTER_FAIL.getValue(), UCenterResultEnum.USER_REGISTER_FAIL.getComment());
            }
            userGetRequestDto = new UserGetRequestDto();
            userGetRequestDto.setPhoneNumber(requestDto.getAccount());
            userObjectResult = userService.getUser(userGetRequestDto);
        }
        UserResultDto userResultDto = userObjectResult.getData();
        try {
            Subject subject = ShiroUtils.getSubject();
            VerifyCodeToken verifyCodeToken = new VerifyCodeToken();
            verifyCodeToken.setUsername(requestDto.getAccount());
            subject.login(verifyCodeToken);

            authenticate(subject, requestDto.getTerminateType(), httpServletRequest, httpServletResponse, userResultDto, objectResultDto, now);

        } catch (UnknownAccountException e) {
            //账号不存在
            objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        } catch (IncorrectCredentialsException e) {
            //密码错误
            objectResultDto.makeResult(UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
            //记录登录失败次数
            Integer errorCount = userResultDto.getAccessAttemptCount() == null ? Integer.valueOf(0) : userResultDto.getAccessAttemptCount();
            UserUpdateRequestDto updateRequestDto = new UserUpdateRequestDto();
            updateRequestDto.setAccessAttemptCount(errorCount + 1);
            updateRequestDto.setLastLoginTime(now);
            userService.updateUser(updateRequestDto);
            return objectResultDto;
        } catch (LockedAccountException e) {
            //账号锁定
            objectResultDto.makeResult(UCenterResultEnum.USER_LOCKED.getValue(), UCenterResultEnum.USER_LOCKED.getComment());
        } catch (AuthenticationException e) {
            //账户验证失败
            //密码错误
            objectResultDto.makeResult(UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
        } catch (Exception e) {
            objectResultDto.makeResult(UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
            log.error("用户登录失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 用户验证后的各种操作
     *
     * @param subject
     * @param terminateType
     * @param httpServletRequest
     * @param httpServletResponse
     * @param userResultDto
     * @param objectResultDto
     * @param now
     */
    private void authenticate(Subject subject, Integer terminateType, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserResultDto userResultDto, ObjectResultDto objectResultDto, Date now) {
        if (subject.isAuthenticated()) {

            UserUpdateRequestDto updateRequestDto = new UserUpdateRequestDto();
            updateRequestDto.setId(userResultDto.getId());
            updateRequestDto.setAccessAttemptCount(0);
            updateRequestDto.setLastLoginTime(now);
            userService.updateUser(updateRequestDto);
            UserLoginResultDto loginResultDto = new UserLoginResultDto();
            //用户角色
            //用户标签
            UserTagListGetRequestDto userTagListGetRequestDto = new UserTagListGetRequestDto();
            userTagListGetRequestDto.setUserId(userResultDto.getId());
            ListResultDto<UserTagResultDto> userTagResultDtoListResultDto = userService.getUserTagList(userTagListGetRequestDto);
            if (userTagResultDtoListResultDto.isSuccess()) {
                List<UserTagResultDto> userResultDtoList = userTagResultDtoListResultDto.getItems();
                List<String> collect = userResultDtoList.stream().map(UserTagResultDto::getTag).collect(Collectors.toList());
                loginResultDto.setTags(collect);
            }
            //访问令牌
            SessionIdentity sessionIdentity = new SessionIdentity();
            sessionIdentity.setUserType(userResultDto.getUserType());
            sessionIdentity.setUserId(userResultDto.getId());
            sessionIdentity.setUuid(userResultDto.getUuid());
            sessionIdentity.setUsername(userResultDto.getUsername());
            sessionIdentity.setTerminalType(terminateType.toString());
            sessionIdentity.setPhoneNumber(userResultDto.getPhoneNumber());
            sessionIdentity.setEmailAddress(userResultDto.getEmailAddress());
            SessionHandler sessionHandler = new JwtSessionHandler();
            String accessToken = sessionHandler.login(sessionIdentity, httpServletResponse, Constants.DEFAULT_SESSION_TIMEOUT);

            loginResultDto.setUserId(userResultDto.getId());
            loginResultDto.setUsername(userResultDto.getUsername());
            loginResultDto.setEmailAddress(userResultDto.getEmailAddress());
            loginResultDto.setNickname(userResultDto.getNickname());
            loginResultDto.setPhoneNumber(userResultDto.getPhoneNumber());
            loginResultDto.setPortrait(userResultDto.getPortrait());
            loginResultDto.setStatus(userResultDto.getStatus());
            loginResultDto.setUserType(userResultDto.getUserType());
            loginResultDto.setAccessToken(accessToken);
            loginResultDto.setAlias(userResultDto.getAlias());
            loginResultDto.setLastLoginTime(now);

            //登录日志
            String clientId = getClientIdByTerminate(terminateType);
            VisitLogAddRequestDto visitLogAddRequestDto = new VisitLogAddRequestDto();
            visitLogAddRequestDto.setClientId(clientId);
            visitLogAddRequestDto.setUserId(userResultDto.getId());
            visitLogAddRequestDto.setClientIp(ActionUtils.getIpAddress(httpServletRequest));
            visitLogAddRequestDto.setAccessToken(accessToken);
            visitLogService.addVisitLog(visitLogAddRequestDto);

            UserCarCountByIdRequestDto userCarCountByIdRequestDto = new UserCarCountByIdRequestDto();
            userCarCountByIdRequestDto.setUserId(userResultDto.getId());
            ObjectResultDto<UserCarCountResultDto> objectResultDto1 = userCarInfoService.selectUserCarCountById(userCarCountByIdRequestDto);
            if (objectResultDto1.isSuccess() && null != objectResultDto1.getData().getCount()) {
                loginResultDto.setCarCount(objectResultDto1.getData().getCount());
            }
            loginResultDto.setAccessToken(accessToken);
            List<String> roles = new ArrayList<>();
            roles.add("Administrator");
            loginResultDto.setRoles(roles);
            objectResultDto.setData(loginResultDto);
            objectResultDto.success();
        }
    }

    /**
     * 微信用户登录
     */
    private ObjectResultDto<UserLoginResultDto> wxLogin(UserSignInRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {

        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();

        if (StringUtils.isEmpty(requestDto.getAccount())) {
            return objectResultDto.makeResult(UCenterResultEnum.UNIONID_NOT_EXISTS.getValue(), UCenterResultEnum.UNIONID_NOT_EXISTS.getComment());
        }

        final Date now = new Date();
        UserGetByWxUnionIdRequestDto userGetRequestDto = new UserGetByWxUnionIdRequestDto();
        userGetRequestDto.setUnionId(requestDto.getAccount());
        ObjectResultDto<UserResultDto> userObjectResult = userService.getUserByUnionId(userGetRequestDto);
        if (userObjectResult.isFailed() || userObjectResult.getData() == null) {
            //账号不存在
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                    UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        UserResultDto userResultDto = userObjectResult.getData();
        try {
            Subject subject = ShiroUtils.getSubject();
            WeixinUnionIdToken weixinUnionIdToken = new WeixinUnionIdToken();
            weixinUnionIdToken.setUnionId(requestDto.getAccount());
            subject.login(weixinUnionIdToken);

            authenticate(subject, requestDto.getTerminateType(), request, response, userResultDto, objectResultDto, now);

        } catch (UnknownAccountException e) {
            //账号不存在
            objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        } catch (IncorrectCredentialsException e) {
            objectResultDto.makeResult(UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
            return objectResultDto;
        } catch (LockedAccountException e) {
            //账号锁定
            objectResultDto.makeResult(UCenterResultEnum.USER_LOCKED.getValue(), UCenterResultEnum.USER_LOCKED.getComment());
        } catch (AuthenticationException e) {
            //账户验证失败
            objectResultDto.failed();
        } catch (Exception e) {
            log.error("用户登录失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 支付宝用户登录
     */
    private ObjectResultDto<UserLoginResultDto> aliLogin(UserSignInRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {

        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();

        if (StringUtils.isEmpty(requestDto.getAccount())) {
            return objectResultDto.makeResult(UCenterResultEnum.ALIPAYUSERID_EMPTY.getValue(), UCenterResultEnum.ALIPAYUSERID_EMPTY.getComment());
        }

        try {
            final Date now = new Date();
            UserGetByAliUserIdRequestDto userGetByAliUserIdRequestDto = new UserGetByAliUserIdRequestDto();
            userGetByAliUserIdRequestDto.setAliUserId(requestDto.getAccount());
            ObjectResultDto<UserResultDto> userObjectResult = userService.getUserByAliUserId(userGetByAliUserIdRequestDto);
            if (userObjectResult.isFailed() || userObjectResult.getData() == null) {
                //账号不存在
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            UserResultDto userResultDto = userObjectResult.getData();

            Subject subject = ShiroUtils.getSubject();
            AlipayUserIdToken alipayUserIdToken = new AlipayUserIdToken();
            alipayUserIdToken.setAliUserId(requestDto.getAccount());
            subject.login(alipayUserIdToken);

            authenticate(subject, requestDto.getTerminateType(), request, response, userResultDto, objectResultDto, now);

        } catch (UnknownAccountException e) {
            //账号不存在
            objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        } catch (IncorrectCredentialsException e) {
            objectResultDto.makeResult(UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
            return objectResultDto;
        } catch (LockedAccountException e) {
            //账号锁定
            objectResultDto.makeResult(UCenterResultEnum.USER_LOCKED.getValue(), UCenterResultEnum.USER_LOCKED.getComment());
        } catch (AuthenticationException e) {
            //账户验证失败
            objectResultDto.failed();
        } catch (Exception e) {
            log.error("用户登录失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 用户是否已存在(包括判断微信和支付宝是否已经注册过)
     */
    @ApiOperation(value = "用户是否已存在(包括判断微信和支付宝是否已经注册过)")
    @RequestMapping(value = "/accountexist", name = "用户是否已存在(包括判断微信和支付宝是否已经注册过)", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserHasRegisteredResultDto> checkExist(UserHasRegisteredRequestDto requestDto) {
        ObjectResultDto<UserHasRegisteredResultDto> resultDto = new ObjectResultDto<>();
        UserHasRegisteredResultDto userHasRegisteredResultDto = new UserHasRegisteredResultDto();
        resultDto.setData(userHasRegisteredResultDto);
        if (requestDto.getAccountType() == UserAccountTypeEnum.PHONE.getValue()) {
            UserHasExistRequestDto userHasExistRequestDto = new UserHasExistRequestDto();
            userHasExistRequestDto.setPhoneNumber(requestDto.getAccount());
            ObjectResultDto<UserHasExistResultDto> result = userService.checkExist(userHasExistRequestDto);
            resultDto.makeResult(result.getCode(), result.getMessage());
            userHasRegisteredResultDto.setRegistered(result.getData().getHasExist());
        } else if (requestDto.getAccountType() == UserAccountTypeEnum.WX.getValue()) {
            WechatUserHasRegisteredRequestDto wechatUserHasRegisteredRequestDto = new WechatUserHasRegisteredRequestDto();
            wechatUserHasRegisteredRequestDto.setUnionId(requestDto.getAccount());
            ObjectResultDto<WechatUserHasRegisteredResultDto> result = accountService.wechatUserHasRegistered(wechatUserHasRegisteredRequestDto);
            resultDto.makeResult(result.getCode(), result.getMessage());
            userHasRegisteredResultDto.setRegistered(result.getData().getRegistered());
        } else {
            AlipayUserHasRegisteredRequestDto alipayUserHasRegisteredRequestDto = new AlipayUserHasRegisteredRequestDto();
            alipayUserHasRegisteredRequestDto.setAlipayUserId(requestDto.getAccount());
            ObjectResultDto<AlipayUserHasRegisteredResultDto> result = accountService.alipayUserHasRegistered(alipayUserHasRegisteredRequestDto);
            resultDto.makeResult(result.getCode(), result.getMessage());
            userHasRegisteredResultDto.setRegistered(result.getData().getRegistered());
        }

        return resultDto;
    }

    /**
     * 获取clientId
     *
     * @param terminateType terminateType
     */
    private String getClientIdByTerminate(Integer terminateType) {
        String clientId = "";
        if (terminateType.equals(TerminateType.ANDROID.getValue())) {
            clientId = platformConfig.getAndroidClientId();
        } else if (terminateType.equals(TerminateType.APPLE.getValue())) {
            clientId = platformConfig.getAppleClientId();
        } else if (terminateType.equals(TerminateType.H5.getValue())) {
            clientId = platformConfig.getH5ClientId();
        } else if (terminateType.equals(TerminateType.WEB.getValue())) {
            clientId = platformConfig.getWebClientId();
        }
        return clientId;
    }

}
