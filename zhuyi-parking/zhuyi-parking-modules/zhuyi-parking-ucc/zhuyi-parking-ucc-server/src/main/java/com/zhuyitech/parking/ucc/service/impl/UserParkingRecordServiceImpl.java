package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.ucc.car.UserCarInfoService;
import com.zhuyitech.parking.ucc.car.request.UserCarInfoGetByPlateNumberRequestDto;
import com.zhuyitech.parking.ucc.car.result.UserCarViewResultDto;
import com.zhuyitech.parking.ucc.dto.request.record.UserParkingRecordGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.record.UserParkingRecordListGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.record.UserParkingRecordPagedResultRequestDto;
import com.zhuyitech.parking.ucc.dto.result.record.UserParkingRecordDetailResultDto;
import com.zhuyitech.parking.ucc.dto.result.record.UserParkingRecordResultDto;
import com.zhuyitech.parking.ucc.dto.result.record.UserParkingRecordViewResultDto;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.service.api.UserParkingRecordService;
import com.zoeeasy.cloud.integration.order.CustomerParkingOrderIntegrationService;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderListGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderQueryByPlatePageRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderViewResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 用户停车记录服务实现
 * @Date: 2018/1/18 0018
 * @author: AkeemSuper
 */
@Service("userParkingRecordService")
@Slf4j
public class UserParkingRecordServiceImpl implements UserParkingRecordService {

    @Autowired
    private UserCarInfoService userCarInfoService;

    @Autowired
    private CustomerParkingOrderIntegrationService customerParkOrderIntegrationService;

