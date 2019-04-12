package com.zoeeasy.cloud.order.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.core.enums.PayStateEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.order.domain.ParkingOrderEntity;
import com.zoeeasy.cloud.order.mapper.ParkingOrderMapper;
import com.zoeeasy.cloud.order.parking.PlatformParkingOrderService;
import com.zoeeasy.cloud.order.parking.dto.request.*;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderCreateResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.service.ParkingOrderCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 平台停车订单服务
 *
 * @author walkman
 * @date 2018/11/1
 */
@Service("platformParkingOrderService")
@Slf4j
public class PlatformParkingOrderServiceImpl implements PlatformParkingOrderService {

    @Autowired
    private ParkingOrderCrudService parkingOrderCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ParkingOrderMapper parkingOrderMapper;

    /**
     * 分页获取用户停车订单
     */
    @Override
    public PagedResultDto<ParkingOrderResultDto> getParkingOrderPageList(ParkingOrderQueryByPlatePageRequestDto requestDto) {

        PagedResultDto<ParkingOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            if (null != requestDto.getPlateColor()) {
                entityWrapper.eq("plateColor", requestDto.getPlateColor());
            }
            if (null != requestDto.getParkingId()) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (requestDto.getPayStatus() != null) {
                //已支付
                PayStateEnum payStateEnum = PayStateEnum.parse(requestDto.getPayStatus());
                if (payStateEnum != null) {

                    if (payStateEnum.getValue().equals(PayStateEnum.NOT_PAYED.getValue())) {
                        //未支付
                        entityWrapper.andNew("payStatus={0} OR payStatus={1} ",
                                PayStatusEnum.CREATED.getValue(), PayStatusEnum.PAY_WAITING.getValue());
                    } else if (payStateEnum.getValue().equals(PayStateEnum.PAYED.getValue())) {
                        //已支付
                        entityWrapper.andNew("payStatus={0}", PayStatusEnum.PAY_SUCCESS.getValue());
                    }
                }
            }
            entityWrapper.orderBy("startTime", false);
            entityWrapper.orderBy("payStatus", true);
            Page<ParkingOrderEntity> parkingOrderPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingOrderEntity> parkingOrderPageList = parkingOrderCrudService.selectOrderListPage(parkingOrderPage, entityWrapper);

            if (CollectionUtils.isNotEmpty(parkingOrderPageList.getRecords())) {

                List<ParkingOrderResultDto> parkingResultDtoList = modelMapper.map(parkingOrderPageList.getRecords(), new TypeToken<List<ParkingOrderResultDto>>() {
                }.getType());
                pagedResultDto.setItems(parkingResultDtoList);
                pagedResultDto.setPageNo(requestDto.getPageNo());
                pagedResultDto.setPageSize(requestDto.getPageSize());
                pagedResultDto.setTotalCount(parkingOrderPageList.getTotal());
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
     * 获取停车账单
     *
     * @param requestDto requestDto
     * @return ListResultDto<ParkingOrderResultDto>
     */
    @Override
    public ListResultDto<ParkingOrderResultDto> getParkingOrderList(ParkingOrderListGetRequestDto requestDto) {
        ListResultDto<ParkingOrderResultDto> listResultDto = new ListResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity = new ParkingOrderEntity();
            parkingOrderEntity.setPlateNumber(requestDto.getPlateNumber());
            parkingOrderEntity.setPayStatus(requestDto.getPayStatus());
            List<ParkingOrderEntity> userParkingOrderList = parkingOrderCrudService.selectOrderList(parkingOrderEntity);
            if (CollectionUtils.isNotEmpty(userParkingOrderList)) {

                List<ParkingOrderResultDto> parkingResultDtoList = modelMapper.map(userParkingOrderList, new TypeToken<List<ParkingOrderResultDto>>() {
                }.getType());
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
     * 支付更新订单
     *
     * @param requestDto ParkingOrderUpdateForPayRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto updateParkingOrder(ParkingOrderPayStatusUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingOrderEntity parkingOrderEntity = modelMapper.map(requestDto, ParkingOrderEntity.class);
            parkingOrderEntity.setLastModificationTime(DateUtils.now());
            boolean update = parkingOrderCrudService.updateOrder(parkingOrderEntity);
            if (!update) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 停车订单准备支付更新
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @Override
    public ResultDto updateParkingOrder(ParkingOrderPayingUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingOrderEntity parkingOrderEntity = modelMapper.map(requestDto, ParkingOrderEntity.class);
            parkingOrderEntity.setLastModificationTime(DateUtils.now());
            boolean update = parkingOrderCrudService.updateOrderByPlateNumber(parkingOrderEntity);
            if (!update) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据订单号车牌号更新订单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateParkingOrderByPlatNumber(ParkingOrderUpdateByPlateNumberRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingOrderEntity parkingOrderEntity = modelMapper.map(requestDto, ParkingOrderEntity.class);
            parkingOrderEntity.setLastModificationTime(DateUtils.now());
            boolean update = parkingOrderCrudService.updateOrderByPlateNumber(parkingOrderEntity);
            if (!update) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据单号获取订单信息
     *
     * @param requestDto requestDto
     * @return ParkingOrderResultDto
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> getByOrderNo(ParkingOrderGetByOrderNoRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity = parkingOrderCrudService.findByOrderTenantLess(requestDto.getOrderNo());
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
     * 根据车牌号订单号获取停车订单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> getByPlateOrderNo(ParkingOrderGetPlateOrderNoRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity = parkingOrderCrudService.findByPlateOrderNo(requestDto.getPlateNumber(), requestDto.getOrderNo());
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
     * 根据停车记录号获取订单信息
     *
     * @param requestDto ParkingOrderGetByRecordNoRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> getParkingOrderByRecordNo(ParkingOrderGetByRecordNoRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity = parkingOrderCrudService.findByRecord(requestDto.getRecordNo(), requestDto.getTenantId());
            if (parkingOrderEntity != null) {
                ParkingOrderResultDto parkingOrderResultDto = modelMapper.map(parkingOrderEntity, ParkingOrderResultDto.class);
                objectResultDto.setData(parkingOrderResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据停车记录号获取订单信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据停车记录号车牌号获取订单信息
     *
     * @param requestDto ParkingOrderGetByRecordNoRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> getParkingOrderByRecordNoAndPlate(ParkingOrderGetByRecordNoPlateRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity = parkingOrderCrudService.findByRecord(
                    requestDto.getRecordNo(),
                    requestDto.getTenantId());
            if (parkingOrderEntity != null) {
                ParkingOrderResultDto parkingOrderResultDto = modelMapper.map(parkingOrderEntity, ParkingOrderResultDto.class);
                objectResultDto.setData(parkingOrderResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据停车记录号获取订单信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 保存订单
     *
     * @param requestDto ParkingOrderSaveRequestDto
     * @return ObjectResultDto<ParkingOrderSaveResultDto>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<ParkingOrderCreateResultDto> saveParkingOrder(@FluentValid ParkingOrderCreateRequestDto requestDto) {
        ObjectResultDto<ParkingOrderCreateResultDto> resultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity = modelMapper.map(requestDto, ParkingOrderEntity.class);
            parkingOrderEntity.setCreationTime(DateUtils.now());
            boolean insert = parkingOrderCrudService.saveOrder(parkingOrderEntity);
            if (!insert) {
                return resultDto.failed();
            }
            ParkingOrderCreateResultDto parkingOrderSaveResultDto = new ParkingOrderCreateResultDto();
            parkingOrderSaveResultDto.setId(parkingOrderEntity.getId());
            resultDto.setData(parkingOrderSaveResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("保存订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改停车订单支付状态
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @Override
    public ResultDto updatePayStatus(ParkingOrderPayedUpdateRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {
            parkingOrderCrudService.updatePayStatus(requestDto.getOrderId(),
                    requestDto.getOrderNo(), requestDto.getPayedUserId(), requestDto.getPayTime(),
                    requestDto.getPayStatus(), requestDto.getPayAmount());
            resultDto.success();
        } catch (Exception e) {
            log.error("支付更新订单支付状态失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新账单结算记录
     *
     * @param requestDto
     */
    @Override
    public ResultDto updateSettleRecord(ParkingOrderSettleUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (resultDto != null) {
//                ParkingSettleRecordEntity parkingSettleRecordEntity = modelMapper.map(resultDto, ParkingSettleRecordEntity.class);
//                Boolean update = parkingSettleRecordCrudService.updateByOrderNo(parkingSettleRecordEntity);
//                if (!update) {
//                    resultDto.failed();
//                    return resultDto;
//                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新账单结算记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
