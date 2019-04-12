package com.zoeeasy.cloud.order.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.order.domain.ParkingOrderEntity;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingGuidanceRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/7 0007
 */
@Repository("parkingOrderMapper")
public interface ParkingOrderMapper extends BaseMapper<ParkingOrderEntity> {

    /**
     * 根据停车记录号查找
     *
     * @param recordNo 停车记录号
     * @return 订单
     */
    @SqlParser(filter = true)
    ParkingOrderEntity findByRecord(@Param("recordNo") String recordNo, @Param("tenantId") Long tenantId);

    /**
     * 根据orderNo查找
     *
     * @param orderNo 订单号
     * @return 订单
     */
    @SqlParser(filter = true)
    ParkingOrderEntity findByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据停车记录号及车牌号查询
     *
     * @param orderNo
     * @param plateNumber
     * @return
     */
    @SqlParser(filter = true)
    ParkingOrderEntity findByPlateOrderNo(@Param("plateNumber") String plateNumber, @Param("orderNo") String orderNo);

    /**
     * 保存订单
     *
     * @param parkingOrderEntity ParkingOrderEntity
     * @return 保存订单结果
     */
    @SqlParser(filter = true)
    boolean saveOrder(ParkingOrderEntity parkingOrderEntity);

    /**
     * 更新订单
     *
     * @param parkingOrderEntity ParkingOrderEntity
     * @return 更新订单结果
     */
    @SqlParser(filter = true)
    boolean updateOrder(ParkingOrderEntity parkingOrderEntity);

    /**
     * 根据车牌号订单号更新订单
     *
     * @param parkingOrderEntity ParkingOrderEntity
     * @return 更新订单结果
     */
    @SqlParser(filter = true)
    boolean updateOrderByPlateNumber(ParkingOrderEntity parkingOrderEntity);

    /**
     * 获取订单列表
     *
     * @param wrapper 查询条件
     * @return 订单列表
     */
    @SqlParser(filter = true)
    List<ParkingOrderEntity> selectOrderList(@Param("ew") Wrapper<ParkingOrderEntity> wrapper);

    /**
     * 分页获取订单列表
     *
     * @param wrapper 查询条件
     * @return 分页订单列表
     */
    @SqlParser(filter = true)
    List<ParkingOrderEntity> selectOrderListPage(Pagination pagination, @Param("ew") Wrapper<ParkingOrderEntity> wrapper);

    /**
     * 获取订单列表条数
     *
     * @param wrapper 查询条件
     * @return 数目
     */
    @SqlParser(filter = true)
    Integer selectOrderListCount(@Param("ew") Wrapper<ParkingOrderEntity> wrapper);

    /**
     * 根据车牌号和车牌颜色获取账单
     *
     * @param plateNumber
     * @param plateColor
     * @return
     */
    @SqlParser(filter = true)
    ParkingOrderEntity findOrder(@Param("plateNumber") String plateNumber, @Param("plateColor") Integer plateColor);

    /**
     * 根据车牌号和车牌颜色获取账单
     *
     * @param plateNumber
     * @param plateColor
     * @return
     */
    @SqlParser(filter = true)
    ParkingOrderEntity findOrderByNumberAndColor(@Param("plateNumber") String plateNumber, @Param("plateColor") Integer plateColor);

    /**
     * 根据第三方账单编号查询账单
     *
     * @param thirdBillNo
     * @return
     */
    @SqlParser(filter = true)
    ParkingOrderEntity findOrderByThirdBillNo(@Param("thirdBillNo") String thirdBillNo);

}
