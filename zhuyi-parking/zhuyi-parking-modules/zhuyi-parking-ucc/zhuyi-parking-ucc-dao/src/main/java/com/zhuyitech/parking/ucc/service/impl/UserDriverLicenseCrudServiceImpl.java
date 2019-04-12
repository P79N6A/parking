package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserDriverLicense;
import com.zhuyitech.parking.ucc.mapper.UserDriverLicenseMapper;
import com.zhuyitech.parking.ucc.service.UserDriverLicenseCrudService;
import org.springframework.stereotype.Service;

/**
 * 用户驾驶证
 * @Date: 2018/1/15
 * @author: yuzhicheng
 */
@Service("UserDriverLicenseCrudService")
public class UserDriverLicenseCrudServiceImpl extends CrudServiceImpl<UserDriverLicenseMapper, UserDriverLicense> implements UserDriverLicenseCrudService {

    /**
     * 通过用户id查找
     * @param userId
     * @return
     */
    @Override
    public UserDriverLicense findByUserId(Long userId){
        UserDriverLicense userDriverLicense = new UserDriverLicense();
        userDriverLicense.setUserId(userId);
        return baseMapper.selectOne(userDriverLicense);
    }

    @Override
    public Integer deleteByUserId(Long userId){
        EntityWrapper<UserDriverLicense> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        return baseMapper.delete(entityWrapper);
    }
}
