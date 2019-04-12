package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zoeeasy.cloud.pms.domain.ParkingDetailInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:停车场详细信息表Mapper
 * @Autor: Kane
 * @Date: 2018/9/17
 */
@Repository("parkingDetailInfoMapper")
public interface ParkingDetailInfoMapper extends BaseMapper<ParkingDetailInfoEntity> {

    /**
     * 获取停车场详细信息
     *
     * @return
     */
    @SqlParser(filter = true)
    ParkingDetailInfoEntity findByParkingId(@Param("parkingId") Long parkingId);

    /**
     * 添加ParkingDetailInfo(无租户)
     *
     * @param parkingDetailInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean insertParkingDetailInfoNonTenant(ParkingDetailInfoEntity parkingDetailInfoEntity);

    /**
     * 修改ParkingDetailInfo(无租户)
     *
     * @param parkingDetailInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingDetailInfoNonTenant(ParkingDetailInfoEntity parkingDetailInfoEntity);

    /**
     * 删除ParkingDetailInfo(无租户)
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingDetailInfoNonTenant(@Param("parkingId") Long parkingId);

    @SqlParser(filter = true)
    List<ParkingDetailInfoEntity> findParkingDetailNonTenant(@Param("ew") Wrapper<ParkingDetailInfoEntity> ew);

    @SqlParser(filter = true)
    ParkingDetailInfoEntity selectParkingDetailNonTenant(@Param("ew") Wrapper<ParkingDetailInfoEntity> ew);

    @SqlParser(filter = true)
    String selectParkingAddress(@Param("parkingId") Long parkingId);

    /**
     * 分页获取停车场详情
     *
     * @param wrapper 查询条件
     * @return 分页停车场详情
     */
    @SqlParser(filter = true)
    List<ParkingDetailInfoEntity> selectListPage(Pagination pagination, @Param("ew") Wrapper<ParkingDetailInfoEntity> wrapper);

}
