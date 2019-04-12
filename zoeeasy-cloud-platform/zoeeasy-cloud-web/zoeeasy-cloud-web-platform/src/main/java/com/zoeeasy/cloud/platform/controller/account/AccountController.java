package com.zoeeasy.cloud.platform.controller.account;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.scapegoat.infrastructure.session.JwtSessionHandler;
import com.scapegoat.infrastructure.session.SessionHandler;
import com.scapegoat.infrastructure.shiro.util.ShiroUtils;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.scapegoat.infrastructure.web.utils.ActionUtils;
import com.zoeeasy.cloud.common.web.utils.ServletUtils;
import com.zoeeasy.cloud.sys.visit.VisitLogService;
import com.zoeeasy.cloud.sys.visit.dto.request.VisitLogAddRequestDto;
import com.zoeeasy.cloud.ucc.account.AccountService;
import com.zoeeasy.cloud.ucc.account.dto.request.AccountSignInRequestDto;
import com.zoeeasy.cloud.ucc.account.dto.request.AccountSignOutRequestDto;
import com.zoeeasy.cloud.ucc.enums.UccResultEnum;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/cloud/account")
@Slf4j
@Api(tags = "登录登出", value = "登录Api", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiSort(1)
@ApiVersion(1)
@ApiSigned(value = false)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private VisitLogService visitLogService;

    /**
     * 登录
     *
     * @param request  request
     * @param response response
     * @return
     */
    @ApiOperation(value = "登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/login", name = "登录")
    @SuppressWarnings("rawtypes")
    public ResultDto login(@RequestBody AccountSignInRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        ResultDto resultDto = new ResultDto();
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
            usernamePasswordToken.setUsername(requestDto.getAccount());
            usernamePasswordToken.setPassword(requestDto.getPassword().toCharArray());
            usernamePasswordToken.setRememberMe(requestDto.getRememberMe());
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()) {
                JwtSessionHandler<SessionIdentity> sessionHandler = new JwtSessionHandler<>();
                String accessToken = ((SessionHandler) sessionHandler).login(subject.getPrincipal(), response);
                response.setHeader("Authorization", "Bearer  " + accessToken);
                response.setHeader("Access-Control-Expose-Headers", "Authorization");
                response.setHeader("Cache-Control", "no-store");

                //登录日志
                //设置浏览器信息
                UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
                SessionIdentity sessionIdentity = (SessionIdentity) subject.getPrincipal();
                VisitLogAddRequestDto visitLogAddRequestDto = new VisitLogAddRequestDto();
                visitLogAddRequestDto.setUserId(sessionIdentity.getUserId());
                visitLogAddRequestDto.setTenantId(sessionIdentity.getTenantId());
                visitLogAddRequestDto.setAccount(requestDto.getAccount());
                visitLogAddRequestDto.setClientIp(ActionUtils.getIpAddress(request));
                visitLogAddRequestDto.setClientOSVersion(userAgent.getOperatingSystem().getName());
                visitLogAddRequestDto.setClientOSName(request.getRemoteHost());
                visitLogAddRequestDto.setClientAgent(userAgent.getBrowser().toString());
                visitLogAddRequestDto.setRequestUrl(request.getRequestURL().toString());
                visitLogAddRequestDto.setRequestUri(request.getRequestURI());
                visitLogAddRequestDto.setRequestVerb(request.getMethod());
                visitLogAddRequestDto.setLocalAddress(request.getLocalAddr());
                visitLogAddRequestDto.setLocalName(request.getLocalName());
                visitLogAddRequestDto.setSessionId(request.getRequestedSessionId());
                visitLogAddRequestDto.setAccessToken(accessToken);
                visitLogService.addVisitLog(visitLogAddRequestDto);
                resultDto.success();
            }
        } catch (UnknownAccountException e) {
            //账号不存在
            log.warn("账号{}不存在,异常信息:{}", requestDto.getAccount(), e.getMessage());
            resultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(), UccResultEnum.USER_NOT_FOUND.getComment());
        } catch (LockedAccountException e) {
            //账号锁定
            log.warn("账号{}锁定,异常信息:{}", requestDto.getAccount(), e.getMessage());
            resultDto.makeResult(UccResultEnum.USER_LOCKED.getValue(), UccResultEnum.USER_LOCKED.getComment());
        } catch (IncorrectCredentialsException e) {
            //密码错误
            log.warn("账号{}密码错误,异常信息:{}", requestDto.getAccount(), e.getMessage());
            resultDto.makeResult(UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
            //记录登录失败次数
        } catch (AuthenticationException e) {
            //账户验证失败
            log.warn("账号{}验证失败，异常信息:{}", requestDto.getAccount(), e.getMessage());
            resultDto.makeResult(UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
        } catch (Exception e) {
            log.warn("账号{}登录失败，异常信息:{}", requestDto.getAccount(), e.getMessage());
            resultDto.makeResult(UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
        }
        return resultDto;
    }

    /**
     * 登出
     *
     * @param request  request
     * @param response response
     * @return
     */
    @ApiOperation(value = "登出", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/logout", name = "登出")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ResultDto logout(@RequestBody AccountSignOutRequestDto requestDto, HttpServletRequest request,
                            HttpServletResponse response) {
        ResultDto resultDto = new ResultDto();
        Subject subject = ShiroUtils.getSubject();
        subject.logout();
        JwtSessionHandler<SessionIdentity> sessionHandler = new JwtSessionHandler<>();
        sessionHandler.logout(request, response);
        resultDto.success();
        return resultDto;
    }
}
