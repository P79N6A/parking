package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.shiro.service.SaltGenerator;
import com.scapegoat.infrastructure.shiro.service.ShiroCryptoService;
import com.zhuyitech.parking.ucc.account.request.UserLoginRequestDto;
import com.zhuyitech.parking.ucc.account.request.UserPhoneRegisterRequestDto;
import com.zhuyitech.parking.ucc.account.request.UserVerifyCodeLoginRequestDto;
import com.zhuyitech.parking.ucc.domain.User;
import com.zhuyitech.parking.ucc.domain.UserAsset;
import com.zhuyitech.parking.ucc.domain.UserInfo;
import com.zhuyitech.parking.ucc.domain.UserPasswordLog;
import com.zhuyitech.parking.ucc.dto.request.CurrentLoginUserRequestDto;
import com.zhuyitech.parking.ucc.dto.result.user.CurrentUserResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserLoginResultDto;
import com.zhuyitech.parking.ucc.enums.*;
import com.zhuyitech.parking.ucc.service.UserAssetCrudService;
import com.zhuyitech.parking.ucc.service.UserCrudService;
import com.zhuyitech.parking.ucc.service.UserInfoCrudService;
import com.zhuyitech.parking.ucc.service.UserPasswordLogCrudService;
import com.zhuyitech.parking.ucc.service.api.SecurityService;
import com.zhuyitech.parking.ucc.user.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户安全服务
 *
 * @author walkman
 */
