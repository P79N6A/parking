package com.zoeeasy.cloud.order.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.core.enums.PayStateEnum;
import com.zoeeasy.cloud.order.domain.ParkingOrderEntity;
import com.zoeeasy.cloud.order.mapper.ParkingOrderMapper;
import com.zoeeasy.cloud.order.service.ParkingOrderCrudService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/7 0007
 */
@Service("parkingOrderCrudService")
public class ParkingOrderCrudServiceImpl extends CrudServiceImpl<ParkingOrderMapper, ParkingOrderEntity> implements ParkingOrderCrudService {

    /**
     * 保存订单
     *
     * @param parkingOrderEntity
     * @return
     */
    @Override
    public boolean saveOrder(ParkingOrderEntity parkingOrderEntity) {
        return baseMapper.saveOrder(parkingOrderEntity);
    }

    /**
     * 更新订单
     *
     * @param parkingOrderEntity
     * @return
     */
    @Override
    public boolean updateOrder(ParkingOrderEntity parkingOrderEntity) {
        if (parkingOrderEntity.getLastModificationTime() == null) {
            parkingOrderEntity.setLastModificationTime(DateUtils.now());
        }
        return baseMapper.updateOrder(parkingOrderEntity);
    }

    /**
     * 根据车牌号订单号更新订单
     *
     * @param parkingOrderEntity
     * @return
     */
    @Override
    public boolean updateOrderByPlateNumber(ParkingOrderEntity parkingOrderEntity) {
        if (StringUtils.isEmpty(parkingOrderEntity.getOrderNo()) ||
                StringUtils.isEmpty(parkingOrderEntity.getPlateNumber())) {
            return false;
        }
        return baseMapper.updateOrderByPlateNumber(parkingOrderEntity);
    }

    /**
     * 通过停车记录号查找
     *
     * @param recordNo recordNo
     * @return
     */
    @Override
    public ParkingOrderEntity findByRecord(String recordNo, Long tenantId) {
        return baseMapper.findByRecord(recordNo, tenantId);
    }

    /**
     * 通过停车订单号查找
     *
     * @param orderNo orderNo
     * @return
     */
    @Override
    public ParkingOrderEntity findByOrderNo(String orderNo) {
        ParkingOrderEntity parkingOrder = new ParkingOrderEntity();
        parkingOrder.setOrderNo(orderNo);
        return baseMapper.selectOne(parkingOrder);
    }

    /**
     * 修改停车订单支付状态
     *
     * @param orderId   orderId
     * @param orderNo   orderNo
     * @param payTime   payTime
     * @param payStatus payStatus
     * @param payAmount payAmount
     * @return
     */
    @Override
    public Integer updatePayStatus(Long orderId, String orderNo, Long payedUserId, Date payTime, Integer payStatus, Integer payAmount) {

        ParkingOrderEntity parkingOrder = new ParkingOrderEntity();
        if (payAmount != null) {
            parkingOrder.setActualPayAmount(payAmount);
        }
        if (payStatus != null) {
            parkingOrder.setPayStatus(payStatus);
        }
        if (payTime != null) {
            parkingOrder.setPayTime(payTime);
        }
        if (payedUserId != null) {
            parkingOrder.setPayedUserId(payedUserId);
        }
        EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", orderId);
        entityWrapper.eq("orderNo", orderNo);
        return baseMapper.update(parkingOrder, entityWrapper);
    }

    /**
     * 根据orderNo删除
     *
     * @param orderNo orderNo
     * @return
     */
    @Override
    public Integer deleteByOrderNo(String orderNo) {
        EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("orderNo", orderNo);
        return baseMapper.delete(entityWrapper);
    }

    /**
     * 通过停车订单号查找(无talentId)
     *
     * @param orderNo orderNo
     * @return
     */
    @Override
    public ParkingOrderEntity findByOrderTenantLess(String orderNo) {
        return baseMapper.findByOrderNo(orderNo);
    }

    /**
     * 通过车牌号、订单号查找
     *
     * @param orderNo
     * @param plateNumber
     * @return
     */
    @Override
    public ParkingOrderEntity findByPlateOrderNo(String plateNumber, String orderNo) {
        return baseMapper.findByPlateOrderNo(plateNumber, orderNo);
    }

    /**
     * 通过停车订单号查找(无talentId)
     *
     * @return
     */
    @Override
    public List<ParkingOrderEntity> selectOrderList(ParkingOrderEntity parkingOrderEntity) {
        EntityWrapper<ParkingOrderEntity> entityWrapper = new EntityWrapper<>();
        if (StringUtils.isEmpty(parkingOrderEntity.getPlateNumber())) {
            entityWrapper.eq("plateNumber", parkingOrderEntity.getPlateNumber());
        }
        if (parkingOrderEntity.getPayStatus() != null) {
            //已支付
            PayStateEnum payStateEnum = PayStateEnum.parse(parkingOrderEntity.getPayStatus());
            if (payStateEnum != null) {
                if (payStateEnum.getValue().equals(PayStateEnum.NOT_PAYED.getValue())) {
                    //未支付
                    entityWrapper.andNew().or("payStatus={0}", "0").or("payStatus={0}", "1");
                } else if (payStateEnum.getValue().equals(PayStateEnum.PAYED.getValue())) {
                    //已支付
                    entityWrapper.andNew("payStatus={0}", "3");
                }
            }
        }
        entityWrapper.orderBy("startTime", false);
        entityWrapper.orderBy("payStatus", true);
        return baseMapper.selectOrderList(entityWrapper);
    }

    /**
     * 分页获取订单列表(无talentId)
     *
     * @return
     */
    @Override
    public Page<ParkingOrderEntity> selectOrderListPage(Page<ParkingOrderEntity> page, Wrapper<ParkingOrderEntity> wrapper) {
        return page.setRecords(baseMapper.selectOrderListPage(page, wrapper));
    }

    /**
     * 获取订单列表总数(无talentId)
     *
     * @return
     */
    @Override
    public Integer selectOrderListCount(Wrapper<ParkingOrderEntity> wrapper) {
        return baseMapper.selectOrderListCount(wrapper);
    }

    /**
     * 根据订单号和车牌号查找
     *
     * @param orderNo
     * @param plateNumber
     * @return
     */
    @Override
    public ParkingOrderEntity findByOrderAndPlateNumber(String orderNo, String plateNumber) {
        ParkingOrderEntity parkingOrder = new ParkingOrderEntity();
        parkingOrder.setOrderNo(orderNo);
        parkingOrder.setPlateNumber(plateNumber);
        return baseMapper.selectOne(parkingOrder);
    }

    /**
     * 根据车牌号和车牌颜色获取账单
     *
     * @param plateNumber
     * @param plateColor
     * @return
     */
    @Override
    public ParkingOrderEntity findOrder(String plateNumber, Integer plateColor) {
        return baseMapper.findOrder(plateNumber, plateColor);
    }

    /**
     * 根据车牌号和车牌颜色获取账单
     *
     * @param plateNumber
     * @param plateColor
     * @return
     */
    @Override
    public ParkingOrderEntity findOrderByNumberAndColor(String plateNumber, Integer plateColor) {
        return baseMapper.findOrderByNumberAndColor(plateNumber, plateColor);
    }

    /**
     * 根据第三方账单编号查询账单
     *
     * @param thirdBillNo
     * @return
     */
    @Override
    public ParkingOrderEntity findOrderByThirdBillNo(String thirdBillNo) {
        return baseMapper.findOrderByThirdBillNo(thirdBillNo);
    }
}
