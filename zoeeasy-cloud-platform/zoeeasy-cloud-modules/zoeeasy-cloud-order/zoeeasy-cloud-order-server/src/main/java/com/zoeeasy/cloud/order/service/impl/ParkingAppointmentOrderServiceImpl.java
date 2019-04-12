package com.zoeeasy.cloud.order.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.core.enums.AppointStatusAdEnum;
import com.zoeeasy.cloud.core.enums.AppointStatusEnum;
import com.zoeeasy.cloud.core.enums.PayStatusAdEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.order.appoint.AppointmentOrderManagerService;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderEnterRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderPagedRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderRemarkAddRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderUpdateEnterRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderUpdateRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderValidPlateRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.CustomerAppointOrderPagedResultRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderGetByIdRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderGetRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderPageResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.domain.ParkingAppointmentOrderEntity;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.service.ParkingAppointmentOrderCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车位预约停车服务
 *
 * @author walkman
 * @date 2018-04-01
 */
@Service(value = "appointmentOrderManagerService")
@Slf4j
public class ParkingAppointmentOrderServiceImpl implements AppointmentOrderManagerService {

    @Autowired
    private ParkingAppointmentOrderCrudService parkingAppointmentOrderCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 后台分页获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingAppointmentOrderResultDto> getCustomerAppointOrderPagedList(CustomerAppointOrderPagedResultRequestDto requestDto) {
        PagedResultDto<ParkingAppointmentOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            if (null != requestDto.getParkingId()) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (StringUtils.isNotEmpty(requestDto.getPlateNumber())) {
                entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            }
            if (null != requestDto.getStartTime()) {
                entityWrapper.ge("scheduleTime", requestDto.getStartTime());
            }
            if (null != requestDto.getEndTime()) {
                entityWrapper.le("scheduleTime", requestDto.getEndTime());
            }
            Page<ParkingAppointmentOrderEntity> parkingPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingAppointmentOrderEntity> parkingPageList = parkingAppointmentOrderCrudService.selectPage(parkingPage, entityWrapper);
            if (null != parkingPageList && null != parkingPageList.getRecords() && parkingPageList.getRecords().size() > 0) {

                List<ParkingAppointmentOrderResultDto> appointOrderResultDtoList
                        = modelMapper.map(parkingPageList.getRecords(), new TypeToken<List<ParkingAppointmentOrderResultDto>>() {
                }.getType());

                pagedResultDto.setPageNo(parkingPageList.getCurrent());
                pagedResultDto.setPageSize(parkingPageList.getSize());
                pagedResultDto.setTotalCount(parkingPageList.getTotal());
                pagedResultDto.setItems(appointOrderResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("预约订单列表获取失败" + e.getMessage());
            pagedResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 获取预约订单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAppointmentOrderResultDto> getAppointOrder(ParkingAppointOrderGetRequestDto requestDto) {
        ObjectResultDto<ParkingAppointmentOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getCustomerUserId() != null) {
                entityWrapper.eq("customerUserId", requestDto.getCustomerUserId());
            }
            entityWrapper.eq("orderNo", requestDto.getOrderNo());
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.selectOne(entityWrapper);
            if (parkingAppointmentOrderEntity == null) {
                return objectResultDto.makeResult(OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            } else {
                ParkingAppointmentOrderResultDto userAppointmentResultDto = modelMapper.map(parkingAppointmentOrderEntity, ParkingAppointmentOrderResultDto.class);
                objectResultDto.setData(userAppointmentResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取预约订单失败" + e.getMessage());
            return objectResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return objectResultDto;
    }


    /**
     * 预约订单入场
     * TODO
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto enterAppointOrder(AppointOrderEnterRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.findByOrderNo(requestDto.getOrderNo());
            if (parkingAppointmentOrderEntity == null || !parkingAppointmentOrderEntity.getAppointStatus().equals(AppointStatusEnum.SUCCESS.getValue())) {
                return resultDto.failed();
            } else {
                ParkingAppointmentOrderEntity update = new ParkingAppointmentOrderEntity();
                update.setOrderNo(parkingAppointmentOrderEntity.getOrderNo());
                update.setAppointStatus(AppointStatusEnum.ENTERED.getValue());
                update.setEntrance(Boolean.TRUE);
                parkingAppointmentOrderCrudService.updateAppointOrder(update);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("预约车辆入场失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 管理后台分页查询预约订单列表
     *
     * @param requestDto
     * @return
     */

    @Override
    public PagedResultDto<AppointOrderPageResultDto> getOrderPagedListAdmin(AppointOrderPagedRequestDto requestDto) {
        PagedResultDto<AppointOrderPageResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();

            if (!CollectionUtils.isEmpty(requestDto.getParkingId())) {
                entityWrapper.in("parkingId", requestDto.getParkingId());
            }
            if (StringUtils.isNotEmpty(requestDto.getOrderNo())) {
                entityWrapper.like("orderNo", requestDto.getOrderNo());
            }
            if (null != requestDto.getPayAmount()) {
                entityWrapper.eq("payAmount", requestDto.getPayAmount());
            }
            if (StringUtils.isNotEmpty(requestDto.getParkingName())) {
                entityWrapper.like("parkingName", requestDto.getParkingName());
            }
            if (StringUtils.isNotEmpty(requestDto.getPlateNumber())) {
                entityWrapper.like("plateNumber", requestDto.getPlateNumber());
            }
            if (null != requestDto.getCarStyle()) {
                entityWrapper.eq("carStyle", requestDto.getCarStyle());
            }
            if (null != requestDto.getEntryStatus()) {
                entityWrapper.eq("entrance", requestDto.getEntryStatus());
            }
            if (null != requestDto.getStartTime()) {
                entityWrapper.ge("creationTime", requestDto.getStartTime());
            }
            if (null != requestDto.getEndTime()) {
                entityWrapper.le("creationTime", requestDto.getEndTime());
            }
            if (null != requestDto.getAppointStatus()) {
                if (requestDto.getAppointStatus().equals(AppointStatusAdEnum.USERIN.getValue())) {
                    entityWrapper.andNew().eq("appointStatus", AppointStatusEnum.CREATED.getValue()).or().eq("appointStatus", AppointStatusEnum.SUCCESS.getValue());
                } else if (requestDto.getAppointStatus().equals(AppointStatusAdEnum.CANCEL.getValue())) {
                    entityWrapper.andNew().eq("appointStatus", AppointStatusEnum.CANCELED.getValue());
                } else if (requestDto.getAppointStatus().equals(AppointStatusAdEnum.SUCCESS.getValue())) {
                    entityWrapper.andNew().eq("appointStatus", AppointStatusEnum.ENTERED.getValue()).or().eq("appointStatus", AppointStatusEnum.EXITED.getValue());
                }
            }
            if (null != requestDto.getPayStatus()) {
                if (requestDto.getPayStatus().equals(PayStatusAdEnum.PAY_WAITING.getValue())) {
                    entityWrapper.andNew().eq("payStatus", PayStatusEnum.CREATED.getValue()).or().eq("payStatus", PayStatusEnum.PAY_WAITING.getValue()).or().eq("payStatus", PayStatusEnum.PAY_TIMEOUT.getValue()).or().eq("payStatus", PayStatusEnum.PAY_CANCELED.getValue());
                } else if (requestDto.getPayStatus().equals(PayStatusAdEnum.PAY_SUCCESS.getValue())) {
                    entityWrapper.andNew().eq("payStatus", PayStatusEnum.PAY_SUCCESS.getValue());
                } else if (requestDto.getPayStatus().equals(PayStatusAdEnum.REFUND.getValue())) {
                    return pagedResultDto.success();
                }
            }
            entityWrapper.orderBy("creationTime", false);
            Page<ParkingAppointmentOrderEntity> appointmentOrderPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingAppointmentOrderEntity> parkingAppointmentOrderPage = parkingAppointmentOrderCrudService.selectPage(appointmentOrderPage, entityWrapper);
            if (!(null == parkingAppointmentOrderPage || parkingAppointmentOrderPage.getRecords().isEmpty())) {
                List<AppointOrderPageResultDto> appointOrderPageResultDtoList
                        = modelMapper.map(parkingAppointmentOrderPage.getRecords(), new TypeToken<List<AppointOrderPageResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(parkingAppointmentOrderPage.getCurrent());
                pagedResultDto.setPageSize(parkingAppointmentOrderPage.getSize());
                pagedResultDto.setTotalCount(parkingAppointmentOrderPage.getTotal());
                pagedResultDto.setItems(appointOrderPageResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取预约订单列表失败" + e.getMessage());
            pagedResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_LIST_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_LIST_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 添加备注
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addRemark(AppointOrderRemarkAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = new ParkingAppointmentOrderEntity();
            if (StringUtils.isNotEmpty(requestDto.getRemark())) {
                parkingAppointmentOrderEntity.setRemark(requestDto.getRemark());
            } else {
                parkingAppointmentOrderEntity.setRemark("");
            }
            EntityWrapper<ParkingAppointmentOrderEntity> entity = new EntityWrapper<>();
            entity.eq("orderNo", requestDto.getOrderNo());
            parkingAppointmentOrderCrudService.update(parkingAppointmentOrderEntity, entity);
            resultDto.success();
        } catch (Exception e) {
            log.error("备注失败" + e.getMessage());
            resultDto.makeResult(OrderResultEnum.REMARK_ERR.getValue(), OrderResultEnum.REMARK_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 根据订单号获取订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAppointmentOrderResultDto> getOrderByOrderNo(ParkingAppointOrderGetByIdRequestDto requestDto) {
        ObjectResultDto<ParkingAppointmentOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("orderNo", requestDto.getOrderNo());
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.selectOne(entityWrapper);
            if (parkingAppointmentOrderEntity == null) {
                return objectResultDto.makeResult(OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            } else {
                ParkingAppointmentOrderResultDto userAppointmentResultDto = modelMapper.map(parkingAppointmentOrderEntity, ParkingAppointmentOrderResultDto.class);
                objectResultDto.setData(userAppointmentResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取预约订单失败" + e.getMessage());
            return objectResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 带租户根据车牌获取有效的预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAppointmentOrderResultDto> getValidOrderList(AppointOrderValidPlateRequestDto requestDto) {
        ObjectResultDto<ParkingAppointmentOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            if (requestDto.getPlateColor() != null) {
                entityWrapper.eq("plateColor", requestDto.getPlateColor());
            }
            if (requestDto.getDeadlineTime() != null) {
                entityWrapper.gt("deadlineTime", requestDto.getDeadlineTime());
            }
            entityWrapper.eq("appointStatus", AppointStatusEnum.SUCCESS.getValue());
            entityWrapper.eq("payStatus", PayStatusEnum.PAY_SUCCESS.getValue());
            //按照预约入场时间取第一个
            entityWrapper.orderBy("scheduleTime");
            entityWrapper.last("LIMIT 1");
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.selectOne(entityWrapper);
            if (parkingAppointmentOrderEntity == null) {
                return objectResultDto.makeResult(OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
            } else {
                ParkingAppointmentOrderResultDto userAppointmentResultDto = modelMapper.map(parkingAppointmentOrderEntity, ParkingAppointmentOrderResultDto.class);
                objectResultDto.setData(userAppointmentResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取预约订单失败" + e.getMessage());
            return objectResultDto.makeResult(OrderResultEnum.GET_USER_APPOINT_ERR.getValue(), OrderResultEnum.GET_USER_APPOINT_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 带租户更新预约订单入场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateEnterAppointOrder(AppointOrderUpdateEnterRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("orderNo", requestDto.getOrderNo());
            ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.selectByOrderNo(requestDto.getOrderNo());
            if (parkingAppointmentOrderEntity == null || !parkingAppointmentOrderEntity.getAppointStatus().equals(AppointStatusEnum.SUCCESS.getValue())) {
                return resultDto.failed();
            } else {
                entityWrapper.eq("id", parkingAppointmentOrderEntity.getId());
                ParkingAppointmentOrderEntity parkingAppointmentOrderEntityUpdate = new ParkingAppointmentOrderEntity();
                parkingAppointmentOrderEntityUpdate.setAppointStatus(AppointStatusEnum.ENTERED.getValue());
                parkingAppointmentOrderCrudService.update(parkingAppointmentOrderEntityUpdate, entityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("预约车辆入场失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新预约订单无租户
     *
     * @param requestDto ParkingOrderUpdateForPayRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto updateAppointOrder(AppointOrderUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingAppointmentOrderEntity orderUpdate = modelMapper.map(requestDto, ParkingAppointmentOrderEntity.class);
            boolean update = parkingAppointmentOrderCrudService.updateAppointOrder(orderUpdate);
            if (!update) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新预约订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}

