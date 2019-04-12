package com.zoeeasy.cloud.integration.service.impl.order;


import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.*;
import com.zoeeasy.cloud.integration.order.ParkingOrderManagerIntegrationService;
import com.zoeeasy.cloud.integration.order.dto.request.BuildParkingOrderDetailWebViewRequestDto;
import com.zoeeasy.cloud.integration.order.dto.request.BuildParkingOrderSearchRequestDto;
import com.zoeeasy.cloud.order.appoint.AppointmentOrderManagerService;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderGetRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.parking.ParkingOrderManagerService;
import com.zoeeasy.cloud.order.parking.dto.request.*;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSearchDetailResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSearchResultDto;
import com.zoeeasy.cloud.pms.charge.ParkingChargeInfoService;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingChargeInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLocationGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingChargeInfoResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingLocationResultDto;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商户订单管理集成服务
 *
 * @author walkman
 */
@Service("parkingOrderManagerIntegrationService")
@Slf4j
public class ParkingOrderManagerIntegrationServiceImpl implements ParkingOrderManagerIntegrationService {

    @Autowired
    private IdService idService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingOrderManagerService parkingOrderManagerService;

    @Autowired
    private AppointmentOrderManagerService parkingAppointmentOrderService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private ParkingChargeInfoService parkingChargeInfoService;

