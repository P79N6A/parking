package com.zoeeasy.cloud.order.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.core.enums.AppointStatusEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.AppointOrderClosePayload;
import com.zoeeasy.cloud.order.appoint.PlatformAppointOrderService;
import com.zoeeasy.cloud.order.appoint.dto.request.*;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderCreateResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderHadResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.domain.ParkingAppointmentOrderEntity;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.service.ParkingAppointmentOrderCrudService;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/1 0001
 */
@Service("platformAppointOrderService")
@Slf4j
public class PlatformAppointOrderServiceImpl implements PlatformAppointOrderService {

    @Autowired
    private ParkingAppointmentOrderCrudService parkingAppointmentOrderCrudService;

    @Autowired
    private IdService idService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingAppointmentOrderResultDto> getAppointOrderList(ParkingAppointOrderListGetRequestDto requestDto) {
        PagedResultDto<ParkingAppointmentOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            if (null != requestDto.getCustomerUserId()) {
                entityWrapper.eq("customerUserId", requestDto.getCustomerUserId());
            }
            if (null != requestDto.getParkingId()) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (null != requestDto.getParkingLotId()) {
                entityWrapper.eq("parkingLotId", requestDto.getParkingLotId());
            }
            if (StringUtils.isNotEmpty(requestDto.getOrderNo())) {
                entityWrapper.eq("orderNo", requestDto.getOrderNo());
            }
            if (StringUtils.isNotEmpty(requestDto.getPlateNumber())) {
                entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            }
            if (null != requestDto.getPlateColor()) {
                entityWrapper.eq("plateColor", requestDto.getPlateColor());
            }
            if (null != requestDto.getStartOrderTime()) {
                entityWrapper.ge("creationTime", requestDto.getStartOrderTime());
            }
            if (null != requestDto.getEndOrderTime()) {
                entityWrapper.le("creationTime", requestDto.getEndOrderTime());
            }
            entityWrapper.orderBy("creationTime", false);
            List<ParkingAppointmentOrderEntity> parkingAppointmentOrderEntityList = parkingAppointmentOrderCrudService.selectAppointOrderList(entityWrapper);
            if (CollectionUtils.isNotEmpty(parkingAppointmentOrderEntityList)) {

                List<ParkingAppointmentOrderResultDto> appointOrderResultDtoList
                        = modelMapper.map(parkingAppointmentOrderEntityList, new TypeToken<List<ParkingAppointmentOrderResultDto>>() {
                }.getType());
                pagedResultDto.setItems(appointOrderResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("预约订单列表获取失败" + e.getMessage());
            pagedResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 分页获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingAppointmentOrderResultDto> getAppointOrderPagedList(ParkingAppointOrderPagedResultRequestDto requestDto) {
        PagedResultDto<ParkingAppointmentOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            if (null != requestDto.getCustomerUserId()) {
                entityWrapper.eq("customerUserId", requestDto.getCustomerUserId());
            }
            if (null != requestDto.getParkingId()) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (null != requestDto.getParkingLotId()) {
                entityWrapper.eq("parkingLotId", requestDto.getParkingLotId());
            }
            if (StringUtils.isNotEmpty(requestDto.getOrderNo())) {
                entityWrapper.eq("orderNo", requestDto.getOrderNo());
            }
            if (StringUtils.isNotEmpty(requestDto.getPlateNumber())) {
                entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            }
            if (null != requestDto.getPlateColor()) {
                entityWrapper.eq("plateColor", requestDto.getPlateColor());
            }
            if (null != requestDto.getStartOrderTime()) {
                entityWrapper.ge("creationTime", requestDto.getStartOrderTime());
            }
            if (null != requestDto.getEndOrderTime()) {
                entityWrapper.le("creationTime", requestDto.getEndOrderTime());
            }
            entityWrapper.orderBy("scheduleTime", false);

            List<ParkingAppointmentOrderEntity> parkingPageList = parkingAppointmentOrderCrudService.selectAppointOrderListPage(entityWrapper, requestDto.getPageSize(), requestDto.getPageNo());
            Integer totalCount = parkingAppointmentOrderCrudService.selectAppointOrderListCount(entityWrapper);

            if (CollectionUtils.isNotEmpty(parkingPageList)) {

                List<ParkingAppointmentOrderResultDto> appointOrderResultDtoList
                        = modelMapper.map(parkingPageList, new TypeToken<List<ParkingAppointmentOrderResultDto>>() {
                }.getType());

                pagedResultDto.setPageNo(requestDto.getPageNo());
                pagedResultDto.setPageSize(requestDto.getPageSize());
                pagedResultDto.setTotalCount(totalCount.longValue());
                pagedResultDto.setItems(appointOrderResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("预约订单列表获取失败" + e.getMessage());
            pagedResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 创建预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<AppointOrderCreateResultDto> createAppointOrder(AppointOrderCreateRequestDto requestDto) {
        ObjectResultDto<AppointOrderCreateResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = new ParkingAppointmentOrderEntity();
            parkingAppointmentOrderEntity.setCustomerUserId(requestDto.getCustomerUserId());
            parkingAppointmentOrderEntity.setParkingId(requestDto.getParkingId());
            parkingAppointmentOrderEntity.setParkingName(requestDto.getParkingName());
            parkingAppointmentOrderEntity.setOrderNo(String.valueOf(this.idService.genId()));
            parkingAppointmentOrderEntity.setPlateNumber(requestDto.getPlateNumber());
            parkingAppointmentOrderEntity.setPlateColor(requestDto.getPlateColor());
            parkingAppointmentOrderEntity.setCarStyle(requestDto.getCarStyle());
            parkingAppointmentOrderEntity.setTenantId(requestDto.getTenantId());
            Date now = DateUtils.now();
            parkingAppointmentOrderEntity.setScheduleDate(now);
            parkingAppointmentOrderEntity.setScheduleTime(requestDto.getScheduleTime());
            //支付时限
            if (requestDto.getPayLimit() != null) {
                parkingAppointmentOrderEntity.setPayLimit(requestDto.getPayLimit());
                parkingAppointmentOrderEntity.setPayLimitTime(DateUtils.addMinutes(now, parkingAppointmentOrderEntity.getPayLimit()));
            } else {
                parkingAppointmentOrderEntity.setPayLimit(10);
                parkingAppointmentOrderEntity.setPayLimitTime(DateUtils.addMinutes(now, 10));
            }
            if (requestDto.getCancelLimit() != null && requestDto.getCancelLimit().compareTo(0) > 0) {
                parkingAppointmentOrderEntity.setDeadlineTime(DateUtils.addMinutes(requestDto.getScheduleTime(), requestDto.getCancelLimit()));
                parkingAppointmentOrderEntity.setCancelTimeLimit(DateUtils.addMinutes(requestDto.getScheduleTime(), requestDto.getCancelLimit()));
            } else {
                parkingAppointmentOrderEntity.setDeadlineTime(DateUtils.addMinutes(requestDto.getScheduleTime(), 30));
                parkingAppointmentOrderEntity.setCancelTimeLimit(DateUtils.addMinutes(requestDto.getScheduleTime(), 30));
            }
            parkingAppointmentOrderEntity.setOverTimeCancel(requestDto.getOverTimeCancel());
            parkingAppointmentOrderEntity.setPayAmount(requestDto.getPayAmount().multiply(BigDecimal.valueOf(100)).intValue());
            //如果订单预约金额为0，则无需支付，订单直接预约成功
            if (requestDto.getPayAmount().compareTo(BigDecimal.ZERO) <= 0) {
                parkingAppointmentOrderEntity.setAppointStatus(AppointStatusEnum.SUCCESS.getValue());
                parkingAppointmentOrderEntity.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                parkingAppointmentOrderEntity.setActualPayAmount(0);
                parkingAppointmentOrderEntity.setPayTime(now);
            } else {
                parkingAppointmentOrderEntity.setAppointStatus(AppointStatusEnum.CREATED.getValue());
                parkingAppointmentOrderEntity.setPayStatus(PayStatusEnum.CREATED.getValue());
            }
            parkingAppointmentOrderEntity.setCanCancel(Boolean.TRUE);
            parkingAppointmentOrderEntity.setAppointInfoId(requestDto.getAppointInfoId());
            parkingAppointmentOrderEntity.setChargeInfoId(requestDto.getChargeInfoId());
            boolean reVal = parkingAppointmentOrderCrudService.saveAppointOrder(parkingAppointmentOrderEntity);
            if (reVal) {
                AppointOrderCreateResultDto appointOrderPlaceResultDto = new AppointOrderCreateResultDto();
                appointOrderPlaceResultDto.setOrderNo(parkingAppointmentOrderEntity.getOrderNo());
                appointOrderPlaceResultDto.setPayLimit(parkingAppointmentOrderEntity.getPayLimit());
                appointOrderPlaceResultDto.setPayLimitTime(parkingAppointmentOrderEntity.getPayLimitTime());
                appointOrderPlaceResultDto.setPayAmount(BigDecimal.valueOf(parkingAppointmentOrderEntity.getPayAmount()).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP));
                if (parkingAppointmentOrderEntity.getPayAmount() > 0) {
                    appointOrderPlaceResultDto.setNeedPay(Boolean.TRUE);
                } else {
                    appointOrderPlaceResultDto.setNeedPay(Boolean.FALSE);
                }
                objectResultDto.setData(appointOrderPlaceResultDto);
            }
            //发送支付时限消息
            RocketMessage<AppointOrderClosePayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.APPOINT_ORDER_CLOSE);
            message.setSender(MessageQueueDefinitions.Sender.ORDER);
            message.setMessageId(StringUtils.getUUID());
            AppointOrderClosePayload appointOrderClosePayload = new AppointOrderClosePayload();
            appointOrderClosePayload.setOrderNo(parkingAppointmentOrderEntity.getOrderNo());
            message.setPayload(appointOrderClosePayload);
//            Map<String, Object> header = new HashMap<>();
//            header.putIfAbsent(MessageExtConst.PROPERTY_DELAY_TIME_LEVEL,DelayTimeLevel.MINUTE_10);
//            MessageHeaders messageHeaders = new MessageHeaders(header);
//            message.setHeaders(messageHeaders);
            messageSendService.sendAndSaveSync(message);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("预约订单列表获取失败" + e.getMessage());
            objectResultDto.makeResult(OrderResultEnum.APPOINT_PLACE_ORDER_ERR.getValue(), OrderResultEnum.APPOINT_PLACE_ORDER_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 根据车牌获取有效的预约订单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAppointmentOrderResultDto> getValidOrderList(@FluentValid AppointOrderValidPlateRequestDto requestDto) {
        ObjectResultDto<ParkingAppointmentOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            if (requestDto.getPlateColor() != null) {
                entityWrapper.eq("plateColor", requestDto.getPlateColor());
            }
            if (requestDto.getDeadlineTime() != null) {
                entityWrapper.gt("deadlineTime", requestDto.getDeadlineTime());
            }
            entityWrapper.eq("appointStatus", AppointStatusEnum.SUCCESS.getValue());
            entityWrapper.eq("payStatus", PayStatusEnum.PAY_SUCCESS.getValue());
            //按照预约入场时间取第一个
            entityWrapper.orderBy("scheduleTime");
            entityWrapper.last("LIMIT 1");
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.selectAppointOrder(entityWrapper);
            if (parkingAppointmentOrderEntity == null) {
                return objectResultDto.makeResult(OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            } else {
                ParkingAppointmentOrderResultDto userAppointmentResultDto = modelMapper.map(parkingAppointmentOrderEntity, ParkingAppointmentOrderResultDto.class);
                objectResultDto.setData(userAppointmentResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取预约订单失败" + e.getMessage());
            return objectResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 更新预约订单入场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateEnterAppointOrder(AppointOrderUpdateEnterRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.selectByOrderNo(requestDto.getOrderNo());
            if (parkingAppointmentOrderEntity == null || !parkingAppointmentOrderEntity.getAppointStatus().equals(AppointStatusEnum.SUCCESS.getValue())) {
                return resultDto.failed();
            } else {
                ParkingAppointmentOrderEntity parkingAppointmentOrderEntityUpdate = new ParkingAppointmentOrderEntity();
                parkingAppointmentOrderEntityUpdate.setAppointStatus(AppointStatusEnum.ENTERED.getValue());
                parkingAppointmentOrderEntityUpdate.setId(parkingAppointmentOrderEntity.getId());
                parkingAppointmentOrderEntityUpdate.setOrderNo(parkingAppointmentOrderEntity.getOrderNo());
                parkingAppointmentOrderCrudService.updateOrder(parkingAppointmentOrderEntityUpdate);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("预约车辆入场失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 判断用户是否存在有效预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AppointOrderHadResultDto> getAppointOrderByCustomerUserId(ParkingAppointOrderGetByCustomerRequestDto requestDto) {
        ObjectResultDto<AppointOrderHadResultDto> objectResultDto = new ObjectResultDto<>();
        AppointOrderHadResultDto appointOrderHasedResultDto = new AppointOrderHadResultDto();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("customerUserId", requestDto.getCustomerUserId());
            entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.andNew().eq("appointStatus", AppointStatusEnum.CREATED.getValue()).or().eq("appointStatus", AppointStatusEnum.SUCCESS.getValue());
            List<ParkingAppointmentOrderEntity> list = parkingAppointmentOrderCrudService.selectAppointOrderList(entityWrapper);
            if (null == list || list.isEmpty()) {
                appointOrderHasedResultDto.setHad(Boolean.FALSE);
            } else {
                appointOrderHasedResultDto.setHad(Boolean.TRUE);
            }
            objectResultDto.setData(appointOrderHasedResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("判断用户是否存在有效预约订单失败" + e.getMessage());
            return objectResultDto.makeResult(OrderResultEnum.JUDGE_ERR.getValue(), OrderResultEnum.JUDGE_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 根据orderNo更新预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto updateAppointOrderByOrderNo(AppointOrderUpdateByOrderNoRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = modelMapper.map(requestDto, ParkingAppointmentOrderEntity.class);
            parkingAppointmentOrderCrudService.updateAppointOrder(parkingAppointmentOrderEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据orderNo更新预约订单失败" + e.getMessage());
            return resultDto.makeResult(OrderResultEnum.JUDGE_ERR.getValue(), OrderResultEnum.JUDGE_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 获取预约订单(无talentId)
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAppointmentOrderResultDto> getAppointOrderApp(ParkingAppointOrderGetRequestDto requestDto) {
        ObjectResultDto<ParkingAppointmentOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.findByOrderNo(requestDto.getOrderNo(), requestDto.getCustomerUserId());
            if (parkingAppointmentOrderEntity == null) {
                return objectResultDto.makeResult(OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            } else {
                ParkingAppointmentOrderResultDto userAppointmentResultDto = modelMapper.map(parkingAppointmentOrderEntity, ParkingAppointmentOrderResultDto.class);
                objectResultDto.setData(userAppointmentResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取预约订单失败" + e.getMessage());
            return objectResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 预约订单超时未支付取消
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto closePayTimeoutOrder(AppointOrderPayTimeoutCloseRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        Date now = new Date();
        try {
            //关闭当前时间大于支付截止时间的未支付订单
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.ne("appointStatus", AppointStatusEnum.CANCELED.getValue());
            entityWrapper.le("payLimitTime", now);
            entityWrapper.andNew("payStatus=" + PayStatusEnum.CREATED.getValue(), 0);
            entityWrapper.or("payStatus=" + PayStatusEnum.PAY_WAITING.getValue(), 1);
            List<ParkingAppointmentOrderEntity> list = parkingAppointmentOrderCrudService.selectList(entityWrapper);

            if (null != list && list.size() > 0) {
                for (ParkingAppointmentOrderEntity app : list) {

                    app.setPayStatus(PayStatusEnum.PAY_TIMEOUT.getValue());
                    app.setAppointStatus(AppointStatusEnum.CANCELED.getValue());
                    app.setCancelTime(now);
                    app.setCancelReason("后台关闭支付超时预约记录");
                    parkingAppointmentOrderCrudService.updateById(app);
                    log.info("关闭超时未进场预约记录，单号：" + app.getOrderNo() + "用户：" + app.getCustomerUserId() + "停车场：" + app.getParkingId());
                }
            }
            //关闭当前时间大于预计入场时间的未支付订单
            EntityWrapper<ParkingAppointmentOrderEntity> entity = new EntityWrapper<>();
            entity.ne("appointStatus", AppointStatusEnum.CANCELED.getValue());
            entity.le("scheduleTime", now);
            entity.andNew("payStatus=" + PayStatusEnum.CREATED.getValue(), 0);
            entity.or("payStatus=" + PayStatusEnum.PAY_WAITING.getValue(), 1);
            List<ParkingAppointmentOrderEntity> cancelList = parkingAppointmentOrderCrudService.selectList(entity);

            if (CollectionUtils.isNotEmpty(cancelList)) {
                for (ParkingAppointmentOrderEntity app : cancelList) {

                    app.setPayStatus(PayStatusEnum.PAY_TIMEOUT.getValue());
                    app.setAppointStatus(AppointStatusEnum.CANCELED.getValue());
                    app.setCancelTime(now);
                    app.setCancelReason("后台关闭支付超时预约记录");
                    parkingAppointmentOrderCrudService.updateById(app);
                    log.info("关闭超时未进场预约记录，单号：" + app.getOrderNo() + "用户：" + app.getCustomerUserId() + "停车场：" + app.getParkingId());
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("关闭为超时未支付预约记录失败" + e.getMessage());
            resultDto.makeResult(OrderResultEnum.CLOSE_PAYTIMEOUT_ORDER_ERR.getValue(), OrderResultEnum.CLOSE_PAYTIMEOUT_ORDER_ERR.getComment());
        }
        return resultDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto closeEnterTimeoutOrder(AppointOrderEnterTimeoutCloseRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Date now = new Date();
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("payStatus", PayStatusEnum.PAY_SUCCESS.getValue());
            entityWrapper.eq("appointStatus", AppointStatusEnum.SUCCESS.getValue());
            entityWrapper.le("deadlineTime", now);
            List<ParkingAppointmentOrderEntity> list = parkingAppointmentOrderCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(list)) {
                for (ParkingAppointmentOrderEntity app : list) {
                    app.setAppointStatus(AppointStatusEnum.CANCELED.getValue());
                    app.setCancelTime(now);
                    app.setCancelReason("后台关闭超时未进场预约记录");
                    parkingAppointmentOrderCrudService.updateById(app);
                    log.info("关闭超时未进场预约记录，单号：" + app.getOrderNo() + "用户：" + app.getCustomerUserId() + "停车场：" + app.getParkingId());
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("关闭超时未进场预约记录失败" + e.getMessage());
            resultDto.makeResult(OrderResultEnum.CLOSE_ENTERTIMEOUT_ORDER_ERR.getValue(),
                    OrderResultEnum.CLOSE_ENTERTIMEOUT_ORDER_ERR.getComment());
        }
        return resultDto;
    }

}
