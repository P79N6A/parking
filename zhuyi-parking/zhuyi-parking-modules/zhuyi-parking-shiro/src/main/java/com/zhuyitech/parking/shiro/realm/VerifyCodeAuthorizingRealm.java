package com.zhuyitech.parking.shiro.realm;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.zhuyitech.parking.shiro.authc.VerifyCodeToken;
import com.zhuyitech.parking.ucc.enums.UserStatusEnum;
import com.zhuyitech.parking.ucc.user.dto.UserGetByUserNameRequestDto;
import com.zhuyitech.parking.ucc.user.dto.UserGetRequestDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserResultDto;
import com.zhuyitech.parking.ucc.service.api.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author zwq
 */
@Component
public class VerifyCodeAuthorizingRealm extends AuthorizingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(VerifyCodeAuthorizingRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 授权的回调方法
     *
     * @param principals 认证人
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // retrieve role names and permission names
        final SessionIdentity sessionIdentity = (SessionIdentity) principals.getPrimaryPrincipal();
        UserGetRequestDto getRequestDto = new UserGetRequestDto();
        getRequestDto.setUsername(sessionIdentity.getUsername());
        final ObjectResultDto<UserResultDto> objectResultDto = userService.getUser(getRequestDto);
        if (objectResultDto.isFailed() || objectResultDto.getData() == null) {
            throw new UnknownAccountException("Account does not exist");
        }

        final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 认证的回调方法
     *
     * @param token 令牌
     * @return 认证信息
     * @throws AuthenticationException 认证失败
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        VerifyCodeToken verifyCodeToken = (VerifyCodeToken) token;
        String username = verifyCodeToken.getUsername();
        UserGetByUserNameRequestDto userGetByIdRequestDto = new UserGetByUserNameRequestDto();
        userGetByIdRequestDto.setUserName(username);
        ObjectResultDto<UserResultDto> objectResultDto = userService.getUserByUserName(userGetByIdRequestDto);
        if (objectResultDto.isFailed() || objectResultDto.getData() == null) {
            throw new UnknownAccountException("Account does not exist");
        }
        UserResultDto userResultDto = objectResultDto.getData();
        if (UserStatusEnum.LOCKED.getValue().equals(userResultDto.getStatus())) {
            // 帐号锁定
            throw new LockedAccountException();
        }
        SessionIdentity sessionIdentity = new SessionIdentity();
        sessionIdentity.setUserId(userResultDto.getId());
        sessionIdentity.setUsername(userResultDto.getUsername());
        sessionIdentity.setUuid(userResultDto.getUuid());
        sessionIdentity.setPhoneNumber(userResultDto.getPhoneNumber());
        sessionIdentity.setEmailAddress(userResultDto.getEmailAddress());
        sessionIdentity.setUserType(userResultDto.getUserType());
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名
                sessionIdentity,
                // 密码
                username,
                // realm name
                getName()
        );
        return authenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof VerifyCodeToken) {
            return true;
        }
        return false;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
