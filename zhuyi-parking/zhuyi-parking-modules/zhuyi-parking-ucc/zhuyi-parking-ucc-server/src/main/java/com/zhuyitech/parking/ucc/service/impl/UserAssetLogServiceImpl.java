package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.common.enums.BizTypeAdEnum;
import com.zhuyitech.parking.ucc.domain.User;
import com.zhuyitech.parking.ucc.domain.UserAssetLog;
import com.zhuyitech.parking.ucc.domain.UserPayOrder;
import com.zhuyitech.parking.ucc.dto.request.assetlog.*;
import com.zhuyitech.parking.ucc.dto.result.assetlog.*;
import com.zhuyitech.parking.ucc.enums.AssetLogStatusEnum;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.service.UserAssetLogCrudService;
import com.zhuyitech.parking.ucc.service.UserCrudService;
import com.zhuyitech.parking.ucc.service.UserPayOrderCrudService;
import com.zhuyitech.parking.ucc.service.api.UserAssetLogService;
import com.zoeeasy.cloud.core.enums.BizTypeEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 收支明细服务
 *
 * @author zwq
 */
@Service("userAssetLogService")
@Slf4j
public class UserAssetLogServiceImpl implements UserAssetLogService {

    @Autowired
    private UserAssetLogCrudService userAssetLogCrudService;

