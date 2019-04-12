package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserCarInfo;
import com.zhuyitech.parking.ucc.mapper.UserCarInfoMapper;
import com.zhuyitech.parking.ucc.service.UserCarInfoCrudService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 判断车辆是否被绑定用户
 *
 * @Date: 2018/1/4 0004
 * @author: AkeemSuper
 */
@Service("userCarInfoCrudService")
public class UserCarInfoCrudServiceImpl extends CrudServiceImpl<UserCarInfoMapper, UserCarInfo> implements UserCarInfoCrudService {

    /**
     * 通过用户ID查找
     */
    @Override
    public List<UserCarInfo> findByUserId(Long userId) {
        EntityWrapper<UserCarInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", userId);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 通过车牌号查找车辆
     */
    @Override
    public List<UserCarInfo> findByPlateNumber(String plateNumber) {
        EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("concat(platePrefix,plateInitial,plateNumber)", plateNumber);
        entityWrapper.orderBy("defaultCar", false);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 根据车牌号和用户ID获取车辆
     */
    @Override
    public UserCarInfo findByPlateNumberAndUserId(String plateNumber, Long userId) {
        EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("concat(platePrefix,plateInitial,plateNumber)", plateNumber);
        entityWrapper.eq("userId", userId);
        List<UserCarInfo> userCarInfoList = baseMapper.selectList(entityWrapper);
        if (userCarInfoList != null && !userCarInfoList.isEmpty()) {
            return userCarInfoList.get(0);
        }
        return null;
    }

    /**
     * 通过用户ID和状态查找数量
     */
    @Override
    public Integer selectCountByUserIdAndStatus(Long userId, Integer status) {

        EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        if (status != null) {
            entityWrapper.eq("status", status);
        }
        return baseMapper.selectCount(entityWrapper);
    }

    /**
     * 根据id和userid获取车辆
     */
    @Override
    public UserCarInfo findUserCarById(Long userId, Long id) {
        Wrapper<UserCarInfo> entityWrapper = new EntityWrapper<UserCarInfo>();
        entityWrapper.eq("id", id);
        entityWrapper.eq("userId", userId);
        List<UserCarInfo> userCarInfoList = baseMapper.selectList(entityWrapper);
        if (userCarInfoList != null && !userCarInfoList.isEmpty()) {
            return userCarInfoList.get(0);
        }
        return null;
    }
}
