package com.zhuyitech.parking.shiro.realm;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.zhuyitech.parking.ucc.enums.UserStatusEnum;
import com.zhuyitech.parking.ucc.user.dto.UserGetRequestDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserResultDto;
import com.zhuyitech.parking.ucc.service.api.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author walkman
 */
@Component
public class UserAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 认证的回调方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        String username = authcToken.getUsername();

        UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
        userGetRequestDto.setUsername(username);
        ObjectResultDto<UserResultDto> objectResultDto = userService.getUser(userGetRequestDto);
        if (objectResultDto.isFailed() || objectResultDto.getData() == null) {

            userGetRequestDto = new UserGetRequestDto();
            userGetRequestDto.setEmailAddress(username);
            objectResultDto = userService.getUser(userGetRequestDto);
            if (objectResultDto.isFailed() || objectResultDto.getData() == null) {
                userGetRequestDto = new UserGetRequestDto();
                userGetRequestDto.setPhoneNumber(username);
                objectResultDto = userService.getUser(userGetRequestDto);
            }
        }
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
                userResultDto.getPassword(),
                ByteSource.Util.bytes(userResultDto.getUsername() + userResultDto.getSalt()),
                // realm name
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 授权的回调方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {

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
