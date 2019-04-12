package com.zhuyitech.parking.ucc.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.account.AccountService;
import com.zhuyitech.parking.ucc.account.request.AlipayUserHasRegisteredRequestDto;
import com.zhuyitech.parking.ucc.account.request.UserAlipayRegisterRequestDto;
import com.zhuyitech.parking.ucc.account.request.UserWechatSignUpRequestDto;
import com.zhuyitech.parking.ucc.account.request.WechatUserHasRegisteredRequestDto;
import com.zhuyitech.parking.ucc.domain.User;
import com.zhuyitech.parking.ucc.domain.UserAsset;
import com.zhuyitech.parking.ucc.domain.UserInfo;
import com.zhuyitech.parking.ucc.dto.result.user.AlipayRegisterSuccessStatusResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.AlipayUserHasRegisteredResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.WechatRegisterSuccessStatusResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.WechatUserHasRegisteredResultDto;
import com.zhuyitech.parking.ucc.enums.*;
import com.zhuyitech.parking.ucc.service.UserAssetCrudService;
import com.zhuyitech.parking.ucc.service.UserCrudService;
import com.zhuyitech.parking.ucc.service.UserInfoCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 账号注册服务服务
 *
 * @author zwq
 */
@Service("accountService")
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final static String TEMPORARY_PHONENUMBER = "11111111111";

    @Autowired
    private UserInfoCrudService userInfoCrudService;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserAssetCrudService userAssetCrudService;

    @Override
    public ObjectResultDto<AlipayUserHasRegisteredResultDto> alipayUserHasRegistered(AlipayUserHasRegisteredRequestDto requestDto) {
        ObjectResultDto<AlipayUserHasRegisteredResultDto> objectResultDto = new ObjectResultDto<>();
        AlipayUserHasRegisteredResultDto alipayUserHasRegisteredResultDto = new AlipayUserHasRegisteredResultDto();
        if (StringUtils.isEmpty(requestDto.getAlipayUserId())) {
            return objectResultDto.makeResult(UCenterResultEnum.ALIPAYUSERID_EMPTY.getValue(), UCenterResultEnum.ALIPAYUSERID_EMPTY.getComment());
        }
        UserInfo userInfo = userInfoCrudService.findByAliUserId(requestDto.getAlipayUserId());
        if (null == userInfo) {
            alipayUserHasRegisteredResultDto.setRegistered(Boolean.FALSE);
        } else {
            alipayUserHasRegisteredResultDto.setRegistered(Boolean.TRUE);
        }
        objectResultDto.setData(alipayUserHasRegisteredResultDto);
        objectResultDto.success();
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<AlipayRegisterSuccessStatusResultDto> alipayRegister(UserAlipayRegisterRequestDto requestDto) {
        ObjectResultDto<AlipayRegisterSuccessStatusResultDto> objectResultDto = new ObjectResultDto<>();
        AlipayRegisterSuccessStatusResultDto isRegisterSuccessAlipayStatusResultDto = new AlipayRegisterSuccessStatusResultDto();
        UserInfo existUserInfo = userInfoCrudService.findByAliUserId(requestDto.getAlipayUserId());
        if (existUserInfo != null) {
            isRegisterSuccessAlipayStatusResultDto.setSuccess(Boolean.FALSE);
            objectResultDto.setData(isRegisterSuccessAlipayStatusResultDto);
            return objectResultDto.makeResult(UCenterResultEnum.USER_HAS_BIND_ALIPAY.getValue(), UCenterResultEnum.USER_HAS_BIND_ALIPAY.getComment());
        }
        User exUser = userCrudService.findByPhoneNumber(requestDto.getPhoneNumber());
        if (exUser != null) {
            isRegisterSuccessAlipayStatusResultDto.setSuccess(Boolean.FALSE);
            objectResultDto.setData(isRegisterSuccessAlipayStatusResultDto);
            return objectResultDto.makeResult(UCenterResultEnum.USER_PHONE_EXISTS.getValue(), UCenterResultEnum.USER_PHONE_EXISTS.getComment());
        }
        try {
            User newUser = new User();
            newUser.setUsername(requestDto.getPhoneNumber());
            newUser.setPhoneNumber(requestDto.getPhoneNumber());
            newUser.setPhoneNumberConfirmed(Boolean.TRUE);
            newUser.setAccessAttemptCount(0);
            newUser.setAdministrator(Boolean.FALSE);
            newUser.setDefaultUser(Boolean.FALSE);
            newUser.setUuid(StringUtils.getUUID());
            newUser.setUserType(UserTypeEnum.CUSTOMER.getValue());
            newUser.setPassword("");
            newUser.setSalt("");
            newUser.setNickname(requestDto.getNickName());
            newUser.setPortrait(requestDto.getAvatar());
            newUser.setStatus(String.valueOf(UserStatusEnum.ENABLED.getValue()));

            boolean retVal = userCrudService.insert(newUser);
            if (retVal) {
                User existUser = userCrudService.findByUsername(requestDto.getPhoneNumber());
                if (existUser == null) {
                    isRegisterSuccessAlipayStatusResultDto.setSuccess(Boolean.FALSE);
                    objectResultDto.setData(isRegisterSuccessAlipayStatusResultDto);
                    return objectResultDto.makeResult(UCenterResultEnum.USER_ALIPAY_SIGN_UP_ERROR.getValue(), UCenterResultEnum.USER_ALIPAY_SIGN_UP_ERROR.getComment());
                }
                UserInfo newUserInfo = new UserInfo();
                newUserInfo.setUserId(existUser.getId());
                newUserInfo.setLevel(UserLevelEnum.NORMAL.getValue());
                newUserInfo.setCertificateStatus(UserAuthStatusEnum.AUTHENTICATION_NO.getValue());
                newUserInfo.setAliAvatar(requestDto.getAvatar());
                newUserInfo.setAliCity(requestDto.getCity());
                newUserInfo.setAliGender(requestDto.getGender());
                newUserInfo.setAliIsCertified(requestDto.getIsCertified());
                newUserInfo.setAliIsStudentCertified(requestDto.getIsStudentCertified());
                newUserInfo.setAliNickname(requestDto.getNickName());
                newUserInfo.setAliProvince(requestDto.getProvince());
                newUserInfo.setAliUserStatus(requestDto.getUserStatus());
                newUserInfo.setAliUserType(requestDto.getUserType());
                newUserInfo.setAliUserId(requestDto.getAlipayUserId());
                newUserInfo.setAliAuthorized(Boolean.TRUE);
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
                isRegisterSuccessAlipayStatusResultDto.setSuccess(Boolean.TRUE);
                objectResultDto.setData(isRegisterSuccessAlipayStatusResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("支付宝注册失败" + e.getMessage());
            isRegisterSuccessAlipayStatusResultDto.setSuccess(Boolean.FALSE);
            objectResultDto.setData(isRegisterSuccessAlipayStatusResultDto);
            return objectResultDto.makeResult(UCenterResultEnum.USER_ALIPAY_SIGN_UP_ERROR.getValue(), UCenterResultEnum.USER_ALIPAY_SIGN_UP_ERROR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<WechatUserHasRegisteredResultDto> wechatUserHasRegistered(WechatUserHasRegisteredRequestDto requestDto) {
        ObjectResultDto<WechatUserHasRegisteredResultDto> objectResultDto = new ObjectResultDto<>();
        WechatUserHasRegisteredResultDto resultDto = new WechatUserHasRegisteredResultDto();
        if (StringUtils.isEmpty(requestDto.getUnionId())) {
            return objectResultDto.makeResult(UCenterResultEnum.OPENID_NOT_EXISTS.getValue(), UCenterResultEnum.OPENID_NOT_EXISTS.getComment());
        }
        UserInfo userInfo = userInfoCrudService.findByUnionId(requestDto.getUnionId());
        if (null == userInfo) {
            resultDto.setRegistered(Boolean.FALSE);
        } else {
            User existUser = userCrudService.selectById(userInfo.getUserId());
            if (null != existUser && existUser.getUserType().equals(UserTypeEnum.TEMPORARY_CUSTOMER.getValue())) {
                //通过微信公众号绑定的无手机号的账号
                resultDto.setRegistered(Boolean.FALSE);
            } else {
                resultDto.setRegistered(Boolean.TRUE);
            }
        }
        objectResultDto.setData(resultDto);
        objectResultDto.success();
        return objectResultDto;
    }

    /**
     * 通过微信账号注册
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<WechatRegisterSuccessStatusResultDto> wechatRegister(UserWechatSignUpRequestDto requestDto) {
        ObjectResultDto<WechatRegisterSuccessStatusResultDto> objectResultDto = new ObjectResultDto<>();
        WechatRegisterSuccessStatusResultDto resultDto = new WechatRegisterSuccessStatusResultDto();
        try {
            if (!StringUtils.isChinaPhoneNumber(requestDto.getPhoneNumber())) {
                resultDto.setSuccess(Boolean.FALSE);
                objectResultDto.setData(resultDto);
                return objectResultDto.makeResult(UCenterResultEnum.MOBILE_PHONE_NUMBER_INVALID.getValue(), UCenterResultEnum.MOBILE_PHONE_NUMBER_INVALID.getComment());
            }
            UserInfo existWechatUserInfo = userInfoCrudService.findByUnionId(requestDto.getUnionid());
            User existPhoneNumberUser = userCrudService.findByPhoneNumber(requestDto.getPhoneNumber());
            if (existWechatUserInfo != null) {
                User existWechatUser = userCrudService.selectById(existWechatUserInfo.getUserId());
                if (null != existWechatUser && existWechatUser.getUserType().equals(UserTypeEnum.TEMPORARY_CUSTOMER.getValue())) {
                    //通过微信公众号绑定的无手机号的账号
                    if (existPhoneNumberUser == null) {
                        //再次注册 绑定新手机号，需要更新之前的账号信息
                        existWechatUser.setUsername(requestDto.getPhoneNumber());
                        existWechatUser.setPhoneNumber(requestDto.getPhoneNumber());
                        existWechatUser.setUserType(UserTypeEnum.CUSTOMER.getValue());
                        userCrudService.updateById(existWechatUser);
                        resultDto.setSuccess(Boolean.TRUE);
                        objectResultDto.setData(resultDto);
                        return objectResultDto.success();
                    } else {
                        //再次被手机号绑定，需要删除之前的账号信息
                        userCrudService.deleteById(existWechatUser.getId());
                        userInfoCrudService.deleteById(existWechatUserInfo.getId());
                    }
                } else {
                    resultDto.setSuccess(Boolean.FALSE);
                    objectResultDto.setData(resultDto);
                    return objectResultDto.makeResult(UCenterResultEnum.USER_WECHAT_HAS_BIND.getValue(), UCenterResultEnum.USER_WECHAT_HAS_BIND.getComment());
                }
            }
            if (null != existPhoneNumberUser) {
                UserInfo userInfo = userInfoCrudService.findByUserId(existPhoneNumberUser.getId());
                if ((null != userInfo && StringUtils.isEmpty(userInfo.getWxUnionId()))) {
                    User newUser = new User();
                    if (StringUtils.isEmpty(existPhoneNumberUser.getPortrait())) {
                        newUser.setPortrait(requestDto.getHeadImgUrl());
                    }
                    if (StringUtils.isEmpty(existPhoneNumberUser.getNickname())) {
                        newUser.setNickname(requestDto.getNickName());
                    }
                    EntityWrapper<User> entity = new EntityWrapper<>();
                    entity.eq("id", existPhoneNumberUser.getId());
                    userCrudService.update(newUser, entity);
                    UserInfo newUserInfo = new UserInfo();
                    newUserInfo.setWxUuid(StringUtils.getUUID());
                    newUserInfo.setWxOpenId(requestDto.getOpenId());
                    newUserInfo.setWxUnionId(requestDto.getUnionid());
                    newUserInfo.setWxNickname(requestDto.getNickName());
                    newUserInfo.setWxCity(requestDto.getCity());
                    newUserInfo.setWxCounty(requestDto.getCountry());
                    newUserInfo.setWxProvince(requestDto.getProvince());
                    newUserInfo.setWxSex(Integer.parseInt(requestDto.getSex()));
                    newUserInfo.setWxAuthorized(Boolean.TRUE);
                    EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq("id", userInfo.getId());
                    userInfoCrudService.update(newUserInfo, entityWrapper);
                    resultDto.setSuccess(Boolean.TRUE);
                    objectResultDto.setData(resultDto);
                    return objectResultDto.success();
                } else {
                    resultDto.setSuccess(Boolean.FALSE);
                    objectResultDto.setData(resultDto);
                    return objectResultDto.makeResult(UCenterResultEnum.USER_PHONE_EXISTS.getValue(), UCenterResultEnum.USER_PHONE_EXISTS.getComment());
                }
            }
            Boolean registered = sealWechatRegister(requestDto);
            if (registered) {
                resultDto.setSuccess(Boolean.TRUE);
                objectResultDto.setData(resultDto);
                objectResultDto.success();
            } else {
                resultDto.setSuccess(Boolean.FALSE);
                objectResultDto.setData(resultDto);
                return objectResultDto.makeResult(UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getValue(), UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getComment());
            }
        } catch (Exception e) {
            log.error("微信注册失败" + e.getMessage());
            resultDto.setSuccess(Boolean.FALSE);
            objectResultDto.setData(resultDto);
            return objectResultDto.makeResult(UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getValue(), UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 微信公众号通过微信账号注册
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<WechatRegisterSuccessStatusResultDto> jsapiRegister(UserWechatSignUpRequestDto requestDto) {
        ObjectResultDto<WechatRegisterSuccessStatusResultDto> objectResultDto = new ObjectResultDto<>();
        WechatRegisterSuccessStatusResultDto resultDto = new WechatRegisterSuccessStatusResultDto();
        try {
            if (!StringUtils.isChinaPhoneNumber(requestDto.getPhoneNumber())) {
                resultDto.setSuccess(Boolean.FALSE);
                objectResultDto.setData(resultDto);
                return objectResultDto.makeResult(UCenterResultEnum.MOBILE_PHONE_NUMBER_INVALID.getValue(), UCenterResultEnum.MOBILE_PHONE_NUMBER_INVALID.getComment());
            }
            UserInfo existWechatUserInfo = userInfoCrudService.findByUnionId(requestDto.getUnionid());
            User existPhoneNumberUser = userCrudService.findByPhoneNumber(requestDto.getPhoneNumber());
            if (existWechatUserInfo != null) {
                User existWechatUser = userCrudService.selectById(existWechatUserInfo.getUserId());
                if (null != existWechatUser && existWechatUser.getUserType().equals(UserTypeEnum.TEMPORARY_CUSTOMER.getValue())) {
                    //通过微信公众号绑定的无手机号的账号
                    if (existPhoneNumberUser == null) {
                        //再次注册 绑定新手机号，需要更新之前的账号信息
                        existWechatUser.setUsername(requestDto.getPhoneNumber());
                        existWechatUser.setPhoneNumber(requestDto.getPhoneNumber());
                        existWechatUser.setUserType(UserTypeEnum.CUSTOMER.getValue());
                        userCrudService.updateById(existWechatUser);
                        resultDto.setSuccess(Boolean.TRUE);
                        objectResultDto.setData(resultDto);
                        return objectResultDto.success();
                    } else {
                        //再次被手机号绑定，需要删除之前的账号信息
                        userCrudService.deleteById(existWechatUser.getId());
                        userInfoCrudService.deleteById(existWechatUserInfo.getId());
                    }
                } else {
                    resultDto.setSuccess(Boolean.FALSE);
                    objectResultDto.setData(resultDto);
                    return objectResultDto.makeResult(UCenterResultEnum.USER_WECHAT_HAS_BIND.getValue(), UCenterResultEnum.USER_WECHAT_HAS_BIND.getComment());
                }
            }
            if (null != existPhoneNumberUser) {
                UserInfo userInfo = userInfoCrudService.findByUserId(existPhoneNumberUser.getId());
                if ((null != userInfo && StringUtils.isEmpty(userInfo.getWxUnionId()))) {
                    User newUser = new User();
                    if (StringUtils.isEmpty(existPhoneNumberUser.getPortrait())) {
                        newUser.setPortrait(requestDto.getHeadImgUrl());
                    }
                    if (StringUtils.isEmpty(existPhoneNumberUser.getNickname())) {
                        newUser.setNickname(requestDto.getNickName());
                    }
                    EntityWrapper<User> entity = new EntityWrapper<>();
                    entity.eq("id", existPhoneNumberUser.getId());
                    userCrudService.update(newUser, entity);
                    UserInfo newUserInfo = new UserInfo();
                    newUserInfo.setWxUuid(StringUtils.getUUID());
                    newUserInfo.setWxOpenId(requestDto.getOpenId());
                    newUserInfo.setWxUnionId(requestDto.getUnionid());
                    newUserInfo.setWxNickname(requestDto.getNickName());
                    newUserInfo.setWxCity(requestDto.getCity());
                    newUserInfo.setWxCounty(requestDto.getCountry());
                    newUserInfo.setWxProvince(requestDto.getProvince());
                    newUserInfo.setWxSex(Integer.parseInt(requestDto.getSex()));
                    newUserInfo.setWxAuthorized(Boolean.TRUE);
                    EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq("id", userInfo.getId());
                    userInfoCrudService.update(newUserInfo, entityWrapper);
                    resultDto.setSuccess(Boolean.TRUE);
                    objectResultDto.setData(resultDto);
                    return objectResultDto.success();
                } else {
                    resultDto.setSuccess(Boolean.FALSE);
                    objectResultDto.setData(resultDto);
                    return objectResultDto.makeResult(UCenterResultEnum.USER_PHONE_EXISTS.getValue(), UCenterResultEnum.USER_PHONE_EXISTS.getComment());
                }
            }
            Boolean registered = sealWechatRegister(requestDto);
            if (registered) {
                resultDto.setSuccess(Boolean.TRUE);
                objectResultDto.setData(resultDto);
                objectResultDto.success();
            } else {
                resultDto.setSuccess(Boolean.FALSE);
                objectResultDto.setData(resultDto);
                return objectResultDto.makeResult(UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getValue(), UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getComment());
            }
        } catch (Exception e) {
            log.error("微信公众号 微信注册失败" + e.getMessage());
            resultDto.setSuccess(Boolean.FALSE);
            objectResultDto.setData(resultDto);
            return objectResultDto.makeResult(UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getValue(), UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<WechatRegisterSuccessStatusResultDto> jsapiRegisterByScanQRCode(UserWechatSignUpRequestDto requestDto) {
        ObjectResultDto<WechatRegisterSuccessStatusResultDto> objectResultDto = new ObjectResultDto<>();
        WechatRegisterSuccessStatusResultDto resultDto = new WechatRegisterSuccessStatusResultDto();
        try {
            UserInfo existUserInfo = userInfoCrudService.findByUnionId(requestDto.getUnionid());
            if (existUserInfo != null) {
                resultDto.setSuccess(Boolean.FALSE);
                objectResultDto.setData(resultDto);
                return objectResultDto.makeResult(UCenterResultEnum.USER_WECHAT_HAS_BIND.getValue(), UCenterResultEnum.USER_WECHAT_HAS_BIND.getComment());
            }
            Boolean registered = sealWechatRegister(requestDto);
            if (registered) {
                resultDto.setSuccess(Boolean.TRUE);
                objectResultDto.setData(resultDto);
                objectResultDto.success();
            } else {
                resultDto.setSuccess(Boolean.FALSE);
                objectResultDto.setData(resultDto);
                return objectResultDto.makeResult(UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getValue(), UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getComment());
            }
        } catch (Exception e) {
            log.error("微信注册失败" + e.getMessage());
            resultDto.setSuccess(Boolean.FALSE);
            objectResultDto.setData(resultDto);
            return objectResultDto.makeResult(UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getValue(), UCenterResultEnum.USER_WEIXIN_SIGN_UP_ERROR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 微信注册用户信息入库
     *
     * @param requestDto
     * @return
     */
    private Boolean sealWechatRegister(UserWechatSignUpRequestDto requestDto) {
        Boolean registerResult = Boolean.FALSE;
        User newUser = new User();
        newUser.setUsername(requestDto.getPhoneNumber());
        newUser.setPhoneNumber(requestDto.getPhoneNumber());
        newUser.setUserType(UserTypeEnum.CUSTOMER.getValue());
        if (StringUtils.isEmpty(requestDto.getPhoneNumber())) {
            // 默认填充一个随机字符串作为用户名
            newUser.setPhoneNumber(TEMPORARY_PHONENUMBER);
            newUser.setUsername(TEMPORARY_PHONENUMBER);
            newUser.setUserType(UserTypeEnum.TEMPORARY_CUSTOMER.getValue());
        }
        newUser.setPhoneNumberConfirmed(Boolean.TRUE);
        newUser.setAccessAttemptCount(0);
        newUser.setAdministrator(Boolean.FALSE);
        newUser.setDefaultUser(Boolean.FALSE);
        newUser.setUuid(StringUtils.getUUID());
        newUser.setPassword("");
        newUser.setSalt("");
        newUser.setPortrait(requestDto.getHeadImgUrl());
        newUser.setNickname(requestDto.getNickName());
        newUser.setStatus(String.valueOf(UserStatusEnum.ENABLED.getValue()));

        boolean retVal = userCrudService.insert(newUser);
        if (retVal) {
            User existUser;
            if (StringUtils.isNotEmpty(requestDto.getPhoneNumber())) {
                //普通注册
                existUser = userCrudService.findByUsername(requestDto.getPhoneNumber());
                if (existUser == null) {
                    return false;
                }
            } else {
                // 无手机号注册
                EntityWrapper<User> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("uuid", newUser.getUuid());
                entityWrapper.eq("nickname", newUser.getNickname());
                existUser = userCrudService.selectOne(entityWrapper);
                if (existUser == null) {
                    return false;
                }
            }
            //用户信息
            UserInfo newUserInfo = new UserInfo();
            newUserInfo.setUserId(existUser.getId());
            newUserInfo.setLevel(UserLevelEnum.NORMAL.getValue());
            newUserInfo.setCertificateStatus(UserAuthStatusEnum.AUTHENTICATION_NO.getValue());
            newUserInfo.setWxUuid(StringUtils.getUUID());
            newUserInfo.setWxOpenId(requestDto.getOpenId());
            newUserInfo.setWxUnionId(requestDto.getUnionid());
            newUserInfo.setWxNickname(requestDto.getNickName());
            newUserInfo.setWxCity(requestDto.getCity());
            newUserInfo.setWxCounty(requestDto.getCountry());
            newUserInfo.setWxProvince(requestDto.getProvince());
            newUserInfo.setWxSex(Integer.parseInt(requestDto.getSex()));
            newUserInfo.setWxAuthorized(Boolean.TRUE);
            userInfoCrudService.insert(newUserInfo);

            //用户资产信息
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
            registerResult = Boolean.TRUE;
        }
        return registerResult;
    }
}
