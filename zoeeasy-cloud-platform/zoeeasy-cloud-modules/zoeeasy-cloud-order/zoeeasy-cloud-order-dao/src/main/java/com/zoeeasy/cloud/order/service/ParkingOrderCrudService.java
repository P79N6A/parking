package com.zoeeasy.cloud.order.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.order.domain.ParkingOrderEntity;

import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/7 0007
 */
public interface ParkingOrderCrudService extends CrudService<ParkingOrderEntity> {

    /**
     * 通过停车记录号查找
     *
     * @param recordNo recordNo
     * @return
     */
    ParkingOrderEntity findByRecord(String recordNo, Long tenantId);

    /**
     * 通过停车订单号查找
     *
     * @param orderNo orderNo
     * @return
     */
    ParkingOrderEntity findByOrderNo(String orderNo);

    /**
     * 通过车牌号、订单号查找
     *
     * @param orderNo
     * @param plateNumber
     * @return
     */
    ParkingOrderEntity findByPlateOrderNo(String plateNumber, String orderNo);

    /**
     * 修改停车订单支付状态
     *
     * @param orderId     orderId
     * @param orderNo     orderNo
     * @param payedUserId payedUserId
     * @param payTime     payTime
     * @param payStatus   payStatus
     * @param payAmount   payAmount
     * @return
     */
    Integer updatePayStatus(Long orderId, String orderNo, Long payedUserId, Date payTime, Integer payStatus, Integer payAmount);

    /**
     * 保存订单
     *
     * @param parkingOrderEntity
     * @return
     */
    boolean saveOrder(ParkingOrderEntity parkingOrderEntity);

    /**
     * 更新订单
     *
     * @param parkingOrderEntity
     * @return
     */
    boolean updateOrder(ParkingOrderEntity parkingOrderEntity);

    /**
     * 根据车牌号订单号更新订单
     *
     * @param parkingOrderEntity
     * @return
     */
    boolean updateOrderByPlateNumber(ParkingOrderEntity parkingOrderEntity);

    /**
     * 根据订单号删除
     *
     * @param orderNo orderNo
     * @return
     */
    Integer deleteByOrderNo(String orderNo);

    /**
     * 通过停车订单号查找(无talentId)
     *
     * @param orderNo orderNo
     * @return
     */
    ParkingOrderEntity findByOrderTenantLess(String orderNo);

    /**
     * 查询订单列表(无talentId)
     *
     * @return
     */
    List<ParkingOrderEntity> selectOrderList(ParkingOrderEntity parkingOrderEntity);

    /**
     * 分页获取订单列表
     *
     * @param page
     * @param wrapper
     * @return
     */
    Page<ParkingOrderEntity> selectOrderListPage(Page<ParkingOrderEntity> page, Wrapper<ParkingOrderEntity> wrapper);

    /**
     * 获取订单列表总数(无talentId)
     *
     * @return
     */
    Integer selectOrderListCount(Wrapper<ParkingOrderEntity> wrapper);

    /**
     * 根据订单号和车牌号查找
     *
     * @param orderNo
     * @param plateNumber
     * @return
     */
    ParkingOrderEntity findByOrderAndPlateNumber(String orderNo, String plateNumber);

    /**
     *根据车牌号和车牌颜色获取账单
     *
     * @param plateNumber
     * @param plateColor
     * @return
     */
    ParkingOrderEntity findOrder(String plateNumber, Integer plateColor);

    /**
     *根据车牌号和车牌颜色获取账单
     *
     * @param plateNumber
     * @param plateColor
     * @return
     */
    ParkingOrderEntity findOrderByNumberAndColor(String plateNumber, Integer plateColor);

    /**
     * 根据第三方账单编号查询账单
     *
     * @param thirdBillNo
     * @return
     */
    ParkingOrderEntity findOrderByThirdBillNo(String thirdBillNo);
}