    @Autowired
    private UserPayOrderCrudService userPayOrderCrudService;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 用户收支明细列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<AssetLogListResultDto> getList(AssetLogListRequestDto requestDto) {
        PagedResultDto<AssetLogListResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<UserAssetLog> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", requestDto.getSessionIdentity().getUserId());
            entityWrapper.eq("status", AssetLogStatusEnum.CONFORM.getValue());
            entityWrapper.orderBy("creationTime", false);
            Page<UserAssetLog> assetLogPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserAssetLog> userAssetLogPage = userAssetLogCrudService.selectPage(assetLogPage, entityWrapper);

            if (null != userAssetLogPage && null != userAssetLogPage.getRecords() && userAssetLogPage.getRecords().size() > 0) {

                List<AssetLogListResultDto> assetLogListResultDtoList
                        = modelMapper.map(userAssetLogPage.getRecords(), new TypeToken<List<AssetLogListResultDto>>() {
                }.getType());

                for (AssetLogListResultDto obj : assetLogListResultDtoList) {
                    obj.setAmount(obj.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                pagedResultDto.setPageNo(userAssetLogPage.getCurrent());
                pagedResultDto.setPageSize(userAssetLogPage.getSize());
                pagedResultDto.setTotalCount(userAssetLogPage.getTotal());
                pagedResultDto.setItems(assetLogListResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取用户收支明细列表失败" + e.getMessage());
            pagedResultDto.makeResult(UCenterResultEnum.GETLIST_ERR.getValue(), UCenterResultEnum.GETLIST_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 用户收支明细详情
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AssetLogDetailResultDto> assetLogDetail(AssetLogDetailRequestDto requestDto) {
        ObjectResultDto<AssetLogDetailResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getId()) {
                return objectResultDto.makeResult(UCenterResultEnum.ASSETLOGID_EMPTY.getValue(), UCenterResultEnum.ASSETLOGID_EMPTY.getComment());
            }
            EntityWrapper<UserAssetLog> entity = new EntityWrapper<>();
            entity.eq("userId", requestDto.getSessionIdentity().getUserId());
            entity.eq("id", requestDto.getId());
            UserAssetLog userAssetLog = userAssetLogCrudService.selectOne(entity);
            if (null == userAssetLog) {
                return objectResultDto.makeResult(UCenterResultEnum.ASSETLOG_NOT_EXIST.getValue(), UCenterResultEnum.ASSETLOG_NOT_EXIST.getComment());
            }
            AssetLogDetailResultDto assetLogDetailResultDto = modelMapper.map(userAssetLog, AssetLogDetailResultDto.class);
            assetLogDetailResultDto.setAmount(userAssetLog.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "元");
            if (userAssetLog.getBizType().equals(BizTypeEnum.RECHARGE.getValue())) {
                EntityWrapper<UserPayOrder> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("bizOrderType", BizTypeEnum.RECHARGE.getValue());
                entityWrapper.eq("bizOrderNo", userAssetLog.getBizNo());
                UserPayOrder userPayOrder = userPayOrderCrudService.selectOne(entityWrapper);
                if (null == userPayOrder) {
                    return objectResultDto.makeResult(UCenterResultEnum.RECHARGEORDER_NOTEXIST.getValue(), UCenterResultEnum.RECHARGEORDER_NOTEXIST.getComment());
                }
                assetLogDetailResultDto.setPayWay(userPayOrder.getPayWay());
            } else if (userAssetLog.getBizType().equals(BizTypeEnum.PAYMENT.getValue())) {
                assetLogDetailResultDto.setPayWay(PayWayEnum.PACKET.getValue());
            } else {
                return objectResultDto.makeResult(UCenterResultEnum.BIZTYPE_ERR.getValue(), UCenterResultEnum.BIZTYPE_ERR.getComment());
            }
            objectResultDto.setData(assetLogDetailResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户收支明细详情失败" + e.getMessage());
            objectResultDto.makeResult(UCenterResultEnum.GETDETAIL_ERR.getValue(), UCenterResultEnum.GETDETAIL_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 收支明细条件查询
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<AssetLogPageQueryResultDto> pageQuery(AssetLogPageQueryRequestDto requestDto) {
        PagedResultDto<AssetLogPageQueryResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<UserAssetLog> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getBizNo())) {
                entityWrapper.eq("bizNo", requestDto.getBizNo());
            }
            if (null != requestDto.getAmount()) {
                entityWrapper.eq("amount", requestDto.getAmount());
            }
            if (StringUtils.isNotEmpty(requestDto.getNickname()) && StringUtils.isNotEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.addFilter("userId = (select id from up_user where phoneNumber ='" + requestDto.getPhoneNumber() + "')");
            } else if (StringUtils.isNotEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.addFilter("userId = (select id from up_user where phoneNumber ='" + requestDto.getPhoneNumber() + "')");
            } else if (StringUtils.isNotEmpty(requestDto.getNickname())) {
                entityWrapper.addFilter("userId in(select id from up_user where nickname LIKE '%" + requestDto.getNickname() + "%')");
            }
            if (null != requestDto.getStartTime()) {
                entityWrapper.ge("creationTime", requestDto.getStartTime());
            }
            if (null != requestDto.getEndTime()) {
                entityWrapper.le("creationTime", requestDto.getEndTime());
            }
            if (null != requestDto.getBizType() && requestDto.getBizType().equals(BizTypeAdEnum.RECHARGE.getValue())) {
                entityWrapper.eq("bizType", BizTypeEnum.RECHARGE.getValue());
            }
            if (null != requestDto.getBizType() && requestDto.getBizType().equals(BizTypeAdEnum.PAYMENT.getValue())) {
                entityWrapper.andNew("bizType=" + BizTypeEnum.PAYMENT.getValue(), 0);
                entityWrapper.or("bizType=" + BizTypeEnum.APPOINTMENT.getValue(), 1);
            }
            Page<UserAssetLog> assetLogPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserAssetLog> userAssetLogPage = userAssetLogCrudService.selectPage(assetLogPage, entityWrapper);

            if (!(null == userAssetLogPage || userAssetLogPage.getRecords().isEmpty())) {

                List<AssetLogPageQueryResultDto> assetLogListResultDtoList
                        = modelMapper.map(userAssetLogPage.getRecords(), new TypeToken<List<AssetLogPageQueryResultDto>>() {
                }.getType());

                for (AssetLogPageQueryResultDto obj : assetLogListResultDtoList) {
                    User user = userCrudService.selectById(obj.getUserId());
                    obj.setOrderAmount(new BigDecimal(obj.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    if (null != user) {
                        obj.setNickname(user.getNickname());
                        obj.setPhoneNumber(user.getPhoneNumber());
                    }
                    if (obj.getBizType().equals(BizTypeEnum.RECHARGE.getValue())) {
                        EntityWrapper<UserPayOrder> entity = new EntityWrapper<>();
                        entity.eq("bizOrderType", BizTypeEnum.RECHARGE.getValue());
                        entity.eq("bizOrderNo", obj.getBizNo());
                        UserPayOrder userPayOrder = userPayOrderCrudService.selectOne(entity);
                        if (null != userPayOrder) {
                            obj.setPayWay(userPayOrder.getPayWay());
                        }
                    } else if (obj.getBizType().equals(BizTypeEnum.PAYMENT.getValue())) {
                        obj.setPayWay(PayWayEnum.PACKET.getValue());
                    }
                }
                pagedResultDto.setPageNo(userAssetLogPage.getCurrent());
                pagedResultDto.setPageSize(userAssetLogPage.getSize());
                pagedResultDto.setTotalCount(userAssetLogPage.getTotal());
                pagedResultDto.setItems(assetLogListResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取钱包账单列表失败" + e.getMessage());
            pagedResultDto.makeResult(UCenterResultEnum.GETLIST_ERR.getValue(), UCenterResultEnum.GETLIST_ERR.getComment());
        }
        return pagedResultDto;
    }


    /**
     * 钱包账单详情
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<WalletDetailResultDto> walletDetail(WalletDetailRequestDto requestDto) {
        ObjectResultDto<WalletDetailResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getId()) {
                return objectResultDto.makeResult(UCenterResultEnum.ASSETLOGID_EMPTY.getValue(), UCenterResultEnum.ASSETLOGID_EMPTY.getComment());
            }
            EntityWrapper<UserAssetLog> entity = new EntityWrapper<>();
            entity.eq("id", requestDto.getId());
            UserAssetLog userAssetLog = userAssetLogCrudService.selectOne(entity);
            if (null == userAssetLog) {
                return objectResultDto.makeResult(UCenterResultEnum.ASSETLOG_NOT_EXIST.getValue(), UCenterResultEnum.ASSETLOG_NOT_EXIST.getComment());
            }
            WalletDetailResultDto walletDetailResultDto = modelMapper.map(userAssetLog, WalletDetailResultDto.class);
            walletDetailResultDto.setOrderAmount(new BigDecimal(String.valueOf(userAssetLog.getAmount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            if (userAssetLog.getBizType().equals(BizTypeEnum.RECHARGE.getValue())) {
                EntityWrapper<UserPayOrder> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("bizOrderType", BizTypeEnum.RECHARGE.getValue());
                entityWrapper.eq("bizOrderNo", userAssetLog.getBizNo());
                UserPayOrder userPayOrder = userPayOrderCrudService.selectOne(entityWrapper);
                if (null == userPayOrder) {
                    return objectResultDto.makeResult(UCenterResultEnum.RECHARGEORDER_NOTEXIST.getValue(), UCenterResultEnum.RECHARGEORDER_NOTEXIST.getComment());
                }
                walletDetailResultDto.setPayWay(userPayOrder.getPayWay());
                walletDetailResultDto.setPayTime(userPayOrder.getPayTime());
                walletDetailResultDto.setPayStatus(userPayOrder.getPayStatus());
            } else if (userAssetLog.getBizType().equals(BizTypeEnum.PAYMENT.getValue()) || userAssetLog.getBizType().equals(BizTypeEnum.APPOINTMENT.getValue())) {
                walletDetailResultDto.setPayWay(PayWayEnum.PACKET.getValue());
                walletDetailResultDto.setPayTime(userAssetLog.getCreationTime());
                walletDetailResultDto.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
            } else {
                return objectResultDto.makeResult(UCenterResultEnum.BIZTYPE_ERR.getValue(), UCenterResultEnum.BIZTYPE_ERR.getComment());
            }
            User user = userCrudService.selectById(userAssetLog.getUserId());
            if (null != user) {
                walletDetailResultDto.setNickname(user.getNickname());
                walletDetailResultDto.setPhoneNumber(user.getPhoneNumber());
            } else {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            objectResultDto.setData(walletDetailResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户收支明细详情失败" + e.getMessage());
            objectResultDto.makeResult(UCenterResultEnum.GETDETAIL_ERR.getValue(), UCenterResultEnum.GETDETAIL_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 添加备注
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addRemark(AssetLogAddRemarkRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getId()) {
                return resultDto.makeResult(UCenterResultEnum.ASSETLOGID_EMPTY.getValue(), UCenterResultEnum.ASSETLOGID_EMPTY.getComment());
            }
            UserAssetLog userAssetLog = new UserAssetLog();
            if (StringUtils.isNotEmpty(requestDto.getRemark())) {
                userAssetLog.setRemark(requestDto.getRemark());
            } else {
                userAssetLog.setRemark("");
            }
            EntityWrapper<UserAssetLog> entity = new EntityWrapper<>();
            entity.eq("id", requestDto.getId());
            userAssetLogCrudService.update(userAssetLog, entity);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取用户收支明细详情失败" + e.getMessage());
            resultDto.makeResult(UCenterResultEnum.UPDATE_REMARK_ERR.getValue(), UCenterResultEnum.UPDATE_REMARK_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 根据订单查询
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AssetLogGetByBizNoResultDto> getByBizNo(GetByBizNoRequestDto requestDto) {
        ObjectResultDto<AssetLogGetByBizNoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isNotEmpty(requestDto.getBizNo())) {
                EntityWrapper<UserAssetLog> entity = new EntityWrapper<>();
                entity.eq("bizNo", requestDto.getBizNo());
                UserAssetLog userAssetLog = userAssetLogCrudService.selectOne(entity);
                AssetLogGetByBizNoResultDto getByBizNoResultDto = modelMapper.map(userAssetLog, AssetLogGetByBizNoResultDto.class);
                objectResultDto.setData(getByBizNoResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("拉取用户收支明细失败" + e.getMessage());
            objectResultDto.makeResult(UCenterResultEnum.GETLIST_ERR.getValue(), UCenterResultEnum.GETLIST_ERR.getComment());
        }
        return objectResultDto;
    }
}
