package com.zoeeasy.cloud.order.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.order.domain.ParkingAppointmentOrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 * @date 2018-03-30
 */
@Repository
public interface ParkingAppointmentOrderMapper extends BaseMapper<ParkingAppointmentOrderEntity> {

    /**
     * 获取预约订单
     *
     * @param entityWrapper
     * @return
     */
    @SqlParser(filter = true)
    ParkingAppointmentOrderEntity selectAppointOrder(@Param("ew") EntityWrapper<ParkingAppointmentOrderEntity> entityWrapper);

    /**
     * 根据订单号查找
     *
     * @param orderNo
     * @return
     */
    @SqlParser(filter = true)
    ParkingAppointmentOrderEntity selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据id和订单号更新
     *
     * @param parkingAppointmentOrderEntityUpdate
     */
    @SqlParser(filter = true)
    void updateOrder(ParkingAppointmentOrderEntity parkingAppointmentOrderEntityUpdate);

    /**
     * 获取预约订单
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    ParkingAppointmentOrderEntity findByOrderNo(@Param("ew") Wrapper<ParkingAppointmentOrderEntity> wrapper);

    /**
     * 分页获取预约订单列表
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingAppointmentOrderEntity> selectAppointOrderListPage(@Param("ew") Wrapper<ParkingAppointmentOrderEntity> wrapper, @Param("pageSize") Integer pageSize, @Param("pageStart") Integer pageStart);

    /**
     * 获取预约订单列表条数
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    Integer selectAppointOrderListCount(@Param("ew") Wrapper<ParkingAppointmentOrderEntity> wrapper);

    /**
     * 获取预约订单列表
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingAppointmentOrderEntity> selectAppointOrderList(@Param("ew") Wrapper<ParkingAppointmentOrderEntity> wrapper);

    /**
     * 更新预约订单
     *
     * @param parkingAppointmentOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateAppointOrder(ParkingAppointmentOrderEntity parkingAppointmentOrderEntity);

    /**
     * 保存预约订单
     *
     * @param parkingAppointmentOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean saveAppointOrder(ParkingAppointmentOrderEntity parkingAppointmentOrderEntity);
}
