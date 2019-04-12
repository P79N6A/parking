package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.ParkingPictureEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by song on 2018/9/18.
 */
@Repository("parkingPictureMapper")
public interface ParkingPictureMapper extends BaseMapper<ParkingPictureEntity> {

    /**
     * 获取停车图像列表
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingPictureEntity> selectParkingPictureList(@Param("ew") Wrapper<ParkingPictureEntity> wrapper);

    /**
     * 添加ParkingPictureEntity(无租户)
     *
     * @param parkingPictureEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean insertParkingPictureNonTenant(ParkingPictureEntity parkingPictureEntity);

    /**
     * 删除停车场图片(无租户)
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingPictureNonTenant(@Param("parkingId") Long parkingId);
}
