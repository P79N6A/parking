package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:停车场车库信息表Mapper
 * @Autor: Kane
 * @Date: 2018/9/17
 */
@Repository("parkingGarageInfoMapper")
public interface ParkingGarageInfoMapper extends BaseMapper<ParkingGarageInfoEntity> {

    Integer selectGarageLotTotal(@Param("parkingId") Long parkingId);

    Integer selectGarageLotFixedTotal(@Param("parkingId") Long parkingId);

    /**
     * 无租户查询停车场下所有车库固定车位总数
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    Integer selectGarageLotFixedTotalByParkingId (@Param("parkingId") Long parkingId);

    /**
     * 无租户查询停车场下所有车库车位总数
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    Integer selectGarageLotTotalByParkingId(@Param("parkingId") Long parkingId);

    /**
     * 删除车库(无租户)
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteGarageByParkingIdNonTenant(@Param("parkingId") Long parkingId);

    /**
     * 根据客户端编号查询
     *
     * @param localCode
     * @return
     */
    @SqlParser(filter = true)
    ParkingGarageInfoEntity findByCode (@Param("localCode") String localCode);

    /**
     * 根据云平台code查询
     *
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    ParkingGarageInfoEntity selectByCode (@Param("code") String code);

    /**
     * 根据停车场Id和停车场名称查询
     *
     * @param name
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    ParkingGarageInfoEntity selectGarageByNameAndParkingId(@Param("name") String name,@Param("parkingId") Long parkingId);

    /**
     * 无租户，车库查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    ParkingGarageInfoEntity findGarageByParkingIdAndCode(@Param("parkingId") Long parkingId,@Param("code") String code);

    @SqlParser(filter = true)
    boolean updateGarageNonTenant(ParkingGarageInfoEntity parkingGarageInfoEntity);

}
