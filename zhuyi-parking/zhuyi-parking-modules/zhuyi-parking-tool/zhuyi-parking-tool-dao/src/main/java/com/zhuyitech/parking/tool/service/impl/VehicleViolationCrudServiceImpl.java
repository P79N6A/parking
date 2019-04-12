package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.tool.domain.VehicleViolation;
import com.zhuyitech.parking.tool.mapper.VehicleViolationMapper;
import com.zhuyitech.parking.tool.service.VehicleViolationCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuzhicheng
 * @date 2018/4/14
 */
@Service("vehicleViolationCrudService")
public class VehicleViolationCrudServiceImpl extends ServiceImpl<VehicleViolationMapper, VehicleViolation> implements VehicleViolationCrudService {

    @Override
    public VehicleViolation findByCarIdAndCode(Long carId, String code) {
        VehicleViolation vehicleViolation = new VehicleViolation();
        vehicleViolation.setCarId(carId);
        vehicleViolation.setCode(code);
        return baseMapper.selectOne(vehicleViolation);
    }

    @Override
    public List<VehicleViolation> findByCarId(Long carId) {
        EntityWrapper<VehicleViolation> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("carId", carId);
        return baseMapper.selectList(entityWrapper);
    }

}
