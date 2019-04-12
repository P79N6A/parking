package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.ucc.domain.UserPayOrder;
import com.zhuyitech.parking.ucc.domain.UserRecharge;
import com.zhuyitech.parking.ucc.dto.request.pay.UserPayOrderGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.pay.UserPayOrderListGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.pay.UserPayOrderQueryPageGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.recharge.UserRechargeRecordGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.recharge.UserRechargeRecordListGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.recharge.UserRechargeRecordQueryPageRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserPayOrderResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserRechargeRecordResultDto;
import com.zhuyitech.parking.ucc.service.UserPayOrderCrudService;
import com.zhuyitech.parking.ucc.service.UserRechargeCrudService;
import com.zhuyitech.parking.ucc.service.api.UserTransactionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户交易服务
 */
@Service("userTransactionService")
public class UserTransactionServiceImpl implements UserTransactionService {

    private static final Logger LOG = LoggerFactory.getLogger(UserTransactionServiceImpl.class);

    @Autowired
    private UserRechargeCrudService userRechargeCrudService;

    @Autowired
    private UserPayOrderCrudService userPayOrderCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 分页获取充值记录
     *
     * @param requestDto 分页获取充值记录请求参数
     * @return
     */
    @Override
    public PagedResultDto<UserRechargeRecordResultDto> getUserRechargePagedList(UserRechargeRecordQueryPageRequestDto requestDto) {
        PagedResultDto<UserRechargeRecordResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<UserRecharge> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getOrderUuid())) {
                entityWrapper.eq("orderUuid", requestDto.getOrderUuid());
            }
            if (!StringUtils.isEmpty(requestDto.getOrderNo())) {
                entityWrapper.eq("orderNo", requestDto.getOrderNo());
            }
            if (!StringUtils.isEmpty(requestDto.getPayOrderNo())) {
                entityWrapper.eq("payOrderNo", requestDto.getPayOrderNo());
            }
            if (requestDto.getRechargeType() != null) {
                entityWrapper.eq("rechargeType", requestDto.getRechargeType());
            }
            if (requestDto.getRechargeChannel() != null) {
                entityWrapper.eq("rechargeChannel", requestDto.getRechargeChannel());
            }
            if (requestDto.getRechargeTime() != null) {
                entityWrapper.eq("rechargeTime", requestDto.getRechargeTime());
            }
            if (requestDto.getRechargeStatus() != null) {
                entityWrapper.eq("rechargeStatus", requestDto.getRechargeStatus());
            }
            if (requestDto.getSucceedTime() != null) {
                entityWrapper.eq("succeedTime", requestDto.getSucceedTime());
            }
            entityWrapper.orderBy("rechargeTime", false);
            Page<UserRecharge> userRecharge = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserRecharge> userRechargePage = userRechargeCrudService.selectPage(userRecharge, entityWrapper);
            if (userRechargePage != null) {
                List<UserRechargeRecordResultDto> userRechargeRecordResultDtos = modelMapper.map(userRechargePage.getRecords(), new TypeToken<List<UserRechargeRecordResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(userRechargePage.getCurrent());
                pagedResultDto.setPageSize(userRechargePage.getSize());
                pagedResultDto.setTotalCount(userRechargePage.getTotal());
                pagedResultDto.setItems(userRechargeRecordResultDtos);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            LOG.error("分页获取充值记录失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取充值记录列表
     *
     * @param requestDto 获取充值记录列表请求参数
     * @return ListResultDto
     */
    @Override
    public ListResultDto<UserRechargeRecordResultDto> getUserRechargeList(UserRechargeRecordListGetRequestDto requestDto) {
        ListResultDto<UserRechargeRecordResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<UserRecharge> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getOrderUuid())) {
                entityWrapper.eq("orderUuid", requestDto.getOrderUuid());
            }
            if (!StringUtils.isEmpty(requestDto.getOrderNo())) {
                entityWrapper.eq("orderNo", requestDto.getOrderNo());
            }
            if (!StringUtils.isEmpty(requestDto.getPayOrderNo())) {
                entityWrapper.eq("payOrderNo", requestDto.getPayOrderNo());
            }
            if (requestDto.getRechargeType() != null) {
                entityWrapper.eq("rechargeType", requestDto.getRechargeType());
            }
            if (requestDto.getRechargeChannel() != null) {
                entityWrapper.eq("rechargeChannel", requestDto.getRechargeChannel());
            }
            if (requestDto.getRechargeTime() != null) {
                entityWrapper.eq("rechargeTime", requestDto.getRechargeTime());
            }
            if (requestDto.getRechargeStatus() != null) {
                entityWrapper.eq("rechargeStatus", requestDto.getRechargeStatus());
            }
            if (requestDto.getSucceedTime() != null) {
                entityWrapper.eq("succeedTime", requestDto.getSucceedTime());
            }
            entityWrapper.orderBy("rechargeTime", false);
            List<UserRecharge> userRecharges = userRechargeCrudService.selectList(entityWrapper);
            if (!userRecharges.isEmpty()) {
                List<UserRechargeRecordResultDto> userRechargeRecordResultDtos = modelMapper.map(userRecharges, new TypeToken<List<UserRechargeRecordResultDto>>() {
                }.getType());
                listResultDto.setItems(userRechargeRecordResultDtos);
                listResultDto.success();
            }
        } catch (Exception e) {
            LOG.error("获取充值记录列表失败" + e.getMessage());
        }
        return listResultDto;
    }

    /**
     * 获取充值记录
     *
     * @param requestDto 获取充值记录请求参数
     * @return ObjectResultDto
     */
    @Override
    public ObjectResultDto<UserRechargeRecordResultDto> getUserRecharge(UserRechargeRecordGetRequestDto requestDto) {
        ObjectResultDto<UserRechargeRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<UserRecharge> entityWrapper = new EntityWrapper<>();
            if (requestDto.getId() != null) {
                entityWrapper.eq("id", requestDto.getId());
            }
            if (requestDto.getSessionIdentity() != null) {
                entityWrapper.eq("userId", requestDto.getSessionIdentity().getUserId());
            }
            if (StringUtils.isNotEmpty(requestDto.getOrderNo())) {
                entityWrapper.eq("orderNo", requestDto.getOrderNo());
            }
            if (StringUtils.isNotEmpty(requestDto.getOrderUuid())) {
                entityWrapper.eq("orderUuid", requestDto.getOrderUuid());
            }
            UserRecharge userRecharge = userRechargeCrudService.selectOne(entityWrapper);
            if (userRecharge != null) {
                UserRechargeRecordResultDto userRechargeRecordResultDto = modelMapper.map(userRecharge, UserRechargeRecordResultDto.class);
                objectResultDto.setData(userRechargeRecordResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            LOG.error("获取充值记录失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 获取支付记录
     *
     * @param requestDto 获取支付记录请求参数
     * @return ObjectResultDto
     */
    @Override
    public ObjectResultDto<UserPayOrderResultDto> getUserPay(UserPayOrderGetRequestDto requestDto) {
        ObjectResultDto<UserPayOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<UserPayOrder> entityWrapper = new EntityWrapper<>();
            if (requestDto.getId() != null) {
                entityWrapper.eq("id", requestDto.getId());
            }
            UserPayOrder userPayOrder = userPayOrderCrudService.selectOne(entityWrapper);
            if (userPayOrder != null) {
                UserPayOrderResultDto userPayOrderResultDto = modelMapper.map(userPayOrder, UserPayOrderResultDto.class);
                objectResultDto.setData(userPayOrderResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            LOG.error("获取支付记录失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 获取支付记录列表
     *
     * @param requestDto 获取支付记录列表请求参数
     * @return ListResultDto
     */
    @Override
    public ListResultDto<UserPayOrderResultDto> getUserPayList(UserPayOrderListGetRequestDto requestDto) {
        ListResultDto<UserPayOrderResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<UserPayOrder> entityWrapper = new EntityWrapper<>();
            if (requestDto.getUserId() != null) {
                entityWrapper.eq("userId", requestDto.getUserId());
            }
            if (!StringUtils.isEmpty(requestDto.getOrderUuid())) {
                entityWrapper.eq("orderUuid", requestDto.getOrderUuid());
            }
            if (!StringUtils.isEmpty(requestDto.getOrderNo())) {
                entityWrapper.eq("orderNo", requestDto.getOrderNo());
            }
            if (!StringUtils.isEmpty(requestDto.getBizOrderNo())) {
                entityWrapper.eq("bizOrderNo", requestDto.getBizOrderNo());
            }
            if (requestDto.getOrderType() != null) {
                entityWrapper.eq("orderType", requestDto.getOrderType());
            }
            if (requestDto.getPayType() != null) {
                entityWrapper.eq("payType", requestDto.getPayType());
            }
            if (requestDto.getPayStatus() != null) {
                entityWrapper.eq("payStatus", requestDto.getPayStatus());
            }
            if (requestDto.getPayTime() != null) {
                entityWrapper.eq("payTime", requestDto.getPayTime());
            }
            if (requestDto.getSucceedPayTime() != null) {
                entityWrapper.eq("succeedPayTime", requestDto.getSucceedPayTime());
            }
            entityWrapper.orderBy("payTime", false);
            List<UserPayOrder> userPayOrders = userPayOrderCrudService.selectList(entityWrapper);
            if (!userPayOrders.isEmpty()) {
                List<UserPayOrderResultDto> userPayOrderResultDtoList = modelMapper.map(userPayOrders, new TypeToken<List<UserPayOrderResultDto>>() {
                }.getType());
                listResultDto.setItems(userPayOrderResultDtoList);
                listResultDto.success();
            }
        } catch (Exception e) {
            LOG.error("获取充值记录列表失败" + e.getMessage());
        }
        return listResultDto;
    }

    /**
     * 分页获取支付记录列表
     *
     * @param requestDto 分页获取支付记录列表请求参数
     * @return PagedResultDto
     */
    @Override
    public PagedResultDto<UserPayOrderResultDto> getUserPayPagedList(UserPayOrderQueryPageGetRequestDto requestDto) {
        PagedResultDto<UserPayOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<UserPayOrder> entityWrapper = new EntityWrapper<>();
            if (requestDto.getUserId() != null) {
                entityWrapper.eq("userId", requestDto.getUserId());
            }
            if (!StringUtils.isEmpty(requestDto.getOrderUuid())) {
                entityWrapper.eq("orderUuid", requestDto.getOrderUuid());
            }
            if (!StringUtils.isEmpty(requestDto.getOrderNo())) {
                entityWrapper.eq("orderNo", requestDto.getOrderNo());
            }
            if (!StringUtils.isEmpty(requestDto.getBizOrderNo())) {
                entityWrapper.eq("bizOrderNo", requestDto.getBizOrderNo());
            }
            if (requestDto.getBizOrderType() != null) {
                entityWrapper.eq("bizOrderType", requestDto.getBizOrderType());
            }
            if (requestDto.getPayWay() != null) {
                entityWrapper.eq("payWay", requestDto.getPayWay());
            }
            if (requestDto.getPayAmount() != null) {
                entityWrapper.eq("payAmount", requestDto.getPayAmount());
            }
            if (requestDto.getPayAmountActual() != null) {
                entityWrapper.eq("payAmountActual", requestDto.getPayAmountActual());
            }
            if (requestDto.getPayStatus() != null) {
                entityWrapper.eq("payStatus", requestDto.getPayStatus());
            }
            if (requestDto.getPayTime() != null) {
                entityWrapper.eq("payTime", requestDto.getPayTime());
            }
            if (requestDto.getSucceedPayTime() != null) {
                entityWrapper.eq("succeedPayTime", requestDto.getSucceedPayTime());
            }
            entityWrapper.orderBy("payTime", false);
            Page<UserPayOrder> userPayOrder = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserPayOrder> userPayOrderPage = userPayOrderCrudService.selectPage(userPayOrder, entityWrapper);
            if (userPayOrderPage != null) {
                List<UserPayOrderResultDto> userPayOrderResultDtoList = modelMapper.map(userPayOrderPage.getRecords(), new TypeToken<List<UserPayOrderResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(userPayOrderPage.getCurrent());
                pagedResultDto.setPageSize(userPayOrderPage.getSize());
                pagedResultDto.setTotalCount(userPayOrderPage.getTotal());
                pagedResultDto.setItems(userPayOrderResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            LOG.error("分页获取充值记录失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取充值记录状态选择项
     */
    @Override
    public ListResultDto<ComboboxItemDto> getRechargeStatusComboboxList() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("1", "等待支付", false));
            itemDtoList.add(new ComboboxItemDto("2", "充值中", false));
            itemDtoList.add(new ComboboxItemDto("3", "充值关闭", false));
            itemDtoList.add(new ComboboxItemDto("4", "支付成功", false));
            itemDtoList.add(new ComboboxItemDto("5", "充值完成", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取充值记录状态失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }
}
