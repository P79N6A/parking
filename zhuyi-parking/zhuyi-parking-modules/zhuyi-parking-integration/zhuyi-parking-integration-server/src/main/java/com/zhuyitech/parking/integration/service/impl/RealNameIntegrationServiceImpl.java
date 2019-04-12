package com.zhuyitech.parking.integration.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zhuyitech.parking.common.enums.Gender;
import com.zhuyitech.parking.common.utils.IDCardUtil;
import com.zhuyitech.parking.integration.config.IntegrationConfig;
import com.zhuyitech.parking.integration.service.api.RealNameIntegrationService;
import com.zhuyitech.parking.tool.dto.request.IdentityCardVerifyRequestDto;
import com.zhuyitech.parking.tool.dto.result.identity.IdentityCardVerifyResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.api.IdentityCardService;
import com.zhuyitech.parking.ucc.dto.request.realname.UserAuthApplyAddRequestDto;
import com.zhuyitech.parking.ucc.dto.request.realname.UserAuthApplyGetByUserIdRequestDto;
import com.zhuyitech.parking.ucc.dto.request.realname.UserAuthApplyInsertRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserAuthApplyResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserInfoResultDto;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.enums.UserAuthStatusEnum;
import com.zhuyitech.parking.ucc.service.api.UserAuthenticationApplyService;
import com.zhuyitech.parking.ucc.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import com.zhuyitech.parking.ucc.user.dto.UserInfoGetRequestDto;
import com.zhuyitech.parking.ucc.user.dto.UserInfoUpdateRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/7/11 0011
 */
