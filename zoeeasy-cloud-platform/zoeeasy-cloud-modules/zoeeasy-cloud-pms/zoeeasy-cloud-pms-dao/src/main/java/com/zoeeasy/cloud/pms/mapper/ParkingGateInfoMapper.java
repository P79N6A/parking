package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:停车场出入口信息表Mapper
 * @Autor: Kane
 * @Date: 2018/9/17
 */
@Repository("parkingGateInfoMapper")
public interface ParkingGateInfoMapper extends BaseMapper<ParkingGateInfoEntity> {

    /**
     * 删除ParkingGateInfo(无租户)
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingGateInfoNonTenant(@Param("parkingId") Long parkingId);

    /**
     * 无租户根据云平台code查询出入口
     *
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    ParkingGateInfoEntity selectGateByCode(@Param("code") String code);

    /**
     * 无租户根据客户端编号查询
     *
     * @param localCode
     * @return
     */
    @SqlParser(filter = true)
    ParkingGateInfoEntity selectByCode(@Param("localCode") String localCode);

    /**
     * 无租户根据Id和名称查询车库内名称唯一
     *
     * @param garageId
     * @param name
     * @return
     */
    @SqlParser(filter = true)
    ParkingGateInfoEntity findByGarageIdeAndName(@Param("garageId") Long garageId, @Param("name") String name);

    /**
     * 无租户，未关联车库，出入口名称停车场内唯一
     *
     * @param parkingId
     * @param name
     * @return
     */
    @SqlParser(filter = true)
    ParkingGateInfoEntity findByParkingIdeAndName(@Param("parkingId") Long parkingId, @Param("name") String name);

    /**
     * 无租户，根据停车场Id和编号查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    ParkingGateInfoEntity selectByParkingIdeAndCode(@Param("parkingId") Long parkingId,@Param("code") String code);

    /**
     *无租户，根据车库Id和出入口编号查询
     *
     * @param garageId
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    ParkingGateInfoEntity findByGarageIdAndCode(@Param("garageId") Long garageId,@Param("code") String code);

    /**
     * 修改gate(无租户)
     * @param parkingGateInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingGateNonTenant(ParkingGateInfoEntity parkingGateInfoEntity);

}
