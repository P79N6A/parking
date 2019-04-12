package com.zoeeasy.cloud.integration.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.appointrule.ParkingAppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleGetByTimeRequestDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleDetailViewResultDto;
import com.zoeeasy.cloud.core.enums.AppointStatusEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.integration.appoint.AppointOrderManagerIntegrationService;
import com.zoeeasy.cloud.order.appoint.AppointmentOrderManagerService;
import com.zoeeasy.cloud.order.appoint.PlatformAppointOrderService;
import com.zoeeasy.cloud.order.appoint.dto.request.*;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderCancelResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderGetDetailResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderPageResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLocationGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingLocationResultDto;
import com.zoeeasy.cloud.ucc.user.dto.request.UserGetRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 预约订单管理集成服务
 *
 * @author walkman
 */
@Service("appointOrderManagerIntegrationService")
@Slf4j
public class AppointOrderManagerIntegrationServiceImpl implements AppointOrderManagerIntegrationService {

    @Autowired
    private AppointmentOrderManagerService appointmentOrderManagerService;

    @Autowired
    private ParkingAppointRuleService parkingAppointRuleService;

    @Autowired
    private PlatformAppointOrderService platformAppointOrderService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    /**
     * 分页获取预约订单列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<AppointOrderPageResultDto> getOrderPagedList(AppointOrderPagedRequestDto requestDto) {
        PagedResultDto<AppointOrderPageResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            pagedResultDto = appointmentOrderManagerService.getOrderPagedListAdmin(requestDto);
            if (pagedResultDto.isFailed() || pagedResultDto.getItems().isEmpty()) {
                return pagedResultDto;
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("预约订单列表获取失败" + e.getMessage());
            pagedResultDto.makeResult(PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getValue(), PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 预约订单详情
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AppointOrderGetDetailResultDto> getOrderDetail(AppointOrderGetDetailRequestDto requestDto) {
        ObjectResultDto<AppointOrderGetDetailResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAppointOrderGetByIdRequestDto parkingAppointOrderGetByIdRequestDto = new ParkingAppointOrderGetByIdRequestDto();
            parkingAppointOrderGetByIdRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<ParkingAppointmentOrderResultDto> orderResultDtoObjectResultDto = appointmentOrderManagerService.getOrderByOrderNo(parkingAppointOrderGetByIdRequestDto);
            if (orderResultDtoObjectResultDto.isFailed() || null == orderResultDtoObjectResultDto.getData()) {
                return objectResultDto.makeResult(PmsResultEnum.GET_USER_APPOINT_ERR.getValue(), PmsResultEnum.GET_USER_APPOINT_ERR.getComment());
            }
            AppointOrderGetDetailResultDto appointOrderGetDetailResultDto = modelMapper.map(orderResultDtoObjectResultDto.getData(), AppointOrderGetDetailResultDto.class);
            //获取APP端用户信息
            UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
            userGetRequestDto.setId(appointOrderGetDetailResultDto.getUserId());
//            ObjectResultDto<UserListResultDto> userObjectResultDto = userService.getUser(userGetRequestDto);
//            if (userObjectResultDto.isSuccess() && null != userObjectResultDto.getData()) {
//                appointOrderGetDetailResultDto.setPhoneNumber(userObjectResultDto.getData().getPhoneNumber());
//                appointOrderGetDetailResultDto.setNickname(userObjectResultDto.getData().getNickname());
//            }
            //TODO 20181030
            //获取停车场地址
            ParkingLocationGetRequestDto parkingLocationGetRequestDto = new ParkingLocationGetRequestDto();
            parkingLocationGetRequestDto.setParkingId(appointOrderGetDetailResultDto.getParkingId());
            ObjectResultDto<ParkingLocationResultDto> parkingAddress = platformParkingInfoService.getParkingAddressForCloud(parkingLocationGetRequestDto);
            if (parkingAddress.isSuccess() && null != parkingAddress.getData()) {
                String address = parkingAddress.getData().getAddress();
                appointOrderGetDetailResultDto.setParkingAddress(address);
            }
            objectResultDto.setData(appointOrderGetDetailResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取预约订单失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.GET_USER_APPOINT_ERR.getValue(), PmsResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 取消预约订单
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<AppointOrderCancelResultDto> cancelAppointOrder(AppointOrderCancelRequestDto requestDto) {
        ObjectResultDto<AppointOrderCancelResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            ParkingAppointOrderGetRequestDto parkingAppointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
            parkingAppointOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            parkingAppointOrderGetRequestDto.setCustomerUserId(requestDto.getCustomerUserId());
            ObjectResultDto<ParkingAppointmentOrderResultDto> orderResultDtoObjectResultDto = platformAppointOrderService.getAppointOrderApp(parkingAppointOrderGetRequestDto);

            if (orderResultDtoObjectResultDto.isSuccess() && null != orderResultDtoObjectResultDto.getData()) {
                AppointOrderUpdateByOrderNoRequestDto appointOrderUpdateByOrderNoRequestDto = modelMapper.map(orderResultDtoObjectResultDto.getData(), AppointOrderUpdateByOrderNoRequestDto.class);
                Date now = new Date();
                //如果订单已经支付成功
                if (appointOrderUpdateByOrderNoRequestDto.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                    Date appointTime = appointOrderUpdateByOrderNoRequestDto.getScheduleTime();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(appointTime);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String appointDate = sdf.format(appointTime);
                    Date scheduleDate = sdf.parse(appointDate);
                    //计算退款金额
                    ObjectResultDto<ParkingAppointRuleDetailViewResultDto> objectResultDto1 = getAppointRule(appointOrderUpdateByOrderNoRequestDto.getParkingId(), scheduleDate);
                    if (objectResultDto1.isFailed() || objectResultDto1.getData() == null) {
                        return objectResultDto.makeResult(OrderResultEnum.APPOINT_RULE_NOT_FOUND.getValue(),
                                OrderResultEnum.APPOINT_RULE_NOT_FOUND.getComment());
                    }
                    ParkingAppointRuleDetailViewResultDto resultDto = objectResultDto1.getData();
                    if (null == resultDto.getCountOvertimeCancelPrice()) {
                        resultDto.setCountOvertimeCancelPrice(0);
                    }
                    appointOrderUpdateByOrderNoRequestDto.setRefundAmount(BigDecimal.valueOf(0));
                   /* if (resultDto.getCountOvertimeCancelPrice().compareTo(appointOrderUpdateByOrderNoRequestDto.getActualPayAmount()) > 0) {
                        appointOrderUpdateByOrderNoRequestDto.setRefundAmount(BigDecimal.valueOf(0));
                    } else {
                        //parkingAppointmentOrder.setRefundAmount(parkingAppointmentOrder.getActualPayAmount().subtract(resultDto.getCountOvertimeCancelPrice()));
                        appointOrderUpdateByOrderNoRequestDto.setRefundAmount(BigDecimal.valueOf(0));
                    }*/
                    appointOrderUpdateByOrderNoRequestDto.setAppointStatus(AppointStatusEnum.CANCELED.getValue());
                    appointOrderUpdateByOrderNoRequestDto.setCancelTime(now);
                    calendar.add(Calendar.MINUTE, resultDto.getOverTimeLimit());
                    if (now.compareTo(calendar.getTime()) >= 0) {
                        appointOrderUpdateByOrderNoRequestDto.setCancelReason("用户超时未进场");
                    } else {
                        appointOrderUpdateByOrderNoRequestDto.setCancelReason("用户手动取消");
                    }
                } else if (appointOrderUpdateByOrderNoRequestDto.getPayStatus().equals(PayStatusEnum.CREATED.getValue())
                        || appointOrderUpdateByOrderNoRequestDto.getPayStatus().equals(PayStatusEnum.PAY_WAITING.getValue())) {

                    //订单未支付
                    appointOrderUpdateByOrderNoRequestDto.setAppointStatus(AppointStatusEnum.CANCELED.getValue());
                    appointOrderUpdateByOrderNoRequestDto.setCancelTime(now);

                    if (now.compareTo(appointOrderUpdateByOrderNoRequestDto.getPayLimitTime()) >= 0) {
                        appointOrderUpdateByOrderNoRequestDto.setCancelReason("用户超时未支付");
                        appointOrderUpdateByOrderNoRequestDto.setPayStatus(PayStatusEnum.PAY_TIMEOUT.getValue());
                    } else {
                        appointOrderUpdateByOrderNoRequestDto.setCancelReason("用户手动取消");
                        appointOrderUpdateByOrderNoRequestDto.setPayStatus(PayStatusEnum.PAY_CANCELED.getValue());
                    }
                } else {
                    return objectResultDto.makeResult(OrderResultEnum.APPOINT_ORDER_PAYSTATUS_ERR.getValue(),
                            OrderResultEnum.APPOINT_ORDER_PAYSTATUS_ERR.getComment());
                }
                ResultDto resultDto = platformAppointOrderService.updateAppointOrderByOrderNo(appointOrderUpdateByOrderNoRequestDto);
                if (resultDto.isFailed()) {
                    return objectResultDto.makeResult(OrderResultEnum.UPDATE_APPOINTORDER_ERR.getValue(),
                            OrderResultEnum.UPDATE_APPOINTORDER_ERR.getComment());
                }

                AppointOrderCancelResultDto appointOrderCancelResultDto = new AppointOrderCancelResultDto();
                appointOrderCancelResultDto.setOrderNo(appointOrderUpdateByOrderNoRequestDto.getOrderNo());
                objectResultDto.setData(appointOrderCancelResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取预约订单失败" + e.getMessage());
            return objectResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return objectResultDto;
    }

    private ObjectResultDto<ParkingAppointRuleDetailViewResultDto> getAppointRule(Long parkingId, Date date) {
        ParkingAppointRuleGetByTimeRequestDto requestDto = new ParkingAppointRuleGetByTimeRequestDto();
        requestDto.setParkingId(parkingId);
        requestDto.setAppointTime(date);
        return parkingAppointRuleService.getAppointRuleTotalLByAppointTime(requestDto);
    }

}