    /**
     * 获取停车账单查询列表
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingOrderSearchResultDto> getParkingOrderPageList(ParkingOrderSearchRequestDto requestDto) {
        PagedResultDto<ParkingOrderSearchResultDto> pagedResultDto = new PagedResultDto<>();

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
            PagedResultDto<ParkingOrderResultDto> parkingOrderPagedResultDto = parkingOrderManagerService.getParkingOrderPageList(requestDto);

            if (parkingOrderPagedResultDto.isSuccess() && CollectionUtils.isNotEmpty(parkingOrderPagedResultDto.getItems())) {
                List<ParkingOrderSearchResultDto> list = new ArrayList<>();
                for (ParkingOrderResultDto parkingOrder : parkingOrderPagedResultDto.getItems()) {
                    BuildParkingOrderSearchRequestDto buildParkingOrderSearchRequestDto = modelMapper.map(parkingOrder, BuildParkingOrderSearchRequestDto.class);
                    ParkingOrderSearchResultDto parkingOrderSearchResultDto = buildParkingOrderSearch(buildParkingOrderSearchRequestDto);
                    if (parkingOrderSearchResultDto != null) {
                        list.add(parkingOrderSearchResultDto);
                    }
                }
                pagedResultDto.setPageNo(parkingOrderPagedResultDto.getPageNo());
                pagedResultDto.setPageSize(parkingOrderPagedResultDto.getPageSize());
                pagedResultDto.setTotalCount(parkingOrderPagedResultDto.getTotalCount());
                pagedResultDto.setItems(list);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取停车账单查询列表失败:{}", e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 创建账单查询信息视图
     *
     * @param parkingOrder
     * @return
     */
    private ParkingOrderSearchResultDto buildParkingOrderSearch(BuildParkingOrderSearchRequestDto parkingOrder) {

        ParkingOrderSearchResultDto parkingOrderSearchResultDto = new ParkingOrderSearchResultDto();
        //创建时间
        parkingOrderSearchResultDto.setCreateTime(parkingOrder.getCreationTime());
        //订单号
        parkingOrderSearchResultDto.setOrderNo(parkingOrder.getOrderNo());
        //停车场名称
        parkingOrderSearchResultDto.setParkingName(parkingOrder.getParkingName());
        //订单金额
        parkingOrderSearchResultDto.setPayableAmount(BigDecimal.valueOf(parkingOrder.getPayableAmount()).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP));
        //支付状态
        if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
            parkingOrderSearchResultDto.setPayStatus(PayStateEnum.PAYED.getValue());
        } else {
            parkingOrderSearchResultDto.setPayStatus(PayStateEnum.NOT_PAYED.getValue());
        }
        //车牌号
        parkingOrderSearchResultDto.setPlateNumber(parkingOrder.getPlateNumber());
        //是否出场
        parkingOrderSearchResultDto.setOuted(parkingOrder.getStatus());
        //是否手工单
        parkingOrderSearchResultDto.setArtificial(parkingOrder.getArtificial() ? 1 : 0);
        //订单状态
        if ((parkingOrder.getPayStatus().equals(PayStatusEnum.CREATED.getValue()) || parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_WAITING.getValue()))
                && parkingOrder.getStatus().equals(ParkingStatusEnum.PARKING.getValue())) {
            parkingOrderSearchResultDto.setStatus(ParkingOrderStatusEnum.PARKING.getValue());
        } else if ((parkingOrder.getPayStatus().equals(PayStatusEnum.CREATED.getValue()) || parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_WAITING.getValue()))
                && parkingOrder.getStatus().equals(ParkingStatusEnum.OUTING.getValue())) {
            parkingOrderSearchResultDto.setStatus(ParkingOrderStatusEnum.OUTING_CREATED.getValue());
        } else if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())
                && parkingOrder.getStatus().equals(ParkingStatusEnum.PARKING.getValue())) {
            parkingOrderSearchResultDto.setStatus(ParkingOrderStatusEnum.PAYSUCCESS_PARKING.getValue());
        } else if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())
                && parkingOrder.getStatus().equals(ParkingStatusEnum.OUTING.getValue())) {
            if (parkingOrder.getChargeMode().equals(ChargeModeEnum.AFTER.getValue())) {
                parkingOrderSearchResultDto.setStatus(ParkingOrderStatusEnum.OUTING_PAYSUCCESS.getValue());
            } else {
                parkingOrderSearchResultDto.setStatus(ParkingOrderStatusEnum.PAYSUCCESS_OUTING.getValue());
            }
        }
        if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
            //支付方式
            parkingOrderSearchResultDto.setPayWay(parkingOrder.getPayWay());
        }
        return parkingOrderSearchResultDto;

    }

    /**
     * 获取网页停车账单详情
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingOrderSearchDetailResultDto> getParkingOrderSearchDetailView(ParkingOrderDetailGetRequestDto requestDto) {
        ObjectResultDto<ParkingOrderSearchDetailResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
            parkingOrderGetByOrderNoRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = parkingOrderManagerService.findByOrderNo(parkingOrderGetByOrderNoRequestDto);
            if (parkingOrderResultDto.isFailed() || null == parkingOrderResultDto.getData()) {
                objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment()
                );
            } else {
                BuildParkingOrderDetailWebViewRequestDto buildParkingOrderDetailWebViewRequestDto = modelMapper.map(parkingOrderResultDto.getData(), BuildParkingOrderDetailWebViewRequestDto.class);
                ParkingOrderSearchDetailResultDto parkingOrderSearchDetailResultDto = buildParkingOrderDetailWebView(buildParkingOrderDetailWebViewRequestDto);
                if (parkingOrderSearchDetailResultDto != null) {
                    objectResultDto.setData(parkingOrderSearchDetailResultDto);
                }
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取停车订单失败,异常信息{}", e.getMessage());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 创建网页停车账单详情信息视图对象
     *
     * @param parkingOrder parkingOrder
     * @return ParkingOrderDetailViewResultDto
     */
    private ParkingOrderSearchDetailResultDto buildParkingOrderDetailWebView(BuildParkingOrderDetailWebViewRequestDto parkingOrder) {
        ParkingOrderSearchDetailResultDto parkingOrderSearchDetailResultDto = new ParkingOrderSearchDetailResultDto();

        parkingOrderSearchDetailResultDto.setOrderNo(parkingOrder.getOrderNo());
        parkingOrderSearchDetailResultDto.setParkingName(parkingOrder.getParkingName());
        //支付状态
        if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
            parkingOrderSearchDetailResultDto.setPayStatus(PayStateEnum.PAYED.getValue());
        } else {
            parkingOrderSearchDetailResultDto.setPayStatus(PayStateEnum.NOT_PAYED.getValue());
        }

        //停车时间
        if (parkingOrder.getStartTime() != null) {
            parkingOrderSearchDetailResultDto.setStartTime(DateUtils.formatDate(parkingOrder.getStartTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
        }
        //创建时间
        if (parkingOrder.getCreationTime() != null) {
            parkingOrderSearchDetailResultDto.setCreateTime(DateUtils.formatDate(parkingOrder.getCreationTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
        }
        Long timeMillis;
        if (parkingOrder.getArtificial()) {
            if (parkingOrder.getEndTime() == null || parkingOrder.getEndTime().compareTo(DateTimeUtils.getDateMax()) == 0) {

                parkingOrderSearchDetailResultDto.setOuted(ParkingStatusEnum.PARKING.getValue());
                //无出场时间
                parkingOrderSearchDetailResultDto.setEndTime(null);
                parkingOrderSearchDetailResultDto.setStatus(ParkingOrderStatusEnum.PARKING.getValue());
                parkingOrderSearchDetailResultDto.setPayTime(null);
                parkingOrderSearchDetailResultDto.setPayableAmount(null);
                parkingOrderSearchDetailResultDto.setParkingLength(null);

            } else {
                parkingOrderSearchDetailResultDto.setOuted(ParkingStatusEnum.OUTING.getValue());
                //出场时间
                parkingOrderSearchDetailResultDto.setEndTime(DateUtils.formatDate(parkingOrder.getEndTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
                parkingOrderSearchDetailResultDto.setPayableAmount(NumberUtils.amountFen2Yuan(parkingOrder.getPayableAmount()).toString());
                //已支付
                if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {

                    //支付时间
                    parkingOrderSearchDetailResultDto.setPayTime(DateUtils.formatDate(parkingOrder.getPayTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
                    parkingOrderSearchDetailResultDto.setStatus(ParkingOrderStatusEnum.OUTING_PAYSUCCESS.getValue());
                    timeMillis = parkingOrder.getEndTime().getTime() - parkingOrder.getStartTime().getTime();
                    parkingOrderSearchDetailResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
                } else {

                    parkingOrderSearchDetailResultDto.setStatus(ParkingOrderStatusEnum.OUTING_CREATED.getValue());
                    timeMillis = parkingOrder.getEndTime().getTime() - parkingOrder.getStartTime().getTime();
                    parkingOrderSearchDetailResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
                    parkingOrderSearchDetailResultDto.setPayTime(null);
                }

            }

        } else {
            //未出场
            if (parkingOrder.getEndTime() == null || parkingOrder.getEndTime().compareTo(DateTimeUtils.getDateMax()) == 0) {

                parkingOrderSearchDetailResultDto.setOuted(ParkingStatusEnum.PARKING.getValue());
                //无出场时间
                parkingOrderSearchDetailResultDto.setEndTime(null);

                //已支付
                if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                    parkingOrderSearchDetailResultDto.setStatus(ParkingOrderStatusEnum.PAYSUCCESS_PARKING.getValue());
                    parkingOrderSearchDetailResultDto.setPayTime(DateUtils.formatDate(parkingOrder.getPayTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
                    parkingOrderSearchDetailResultDto.setPayableAmount(NumberUtils.amountFen2Yuan(parkingOrder.getPayableAmount()).toString());
                    timeMillis = parkingOrder.getPayTime().getTime() - parkingOrder.getStartTime().getTime();
                    parkingOrderSearchDetailResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));

                } else {
                    parkingOrderSearchDetailResultDto.setStatus(ParkingOrderStatusEnum.PARKING.getValue());
                    parkingOrderSearchDetailResultDto.setPayTime(null);
                    parkingOrderSearchDetailResultDto.setPayableAmount(null);
                    parkingOrderSearchDetailResultDto.setParkingLength(null);
                }

            } else {
                parkingOrderSearchDetailResultDto.setOuted(ParkingStatusEnum.OUTING.getValue());
                //出场时间
                parkingOrderSearchDetailResultDto.setEndTime(DateUtils.formatDate(parkingOrder.getEndTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
                parkingOrderSearchDetailResultDto.setPayableAmount(NumberUtils.amountFen2Yuan(parkingOrder.getPayableAmount()).toString());
                //已支付
                if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {

                    //支付时间
                    parkingOrderSearchDetailResultDto.setPayTime(DateUtils.formatDate(parkingOrder.getPayTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
                    if (parkingOrder.getArtificial()) {
                        parkingOrderSearchDetailResultDto.setStatus(ParkingOrderStatusEnum.OUTING_PAYSUCCESS.getValue());
                        timeMillis = parkingOrder.getEndTime().getTime() - parkingOrder.getStartTime().getTime();
                        parkingOrderSearchDetailResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
                    } else {
                        //离场后缴费
                        if (parkingOrder.getChargeMode().equals(ChargeModeEnum.AFTER.getValue())) {
                            parkingOrderSearchDetailResultDto.setStatus(ParkingOrderStatusEnum.OUTING_PAYSUCCESS.getValue());
                            timeMillis = parkingOrder.getEndTime().getTime() - parkingOrder.getStartTime().getTime();
                            parkingOrderSearchDetailResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
                        } else {
                            parkingOrderSearchDetailResultDto.setStatus(ParkingOrderStatusEnum.PAYSUCCESS_OUTING.getValue());
                            timeMillis = parkingOrder.getPayTime().getTime() - parkingOrder.getStartTime().getTime();
                            parkingOrderSearchDetailResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
                        }
                    }

                } else {

                    parkingOrderSearchDetailResultDto.setStatus(ParkingOrderStatusEnum.OUTING_CREATED.getValue());
                    timeMillis = parkingOrder.getEndTime().getTime() - parkingOrder.getStartTime().getTime();
                    parkingOrderSearchDetailResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
                    parkingOrderSearchDetailResultDto.setPayTime(null);
                }
            }

            parkingOrderSearchDetailResultDto.setOrderType(ParkingOrderTypeEnum.NORMAL_PARKING.getValue());
            //是否预约停车
            if (parkingOrder.getAppointed()) {
                ParkingAppointOrderGetRequestDto parkingAppointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
                parkingAppointOrderGetRequestDto.setOrderNo(parkingOrder.getAppointOrderNo());
                ObjectResultDto<ParkingAppointmentOrderResultDto> parkingAppointOrderResult = parkingAppointmentOrderService.getAppointOrder(parkingAppointOrderGetRequestDto);
                if (parkingAppointOrderResult.isSuccess() && parkingAppointOrderResult.getData() != null) {
                    ParkingAppointmentOrderResultDto parkingAppointOrder = parkingAppointOrderResult.getData();
                    parkingOrderSearchDetailResultDto.setOrderType(ParkingOrderTypeEnum.APPOINT_PARKING.getValue());
                    parkingOrderSearchDetailResultDto.setAppointTime(DateUtils.formatDate(parkingAppointOrder.getScheduleTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
                    parkingOrderSearchDetailResultDto.setAppointFee(NumberUtils.amountFen2Yuan(parkingAppointOrder.getPayAmount()).toString());
                } else {
                    parkingOrderSearchDetailResultDto.setOrderType(ParkingOrderTypeEnum.APPOINT_PARKING.getValue());
                    parkingOrderSearchDetailResultDto.setAppointTime(null);
                    parkingOrderSearchDetailResultDto.setAppointFee(null);
                }
            } else {

                parkingOrderSearchDetailResultDto.setAppointTime(null);
                parkingOrderSearchDetailResultDto.setAppointFee(null);
            }
            //是否限免
            if (parkingOrder.getLimitFree()) {
                parkingOrderSearchDetailResultDto.setOrderType(ParkingOrderTypeEnum.LIMIT_PARKING.getValue());
                parkingOrderSearchDetailResultDto.setFreeLength(DateUtils.formatDateTimeChinese(parkingOrder.getFreeLength() * 1000L));
                parkingOrderSearchDetailResultDto.setChargeLength(DateUtils.formatDateTimeChinese(parkingOrder.getChargeLength() * 1000L));
            } else {
                parkingOrderSearchDetailResultDto.setFreeLength(null);
                parkingOrderSearchDetailResultDto.setChargeLength(null);
            }
        }
        //获取车牌号
        parkingOrderSearchDetailResultDto.setPlateNumber(parkingOrder.getPlateNumber());
        //获取车辆类型
        parkingOrderSearchDetailResultDto.setCarStyle(parkingOrder.getCarStyle());
        //获取停车场地址
        ParkingLocationGetRequestDto parkingLocationGetRequestDto = new ParkingLocationGetRequestDto();
        parkingLocationGetRequestDto.setParkingId(parkingOrder.getParkingId());
        ObjectResultDto<ParkingLocationResultDto> parkingAddress = platformParkingInfoService.getParkingAddressForCloud(parkingLocationGetRequestDto);
        if (parkingAddress.isSuccess() && null != parkingAddress.getData()) {
            String address = parkingAddress.getData().getAddress();
            parkingOrderSearchDetailResultDto.setParkingAddress(address);
        }
        //备注
        parkingOrderSearchDetailResultDto.setRemark(parkingOrder.getRemark());
        //获取停车场名称
        parkingOrderSearchDetailResultDto.setParkingName(parkingOrder.getParkingName());
        parkingOrderSearchDetailResultDto.setEditor(parkingOrder.getEditor());
        if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
            //支付方式
            parkingOrderSearchDetailResultDto.setPayWay(parkingOrder.getPayWay());
        }

        return parkingOrderSearchDetailResultDto;
    }

    /**
     * 人工添加停车账单
     *
     * @param requestDto ParkingOrderAddRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto addParkingOrder(ParkingOrderAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingOrderInsertRequestDto parkingOrder = new ParkingOrderInsertRequestDto();
            String parkingOrderNo = String.valueOf(this.idService.genId());
            parkingOrder.setPlateNumber(requestDto.getPlateNumber());
            parkingOrder.setOrderNo(parkingOrderNo);
            parkingOrder.setRecordNo(String.valueOf(this.idService.genId()));
            parkingOrder.setParkingId(requestDto.getParkingId());
            parkingOrder.setEditor(requestDto.getEditor());
            parkingOrder.setArtificial(true);
            //TODO 20181202 方法重写
            ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
            parkingGetRequestDto.setId(requestDto.getParkingId());
            ObjectResultDto<ParkingResultDto> parkingInfoObject = parkingInfoService.getParkingInfo(parkingGetRequestDto);
            if (parkingInfoObject.isSuccess() && null != parkingInfoObject.getData()) {
                ParkingResultDto parkingInfo = parkingInfoObject.getData();
                parkingOrder.setParkingName(parkingInfo.getFullName());
                //这里之前设置的实际是停车场基本信息ID，是否有误？现修改为了再次查询获取chargeInfo的id
//                parkingOrder.setChargeInfoId(parkingInfoObject.getData().getId());

                //收费信息
                ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
                parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());

                ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = parkingChargeInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
                if (parkChargeInfoByParkingId.isFailed() || null == parkChargeInfoByParkingId.getData()) {
                    return resultDto.makeResult(PmsResultEnum.GET_PARKING_CHARGE_INFO_ERR.getValue(), PmsResultEnum.GET_PARKING_CHARGE_INFO_ERR.getComment());
                }
                ParkingChargeInfoResultDto chargeInfo = parkChargeInfoByParkingId.getData();
                parkingOrder.setChargeInfoId(chargeInfo.getId());
            }
            parkingOrder.setStartTime(requestDto.getStartTime());

            if (ParkingStatusEnum.OUTING.getValue().equals(requestDto.getOuted()) && null != requestDto.getEndTime()) {
                parkingOrder.setEndTime(requestDto.getEndTime());
                Long timeMillis = parkingOrder.getEndTime().getTime() - parkingOrder.getStartTime().getTime();
                parkingOrder.setParkingLength(timeMillis);
            } else {
                parkingOrder.setEndTime(DateTimeUtils.getDateMax());
            }
            parkingOrder.setStatus(requestDto.getOuted());
            if (requestDto.getPayStatus().equals(PayStateEnum.PAYED.getValue())) {
                parkingOrder.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                parkingOrder.setPayTime(parkingOrder.getEndTime());
                parkingOrder.setNeedPay(Boolean.FALSE);
                parkingOrder.setPayable(Boolean.FALSE);
                //支付记录
            } else {
                parkingOrder.setPayStatus(PayStatusEnum.CREATED.getValue());
            }
            parkingOrder.setPayableAmount(NumberUtils.amountYuan2FenInt(requestDto.getPayableAmount()));

            parkingOrderManagerService.createParkingOrder(parkingOrder);
        } catch (Exception e) {
            log.error("人工添加停车账单失败" + e.getMessage());
            return resultDto.failed();
        }
        return resultDto.success();

    }
}

