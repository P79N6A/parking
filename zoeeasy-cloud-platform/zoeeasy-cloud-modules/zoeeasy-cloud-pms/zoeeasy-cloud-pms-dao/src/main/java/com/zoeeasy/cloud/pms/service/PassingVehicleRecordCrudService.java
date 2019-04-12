package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.PassingVehicleRecordEntity;

import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
public interface PassingVehicleRecordCrudService extends CrudService<PassingVehicleRecordEntity> {

    /**
     * 分页查询过车图像未同步的过车数据
     *
     * @param page page
     */
    List<PassingVehicleRecordEntity> selectHikParkingImageNotUploadedList(Page<PassingVehicleRecordEntity> page);

    /**
     * 查找指定停车场的最后一次入车记录
     *
     * @param parkingId   停车场
     * @param plateNumber 车牌号
     * @param plateColor  车牌颜色
     * @param carType     车辆类型
     */
    PassingVehicleRecordEntity findLastEntryRecord(Long parkingId, String plateNumber, Integer plateColor, Integer carType);

    /**
     * 通过海康过车id查找
     *
     * @param thirdPassingId thirdPassingId
     */
    PassingVehicleRecordEntity selectByHikPassingId(String thirdPassingId);

    /**
     * 通过平台过车流水号查找
     *
     * @param passingNo passingNo
     */
    PassingVehicleRecordEntity selectByPassingNo(String passingNo, Long parkingId);

    /**
     * 更新过车图片同步状态
     *
     * @param id            id
     * @param photoUploaded photoUploaded
     * @param uploadedDate  uploadedDate
     * @param photoCount    photoCount
     */
    Integer updatePassingImage(Long id, Boolean photoUploaded, Date uploadedDate, Integer photoCount);

    /**
     * 保存过车记录
     *
     * @param recordEntity
     * @return
     */
    boolean save(PassingVehicleRecordEntity recordEntity);

    /**
     * 根据过车记录号更新过车记录
     *
     * @param passingVehicleRecordEntity
     * @return
     */
    boolean updateByPassNo(PassingVehicleRecordEntity passingVehicleRecordEntity);

    /*
    查找过车记录
     */
    PassingVehicleRecordEntity selectPassRecord(EntityWrapper<PassingVehicleRecordEntity> entityWrapper);
}
