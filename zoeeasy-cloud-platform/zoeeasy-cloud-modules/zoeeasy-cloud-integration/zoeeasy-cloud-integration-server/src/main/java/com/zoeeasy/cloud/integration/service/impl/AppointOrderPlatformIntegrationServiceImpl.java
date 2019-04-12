package com.zoeeasy.cloud.integration.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.LocationUtil;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.common.utils.ThreadUtils;
import com.scapegoat.infrastructure.common.utils.TimeUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.appointrule.ParkingAppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleGetByTimeRequestDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleDetailViewResultDto;
import com.zoeeasy.cloud.core.enums.AppointStatusEnum;
import com.zoeeasy.cloud.core.enums.BizTypeEnum;
import com.zoeeasy.cloud.core.enums.ParamTypeEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.integration.appoint.AppointOrderManagerIntegrationService;
import com.zoeeasy.cloud.integration.appoint.AppointOrderPlatformIntegrationService;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointAmountCalculateRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderListGetRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPagedMonthRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPagedResultRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPlaceRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointAmountCalculateResultDto;
import com.zoeeasy.cloud.integration.appoint.validator.AppointOrderPlaceRequestDtoValidator;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.order.appoint.AppointmentOrderManagerService;
import com.zoeeasy.cloud.order.appoint.PlatformAppointOrderService;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderCancelCheckRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderCancelRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderCloseRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderConfirmRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderCreateRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderGetRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderPagedRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderUpdateRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointRefundAmountRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderGetByCustomerRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderGetRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderListGetRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderPagedResultRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderPayCheckRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.TradePaymentRecordRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderCancelCheckResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderCancelResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderCheckResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderConfirmResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderCreateResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderHadResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderPageResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderPlaceResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderViewMonthResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderViewResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointRefundAmountResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.pay.enums.AlipayTradeStatusEnum;
import com.zoeeasy.cloud.pay.enums.WeixinTradeStatusEnum;
import com.zoeeasy.cloud.pay.trade.TradePaymentManagerService;
import com.zoeeasy.cloud.pay.trade.TradePaymentQueryService;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.record.PaymentRecordGetByBizOrderRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.record.PaymentRecordGetByOrderNoRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentOrderGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentUpdatePayStatusRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.record.PaymentRecordGetResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.record.PaymentRecordResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.trade.TradePaymentOrderResultDto;
import com.zoeeasy.cloud.pay.wechat.params.WeChatCloseOrderParams;
import com.zoeeasy.cloud.pay.wechat.params.WeChatOrderQueryParams;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayCloseOrderResult;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayOrderNotifyResult;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayOrderQueryResult;
import com.zoeeasy.cloud.pay.wechat.service.WeChatPayService;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingChargeInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingAppointInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingAppointInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingChargeInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingImageGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLocationGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.AppointOrderDetailViewResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingAppointInfoResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingChargeInfoResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingImageViewResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingLocationResultDto;
import com.zoeeasy.cloud.sys.parameter.ParameterService;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamGetRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamGetResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 预约集成服务
 *
 * @author walkman
 */
@Service(value = "appointOrderPlatformIntegrationService")
@Slf4j
public class AppointOrderPlatformIntegrationServiceImpl implements AppointOrderPlatformIntegrationService {

    @Autowired
    private ParkingAppointRuleService parkingAppointRuleService;

    @Autowired
    private PlatformAppointOrderService platformOrderService;

    @Autowired
    private AppointmentOrderManagerService parkingAppointmentOrderService;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    @Autowired
    private AppointOrderManagerIntegrationService appointmentManagerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private TradePaymentQueryService tradePaymentQueryService;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private WeChatPayService weChatPayService;

    @Autowired
    private TradePaymentManagerService tradePaymentManagerService;

