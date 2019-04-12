package com.zoeeasy.cloud.integration.service.impl;

import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.core.enums.ChargeModeEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.integration.order.CustomerParkingOrderIntegrationService;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderDetailViewResultDto;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;
import com.zoeeasy.cloud.order.appoint.AppointmentOrderManagerService;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderGetRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.parking.PlatformParkingOrderService;
import com.zoeeasy.cloud.order.parking.dto.request.*;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderViewResultDto;
import com.zoeeasy.cloud.pms.park.ParkingQrcodeService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingChargeInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.PlatformProcessService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLocationGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingQrcodeGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordImageGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingChargeInfoResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingImageViewResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingLocationResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingQrcodeResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 车主用户订单集成服务
 *
 * @author wangfeng
 * @date 2018/10/31 16:57
 **/
@Service(value = "customerParkOrderIntegrationService")
@Slf4j
public class CustomerParkingOrderIntegrationServiceImpl implements CustomerParkingOrderIntegrationService {

    @Autowired
    private AppointmentOrderManagerService parkingAppointmentOrderService;

    @Autowired
    private PlatformProcessService platformProcessService;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private PlatformParkingOrderService platformParkingOrderService;

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    @Autowired
    private ParkingQrcodeService parkingQrcodeService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 分页获取用户停车订单
     *
     * @param requestDto ParkingOrderQueryByPlatePageRequestDto
     * @return ParkingOrderViewResultDto
     */
    @Override
    public PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageList(ParkingOrderQueryByPlatePageRequestDto requestDto) {
        PagedResultDto<ParkingOrderViewResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            PagedResultDto<ParkingOrderResultDto> parkingOrderPage = platformParkingOrderService.getParkingOrderPageList(requestDto);
            if (parkingOrderPage.isSuccess() && null != parkingOrderPage.getItems()) {
                List<ParkingOrderResultDto> records = parkingOrderPage.getItems();
                List<ParkingOrderViewResultDto> parkingResultDtoList = new ArrayList<>();
                for (ParkingOrderResultDto parkingOrder : records) {
                    ParkingOrderViewResultDto parkingOrderViewResultDto = buildParkingOrderView(parkingOrder);
                    parkingResultDtoList.add(parkingOrderViewResultDto);
                }
                pagedResultDto.setPageNo(parkingOrderPage.getPageNo());
                pagedResultDto.setPageSize(parkingOrderPage.getPageSize());
                pagedResultDto.setTotalCount(parkingOrderPage.getTotalCount());
                pagedResultDto.setItems(parkingResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取停车订单失败,异常信息{}", e.getMessage(),e);
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取停车账单列表
     *
     * @param requestDto ParkingOrderListGetRequestDto
     * @return ParkingOrderViewResultDto列表
     */
    @Override
    public ListResultDto<ParkingOrderViewResultDto> getParkingOrderList(ParkingOrderListGetRequestDto requestDto) {
        ListResultDto<ParkingOrderViewResultDto> listResultDto = new ListResultDto<>();
        try {
            ListResultDto<ParkingOrderResultDto> parkingOrderList = platformParkingOrderService.getParkingOrderList(requestDto);
            if (parkingOrderList.isSuccess() && CollectionUtils.isNotEmpty(parkingOrderList.getItems())) {
                List<ParkingOrderViewResultDto> parkingResultDtoList = new ArrayList<>();
                for (ParkingOrderResultDto parkingOrder : parkingOrderList.getItems()) {
                    ParkingOrderViewResultDto parkingOrderViewResultDto = buildParkingOrderView(parkingOrder);
                    parkingResultDtoList.add(parkingOrderViewResultDto);
                }
                listResultDto.setItems(parkingResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户停车订单失败:{}", e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取停车账单详情
     *
     * @param requestDto ParkingOrderDetailGetRequestDto
     * @return ParkingOrderDetailViewResultDto
     */
    @Override
    public ObjectResultDto<ParkingOrderDetailViewResultDto> getParkingOrderDetailView(ParkingOrderDetailGetRequestDto requestDto) {
        ObjectResultDto<ParkingOrderDetailViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
            parkingOrderGetByOrderNoRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetByOrderNoRequestDto);
            if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
                objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment());
            } else {
                ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
                ParkingOrderDetailViewResultDto parkingOrderViewResultDto = buildParkingOrderDetailView(parkingOrder);
                if (parkingOrderViewResultDto != null) {
                    objectResultDto.setData(parkingOrderViewResultDto);
                    objectResultDto.success();
                }
            }
        } catch (Exception e) {
            log.error("获取停车订单失败,异常信息{}，堆栈信息{}", e.getMessage(), e.getStackTrace());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 创建账单信息视图对象
     *
     * @param parkingOrder parkingOrder
     * @return ParkingOrderViewResultDto
     */
    private ParkingOrderViewResultDto buildParkingOrderView(ParkingOrderResultDto parkingOrder) {
        ParkingOrderViewResultDto parkingOrderViewResultDto = new ParkingOrderViewResultDto();
        parkingOrderViewResultDto.setOrderNo(parkingOrder.getOrderNo());
        parkingOrderViewResultDto.setParkingName(parkingOrder.getParkingName());
        parkingOrderViewResultDto.setParkingLotCode("");
        parkingOrderViewResultDto.setParkingLotNumber(parkingOrder.getParkingLotNumber());
        //停车开始时间
        if (parkingOrder.getStartTime() != null) {
            parkingOrderViewResultDto.setParkingTime(
                    DateUtils.formatDate(parkingOrder.getStartTime(), DateUtils.DATE_FORMAT));
            parkingOrderViewResultDto.setStartTime(
                    DateUtils.formatDate(parkingOrder.getStartTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
        }
        //停车中,车辆未出场
        if (parkingOrder.getEndTime() == null || parkingOrder.getEndTime().compareTo(DateTimeUtils.getDateMax()) == 0) {
            Date now = DateUtils.now();
            parkingOrderViewResultDto.setEndTime("");
            //停车时长
            long timeMillis = now.getTime() - parkingOrder.getStartTime().getTime();
            parkingOrderViewResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
            //停车费用
            BigDecimal payableAmount = BigDecimal.ZERO;
            //如果未结算则试算停车费用
            if (!PayStatusEnum.PAY_SUCCESS.getValue().equals(parkingOrder.getPayStatus())) {
                ParkingAmountCalculateRequestDto parkingAmountCalculateRequestDto = new ParkingAmountCalculateRequestDto();
                parkingAmountCalculateRequestDto.setParkingId(parkingOrder.getParkingId());
                parkingAmountCalculateRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
                parkingAmountCalculateRequestDto.setCarStyle(parkingOrder.getCarStyle());
                parkingAmountCalculateRequestDto.setStartTime(parkingOrder.getStartTime());
                parkingAmountCalculateRequestDto.setEndTime(now);
                ObjectResultDto<CalculateAmountResultDto> parkingAmountCalculateResult =
                        calculateIntegrationService.calculateAmount(parkingAmountCalculateRequestDto);
                if (parkingAmountCalculateResult.isSuccess() && parkingAmountCalculateResult.getData() != null) {
                    CalculateAmountResultDto parkingAmountCalculate = parkingAmountCalculateResult.getData();
                    //单位换算为元
                    payableAmount = NumberUtils.amountFen2Yuan(parkingAmountCalculate.getAmount());
                }
            } else {
                //单位换算为元
                payableAmount = NumberUtils.amountFen2Yuan(parkingOrder.getPayableAmount());
            }
//            payableAmount = amountFormat(payableAmount);
            if (parkingOrder.getChargeMode().equals(ChargeModeEnum.BEFORE.getValue())) {
                //先缴费后离场
                getPaymentInfo(parkingOrder, parkingOrderViewResultDto, payableAmount);
            } else if (parkingOrder.getChargeMode().equals(ChargeModeEnum.AFTER.getValue())) {
                //先离场后缴费
                //不允许支付
                parkingOrderViewResultDto.setPayable(Boolean.FALSE);
//                parkingOrderViewResultDto.setNeedPay(Boolean.FALSE);
                parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
            }
        } else {
            //已经离场
            //停车时长
            long timeMillis = parkingOrder.getEndTime().getTime() - parkingOrder.getStartTime().getTime();
            parkingOrderViewResultDto.setEndTime(DateUtils.formatDate(parkingOrder.getEndTime(),
                    DateUtils.DEFAULT_DATE_TIME_FORMAT));
            parkingOrderViewResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
            //应付金额
            BigDecimal payableAmount = NumberUtils.amountFen2Yuan(parkingOrder.getPayableAmount());
//            payableAmount = amountFormat(payableAmount);
            getPaymentInfo(parkingOrder, parkingOrderViewResultDto, payableAmount);
        }
        //实付金额
        BigDecimal actualPayAmount = NumberUtils.amountFen2Yuan(parkingOrder.getActualPayAmount());
        parkingOrderViewResultDto.setActualPayAmount(NumberUtils.formatAmountYuan(actualPayAmount));
        //停车状态
        parkingOrderViewResultDto.setStatus(parkingOrder.getStatus());
        //支付状态
        parkingOrderViewResultDto.setPayStatus(parkingOrder.getPayStatus());
        if (parkingOrder.getPayTime() != null) {
            parkingOrderViewResultDto.setPayTime(DateUtils.formatDate(parkingOrder.getEndTime(),
                    DateUtils.DEFAULT_DATE_TIME_FORMAT));
        } else {
            parkingOrderViewResultDto.setPayTime("");
        }
        parkingOrderViewResultDto.setPlateColor(parkingOrder.getPlateColor());
        parkingOrderViewResultDto.setPlateNumber(parkingOrder.getPlateNumber());
        parkingOrderViewResultDto.setCarStyle(parkingOrder.getCarStyle());
        return parkingOrderViewResultDto;
    }

    /**
     * 创建停车账单详情信息视图对象
     *
     * @param parkingOrder parkingOrder
     * @return ParkingOrderDetailViewResultDto
     */
    private ParkingOrderDetailViewResultDto buildParkingOrderDetailView(ParkingOrderResultDto parkingOrder) {
        ParkingOrderDetailViewResultDto parkingOrderViewResultDto = new ParkingOrderDetailViewResultDto();
        parkingOrderViewResultDto.setOrderNo(parkingOrder.getOrderNo());
        parkingOrderViewResultDto.setParkingName(parkingOrder.getParkingName());
        parkingOrderViewResultDto.setParkingLotCode("");
        parkingOrderViewResultDto.setParkingLotNumber(parkingOrder.getParkingLotNumber());

        if (parkingOrder.getStartTime() != null) {
            parkingOrderViewResultDto.setParkingTime(DateUtils.formatDate(parkingOrder.getStartTime(),
                    DateUtils.DATE_FORMAT));
            parkingOrderViewResultDto.setStartTime(DateUtils.formatDate(parkingOrder.getStartTime(),
                    DateUtils.DEFAULT_DATE_TIME_FORMAT));
        }
        //停车中
        if (parkingOrder.getEndTime() == null || parkingOrder.getEndTime().compareTo(DateTimeUtils.getDateMax()) == 0) {
            Date now = DateUtils.now();
            long timeMillis = now.getTime() - parkingOrder.getStartTime().getTime();
            //停车中不显示离场时间
            parkingOrderViewResultDto.setEndTime("");
            //停车时长
            parkingOrderViewResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
            //限免停车
            parkingOrderViewResultDto.setLimitFree(parkingOrder.getLimitFree());
            //停车费用
            BigDecimal payableAmount = BigDecimal.ZERO;
            //如果未结算则试算停车费用
            if (!parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                ParkingAmountCalculateRequestDto parkingAmountCalculateRequestDto = new ParkingAmountCalculateRequestDto();
                parkingAmountCalculateRequestDto.setParkingId(parkingOrder.getParkingId());
                parkingAmountCalculateRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
                parkingAmountCalculateRequestDto.setCarStyle(parkingOrder.getCarStyle());
                parkingAmountCalculateRequestDto.setStartTime(parkingOrder.getStartTime());
                parkingAmountCalculateRequestDto.setEndTime(now);
                CalculateAmountResultDto parkingAmountCalculate = calculateAmount(parkingAmountCalculateRequestDto);
                if (parkingAmountCalculate != null) {
                    //单位换算为元
                    payableAmount = NumberUtils.amountFen2Yuan(parkingAmountCalculate.getAmount());
                    //如果是限免停车
                    parkingOrderViewResultDto.setLimitFree(parkingOrder.getLimitFree());
                    if (parkingOrder.getLimitFree()) {
                        //免费时长
                        parkingOrderViewResultDto.setFreeLength(DateUtils.formatDateTimeChinese(
                                parkingAmountCalculate.getFreeDuration() * 1000L));
                        //如果收费时长不大于0
                        if (parkingAmountCalculate.getChargeDuration().compareTo(0L) <= 0 ||
                                parkingAmountCalculate.getAmount() <= 0) {
                            parkingOrderViewResultDto.setChargeLength("未超时");
                        } else {
                            parkingOrderViewResultDto.setChargeLength(DateUtils.formatDateTimeChinese(
                                    parkingAmountCalculate.getChargeDuration() * 1000L));
                        }
                    } else {
                        parkingOrderViewResultDto.setFreeLength("");
                        parkingOrderViewResultDto.setChargeLength("");
                    }
                } else {
                    parkingOrderViewResultDto.setFreeLength("");
                    parkingOrderViewResultDto.setChargeLength("");
                }
            } else {
                //单位换算为元
                payableAmount = NumberUtils.amountFen2Yuan(parkingOrder.getPayableAmount());
                //如果是限免停车
                parkingOrderViewResultDto.setLimitFree(parkingOrder.getLimitFree());
                if (parkingOrder.getLimitFree()) {
                    //免费时长
                    parkingOrderViewResultDto.setFreeLength(DateUtils.formatDateTimeChinese(
                            parkingOrder.getFreeLength() * 1000L));
                    //如果收费时长不大于0
                    if (parkingOrder.getChargeLength() <= 0) {
                        parkingOrderViewResultDto.setChargeLength("未超时");
                    } else {
                        parkingOrderViewResultDto.setChargeLength(DateUtils.formatDateTimeChinese(
                                parkingOrder.getChargeLength() * 1000L));
                    }
                } else {
                    parkingOrderViewResultDto.setFreeLength("");
                    parkingOrderViewResultDto.setChargeLength("");
                }
            }

            if (parkingOrder.getChargeMode().equals(ChargeModeEnum.BEFORE.getValue())) {
                // 先缴费后离场
                //如果未支付，则可以支付
//                payableAmount = amountFormat(payableAmount);
                if (!parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                    //金额大于0则可以支付
                    if (payableAmount.compareTo(BigDecimal.ZERO) > 0) {
                        parkingOrderViewResultDto.setPayable(Boolean.TRUE);
                        parkingOrderViewResultDto.setNeedPay(Boolean.TRUE);
                        parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
                    } else {
                        parkingOrderViewResultDto.setPayable(Boolean.FALSE);
                        parkingOrderViewResultDto.setNeedPay(Boolean.FALSE);
                        parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
                    }
                } else {
                    //已经支付，则无需支付
                    parkingOrderViewResultDto.setPayable(Boolean.FALSE);
                    parkingOrderViewResultDto.setNeedPay(Boolean.FALSE);
                    //单位换算为元
                    parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
                    parkingOrderViewResultDto.setActualPayAmount(NumberUtils.formatAmountYuan
                            (BigDecimal.valueOf(parkingOrder.getActualPayAmount())
                                    .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP)));
                }
            } else if (parkingOrder.getChargeMode().equals(ChargeModeEnum.AFTER.getValue())) {
                //先离场后缴费
                //不允许支付
                parkingOrderViewResultDto.setPayable(Boolean.FALSE);
                parkingOrderViewResultDto.setNeedPay(Boolean.FALSE);
                parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
            }
        } else {
            //已经离场
            parkingOrderViewResultDto.setEndTime(DateUtils.formatDate(parkingOrder.getEndTime(),
                    DateUtils.DEFAULT_DATE_TIME_FORMAT));
            //停车时长
            long timeMillis = parkingOrder.getEndTime().getTime() - parkingOrder.getStartTime().getTime();
            parkingOrderViewResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
            //如果是限免停车
            parkingOrderViewResultDto.setLimitFree(parkingOrder.getLimitFree());
            if (parkingOrder.getLimitFree()) {
                //免费时长
                parkingOrderViewResultDto.setFreeLength(DateUtils.formatDateTimeChinese(
                        parkingOrder.getFreeLength() * 1000L));
                //如果收费时长不大于0
                if (parkingOrder.getChargeLength() <= 0
                        || parkingOrder.getPayableAmount() <= 0) {
                    parkingOrderViewResultDto.setChargeLength("未超时");
                } else {
                    parkingOrderViewResultDto.setChargeLength(DateUtils.formatDateTimeChinese(
                            parkingOrder.getChargeLength() * 1000L));
                }
            } else {
                parkingOrderViewResultDto.setFreeLength("");
                parkingOrderViewResultDto.setChargeLength("");
            }
            //单位换算为元
            BigDecimal payableAmount = NumberUtils.amountFen2Yuan(parkingOrder.getPayableAmount());
            //如果未支付，则可以支付
            if (!parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                //订单金额
                parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
                //金额大于0则可以支付
                if (parkingOrder.getPayableAmount().compareTo(0) > 0) {
                    parkingOrderViewResultDto.setPayable(Boolean.TRUE);
                    parkingOrderViewResultDto.setNeedPay(Boolean.TRUE);
                } else {
                    parkingOrderViewResultDto.setPayable(Boolean.FALSE);
                    parkingOrderViewResultDto.setNeedPay(Boolean.FALSE);
                }
            } else {
                //已经支付，则无需支付
                parkingOrderViewResultDto.setPayable(Boolean.FALSE);
                parkingOrderViewResultDto.setNeedPay(Boolean.FALSE);
                //单位换算为元
                parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
            }
        }
        //实付金额
        BigDecimal actualPayAmount = NumberUtils.amountFen2Yuan(parkingOrder.getActualPayAmount());
        parkingOrderViewResultDto.setActualPayAmount(NumberUtils.formatAmountYuan(actualPayAmount));

        //是否预约停车
        if (parkingOrder.getAppointed()) {
            ParkingAppointOrderGetRequestDto parkingAppointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
            parkingAppointOrderGetRequestDto.setOrderNo(parkingOrder.getAppointOrderNo());
            ObjectResultDto<ParkingAppointmentOrderResultDto> parkingAppointOrderResult =
                    parkingAppointmentOrderService.getAppointOrder(parkingAppointOrderGetRequestDto);
            if (parkingAppointOrderResult.isSuccess() && parkingAppointOrderResult.getData() != null) {
                ParkingAppointmentOrderResultDto parkingAppointOrder = parkingAppointOrderResult.getData();
                parkingOrderViewResultDto.setAppointed(Boolean.TRUE);
                parkingOrderViewResultDto.setAppointTime(DateUtils.formatDate(parkingAppointOrder.getScheduleTime(),
                        DateUtils.DEFAULT_DATE_TIME_FORMAT));
                parkingOrderViewResultDto.setAppointFee(NumberUtils.formatAmountYuan(
                        BigDecimal.valueOf(parkingAppointOrder.getPayAmount()).divide(BigDecimal.valueOf(100))));
            } else {
                parkingOrderViewResultDto.setAppointed(Boolean.FALSE);
                parkingOrderViewResultDto.setAppointTime(null);
                parkingOrderViewResultDto.setAppointFee(null);
            }
        } else {
            parkingOrderViewResultDto.setAppointed(Boolean.FALSE);
            parkingOrderViewResultDto.setAppointTime(null);
            parkingOrderViewResultDto.setAppointFee(null);
        }
        //获取收费详情
        ParkingChargeInfoGetByIdRequestDto parkingChargeInfoGetByIdRequestDto = new ParkingChargeInfoGetByIdRequestDto();
        parkingChargeInfoGetByIdRequestDto.setId(parkingOrder.getChargeInfoId());
        ObjectResultDto<ParkingChargeInfoResultDto> chargeInfoResultDto =
                platformParkingInfoService.getParkChargeInfoById(parkingChargeInfoGetByIdRequestDto);
        if (chargeInfoResultDto.isSuccess() && chargeInfoResultDto.getData() != null) {
            parkingOrderViewResultDto.setChargeDescription(chargeInfoResultDto.getData().getChargeDescription());
        }
        parkingOrderViewResultDto.setStatus(parkingOrder.getStatus());
        parkingOrderViewResultDto.setPayStatus(parkingOrder.getPayStatus());
        if (parkingOrder.getPayTime() != null) {
            parkingOrderViewResultDto.setPayTime(
                    DateUtils.formatDate(parkingOrder.getEndTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
        }
        parkingOrderViewResultDto.setPlateColor(parkingOrder.getPlateColor());
        parkingOrderViewResultDto.setPlateNumber(parkingOrder.getPlateNumber());
        parkingOrderViewResultDto.setCarStyle(parkingOrder.getCarStyle());
        //获取停车场地址
        ParkingLocationGetRequestDto parkingLocationGetRequestDto = new ParkingLocationGetRequestDto();
        parkingLocationGetRequestDto.setParkingId(parkingOrder.getParkingId());
        ObjectResultDto<ParkingLocationResultDto> parkingAddress =
                platformParkingInfoService.getParkingAddress(parkingLocationGetRequestDto);
        if (parkingAddress.isSuccess() && null != parkingAddress.getData()) {
            String address = parkingAddress.getData().getAddress();
            parkingOrderViewResultDto.setParkingAddress(address);
        }
        //获取停车图像
        ParkingRecordImageGetRequestDto parkingRecordImageGetRequestDto = new ParkingRecordImageGetRequestDto();
        parkingRecordImageGetRequestDto.setRecordNo(parkingOrder.getRecordNo());
        ListResultDto<ParkingImageViewResultDto> imageListResultDto =
                platformProcessService.getParkingRecordImageList(parkingRecordImageGetRequestDto);
        if (imageListResultDto.isSuccess()) {
            parkingOrderViewResultDto.setImages(imageListResultDto.getItems());
        }
        return parkingOrderViewResultDto;
    }

    /**
     * 构建支付相关信息
     *
     * @param parkingOrder              ParkingOrderViewBuildDto
     * @param parkingOrderViewResultDto ParkingOrderViewResultDto
     * @param payableAmount             BigDecimal
     */
    private void getPaymentInfo(ParkingOrderResultDto parkingOrder, ParkingOrderViewResultDto parkingOrderViewResultDto, BigDecimal payableAmount) {
        //如果未支付，则可以支付
        if (!PayStatusEnum.PAY_SUCCESS.getValue().equals(parkingOrder.getPayStatus())) {
            //金额大于0则可以支付
            if (payableAmount.compareTo(BigDecimal.ZERO) > 0) {
                parkingOrderViewResultDto.setPayable(Boolean.TRUE);
                parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
            } else {
                parkingOrderViewResultDto.setPayable(Boolean.FALSE);
                parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
            }
        } else {
            //已经支付，则无需支付
            parkingOrderViewResultDto.setPayable(Boolean.FALSE);
            //单位换算为元
            parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(NumberUtils.amountFen2Yuan(parkingOrder.getPayableAmount())));
            parkingOrderViewResultDto.setActualPayAmount(NumberUtils.formatAmountYuan(NumberUtils.amountFen2Yuan(parkingOrder.getActualPayAmount())));
        }
    }

    /**
     * @param requestDto
     * @return
     */
    private CalculateAmountResultDto calculateAmount(ParkingAmountCalculateRequestDto requestDto) {
        ObjectResultDto<CalculateAmountResultDto> parkingAmountCalculateResult = calculateIntegrationService.calculateAmount(requestDto);
        if (parkingAmountCalculateResult.isSuccess() && parkingAmountCalculateResult.getData() != null) {
            return parkingAmountCalculateResult.getData();
        }
        return null;
    }

    /**
     * 分页获取停车账单Jsapi
     *
     * @param requestDto ParkingOrderQueryByPlatePageRequestDto
     * @return ParkingOrderViewResultDto
     */
    @Override
    public PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageListJsapiApplication(ParkingOrderQueryJsapiPageRequestDto requestDto) {
        PagedResultDto<ParkingOrderViewResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            ParkingOrderQueryByPlatePageRequestDto parkingOrderQueryByPlatePageRequestDto = modelMapper.map(requestDto, ParkingOrderQueryByPlatePageRequestDto.class);
            PagedResultDto<ParkingOrderResultDto> parkingOrderPage = platformParkingOrderService.getParkingOrderPageList(parkingOrderQueryByPlatePageRequestDto);
            if (parkingOrderPage.isSuccess() && null != parkingOrderPage.getItems()) {
                List<ParkingOrderResultDto> records = parkingOrderPage.getItems();
                List<ParkingOrderViewResultDto> parkingResultDtoList = new ArrayList<>();
                for (ParkingOrderResultDto parkingOrder : records) {
                    ParkingOrderViewResultDto parkingOrderViewResultDto = buildParkingOrderView(parkingOrder);
                    parkingResultDtoList.add(parkingOrderViewResultDto);
                }
                pagedResultDto.setPageNo(parkingOrderPage.getPageNo());
                pagedResultDto.setPageSize(parkingOrderPage.getPageSize());
                pagedResultDto.setTotalCount(parkingOrderPage.getTotalCount());
                pagedResultDto.setItems(parkingResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取停车订单失败,异常信息{}", e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 分页获取停车账单Jsapi
     *
     * @param requestDto ParkingOrderQueryByPlatePageRequestDto
     * @return ParkingOrderViewResultDto
     */
    @Override
    public PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageListJsapi(ParkingOrderQueryJsapiPageRequestDto requestDto) {
        PagedResultDto<ParkingOrderViewResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            ParkingQrcodeGetRequestDto parkingQrcodeGetRequestDto = new ParkingQrcodeGetRequestDto();
            parkingQrcodeGetRequestDto.setNoncestr(requestDto.getNoncestr());
            ObjectResultDto<ParkingQrcodeResultDto> qrcodeResultDto = parkingQrcodeService.getParkingQrcode(parkingQrcodeGetRequestDto);
            ParkingOrderQueryByPlatePageRequestDto parkingOrderQueryByPlatePageRequestDto = modelMapper.map(requestDto, ParkingOrderQueryByPlatePageRequestDto.class);
            if (qrcodeResultDto.isSuccess() && null != qrcodeResultDto.getData()) {
                parkingOrderQueryByPlatePageRequestDto.setParkingId(qrcodeResultDto.getData().getParkingId());
            }
            PagedResultDto<ParkingOrderResultDto> parkingOrderPage = platformParkingOrderService.getParkingOrderPageList(parkingOrderQueryByPlatePageRequestDto);
            if (parkingOrderPage.isSuccess() && null != parkingOrderPage.getItems()) {
                List<ParkingOrderResultDto> records = parkingOrderPage.getItems();
                List<ParkingOrderViewResultDto> parkingResultDtoList = new ArrayList<>();
                for (ParkingOrderResultDto parkingOrder : records) {
                    ParkingOrderViewResultDto parkingOrderViewResultDto = buildParkingOrderView(parkingOrder);
                    parkingResultDtoList.add(parkingOrderViewResultDto);
                }
                pagedResultDto.setPageNo(parkingOrderPage.getPageNo());
                pagedResultDto.setPageSize(parkingOrderPage.getPageSize());
                pagedResultDto.setTotalCount(parkingOrderPage.getTotalCount());
                pagedResultDto.setItems(parkingResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取停车订单失败,异常信息{}", e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }
}
