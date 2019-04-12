package com.zoeeasy.cloud.pms.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingRecordImageEntity;
import com.zoeeasy.cloud.pms.enums.ParkingImageTypeEnum;
import com.zoeeasy.cloud.pms.mapper.ParkingRecordImageMapper;
import com.zoeeasy.cloud.pms.park.cst.ColumnConstant;
import com.zoeeasy.cloud.pms.service.ParkingRecordImageCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Autor: AkeemSuper
 * @Date: 2018/3/1 0001
 */
@Service("parkingRecordImageCrudService")
public class ParkingRecordImageCrudServiceImpl extends CrudServiceImpl<ParkingRecordImageMapper, ParkingRecordImageEntity> implements ParkingRecordImageCrudService {

    /**
     * 保存停车图片
     *
     * @param parkingRecordImageEntity
     * @return
     */
    @Override
    public Boolean save(ParkingRecordImageEntity parkingRecordImageEntity) {
        return baseMapper.save(parkingRecordImageEntity);
    }

    /**
     * 保存图片
     *
     * @param parkingRecordImageEntity
     * @return
     */
    @Override
    public boolean addParkingRecordImage(ParkingRecordImageEntity parkingRecordImageEntity) {
        return baseMapper.addParkingRecordImage(parkingRecordImageEntity);
    }

    /**
     * 查找过车图片
     *
     * @param parkingId 停车场ID
     * @param bizNo     业务ID
     * @return
     */
    @Override
    public List<ParkingRecordImageEntity> findPassingImage(Long parkingId, String bizNo) {

        EntityWrapper<ParkingRecordImageEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        entityWrapper.eq(ColumnConstant.BIZ_TYPE, ParkingImageTypeEnum.PASSING.getValue());
        entityWrapper.eq(ColumnConstant.BIZ_NO, bizNo);
        return baseMapper.selectParkingImageList(entityWrapper);
    }

    /**
     * 查找停车图片
     *
     * @param parkingId 停车场ID
     * @param bizId     业务ID
     * @return
     */
    @Override
    public List<ParkingRecordImageEntity> findParkingImage(Long parkingId, Long bizId) {
        EntityWrapper<ParkingRecordImageEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        entityWrapper.eq(ColumnConstant.BIZ_TYPE, ParkingImageTypeEnum.PARKING.getValue());
        entityWrapper.eq(ColumnConstant.BIZ_ID, bizId);
        return baseMapper.selectParkingImageList(entityWrapper);
    }

    /**
     * 根据bizNo获取图片
     *
     * @param bizNo
     * @return
     */
    @Override
    public ParkingRecordImageEntity findParkingImageByBizNo(String bizNo) {
        return baseMapper.findParkingImageByBizNo(bizNo);
    }

}
