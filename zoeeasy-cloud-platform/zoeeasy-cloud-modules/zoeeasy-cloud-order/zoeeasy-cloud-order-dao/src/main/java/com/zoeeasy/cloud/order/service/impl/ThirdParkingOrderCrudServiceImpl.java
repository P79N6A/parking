package com.zoeeasy.cloud.order.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.order.domain.ThirdParkingOrderEntity;
import com.zoeeasy.cloud.order.mapper.ThirdParkingOrderMapper;
import com.zoeeasy.cloud.order.service.ThirdParkingOrderCrudService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/29 0029
 */
@Service("thirdParkingOrderCrudService")
public class ThirdParkingOrderCrudServiceImpl extends CrudServiceImpl<ThirdParkingOrderMapper, ThirdParkingOrderEntity> implements ThirdParkingOrderCrudService {

    /**
     * 平台停车记录编号查找
     *
     * @param recordNo
     * @return
     */
    @Override
    public ThirdParkingOrderEntity findByRecordNo(String recordNo) {
        ThirdParkingOrderEntity thirdParkingOrderEntity = new ThirdParkingOrderEntity();
        thirdParkingOrderEntity.setRecordNo(recordNo);
        return baseMapper.selectOne(thirdParkingOrderEntity);
    }

    /**
     * 平台停车账单编号查找
     *
     * @param orderNo
     * @return
     */
    @Override
    public ThirdParkingOrderEntity findByOrderNo(String orderNo, Long tenantId) {
        return baseMapper.findByOrderNo(orderNo, tenantId);
    }

    /**
     * 账单编号查找
     *
     * @param billNo
     * @return
     */
    @Override
    public ThirdParkingOrderEntity findByBillNo(String billNo) {
        return baseMapper.findByBillNo(billNo);
    }

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
    @Override
    public Integer updatePayStatus(Long orderId, String billNo, Date payTime, Integer status, Integer payAmount, Integer payType) {
        ThirdParkingOrderEntity parkingOrder = new ThirdParkingOrderEntity();
        if (payAmount != null) {
            parkingOrder.setPayAmount(payAmount);
        }
        if (status != null) {
            parkingOrder.setStatus(status);
        }
        if (payTime != null) {
            parkingOrder.setPayTime(payTime);
        }
        if (payType != null) {
            parkingOrder.setPayType(payType);
        }
        EntityWrapper<ThirdParkingOrderEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", orderId);
        entityWrapper.eq("billNo", billNo);
        return baseMapper.update(parkingOrder, entityWrapper);
    }

    /**
     * 保存海康订单
     *
     * @param thirdParkingOrderEntity
     * @return
     */
    @Override
    public boolean saveOrder(ThirdParkingOrderEntity thirdParkingOrderEntity) {
        return baseMapper.saveOrder(thirdParkingOrderEntity);
    }
}
