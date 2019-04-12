package com.zhuyitech.parking.data.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.common.enums.ParkingImageTypeEnum;
import com.zhuyitech.parking.data.domain.ParkingImage;
import com.zhuyitech.parking.data.mapper.ParkingImageMapper;
import com.zhuyitech.parking.data.service.ParkingImageCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Autor: AkeemSuper
 * @Date: 2018/3/1 0001
 */
@Service("parkingImageCrudService")
public class ParkingImageCrudServiceImpl extends CrudServiceImpl<ParkingImageMapper, ParkingImage> implements ParkingImageCrudService {

    /**
     * 查找停车场图片
     *
     * @param parkingId 停车场ID
     * @return
     */
    @Override
    public List<ParkingImage> findParkingInfoImage(Long parkingId) {
        EntityWrapper<ParkingImage> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.eq("bizType", ParkingImageTypeEnum.PARKINGINFO.getValue());
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 删除停车场图片
     *
     * @param parkingId 停车场ID
     * @return
     */
    @Override
    public void deleteParkingInfoImage(Long parkingId) {
        EntityWrapper<ParkingImage> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.eq("bizType", ParkingImageTypeEnum.PARKINGINFO.getValue());
        baseMapper.delete(entityWrapper);
    }

    /**
     * 查找过车图片
     *
     * @param parkingId 停车场ID
     * @param bizId     业务ID
     * @return
     */
    @Override
    public List<ParkingImage> findPassingImage(Long parkingId, Long bizId) {

        EntityWrapper<ParkingImage> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.eq("bizType", ParkingImageTypeEnum.PASSING.getValue());
        entityWrapper.eq("bizId", bizId);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 查找停车图片
     *
     * @param parkingId 停车场ID
     * @param bizId     业务ID
     * @return
     */
    @Override
    public List<ParkingImage> findParkingImage(Long parkingId, Long bizId) {
        EntityWrapper<ParkingImage> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.eq("bizType", ParkingImageTypeEnum.PARKING.getValue());
        entityWrapper.eq("bizId", bizId);
        return baseMapper.selectList(entityWrapper);
    }
}
