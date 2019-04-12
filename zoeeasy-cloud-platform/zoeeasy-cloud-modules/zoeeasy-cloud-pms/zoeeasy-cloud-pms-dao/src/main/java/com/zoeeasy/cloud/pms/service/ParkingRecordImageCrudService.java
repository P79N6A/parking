package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingRecordImageEntity;

import java.util.List;

/**
 * @Description: 过车停车图像表的CrudService
 * @Autor: AkeemSuper
 * @Date: 2018/3/1 0001
 */
public interface ParkingRecordImageCrudService extends CrudService<ParkingRecordImageEntity> {

    /**
     * 保存停车图片
     *
     * @param parkingRecordImageEntity
     * @return
     */
    Boolean save(ParkingRecordImageEntity parkingRecordImageEntity);

    /**
     * 保存图片
     *
     * @param parkingRecordImageEntity
     * @return
     */
    boolean addParkingRecordImage(ParkingRecordImageEntity parkingRecordImageEntity);

    /**
     * 查找过车图片
     *
     * @param parkingId 停车场ID
     * @param bizNo     业务ID
     * @return
     */
    List<ParkingRecordImageEntity> findPassingImage(Long parkingId, String bizNo);

    /**
     * 查找停车图片
     *
     * @param parkingId 停车场ID
     * @param bizId     业务ID
     * @return
     */
    List<ParkingRecordImageEntity> findParkingImage(Long parkingId, Long bizId);

    /**
     * 根据bizNo获取图片
     *
     * @param bizNo
     * @return
     */
    ParkingRecordImageEntity findParkingImageByBizNo(String bizNo);

}
