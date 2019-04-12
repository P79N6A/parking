package com.zhuyitech.parking.platform.controller.security;

import com.scapegoat.infrastructure.api.enums.ResultStatus;
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
import com.zhuyitech.parking.shiro.authc.WeixinOpenIdToken;
import com.zhuyitech.parking.tool.dto.request.device.InvalidPushDeviceRequestDto;
import com.zhuyitech.parking.tool.service.api.PushDeviceService;
import com.zhuyitech.parking.ucc.account.AccountService;
import com.zhuyitech.parking.ucc.account.SnsService;
import com.zhuyitech.parking.ucc.account.request.*;
import com.zhuyitech.parking.ucc.account.result.AlipayAuthCodeResultDto;
import com.zhuyitech.parking.ucc.account.result.WeixinGetAccessTokenRequestDto;
import com.zhuyitech.parking.ucc.account.result.WeixinGetUserInfoRequestDto;
import com.zhuyitech.parking.ucc.dto.request.CurrentLoginUserRequestDto;
import com.zhuyitech.parking.ucc.dto.request.sms.VerifyCodeSendRequestDto;
import com.zhuyitech.parking.ucc.dto.request.visitlog.VisitLogAddRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserTagResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.*;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.service.api.SecurityService;
import com.zhuyitech.parking.ucc.service.api.UserService;
import com.zhuyitech.parking.ucc.service.api.VisitLogService;
import com.zhuyitech.parking.ucc.user.dto.*;
import com.zhuyitech.sms.dto.request.MessageSendRequestDto;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckRequestDto;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckResultDto;
import com.zhuyitech.sms.enums.SmsResultCodeEnum;
import com.zhuyitech.sms.enums.VerifyTypeEnum;
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
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private VisitLogService visitLogService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SnsService snsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SmsSendService smsSendService;

    @Autowired
    private PushDeviceService pushDeviceService;

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private PlatformConfig platformConfig;

    /**
     * 登录用户
     */
    @ApiOperation(value = "用户登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserLoginResultDto.class)
    @RequestMapping(value = "/signIn", name = "用户登录", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserLoginResultDto> signIn(UserLoginRequestDto requestDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return login(requestDto, httpServletRequest, httpServletResponse);
    }

    /**
     * 用户登录
     *
     * @param requestDto          requestDto
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     */
    private ObjectResultDto<UserLoginResultDto> login(UserLoginRequestDto requestDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();
        final Date now = new Date();
        UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
        userGetRequestDto.setUsername(requestDto.getUsername());
        ObjectResultDto<UserResultDto> userObjectResult = userService.getUser(userGetRequestDto);
        if (userObjectResult.isSuccess() && userObjectResult.getData() == null) {
            userGetRequestDto = new UserGetRequestDto();
            userGetRequestDto.setPhoneNumber(requestDto.getUsername());
            userObjectResult = userService.getUser(userGetRequestDto);
            if (userObjectResult.isSuccess() && userObjectResult.getData() == null) {
                userGetRequestDto = new UserGetRequestDto();
                userGetRequestDto.setEmailAddress(requestDto.getUsername());
                userObjectResult = userService.getUser(userGetRequestDto);
            }
        }
        if (userObjectResult.isFailed() || userObjectResult.getData() == null) {
            //账号不存在
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        UserResultDto userResultDto = userObjectResult.getData();
        try {
            String clientId = getClientIdByTerminate(requestDto.getTerminateType());
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
            usernamePasswordToken.setUsername(requestDto.getUsername());
            usernamePasswordToken.setPassword(requestDto.getPassword().toCharArray());
            usernamePasswordToken.setRememberMe(false);
            subject.login(usernamePasswordToken);
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
                sessionIdentity.setTerminalType(requestDto.getTerminateType().toString());
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
                objectResultDto.setData(loginResultDto);

                //登录日志
                VisitLogAddRequestDto visitLogAddRequestDto = new VisitLogAddRequestDto();
                visitLogAddRequestDto.setClientId(clientId);
                visitLogAddRequestDto.setUserId(userResultDto.getId());
                visitLogAddRequestDto.setClientIp(ActionUtils.getIpAddress(httpServletRequest));
                visitLogAddRequestDto.setAccessToken(accessToken);
                visitLogService.addVisitLog(visitLogAddRequestDto);
                objectResultDto.success();
            }

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
     * 微信用户登录
     */
    private ObjectResultDto<UserLoginResultDto> wxLogin(UserWechatSignInRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {

        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();
        final Date now = new Date();
        UserGetByWxOpenIdRequestDto userGetRequestDto = new UserGetByWxOpenIdRequestDto();
        userGetRequestDto.setOpenId(requestDto.getOpenId());
        ObjectResultDto<UserResultDto> userObjectResult = userService.getUserByOpenId(userGetRequestDto);
        if (userObjectResult.isFailed() || userObjectResult.getData() == null) {
            //账号不存在
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                    UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        UserResultDto userResultDto = userObjectResult.getData();
        try {
            String clientId = getClientIdByTerminate(requestDto.getTerminateType());

            Subject subject = ShiroUtils.getSubject();
            WeixinOpenIdToken weixinOpenIdToken = new WeixinOpenIdToken();
            weixinOpenIdToken.setOpenId(requestDto.getOpenId());
            weixinOpenIdToken.setRememberMe(false);
            subject.login(weixinOpenIdToken);

            if (subject.isAuthenticated()) {

                UserUpdateRequestDto updateRequestDto = new UserUpdateRequestDto();
                updateRequestDto.setId(userResultDto.getId());
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
                sessionIdentity.setTerminalType(requestDto.getTerminateType().toString());
                sessionIdentity.setPhoneNumber(userResultDto.getPhoneNumber());
                sessionIdentity.setEmailAddress(userResultDto.getEmailAddress());
                SessionHandler sessionHandler = new JwtSessionHandler();
                String accessToken = sessionHandler.login(sessionIdentity, response, Constants.DEFAULT_SESSION_TIMEOUT);

                loginResultDto.setUserId(userResultDto.getId());
                loginResultDto.setUsername(userResultDto.getUsername());
                loginResultDto.setEmailAddress(userResultDto.getEmailAddress());
                loginResultDto.setNickname(userResultDto.getNickname());
                loginResultDto.setPhoneNumber(userResultDto.getPhoneNumber());
                loginResultDto.setPortrait(userResultDto.getPortrait());
                loginResultDto.setStatus(userResultDto.getStatus());
                loginResultDto.setUserType(userResultDto.getUserType());
                loginResultDto.setAccessToken(accessToken);
                loginResultDto.setLastLoginTime(now);
                loginResultDto.setAlias(userResultDto.getAlias());
                objectResultDto.setData(loginResultDto);

                //登录日志
                VisitLogAddRequestDto visitLogAddRequestDto = new VisitLogAddRequestDto();
                visitLogAddRequestDto.setClientId(clientId);
                visitLogAddRequestDto.setUserId(userResultDto.getId());
                visitLogService.addVisitLog(visitLogAddRequestDto);
                objectResultDto.success();
            }

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
     * 用户是否已存在
     */
    @ApiOperation(value = "用户是否已存在")
    @RequestMapping(value = "/exist", name = "用户是否已存在", method = RequestMethod.POST)
    public ObjectResultDto<UserHasExistResultDto> checkExist(UserHasExistRequestDto requestDto) {
        return userService.checkExist(requestDto);
    }

    /**
     * 用户注册
     */
    @ApiOperation(value = "用户注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserLoginResultDto.class)
    @RequestMapping(value = "/signUp", name = "用户注册", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserLoginResultDto> signUp(UserPhoneRegisterRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();
        VerifyCodeCheckRequestDto checkRequestDto = new VerifyCodeCheckRequestDto();
        checkRequestDto.setClientId(smsConfig.getClientId());
        if (!StringUtils.isEmpty(requestDto.getSmsRequestId())) {
            checkRequestDto.setSmsRequestId(requestDto.getSmsRequestId());
        }
        checkRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
        checkRequestDto.setVerifyCode(requestDto.getVerifyCode());
        ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode = smsSendService.checkVerifyCode(checkRequestDto);
        if (checkVerifyCode.isFailed() || null == checkVerifyCode.getData() || !checkVerifyCode.getData().getValid()) {
            return objectResultDto.makeResult(checkVerifyCode.getCode(), checkVerifyCode.getMessage());
        }
        String regx = "^[a-zA-Z0-9\\W]{6,20}$";
        if (!requestDto.getPassword().matches(regx) || !requestDto.getConfirmedPassword().matches(regx)) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getValue(),
                    UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getComment());
        }
        if (!requestDto.getConfirmedPassword().equals(requestDto.getPassword())) {
            return objectResultDto.makeResult(UCenterResultEnum.PASSWORD_DISMATCH_ERROR.getValue(),
                    UCenterResultEnum.PASSWORD_DISMATCH_ERROR.getComment()
            );
        }
        ResultDto registerResultDto = securityService.signUp(requestDto);
        if (registerResultDto.isFailed()) {
            return objectResultDto.makeResult(registerResultDto);
        } else {
            //注册成功后直接登录
            UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
            userLoginRequestDto.setUsername(requestDto.getPhoneNumber());
            userLoginRequestDto.setPassword(requestDto.getPassword());
            userLoginRequestDto.setTerminateType(requestDto.getTerminateType());
            return login(userLoginRequestDto, request, response);
        }
    }

    /**
     * 登录用户信息
     *
     * @param requestDto requestDto
     */
    @ApiOperation(value = "登录用户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CurrentUserResultDto.class)
    @RequestMapping(value = "/currentUser", name = "登录用户信息", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<CurrentUserResultDto> currentUser(HttpServletRequest request, CurrentLoginUserRequestDto requestDto) {

        ObjectResultDto<CurrentUserResultDto> objectResultDto = new ObjectResultDto<>();

        if (requestDto.getSessionIdentity() == null) {
            return objectResultDto.makeResult(ResultStatus.USER_NOT_LOGIN.getCode(),
                    ResultStatus.USER_NOT_LOGIN.getMessage());
        } else {
            UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
            userGetRequestDto.setId(requestDto.getSessionIdentity().getUserId());
            ObjectResultDto<UserResultDto> userObjectResultDto = userService.getUser(userGetRequestDto);
            if (userObjectResultDto.isFailed() || userObjectResultDto.getData() == null) {
                return
                        objectResultDto.makeResult(ResultStatus.USER_NOT_FOUND.getCode(), ResultStatus.USER_NOT_FOUND.getMessage());
            }
            UserResultDto userResultDto = userObjectResultDto.getData();
            CurrentUserResultDto currentUserResultDto = new CurrentUserResultDto();
            currentUserResultDto.setUserId(userResultDto.getId());
            currentUserResultDto.setUuid(userResultDto.getUuid());
            currentUserResultDto.setPhoneNumber(userResultDto.getPhoneNumber());
            currentUserResultDto.setEmailAddress(userResultDto.getEmailAddress());
            currentUserResultDto.setUsername(userResultDto.getUsername());
            currentUserResultDto.setLastLoginTime(userResultDto.getLastLoginTime());
            currentUserResultDto.setUserType(userResultDto.getUserType());
            currentUserResultDto.setStatus(userResultDto.getStatus());
            currentUserResultDto.setPortrait(userResultDto.getPortrait());
            List<String> roles = new ArrayList<>();
            roles.add("Administrator");
            currentUserResultDto.setRoles(roles);
            //用户角色
            //用户标签
//            UserTagListGetRequestDto userTagListGetRequestDto = new UserTagListGetRequestDto();
//            userTagListGetRequestDto.setUserId(userResultDto.getId());
//            ListResultDto<UserTagResultDto> userTagResultDtoListResultDto = userService.getUserTagList(userTagListGetRequestDto);
//            if (userTagResultDtoListResultDto.isSuccess()) {
//                List<UserTagResultDto> userResultDtoList = userTagResultDtoListResultDto.getItems();
//                List<String> collect = userResultDtoList.stream().map(UserTagResultDto::getTag).collect(Collectors.toList());
//                currentUserResultDto.setTags(collect);
//            }
            objectResultDto.setData(currentUserResultDto);
        }
        return objectResultDto.success();
    }

    /**
     * 用户是否已登录
     *
     * @param request request
     */
    @ApiOperation(value = "用户是否已登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/isLogin", name = "用户登出", method = RequestMethod.POST)
    public ObjectResultDto<UserLoginStatusResultDto> isLogin(HttpServletRequest request) {
        ObjectResultDto<UserLoginStatusResultDto> objectResultDto = new ObjectResultDto<>();
        UserLoginStatusResultDto resultDto = new UserLoginStatusResultDto();
        try {
            SessionHandler sessionHandler = new JwtSessionHandler();
            if (ShiroUtils.isLogin()) {
                resultDto.setLogined(Boolean.TRUE);
            } else {
                resultDto.setLogined(Boolean.FALSE);
            }
            objectResultDto.setData(resultDto);
            objectResultDto.success();
        } catch (Exception e) {
        }
        return objectResultDto;
    }

    /**
     * 用户登出
     *
     * @param requestDto requestDto
     */
    @ApiOperation(value = "用户登出", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/signOut", name = "用户登出", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto signOut(UserLogoutRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        ResultDto resultDto = new ResultDto();
        try {

            //清除AccessToken
            SessionHandler sessionHandler = new JwtSessionHandler();
            sessionHandler.logout(request, response);
            resultDto.success();
        } catch (Exception e) {
        }
        return resultDto;
    }

    /**
     * 密码找回之修改密码
     */
    @ApiOperation(value = "密码找回之修改密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/retrievePassword", name = "修改密码", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto retrievePassword(UserRetrievePasswordRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        if (!StringUtils.isEmpty(requestDto.getVerifyCode()) && !StringUtils.isEmpty(requestDto.getSmsRequestId())) {
            VerifyCodeCheckRequestDto checkRequestDto = new VerifyCodeCheckRequestDto();
            checkRequestDto.setClientId(smsConfig.getClientId());
            checkRequestDto.setSmsRequestId(requestDto.getSmsRequestId());
            checkRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
            checkRequestDto.setVerifyCode(requestDto.getVerifyCode());
            ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode = smsSendService.checkVerifyCode(checkRequestDto);
            if (checkVerifyCode.isFailed() || null == checkVerifyCode.getData() || !checkVerifyCode.getData().getValid()) {
                return resultDto.makeResult(checkVerifyCode.getCode(), checkVerifyCode.getMessage());
            }
        }
        resultDto = securityService.retrievePassword(requestDto);
        return resultDto;
    }

    /**
     * 用户是否注册微信
     */
    @ApiOperation(value = "用户是否注册微信", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WechatUserHasRegisteredResultDto.class)
    @RequestMapping(value = "/isRegisterWechat", name = "用户是否注册微信", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<WechatUserHasRegisteredResultDto> isRegisterWechat(WechatUserHasRegisteredRequestDto requestDto) {
        return accountService.wechatUserHasRegistered(requestDto);
    }

    /**
     * 获取微信账号信息
     */
    @ApiOperation(value = "获取微信账号信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WeixinGetUserInfoResultDto.class)
    @RequestMapping(value = "/getWechatInfo", name = "获取微信账号信息", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<WeixinGetUserInfoResultDto> getWechatInfo(WeixinGetAccessTokenRequestDto requestDto) {
        ObjectResultDto<WeixinGetUserInfoResultDto> objectResultDto = new ObjectResultDto<>();
        requestDto.setGoway(Boolean.TRUE);
        ObjectResultDto<WeixinGetAccessTokenResultDto> resultDto = snsService.getAccessToken(requestDto);
        if (resultDto.isFailed()) {
            return objectResultDto.makeResult(resultDto.getCode(), resultDto.getMessage());
        }
        WeixinGetUserInfoRequestDto requestDto1 = new WeixinGetUserInfoRequestDto();
        requestDto1.setAccessToken(resultDto.getData().getAccessToken());
        requestDto1.setOpenId(resultDto.getData().getOpenId());
        requestDto1.setRefreshToken(resultDto.getData().getRefreshToken());
        return snsService.getWechatInfo(requestDto1);
    }

    /**
     * 微信账号注册
     */
    @ApiOperation(value = "微信账号注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WechatRegisterSuccessStatusResultDto.class)
    @RequestMapping(value = "/wechatRegister", name = "微信账号注册", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<WechatRegisterSuccessStatusResultDto> wechatRegister(UserWechatSignUpRequestDto requestDto) {
        ObjectResultDto<WechatRegisterSuccessStatusResultDto> objectResultDto = new ObjectResultDto<>();
        if (!StringUtils.isEmpty(requestDto.getVerifyCode()) && !StringUtils.isEmpty(requestDto.getSmsRequestId())) {
            VerifyCodeCheckRequestDto checkRequestDto = new VerifyCodeCheckRequestDto();
            checkRequestDto.setClientId(smsConfig.getClientId());
            checkRequestDto.setSmsRequestId(requestDto.getSmsRequestId());
            checkRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
            checkRequestDto.setVerifyCode(requestDto.getVerifyCode());
            ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode = smsSendService.checkVerifyCode(checkRequestDto);
            if (checkVerifyCode.isFailed() || null == checkVerifyCode.getData() || !checkVerifyCode.getData().getValid()) {
                return objectResultDto.makeResult(checkVerifyCode.getCode(), checkVerifyCode.getMessage());
            }
        }
        return accountService.wechatRegister(requestDto);
    }

    /**
     * 用户微信登录
     */
    @ApiOperation(value = "用户微信登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserLoginResultDto.class)
    @RequestMapping(value = "/wxSignIn", name = "用户登录", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserLoginResultDto> wxSignIn(UserWechatSignInRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        return wxLogin(requestDto, request, response);
    }

    /**
     * 用户是否注册支付宝
     */
    @ApiOperation(value = "用户是否注册支付宝", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AlipayUserHasRegisteredResultDto.class)
    @RequestMapping(value = "/isRegisterAlipay", name = "用户是否注册支付宝", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<AlipayUserHasRegisteredResultDto> isRegisterWechat(AlipayUserHasRegisteredRequestDto requestDto) {
        return accountService.alipayUserHasRegistered(requestDto);
    }

    /**
     * 获取支付宝账号信息
     */
    @ApiOperation(value = "获取支付宝账号信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AlipayGetUserInfoResultDto.class)
    @RequestMapping(value = "/getAlipayInfo", name = "获取支付宝账号信息", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<AlipayGetUserInfoResultDto> getAlipayInfo(AlipayGetAccessTokenRequestDto requestDto) {
        ObjectResultDto<AlipayGetUserInfoResultDto> objectResultDto = new ObjectResultDto<>();
        ObjectResultDto<AlipayGetAccessTokenResultDto> resultDto = snsService.getAccessToken(requestDto);
        if (resultDto.isFailed()) {
            return objectResultDto.makeResult(resultDto.getCode(), resultDto.getMessage());
        }
        AlipayGetUserInfoRequestDto requestDto1 = new AlipayGetUserInfoRequestDto();
        requestDto1.setAccessToken(resultDto.getData().getAccessToken());
        requestDto1.setRefreshToken(resultDto.getData().getRefreshToken());
        return snsService.getAlipayInfo(requestDto1);
    }

    /**
     * 支付宝账号注册
     */
    @ApiOperation(value = "支付宝账号注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AlipayRegisterSuccessStatusResultDto.class)
    @RequestMapping(value = "/alipayRegister", name = "支付宝账号注册", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<AlipayRegisterSuccessStatusResultDto> alipayRegister(UserAlipayRegisterRequestDto requestDto) {
        ObjectResultDto<AlipayRegisterSuccessStatusResultDto> objectResultDto = new ObjectResultDto<>();
        if (!StringUtils.isEmpty(requestDto.getVerifyCode()) && !StringUtils.isEmpty(requestDto.getSmsRequestId())) {
            VerifyCodeCheckRequestDto checkRequestDto = new VerifyCodeCheckRequestDto();
            checkRequestDto.setClientId(smsConfig.getClientId());
            checkRequestDto.setSmsRequestId(requestDto.getSmsRequestId());
            checkRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
            checkRequestDto.setVerifyCode(requestDto.getVerifyCode());
            ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode = smsSendService.checkVerifyCode(checkRequestDto);
            if (checkVerifyCode.isFailed() || null == checkVerifyCode.getData() || !checkVerifyCode.getData().getValid()) {
                return objectResultDto.makeResult(checkVerifyCode.getCode(), checkVerifyCode.getMessage());
            }
        }
        objectResultDto = accountService.alipayRegister(requestDto);
        return objectResultDto;
    }

    /**
     * 支付宝用户登录
     */
    private ObjectResultDto<UserLoginResultDto> aliLogin(UserAlipaySignInRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {

        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();
        final Date now = new Date();
        UserGetByAliUserIdRequestDto userGetByAliUserIdRequestDto = new UserGetByAliUserIdRequestDto();
        userGetByAliUserIdRequestDto.setAliUserId(requestDto.getAliUserId());
        ObjectResultDto<UserResultDto> userObjectResult = userService.getUserByAliUserId(userGetByAliUserIdRequestDto);
        if (userObjectResult.isFailed() || userObjectResult.getData() == null) {
            //账号不存在
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                    UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        UserResultDto userResultDto = userObjectResult.getData();
        try {
            String clientId = getClientIdByTerminate(requestDto.getTerminateType());

            Subject subject = ShiroUtils.getSubject();
            AlipayUserIdToken alipayUserIdToken = new AlipayUserIdToken();
            alipayUserIdToken.setAliUserId(requestDto.getAliUserId());
            subject.login(alipayUserIdToken);

            if (subject.isAuthenticated()) {

                UserUpdateRequestDto updateRequestDto = new UserUpdateRequestDto();
                updateRequestDto.setId(userResultDto.getId());
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
                sessionIdentity.setTerminalType(requestDto.getTerminateType().toString());
                sessionIdentity.setPhoneNumber(userResultDto.getPhoneNumber());
                sessionIdentity.setEmailAddress(userResultDto.getEmailAddress());
                SessionHandler sessionHandler = new JwtSessionHandler();
                String accessToken = sessionHandler.login(sessionIdentity, response, Constants.DEFAULT_SESSION_TIMEOUT);

                loginResultDto.setUserId(userResultDto.getId());
                loginResultDto.setUsername(userResultDto.getUsername());
                loginResultDto.setEmailAddress(userResultDto.getEmailAddress());
                loginResultDto.setNickname(userResultDto.getNickname());
                loginResultDto.setLastLoginTime(now);
                loginResultDto.setPhoneNumber(userResultDto.getPhoneNumber());
                loginResultDto.setPortrait(userResultDto.getPortrait());
                loginResultDto.setStatus(userResultDto.getStatus());
                loginResultDto.setUserType(userResultDto.getUserType());
                loginResultDto.setAccessToken(accessToken);
                objectResultDto.setData(loginResultDto);

                //登录日志
                VisitLogAddRequestDto visitLogAddRequestDto = new VisitLogAddRequestDto();
                visitLogAddRequestDto.setClientId(clientId);
                visitLogAddRequestDto.setUserId(userResultDto.getId());
                visitLogService.addVisitLog(visitLogAddRequestDto);

                objectResultDto.success();
            }
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
     * 用户支付宝登录
     */
    @ApiOperation(value = "用户支付宝登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserLoginResultDto.class)
    @RequestMapping(value = "/aliSignIn", name = "用户登录", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserLoginResultDto> aliSignIn(UserAlipaySignInRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        return aliLogin(requestDto, request, response);
    }

    /**
     * 授权请求参数
     */
    @ApiOperation(value = "授权请求参数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserLoginResultDto.class)
    @RequestMapping(value = "/authSdkCodeSet", name = "用户登录", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<AlipayAuthCodeResultDto> authSdkCodeSet(AlipayAuthCodeGetRequestDto requestDto) {
        return snsService.authSdkCodeGet(requestDto);
    }

    /**
     * 用户验证码登录
     */
    @ApiOperation(value = "用户验证码登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserLoginResultDto.class)
    @RequestMapping(value = "/verifyCodeSignIn", name = "用户验证码登录", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserLoginResultDto> verifyCodeSignIn(UserVerifyCodeLoginRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        return verifySignIn(requestDto, request, response);
    }

    /**
     * 用户验证码登录
     *
     * @param requestDto          requestDto
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     */
    private ObjectResultDto<UserLoginResultDto> verifySignIn(UserVerifyCodeLoginRequestDto requestDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();
        final Date now = new Date();

        if (!StringUtils.isChinaPhoneNumber(requestDto.getPhoneNumber())) {
            return objectResultDto.makeResult(UCenterResultEnum.MOBILE_PHONE_NUMBER_INVALID.getValue(), UCenterResultEnum.MOBILE_PHONE_NUMBER_INVALID.getComment());
        }
        if (StringUtils.isEmpty(requestDto.getVerifyCode())) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_VERIFY_CODE_EMPTY.getValue(), UCenterResultEnum.USER_VERIFY_CODE_EMPTY.getComment());
        }
        VerifyCodeCheckRequestDto verifyCodeCheckRequestDto = new VerifyCodeCheckRequestDto();
        verifyCodeCheckRequestDto.setClientId(smsConfig.getClientId());
        verifyCodeCheckRequestDto.setVerifyCode(requestDto.getVerifyCode());
        verifyCodeCheckRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
        ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode = smsSendService.checkVerifyCode(verifyCodeCheckRequestDto);
        if (checkVerifyCode.isFailed() || null == checkVerifyCode.getData() || !checkVerifyCode.getData().getValid()) {
            return objectResultDto.makeResult(SmsResultCodeEnum.VERIFY_CODE_CHECK_FAIL.getCode(), SmsResultCodeEnum.VERIFY_CODE_CHECK_FAIL.getMessage());
        }
        UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
        userGetRequestDto.setUsername(requestDto.getPhoneNumber());
        ObjectResultDto<UserResultDto> userObjectResult = userService.getUser(userGetRequestDto);
        if (userObjectResult.isSuccess() && userObjectResult.getData() == null) {
            userGetRequestDto = new UserGetRequestDto();
            userGetRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
            userObjectResult = userService.getUser(userGetRequestDto);
            if (userObjectResult.isSuccess() && userObjectResult.getData() == null) {
                userGetRequestDto = new UserGetRequestDto();
                userGetRequestDto.setEmailAddress(requestDto.getPhoneNumber());
                userObjectResult = userService.getUser(userGetRequestDto);
            }
        }
        if (userObjectResult.isFailed() || userObjectResult.getData() == null) {
            //注册账号
            ResultDto resultDto = securityService.signUpByVerifyCode(requestDto);
            if (resultDto.isFailed()) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_REGISTER_FAIL.getValue(), UCenterResultEnum.USER_REGISTER_FAIL.getComment());
            }
            userGetRequestDto = new UserGetRequestDto();
            userGetRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
            userObjectResult = userService.getUser(userGetRequestDto);
        }
        UserResultDto userResultDto = userObjectResult.getData();
        try {
            String clientId = getClientIdByTerminate(requestDto.getTerminateType());
            Subject subject = ShiroUtils.getSubject();

            VerifyCodeToken verifyCodeToken = new VerifyCodeToken();
            verifyCodeToken.setUsername(requestDto.getPhoneNumber());
            subject.login(verifyCodeToken);

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
                sessionIdentity.setTerminalType(requestDto.getTerminateType().toString());
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
                objectResultDto.setData(loginResultDto);

                //登录日志
                VisitLogAddRequestDto visitLogAddRequestDto = new VisitLogAddRequestDto();
                visitLogAddRequestDto.setClientId(clientId);
                visitLogAddRequestDto.setUserId(userResultDto.getId());
                visitLogAddRequestDto.setClientIp(ActionUtils.getIpAddress(httpServletRequest));
                visitLogAddRequestDto.setAccessToken(accessToken);
                visitLogService.addVisitLog(visitLogAddRequestDto);
                objectResultDto.success();
            }

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

    /**
     * 用户登出
     *
     * @param requestDto requestDto
     */
    @ApiOperation(value = "用户登出", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/logOut", name = "用户登出", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto logOut(UserLogoutIntegrationRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        ResultDto resultDto = new ResultDto();
        try {

            //清除AccessToken
            SessionHandler sessionHandler = new JwtSessionHandler();
            sessionHandler.logout(request, response);
            //修改用户设备推送状态
            InvalidPushDeviceRequestDto invalidPushDeviceRequestDto = new InvalidPushDeviceRequestDto();
            invalidPushDeviceRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            invalidPushDeviceRequestDto.setRegistrationId(requestDto.getRegistrationId());
            pushDeviceService.invalidDevice(invalidPushDeviceRequestDto);
            resultDto.success();
        } catch (Exception e) {
        }
        return resultDto;
    }

    /**
     * 公众号判断用户是否已存（手机号已存在未绑定微信为FALSE）并发送短信
     */
    @ApiOperation(value = "公众号判断用户是否已存在并发送短信", httpMethod = "POST", response = ResultDto.class)
    @RequestMapping(value = "/jsapiSendCode", name = "公众号判断用户是否已存在并发送短信", method = RequestMethod.POST)
    public ResultDto jsapiCheckExist(VerifyCodeSendRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        UserHasExistRequestDto userHasExistRequestDto = new UserHasExistRequestDto();
        userHasExistRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
        ObjectResultDto<UserHasExistResultDto> objectResultDto = userService.checkUserExist(userHasExistRequestDto);
        if (objectResultDto.isFailed() || null == objectResultDto.getData()) {
            return resultDto.failed();
        }
        if (objectResultDto.getData().getHasExist()) {
            return resultDto.makeResult(UCenterResultEnum.USER_PHONE_EXISTS.getValue(), UCenterResultEnum.USER_PHONE_EXISTS.getComment());
        } else {
            if (StringUtils.isEmpty(smsConfig.getClientId())) {
                return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                        SmsResultCodeEnum.SMS_SEND_FAIL.getMessage()
                );
            }
            MessageSendRequestDto messageSendRequestDto = new MessageSendRequestDto();
            if (requestDto.getVerifyType().equals(VerifyTypeEnum.REGISTER.getValue())) {
                messageSendRequestDto.setTemplateId(smsConfig.getRegisterTemplateId());
            } else if (requestDto.getVerifyType().equals(VerifyTypeEnum.LOGIN.getValue())) {
                messageSendRequestDto.setTemplateId(smsConfig.getLoginTemplateId());
            } else if (requestDto.getVerifyType().equals(VerifyTypeEnum.FORGETPASSWORD.getValue())) {
                messageSendRequestDto.setTemplateId(smsConfig.getForgetTemplateId());
            } else if (requestDto.getVerifyType().equals(VerifyTypeEnum.SETPASSWORD.getValue())) {
                messageSendRequestDto.setTemplateId(smsConfig.getSetTemplateId());
            } else if (requestDto.getVerifyType().equals(VerifyTypeEnum.PAYPASSWORD.getValue())) {
                messageSendRequestDto.setTemplateId(smsConfig.getPayTemplateId());
            } else {
                messageSendRequestDto.setTemplateId(smsConfig.getTemplateId());
            }
            if (StringUtils.isEmpty(messageSendRequestDto.getTemplateId())) {
                return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                        SmsResultCodeEnum.SMS_SEND_FAIL.getMessage()
                );
            }
            messageSendRequestDto.setClientId(smsConfig.getClientId());
            messageSendRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
            messageSendRequestDto.setVerifyType(requestDto.getVerifyType());
            resultDto = smsSendService.sendVerifySms(messageSendRequestDto);
        }
        return resultDto;
    }

    /**
     * 公众号微信账号注册
     */
    @ApiOperation(value = "公众号微信账号注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WechatRegisterSuccessStatusResultDto.class)
    @RequestMapping(value = "/jsapiRegister", name = "公众号微信账号注册", method = RequestMethod.POST)
    public ObjectResultDto<WechatRegisterSuccessStatusResultDto> jsapiRegister(UserWechatSignUpRequestDto requestDto) {
        ObjectResultDto<WechatRegisterSuccessStatusResultDto> objectResultDto = new ObjectResultDto<>();
        if (!StringUtils.isEmpty(requestDto.getVerifyCode()) && !StringUtils.isEmpty(requestDto.getSmsRequestId())) {
            VerifyCodeCheckRequestDto checkRequestDto = new VerifyCodeCheckRequestDto();
            checkRequestDto.setClientId(smsConfig.getClientId());
            checkRequestDto.setSmsRequestId(requestDto.getSmsRequestId());
            checkRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
            checkRequestDto.setVerifyCode(requestDto.getVerifyCode());
            ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode = smsSendService.checkVerifyCode(checkRequestDto);
            if (checkVerifyCode.isFailed() || null == checkVerifyCode.getData() || !checkVerifyCode.getData().getValid()) {
                return objectResultDto.makeResult(checkVerifyCode.getCode(), checkVerifyCode.getMessage());
            }
        }
        return accountService.jsapiRegister(requestDto);
    }

    /**
     * 公众号微信账号注册
     */
    @ApiOperation(value = "公众号微信账号注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WechatRegisterSuccessStatusResultDto.class)
    @RequestMapping(value = "/jsapiRegisterByScanQR", name = "公众号微信账号注册", method = RequestMethod.POST)
    public ObjectResultDto<WechatRegisterSuccessStatusResultDto> jsapiRegisterByScanQR(UserWechatSignUpRequestDto requestDto) {
        return accountService.jsapiRegisterByScanQRCode(requestDto);
    }

    /**
     * 公众号获取微信账号信息
     */
    @ApiOperation(value = "公众号获取微信账号信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WeixinGetUserInfoResultDto.class)
    @RequestMapping(value = "/getWechatInfoJsapi", name = "公众号获取微信账号信息", method = RequestMethod.POST)
    public ObjectResultDto<WeixinGetUserInfoResultDto> getWechatInfoJsapi(WeixinGetAccessTokenRequestDto requestDto) {
        ObjectResultDto<WeixinGetUserInfoResultDto> objectResultDto = new ObjectResultDto<>();
        requestDto.setGoway(Boolean.FALSE);
        ObjectResultDto<WeixinGetAccessTokenResultDto> resultDto = snsService.getAccessToken(requestDto);
        if (resultDto.isFailed()) {
            return objectResultDto.makeResult(resultDto.getCode(), resultDto.getMessage());
        }
        WeixinGetUserInfoRequestDto requestDto1 = new WeixinGetUserInfoRequestDto();
        requestDto1.setAccessToken(resultDto.getData().getAccessToken());
        requestDto1.setOpenId(resultDto.getData().getOpenId());
        requestDto1.setRefreshToken(resultDto.getData().getRefreshToken());
        return snsService.getWechatInfo(requestDto1);
    }

    /**
     * 用户是否已存在(用于微信注册判断手机号，手机号存在微信为绑定也为FALSE)
     */
    @ApiOperation(value = "用户是否已存在(用于微信注册判断手机号，手机号存在微信为绑定也为FALSE)")
    @RequestMapping(value = "/checkUserExist", name = "用户是否已存在(用于微信注册判断手机号，手机号存在微信为绑定也为FALSE)", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserHasExistResultDto> checkUserExist(UserHasExistRequestDto requestDto) {
        return userService.checkUserExist(requestDto);
    }

}
