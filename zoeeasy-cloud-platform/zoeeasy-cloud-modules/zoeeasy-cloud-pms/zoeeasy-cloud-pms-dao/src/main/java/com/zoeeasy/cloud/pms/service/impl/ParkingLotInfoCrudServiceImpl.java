package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingLotInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 * @date 2017-12-11
 */
@Service("parkingLotInfoCrudService")
public class ParkingLotInfoCrudServiceImpl extends ServiceImpl<ParkingLotInfoMapper, ParkingLotInfoEntity> implements ParkingLotInfoCrudService {

    /**
     * 更新车位状态
     *
     * @param parkingLotId
     * @param status
     * @return
     */
    @Override
    public Integer updateParkingLotStatus(Long parkingLotId, Integer status) {
        ParkingLotInfoEntity parkingLotInfo = new ParkingLotInfoEntity();
        parkingLotInfo.setStatus(status);
        EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", parkingLotId);
        return baseMapper.update(parkingLotInfo, entityWrapper);
    }

    /**
     * 通过停车场ID，编号查询
     */
    @Override
    public ParkingLotInfoEntity findByParkingIdAndCode(Long parkingId, String code) {

        ParkingLotInfoEntity parkingLotInfo = new ParkingLotInfoEntity();
        parkingLotInfo.setParkingId(parkingId);
        parkingLotInfo.setCode(code);
        return baseMapper.selectOne(parkingLotInfo);
    }

    @Override
    public ParkingLotInfoEntity findByIdAndCode(Long id, Long parkingId, String code) {
        ParkingLotInfoEntity parkingLotInfo = new ParkingLotInfoEntity();
        parkingLotInfo.setId(id);
        parkingLotInfo.setParkingId(parkingId);
        parkingLotInfo.setCode(code);
        return baseMapper.selectOne(parkingLotInfo);
    }

    /**
     * 通过海康平台ID查询
     */
    @Override
    public ParkingLotInfoEntity findByHikParkingLotId(String hikParkingLotId) {
        ParkingLotInfoEntity parkingLotInfo = new ParkingLotInfoEntity();
        parkingLotInfo.setHikParkingLotId(hikParkingLotId);
        return baseMapper.selectOne(parkingLotInfo);
    }

    /**
     * 通过平台停车场ID和海康平台车位编号查询
     *
     * @param parkingId      parkingId
     * @param hikBerthNumber hikBerthNumber
     * @return ParkingLotInfo
     */
    @Override
    public ParkingLotInfoEntity findByParkingIdAndHikBerthNumber(Long parkingId, String hikBerthNumber) {
        ParkingLotInfoEntity parkingLotInfo = new ParkingLotInfoEntity();
        parkingLotInfo.setParkingId(parkingId);
        parkingLotInfo.setHikBerthNumber(hikBerthNumber);
        return baseMapper.selectOne(parkingLotInfo);
    }

    /**
     * 通过支付宝平台查询
     */
    @Override
    public ParkingLotInfoEntity findByAliParkingLotId(String aliParkingLotId) {
        ParkingLotInfoEntity parkingLotInfo = new ParkingLotInfoEntity();
        parkingLotInfo.setAliParkingLotId(aliParkingLotId);
        return baseMapper.selectOne(parkingLotInfo);
    }

    /**
     * 根据停车场id和泊位id获取泊位
     *
     * @param parkingId
     * @param parkingLotId
     * @return
     */
    @Override
    public ParkingLotInfoEntity findByParkingId(Long parkingId, Long parkingLotId, Long tenantId) {
        Wrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.eq("id", parkingLotId);
        entityWrapper.eq("tenantId", tenantId);
        return baseMapper.findByParkingId(entityWrapper);
    }

    /**
     * 根据泊位编号查找
     *
     * @param code
     * @return
     */
    @Override
    public ParkingLotInfoEntity findByNumber(String code) {
        ParkingLotInfoEntity parkingLotInfoEntity = new ParkingLotInfoEntity();
        parkingLotInfoEntity.setCode(code);
        return baseMapper.selectOne(parkingLotInfoEntity);
    }

    /**
     * 过车记录获取泊位信息
     *
     * @param wrapper
     * @return
     */
    @Override
    @SqlParser(filter = true)
    public ParkingLotInfoEntity findForPassVehicle(Wrapper<ParkingLotInfoEntity> wrapper) {
        return baseMapper.findForPassVehicle(wrapper);
    }

    /**
     * 根据code查找
     *
     * @param code
     * @return
     */
    @Override
    public ParkingLotInfoEntity findByCode(String code) {
        return baseMapper.findByCode(code);
    }

    /**
     * 根据garageId查找
     *
     * @param garageId
     * @return
     */
    @Override
    public List<ParkingLotInfoEntity> findByGarageId(Long garageId) {
        EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("garageId", garageId);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 根据parkingAreaId获取
     *
     * @param parkingAreaId
     * @return
     */
    @Override
    public List<ParkingLotInfoEntity> findByParkingAreaId(Long parkingAreaId) {
        EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingAreaId", parkingAreaId);
        return baseMapper.selectList(entityWrapper);
    }

    @Override
    public Integer findCountByGarageId(Long garageId) {
        EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("garageId", garageId);
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public Integer findCountByParkingAreaId(Long parkingAreaId) {
        EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingAreaId", parkingAreaId);
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public ParkingLotInfoEntity findByNumberAndParkingAreaId(String number, Long parkingAreaId) {
        ParkingLotInfoEntity parkingLotInfoEntity = new ParkingLotInfoEntity();
        parkingLotInfoEntity.setNumber(number);
        parkingLotInfoEntity.setParkingAreaId(parkingAreaId);
        return baseMapper.selectOne(parkingLotInfoEntity);
    }

    @Override
    public ParkingLotInfoEntity findByNumberAndGarageId(String number, Long garageId) {
        ParkingLotInfoEntity parkingLotInfoEntity = new ParkingLotInfoEntity();
        parkingLotInfoEntity.setNumber(number);
        parkingLotInfoEntity.setGarageId(garageId);
        return baseMapper.selectOne(parkingLotInfoEntity);
    }

    @Override
    public ParkingLotInfoEntity findByNumberAndParkingId(String number, Long parkingId) {
        EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("number", number);
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.isNull("garageId");
        List<ParkingLotInfoEntity> list = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据停车场Id和泊位编号分页查询
     *
     * @param parkingId
     * @param number
     * @return
     */
    @Override
    public List<ParkingLotInfoEntity> findByParkingIdAndLotNumber(Pagination page, Long parkingId, String number) {
        return baseMapper.findByParkingIdAndLotNumber(page, parkingId, number);
    }

    /**
     * 根据停车场Id获取泊位code
     *
     * @param parkingId
     * @return
     */
    @Override
    public List<ParkingLotInfoEntity> findCodeByParkingId(Long parkingId) {
        return baseMapper.findCodeByParkingId(parkingId);
    }

    @Override
    public boolean deleteParkingLotInfoNonTenant(Long parkingId) {
        return baseMapper.deleteParkingLotInfoNonTenant(parkingId);
    }

    @Override
    public ParkingLotInfoEntity findByLocalCode(String localCode) {
        EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("localCode", localCode);
        return baseMapper.findByParkingId(entityWrapper);
    }

    @Override
    public boolean updateParkingLotInfoNonTenant(ParkingLotInfoEntity parkingLotInfoEntity) {
        return baseMapper.updateParkingLotInfoNonTenant(parkingLotInfoEntity);
    }
}
