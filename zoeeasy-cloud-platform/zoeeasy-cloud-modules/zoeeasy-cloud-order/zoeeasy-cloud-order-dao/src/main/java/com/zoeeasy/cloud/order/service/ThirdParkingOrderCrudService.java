package com.zoeeasy.cloud.order.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.order.domain.ThirdParkingOrderEntity;

import java.util.Date;

/**
 * 海康平台订单curd服务
 *
 * @author AkeemSuper
 * @date 2018/9/29 0029
 */
public interface ThirdParkingOrderCrudService extends CrudService<ThirdParkingOrderEntity> {

    /**
     * 平台停车记录编号查找
     *
     * @param recordNo recordNo
     * @return
     */
    ThirdParkingOrderEntity findByRecordNo(String recordNo);

    /**
     * 平台停车账单编号查找
     *
     * @param orderNo orderNo
     * @return
     */
    ThirdParkingOrderEntity findByOrderNo(String orderNo, Long tenantId);

    /**
     * 账单编号查找
     *
     * @param billNo billNo
     * @return
     */
    ThirdParkingOrderEntity findByBillNo(String billNo);

    /**
     * 修改停车订单支付状态
     *
     * @param orderId   orderId
     * @param billNo    orderNo
     * @param payType   payType
     * @param payTime   payTime
     * @param status    status
     * @param payAmount payAmount
     * @return
     */
    Integer updatePayStatus(Long orderId, String billNo, Date payTime, Integer status, Integer payAmount, Integer payType);

    /**
     * 保存海康订单
     *
     * @param thirdParkingOrderEntity
     * @return
     */
    boolean saveOrder(ThirdParkingOrderEntity thirdParkingOrderEntity);
}
