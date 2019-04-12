package com.zhuyitech.parking.integration.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zhuyitech.parking.integration.appointment.AppointmentIntegrationService;
import com.zhuyitech.parking.integration.appointment.dto.request.*;
import com.zhuyitech.parking.pms.enums.PmsResultEnum;
import com.zhuyitech.parking.ucc.car.UserCarInfoService;
import com.zhuyitech.parking.ucc.car.request.UserCarInfoGetByPlateNumberRequestDto;
import com.zhuyitech.parking.ucc.car.result.UserCarViewResultDto;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zoeeasy.cloud.integration.appoint.AppointOrderPlatformIntegrationService;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderListGetRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPagedMonthRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPagedResultRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPlaceRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.*;
import com.zoeeasy.cloud.order.appoint.dto.result.*;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.result.AppointOrderDetailViewResultDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 交易集成服务
 *
 * @author walkman
 */
@Service("appointmentIntegrationService")
@Slf4j
public class AppointmentIntegrationServiceImpl implements AppointmentIntegrationService {

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private AppointOrderPlatformIntegrationService appointIntegrationService;

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private UserCarInfoService userCarInfoService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 预约订单下单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<AppointOrderPlaceResultDto> placeOrder(UserAppointOrderPlaceRequestDto requestDto) {
        ObjectResultDto<AppointOrderPlaceResultDto> objectResultDto = new ObjectResultDto<>();
        if (requestDto.getScheduleTime().compareTo(new Date()) < 0) {
            return objectResultDto.makeResult(PmsResultEnum.SCHEDULE_TIME_ERROR.getValue(), PmsResultEnum.SCHEDULE_TIME_ERROR.getComment());
        }
        CustomerAppointOrderPlaceRequestDto customerAppointOrderPlaceRequestDto = modelMapper.map(requestDto, CustomerAppointOrderPlaceRequestDto.class);
        Long userId = requestDto.getSessionIdentity().getUserId();
        customerAppointOrderPlaceRequestDto.setCustomerUserId(userId);

        try {
            //判断用户车辆是否存在
            UserCarInfoGetByPlateNumberRequestDto numberRequestDto = new UserCarInfoGetByPlateNumberRequestDto();
            numberRequestDto.setUserId(userId);
            numberRequestDto.setPlateNumber(requestDto.getPlateNumber());
            ObjectResultDto<UserCarViewResultDto> userCar = userCarInfoService.getUserCar(numberRequestDto);
            if (userCar.isFailed() || null == userCar.getData()) {
                return objectResultDto.makeResult(UCenterResultEnum.CAR_UNBIND.getValue(), UCenterResultEnum.CAR_UNBIND.getComment());
            }

            ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
            parkingGetRequestDto.setId(requestDto.getParkingId());
            ObjectResultDto<ParkingResultDto> parkingObjectResultDto = platformParkingInfoService.getParkingApp(parkingGetRequestDto);
            if (parkingObjectResultDto.isFailed() || parkingObjectResultDto.getData() == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(), PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
            ParkingResultDto parkingResultDto = parkingObjectResultDto.getData();
            customerAppointOrderPlaceRequestDto.setParkingResultDto(parkingResultDto);
            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getPlaceAppointOrderLockKey(parkingResultDto.getCode(), requestDto.getPlateNumber()));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {

                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    objectResultDto = appointIntegrationService.placeOrder(customerAppointOrderPlaceRequestDto);
                }
            } catch (Exception e) {
                log.error("获取分布式锁时抛错：", e);
                objectResultDto.failed();
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
        } catch (Exception e) {
            log.error("预约下单失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.APPOINT_PLACE_ORDER_ERR.getValue(), PmsResultEnum.APPOINT_PLACE_ORDER_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 获取预约下单分布式锁键
     *
     * @param parkingCode parkingCode
     * @param plateNumber plateNumber
     */
    private String getPlaceAppointOrderLockKey(String parkingCode, String plateNumber) {
        return "lock:parking.pms.appoint.order_" + parkingCode + plateNumber;
    }

    /**
     * 获取预约订单取消分布式锁键
     *
     * @param orderNo orderNo
     */
    private String getCancelAppointOrderLockKey(String orderNo) {
        return "lock:parking.pay.appoint.order_" + orderNo;
    }

    /**
     * 计算预约取消退款金额
     */
    @Override
    public ObjectResultDto<AppointRefundAmountResultDto> calculateRefundAmount(AppointRefundAmountRequestDto requestDto) {

        ObjectResultDto<AppointRefundAmountResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            objectResultDto = appointIntegrationService.calculateRefundAmount(requestDto);
        } catch (Exception e) {
            log.error("计算预约取消退款金额失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.APPOINTRULE_GET_ERR.getValue(), PmsResultEnum.APPOINTRULE_GET_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 预约订单确认
     */
    @Override
    public ObjectResultDto<AppointOrderConfirmResultDto> confirmOrder(AppointOrderConfirmParkingRequestDto requestDto) {
        ObjectResultDto<AppointOrderConfirmResultDto> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getOrderNo())) {
            objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NO_EMPTY_ERROR.getValue(), PmsResultEnum.APPOINT_ORDER_NO_EMPTY_ERROR.getComment());
        }
        try {
            AppointOrderConfirmRequestDto appointOrderConfirmRequestDto = modelMapper.map(requestDto, AppointOrderConfirmRequestDto.class);
            appointOrderConfirmRequestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
            objectResultDto = appointIntegrationService.confirmOrder(appointOrderConfirmRequestDto);
        } catch (Exception e) {
            log.error("预约订单确认失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.APPOINT_CHECK_ORDER_ERR.getValue(), PmsResultEnum.APPOINT_CHECK_ORDER_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 预约订单取消
     */
    @Override
    public ObjectResultDto<AppointOrderCancelResultDto> cancelOrder(AppointOrderCancelParkingRequestDto requestDto) {
        ObjectResultDto<AppointOrderCancelResultDto> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getOrderNo())) {
            objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NO_EMPTY_ERROR.getValue(), PmsResultEnum.APPOINT_ORDER_NO_EMPTY_ERROR.getComment());
        }
        try {

            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getCancelAppointOrderLockKey(requestDto.getOrderNo()));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {

                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    AppointOrderCancelRequestDto appointOrderCancelRequestDto = new AppointOrderCancelRequestDto();
                    appointOrderCancelRequestDto.setOrderNo(requestDto.getOrderNo());
                    appointOrderCancelRequestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
                    objectResultDto = appointIntegrationService.cancelOrder(appointOrderCancelRequestDto);
                }
            } catch (Exception e) {
                log.error("获取分布式锁时抛错：", e);
                objectResultDto.failed();
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
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
    public PagedResultDto<AppointOrderViewMonthResultDto> getOrderPagedListByMonth(UserAppointOrderPagedMonthRequestDto requestDto) {
        PagedResultDto<AppointOrderViewMonthResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            CustomerAppointOrderPagedMonthRequestDto customerAppointOrderPagedMonthRequestDto = modelMapper.map(requestDto, CustomerAppointOrderPagedMonthRequestDto.class);
            customerAppointOrderPagedMonthRequestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
            pagedResultDto = appointIntegrationService.getOrderPagedListByMonth(customerAppointOrderPagedMonthRequestDto);
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
        if (StringUtils.isEmpty(requestDto.getOrderNo())) {
            objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NO_EMPTY_ERROR.getValue(), PmsResultEnum.APPOINT_ORDER_NO_EMPTY_ERROR.getComment());
        }
        try {
            requestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
            objectResultDto = appointIntegrationService.getOrder(requestDto);
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
    public ObjectResultDto<AppointOrderDetailViewResultDto> getOrderDetail(AppointOrderGetParkingRequestDto requestDto) {
        ObjectResultDto<AppointOrderDetailViewResultDto> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getOrderNo())) {
            objectResultDto.makeResult(PmsResultEnum.APPOINT_ORDER_NO_EMPTY_ERROR.getValue(), PmsResultEnum.APPOINT_ORDER_NO_EMPTY_ERROR.getComment());
        }
        try {
            AppointOrderGetRequestDto appointOrderGetRequestDto = modelMapper.map(requestDto, AppointOrderGetRequestDto.class);
            appointOrderGetRequestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
            objectResultDto = appointIntegrationService.getOrderDetail(appointOrderGetRequestDto);
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
    public ListResultDto<AppointOrderViewResultDto> getOrderList(UserAppointOrderListGetRequestDto requestDto) {
        ListResultDto<AppointOrderViewResultDto> listResultDto = new ListResultDto<>();
        try {
            CustomerAppointOrderListGetRequestDto customerAppointOrderListGetRequestDto = new CustomerAppointOrderListGetRequestDto();
            customerAppointOrderListGetRequestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
            listResultDto = appointIntegrationService.getOrderList(customerAppointOrderListGetRequestDto);
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
    public PagedResultDto<AppointOrderViewResultDto> getOrderPagedList(UserAppointOrderPagedResultRequestDto
                                                                               requestDto) {
        PagedResultDto<AppointOrderViewResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            CustomerAppointOrderPagedResultRequestDto customerAppointOrderPagedResultRequestDto = new CustomerAppointOrderPagedResultRequestDto();
            customerAppointOrderPagedResultRequestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
            pagedResultDto = appointIntegrationService.getOrderPagedList(customerAppointOrderPagedResultRequestDto);
        } catch (Exception e) {
            log.error("预约订单列表获取失败" + e.getMessage());
            pagedResultDto.makeResult(PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getValue(), PmsResultEnum.GET_USER_APPOINT_LIST_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 预约账单支付判断
     *
     * @param requestDto 请求参数
     */
    @Override
    public ObjectResultDto<AppointOrderCheckResultDto> payCheck(AppointOrderPayCheckParkingRequestDto requestDto) {
        ObjectResultDto<AppointOrderCheckResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAppointOrderPayCheckRequestDto parkingAppointOrderPayCheckRequestDto = modelMapper.map(requestDto, ParkingAppointOrderPayCheckRequestDto.class);
            parkingAppointOrderPayCheckRequestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
            objectResultDto = appointIntegrationService.payCheck(parkingAppointOrderPayCheckRequestDto);
        } catch (Exception e) {
            log.error("预约订单支付判断失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 预约账单取消判断
     *
     * @param requestDto requestDto
     */
    @Override
    public ObjectResultDto<AppointOrderCancelCheckResultDto> cancelCheck(AppointOrderCancelCheckParkingRequestDto requestDto) {
        ObjectResultDto<AppointOrderCancelCheckResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            AppointOrderCancelCheckRequestDto appointOrderCancelCheckRequestDto = modelMapper.map(requestDto, AppointOrderCancelCheckRequestDto.class);
            appointOrderCancelCheckRequestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
            objectResultDto = appointIntegrationService.cancelCheck(appointOrderCancelCheckRequestDto);
        } catch (Exception e) {
            log.error("预约订单取消判断失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
