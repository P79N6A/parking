package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.ParkingRecordImageEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 过车停车图像表mapper
 * @Date: 2018/1/11 0011
 * @author: AkeemSuper
 */
@Repository("parkingRecordImageMapper")
public interface ParkingRecordImageMapper extends BaseMapper<ParkingRecordImageEntity> {

    /**
     * 保存停车图片
     *
     * @param parkingRecordImageEntity
     * @return
     */
    @SqlParser(filter = true)
    Boolean save(ParkingRecordImageEntity parkingRecordImageEntity);

    /**
     * 保存图片
     *
     * @param parkingRecordImageEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean addParkingRecordImage(ParkingRecordImageEntity parkingRecordImageEntity);

    /**
     * 获取停车图像列表
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingRecordImageEntity> selectParkingImageList(@Param("ew") Wrapper<ParkingRecordImageEntity> wrapper);

    /**
     * 根据bizNo获取图片
     *
     * @param bizNo
     * @return
     */
    @SqlParser(filter = true)
    ParkingRecordImageEntity findParkingImageByBizNo(@Param("bizNo") String bizNo);
}
