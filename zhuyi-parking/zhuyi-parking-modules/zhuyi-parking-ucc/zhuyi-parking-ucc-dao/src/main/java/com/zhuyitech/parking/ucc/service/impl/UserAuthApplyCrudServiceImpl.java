package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserAuthApply;
import com.zhuyitech.parking.ucc.mapper.UserAuthApplyMapper;
import com.zhuyitech.parking.ucc.service.UserAuthApplyCrudService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("userAuthApplyCrudService")
public class UserAuthApplyCrudServiceImpl extends CrudServiceImpl<UserAuthApplyMapper, UserAuthApply> implements UserAuthApplyCrudService {

    /**
     * 通过用户ID查找
     */
    @Override
    public UserAuthApply findByUserId(Long userId) {
        EntityWrapper<UserAuthApply> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        entityWrapper.orderBy("applyTime", false);
        List<UserAuthApply> userAuthApplies = baseMapper.selectList(entityWrapper);
        if (userAuthApplies.isEmpty()) {
            return null;
        } else {
            return userAuthApplies.get(0);
        }
    }
}
