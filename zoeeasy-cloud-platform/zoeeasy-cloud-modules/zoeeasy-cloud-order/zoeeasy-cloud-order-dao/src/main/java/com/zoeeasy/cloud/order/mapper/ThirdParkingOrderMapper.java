package com.zoeeasy.cloud.order.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.order.domain.ThirdParkingOrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author AkeemSuper
 * @date 2018/9/29 0029
 */
@Repository
public interface ThirdParkingOrderMapper extends BaseMapper<ThirdParkingOrderEntity> {

    /**
     * 根据orderNo查找
     *
     * @param orderNo
     * @return
     */
    @SqlParser(filter = true)
    ThirdParkingOrderEntity findByOrderNo(@Param("orderNo") String orderNo, @Param("tenantId") Long tenantId);

    /**
     * 保存海康订单
     *
     * @param thirdParkingOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean saveOrder(ThirdParkingOrderEntity thirdParkingOrderEntity);

    @SqlParser(filter = true)
    ThirdParkingOrderEntity findByBillNo(@Param("billNo") String billNo);
}
