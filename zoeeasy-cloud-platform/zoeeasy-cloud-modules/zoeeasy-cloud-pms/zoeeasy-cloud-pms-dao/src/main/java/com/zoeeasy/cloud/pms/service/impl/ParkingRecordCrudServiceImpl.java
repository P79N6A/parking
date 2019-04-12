package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingRecordEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingRecordMapper;
import com.zoeeasy.cloud.pms.service.ParkingRecordCrudService;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@Service("parkingRecordCrudService")
public class ParkingRecordCrudServiceImpl extends CrudServiceImpl<ParkingRecordMapper, ParkingRecordEntity> implements ParkingRecordCrudService {

    /**
     * 通过第三方平台停车记录ID查找
     */
    @Override
    public ParkingRecordEntity findByHikParkingRecordId(String thirdParkingRecordId) {
        ParkingRecordEntity parkingRecord = new ParkingRecordEntity();
        parkingRecord.setThirdParkingRecordId(thirdParkingRecordId);
        return baseMapper.selectOne(parkingRecord);
    }

    /**
     * 通过支付宝平台停车记录ID查找
     */
    @Override
    public ParkingRecordEntity findByAliParkingRecordId(String aliParkingRecordId) {
        ParkingRecordEntity parkingRecord = new ParkingRecordEntity();
        parkingRecord.setAliParkingRecordId(aliParkingRecordId);
        return baseMapper.selectOne(parkingRecord);
    }

    /**
     * 通过平台记录流水号查找
     */
    @Override
    public ParkingRecordEntity findByRecordNo(String recordNo) {
        return baseMapper.selectByRecordNo(recordNo);
    }

    /**
     * 根据停车记录号查找
     *
     * @param entityWrapper
     * @return
     */
    @Override
    public ParkingRecordEntity selectByIntoRecordNo(Wrapper<ParkingRecordEntity> entityWrapper) {
        return baseMapper.selectByIntoRecordNo(entityWrapper);
    }

    /**
     * 保存停车记录
     *
     * @param map
     * @return
     */
    @Override
    public Long save(ParkingRecordEntity map) {
        return baseMapper.save(map);
    }

    /**
     * 更新停车记录
     *
     * @param updateRecordEntity
     * @return
     */
    @Override
    public boolean updateParkingRecordByParkingIdAndId(ParkingRecordEntity updateRecordEntity) {
        return baseMapper.updateParkingRecordByParkingIdAndId(updateRecordEntity);
    }

    /**
     * 修改停车记录泊位
     *
     * @param updateRecordEntity
     * @return
     */
    @Override
    public boolean updateParkingLot(ParkingRecordEntity updateRecordEntity) {
        return baseMapper.updateParkingLot(updateRecordEntity);
    }
}
