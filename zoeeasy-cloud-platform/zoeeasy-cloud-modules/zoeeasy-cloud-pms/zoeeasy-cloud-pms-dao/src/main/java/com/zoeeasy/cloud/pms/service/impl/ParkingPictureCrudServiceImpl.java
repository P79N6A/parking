package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingPictureEntity;
import com.zoeeasy.cloud.pms.enums.PictureTypeEnum;
import com.zoeeasy.cloud.pms.mapper.ParkingPictureMapper;
import com.zoeeasy.cloud.pms.park.cst.ColumnConstant;
import com.zoeeasy.cloud.pms.service.ParkingPictureCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by song on 2018/9/18.
 */
@Service("parkingPictureCrudService")
public class ParkingPictureCrudServiceImpl extends ServiceImpl<ParkingPictureMapper, ParkingPictureEntity> implements ParkingPictureCrudService {
    @Override
    public void deleteParkingPicture(Long parkingId) {
        EntityWrapper<ParkingPictureEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        entityWrapper.eq("pictureType", PictureTypeEnum.DEFAULT.getValue());
        baseMapper.delete(entityWrapper);
    }

    @Override
    public List<ParkingPictureEntity> findParkingPicture(Long parkingId) {
        EntityWrapper<ParkingPictureEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        entityWrapper.eq("pictureType", PictureTypeEnum.DEFAULT.getValue());
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 查找停车图片
     *
     * @param parkingId 停车场ID
     * @return
     */
    @Override
    public List<ParkingPictureEntity> findParkingPictureList(Long parkingId) {
        EntityWrapper<ParkingPictureEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        entityWrapper.eq("pictureType", PictureTypeEnum.DEFAULT.getValue());
        return baseMapper.selectParkingPictureList(entityWrapper);
    }

    /**
     * 添加ParkingPictureEntity(无租户)
     *
     * @param parkingPictureEntity
     * @return
     */
    @Override
    public boolean insertParkingPictureNonTenant(ParkingPictureEntity parkingPictureEntity) {
        return baseMapper.insertParkingPictureNonTenant(parkingPictureEntity);
    }

    @Override
    public boolean deleteParkingPictureNonTenant(Long parkingId) {
        return baseMapper.deleteParkingPictureNonTenant(parkingId);
    }
}
