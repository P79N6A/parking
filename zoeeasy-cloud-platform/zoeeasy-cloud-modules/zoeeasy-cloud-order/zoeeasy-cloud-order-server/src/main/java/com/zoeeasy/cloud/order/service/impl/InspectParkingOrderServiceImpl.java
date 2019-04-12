package com.zoeeasy.cloud.order.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.core.enums.PayStateEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayTypeEnum;
import com.zoeeasy.cloud.order.domain.ParkingOrderEntity;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.inspect.InspectParkingOrderService;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectGetParkingOrderListRequestDto;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectUpdateParkingOrderRequestDto;
import com.zoeeasy.cloud.order.inspect.validator.InspectUpdateParkingOrderRequestDtoValidator;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetPlateOrderNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.service.ParkingOrderCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/1 0001
 */
@Service("inspectParkingOrderService")
@Slf4j
public class InspectParkingOrderServiceImpl implements InspectParkingOrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingOrderCrudService parkingOrderCrudService;

    /**
     * 分页获取用户停车订单
     */
    @Override
    public PagedResultDto<ParkingOrderResultDto> getParkingOrderPageList(InspectGetParkingOrderListRequestDto requestDto) {

        PagedResultDto<ParkingOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            if (requestDto.getPayStatus() != null) {
                //已支付
                PayStateEnum payStateEnum = PayStateEnum.parse(requestDto.getPayStatus());
                if (payStateEnum != null) {

                    if (payStateEnum.getValue().equals(PayStateEnum.NOT_PAYED.getValue())) {
                        //未支付
                        entityWrapper.andNew().eq("payStatus", PayStatusEnum.CREATED.getValue()).or().eq("payStatus", PayStatusEnum.PAY_WAITING.getValue());
                    } else if (payStateEnum.getValue().equals(PayStateEnum.PAYED.getValue())) {
                        //已支付
                        entityWrapper.andNew().eq("payStatus", PayStatusEnum.PAY_SUCCESS.getValue());
                    }
                }
            }
            entityWrapper.orderBy("startTime", false);
            entityWrapper.orderBy("payStatus", true);
            Page<ParkingOrderEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingOrderEntity> parkingOrderEntityPage = parkingOrderCrudService.selectPage(page, entityWrapper);
            if (CollectionUtils.isNotEmpty(parkingOrderEntityPage.getRecords())) {
                List<ParkingOrderResultDto> parkingResultDtoList = modelMapper.map(parkingOrderEntityPage.getRecords(),
                        new TypeToken<List<ParkingOrderResultDto>>() {
                        }.getType());
                pagedResultDto.setItems(parkingResultDtoList);

                pagedResultDto.setPageNo(requestDto.getPageNo());
                pagedResultDto.setPageSize(requestDto.getPageSize());
                pagedResultDto.setTotalCount(parkingOrderEntityPage.getTotal());
                pagedResultDto.setItems(parkingResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取停车订单失败,异常信息{}" , e.getMessage());
            pagedResultDto.makeResult(OrderResultEnum.PARKING_ORDER_LIST_GET_ERR.getValue(), OrderResultEnum.PARKING_ORDER_LIST_GET_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 获取停车账单
     *
     * @param requestDto ParkingOrderGetRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> getParkingOrder(ParkingOrderGetRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("orderNo", requestDto.getOrderNo());
            ParkingOrderEntity parkingOrder = parkingOrderCrudService.selectOne(entityWrapper);
            if (parkingOrder == null) {
                objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment());
            } else {
                ParkingOrderResultDto orderResultDto = modelMapper.map(parkingOrder, ParkingOrderResultDto.class);
                if (orderResultDto != null) {
                    objectResultDto.setData(orderResultDto);
                }
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取停车订单失败,异常信息{}" , e.getMessage());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据车牌号订单号获取停车订单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> getByPlateOrderNoInspect(ParkingOrderGetPlateOrderNoRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper();
            entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            entityWrapper.eq("orderNo", requestDto.getOrderNo());
            ParkingOrderEntity parkingOrderEntity = parkingOrderCrudService.selectOne(entityWrapper);
            if (null != parkingOrderEntity) {
                ParkingOrderResultDto parkingOrderResultDto = modelMapper.map(parkingOrderEntity, ParkingOrderResultDto.class);
                objectResultDto.setData(parkingOrderResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据单号获取订单信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 更新未支付订单支付渠道
     *
     * @param requestDto requestDto
     * @return PagedResultDto<ParkingOrderResultDto>
     */
    @Override
    public ResultDto updateParkingOrder(@FluentValid(InspectUpdateParkingOrderRequestDtoValidator.class) InspectUpdateParkingOrderRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("orderNo", requestDto.getOrderNo());
            ParkingOrderEntity payOrder = parkingOrderCrudService.selectOne(entityWrapper);
            if (null == payOrder) {
                return resultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(), OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment());
            }
            if (!payOrder.getPayableAmount().equals(NumberUtils.amountYuan2FenInt(requestDto.getPaymentAmount()))) {
                return resultDto.makeResult(OrderResultEnum.PARKING_ORDER_AMOUNT_INVALID.getValue(), OrderResultEnum.PARKING_ORDER_AMOUNT_INVALID.getComment());
            }
            if (PayStatusEnum.CREATED.getValue().equals(payOrder.getPayStatus()) || PayStatusEnum.PAY_WAITING.getValue().equals(payOrder.getPayStatus())) {
                ParkingOrderEntity parkingOrderEntity = new ParkingOrderEntity();
                parkingOrderEntity.setPayTime(new Date());
                parkingOrderEntity.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                parkingOrderEntity.setActualPayAmount(NumberUtils.amountYuan2Fen(requestDto.getPaymentAmount()).intValue());
                parkingOrderEntity.setPayWay(requestDto.getPayWay());
                parkingOrderEntity.setPayType(PayTypeEnum.OTHER.getValue());
                parkingOrderEntity.setPayable(Boolean.FALSE);
                parkingOrderEntity.setNeedPay(Boolean.FALSE);
                parkingOrderCrudService.update(parkingOrderEntity, entityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新停车订单失败" + e.getMessage());
            return resultDto.makeResult(OrderResultEnum.PARKING_ORDER_UPDATE_ERR.getValue(), OrderResultEnum.PARKING_ORDER_UPDATE_ERR.getComment());
        }
        return resultDto;
    }

}
