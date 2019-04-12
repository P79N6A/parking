package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.ParkingLaneInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by song on 2018/9/14.
 */
@Repository("parkingLaneInfoMapper")
public interface ParkingLaneInfoMapper extends BaseMapper<ParkingLaneInfoEntity> {

    /**
     * 删除ParkingLaneInfo（无租户）
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingLaneInfoNonTenant(@Param("parkingId") Long parkingId);

    /**
     * 无租户，根据云平台code查询
     *
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    ParkingLaneInfoEntity selectByCode(@Param("code") String code);

    /**
     * 无租户，根据客户端code查询
     *
     * @param localCode
     * @return
     */
    @SqlParser(filter = true)
    ParkingLaneInfoEntity selectByLocalCode(@Param("localCode") String localCode);

    /**
     * 无租户，根据出入口Id和车道名称查询
     *
     * @param gateId
     * @param name
     * @return
     */
    @SqlParser(filter = true)
    ParkingLaneInfoEntity selectGateIdAndName(@Param("gateId") Long gateId, @Param("name") String name);

    /**
     * 无租户，根据车库Id和车道名称查询
     *
     * @param garageId
     * @param name
     * @return
     */
    @SqlParser(filter = true)
    ParkingLaneInfoEntity selectByGarageIdAndName(@Param("garageId") Long garageId, @Param("name") String name);

    /**
     * 无租户，根据停车场Id，车道名称查询
     *
     * @param parkingId
     * @param name
     * @return
     */
    @SqlParser(filter = true)
    ParkingLaneInfoEntity selectByParkingIdAndName(@Param("parkingId") Long parkingId,@Param("name") String name);

    /**
     * 无租户，根据停车场Id和车道code查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    ParkingLaneInfoEntity selectByParkingIdAndCode(@Param("parkingId") Long parkingId,@Param("code") String code);

    /**
     * 修改车道（无租户）
     * @param parkingLaneInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingLaneNonTenant(ParkingLaneInfoEntity parkingLaneInfoEntity);

}