    /**
     * 预约订单下单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<AppointOrderPlaceResultDto> placeOrder(@FluentValid(AppointOrderPlaceRequestDtoValidator.class) CustomerAppointOrderPlaceRequestDto requestDto) {
        ObjectResultDto<AppointOrderPlaceResultDto> objectResultDto = new ObjectResultDto<>();
        Date now = new Date();
        try {
            //是否有有效的预约订单
            ParkingAppointOrderGetByCustomerRequestDto parkingAppointOrderGetByUserIdRequestDto = new ParkingAppointOrderGetByCustomerRequestDto();
            parkingAppointOrderGetByUserIdRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            parkingAppointOrderGetByUserIdRequestDto.setPlateNumber(requestDto.getPlateNumber());
            parkingAppointOrderGetByUserIdRequestDto.setParkingId(requestDto.getParkingId());
            ObjectResultDto<AppointOrderHadResultDto> obj = platformOrderService.getAppointOrderByCustomerUserId(parkingAppointOrderGetByUserIdRequestDto);
            if (obj.isFailed() || obj.getData().getHad()) {
                return objectResultDto.makeResult(PmsResultEnum.USER_APPOINTORDER_EXIST.getValue(), PmsResultEnum.USER_APPOINTORDER_EXIST.getComment());
            }
            ObjectResultDto<ParkingAppointRuleDetailViewResultDto> appointRuleObjectResultDto = getAppointRule(requestDto.getParkingId(), requestDto.getScheduleTime());
            if (appointRuleObjectResultDto.isFailed() || appointRuleObjectResultDto.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_APPOINT_NOT_SUPPORT_ERROR.getValue(),
                        PmsResultEnum.PARKING_APPOINT_NOT_SUPPORT_ERROR.getComment());
            }
            AppointAmountCalculateRequestDto appointAmountCalculateRequestDto = new AppointAmountCalculateRequestDto();
            appointAmountCalculateRequestDto.setParkingId(requestDto.getParkingId());
            appointAmountCalculateRequestDto.setAppointTime(requestDto.getScheduleTime());
            ObjectResultDto<AppointAmountCalculateResultDto> objectResultCalculateAmount = calculateIntegrationService.calculateAppointAmount(appointAmountCalculateRequestDto);
            if (objectResultCalculateAmount.isFailed() || objectResultCalculateAmount.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_RULE_NOT_FOUND.getValue(),
                        PmsResultEnum.APPOINT_RULE_NOT_FOUND.getComment());
            }
            AppointAmountCalculateResultDto appointAmountCalculateResultDto = objectResultCalculateAmount.getData();
            ParkingAppointRuleDetailViewResultDto parkingAppointRuleDetailViewResultDto = appointRuleObjectResultDto.getData();
            if (appointAmountCalculateResultDto.getAmount().compareTo(requestDto.getAppointAmount()) != 0) {
                return objectResultDto.makeResult(PmsResultEnum.AMOUNT_DIFFERENT.getValue(), PmsResultEnum.AMOUNT_DIFFERENT.getComment());
            }
            AppointOrderCreateRequestDto appointOrderCreateRequestDto = new AppointOrderCreateRequestDto();
            if (parkingAppointRuleDetailViewResultDto.getPayLimit() != null && parkingAppointRuleDetailViewResultDto.getPayLimit().compareTo(0) > 0) {
                appointOrderCreateRequestDto.setPayLimit(parkingAppointRuleDetailViewResultDto.getPayLimit());
                appointOrderCreateRequestDto.setPayLimitTime(DateUtils.addMinutes(now, parkingAppointRuleDetailViewResultDto.getPayLimit()));
            } else {
                appointOrderCreateRequestDto.setPayLimit(10);
                appointOrderCreateRequestDto.setPayLimitTime(DateUtils.addMinutes(now, 10));
            }
            if (requestDto.getScheduleTime().before(appointOrderCreateRequestDto.getPayLimitTime())) {
                return objectResultDto.makeResult(PmsResultEnum.SCHEDULETIME_ERR.getValue(),
                        PmsResultEnum.SCHEDULETIME_ERR.getComment());
            }
            appointOrderCreateRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            appointOrderCreateRequestDto.setParkingId(requestDto.getParkingId());
            appointOrderCreateRequestDto.setParkingName(requestDto.getParkingResultDto().getFullName());
            appointOrderCreateRequestDto.setPlateNumber(requestDto.getPlateNumber());
            appointOrderCreateRequestDto.setPlateColor(requestDto.getPlateColor());
            appointOrderCreateRequestDto.setCarStyle(requestDto.getCarStyle());
            appointOrderCreateRequestDto.setScheduleDate(now);
            appointOrderCreateRequestDto.setScheduleTime(requestDto.getScheduleTime());
            if (parkingAppointRuleDetailViewResultDto.getCancelLimit() != null && parkingAppointRuleDetailViewResultDto.getCancelLimit().compareTo(0) > 0) {
                appointOrderCreateRequestDto.setDeadlineTime(DateUtils.addMinutes(requestDto.getScheduleTime(), parkingAppointRuleDetailViewResultDto.getCancelLimit()));
                appointOrderCreateRequestDto.setCancelTimeLimit(DateUtils.addMinutes(requestDto.getScheduleTime(), parkingAppointRuleDetailViewResultDto.getCancelLimit()));
            } else {
                appointOrderCreateRequestDto.setDeadlineTime(DateUtils.addMinutes(requestDto.getScheduleTime(), 30));
                appointOrderCreateRequestDto.setCancelTimeLimit(DateUtils.addMinutes(requestDto.getScheduleTime(), 30));
            }
            appointOrderCreateRequestDto.setOverTimeCancel(parkingAppointRuleDetailViewResultDto.getOverTimeCancel());
            appointOrderCreateRequestDto.setPayAmount(requestDto.getAppointAmount());
            appointOrderCreateRequestDto.setCanCancel(Boolean.TRUE);
            ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
            parkingChargeInfoGetByParkingIdRequestDto.setParkingId(requestDto.getParkingId());
            ObjectResultDto<ParkingChargeInfoResultDto> chargeInfoResultDto = platformParkingInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
            if (chargeInfoResultDto.isFailed() || null == chargeInfoResultDto.getData()) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getComment());
            }
            ParkingAppointInfoGetByParkingIdRequestDto parkingAppointInfoGetByParkingIdRequestDto = new ParkingAppointInfoGetByParkingIdRequestDto();
            parkingAppointInfoGetByParkingIdRequestDto.setParkingId(requestDto.getParkingId());
            ObjectResultDto<ParkingAppointInfoResultDto> appointInfoResultDto = platformParkingInfoService.getAppointmentInfoByParkingId(parkingAppointInfoGetByParkingIdRequestDto);
            if (appointInfoResultDto.isFailed() || null == appointInfoResultDto.getData()) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_APPOINTINFO_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_APPOINTINFO_NOT_FOUND.getComment());
            }
            appointOrderCreateRequestDto.setAppointInfoId(appointInfoResultDto.getData().getId());
            appointOrderCreateRequestDto.setChargeInfoId(chargeInfoResultDto.getData().getId());
            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(requestDto.getParkingId());
            parkingInfoGetRequestDto.setStatus(ParkingStatusEnum.ON_LINE.getValue());
            ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDtoObjectResultDto = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
            if (parkingInfoResultDtoObjectResultDto.isFailed() || null == parkingInfoResultDtoObjectResultDto.getData()) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
            appointOrderCreateRequestDto.setTenantId(parkingInfoResultDtoObjectResultDto.getData().getTenantId());
            ObjectResultDto<AppointOrderCreateResultDto> appointOrderCreateResultDto = platformOrderService.createAppointOrder(appointOrderCreateRequestDto);
            if (appointOrderCreateResultDto.isFailed() || appointOrderCreateResultDto.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_PLACE_ORDER_ERR.getValue(),
                        PmsResultEnum.APPOINT_PLACE_ORDER_ERR.getComment());
            }
            AppointOrderCreateResultDto appointOrderCreateResult = appointOrderCreateResultDto.getData();
            AppointOrderPlaceResultDto appointOrderPlaceResultDto = new AppointOrderPlaceResultDto();
            appointOrderPlaceResultDto.setPayAmount(appointOrderCreateRequestDto.getPayAmount());
            appointOrderPlaceResultDto.setOrderNo(appointOrderCreateResult.getOrderNo());
            appointOrderPlaceResultDto.setPayLimit(appointOrderCreateResult.getPayLimit());
            appointOrderPlaceResultDto.setPayLimitTime(appointOrderCreateResult.getPayLimitTime());
            appointOrderPlaceResultDto.setNeedPay(appointOrderCreateResult.getNeedPay());
            objectResultDto.setData(appointOrderPlaceResultDto);
            objectResultDto.success();

        } catch (Exception e) {
            log.error("预约下单失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.APPOINT_PLACE_ORDER_ERR.getValue(), PmsResultEnum.APPOINT_PLACE_ORDER_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 计算预约取消退款金额
     */
    @Override
    public ObjectResultDto<AppointRefundAmountResultDto> calculateRefundAmount(AppointRefundAmountRequestDto requestDto) {

        ObjectResultDto<AppointRefundAmountResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            AppointRefundAmountResultDto appointAmountCalculateResultDto = new AppointRefundAmountResultDto();
            appointAmountCalculateResultDto.setRefundAmount(BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP));
            objectResultDto.setData(appointAmountCalculateResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询停车场对应时间预约规则失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.APPOINTRULE_GET_ERR.getValue(), PmsResultEnum.APPOINTRULE_GET_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 预约订单确认
     */
    @Override
    public ObjectResultDto<AppointOrderConfirmResultDto> confirmOrder(AppointOrderConfirmRequestDto requestDto) {
        ObjectResultDto<AppointOrderConfirmResultDto> objectResultDto = new ObjectResultDto<>();
        //获取预约订单
        ParkingAppointOrderGetRequestDto appointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
        appointOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
        appointOrderGetRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
        ObjectResultDto<ParkingAppointmentOrderResultDto> parkingAppointmentOrderResultDto = platformOrderService.getAppointOrderApp(appointOrderGetRequestDto);
        if (parkingAppointmentOrderResultDto.isFailed() || parkingAppointmentOrderResultDto.getData() == null) {
            return objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                    PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
        }
        ParkingAppointmentOrderResultDto parkingAppointmentOrder = parkingAppointmentOrderResultDto.getData();
        if (parkingAppointmentOrder.getPayAmount().compareTo(NumberUtils.amountYuan2Fen(requestDto.getPayAmount()).intValue()) != 0) {
            return objectResultDto.makeResult(PmsResultEnum.AMOUNT_DIFFERENT.getValue(), PmsResultEnum.AMOUNT_DIFFERENT.getComment()
            );
        }
        boolean paySuccess = false;
        //付款成功
        if (parkingAppointmentOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
            paySuccess = true;
        } else if (parkingAppointmentOrder.getPayStatus().equals(PayStatusEnum.WAITING_PAYMENT_RESULT.getValue())) {
            //支付处理中
            //自定义重试5次
            int count = 0;
            while (true) {
                count++;
                parkingAppointmentOrderResultDto = platformOrderService.getAppointOrderApp(appointOrderGetRequestDto);
                if (parkingAppointmentOrderResultDto.isFailed() || parkingAppointmentOrderResultDto.getData() == null) {
                    return objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                            PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
                }
                parkingAppointmentOrder = parkingAppointmentOrderResultDto.getData();
                if (parkingAppointmentOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                    paySuccess = true;
                    break;
                } else if (parkingAppointmentOrder.getPayStatus().equals(PayStatusEnum.PAY_FAILED.getValue())) {
                    break;
                }
                if (count >= 5) {
                    objectResultDto.makeResult(PmsResultEnum.PAYING_TRY_LATER.getValue(),
                            PmsResultEnum.PAYING_TRY_LATER.getComment());
                    break;
                }
                ThreadUtils.sleep((2 * count - 1) & TimeUtils.MILLIS_OF_MINUTE);
            }
        }
        if (paySuccess) {
            AppointOrderConfirmResultDto appointOrderConfirmResultDto = new AppointOrderConfirmResultDto();
            appointOrderConfirmResultDto.setOrderNo(parkingAppointmentOrder.getOrderNo());
            appointOrderConfirmResultDto.setSucceedTime(parkingAppointmentOrder.getPayTime());
            appointOrderConfirmResultDto.setTotalAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingAppointmentOrder.getActualPayAmount())));
            appointOrderConfirmResultDto.setSucceed(Boolean.TRUE);
            objectResultDto.setData(appointOrderConfirmResultDto);
        } else {
            AppointOrderConfirmResultDto appointOrderConfirmResultDto = new AppointOrderConfirmResultDto();
            appointOrderConfirmResultDto.setOrderNo(parkingAppointmentOrder.getOrderNo());
            appointOrderConfirmResultDto.setSucceed(Boolean.FALSE);
            objectResultDto.setData(appointOrderConfirmResultDto);
        }
        return objectResultDto.success();
    }

    /**
     * 预约订单取消
     */
    @Override
    public ObjectResultDto<AppointOrderCancelResultDto> cancelOrder(AppointOrderCancelRequestDto requestDto) {
        ObjectResultDto<AppointOrderCancelResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            //获取预约订单
            ParkingAppointOrderGetRequestDto appointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
            appointOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            appointOrderGetRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            ObjectResultDto<ParkingAppointmentOrderResultDto> parkingAppointmentOrderResultDto = platformOrderService.getAppointOrderApp(appointOrderGetRequestDto);
            if (parkingAppointmentOrderResultDto.isFailed() || parkingAppointmentOrderResultDto.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            }
            ParkingAppointmentOrderResultDto parkingAppointmentOrder = parkingAppointmentOrderResultDto.getData();
            if (parkingAppointmentOrder.getAppointStatus().equals(AppointStatusEnum.CANCELED.getValue())) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_STATUS_ERR.getValue(),
                        PmsResultEnum.APPOINT_STATUS_ERR.getComment());
            }
            objectResultDto = appointmentManagerService.cancelAppointOrder(requestDto);
        } catch (Exception e) {
            log.error("取消订单失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.CANCEL_APPOINT_ORDER_ERR.getValue(), PmsResultEnum.CANCEL_APPOINT_ORDER_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 分页根据月份获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public PagedResultDto<AppointOrderViewMonthResultDto> getOrderPagedListByMonth(CustomerAppointOrderPagedMonthRequestDto requestDto) {
        PagedResultDto<AppointOrderViewMonthResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            ParkingAppointOrderPagedResultRequestDto parkingAppointOrderPagedResultRequestDto = new ParkingAppointOrderPagedResultRequestDto();
            if (null != requestDto.getDateTime()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = sdf.format(requestDto.getDateTime());
                Date dateTime = sdf.parse(dateStr);
                parkingAppointOrderPagedResultRequestDto.setStartOrderTime(DateTimeUtils.getMonthStart(dateTime));
                parkingAppointOrderPagedResultRequestDto.setEndOrderTime(DateTimeUtils.getMonthEnd(dateTime));
            }
            parkingAppointOrderPagedResultRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            parkingAppointOrderPagedResultRequestDto.setPageNo(requestDto.getPageNo());
            parkingAppointOrderPagedResultRequestDto.setPageSize(requestDto.getPageSize());
            PagedResultDto<ParkingAppointmentOrderResultDto> orderResultDtoPaged = platformOrderService.getAppointOrderPagedList(parkingAppointOrderPagedResultRequestDto);
            if (orderResultDtoPaged.isSuccess()) {

                List<AppointOrderViewMonthResultDto> appointOrderViewMonthResultDtoList = new ArrayList<>();

                List<ParkingAppointmentOrderResultDto> appointmentOrderResultDtoList = orderResultDtoPaged.getItems();

                for (int i = 0; i < appointmentOrderResultDtoList.size(); i++) {

                    ParkingAppointmentOrderResultDto userAppointment = appointmentOrderResultDtoList.get(i);

                    AppointOrderViewMonthResultDto appointOrderViewMonthResultDto = modelMapper.map(userAppointment, AppointOrderViewMonthResultDto.class);
                    appointOrderViewMonthResultDto.setPayAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(userAppointment.getPayAmount())));

                    //获取收费信息
                    ParkingChargeInfoGetByIdRequestDto parkingChargeInfoGetByIdRequestDto = new ParkingChargeInfoGetByIdRequestDto();
                    parkingChargeInfoGetByIdRequestDto.setId(userAppointment.getChargeInfoId());
                    ObjectResultDto<ParkingChargeInfoResultDto> chargeInfo = platformParkingInfoService.getParkChargeInfoById(parkingChargeInfoGetByIdRequestDto);
                    if (chargeInfo.isFailed() || chargeInfo.getData() == null) {
                        return pagedResultDto.makeResult(PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getValue(),
                                PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getComment());
                    }
                    appointOrderViewMonthResultDto.setChargeDescription(chargeInfo.getData().getChargeDescription());

                    if (PayStatusEnum.PAY_WAITING.getValue().equals(userAppointment.getPayStatus()) || PayStatusEnum.CREATED.getValue().equals(userAppointment.getPayStatus())) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new Date());
                        long time1 = cal.getTimeInMillis();
                        cal.setTime(userAppointment.getPayLimitTime());
                        long time2 = cal.getTimeInMillis();
                        long payLimit = (time2 - time1) / 60000L;
                        if (payLimit < 0) {
                            appointOrderViewMonthResultDto.setPayLimit(0);
                        } else {
                            appointOrderViewMonthResultDto.setPayLimit((int) (payLimit + 1L));
                        }
                    } else {
                        appointOrderViewMonthResultDto.setPayLimit(null);
                    }
                    //获取停车场位置
                    ParkingLocationGetRequestDto requestDto1 = new ParkingLocationGetRequestDto();
                    requestDto1.setParkingId(userAppointment.getParkingId());
                    ObjectResultDto<ParkingLocationResultDto> objectResultDto1 = platformParkingInfoService.getParkingAddress(requestDto1);
                    if (objectResultDto1.isFailed() || objectResultDto1.getData() == null) {
                        appointOrderViewMonthResultDto.setParkingAddress("");
                    } else {
                        ParkingLocationResultDto parkingAddressGetResultDto = objectResultDto1.getData();
                        appointOrderViewMonthResultDto.setParkingAddress(parkingAddressGetResultDto.getAddress());
                        appointOrderViewMonthResultDto.setLatitude(parkingAddressGetResultDto.getLatitude());
                        appointOrderViewMonthResultDto.setLongitude(parkingAddressGetResultDto.getLongitude());
                    }
                    appointOrderViewMonthResultDtoList.add(appointOrderViewMonthResultDto);
                }
                pagedResultDto.setPageNo(orderResultDtoPaged.getPageNo());
                pagedResultDto.setPageSize(orderResultDtoPaged.getPageSize());
                pagedResultDto.setTotalCount(orderResultDtoPaged.getTotalCount());
                pagedResultDto.setItems(appointOrderViewMonthResultDtoList);
            }

            pagedResultDto.success();
        } catch (Exception e) {
            log.error("预约订单列表获取失败" + e.getMessage());
            pagedResultDto.makeResult(PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getValue(),
                    PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 获取预约订单
     */
    @Override
    public ObjectResultDto<AppointOrderViewResultDto> getOrder(AppointOrderGetRequestDto requestDto) {
        ObjectResultDto<AppointOrderViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            //获取预约订单
            ParkingAppointOrderGetRequestDto appointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
            appointOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            appointOrderGetRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            ObjectResultDto<ParkingAppointmentOrderResultDto> parkingAppointmentOrderResultDto = platformOrderService.getAppointOrderApp(appointOrderGetRequestDto);
            if (parkingAppointmentOrderResultDto.isFailed() || parkingAppointmentOrderResultDto.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            }
            ParkingAppointmentOrderResultDto parkingAppointmentOrder = parkingAppointmentOrderResultDto.getData();

            AppointOrderViewResultDto appointOrderViewResultDto = modelMapper.map(parkingAppointmentOrder, AppointOrderViewResultDto.class);

            ParkingLocationGetRequestDto parkingAddressGetRequestDto = new ParkingLocationGetRequestDto();
            parkingAddressGetRequestDto.setParkingId(parkingAppointmentOrder.getParkingId());
            ObjectResultDto<ParkingLocationResultDto> parkingAddressObjectResultDto = platformParkingInfoService.getParkingAddress(parkingAddressGetRequestDto);
            if (parkingAddressObjectResultDto.isFailed() || parkingAddressObjectResultDto.getData() == null) {
            } else {
                ParkingLocationResultDto parkingAddressGetResultDto = parkingAddressObjectResultDto.getData();
                appointOrderViewResultDto.setLatitude(parkingAddressGetResultDto.getLatitude());
                appointOrderViewResultDto.setLongitude(parkingAddressGetResultDto.getLongitude());
                appointOrderViewResultDto.setParkingAddress(parkingAddressGetResultDto.getAddress());
                if (null != requestDto.getLatitude() && null != requestDto.getLongitude()) {

                    Double distance = LocationUtil.getDistanceFromTwoPoints(requestDto.getLatitude(), requestDto.getLongitude(),
                            parkingAddressGetResultDto.getLatitude(), parkingAddressGetResultDto.getLongitude());
                    BigDecimal distanceKilo = BigDecimal.valueOf(distance / 1000);
                    appointOrderViewResultDto.setDistance(distanceKilo.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }
            objectResultDto.setData(appointOrderViewResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取预约订单失败" + e.getMessage());
            return objectResultDto.makeResult(PmsResultEnum.GET_USER_APPOINT_ERR.getValue(),
                    PmsResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 获取预约订单
     */
    @Override
    public ObjectResultDto<AppointOrderDetailViewResultDto> getOrderDetail(AppointOrderGetRequestDto requestDto) {
        ObjectResultDto<AppointOrderDetailViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            //获取预约订单
            ParkingAppointOrderGetRequestDto appointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
            appointOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            appointOrderGetRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            ObjectResultDto<ParkingAppointmentOrderResultDto> parkingAppointmentOrderResultDto = platformOrderService.getAppointOrderApp(appointOrderGetRequestDto);
            if (parkingAppointmentOrderResultDto.isFailed() || parkingAppointmentOrderResultDto.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            }
            ParkingAppointmentOrderResultDto parkingAppointmentOrder = parkingAppointmentOrderResultDto.getData();

            //获取停车场位置信息
            ParkingLocationGetRequestDto parkingAddressGetRequestDto = new ParkingLocationGetRequestDto();
            parkingAddressGetRequestDto.setParkingId(parkingAppointmentOrder.getParkingId());
            ObjectResultDto<ParkingLocationResultDto> parkingAddressObjectResultDto = platformParkingInfoService.getParkingAddress(parkingAddressGetRequestDto);
            if (parkingAddressObjectResultDto.isFailed() || parkingAddressObjectResultDto.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
            ParkingLocationResultDto parkingAddressGetResultDto = parkingAddressObjectResultDto.getData();

            AppointOrderDetailViewResultDto appointOrderViewResultDto = modelMapper.map(parkingAppointmentOrder, AppointOrderDetailViewResultDto.class);
            appointOrderViewResultDto.setRefundAmount(NumberUtils.formatAmountYuan(BigDecimal.valueOf(parkingAppointmentOrder.getRefundAmount()).divide(BigDecimal.valueOf(100))));
            appointOrderViewResultDto.setPayAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingAppointmentOrder.getPayAmount())));
            //获取预约信息
            ParkingAppointInfoGetByIdRequestDto parkingAppointInfoGetByIdRequestDto = new ParkingAppointInfoGetByIdRequestDto();
            parkingAppointInfoGetByIdRequestDto.setId(parkingAppointmentOrder.getAppointInfoId());
            ObjectResultDto<ParkingAppointInfoResultDto> appointInfo = platformParkingInfoService.getAppointmentInfoById(parkingAppointInfoGetByIdRequestDto);
            if (appointInfo.isFailed() || appointInfo.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_APPOINTINFO_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_APPOINTINFO_NOT_FOUND.getComment());
            }
            appointOrderViewResultDto.setAppointDescription(appointInfo.getData().getAppointmentRule());
            //获取收费信息
            ParkingChargeInfoGetByIdRequestDto parkingChargeInfoGetByIdRequestDto = new ParkingChargeInfoGetByIdRequestDto();
            parkingChargeInfoGetByIdRequestDto.setId(parkingAppointmentOrder.getChargeInfoId());
            ObjectResultDto<ParkingChargeInfoResultDto> chargeInfo = platformParkingInfoService.getParkChargeInfoById(parkingChargeInfoGetByIdRequestDto);
            if (chargeInfo.isFailed() || chargeInfo.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getComment());
            }
            appointOrderViewResultDto.setChargeDescription(chargeInfo.getData().getChargeDescription());

            if (PayStatusEnum.PAY_WAITING.getValue().equals(parkingAppointmentOrder.getPayStatus()) || PayStatusEnum.CREATED.getValue().equals(parkingAppointmentOrder.getPayStatus())) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                long time1 = cal.getTimeInMillis();
                cal.setTime(parkingAppointmentOrder.getPayLimitTime());
                long time2 = cal.getTimeInMillis();
                long payLimit = (time2 - time1) / 60000L;
                if (payLimit < 0) {
                    appointOrderViewResultDto.setPayLimit(0);
                } else {
                    appointOrderViewResultDto.setPayLimit((int) (payLimit + 1L));
                }
            } else {
                appointOrderViewResultDto.setPayLimit(null);
            }

            appointOrderViewResultDto.setLatitude(parkingAddressGetResultDto.getLatitude());
            appointOrderViewResultDto.setLongitude(parkingAddressGetResultDto.getLongitude());
            appointOrderViewResultDto.setParkingAddress(parkingAddressGetResultDto.getAddress());
            if (null != requestDto.getLatitude() && null != requestDto.getLongitude()) {
                Double distance = LocationUtil.getDistanceFromTwoPoints(requestDto.getLatitude(), requestDto.getLongitude(),
                        parkingAddressGetResultDto.getLatitude(), parkingAddressGetResultDto.getLongitude());
                BigDecimal distanceKilo = BigDecimal.valueOf(distance / 1000);
                appointOrderViewResultDto.setDistance(distanceKilo.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            //获取停车场图像
            ParkingImageGetRequestDto parkingImageGetRequestDto = new ParkingImageGetRequestDto();
            parkingImageGetRequestDto.setParkingId(parkingAppointmentOrder.getParkingId());
            ListResultDto<ParkingImageViewResultDto> parkingImageObjectResultDto = platformParkingInfoService.getParkingImageList(parkingImageGetRequestDto);
            if (parkingImageObjectResultDto.isSuccess() && parkingImageObjectResultDto.getItems() != null) {
                appointOrderViewResultDto.setImageUrls(parkingImageObjectResultDto.getItems());
            }
            objectResultDto.setData(appointOrderViewResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取预约订单失败" + e.getMessage());
            return objectResultDto.makeResult(PmsResultEnum.GET_USER_APPOINT_ERR.getValue(),
                    PmsResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 获取预约订单列表
     */
    @Override
    public ListResultDto<AppointOrderViewResultDto> getOrderList(CustomerAppointOrderListGetRequestDto requestDto) {
        ListResultDto<AppointOrderViewResultDto> listResultDto = new ListResultDto<>();
        try {

            ParkingAppointOrderListGetRequestDto appointOrderListGetRequestDto = new ParkingAppointOrderListGetRequestDto();
            appointOrderListGetRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            ListResultDto<ParkingAppointmentOrderResultDto> parkingAppointmentOrderResultDto = platformOrderService.getAppointOrderList(appointOrderListGetRequestDto);

            if (parkingAppointmentOrderResultDto.isSuccess()) {

                List<ParkingAppointmentOrderResultDto> parkingAppointmentOrderResultDtos = parkingAppointmentOrderResultDto.getItems();

                if (CollectionUtils.isNotEmpty(parkingAppointmentOrderResultDtos)) {

                    List<AppointOrderViewResultDto> appointOrderViewResultDtoList = new ArrayList<>();

                    for (int i = 0; i < parkingAppointmentOrderResultDtos.size(); i++) {

                        ParkingAppointmentOrderResultDto userAppointment = parkingAppointmentOrderResultDtos.get(i);

                        AppointOrderViewResultDto appointOrderViewResultDto = modelMapper.map(userAppointment, AppointOrderViewResultDto.class);
                        appointOrderViewResultDto.setPayAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(userAppointment.getPayAmount())));
                        //获取收费信息
                        ParkingChargeInfoGetByIdRequestDto parkingChargeInfoGetByIdRequestDto = new ParkingChargeInfoGetByIdRequestDto();
                        parkingChargeInfoGetByIdRequestDto.setId(userAppointment.getChargeInfoId());
                        ObjectResultDto<ParkingChargeInfoResultDto> chargeInfo = platformParkingInfoService.getParkChargeInfoById(parkingChargeInfoGetByIdRequestDto);
                        if (chargeInfo.isFailed() || chargeInfo.getData() == null) {
                            return listResultDto.makeResult(PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getValue(),
                                    PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getComment());
                        }
                        appointOrderViewResultDto.setChargeDescription(chargeInfo.getData().getChargeDescription());

                        if (PayStatusEnum.PAY_WAITING.getValue().equals(userAppointment.getPayStatus()) || PayStatusEnum.CREATED.getValue().equals(userAppointment.getPayStatus())) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(new Date());
                            long time1 = cal.getTimeInMillis();
                            cal.setTime(userAppointment.getPayLimitTime());
                            long time2 = cal.getTimeInMillis();
                            long payLimit = (time2 - time1) / 60000L;
                            if (payLimit < 0) {
                                appointOrderViewResultDto.setPayLimit(0);
                            } else {
                                appointOrderViewResultDto.setPayLimit((int) (payLimit + 1L));
                            }
                        } else {
                            appointOrderViewResultDto.setPayLimit(null);
                        }
                        ParkingLocationGetRequestDto requestDto1 = new ParkingLocationGetRequestDto();
                        requestDto1.setParkingId(userAppointment.getParkingId());
                        ObjectResultDto<ParkingLocationResultDto> objectResultDto1 = platformParkingInfoService.getParkingAddress(requestDto1);
                        if (objectResultDto1.isFailed() || objectResultDto1.getData() == null) {
                            appointOrderViewResultDto.setParkingAddress("");
                        } else {
                            ParkingLocationResultDto parkingAddressGetResultDto = objectResultDto1.getData();
                            appointOrderViewResultDto.setParkingAddress(parkingAddressGetResultDto.getAddress());
                            appointOrderViewResultDto.setLatitude(parkingAddressGetResultDto.getLatitude());
                            appointOrderViewResultDto.setLongitude(parkingAddressGetResultDto.getLongitude());
                        }
                        appointOrderViewResultDtoList.add(appointOrderViewResultDto);
                    }
                    listResultDto.setItems(appointOrderViewResultDtoList);
                }
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("预约订单列表获取失败" + e.getMessage());
            listResultDto.makeResult(PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getValue(), PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getComment());
        }
        return listResultDto;
    }

    /**
     * 分页获取预约订单列表
     */
    @Override
    public PagedResultDto<AppointOrderViewResultDto> getOrderPagedList(CustomerAppointOrderPagedResultRequestDto requestDto) {
        PagedResultDto<AppointOrderViewResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            ParkingAppointOrderPagedResultRequestDto parkingAppointOrderPagedResultRequestDto = new ParkingAppointOrderPagedResultRequestDto();
            parkingAppointOrderPagedResultRequestDto.setPageSize(requestDto.getPageSize());
            parkingAppointOrderPagedResultRequestDto.setPageNo(requestDto.getPageNo());
            parkingAppointOrderPagedResultRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            PagedResultDto<ParkingAppointmentOrderResultDto> pagedParkingAppointmentOrderResult = platformOrderService.getAppointOrderPagedList(parkingAppointOrderPagedResultRequestDto);

            if (pagedParkingAppointmentOrderResult.isSuccess()) {

                List<AppointOrderViewResultDto> appointOrderViewResultDtoList = new ArrayList<>();

                if (CollectionUtils.isNotEmpty(pagedParkingAppointmentOrderResult.getItems())) {

                    List<ParkingAppointmentOrderResultDto> appointmentOrderResultDtoList = pagedParkingAppointmentOrderResult.getItems();

                    for (int i = 0; i < appointmentOrderResultDtoList.size(); i++) {

                        ParkingAppointmentOrderResultDto userAppointment = appointmentOrderResultDtoList.get(i);
                        AppointOrderViewResultDto appointOrderViewResultDto = modelMapper.map(userAppointment, AppointOrderViewResultDto.class);
                        appointOrderViewResultDto.setPayAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(userAppointment.getPayAmount())));
                        //获取收费信息
                        ParkingChargeInfoGetByIdRequestDto parkingChargeInfoGetByIdRequestDto = new ParkingChargeInfoGetByIdRequestDto();
                        parkingChargeInfoGetByIdRequestDto.setId(userAppointment.getChargeInfoId());
                        ObjectResultDto<ParkingChargeInfoResultDto> chargeInfo = platformParkingInfoService.getParkChargeInfoById(parkingChargeInfoGetByIdRequestDto);
                        if (chargeInfo.isFailed() || chargeInfo.getData() == null) {
                            return pagedResultDto.makeResult(PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getValue(),
                                    PmsResultEnum.PARKING_CHARGEINFO_NOT_FOUND.getComment());
                        }
                        appointOrderViewResultDto.setChargeDescription(chargeInfo.getData().getChargeDescription());

                        if (PayStatusEnum.PAY_WAITING.getValue().equals(userAppointment.getPayStatus()) ||
                                PayStatusEnum.CREATED.getValue().equals(userAppointment.getPayStatus())) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(new Date());
                            long time1 = cal.getTimeInMillis();
                            cal.setTime(userAppointment.getPayLimitTime());
                            long time2 = cal.getTimeInMillis();
                            long payLimit = (time2 - time1) / 60000L;
                            if (payLimit < 0) {
                                appointOrderViewResultDto.setPayLimit(0);
                            } else {
                                appointOrderViewResultDto.setPayLimit((int) (payLimit + 1L));
                            }
                        } else {
                            appointOrderViewResultDto.setPayLimit(null);
                        }
                        ParkingLocationGetRequestDto requestDto1 = new ParkingLocationGetRequestDto();
                        requestDto1.setParkingId(userAppointment.getParkingId());
                        ObjectResultDto<ParkingLocationResultDto> objectResultDto1 = platformParkingInfoService.getParkingAddress(requestDto1);
                        if (objectResultDto1.isFailed() || objectResultDto1.getData() == null) {
                            appointOrderViewResultDto.setParkingAddress("");
                        } else {
                            ParkingLocationResultDto parkingAddressGetResultDto = objectResultDto1.getData();
                            appointOrderViewResultDto.setParkingAddress(parkingAddressGetResultDto.getAddress());
                            appointOrderViewResultDto.setLatitude(parkingAddressGetResultDto.getLatitude());
                            appointOrderViewResultDto.setLongitude(parkingAddressGetResultDto.getLongitude());
                        }
                        appointOrderViewResultDtoList.add(appointOrderViewResultDto);
                    }
                }
                pagedResultDto.setPageNo(pagedParkingAppointmentOrderResult.getPageNo());
                pagedResultDto.setPageSize(pagedParkingAppointmentOrderResult.getPageSize());
                pagedResultDto.setTotalCount(pagedParkingAppointmentOrderResult.getTotalCount());
                pagedResultDto.setItems(appointOrderViewResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("预约订单列表获取失败" + e.getMessage());
            pagedResultDto.makeResult(PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getValue(), PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getComment());
        }
        return pagedResultDto;
    }

    private ObjectResultDto<ParkingAppointRuleDetailViewResultDto> getAppointRule(Long parkingId, Date date) {
        ParkingAppointRuleGetByTimeRequestDto requestDto = new ParkingAppointRuleGetByTimeRequestDto();
        requestDto.setParkingId(parkingId);
        requestDto.setAppointTime(date);
        return parkingAppointRuleService.getAppointRuleTotalLByAppointTime(requestDto);
    }

    /**
     * 预约账单支付判断
     *
     * @param requestDto 请求参数
     */
    @Override
    public ObjectResultDto<AppointOrderCheckResultDto> payCheck(ParkingAppointOrderPayCheckRequestDto requestDto) {
        ObjectResultDto<AppointOrderCheckResultDto> objectResultDto = new ObjectResultDto<>();
        AppointOrderCheckResultDto appointOrderCheckResultDto = new AppointOrderCheckResultDto();
        appointOrderCheckResultDto.setPayCheck(Boolean.FALSE);
        //获取停车记录
        try {
            ParkingAppointOrderGetRequestDto appointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
            appointOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            appointOrderGetRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            ObjectResultDto<ParkingAppointmentOrderResultDto> parkingAppointmentOrderResultDto = platformOrderService.getAppointOrderApp(appointOrderGetRequestDto);
            if (parkingAppointmentOrderResultDto.isFailed() || parkingAppointmentOrderResultDto.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            }
            ParkingAppointmentOrderResultDto parkingAppointmentOrder = parkingAppointmentOrderResultDto.getData();
            if (parkingAppointmentOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())
                    || parkingAppointmentOrder.getPayStatus().equals(PayStatusEnum.PAY_FAILED.getValue())
                    || parkingAppointmentOrder.getPayStatus().equals(PayStatusEnum.WAITING_PAYMENT_RESULT.getValue())) {
                return objectResultDto.makeResult(PmsResultEnum.ORDER_PAYED.getValue(),
                        PmsResultEnum.ORDER_PAYED.getComment()
                );
            }
            if (parkingAppointmentOrder.getAppointStatus().equals(AppointStatusEnum.CANCELED.getValue())) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_STATUS_ERR.getValue(),
                        PmsResultEnum.APPOINT_STATUS_ERR.getComment()
                );
            }
            appointOrderCheckResultDto.setPayCheck(Boolean.TRUE);
            objectResultDto.setData(appointOrderCheckResultDto);
        } catch (Exception e) {
            log.error("预约订单支付判断失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto.success();
    }

    /**
     * 预约账单取消判断
     *
     * @param requestDto requestDto
     */
    @Override
    public ObjectResultDto<AppointOrderCancelCheckResultDto> cancelCheck(AppointOrderCancelCheckRequestDto requestDto) {
        ObjectResultDto<AppointOrderCancelCheckResultDto> objectResultDto = new ObjectResultDto<>();
        AppointOrderCancelCheckResultDto appointOrderCancelCheckResultDto = new AppointOrderCancelCheckResultDto();
        appointOrderCancelCheckResultDto.setCancelCheck(Boolean.FALSE);
        //获取停车记录
        try {
            ParkingAppointOrderGetRequestDto appointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
            appointOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            appointOrderGetRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            ObjectResultDto<ParkingAppointmentOrderResultDto> parkingAppointmentOrderResultDto = platformOrderService.getAppointOrderApp(appointOrderGetRequestDto);
            if (parkingAppointmentOrderResultDto.isFailed() || parkingAppointmentOrderResultDto.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        PmsResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            }
            ParkingAppointmentOrderResultDto parkingAppointmentOrder = parkingAppointmentOrderResultDto.getData();
            if (parkingAppointmentOrder.getAppointStatus().equals(AppointStatusEnum.CANCELED.getValue())) {
                return objectResultDto.makeResult(PmsResultEnum.APPOINT_STATUS_ERR.getValue(),
                        PmsResultEnum.APPOINT_STATUS_ERR.getComment()
                );
            }
            appointOrderCancelCheckResultDto.setCancelCheck(Boolean.TRUE);
            objectResultDto.setData(appointOrderCancelCheckResultDto);
        } catch (Exception e) {
            log.error("预约订单取消判断失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto.success();
    }


    /**
     * 云平台分页查询预约订单列表
     *
     * @param requestDto
     * @return
     */

    @Override
    public PagedResultDto<AppointOrderPageResultDto> getOrderPagedListAdmin(AppointOrderPagedRequestDto requestDto) {
        PagedResultDto<AppointOrderPageResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            if (StringUtils.isNotEmpty(requestDto.getAreaCode())) {
                ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
                ListResultDto<ParkingListGetResultDto> listGetResultDtoListResultDto = parkingInfoService.getParkingList(parkingListGetRequestDto);
                if (listGetResultDtoListResultDto.isSuccess() && !CollectionUtils.isEmpty(listGetResultDtoListResultDto.getItems())) {
                    List<ParkingListGetResultDto> list = listGetResultDtoListResultDto.getItems();
                    List<Long> parkingId = list.stream().distinct().
                            map(ParkingListGetResultDto::getId).collect(Collectors.toList());
                    requestDto.setParkingId(parkingId);
                }
            }
            pagedResultDto = parkingAppointmentOrderService.getOrderPagedListAdmin(requestDto);
        } catch (Exception e) {
            log.error("云平台分页查询预约订单列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * MQ关闭预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto closeAppointOrder(AppointOrderCloseRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Date now = new Date();
            ParkingAppointOrderGetRequestDto parkingAppointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
            parkingAppointOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<ParkingAppointmentOrderResultDto> appointObjectResultDto = platformOrderService.getAppointOrderApp(parkingAppointOrderGetRequestDto);
            if (appointObjectResultDto.isFailed() || null == appointObjectResultDto.getData()) {
                return resultDto.failed();
            }
            ParkingAppointmentOrderResultDto appointOrder = appointObjectResultDto.getData();
            if (!appointOrder.getAppointStatus().equals(AppointStatusEnum.CANCELED.getValue()) &&
                    (appointOrder.getPayStatus().equals(PayStatusEnum.CREATED.getValue()) ||
                            appointOrder.getPayStatus().equals(PayStatusEnum.PAY_WAITING.getValue()))) {

                if (appointOrder.getPayLimitTime().compareTo(now) <= 0) {
                    //根据bizOrderNo和type查询tradePaymentOrder
                    TradePaymentOrderGetRequestDto tradePaymentOrderGetRequestDto = new TradePaymentOrderGetRequestDto();
                    tradePaymentOrderGetRequestDto.setBizOrderType(BizTypeEnum.APPOINTMENT.getValue());
                    tradePaymentOrderGetRequestDto.setBizOrderNo(appointOrder.getOrderNo());
                    ObjectResultDto<TradePaymentOrderResultDto> tradeOrderResult = tradePaymentManagerService.getTradePaymentOrder(tradePaymentOrderGetRequestDto);
                    if (tradeOrderResult.isFailed() || null == tradeOrderResult.getData()) {
                        return resultDto.failed();
                    }
                    //根绝tradePaymentOrder的bizOrderNo查询最新的一条record
                    PaymentRecordGetByBizOrderRequestDto paymentRecordGetByBizOrderRequestDto = new PaymentRecordGetByBizOrderRequestDto();
                    paymentRecordGetByBizOrderRequestDto.setBizOrderType(BizTypeEnum.APPOINTMENT.getValue());
                    paymentRecordGetByBizOrderRequestDto.setBizOrderNo(tradeOrderResult.getData().getBizOrderNo());
                    ObjectResultDto<PaymentRecordGetResultDto> recordResult = tradePaymentQueryService.getByBizOrderNoAndType(paymentRecordGetByBizOrderRequestDto);
                    if (recordResult.isFailed() || null == recordResult.getData()) {
                        return resultDto.failed();
                    }
                    PaymentRecordGetResultDto paymentRecord = recordResult.getData();

                    //判断支付状态如果是success,更新订单状态
                    if (paymentRecord.getStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {

                        //更新tradePaymentOrder
                        TradePaymentUpdatePayStatusRequestDto tradePaymentUpdatePayStatusRequestDto = new TradePaymentUpdatePayStatusRequestDto();
                        tradePaymentUpdatePayStatusRequestDto.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                        tradePaymentUpdatePayStatusRequestDto.setSuccessPayTime(paymentRecord.getSucceedPayTime());
                        tradePaymentUpdatePayStatusRequestDto.setTransactionNo(paymentRecord.getTransactionNo());
                        tradePaymentUpdatePayStatusRequestDto.setOrderNo(paymentRecord.getOrderNo());

                        tradePaymentManagerService.updatePayStatusTradePaymentOrder(tradePaymentUpdatePayStatusRequestDto);
                        //更新微信订单
                        WeChatPayOrderNotifyResult weChatPayOrderNotifyResult = new WeChatPayOrderNotifyResult();
                        weChatPayOrderNotifyResult.setOutTradeNo(paymentRecord.getOrderNo());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String endTime = sdf.format(paymentRecord.getSucceedPayTime());
                        weChatPayOrderNotifyResult.setTimeEnd(endTime);
                        weChatPayOrderNotifyResult.setResultCode(WeixinTradeStatusEnum.SUCCESS.getValue());
                        tradePaymentManagerService.updateWeixinOrder(weChatPayOrderNotifyResult);

                        //更新预约订单
                        AppointOrderUpdateRequestDto appointOrderUpdateRequestDto = new AppointOrderUpdateRequestDto();
                        appointOrderUpdateRequestDto.setPayTime(paymentRecord.getSucceedPayTime());
                        appointOrderUpdateRequestDto.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                        appointOrderUpdateRequestDto.setAppointStatus(AppointStatusEnum.SUCCESS.getValue());
                        appointOrderUpdateRequestDto.setActualPayAmount(paymentRecord.getOrderAmount());
                        appointOrderUpdateRequestDto.setPayWay(paymentRecord.getPayWay());
                        appointOrderUpdateRequestDto.setPayType(paymentRecord.getPayType());
                        appointOrderUpdateRequestDto.setOrderNo(appointOrder.getOrderNo());
                        parkingAppointmentOrderService.updateAppointOrder(appointOrderUpdateRequestDto);

                        return resultDto.success();
                    }
                    //关闭当前时间大于支付截止时间的未支付订单
                    AppointOrderUpdateRequestDto appointOrderUpdateRequestDto = new AppointOrderUpdateRequestDto();
                    appointOrderUpdateRequestDto.setAppointStatus(AppointStatusEnum.CANCELED.getValue());
                    appointOrderUpdateRequestDto.setPayStatus(PayStatusEnum.PAY_TIMEOUT.getValue());
                    appointOrderUpdateRequestDto.setCancelTime(now);
                    appointOrderUpdateRequestDto.setCancelReason("后台关闭支付超时预约记录");
                    appointOrderUpdateRequestDto.setOrderNo(appointOrder.getOrderNo());
                    parkingAppointmentOrderService.updateAppointOrder(appointOrderUpdateRequestDto);
                    log.info("关闭超时未支付预约记录，单号：" + appointOrder.getOrderNo() + "用户：" + appointOrder.getCustomerUserId() + "停车场：" + appointOrder.getParkingId());
                }
                //关闭当前时间大于预计入场时间的未支付订单
               /* if (appointOrder.getScheduleTime().before(now)) {
                    AppointOrderUpdateRequestDto appointOrderUpdateRequestDto = new AppointOrderUpdateRequestDto();
                    appointOrderUpdateRequestDto.setPayStatus(PayStatusEnum.PAY_TIMEOUT.getValue());
                    appointOrderUpdateRequestDto.setAppointStatus(AppointStatusEnum.CANCELED.getValue());
                    appointOrderUpdateRequestDto.setCancelTime(now);
                    appointOrderUpdateRequestDto.setCancelReason("后台关闭入场超时预约记录");
                    appointOrderUpdateRequestDto.setOrderNo(appointOrder.getOrderNo());
                    parkingAppointmentOrderService.updateAppointOrder(appointOrderUpdateRequestDto);
                    log.info("关闭超时未进场预约记录，单号：" + appointOrder.getOrderNo() + "用户：" + appointOrder.getCustomerUserId() + "停车场：" + appointOrder.getParkingId());
                    return resultDto.success();
                }*/
            }
          /*  if (appointOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue()) && appointOrder.getAppointStatus().equals(AppointStatusEnum.SUCCESS.getValue())) {
                if(appointOrder.getDeadlineTime().before(now)){
                    AppointOrderUpdateRequestDto orderUpdate = new AppointOrderUpdateRequestDto();
                    orderUpdate.setAppointStatus(AppointStatusEnum.CANCELED.getValue());
                    orderUpdate.setCancelTime(now);
                    orderUpdate.setCancelReason("后台关闭超时未进场预约记录");
                    orderUpdate.setOrderNo(appointOrder.getOrderNo());
                    parkingAppointmentOrderService.updateAppointOrder(orderUpdate);
                    log.info("关闭超时未进场预约记录，单号：" + appointOrder.getOrderNo() + "用户：" + appointOrder.getCustomerUserId() + "停车场：" + appointOrder.getParkingId());
                }
            }*/
            resultDto.success();
        } catch (Exception e) {
            log.error("MQ关闭预约订单失败" + e.getMessage());
            resultDto.makeResult(OrderResultEnum.CLOSE_ORDER_ERR.getValue(),
                    OrderResultEnum.CLOSE_ORDER_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 微信关闭订单
     *
     * @param requestDto requestDto
     * @return
     */
    private ObjectResultDto<WeChatPayCloseOrderResult> closeWechatOrder(WeChatCloseOrderParams requestDto) {
        ObjectResultDto<WeChatPayCloseOrderResult> objectResultDto = new ObjectResultDto<>();
        try {
            requestDto.setNonceStr(StringUtils.getUUID());
            requestDto.setMchId(sealParam(null, ParamTypeEnum.WEIXINPAY.getValue(), "wechatPayMchId"));
            requestDto.setAppid(sealParam(null, ParamTypeEnum.WEIXINPAY.getValue(), "wechatPayAppId"));
            requestDto.setSignKey(sealParam(null, ParamTypeEnum.WEIXINPAY.getValue(), "wechatPaySignKey"));
            return weChatPayService.closeOrder(requestDto);
        } catch (Exception e) {
            log.error("微信关闭订单失败" + e.getMessage());
            objectResultDto.makeResult(OrderResultEnum.CLOSE_ORDER_ERR.getValue(),
                    OrderResultEnum.CLOSE_ORDER_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 查询参数值
     *
     * @return
     */
    private String sealParam(Long tenantId, Integer type, String paramKey) {
        ParamGetRequestDto paramGetRequestDto = new ParamGetRequestDto();
        paramGetRequestDto.setTenantId(tenantId);
        paramGetRequestDto.setType(type);
        paramGetRequestDto.setParamKey(paramKey);
        ObjectResultDto<ParamGetResultDto> paramGetResultDto = parameterService.paramGet(paramGetRequestDto);
        if (paramGetResultDto.isSuccess() && null != paramGetResultDto.getData()) {
            return paramGetResultDto.getData().getParamValue();
        }
        return null;
    }

    /**
     * 微信查询订单
     *
     * @param requestDto requestDto
     * @return
     */
    private ObjectResultDto<WeChatPayOrderQueryResult> queryWechatOrder(WeChatOrderQueryParams requestDto) {
        ObjectResultDto<WeChatPayOrderQueryResult> objectResultDto = new ObjectResultDto<>();
        try {
            requestDto.setNonceStr(StringUtils.getUUID());
            requestDto.setMchId(sealParam(null, ParamTypeEnum.WEIXINPAY.getValue(), "wechatPayMchId"));
            requestDto.setAppid(sealParam(null, ParamTypeEnum.WEIXINPAY.getValue(), "wechatPayAppId"));
            requestDto.setSignKey(sealParam(null, ParamTypeEnum.WEIXINPAY.getValue(), "wechatPaySignKey"));
            return weChatPayService.orderQuery(requestDto);
        } catch (Exception e) {
            log.error("微信查询订单失败" + e.getMessage());
            objectResultDto.makeResult(OrderResultEnum.CLOSE_ORDER_ERR.getValue(),
                    OrderResultEnum.CLOSE_ORDER_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * MQ处理支付记录
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto tradePaymentRecord(TradePaymentRecordRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            PaymentRecordGetByOrderNoRequestDto paymentRecordGetByBizOrderRequestDto = new PaymentRecordGetByOrderNoRequestDto();
            paymentRecordGetByBizOrderRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<PaymentRecordResultDto> recordResult = tradePaymentQueryService.getTradePaymentRecordByOrderNo(paymentRecordGetByBizOrderRequestDto);
            if (recordResult.isFailed() || null == recordResult.getData()) {
                return resultDto.failed();
            }
            PaymentRecordResultDto paymentRecord = recordResult.getData();
            //如果record状态不是支付成功则需要做关单处理
            if (!paymentRecord.getStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                if (paymentRecord.getPayWay().equals(PayWayEnum.ALIPAY.getValue())) {
                    //支付宝下单并未在支付宝平台真正下单，不需要关单
                    //若record状态不为已关闭则更新tradepaymentrecord表支付状态为关闭
                    if (!paymentRecord.getStatus().equals(PayStatusEnum.PAY_CANCELED.getValue())) {
                        TradePaymentUpdatePayStatusRequestDto tradePaymentUpdateRequestDto = new TradePaymentUpdatePayStatusRequestDto();
                        tradePaymentUpdateRequestDto.setOrderNo(paymentRecord.getOrderNo());
                        tradePaymentUpdateRequestDto.setPayStatus(PayStatusEnum.PAY_CANCELED.getValue());
                        tradePaymentManagerService.updatePayStatusTradePaymentRecord(tradePaymentUpdateRequestDto);

                        //更新支付宝订单表支付状态为关闭
                        AlipayAsyncNotifyResultRequestDto alipayAsyncNotifyResultRequestDto = new AlipayAsyncNotifyResultRequestDto();
                        alipayAsyncNotifyResultRequestDto.setTradeStatus(AlipayTradeStatusEnum.TRADE_CLOSED.name());
                        tradePaymentManagerService.updateAlipayOrder(alipayAsyncNotifyResultRequestDto);
                    } else if (paymentRecord.getPayWay().equals(PayWayEnum.WEIXINPAY.getValue())) {
                        //向微信平台查询微信订单状态
                        WeChatOrderQueryParams weChatOrderQueryParams = new WeChatOrderQueryParams();
                        weChatOrderQueryParams.setOutTradeNo(paymentRecord.getOrderNo());
                        ObjectResultDto<WeChatPayOrderQueryResult> queryResult = queryWechatOrder(weChatOrderQueryParams);
                        if (queryResult.isFailed() || null == queryResult.getData()) {
                            return resultDto.failed();
                        }
                        WeChatPayOrderQueryResult queryWechat = queryResult.getData();
                        if (queryWechat.getTradeState().equals(WeixinTradeStatusEnum.SUCCESS.getValue())) {
                            //微信查询结果为支付成功，维护平台订单
                            Date succeedPayTime = DateUtils.parseDate(queryWechat.getTimeEnd(), "yyyyMMddHHmmss");
                            String transactionNo = queryWechat.getTransactionId();

                            //更新tradepaymentrecord表为成功
                            TradePaymentUpdatePayStatusRequestDto tradePaymentUpdatePayStatusRequestDto = new TradePaymentUpdatePayStatusRequestDto();
                            tradePaymentUpdatePayStatusRequestDto.setSuccessPayTime(succeedPayTime);
                            tradePaymentUpdatePayStatusRequestDto.setTransactionNo(paymentRecord.getTransactionNo());
                            tradePaymentUpdatePayStatusRequestDto.setOrderNo(paymentRecord.getOrderNo());
                            tradePaymentUpdatePayStatusRequestDto.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());

                            tradePaymentManagerService.updatePayStatusTradePaymentRecord(tradePaymentUpdatePayStatusRequestDto);
                            //更新tradepaymentorder表为成功
                            //修改支付订单状态
                            TradePaymentOrderGetRequestDto tradePaymentOrderGetRequestDto = new TradePaymentOrderGetRequestDto();
                            tradePaymentOrderGetRequestDto.setBizOrderType(BizTypeEnum.APPOINTMENT.getValue());
                            tradePaymentOrderGetRequestDto.setBizOrderNo(paymentRecord.getBizOrderNo());
                            ObjectResultDto<TradePaymentOrderResultDto> payOrderObjectResultDto = tradePaymentManagerService.getTradePaymentOrder(tradePaymentOrderGetRequestDto);
                            TradePaymentOrderResultDto tradePaymentOrder = payOrderObjectResultDto.getData();

                            TradePaymentUpdatePayStatusRequestDto tradePaymentOrderUpdatePayStatusRequestDto = new TradePaymentUpdatePayStatusRequestDto();
                            tradePaymentOrderUpdatePayStatusRequestDto.setSuccessPayTime(succeedPayTime);
                            tradePaymentOrderUpdatePayStatusRequestDto.setTransactionNo(transactionNo);
                            tradePaymentOrderUpdatePayStatusRequestDto.setOrderNo(tradePaymentOrder.getOrderNo());
                            tradePaymentOrderUpdatePayStatusRequestDto.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                            tradePaymentManagerService.updatePayStatusTradePaymentOrder(tradePaymentOrderUpdatePayStatusRequestDto);

                            //更新微信订单
                            WeChatPayOrderNotifyResult weChatPayOrderNotifyResult = modelMapper.map(queryWechat, WeChatPayOrderNotifyResult.class);
                            tradePaymentManagerService.updateWeixinOrder(weChatPayOrderNotifyResult);

                            //更新预约订单
                            AppointOrderUpdateRequestDto appointOrderUpdateRequestDto = new AppointOrderUpdateRequestDto();
                            appointOrderUpdateRequestDto.setPayTime(succeedPayTime);
                            appointOrderUpdateRequestDto.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                            appointOrderUpdateRequestDto.setAppointStatus(AppointStatusEnum.SUCCESS.getValue());
                            appointOrderUpdateRequestDto.setActualPayAmount(queryWechat.getTotalFee());
                            appointOrderUpdateRequestDto.setPayWay(paymentRecord.getPayWay());
                            appointOrderUpdateRequestDto.setPayType(paymentRecord.getPayType());
                            appointOrderUpdateRequestDto.setOrderNo(paymentRecord.getBizOrderNo());
                            parkingAppointmentOrderService.updateAppointOrder(appointOrderUpdateRequestDto);

                            return resultDto.success();

                        } else if (queryWechat.getTradeState().equals(WeixinTradeStatusEnum.CLOSED.getValue())) {
                            //微信已关单，只需关闭平台订单
                            //若record状态不为已关闭则更新tradepaymentrecord表支付状态为关闭
                            if (!paymentRecord.getStatus().equals(PayStatusEnum.PAY_CANCELED.getValue())) {

                                TradePaymentUpdatePayStatusRequestDto tradePaymentUpdatePayStatusRequestDto = new TradePaymentUpdatePayStatusRequestDto();
                                tradePaymentUpdatePayStatusRequestDto.setOrderNo(paymentRecord.getOrderNo());
                                tradePaymentUpdatePayStatusRequestDto.setPayStatus(PayStatusEnum.PAY_CANCELED.getValue());
                                tradePaymentManagerService.updatePayStatusTradePaymentRecord(tradePaymentUpdatePayStatusRequestDto);
                            }
                        }

                        //更新微信订单表支付状态为关闭
                        WeChatPayOrderNotifyResult weChatPayOrderNotifyResult = new WeChatPayOrderNotifyResult();
                        weChatPayOrderNotifyResult.setResultCode(WeixinTradeStatusEnum.CLOSED.getValue());
                        tradePaymentManagerService.updateWeixinOrder(weChatPayOrderNotifyResult);
                    } else {
                        //微信关单
                        WeChatCloseOrderParams weChatCloseOrderParams = new WeChatCloseOrderParams();
                        weChatCloseOrderParams.setOutTradeNo(paymentRecord.getOrderNo());
                        ObjectResultDto<WeChatPayCloseOrderResult> closeResult = closeWechatOrder(weChatCloseOrderParams);
                        if (closeResult.isFailed() || null == closeResult.getData()) {
                            return resultDto.failed();
                        }
                        //若record状态不为已关闭则更新tradepaymentrecord表支付状态为关闭
                        if (!paymentRecord.getStatus().equals(PayStatusEnum.PAY_CANCELED.getValue())) {

                            TradePaymentUpdatePayStatusRequestDto tradePaymentUpdatePayStatusRequestDto = new TradePaymentUpdatePayStatusRequestDto();
                            tradePaymentUpdatePayStatusRequestDto.setOrderNo(paymentRecord.getOrderNo());
                            tradePaymentUpdatePayStatusRequestDto.setPayStatus(PayStatusEnum.PAY_CANCELED.getValue());
                            tradePaymentManagerService.updatePayStatusTradePaymentRecord(tradePaymentUpdatePayStatusRequestDto);
                        }

                        //更新微信订单表支付状态为关闭
                        WeChatPayOrderNotifyResult weChatPayOrderNotifyResult = new WeChatPayOrderNotifyResult();
                        weChatPayOrderNotifyResult.setResultCode(WeixinTradeStatusEnum.CLOSED.getValue());
                        tradePaymentManagerService.updateWeixinOrder(weChatPayOrderNotifyResult);
                    }
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("MQ处理支付记录失败" + e.getMessage());
            resultDto.makeResult(OrderResultEnum.OPERATE_RECORD_ERR.getValue(),
                    OrderResultEnum.OPERATE_RECORD_ERR.getComment());
        }
        return resultDto;
    }
}
