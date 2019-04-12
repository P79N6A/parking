package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserCarAuth;
import com.zhuyitech.parking.ucc.mapper.UserCarAuthMapper;
import com.zhuyitech.parking.ucc.service.UserCarAuthCrudService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 */
@Service("userCarAuthCrudService")
public class UserCarAuthCrudServiceImpl extends CrudServiceImpl<UserCarAuthMapper, UserCarAuth> implements UserCarAuthCrudService {

    /**
     * 通过车牌号查找车辆
     *
     * @param plateNumber
     * @return
     */
    @Override
    public UserCarAuth findByPlateNumber(String plateNumber) {
        EntityWrapper<UserCarAuth> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("plateNumber", plateNumber);
        List<UserCarAuth> userCarInfoList = baseMapper.selectList(entityWrapper);
        if (userCarInfoList != null && !userCarInfoList.isEmpty()) {
            return userCarInfoList.get(0);
        }
        return null;
    }

    /**
     * 根据车牌号和用户Id获取车辆
     */
    @Override
    public UserCarAuth findByPlateNumberAndUserId(String plateNumber, Long userId) {
        EntityWrapper<UserCarAuth> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("plateNumber", plateNumber);
        entityWrapper.eq("userId", userId);
        entityWrapper.orderBy("creationTime", false);
        List<UserCarAuth> userCarInfoList = baseMapper.selectList(entityWrapper);
        if (userCarInfoList != null && !userCarInfoList.isEmpty()) {
            return userCarInfoList.get(0);
        }
        return null;
    }

}
