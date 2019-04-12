package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.common.enums.Gender;
import com.zhuyitech.parking.common.utils.IDCardUtil;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.ucc.domain.User;
import com.zhuyitech.parking.ucc.domain.UserAuthApply;
import com.zhuyitech.parking.ucc.domain.UserInfo;
import com.zhuyitech.parking.ucc.dto.request.realname.*;
import com.zhuyitech.parking.ucc.dto.result.UserAuthApplyResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserAuthStatusResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserRealNameAuthApplyResultDto;
import com.zhuyitech.parking.ucc.enums.*;
import com.zhuyitech.parking.ucc.service.UserAuthApplyCrudService;
import com.zhuyitech.parking.ucc.service.UserCrudService;
import com.zhuyitech.parking.ucc.service.UserInfoCrudService;
import com.zhuyitech.parking.ucc.service.api.UserAuthenticationApplyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户实名认证信息维护
 */
@Service("userAuthenticationApplyService")
@Slf4j
public class UserAuthenticationApplyServiceImpl implements UserAuthenticationApplyService {

    @Autowired
    private UserAuthApplyCrudService userAuthApplyCrudService;

    @Autowired
    private UserInfoCrudService userInfoCrudService;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 修改用户实名认证信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateAuthApply(CurrentUserAuthApplyUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserInfo userInfo = userInfoCrudService.findByUserId(userId);
            if (userInfo == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            if (userInfo.getCertificateStatus().equals(UserAuthStatusEnum.AUTHENTICATED.getValue())) {
                return resultDto.makeResult(UCenterResultEnum.USER_ALREADY_AUTHENTICATED.getValue(),
                        UCenterResultEnum.USER_ALREADY_AUTHENTICATED.getComment());
            }
            UserAuthApply userAuthApply = userAuthApplyCrudService.selectById(requestDto.getId());
            if (userAuthApply == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_AUTHENTICATE_NOT_FOUND.getComment());
            }
            //只有审批驳回的允许用户再次修改
            if (!userAuthApply.getAuthStatus().equals(UserAuthStatusEnum.AUTHENTICATION_REJECT.getValue())) {
                return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getValue(),
                        UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getComment());
            }
            userAuthApply.setRealName(requestDto.getRealName());
            userAuthApply.setCardNo(requestDto.getCardNo());
            userAuthApply.setCardFront(requestDto.getCardFront());
            userAuthApply.setCardContrary(requestDto.getCardContrary());
            userAuthApply.setAuthStatus(UserAuthStatusEnum.AUTHENTICATION_PENDING.getValue());
            userAuthApply.setLastModifierUserId(userId);
            userAuthApplyCrudService.updateById(userAuthApply);
            return resultDto.success();
        } catch (Exception e) {
            log.error("实名认证申请失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取实名认证申请
     */
    @Override
    public ObjectResultDto<UserAuthApplyResultDto> getUserAuthApply(UserAuthApplyGetRequestDto requestDto) {
        ObjectResultDto<UserAuthApplyResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            UserAuthApply userAuthApply = userAuthApplyCrudService.selectById(requestDto.getId());
            if (userAuthApply != null) {
                UserAuthApplyResultDto userAuthResultDto = modelMapper.map(userAuthApply, UserAuthApplyResultDto.class);
                objectResultDto.setData(userAuthResultDto);
                return objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取实名认证申请失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取当前用户实名认证申请信息
     */
    @Override
    public ObjectResultDto<UserRealNameAuthApplyResultDto> getCurrentUserAuthApply(CurrentUserAuthApplyGetRequestDto requestDto) {
        ObjectResultDto<UserRealNameAuthApplyResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            User user = userCrudService.selectById(userId);
            if (null == user) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            UserRealNameAuthApplyResultDto authApplyResultDto = new UserRealNameAuthApplyResultDto();
            UserAuthApply userAuthApply = userAuthApplyCrudService.findByUserId(userId);
            if (null == userAuthApply) {
                authApplyResultDto.setHasRealAuth(Boolean.FALSE);
            } else {
                authApplyResultDto.setRealName(userAuthApply.getRealName());
                authApplyResultDto.setCardNo(userAuthApply.getCardNo());
                authApplyResultDto.setPortrait(user.getPortrait());
                authApplyResultDto.setAuthStatus(userAuthApply.getAuthStatus());
                authApplyResultDto.setHasRealAuth(Boolean.TRUE);
                if (userAuthApply.getAuthStatus().compareTo(UserAuthStatusEnum.AUTHENTICATION_REJECT.getValue()) == 0) {
                    authApplyResultDto.setRejectReason(userAuthApply.getRemark());
                }
            }
            resultDto.setData(authApplyResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取实名认证申请失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取实名状态
     */
    @Override
    public ObjectResultDto<UserAuthStatusResultDto> getCurrentUserAuthStatus(CurrentUserAuthStatusGetRequestDto requestDto) {
        ObjectResultDto<UserAuthStatusResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserInfo userInfo = userInfoCrudService.findByUserId(userId);
            if (userInfo == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            UserAuthStatusResultDto authStatusResultDto = new UserAuthStatusResultDto();
            authStatusResultDto.setAuthStatus(userInfo.getCertificateStatus());
            resultDto.setData(authStatusResultDto);
            return resultDto;
        } catch (Exception e) {
            log.error("获取实名状态失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 实名认证申请审核
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto approveAuthApply(UserAuthApproveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserAuthApply userAuthApply = userAuthApplyCrudService.selectById(requestDto.getId());
            if (userAuthApply == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_AUTHENTICATE_NOT_FOUND.getComment());
            }
            //只有认证中的允许审批
            if (!userAuthApply.getAuthStatus().equals(UserAuthStatusEnum.AUTHENTICATION_PENDING.getValue())) {
                return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_STATUS_INVALID.getValue(),
                        UCenterResultEnum.USER_AUTHENTICATE_STATUS_INVALID.getComment());
            }
            UserInfo userInfo = userInfoCrudService.findByUserId(userAuthApply.getUserId());
            if (userInfo == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            if (userInfo.getCertificateStatus().equals(UserAuthStatusEnum.AUTHENTICATED.getValue())) {
                return resultDto.makeResult(UCenterResultEnum.USER_ALREADY_AUTHENTICATED.getValue(),
                        UCenterResultEnum.USER_ALREADY_AUTHENTICATED.getComment());
            }
            Long currentUserId = requestDto.getSessionIdentity().getUserId();
            Date now = new Date();
            if (requestDto.getApproveOpinion().equals(UserAuthOpinionEnum.APPROVED.getValue())) {
                if (!StringUtils.isEmpty(userAuthApply.getCardNo())) {
                    userInfo.setCardNo(userAuthApply.getCardNo());
                } else {
                    if (StringUtils.isEmpty(requestDto.getCardNo())) {
                        return resultDto.makeResult(UCenterResultEnum.USER_CARD_NO_EMPTY.getValue(), UCenterResultEnum.USER_CARD_NO_EMPTY.getComment());
                    }
                    if (!IDCardUtil.isIdentity(requestDto.getCardNo())) {
                        return resultDto.makeResult(ToolResultEnum.ID_CARD_TYPE_ERROR.getValue(), ToolResultEnum.ID_CARD_TYPE_ERROR.getComment());
                    }
                    userInfo.setCardNo(requestDto.getCardNo());
                    userAuthApply.setCardNo(requestDto.getCardNo());
                }
                if (!StringUtils.isEmpty(userAuthApply.getRealName())) {
                    userInfo.setRealName(userAuthApply.getRealName());
                } else {
                    if (StringUtils.isEmpty(requestDto.getRealName())) {
                        return resultDto.makeResult(UCenterResultEnum.USER_REAL_NAME_EMPTY.getValue(), UCenterResultEnum.USER_REAL_NAME_EMPTY.getComment());
                    }
                    userInfo.setRealName(requestDto.getRealName());
                    userAuthApply.setRealName(requestDto.getRealName());
                }
                userInfo.setCertificateStatus(UserAuthStatusEnum.AUTHENTICATED.getValue());
                userInfo.setCertificatedDate(now);
                userInfo.setLastModifierUserId(currentUserId);
                try {
                    Gender gender;
                    Date birthday;
                    if (StringUtils.isEmpty(userAuthApply.getCardNo())) {
                        gender = IDCardUtil.getGenderFromPersonIDCode(requestDto.getCardNo());
                        birthday = IDCardUtil.getBirthdayFromPersonIDCode(requestDto.getCardNo());
                    } else {
                        gender = IDCardUtil.getGenderFromPersonIDCode(userAuthApply.getCardNo());
                        birthday = IDCardUtil.getBirthdayFromPersonIDCode(userAuthApply.getCardNo());
                    }
                    userInfo.setGender(gender.getValue());
                    userInfo.setBirthday(birthday);
                    userAuthApply.setGender(gender.getValue());
                } catch (Throwable e) {
                    log.error("身份证号码错误" + e.getMessage());
                    return resultDto.makeResult(ToolResultEnum.ID_CARD_TYPE_ERROR.getValue(), ToolResultEnum.ID_CARD_TYPE_ERROR.getComment());
                }
                userInfoCrudService.updateById(userInfo);
                userAuthApply.setAuthStatus(UserAuthStatusEnum.AUTHENTICATED.getValue());
                userAuthApply.setAuthUserId(currentUserId);
                userAuthApply.setAuthTime(now);
                userAuthApply.setRemark(requestDto.getRemarks());
                userAuthApply.setLastModifierUserId(currentUserId);
                userAuthApplyCrudService.updateById(userAuthApply);
                resultDto.success();
            } else if (requestDto.getApproveOpinion().equals(UserAuthOpinionEnum.REJECTED.getValue())) {
                if (StringUtils.isEmpty(requestDto.getRemarks())) {
                    return resultDto.makeResult(UCenterResultEnum.USER_REAL_NAME_AUTH_REJECT_REASON_EMPTY.getValue(), UCenterResultEnum.USER_REAL_NAME_AUTH_REJECT_REASON_EMPTY.getComment());
                }
                userInfo.setCertificateStatus(UserAuthStatusEnum.AUTHENTICATION_REJECT.getValue());
                userInfoCrudService.updateById(userInfo);
                userAuthApply.setAuthUserId(currentUserId);
                userAuthApply.setAuthTime(now);
                userAuthApply.setAuthStatus(UserAuthStatusEnum.AUTHENTICATION_REJECT.getValue());
                userAuthApply.setRemark(requestDto.getRemarks());
                userAuthApply.setLastModifierUserId(currentUserId);
                userAuthApplyCrudService.updateById(userAuthApply);
                resultDto.success();
            } else {
                resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_STATUS_INVALID.getValue(),
                        UCenterResultEnum.USER_AUTHENTICATE_STATUS_INVALID.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("实名认证审核失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取实名认证申请列表
     */
    @Override
    public ListResultDto<UserAuthApplyResultDto> getAuthApplyList(UserAuthApplyListGetRequestDto requestDto) {
        ListResultDto<UserAuthApplyResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<UserAuthApply> entityWrapper = new EntityWrapper<>();
            if (requestDto.getId() != null) {
                entityWrapper.eq("id", requestDto.getId());
            }
            if (!StringUtils.isEmpty(requestDto.getRealName())) {
                entityWrapper.eq("realName", requestDto.getRealName());
            }
            if (!StringUtils.isEmpty(requestDto.getCardNo())) {
                entityWrapper.eq("cardNo", requestDto.getCardNo());
            }
            if (requestDto.getApplyTimeStart() != null) {
                entityWrapper.ge("applyTime", requestDto.getApplyTimeStart());
            }
            if (requestDto.getApplyTimeEnd() != null) {
                entityWrapper.le("applyTime", requestDto.getApplyTimeEnd());
            }
            entityWrapper.orderBy("applyTime", false);
            entityWrapper.orderBy("creationTime", false);
            entityWrapper.orderBy("LastModificationTime", false);
            List<UserAuthApply> authApplyList = userAuthApplyCrudService.selectList(entityWrapper);
            if (!authApplyList.isEmpty()) {
                List<UserAuthApplyResultDto> authApplyResultDtoList = modelMapper.map(authApplyList, new TypeToken<List<UserAuthApplyResultDto>>() {
                }.getType());
                listResultDto.setItems(authApplyResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取实名认证申请失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页获取实名认证申请列表
     */
    @Override
    public PagedResultDto<UserAuthApplyResultDto> getAuthApplyPagedList(UserAuthApplyQueryPagedResultRequestDto requestDto) {

        PagedResultDto<UserAuthApplyResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<UserAuthApply> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getRealName())) {
                entityWrapper.eq("realName", requestDto.getRealName());
            }
            if (!StringUtils.isEmpty(requestDto.getCardNo())) {
                entityWrapper.eq("cardNo", requestDto.getCardNo());
            }
            if (requestDto.getAuthStatus() != null) {
                entityWrapper.eq("authStatus", requestDto.getAuthStatus());
            }
            if (requestDto.getApplyTimeStart() != null) {
                entityWrapper.ge("applyTime", requestDto.getApplyTimeStart());
            }
            if (requestDto.getApplyTimeEnd() != null) {
                entityWrapper.le("applyTime", requestDto.getApplyTimeEnd());
            }
            entityWrapper.orderBy("applyTime", false);
            entityWrapper.orderBy("creationTime", false);
            entityWrapper.orderBy("LastModificationTime", false);
            Page<UserAuthApply> authApplyPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserAuthApply> authApplyPageList = userAuthApplyCrudService.selectPage(authApplyPage, entityWrapper);
            if (authApplyPageList != null) {

                List<UserAuthApplyResultDto> userAuthApplyDtoList = modelMapper.map(authApplyPageList.getRecords(), new TypeToken<List<UserAuthApplyResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(authApplyPageList.getCurrent());
                pagedResultDto.setPageSize(authApplyPageList.getSize());
                pagedResultDto.setTotalCount(authApplyPageList.getTotal());
                pagedResultDto.setItems(userAuthApplyDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取实名认证申请列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 根据用户Id获取用户实名认证申请
     */
    @Override
    public ObjectResultDto<UserAuthApplyResultDto> getUserAuthApplyByUserId(UserAuthApplyGetByUserIdRequestDto requestDto) {
        ObjectResultDto<UserAuthApplyResultDto> resultDto = new ObjectResultDto<>();
        try {
            UserAuthApply userAuthApply = userAuthApplyCrudService.findByUserId(requestDto.getSessionIdentity().getUserId());
            if (null != userAuthApply) {
                UserAuthApplyResultDto userAuthApplyResultDto = modelMapper.map(userAuthApply, UserAuthApplyResultDto.class);
                resultDto.setData(userAuthApplyResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("根据用户Id获取用户实名认证申请失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 添加用户认证申请
     */
    @Override
    public ResultDto insertUserAuthApply(UserAuthApplyInsertRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserAuthApply userAuthApply = modelMapper.map(requestDto, UserAuthApply.class);
            userAuthApply.setLastModifierUserId(requestDto.getSessionIdentity().getUserId());
            boolean insert = userAuthApplyCrudService.insert(userAuthApply);
            if (!insert) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("添加用户认证申请失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户实名认证驳回原因
     */
    @Override
    public ListResultDto<ComboboxItemDto> getRejectReason() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(RealNameAuthRejectReasonEnum.NAME_NOT_COINCIDE.getValue(), RealNameAuthRejectReasonEnum.NAME_NOT_COINCIDE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(RealNameAuthRejectReasonEnum.CARD_NUMBER_NOT_COINCIDE.getValue(), RealNameAuthRejectReasonEnum.CARD_NUMBER_NOT_COINCIDE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(RealNameAuthRejectReasonEnum.CARD_IMAGE_ERROR.getValue(), RealNameAuthRejectReasonEnum.CARD_IMAGE_ERROR.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(RealNameAuthRejectReasonEnum.IMAGE_INDISTINCT.getValue(), RealNameAuthRejectReasonEnum.IMAGE_INDISTINCT.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(RealNameAuthRejectReasonEnum.FACE_NOT_MATCH_IMAGE.getValue(), RealNameAuthRejectReasonEnum.FACE_NOT_MATCH_IMAGE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(RealNameAuthRejectReasonEnum.OTHER.getValue(), RealNameAuthRejectReasonEnum.OTHER.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户实名认证驳回原因失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

}
