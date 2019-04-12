package com.zoeeasy.cloud.inspect.controller.account;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.scapegoat.infrastructure.session.JwtSessionHandler;
import com.scapegoat.infrastructure.session.SessionHandler;
import com.scapegoat.infrastructure.shiro.util.ShiroUtils;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.ucc.account.dto.request.AccountSignInRequestDto;
import com.zoeeasy.cloud.ucc.account.dto.request.AccountSignOutRequestDto;
import com.zoeeasy.cloud.ucc.enums.UccResultEnum;
import com.zoeeasy.cloud.ucc.platform.PlatformUccService;
import com.zoeeasy.cloud.ucc.platform.dto.request.UserGetByUserIdRequestDto;
import com.zoeeasy.cloud.ucc.platform.dto.result.UserInfoResultDto;
import com.zoeeasy.cloud.ucc.user.UserLoginLogService;
import com.zoeeasy.cloud.ucc.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */
@RestController
@RequestMapping("/cloud/inspect")
@Slf4j
@Api(tags = "登录登出", value = "登录Api", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiVersion(1)
@ApiSigned
public class AccountController {

    @Autowired
    private UserLoginLogService userLoginLogService;

    @Autowired
    private PlatformUccService platformUccService;

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param request  request
     * @param response response
     * @return
     */
    @ApiOperation(value = "登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/login", name = "登录")
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
                resultDto.success();
            }
        } catch (UnknownAccountException e) {
            //账号不存在
            log.warn("账号{]不存在,异常信息:{}", requestDto.getAccount(), e.getMessage());
            resultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(), UccResultEnum.USER_NOT_FOUND.getComment());
        } catch (LockedAccountException e) {
            //账号锁定
            log.warn("账号{}锁定,异常信息:{}", requestDto.getAccount(), e.getMessage());
            resultDto.makeResult(UccResultEnum.USER_LOCKED.getValue(), UccResultEnum.USER_LOCKED.getComment());
        } catch (IncorrectCredentialsException e) {
            //密码错误
            log.warn("账号{}密码错误,异常信息:{}", requestDto.getAccount(), e.getMessage());
            resultDto.makeResult(UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(),
                    UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
        } catch (AuthenticationException e) {
            //账户验证失败
            log.warn("账号{}验证失败，异常信息:{}", requestDto.getAccount(), e.getMessage());
            resultDto.makeResult(UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(), UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
        } catch (Exception e) {
            log.error(e.getMessage());
            resultDto.makeResult(UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(),

                    UccResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
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

    /**
     * 获取用户信息
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            UserInfoResultDto.class)
    @PostMapping(value = "/userInfo")
    public ObjectResultDto<UserInfoResultDto> getUserByUserId(@RequestBody UserGetByUserIdRequestDto requestDto) {
        return platformUccService.getUserByUserId(requestDto);
    }
}
