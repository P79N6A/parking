package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.session.SessionParkingInfo;
import com.zhuyitech.parking.ucc.dto.request.jsapi.JsapiParkingOrderGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.jsapi.JsapiParkingOrderPagedRequestDto;
import com.zhuyitech.parking.ucc.service.api.JsapiRelatedService;
import com.zoeeasy.cloud.core.enums.PayStateEnum;
import com.zoeeasy.cloud.integration.order.CustomerParkingOrderIntegrationService;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderDetailViewResultDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderDetailGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderQueryJsapiPageRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderViewResultDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 认证服务
 *
 * @author walkman
 */
@Service("jsapiRelatedService")
@Slf4j
public class JsapiRelatedServiceImpl implements JsapiRelatedService {

    @Autowired
    private CustomerParkingOrderIntegrationService customerParkOrderIntegrationService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 分页获取用户停车订单
     */
    @Override
    public PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageListApplication(JsapiParkingOrderPagedRequestDto requestDto) {

        PagedResultDto<ParkingOrderViewResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            ParkingOrderQueryJsapiPageRequestDto parkingOrderQueryJsapiPageRequestDto = modelMapper.map(requestDto, ParkingOrderQueryJsapiPageRequestDto.class);
            SessionParkingInfo currentParking = FundamentalRequestContext.getFundamentalRequestContext().getCurrentParking();
            if (currentParking != null) {
                parkingOrderQueryJsapiPageRequestDto.setParkingId(currentParking.getParkingId());
            }
            parkingOrderQueryJsapiPageRequestDto.setPayStatus(PayStateEnum.NOT_PAYED.getValue());
            return customerParkOrderIntegrationService.getParkingOrderPageListJsapi(parkingOrderQueryJsapiPageRequestDto);
        } catch (Exception e) {
            log.error("获取停车订单列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 根据停车订单ID获取停车订单
     */
    @Override
    public ObjectResultDto<ParkingOrderDetailViewResultDto> getParkingOrderView(JsapiParkingOrderGetRequestDto requestDto) {
        ObjectResultDto<ParkingOrderDetailViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            ParkingOrderDetailGetRequestDto parkingOrderDetailGetRequestDto = new ParkingOrderDetailGetRequestDto();
            parkingOrderDetailGetRequestDto.setOrderNo(requestDto.getOrderNo());
            return customerParkOrderIntegrationService.getParkingOrderDetailView(parkingOrderDetailGetRequestDto);

        } catch (Exception e) {
            log.error("获取停车订单失败" + e.getMessage());
            return objectResultDto.failed();
        }
    }

    /**
     * 分页获取用户停车订单
     */
    @Override
    public PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageList(JsapiParkingOrderPagedRequestDto requestDto) {

        PagedResultDto<ParkingOrderViewResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            ParkingOrderQueryJsapiPageRequestDto parkingOrderQueryJsapiPageRequestDto = modelMapper.map(requestDto, ParkingOrderQueryJsapiPageRequestDto.class);
            parkingOrderQueryJsapiPageRequestDto.setPayStatus(PayStateEnum.NOT_PAYED.getValue());
            return customerParkOrderIntegrationService.getParkingOrderPageListJsapi(parkingOrderQueryJsapiPageRequestDto);
        } catch (Exception e) {
            log.error("获取停车订单列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }
}