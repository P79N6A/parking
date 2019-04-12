package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.core.enums.PassingVehicleDataSourceEnum;
import com.zoeeasy.cloud.pms.domain.PassingVehicleRecordEntity;
import com.zoeeasy.cloud.pms.mapper.PassingVehicleRecordMapper;
import com.zoeeasy.cloud.pms.service.PassingVehicleRecordCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@Service("passingVehicleRecordCrudService")
public class PassingVehicleRecordCrudServiceImpl extends CrudServiceImpl<PassingVehicleRecordMapper, PassingVehicleRecordEntity> implements PassingVehicleRecordCrudService {

    /**
     * 分页查询过车图像未同步的过车数据
     *
     * @param page page
     */
    @Override
    public List<PassingVehicleRecordEntity> selectHikParkingImageNotUploadedList(Page<PassingVehicleRecordEntity> page) {
        EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("dataSource", PassingVehicleDataSourceEnum.HIKVISION.getValue());
        entityWrapper.eq("photoUploaded", Boolean.FALSE);
        return baseMapper.selectPage(page, entityWrapper);
    }

    /**
     * 查找指定停车场的最后一次入车记录
     *
     * @param parkingId   停车场
     * @param plateNumber 车牌号
     * @param plateColor  车牌颜色
     * @param carType     车辆类型
     */
    @Override
    public PassingVehicleRecordEntity findLastEntryRecord(Long parkingId, String plateNumber, Integer plateColor, Integer carType) {
        EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.eq("plateNumber", plateNumber);
        entityWrapper.eq("plateColor", plateColor);
        entityWrapper.eq("carType", carType);
        entityWrapper.eq("passCarType", PassingTypeEnum.ENTRY.getValue());
        return baseMapper.findLastEntryRecord(entityWrapper);
    }

    /**
     * 通过海康过车id查找
     */
    @Override
    public PassingVehicleRecordEntity selectByHikPassingId(String thirdPassingId) {
        EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("thirdPassingId", thirdPassingId);
        List<PassingVehicleRecordEntity> passingVehicleRecordEntities = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(passingVehicleRecordEntities)) {
            return passingVehicleRecordEntities.get(0);
        } else {
            return null;
        }
    }

    /**
     * 通过平台过车流水号查找
     */
    @Override
    public PassingVehicleRecordEntity selectByPassingNo(String passingNo, Long parkingId) {
        EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("passingNo", passingNo);
        entityWrapper.eq("parkingId", parkingId);
        List<PassingVehicleRecordEntity> passingVehicleRecordEntities = baseMapper.selectByPassingNo(entityWrapper);
        if (CollectionUtils.isNotEmpty(passingVehicleRecordEntities)) {
            return passingVehicleRecordEntities.get(0);
        } else {
            return null;
        }
    }

    /**
     * 更新过车图片同步状态
     *
     * @param id            id
     * @param photoUploaded photoUploaded
     * @param uploadedDate  uploadedDate
     * @param photoCount    photoCount
     */
    @Override
    public Integer updatePassingImage(Long id, Boolean photoUploaded, Date uploadedDate, Integer photoCount) {
        EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", id);
        PassingVehicleRecordEntity passingVehicleRecord = new PassingVehicleRecordEntity();
        passingVehicleRecord.setPhotoCount(photoCount);
        passingVehicleRecord.setPhotoUploaded(photoUploaded);
        passingVehicleRecord.setUploadedDate(uploadedDate);
        return baseMapper.update(passingVehicleRecord, entityWrapper);
    }

    /**
     * 保存过车记录
     *
     * @param recordEntity
     * @return
     */
    @Override
    public boolean save(PassingVehicleRecordEntity recordEntity) {
        return baseMapper.save(recordEntity);
    }

    /**
     * 根据过车记录号更新过车记录
     *
     * @param passingVehicleRecordEntity
     * @return
     */
    @Override
    public boolean updateByPassNo(PassingVehicleRecordEntity passingVehicleRecordEntity) {
        return baseMapper.updateByPassNo(passingVehicleRecordEntity);
    }

    @Override
    public PassingVehicleRecordEntity selectPassRecord(EntityWrapper<PassingVehicleRecordEntity> entityWrapper) {
        return baseMapper.selectPassRecord(entityWrapper);
    }
}