@Service("realNameIntegrationService")
@Slf4j
public class RealNameIntegrationServiceImpl implements RealNameIntegrationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IdentityCardService identityCardService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationApplyService userAuthenticationApplyService;

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private IntegrationConfig integrationConfig;

    /**
     * 身份认证集成服务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addAuthApply(UserAuthApplyAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (!StringUtils.isEmpty(requestDto.getCardNo())) {
                boolean validatedAllIdCard = IDCardUtil.isIdentity(requestDto.getCardNo());
                if (!validatedAllIdCard) {
                    requestDto.setCardNo("");
                }
            }
            if (StringUtils.isEmpty(requestDto.getCardFront())) {
                return resultDto.makeResult(UCenterResultEnum.USER_CARD_FRONT_EMPTY.getValue(),
                        UCenterResultEnum.USER_CARD_FRONT_EMPTY.getComment());
            }

            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName("lock.redission.zhuyi.parking.user.auth." + requestDto.getSessionIdentity().getUserId());
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {

                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    Long userId = requestDto.getSessionIdentity().getUserId();
                    //如果用户已经实名或者用户已经提交,禁止重复提交
                    UserInfoGetRequestDto userInfoGetRequestDto = new UserInfoGetRequestDto();
                    userInfoGetRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
                    ObjectResultDto<UserInfoResultDto> userInfoResultDto = userService.getUserInfoByUserId(userInfoGetRequestDto);
                    if (userInfoResultDto.isFailed() || null == userInfoResultDto.getData()) {
                        return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                                UCenterResultEnum.USER_NOT_FOUND.getComment());
                    }
                    UserInfoResultDto userInfoResultDtoData = userInfoResultDto.getData();
                    if (userInfoResultDtoData.getCertificated().equals(UserAuthStatusEnum.AUTHENTICATED.getValue())) {
                        return resultDto.makeResult(UCenterResultEnum.USER_ALREADY_AUTHENTICATED.getValue(),
                                UCenterResultEnum.USER_ALREADY_AUTHENTICATED.getComment());
                    }
                    UserAuthApplyGetByUserIdRequestDto userAuthApplyGetByUserIdRequestDto = new UserAuthApplyGetByUserIdRequestDto();
                    userAuthApplyGetByUserIdRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
                    ObjectResultDto<UserAuthApplyResultDto> userAuthApplyByUserId = userAuthenticationApplyService.getUserAuthApplyByUserId(userAuthApplyGetByUserIdRequestDto);
                    if (userAuthApplyByUserId.isFailed()) {
                        return resultDto.failed();
                    }
                    UserAuthApplyResultDto authApplyByUserIdData = userAuthApplyByUserId.getData();
                    //用户是否有实名认证记录
                    if (authApplyByUserIdData != null) {
                        if (!authApplyByUserIdData.getAuthStatus().equals(UserAuthStatusEnum.AUTHENTICATION_REJECT.getValue())) {
                            return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_SUBMITTED.getValue(),
                                    UCenterResultEnum.USER_AUTHENTICATE_SUBMITTED.getComment());
                        }
                    }
                    if (integrationConfig.getManualAudit()) {
                        //人工审核
                        return manualRealNameAuth(requestDto, userId, userInfoResultDtoData);
                    } else {
                        //系统自动审核
                        return systemRealNameAuth(requestDto, userId, userInfoResultDtoData);
                    }
                }
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
        } catch (Exception e) {
            log.error("实名认证申请失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 人工审核实名认证申请
     */
    private ResultDto manualRealNameAuth(UserAuthApplyAddRequestDto requestDto, Long userId, UserInfoResultDto userInfoResultDtoData) {
        ResultDto resultDto = new ResultDto();
        try {
            Date now = new Date();
            UserAuthApplyInsertRequestDto applyAddRequestDto = modelMapper.map(requestDto, UserAuthApplyInsertRequestDto.class);
            applyAddRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            applyAddRequestDto.setUserId(userId);
            applyAddRequestDto.setApplyTime(now);
            UserInfoUpdateRequestDto userInfoUpdateRequestDto = new UserInfoUpdateRequestDto();
            userInfoUpdateRequestDto.setId(userInfoResultDtoData.getId());
            userInfoUpdateRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            userInfoUpdateRequestDto.setCardNo(requestDto.getCardNo());
            userInfoUpdateRequestDto.setRealName(requestDto.getRealName());
            userInfoUpdateRequestDto.setCertificated(UserAuthStatusEnum.AUTHENTICATION_PENDING.getValue());
            userInfoUpdateRequestDto.setCertificatedDate(now);
            applyAddRequestDto.setAuthStatus(UserAuthStatusEnum.AUTHENTICATION_PENDING.getValue());
            userService.updateUserInfo(userInfoUpdateRequestDto);
            ResultDto insertUserAuthApply = userAuthenticationApplyService.insertUserAuthApply(applyAddRequestDto);
            if (insertUserAuthApply.isFailed()) {
                return resultDto.failed();
            } else {
                return resultDto.success();
            }
        } catch (Exception e) {
            log.error("人工审核实名认证申请失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 系统自动审核实名认证申请
     */
    private ResultDto systemRealNameAuth(UserAuthApplyAddRequestDto requestDto, Long userId, UserInfoResultDto userInfoResultDtoData) {
        ResultDto resultDto = new ResultDto();
        try {
            Integer authStatus = UserAuthStatusEnum.AUTHENTICATION_PENDING.getValue();
            if (!StringUtils.isEmpty(requestDto.getCardNo()) && !StringUtils.isEmpty(requestDto.getRealName())) {
                IdentityCardVerifyRequestDto identityCardVerifyRequestDto = new IdentityCardVerifyRequestDto();
                identityCardVerifyRequestDto.setCardNo(requestDto.getCardNo());
                identityCardVerifyRequestDto.setRealName(requestDto.getRealName());
                ObjectResultDto<IdentityCardVerifyResultDto> identityCardResultDto = identityCardService.verifyIdCardSelect(identityCardVerifyRequestDto);
                if (identityCardResultDto.isSuccess() && null != identityCardResultDto.getData() && identityCardResultDto.getData().getVerifyStatus() == 0) {
                    authStatus = UserAuthStatusEnum.AUTHENTICATED.getValue();
                }
            }
            Date now = new Date();
            UserAuthApplyInsertRequestDto applyAddRequestDto = modelMapper.map(requestDto, UserAuthApplyInsertRequestDto.class);
            applyAddRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            applyAddRequestDto.setUserId(userId);
            applyAddRequestDto.setApplyTime(now);
            UserInfoUpdateRequestDto userInfoUpdateRequestDto = new UserInfoUpdateRequestDto();
            userInfoUpdateRequestDto.setId(userInfoResultDtoData.getId());
            userInfoUpdateRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            if (authStatus.equals(UserAuthStatusEnum.AUTHENTICATED.getValue())) {
                userInfoUpdateRequestDto.setCardNo(requestDto.getCardNo());
                userInfoUpdateRequestDto.setRealName(requestDto.getRealName());
                userInfoUpdateRequestDto.setCertificated(UserAuthStatusEnum.AUTHENTICATED.getValue());
                userInfoUpdateRequestDto.setCertificatedDate(now);
                try {
                    Gender gender = IDCardUtil.getGenderFromPersonIDCode(requestDto.getCardNo());
                    Date birthday = IDCardUtil.getBirthdayFromPersonIDCode(requestDto.getCardNo());
                    userInfoUpdateRequestDto.setGender(gender.getValue());
                    userInfoUpdateRequestDto.setBirthday(birthday);
                    applyAddRequestDto.setGender(gender.getValue());
                } catch (Throwable e) {
                    log.error("身份证号码错误" + e.getMessage());
                    return resultDto.makeResult(ToolResultEnum.ID_CARD_TYPE_ERROR.getValue(), ToolResultEnum.ID_CARD_TYPE_ERROR.getComment());
                }
                applyAddRequestDto.setAuthStatus(UserAuthStatusEnum.AUTHENTICATED.getValue());
                applyAddRequestDto.setAuthTime(now);
            } else {
                userInfoUpdateRequestDto.setCertificated(UserAuthStatusEnum.AUTHENTICATION_PENDING.getValue());
                applyAddRequestDto.setAuthStatus(UserAuthStatusEnum.AUTHENTICATION_PENDING.getValue());
            }
            userService.updateUserInfo(userInfoUpdateRequestDto);
            ResultDto insertUserAuthApply = userAuthenticationApplyService.insertUserAuthApply(applyAddRequestDto);
            if (insertUserAuthApply.isFailed()) {
                return resultDto.failed();
            } else {
                return resultDto.success();
            }
        } catch (Exception e) {
            log.error("系统自动审核实名认证申请失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
