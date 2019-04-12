package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingPictureEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by song on 2018/9/18.
 */
public interface ParkingPictureCrudService extends CrudService<ParkingPictureEntity> {

    void deleteParkingPicture(Long parkingId);

    List<ParkingPictureEntity> findParkingPicture(Long parkingId);

    /**
     * 查找停车图片
     *
     * @param parkingId 停车场ID
     * @return
     */
    List<ParkingPictureEntity> findParkingPictureList(Long parkingId);

    /**
     * 添加ParkingPictureEntity(无租户)
     *
     * @param parkingPictureEntity
     * @return
     */
    boolean insertParkingPictureNonTenant(ParkingPictureEntity parkingPictureEntity);

    /**
     * 删除停车场图片(无租户)
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingPictureNonTenant(@Param("parkingId") Long parkingId);

}
