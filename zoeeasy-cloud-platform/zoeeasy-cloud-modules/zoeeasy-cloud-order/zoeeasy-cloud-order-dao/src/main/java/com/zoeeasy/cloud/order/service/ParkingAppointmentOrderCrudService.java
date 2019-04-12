package com.zoeeasy.cloud.order.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.order.domain.ParkingAppointmentOrderEntity;

import java.util.Date;
import java.util.List;

/**
 * @author walkman
 * @date 2018-03-30
 */
public interface ParkingAppointmentOrderCrudService extends CrudService<ParkingAppointmentOrderEntity> {

    /**
     * @param orderNo orderNo
     * @return 用户预约订单
     */
    ParkingAppointmentOrderEntity findByOrderNo(String orderNo);

    /**
     * 修改用户停车预约订单支付状态
     *
     * @param orderId   orderId
     * @param orderNo   orderNo
     * @param payTime   payTime
     * @param payStatus payStatus
     * @param payAmount payAmount
     * @return
     */
    Integer updatePayStatus(Long orderId, String orderNo, Date payTime, Integer payStatus, Integer payAmount);

    /**
     * 获取预约订单
     *
     * @param entityWrapper
     * @return
     */
    ParkingAppointmentOrderEntity selectAppointOrder(EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper);

    /**
     * 根据订单号查找
     *
     * @param orderNo
     * @return
     */
    ParkingAppointmentOrderEntity selectByOrderNo(String orderNo);

    /**
     * 根据订单号和id更新
     *
     * @param parkingAppointmentOrderEntityUpdate
     */
    void updateOrder(ParkingAppointmentOrderEntity parkingAppointmentOrderEntityUpdate);

    /**
     * 查询订单列表(无talentId)
     *
     * @return
     */
    ParkingAppointmentOrderEntity findByOrderNo(String orderNo, Long userId);

    /**
     * 分页获取预约订单列表(无talentId)
     *
     * @return
     */
    List<ParkingAppointmentOrderEntity> selectAppointOrderListPage(Wrapper<ParkingAppointmentOrderEntity> wrapper, Integer pageSize, Integer pageNo);

    /**
     * 获取预约订单列表总数(无talentId)
     *
     * @return
     */
    Integer selectAppointOrderListCount(Wrapper<ParkingAppointmentOrderEntity> wrapper);

    /**
     * 获取预约订单列表(无talentId)
     *
     * @return
     */
    List<ParkingAppointmentOrderEntity> selectAppointOrderList(Wrapper<ParkingAppointmentOrderEntity> wrapper);

    /**
     * 更新订单
     *
     * @param parkingAppointmentOrderEntity
     * @return
     */
    boolean updateAppointOrder(ParkingAppointmentOrderEntity parkingAppointmentOrderEntity);

    /**
     * 保存订单
     *
     * @param parkingAppointmentOrderEntity
     * @return
     */
    boolean saveAppointOrder(ParkingAppointmentOrderEntity parkingAppointmentOrderEntity);
}
