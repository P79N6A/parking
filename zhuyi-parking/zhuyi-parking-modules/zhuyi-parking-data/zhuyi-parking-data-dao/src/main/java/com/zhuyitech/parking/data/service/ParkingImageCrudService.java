package com.zhuyitech.parking.data.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.data.domain.ParkingImage;

import java.util.List;

/**
 * @Description: 过车停车图像表的CrudService
 * @Autor: AkeemSuper
 * @Date: 2018/3/1 0001
 */
public interface ParkingImageCrudService extends CrudService<ParkingImage> {

    /**
     * 查找停车场图片
     *
     * @param parkingId 停车场ID
     * @return
     */
    List<ParkingImage> findParkingInfoImage(Long parkingId);

    /**
     * 删除停车场图片
     *
     * @param parkingId 停车场ID
     * @return
     */
    void deleteParkingInfoImage(Long parkingId);

    /**
     * 查找过车图片
     *
     * @param parkingId 停车场ID
     * @param bizId     业务ID
     * @return
     */
    List<ParkingImage> findPassingImage(Long parkingId, Long bizId);

    /**
     * 查找停车图片
     *
     * @param parkingId 停车场ID
     * @param bizId     业务ID
     * @return
     */
    List<ParkingImage> findParkingImage(Long parkingId, Long bizId);

}