    @Override
    public ListResultDto<UserParkingRecordViewResultDto> getUserParkingRecordList(UserParkingRecordListGetRequestDto requestDto) {
        ListResultDto<UserParkingRecordViewResultDto> listResultDto = new ListResultDto<>();

        if (StringUtils.isEmpty(requestDto.getPlateNumber())) {
            return listResultDto.makeResult(UCenterResultEnum.CAR_PLATE_NUMBER_EMPTY.getValue(),
                    UCenterResultEnum.CAR_PLATE_NUMBER_EMPTY.getComment()
            );
        }

        try {

            //获取用户ID
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserCarInfoGetByPlateNumberRequestDto userCarInfoGetByPlateNumberRequestDto = new UserCarInfoGetByPlateNumberRequestDto();
            userCarInfoGetByPlateNumberRequestDto.setUserId(userId);
            userCarInfoGetByPlateNumberRequestDto.setPlateNumber(requestDto.getPlateNumber());
            ObjectResultDto<UserCarViewResultDto> userCarViewResultDto = userCarInfoService.getUserCar(userCarInfoGetByPlateNumberRequestDto);
            if (userCarViewResultDto.isFailed() || userCarViewResultDto.getData() == null) {
                return listResultDto.makeResult(UCenterResultEnum.CAR_NOT_FOUND.getValue(),
                        UCenterResultEnum.CAR_NOT_FOUND.getComment()
                );
            } else {

                UserCarViewResultDto userCar = userCarViewResultDto.getData();
                ParkingOrderListGetRequestDto parkingOrderListGetRequestDto = new ParkingOrderListGetRequestDto();
                parkingOrderListGetRequestDto.setPlateNumber(requestDto.getPlateNumber());
                ListResultDto<ParkingOrderViewResultDto> parkingOrderViewListResultDto = customerParkOrderIntegrationService.getParkingOrderList(parkingOrderListGetRequestDto);

                if (parkingOrderViewListResultDto.isSuccess() && CollectionUtils.isNotEmpty(parkingOrderViewListResultDto.getItems())) {

                    List<UserParkingRecordViewResultDto> useParkingResultDtos = new ArrayList<>();

                    List<ParkingOrderViewResultDto> orderViewResultDtoList = parkingOrderViewListResultDto.getItems();

                    for (ParkingOrderViewResultDto orderView : orderViewResultDtoList) {
                        UserParkingRecordViewResultDto recordView = new UserParkingRecordViewResultDto();

                        recordView.setRecordNo(orderView.getOrderNo());
                        recordView.setActualPayAmount(orderView.getActualPayAmount());
                        recordView.setCarStyle(orderView.getCarStyle());
                        recordView.setPlateNumber(orderView.getPlateNumber());
                        recordView.setEndTime(orderView.getEndTime());
                        recordView.setStartTime(orderView.getStartTime());
                        recordView.setPlateColor(orderView.getPlateColor());
                        recordView.setParkingLength(orderView.getParkingLength());
                        recordView.setPayable(orderView.getPayable());
                        recordView.setPayStatus(orderView.getPayStatus());
                        recordView.setPayableAmount(orderView.getPayableAmount());
                        recordView.setParkingName(orderView.getParkingName());
                        recordView.setParkingTime(orderView.getParkingTime());
                        recordView.setParkingLotCode(orderView.getParkingLotCode());
                        recordView.setStatus(orderView.getStatus());
                        recordView.setPayTime(orderView.getPayTime());
                        useParkingResultDtos.add(recordView);

                    }
                    listResultDto.setItems(useParkingResultDtos);
                }
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户停车记录失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页获取用户停车记录
     */
    @Override
    public PagedResultDto<UserParkingRecordViewResultDto> getUserParkingRecordPageList(UserParkingRecordPagedResultRequestDto requestDto) {

        PagedResultDto<UserParkingRecordViewResultDto> pagedResultDto = new PagedResultDto<>();
        if (StringUtils.isEmpty(requestDto.getPlateNumber())) {
            return pagedResultDto.makeResult(UCenterResultEnum.CAR_PLATE_NUMBER_EMPTY.getValue(),
                    UCenterResultEnum.CAR_PLATE_NUMBER_EMPTY.getComment()
            );
        }
        try {

            // 获取用户ID
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
                ParkingOrderQueryByPlatePageRequestDto parkingOrderListGetRequestDto = new ParkingOrderQueryByPlatePageRequestDto();
                parkingOrderListGetRequestDto.setPlateNumber(requestDto.getPlateNumber());
                parkingOrderListGetRequestDto.setPageSize(requestDto.getPageSize());
                parkingOrderListGetRequestDto.setPageNo(requestDto.getPageNo());
                PagedResultDto<ParkingOrderViewResultDto> parkingOrderViewListResultDto = customerParkOrderIntegrationService.getParkingOrderPageList(parkingOrderListGetRequestDto);

                if (parkingOrderViewListResultDto.isSuccess() && CollectionUtils.isNotEmpty(parkingOrderViewListResultDto.getItems())) {

                    List<UserParkingRecordViewResultDto> useParkingResultDtos = new ArrayList<>();

                    List<ParkingOrderViewResultDto> orderViewResultDtoList = parkingOrderViewListResultDto.getItems();

                    for (ParkingOrderViewResultDto orderView : orderViewResultDtoList) {

                        UserParkingRecordViewResultDto recordView = new UserParkingRecordViewResultDto();
                        recordView.setRecordNo(orderView.getOrderNo());
                        recordView.setActualPayAmount(orderView.getActualPayAmount());
                        recordView.setCarStyle(orderView.getCarStyle());
                        recordView.setPlateNumber(orderView.getPlateNumber());
                        recordView.setEndTime(orderView.getEndTime());
                        recordView.setStartTime(orderView.getStartTime());
                        recordView.setPlateColor(orderView.getPlateColor());
                        recordView.setParkingLength(orderView.getParkingLength());
                        recordView.setPayable(orderView.getPayable());
                        recordView.setPayStatus(orderView.getPayStatus());
                        recordView.setPayableAmount(orderView.getPayableAmount());
                        recordView.setParkingName(orderView.getParkingName());
                        recordView.setParkingTime(orderView.getParkingTime());
                        recordView.setParkingLotCode(orderView.getParkingLotCode());
                        recordView.setStatus(orderView.getStatus());
                        recordView.setPayTime(orderView.getPayTime());
                        useParkingResultDtos.add(recordView);
                    }
                    pagedResultDto.setPageSize(parkingOrderViewListResultDto.getPageSize());
                    pagedResultDto.setPageNo(parkingOrderViewListResultDto.getPageNo());
                    pagedResultDto.setTotalCount(parkingOrderViewListResultDto.getTotalCount());
                    pagedResultDto.setItems(useParkingResultDtos);
                }
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取用户停车记录失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取停车记录
     */
    @Override
    public ObjectResultDto<UserParkingRecordResultDto> getUserParkingRecord(UserParkingRecordGetRequestDto requestDto) {
        ObjectResultDto<UserParkingRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {

//            EntityWrapper<UserParkingOrder> entityWrapper = new EntityWrapper<>();
//            if (requestDto.getId() != null) {
//                entityWrapper.eq("id", requestDto.getId());
//            }
//            if (StringUtils.isNotEmpty(requestDto.getRecordNo())) {
//                entityWrapper.eq("recordNo", requestDto.getRecordNo());
//            }
//            UserParkingOrder userParkingRecord = userParkingRecordCrudService.selectOne(entityWrapper);
//            if (userParkingRecord == null) {
//                return objectResultDto.makeResult(UCenterResultEnum.PARKING_RECORD_NOT_FOUND.getValue(),
//                        UCenterResultEnum.PARKING_RECORD_NOT_FOUND.getComment()
//                );
//            }
//            UserParkingRecordResultDto resultDto = modelMapper.map(userParkingRecord, UserParkingRecordResultDto.class);
//            objectResultDto.setData(resultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户停车记录失败" + e.getMessage());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据停车记录ID获取停车记录
     */
    @Override
    public ObjectResultDto<UserParkingRecordDetailResultDto> getUserParkingRecordView(UserParkingRecordGetRequestDto requestDto) {
        ObjectResultDto<UserParkingRecordDetailResultDto> objectResultDto = new ObjectResultDto<>();
        try {

//            if (requestDto.getId() == null && StringUtils.isBlank(requestDto.getRecordNo())) {
//                return objectResultDto.makeResult(UCenterResultEnum.REQUEST_EMPRY.getValue(),
//                        UCenterResultEnum.REQUEST_EMPRY.getComment());
//            }
//            EntityWrapper<UserParkingOrder> wrapper = new EntityWrapper<>();
//            if (requestDto.getId() != null) {
//                wrapper.eq("id", requestDto.getId());
//            }
//            if (StringUtils.isNotEmpty(requestDto.getRecordNo())) {
//                wrapper.eq("recordNo", requestDto.getRecordNo());
//            }
//            UserParkingOrder userParkingRecord = userParkingRecordCrudService.selectOne(wrapper);
//            if (userParkingRecord == null) {
//                return objectResultDto.makeResult(UCenterResultEnum.PARKING_RECORD_NOT_FOUND.getValue(),
//                        UCenterResultEnum.PARKING_RECORD_NOT_FOUND.getComment()
//                );
//            }
//            ParkingLocationGetRequestDto parkingLocationGetRequestDto = new ParkingLocationGetRequestDto();
//            parkingLocationGetRequestDto.setParkingId(userParkingRecord.getParkingId());
//            ObjectResultDto<ParkingLocationResultDto> parkingAddress = parkingInfoService.getParkingAddress(parkingLocationGetRequestDto);
//
//            if (parkingAddress.isSuccess() && null != parkingAddress.getData()) {
//                String address = parkingAddress.getData().getAddress();
//                userParkingRecord.setParkingAddress(address);
//            }
//            UserParkingRecordDetailResultDto resultDto = modelMapper.map(userParkingRecord, UserParkingRecordDetailResultDto.class);
//            ParkingRecordImageGetRequestDto parkingRecordImageGetRequestDto = new ParkingRecordImageGetRequestDto();
//            parkingRecordImageGetRequestDto.setRecordNo(userParkingRecord.getRecordNo());
//            ListResultDto<ParkingImageViewResultDto> imageListResultDto = parkingRecordService.getParkingRecordImageList(parkingRecordImageGetRequestDto);
//            if (imageListResultDto.isSuccess()) {
//                resultDto.setImages(imageListResultDto.getItems());
//            }
//            objectResultDto.setData(resultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户停车记录失败" + e.getMessage());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }
}