@Service("securityService")
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserInfoCrudService userInfoCrudService;

    @Autowired
    private UserAssetCrudService userAssetCrudService;

    @Autowired
    private SaltGenerator saltGenerator;

    @Autowired
    private ShiroCryptoService shiroCryptoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserPasswordLogCrudService userPasswordLogCrudService;

    /**
     * 用户注册
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto signUp(UserPhoneRegisterRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        User existUser = userCrudService.findByPhoneNumber(requestDto.getPhoneNumber());
        if (existUser != null) {
            return resultDto.makeResult(UCenterResultEnum.USER_PHONE_EXISTS.getValue(), UCenterResultEnum.USER_PHONE_EXISTS.getComment());
        } else {
            existUser = userCrudService.findByUsername(requestDto.getPhoneNumber());
            if (existUser != null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NAME_EXISTS.getValue(), UCenterResultEnum.USER_NAME_EXISTS.getComment());
            }
        }
        try {
            if (StringUtils.isEmpty(requestDto.getPassword())) {
                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_EMPTY.getValue(),
                        UCenterResultEnum.USER_PASSWORD_EMPTY.getComment());
            }
            String regx = "^[a-zA-Z0-9\\W]{6,20}$";
            if (!requestDto.getPassword().matches(regx)) {
                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getValue(),
                        UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getComment());
            }
            //新密码处理
            String salt = saltGenerator.generate();
            String encryptPassword = shiroCryptoService.password(requestDto.getPassword(), requestDto.getPhoneNumber() + salt);
            //用户信息
            User newUser = new User();
            newUser.setUsername(requestDto.getPhoneNumber());
            newUser.setPhoneNumberConfirmed(Boolean.TRUE);
            newUser.setPhoneNumber(requestDto.getPhoneNumber());
            newUser.setAccessAttemptCount(0);
            newUser.setAdministrator(Boolean.FALSE);
            newUser.setDefaultUser(Boolean.FALSE);
            newUser.setUuid(StringUtils.getUUID());
            newUser.setUserType(UserTypeEnum.CUSTOMER.getValue());
            newUser.setPassword(encryptPassword);
            newUser.setSalt(salt);
            newUser.setStatus(String.valueOf(UserStatusEnum.ENABLED.getValue()));

            boolean retVal = userCrudService.insert(newUser);
            if (retVal) {
                existUser = userCrudService.findByUsername(requestDto.getPhoneNumber());
                if (existUser == null) {
                    return resultDto.makeResult(UCenterResultEnum.USER_REGISTER_FAIL.getValue(), UCenterResultEnum.USER_REGISTER_FAIL.getComment());
                }
                UserInfo newUserInfo = new UserInfo();
                newUserInfo.setUserId(existUser.getId());
                newUserInfo.setLevel(UserLevelEnum.NORMAL.getValue());
                newUserInfo.setCertificateStatus(UserAuthStatusEnum.AUTHENTICATION_NO.getValue());
                userInfoCrudService.insert(newUserInfo);
                UserAsset newUserAsset = new UserAsset();
                newUserAsset.setUserId(existUser.getId());
                newUserAsset.setBalance(BigDecimal.ZERO);
                newUserAsset.setPoint(BigDecimal.ZERO);
                newUserAsset.setCouponBalance(BigDecimal.ZERO);
                newUserAsset.setCouponCount(0);
                newUserAsset.setPacketBalance(BigDecimal.ZERO);
                newUserAsset.setPacketCount(0);
                newUserAsset.setUnPaidBalance(BigDecimal.ZERO);
                newUserAsset.setUnPaidCount(0);
                newUserAsset.setViolationBalance(BigDecimal.ZERO);
                newUserAsset.setViolationCount(0);
                userAssetCrudService.insert(newUserAsset);
                resultDto.success();
            }
        } catch (Exception e) {
            log.error("用户注册失败" + e.getMessage());
            //用户注册失败
            return resultDto.makeResult(UCenterResultEnum.USER_REGISTER_FAIL.getValue(),
                    UCenterResultEnum.USER_REGISTER_FAIL.getComment());
        }
        return resultDto;
    }

    /**
     * 用户验证码注册
     *
     * @param requestDto UserVerifyCodeLoginRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto signUpByVerifyCode(UserVerifyCodeLoginRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        User existUser = userCrudService.findByPhoneNumber(requestDto.getPhoneNumber());
        if (existUser != null) {
            return resultDto.makeResult(UCenterResultEnum.USER_PHONE_EXISTS.getValue(), UCenterResultEnum.USER_PHONE_EXISTS.getComment());
        } else {
            existUser = userCrudService.findByUsername(requestDto.getPhoneNumber());
            if (existUser != null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NAME_EXISTS.getValue(), UCenterResultEnum.USER_NAME_EXISTS.getComment());
            }
        }
        try {
            //新密码处理
            String salt = saltGenerator.generate();
            //用户信息
            User newUser = new User();
            newUser.setUsername(requestDto.getPhoneNumber());
            newUser.setPhoneNumberConfirmed(Boolean.TRUE);
            newUser.setPhoneNumber(requestDto.getPhoneNumber());
            newUser.setAccessAttemptCount(0);
            newUser.setAdministrator(Boolean.FALSE);
            newUser.setDefaultUser(Boolean.FALSE);
            newUser.setUuid(StringUtils.getUUID());
            newUser.setUserType(UserTypeEnum.CUSTOMER.getValue());
            newUser.setStatus(String.valueOf(UserStatusEnum.ENABLED.getValue()));
            newUser.setPassword("");
            newUser.setSalt("");

            boolean retVal = userCrudService.insert(newUser);
            if (retVal) {
                existUser = userCrudService.findByUsername(requestDto.getPhoneNumber());
                if (existUser == null) {
                    return resultDto.makeResult(UCenterResultEnum.USER_REGISTER_FAIL.getValue(), UCenterResultEnum.USER_REGISTER_FAIL.getComment());
                }
                UserInfo newUserInfo = new UserInfo();
                newUserInfo.setUserId(existUser.getId());
                newUserInfo.setLevel(UserLevelEnum.NORMAL.getValue());
                newUserInfo.setCertificateStatus(UserAuthStatusEnum.AUTHENTICATION_NO.getValue());
                userInfoCrudService.insert(newUserInfo);
                UserAsset newUserAsset = new UserAsset();
                newUserAsset.setUserId(existUser.getId());
                newUserAsset.setBalance(BigDecimal.ZERO);
                newUserAsset.setPoint(BigDecimal.ZERO);
                newUserAsset.setCouponBalance(BigDecimal.ZERO);
                newUserAsset.setCouponCount(0);
                newUserAsset.setPacketBalance(BigDecimal.ZERO);
                newUserAsset.setPacketCount(0);
                newUserAsset.setUnPaidBalance(BigDecimal.ZERO);
                newUserAsset.setUnPaidCount(0);
                newUserAsset.setViolationBalance(BigDecimal.ZERO);
                newUserAsset.setViolationCount(0);
                userAssetCrudService.insert(newUserAsset);
                resultDto.success();
            }
        } catch (Exception e) {
            log.error("用户注册失败" + e.getMessage());
            //用户注册失败
            return resultDto.makeResult(UCenterResultEnum.USER_REGISTER_FAIL.getValue(),
                    UCenterResultEnum.USER_REGISTER_FAIL.getComment());
        }
        return resultDto;
    }

    /**
     * 用户登录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<UserLoginResultDto> signIn(UserLoginRequestDto requestDto) {

        ObjectResultDto<UserLoginResultDto> objectResultDto = new ObjectResultDto<>();
        User user = userCrudService.findByUsername(requestDto.getUsername());
        if (user == null) {
            user = userCrudService.findByPhoneNumber(requestDto.getUsername());
            if (user == null) {
                user = userCrudService.findByEmailAddress(requestDto.getUsername());
            }
        }
        if (user == null) {
            //账号不存在
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                    UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        final Date now = new Date();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
            usernamePasswordToken.setUsername(requestDto.getUsername());
            usernamePasswordToken.setPassword(requestDto.getPassword().toCharArray());
            usernamePasswordToken.setRememberMe(false);
            user.setAccessAttemptCount(0);
            user.setLastLoginTime(now);
            userCrudService.updateById(user);
            UserLoginResultDto loginResultDto = modelMapper.map(user, UserLoginResultDto.class);
            objectResultDto.setData(loginResultDto);
        } catch (UnknownAccountException e) {
            //账号不存在
            objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                    UCenterResultEnum.USER_NOT_FOUND.getComment());
        } catch (IncorrectCredentialsException e) {
            //密码错误
            objectResultDto.makeResult(UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getValue(),
                    UCenterResultEnum.USERNAME_OR_PASSWORD_ERROR.getComment());
            //记录登录失败次数
            Integer errorCount = user.getAccessAttemptCount() == null ? Integer.valueOf(0) : user.getAccessAttemptCount();
            user.setAccessAttemptCount(errorCount + 1);
            user.setLastLoginTime(now);
            userCrudService.updateById(user);
            return objectResultDto;
        } catch (LockedAccountException e) {
            //账号锁定
            objectResultDto.makeResult(UCenterResultEnum.USER_LOCKED.getValue(),
                    UCenterResultEnum.USER_LOCKED.getComment());
        } catch (AuthenticationException e) {
            //账户验证失败
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 登录用户信息
     */
    @Override
    public ObjectResultDto<CurrentUserResultDto> currentUser(CurrentLoginUserRequestDto requestDto) {
        ObjectResultDto<CurrentUserResultDto> objectResultDto = new ObjectResultDto<>();
        return objectResultDto;
    }

    /**
     * 用户退出
     */
    @Override
    public ResultDto signOut(UserLogoutRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        return resultDto;
    }

    /**
     * 密码找回之更新密码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto retrievePassword(UserRetrievePasswordRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            String regx = "^[a-zA-Z0-9\\W]{6,20}$";
            if (StringUtils.isEmpty(requestDto.getPassword()) || StringUtils.isEmpty(requestDto.getConfirmedPassword())) {
                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_EMPTY.getValue(),
                        UCenterResultEnum.USER_PASSWORD_EMPTY.getComment());
            }
            if (!requestDto.getPassword().matches(regx) || !requestDto.getConfirmedPassword().matches(regx)) {
                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getValue(),
                        UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getComment());
            }
            if (!requestDto.getConfirmedPassword().equals(requestDto.getPassword())) {
                return resultDto.makeResult(UCenterResultEnum.PASSWORD_DISMATCH_ERROR.getValue(),
                        UCenterResultEnum.PASSWORD_DISMATCH_ERROR.getComment());
            }
            User user = userCrudService.findByPhoneNumber(requestDto.getPhoneNumber());
            if (user == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            //新密码处理
            String salt = saltGenerator.generate();
            String encryptPassword = shiroCryptoService.password(requestDto.getPassword(), user.getUsername() + salt);
            //添加密码修改记录
            UserPasswordLog userPasswordLog = new UserPasswordLog();
            userPasswordLog.setUserId(user.getId());
            userPasswordLog.setPasswordType(PasswordTypeEnum.ACCESS.getValue());
            userPasswordLog.setOldPassword(user.getPassword());
            userPasswordLog.setNewPassword(encryptPassword);
            userPasswordLogCrudService.insert(userPasswordLog);
            //设置新密码
            user.setSalt(salt);
            user.setPassword(encryptPassword);
            userCrudService.updateById(user);
            return resultDto.success();
        } catch (Exception e) {
            log.error("找回密码失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
