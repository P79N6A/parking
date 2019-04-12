package com.zhuyitech.parking.integration.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.common.enums.PlateColorEnum;
import com.zhuyitech.parking.integration.order.UserParkingOrderIntegrationService;
import com.zhuyitech.parking.integration.order.dto.request.UserParkingOrderGetRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.UserParkingOrderListGetRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.UserParkingOrderPagedResultRequestDto;
import com.zhuyitech.parking.ucc.car.UserCarInfoService;
import com.zhuyitech.parking.ucc.car.request.UserCarInfoGetByPlateNumberRequestDto;
import com.zhuyitech.parking.ucc.car.result.UserCarViewResultDto;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zoeeasy.cloud.integration.order.CustomerParkingOrderIntegrationService;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderDetailViewResultDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderDetailGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderListGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderQueryByPlatePageRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderViewResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户停车订单服务
 *
 * @author walkman
 */
@Service("userParkingOrderIntegrationService")
@Slf4j
public class UserParkingOrderServiceImpl implements UserParkingOrderIntegrationService {

    @Autowired
    private CustomerParkingOrderIntegrationService customerParkOrderIntegrationService;

    @Autowired
    private UserCarInfoService userCarInfoService;

    /**
     * 获取停车账单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingOrderViewResultDto> getUserParkingOrderList(UserParkingOrderListGetRequestDto requestDto) {
        ListResultDto<ParkingOrderViewResultDto> pagedResultDto = new ListResultDto<>();
        try {

            //获取用户ID
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserCarInfoGetByPlateNumberRequestDto userCarInfoGetByPlateNumberRequestDto = new UserCarInfoGetByPlateNumberRequestDto();
            userCarInfoGetByPlateNumberRequestDto.setUserId(userId);
            userCarInfoGetByPlateNumberRequestDto.setPlateNumber(requestDto.getPlateNumber());
            ObjectResultDto<UserCarViewResultDto> userCarViewResultDto = userCarInfoService.getUserCar(userCarInfoGetByPlateNumberRequestDto);
            if (userCarViewResultDto.isFailed() || userCarViewResultDto.getData() == null) {
                return pagedResultDto.makeResult(UCenterResultEnum.CAR_NOT_FOUND.getValue(),
                        UCenterResultEnum.CAR_NOT_FOUND.getComment()
                );
            } else {
                UserCarViewResultDto userCar = userCarViewResultDto.getData();
                ParkingOrderListGetRequestDto parkingOrderListGetRequestDto = new ParkingOrderListGetRequestDto();
                parkingOrderListGetRequestDto.setPlateNumber(requestDto.getPlateNumber());
//                parkingOrderListGetRequestDto.setPlateColor(userCar.getPlateColor());
                parkingOrderListGetRequestDto.setPayStatus(requestDto.getPayStatus());
                return customerParkOrderIntegrationService.getParkingOrderList(parkingOrderListGetRequestDto);
            }
        } catch (Exception e) {
            log.error("获取用户停车订单失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 分页获取用户停车订单
     */
    @Override
    public PagedResultDto<ParkingOrderViewResultDto> getUserParkingOrderPageList(UserParkingOrderPagedResultRequestDto requestDto) {

        PagedResultDto<ParkingOrderViewResultDto> pagedResultDto = new PagedResultDto<>();
        if (StringUtils.isEmpty(requestDto.getPlateNumber())) {
            return pagedResultDto.makeResult(UCenterResultEnum.CAR_PLATE_NUMBER_EMPTY.getValue(),
                    UCenterResultEnum.CAR_PLATE_NUMBER_EMPTY.getComment()
            );
        }
        if (null != requestDto.getPlateColor() && null == PlateColorEnum.parse(requestDto.getPlateColor())) {
            return pagedResultDto.makeResult(UCenterResultEnum.CAR_PLATE_COLOR_INVALID.getValue(),
                    UCenterResultEnum.CAR_PLATE_COLOR_INVALID.getComment()
            );
        }
        try {

            //获取用户车辆信息
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserCarInfoGetByPlateNumberRequestDto userCarInfoGetByPlateNumberRequestDto = new UserCarInfoGetByPlateNumberRequestDto();
            userCarInfoGetByPlateNumberRequestDto.setUserId(userId);
            userCarInfoGetByPlateNumberRequestDto.setPlateNumber(requestDto.getPlateNumber());
            if (null != requestDto.getPlateColor()) {
                userCarInfoGetByPlateNumberRequestDto.setPlateColor(requestDto.getPlateColor());
            }
            ObjectResultDto<UserCarViewResultDto> userCarViewResultDto = userCarInfoService.getUserCar(userCarInfoGetByPlateNumberRequestDto);
            if (userCarViewResultDto.isFailed() || userCarViewResultDto.getData() == null) {
                return pagedResultDto.makeResult(UCenterResultEnum.CAR_NOT_FOUND.getValue(),
                        UCenterResultEnum.CAR_NOT_FOUND.getComment()
                );
            } else {
                UserCarViewResultDto userCar = userCarViewResultDto.getData();
                ParkingOrderQueryByPlatePageRequestDto parkingOrderListGetRequestDto = new ParkingOrderQueryByPlatePageRequestDto();
                parkingOrderListGetRequestDto.setPlateNumber(requestDto.getPlateNumber());
                parkingOrderListGetRequestDto.setPlateColor(requestDto.getPlateColor());
//                parkingOrderListGetRequestDto.setPlateColor(userCar.getPlateColor());
                parkingOrderListGetRequestDto.setPayStatus(requestDto.getPayStatus());
                parkingOrderListGetRequestDto.setPageNo(requestDto.getPageNo());
                parkingOrderListGetRequestDto.setPageSize(requestDto.getPageSize());
                return customerParkOrderIntegrationService.getParkingOrderPageList(parkingOrderListGetRequestDto);
            }
        } catch (Exception e) {
            log.error("获取用户停车订单失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 根据停车订单ID获取停车订单
     */
    @Override
    public ObjectResultDto<ParkingOrderDetailViewResultDto> getUserParkingOrderView(UserParkingOrderGetRequestDto requestDto) {
        ObjectResultDto<ParkingOrderDetailViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            ParkingOrderDetailGetRequestDto parkingOrderDetailGetRequestDto = new ParkingOrderDetailGetRequestDto();
            parkingOrderDetailGetRequestDto.setOrderNo(requestDto.getOrderNo());
            parkingOrderDetailGetRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            return customerParkOrderIntegrationService.getParkingOrderDetailView(parkingOrderDetailGetRequestDto);

        } catch (Exception e) {
            log.error("获取用户停车订单失败" + e.getMessage());
            return objectResultDto.failed();
        }
    }
}
