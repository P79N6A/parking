package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingLaneInfoEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingLaneInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingLaneInfoCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by song on 2018/9/14.
 */
@Service("parkingLaneInfoCrudService")
public class ParkingLaneInfoCrudServiceImpl extends ServiceImpl<ParkingLaneInfoMapper, ParkingLaneInfoEntity> implements ParkingLaneInfoCrudService {

    @Override
    public ParkingLaneInfoEntity findByName(String name) {
        ParkingLaneInfoEntity entity = new ParkingLaneInfoEntity();
        entity.setName(name);
        return baseMapper.selectOne(entity);
    }

    @Override
    public ParkingLaneInfoEntity findByCode(String code) {
        ParkingLaneInfoEntity entity = new ParkingLaneInfoEntity();
        entity.setCode(code);
        return baseMapper.selectOne(entity);
    }

    /**
     * 无租户，根据云平台code查询
     *
     * @param code
     * @return
     */
    @Override
    public ParkingLaneInfoEntity selectByCode(String code) {
        return baseMapper.selectByCode(code);
    }

    /**
     * 无租户，根据客户端code查询
     *
     * @param localCode
     * @return
     */
    @Override
    public ParkingLaneInfoEntity selectByLocalCode(String localCode) {
        return baseMapper.selectByLocalCode(localCode);
    }

    @Override
    public Integer findCountByGarageId(Long garageId) {
        EntityWrapper<ParkingLaneInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("garageId", garageId);
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public Integer findByGateId(Long gateId) {
        EntityWrapper<ParkingLaneInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("gateId", gateId);
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public ParkingLaneInfoEntity findByParkingIdAndCode(Long parkingId, String code) {
        ParkingLaneInfoEntity entity = new ParkingLaneInfoEntity();
        entity.setParkingId(parkingId);
        entity.setCode(code);
        return baseMapper.selectOne(entity);
    }

    /**
     * 无租户，根据停车场Id和车道code查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    @Override
    public ParkingLaneInfoEntity selectByParkingIdAndCode(Long parkingId, String code) {
        return baseMapper.selectByParkingIdAndCode(parkingId,code);
    }

    @Override
    public ParkingLaneInfoEntity findByParkingIdAndName(Long parkingId, String name) {
        EntityWrapper<ParkingLaneInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.eq("name", name);
        entityWrapper.isNull("gateId");
        entityWrapper.isNull("garageId");
        List<ParkingLaneInfoEntity> parkingLaneInfoEntities = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(parkingLaneInfoEntities)) {
            return parkingLaneInfoEntities.get(0);
        }
        return null;
    }

    /**
     * 无租户，根据停车场Id，车道名称查询
     *
     * @param parkingId
     * @param name
     * @return
     */
    @Override
    public ParkingLaneInfoEntity selectByParkingIdAndName(Long parkingId, String name) {
        return baseMapper.selectByParkingIdAndName(parkingId,name);
    }

    @Override
    public ParkingLaneInfoEntity findByGarageIdAndName(Long garageId, String name) {
        EntityWrapper<ParkingLaneInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("garageId", garageId);
        entityWrapper.eq("name", name);
        entityWrapper.isNull("gateId");
        List<ParkingLaneInfoEntity> parkingLaneInfoEntities = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(parkingLaneInfoEntities)) {
            return parkingLaneInfoEntities.get(0);
        }
        return null;
    }

    /**
     * 无租户，根据车库Id和车道名称查询
     *
     * @param garageId
     * @param name
     * @return
     */
    @Override
    public ParkingLaneInfoEntity selectByGarageIdAndName(Long garageId, String name) {
        return baseMapper.selectByGarageIdAndName(garageId,name);
    }

    @Override
    public ParkingLaneInfoEntity findByGateIdAndName(Long gateId, String name) {
        ParkingLaneInfoEntity entity = new ParkingLaneInfoEntity();
        entity.setGateId(gateId);
        entity.setName(name);
        return baseMapper.selectOne(entity);
    }

    /**
     * 无租户，根据出入口Id和车道名称查询
     *
     * @param gateId
     * @param name
     * @return
     */
    @Override
    public ParkingLaneInfoEntity selectGateIdAndName(Long gateId, String name) {
        return baseMapper.selectGateIdAndName(gateId,name);
    }

    @Override
    public ParkingLaneInfoEntity findByNameAndGateId(String name, Long gateId) {
        ParkingLaneInfoEntity parkingLaneInfoEntity = new ParkingLaneInfoEntity();
        parkingLaneInfoEntity.setName(name);
        parkingLaneInfoEntity.setGateId(gateId);
        return baseMapper.selectOne(parkingLaneInfoEntity);
    }

    @Override
    public ParkingLaneInfoEntity findByNameAndParkingId(String name, Long parkingId) {
        ParkingLaneInfoEntity parkingLaneInfoEntity = new ParkingLaneInfoEntity();
        parkingLaneInfoEntity.setName(name);
        parkingLaneInfoEntity.setParkingId(parkingId);
        return baseMapper.selectOne(parkingLaneInfoEntity);
    }

    /**
     * 删除ParkingLaneInfo（无租户）
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteParkingLaneInfoNonTenant(Long parkingId) {
        return baseMapper.deleteParkingLaneInfoNonTenant(parkingId);
    }

    @Override
    public boolean updateParkingLaneNonTenant(ParkingLaneInfoEntity parkingLaneInfoEntity) {
        return baseMapper.updateParkingLaneNonTenant(parkingLaneInfoEntity);
    }
}
