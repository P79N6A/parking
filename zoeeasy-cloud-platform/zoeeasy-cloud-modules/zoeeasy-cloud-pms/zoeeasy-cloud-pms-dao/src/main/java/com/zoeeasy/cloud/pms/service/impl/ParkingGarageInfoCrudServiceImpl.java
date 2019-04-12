package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingGarageInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("parkingGarageInfoCrudService")
public class ParkingGarageInfoCrudServiceImpl extends ServiceImpl<ParkingGarageInfoMapper, ParkingGarageInfoEntity> implements ParkingGarageInfoCrudService {
    @Override
    public ParkingGarageInfoEntity findByName(String name) {
        ParkingGarageInfoEntity parkingGarageInfoEntity = new ParkingGarageInfoEntity();
        parkingGarageInfoEntity.setName(name);
        return baseMapper.selectOne(parkingGarageInfoEntity);
    }

    /**
     * 根据客户端编号查询
     *
     * @param localCode
     * @return
     */
    @Override
    public ParkingGarageInfoEntity findByCode(String localCode) {
        return baseMapper.findByCode(localCode);
    }

    /**
     * 无租户根据云平台code查询
     *
     * @param code
     * @return
     */
    @Override
    public ParkingGarageInfoEntity selectByCode(String code) {
        return baseMapper.selectByCode(code);
    }

    @Override
    public Integer findGarageLotTotalByParkingId(Long parkingId) {
        return baseMapper.selectGarageLotTotal(parkingId);
    }

    /**
     * 无租户查询停车场下所有车库车位总数
     *
     * @param parkingId
     * @return
     */
    @Override
    public Integer selectGarageLotTotalByParkingId(Long parkingId) {
        return baseMapper.selectGarageLotTotalByParkingId(parkingId);
    }

    @Override
    public Integer findGarageLotFixedTotalByParkingId(Long parkingId) {
        return baseMapper.selectGarageLotFixedTotal(parkingId);
    }

    /**
     * 无租户查询停车场下所有车库固定车位总数
     *
     * @param parkingId
     * @return
     */
    @Override
    public Integer selectGarageLotFixedTotalByParkingId(Long parkingId) {
        return baseMapper.selectGarageLotFixedTotalByParkingId(parkingId);
    }

    @Override
    public ParkingGarageInfoEntity parkingGarageByParkingIdAndCode(Long parkingId, String code) {
        ParkingGarageInfoEntity parkingGarageInfoEntity = new ParkingGarageInfoEntity();
        parkingGarageInfoEntity.setCode(code);
        parkingGarageInfoEntity.setParkingId(parkingId);
        return baseMapper.selectOne(parkingGarageInfoEntity);
    }

    /**
     * 无租户，关联车库的出入口查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    @Override
    public ParkingGarageInfoEntity findGarageByParkingIdAndCode(Long parkingId, String code) {
        return baseMapper.findGarageByParkingIdAndCode(parkingId,code);
    }

    @Override
    public ParkingGarageInfoEntity parkingGarageByParkingIdAndCodeAndGateId(Long parkingId, String code) {
        EntityWrapper<ParkingGarageInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.eq("code", code);
        entityWrapper.isNull("gateId");
        List<ParkingGarageInfoEntity> parkingGateInfoEntities = baseMapper.selectList(entityWrapper);
        baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(parkingGateInfoEntities)) {
            return parkingGateInfoEntities.get(0);
        }
        return null;
    }

    @Override
    public ParkingGarageInfoEntity findGarageByNameAndParkingId(String name, Long parkingId) {
        ParkingGarageInfoEntity parkingGarageInfoEntity = new ParkingGarageInfoEntity();
        parkingGarageInfoEntity.setName(name);
        parkingGarageInfoEntity.setParkingId(parkingId);
        return baseMapper.selectOne(parkingGarageInfoEntity);
    }

    /**
     * 无租户根据车库名称和停车场id查询
     *
     * @param name
     * @param parkingId
     * @return
     */
    @Override
    public ParkingGarageInfoEntity selectGarageByNameAndParkingId(String name, Long parkingId) {
        return baseMapper.selectGarageByNameAndParkingId(name,parkingId);
    }

    /**
     * 删除车库(无租户)
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteGarageByParkingIdNonTenant(Long parkingId) {
        return baseMapper.deleteGarageByParkingIdNonTenant(parkingId);
    }

    @Override
    public boolean updateGarageNonTenant(ParkingGarageInfoEntity parkingGarageInfoEntity) {
        return baseMapper.updateGarageNonTenant(parkingGarageInfoEntity);
    }
}
