package com.zhuyitech.parking.shiro.realm;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.zhuyitech.parking.shiro.authc.JwtAuthenticationToken;
import com.zhuyitech.parking.ucc.enums.UserStatusEnum;
import com.zhuyitech.parking.ucc.user.dto.UserGetRequestDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserResultDto;
import com.zhuyitech.parking.ucc.service.api.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * JwtAuthorizingRealm
 *
 * @author walkman
 */
public class JwtAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof JwtAuthenticationToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取用户信息
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

    @SuppressWarnings("unchecked")
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {

        String token = (String) authenticationToken.getCredentials();
//        DecodedJWT jwt = JwtUtils.get(token);
        // 解密获得username，用于和数据库进行对比
//        String username = JwtUtils.getUsername(token);
        String username = "";

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
//        if (!JwtUtils.verify(token, username, "111111")) {
//            throw new AuthenticationException("Username or password error");
//        }
//        if (!JwtUtils.verify(token, username, userResultDto.getPassword(), jwt.getClaim("terminateType").asInt())) {
//            throw new AuthenticationException("Username or password error");
//        }
        SessionIdentity sessionIdentity = new SessionIdentity();
        sessionIdentity.setUserId(userResultDto.getId());
        sessionIdentity.setUsername(userResultDto.getUsername());
        sessionIdentity.setUuid(userResultDto.getUuid());
        sessionIdentity.setPhoneNumber(userResultDto.getPhoneNumber());
        sessionIdentity.setEmailAddress(userResultDto.getEmailAddress());
        sessionIdentity.setUserType(userResultDto.getUserType());
        /*// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名
                sessionIdentity,
                // 密码
                userResultDto.getPassword(),
                ByteSource.Util.bytes(userResultDto.getCredentialsSalt()),
                // realm name
                getName()
        );*/
        return new SimpleAuthenticationInfo(sessionIdentity, token, getName());
    }

}
