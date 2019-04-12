package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.ParkingPacketRuleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Repository("ParkingPacketRuleMapper")
public interface ParkingPacketRuleMapper extends BaseMapper<ParkingPacketRuleEntity> {

    List<ParkingPacketRuleEntity> selectParkingPacketRules(@Param("packetType") Integer packetType, @Param("parkingIds") List<Long> parkingIds);

}
