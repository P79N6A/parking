package com.zoeeasy.cloud.order.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.core.enums.ChargeModeEnum;
import com.zoeeasy.cloud.core.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.order.domain.ParkingOrderEntity;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.parking.ParkingOrderManagerService;
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

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务
 *
 * @author AkeemSuper
 * @date 2018/10/8 0008
 */
@Service(value = "parkingOrderManagerService")
@Slf4j
public class ParkingOrderManagerServiceImpl implements ParkingOrderManagerService {

    @Autowired
    private ParkingOrderCrudService parkingOrderCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 保存订单
     *
     * @param requestDto ParkingOrderInsertRequestDto
     * @return ObjectResultDto<ParkingOrderSaveResultDto>
     */
    @Override
    public ObjectResultDto<ParkingOrderCreateResultDto> createParkingOrder(ParkingOrderInsertRequestDto requestDto) {
        ObjectResultDto<ParkingOrderCreateResultDto> resultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity = modelMapper.map(requestDto, ParkingOrderEntity.class);
            parkingOrderEntity.setCreationTime(DateUtils.now());
            boolean insert = parkingOrderCrudService.insert(parkingOrderEntity);
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
     * 根据单号获取订单信息
     *
     * @param requestDto ParkingOrderGetByOrderNoRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> findByOrderNo(ParkingOrderGetByOrderNoRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity = parkingOrderCrudService.findByOrderNo(requestDto.getOrderNo());
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
     * 获取停车账单
     *
     * @param requestDto ParkingOrderGetRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> getParkingOrder(ParkingOrderGetRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrder = parkingOrderCrudService.findByOrderTenantLess(requestDto.getOrderNo());
            if (parkingOrder == null) {
                objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment()
                );
            } else {
                ParkingOrderResultDto parkingOrderResultDto = modelMapper.map(parkingOrder, ParkingOrderResultDto.class);
                if (parkingOrderResultDto != null) {
                    objectResultDto.setData(parkingOrderResultDto);
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
     * 获取停车账单查询列表
     *
     * @param requestDto ParkingOrderSearchRequestDto
     * @return PagedResultDto<ParkingOrderResultDto>
     */
    @Override
    public PagedResultDto<ParkingOrderResultDto> getParkingOrderPageList(ParkingOrderSearchRequestDto requestDto) {
        PagedResultDto<ParkingOrderResultDto> pagedResultDto = new PagedResultDto<>();

        try {
            if (StringUtils.isNotEmpty(requestDto.getAreaCode()) && CollectionUtils.isEmpty(requestDto.getParkingId())) {
                return pagedResultDto.success();
            }
            EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
            //订单编号查询
            if (requestDto.getOrderNo() != null) {
                entityWrapper.like("orderNo", requestDto.getOrderNo());
            }

            //停车场查询
            if (StringUtils.isNotEmpty(requestDto.getParkingName())) {
                entityWrapper.like("parkingName", requestDto.getParkingName());
            }

            //订单类型
            if (requestDto.getOrderType() != null) {
                switch (requestDto.getOrderType()) {
                    case 1:
                        entityWrapper.eq("appointed", 0);
                        entityWrapper.eq("limitFree", 0);
                        break;
                    case 2:
                        entityWrapper.eq("appointed", 1);
                        break;
                    case 3:
                        entityWrapper.eq("limitFree", 1);
                        break;
                    default:
                }
            }
            //省市区
            if (CollectionUtils.isNotEmpty(requestDto.getParkingId())) {
                entityWrapper.in("parkingId", requestDto.getParkingId());
            }

            //车位
            if (requestDto.getParkingLotCode() != null) {
                entityWrapper.like("parkingLotNumber", requestDto.getParkingLotCode());
            }
            //车型
            if (requestDto.getCarStyle() != null) {
                entityWrapper.eq("carStyle", requestDto.getCarStyle());
            }

            if (StringUtils.isNotEmpty(requestDto.getPayableAmount())) {
                entityWrapper.eq("payableAmount", NumberUtils.amountYuan2Fen(requestDto.getPayableAmount()));
            }

            //车牌号
            if (StringUtils.isNotEmpty(requestDto.getPlateNumber())) {
                entityWrapper.like("plateNumber", requestDto.getPlateNumber());
            }

            //编辑人员
            if (StringUtils.isNotEmpty(requestDto.getEditor())) {
                entityWrapper.eq("editor", requestDto.getEditor());
            }

            //订单状态
            if (requestDto.getStatus() != null) {
                switch (requestDto.getStatus()) {
                    case 1:
                        entityWrapper.eq("status", ParkingStatusEnum.PARKING.getValue());
                        entityWrapper.eq("payStatus", PayStatusEnum.CREATED.getValue());
                        break;
                    case 2:
                        entityWrapper.eq("status", ParkingStatusEnum.PARKING.getValue());
                        entityWrapper.eq("payStatus", PayStatusEnum.PAY_SUCCESS.getValue());
                        break;
                    case 3:
                        entityWrapper.eq("status", ParkingStatusEnum.OUTING.getValue());
                        entityWrapper.eq("payStatus", PayStatusEnum.PAY_SUCCESS.getValue());
                        entityWrapper.eq("chargeMode", ChargeModeEnum.BEFORE.getValue());
                        break;
                    case 4:
                        entityWrapper.eq("status", ParkingStatusEnum.OUTING.getValue());
                        entityWrapper.eq("payStatus", PayStatusEnum.CREATED.getValue());
                        break;
                    case 5:
                        entityWrapper.eq("status", ParkingStatusEnum.OUTING.getValue());
                        entityWrapper.eq("payStatus", PayStatusEnum.PAY_SUCCESS.getValue());
                        entityWrapper.eq("chargeMode", ChargeModeEnum.AFTER.getValue());
                        break;
                }
            }
            //支付状态
            if (requestDto.getPayStatus() != null) {
                if (requestDto.getPayStatus() == 1) {
                    entityWrapper.eq("payStatus", PayStatusEnum.CREATED.getValue());
                } else {
                    entityWrapper.eq("payStatus", PayStatusEnum.PAY_SUCCESS.getValue());
                }
            }
            //是否手工单
            if (requestDto.getArtificial() != null) {
                entityWrapper.eq("artificial", requestDto.getArtificial());
            }
            //是否出场
            if (requestDto.getOuted() != null) {
                entityWrapper.eq("status", requestDto.getOuted());
            }
            if (null != requestDto.getBeginDate()) {
                entityWrapper.ge("startTime", requestDto.getBeginDate());
            }
            if (null != requestDto.getEndDate()) {
                entityWrapper.le("startTime", requestDto.getEndDate());
            }
            entityWrapper.orderBy("startTime", false);

            Page<ParkingOrderEntity> parkingOrderPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingOrderEntity> userParkingOrderPage = parkingOrderCrudService.selectPage(parkingOrderPage, entityWrapper);
            if (!(null == userParkingOrderPage || userParkingOrderPage.getRecords().isEmpty())) {

                List<ParkingOrderResultDto> list = modelMapper.map(userParkingOrderPage.getRecords(), new TypeToken<List<ParkingOrderResultDto>>() {
                }.getType());

                pagedResultDto.setPageNo(userParkingOrderPage.getCurrent());
                pagedResultDto.setPageSize(userParkingOrderPage.getSize());
                pagedResultDto.setTotalCount(userParkingOrderPage.getTotal());
                pagedResultDto.setItems(list);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取用户停车账单失败:{}", e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 添加网页停车账单备注
     *
     * @param requestDto ParkingOrderAddRemarkRequestDto
     * @return ObjectResultDto<Boolean>
     */
    @Override
    public ObjectResultDto<Boolean> setParkingOrderRemark(ParkingOrderAddRemarkRequestDto requestDto) {
        ObjectResultDto<Boolean> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getOrderNo())) {

            objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_NO_EMPTY.getValue(),
                    OrderResultEnum.PARKING_ORDER_NO_EMPTY.getComment()
            );
            return objectResultDto;
        }
        try {

            ParkingOrderEntity parkingOrder = parkingOrderCrudService.findByOrderNo(requestDto.getOrderNo());
            if (parkingOrder == null) {
                objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment()
                );
            } else {
                ParkingOrderEntity parkingOrderUpdate = new ParkingOrderEntity();
                parkingOrderUpdate.setRemark(requestDto.getRemark());
                //更新备注
                EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("orderNo", parkingOrder.getOrderNo());
                Boolean setSuccess = parkingOrderCrudService.update(parkingOrderUpdate, entityWrapper);
                objectResultDto.setData(setSuccess);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("添加停车订单备注失败" + e.getMessage());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 修改停车账单
     *
     * @param requestDto ParkingOrderUpdateRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto updateParkingOrder(ParkingOrderUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();

        if (StringUtils.isEmpty(requestDto.getOrderNo())) {

            resultDto.makeResult(OrderResultEnum.PARKING_ORDER_NO_EMPTY.getValue(),
                    OrderResultEnum.PARKING_ORDER_NO_EMPTY.getComment()
            );
            return resultDto;
        }
        try {

            ParkingOrderEntity parkingOrder = parkingOrderCrudService.findByOrderNo(requestDto.getOrderNo());
            if (null == parkingOrder) {
                resultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment()
                );
                return resultDto;
            } else {
                if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                    resultDto.makeResult(OrderResultEnum.PARKING_ORDER_PAYSUSSESS_CANT_UPDATE.getValue(),
                            OrderResultEnum.PARKING_ORDER_PAYSUSSESS_CANT_UPDATE.getComment()
                    );
                    return resultDto;
                } else {
                    EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq("orderNo", parkingOrder.getOrderNo());
                    parkingOrder.setStartTime(requestDto.getStartTime());
                    if (null != requestDto.getEndTime()) {
                        parkingOrder.setEndTime(requestDto.getEndTime());
                    } else {
                        parkingOrder.setEndTime(DateTimeUtils.getDateMax());
                    }
                    parkingOrder.setStatus(requestDto.getOuted());

                    parkingOrder.setPayableAmount(new BigDecimal(requestDto.getPayableAmount()).multiply(BigDecimal.valueOf(100)).intValue());
                    //编辑人员
                    parkingOrder.setEditor(requestDto.getEditor());
                    parkingOrderCrudService.update(parkingOrder, entityWrapper);
                }
            }
        } catch (Exception e) {
            log.error("修改停车账单失败" + e.getMessage());
            return resultDto.failed();
        }
        return resultDto.success();

    }

    /**
     * 删除停车账单
     *
     * @param requestDto ParkingOrderDeleteRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto deleteParkingOrder(ParkingOrderDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();

        try {
            ParkingOrderEntity parkingOrder = parkingOrderCrudService.findByOrderAndPlateNumber(requestDto.getOrderNo(), requestDto.getPlateNumber());
            if (null == parkingOrder) {
                resultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment()
                );
                return resultDto;
            } else {

                if (parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                    resultDto.makeResult(OrderResultEnum.PARKING_ORDER_PAYSUSSESS_CANT_UPDATE.getValue(),
                            OrderResultEnum.PARKING_ORDER_PAYSUSSESS_CANT_UPDATE.getComment()
                    );
                    return resultDto;
                }
                parkingOrderCrudService.deleteByOrderNo(parkingOrder.getOrderNo());
                resultDto.success();
            }

        } catch (Exception e) {
            log.error("删除停车账单失败" + e.getMessage());
            return resultDto.failed();
        }
        return resultDto;
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
            Wrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("recordNo", requestDto.getRecordNo());
            ParkingOrderEntity parkingOrderEntity = parkingOrderCrudService.selectOne(entityWrapper);
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
     * 更新订单
     *
     * @param requestDto ParkingOrderUpdateForPayRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto update(InspectParkingOrderUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Wrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("orderNo", requestDto.getOrderNo());
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            ParkingOrderEntity parkingOrderEntity = modelMapper.map(requestDto, ParkingOrderEntity.class);
            boolean update = parkingOrderCrudService.update(parkingOrderEntity, entityWrapper);
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
     * 根据车牌颜色和车牌号获取订单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> getParkingOrderByNumberAndColor(ParkingOrderGetByNumberAndColorRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity;
            parkingOrderEntity = parkingOrderCrudService.findOrder(requestDto.getPlateNumber(), requestDto.getPlateColor());
            if (null != parkingOrderEntity) {
                ParkingOrderResultDto parkingOrderResultDto = modelMapper.map(parkingOrderEntity, ParkingOrderResultDto.class);
                objectResultDto.setData(parkingOrderResultDto);
            } else {
                parkingOrderEntity = parkingOrderCrudService.findOrderByNumberAndColor(requestDto.getPlateNumber(), requestDto.getPlateColor());
                if (null != parkingOrderEntity) {
                    ParkingOrderResultDto parkingOrderResultDto = modelMapper.map(parkingOrderEntity, ParkingOrderResultDto.class);
                    objectResultDto.setData(parkingOrderResultDto);
                }
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据车牌颜色和车牌号获取订单失败" + e.getMessage(), e);
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据第三方账单编号查询账单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingOrderResultDto> getParkingOrderByThirdBillNo(ParkingOrderQueryByThirdBillNoRequestDto requestDto) {
        ObjectResultDto<ParkingOrderResultDto> resultDto = new ObjectResultDto<>();
        try {
            ParkingOrderEntity parkingOrderEntity = parkingOrderCrudService.findOrderByThirdBillNo(requestDto.getThirdBillNo());
            ParkingOrderResultDto parkingOrderResultDto = modelMapper.map(parkingOrderEntity, ParkingOrderResultDto.class);
            resultDto.setData(parkingOrderResultDto);
            resultDto.success();
        } catch (Exception e){
            log.error("根据第三方账单编号查询账单失败：" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
