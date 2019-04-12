package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingGateInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
import org.springframework.stereotype.Service;

@Service("parkingGateInfoCrudService")
public class ParkingGateInfoCrudServiceImpl extends ServiceImpl<ParkingGateInfoMapper, ParkingGateInfoEntity> implements ParkingGateInfoCrudService {
    @Override
    public ParkingGateInfoEntity findByName(String name) {
        ParkingGateInfoEntity entity = new ParkingGateInfoEntity();
        entity.setName(name);
        return baseMapper.selectOne(entity);
    }

    @Override
    public ParkingGateInfoEntity findByCode(String code) {
        ParkingGateInfoEntity entity = new ParkingGateInfoEntity();
        entity.setCode(code);
        return baseMapper.selectOne(entity);
    }

    /**
     * 无租户根据客户端编号查询
     *
     * @param localCode
     * @return
     */
    @Override
    public ParkingGateInfoEntity selectByCode(String localCode) {
        return baseMapper.selectByCode(localCode);
    }

    /**
     * 无租户根据云平台code查询出入口
     *
     * @param code
     * @return
     */
    @Override
    public ParkingGateInfoEntity selectGateByCode(String code) {
        return baseMapper.selectGateByCode(code);
    }

    @Override
    public ParkingGateInfoEntity findByParkingIdeAndCode(Long parkingId, String code) {
        ParkingGateInfoEntity entity = new ParkingGateInfoEntity();
        entity.setParkingId(parkingId);
        entity.setCode(code);
        return baseMapper.selectOne(entity);
    }

    /**
     * 无租户，根据停车场Id和编号查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    @Override
    public ParkingGateInfoEntity selectByParkingIdeAndCode(Long parkingId, String code) {
        return baseMapper.selectByParkingIdeAndCode(parkingId,code);
    }

    /**
     * 无租户，未关联车库，出入口名称停车场内唯一
     *
     * @param parkingId
     * @param name
     * @return
     */
    @Override
    public ParkingGateInfoEntity findByParkingIdeAndName(Long parkingId, String name) {
        return baseMapper.findByParkingIdeAndName(parkingId,name);
    }

    /**
     * 无租户根据Id和名称查询车库内名称唯一
     *
     * @param garageId
     * @param name
     * @return
     */
    @Override
    public ParkingGateInfoEntity findByGarageIdeAndName(Long garageId, String name) {
        return baseMapper.findByGarageIdeAndName(garageId,name);
    }

    /**
     * 无租户，根据车库Id和出入口编号查询
     *
     * @param garageId
     * @param code
     * @return
     */
    @Override
    public ParkingGateInfoEntity findByGarageIdAndCode(Long garageId, String code) {
        return baseMapper.findByGarageIdAndCode(garageId,code);
    }

    @Override
    public Integer findCountByGarageId(Long garageId) {
        EntityWrapper<ParkingGateInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("garageId", garageId);
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public ParkingGateInfoEntity findByNameAndGarageId(String name, Long id) {
        ParkingGateInfoEntity parkingGateInfoEntity = new ParkingGateInfoEntity();
        parkingGateInfoEntity.setName(name);
        parkingGateInfoEntity.setGarageId(id);
        return baseMapper.selectOne(parkingGateInfoEntity);
    }

    @Override
    public ParkingGateInfoEntity findByNameAndParkingId(String name, Long id) {
        ParkingGateInfoEntity parkingGateInfoEntity = new ParkingGateInfoEntity();
        parkingGateInfoEntity.setName(name);
        parkingGateInfoEntity.setParkingId(id);
        return baseMapper.selectOne(parkingGateInfoEntity);
    }

    /**
     * 删除ParkingGateInfo(无租户)
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteParkingGateInfoNonTenant(Long parkingId) {
        return baseMapper.deleteParkingGateInfoNonTenant(parkingId);
    }

    @Override
    public boolean updateParkingGateNonTenant(ParkingGateInfoEntity parkingGateInfoEntity) {
        return baseMapper.updateParkingGateNonTenant(parkingGateInfoEntity);
    }
}
