package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by song on 2018/9/11.
 */
@Repository("parkingLotInfoMapper")
public interface ParkingLotInfoMapper extends BaseMapper<ParkingLotInfoEntity> {

    /**
     * 获取泊位信息
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    ParkingLotInfoEntity findForPassVehicle(@Param("ew") Wrapper<ParkingLotInfoEntity> wrapper);

    /**
     * 根据停车场id和泊位id获取泊位信息
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    ParkingLotInfoEntity findByParkingId(@Param("ew") Wrapper<ParkingLotInfoEntity> wrapper);

    /**
     * 根据泊位信号查找泊位
     *
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    ParkingLotInfoEntity findByCode(@Param("code") String code);

    /**
     * 根据停车场Id和泊位编号分页查询
     *
     * @param parkingId
     * @param number
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingLotInfoEntity> findByParkingIdAndLotNumber(Pagination page, @Param("parkingId") Long parkingId, @Param("number") String number);

    /**
     * 根据停车场Id获取泊位code
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingLotInfoEntity> findCodeByParkingId(@Param("parkingId") Long parkingId);

    /**
     * 删除ParkingLotInfo(无租户)
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingLotInfoNonTenant(@Param("parkingId") Long parkingId);

    /**
     * 修改ParkingLotInfo(无租户)
     * @param parkingLotInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingLotInfoNonTenant(ParkingLotInfoEntity parkingLotInfoEntity);
}
