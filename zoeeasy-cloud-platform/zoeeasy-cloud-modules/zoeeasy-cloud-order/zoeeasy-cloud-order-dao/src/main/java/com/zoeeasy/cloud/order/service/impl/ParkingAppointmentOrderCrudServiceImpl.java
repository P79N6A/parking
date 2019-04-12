package com.zoeeasy.cloud.order.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.order.domain.ParkingAppointmentOrderEntity;
import com.zoeeasy.cloud.order.mapper.ParkingAppointmentOrderMapper;
import com.zoeeasy.cloud.order.service.ParkingAppointmentOrderCrudService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author walkman
 * @date 2017-11-21
 */
@Service("parkingAppointmentOrderCrudService")
public class ParkingAppointmentOrderCrudServiceImpl extends CrudServiceImpl<ParkingAppointmentOrderMapper, ParkingAppointmentOrderEntity> implements ParkingAppointmentOrderCrudService {

    /**
     * @param orderNo
     * @return 用户预约订单
     */
    @Override
    public ParkingAppointmentOrderEntity findByOrderNo(String orderNo) {
        ParkingAppointmentOrderEntity appointment = new ParkingAppointmentOrderEntity();
        appointment.setOrderNo(orderNo);
        return baseMapper.selectOne(appointment);
    }

    /**
     * 修改用户停车预约订单支付状态
     *
     * @param orderId
     * @param orderNo
     * @param payTime
     * @param payStatus
     * @param payAmount
     * @return
     */
    @Override
    public Integer updatePayStatus(Long orderId, String orderNo, Date payTime, Integer payStatus, Integer payAmount) {
        ParkingAppointmentOrderEntity userAppointment = new ParkingAppointmentOrderEntity();
        if (payTime != null) {
            userAppointment.setPayTime(payTime);
        }
        if (payAmount != null) {
            userAppointment.setActualPayAmount(payAmount);
        }
        if (payStatus != null) {
            userAppointment.setPayStatus(payStatus);
        }
        EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", orderId);
        entityWrapper.eq("orderNo", orderNo);
        return baseMapper.update(userAppointment, entityWrapper);
    }

    /**
     * 获取预约订单
     *
     * @param entityWrapper
     * @return
     */
    @Override
    public ParkingAppointmentOrderEntity selectAppointOrder(EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper) {
        return baseMapper.selectAppointOrder(entityWrapper);
    }

    /**
     * 根据订单号查找
     *
     * @param orderNo
     * @return
     */
    @Override
    public ParkingAppointmentOrderEntity selectByOrderNo(String orderNo) {
        return baseMapper.selectByOrderNo(orderNo);
    }

    /**
     * 根据订单号和id更新
     *
     * @param parkingAppointmentOrderEntityUpdate
     */
    @Override
    public void updateOrder(ParkingAppointmentOrderEntity parkingAppointmentOrderEntityUpdate) {
        baseMapper.updateOrder(parkingAppointmentOrderEntityUpdate);
    }

    /**
     * 通过停车订单号查找(无talentId)
     *
     * @return
     */
    @Override
    public ParkingAppointmentOrderEntity findByOrderNo(String orderNo, Long userId) {
        EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper = new EntityWrapper<>();
        if (userId != null) {
            entityWrapper.eq("customerUserId", userId);
        }
        entityWrapper.eq("orderNo", orderNo);
        return baseMapper.findByOrderNo(entityWrapper);
    }

    /**
     * 分页获取预约订单列表(无talentId)
     *
     * @return
     */
    @Override
    public List<ParkingAppointmentOrderEntity> selectAppointOrderListPage(Wrapper<ParkingAppointmentOrderEntity> wrapper, Integer pageSize, Integer pageNo) {
        return baseMapper.selectAppointOrderListPage(wrapper, pageSize, (pageNo > 0 ? pageNo - 1 : pageNo) * pageSize);
    }

    /**
     * 获取预约订单列表总数(无talentId)
     *
     * @return
     */
    @Override
    public Integer selectAppointOrderListCount(Wrapper<ParkingAppointmentOrderEntity> wrapper) {
        return baseMapper.selectAppointOrderListCount(wrapper);
    }

    /**
     * 获取预约订单列表(无talentId)
     *
     * @return
     */
    @Override
    public List<ParkingAppointmentOrderEntity> selectAppointOrderList(Wrapper<ParkingAppointmentOrderEntity> wrapper) {
        return baseMapper.selectAppointOrderList(wrapper);
    }

    /**
     * 更新订单
     *
     * @param parkingAppointmentOrderEntity
     * @return
     */
    @Override
    public boolean updateAppointOrder(ParkingAppointmentOrderEntity parkingAppointmentOrderEntity) {
        parkingAppointmentOrderEntity.setLastModificationTime(DateUtils.now());
        return baseMapper.updateAppointOrder(parkingAppointmentOrderEntity);
    }

    /**
     * 保存订单
     *
     * @param parkingAppointmentOrderEntity
     * @return
     */
    @Override
    public boolean saveAppointOrder(ParkingAppointmentOrderEntity parkingAppointmentOrderEntity) {
        parkingAppointmentOrderEntity.setCreationTime(DateUtils.now());
        return baseMapper.saveAppointOrder(parkingAppointmentOrderEntity);
    }
}
