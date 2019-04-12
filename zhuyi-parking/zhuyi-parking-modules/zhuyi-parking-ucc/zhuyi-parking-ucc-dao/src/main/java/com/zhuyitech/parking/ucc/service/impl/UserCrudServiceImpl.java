package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.User;
import com.zhuyitech.parking.ucc.enums.UserTypeEnum;
import com.zhuyitech.parking.ucc.mapper.UserMapper;
import com.zhuyitech.parking.ucc.service.UserCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 * @date 2017-11-21
 */
@Service("userCrudService")
public class UserCrudServiceImpl extends CrudServiceImpl<UserMapper, User> implements UserCrudService {

    /**
     * 修改用户登录密码
     *
     * @param userId
     * @param salt
     * @param password
     * @return
     */
    @Override
    public Integer updatePassword(Long userId, String salt, String password) {

        User user = new User();
        user.setSalt(salt);
        user.setPassword(password);
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", userId);
        return baseMapper.update(user, entityWrapper);

    }

    /**
     * 修改支付密码
     *
     * @param userId
     * @param tradeSalt
     * @param tradePassword
     * @return
     */
    @Override
    public Integer updateTradePassword(Long userId, String tradeSalt, String tradePassword) {
        User user = new User();
        user.setTradeSalt(tradeSalt);
        user.setTradePassword(tradePassword);
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", userId);
        return baseMapper.update(user, entityWrapper);
    }

    /**
     * 通过用户名查找
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        EntityWrapper<User> entityWrapper = new EntityWrapper();
        entityWrapper.eq("username", username);
        entityWrapper.ne("userType", UserTypeEnum.TEMPORARY_CUSTOMER);
        List<User> list = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过手机号查找
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public User findByPhoneNumber(String phoneNumber) {
        EntityWrapper<User> entityWrapper = new EntityWrapper();
        entityWrapper.eq("phoneNumber", phoneNumber);
        entityWrapper.ne("userType", UserTypeEnum.TEMPORARY_CUSTOMER);
        List<User> list = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过邮箱查找
     *
     * @param emailAddress
     * @return
     */
    @Override
    public User findByEmailAddress(String emailAddress) {
        User upUser = new User();
        upUser.setEmailAddress(emailAddress);
        return baseMapper.selectOne(upUser);
    }

    /**
     * 通过ID关联用户信息查询
     *
     * @param id
     * @return
     */
    @Override
    public User selectByIdWithUserInfo(Long id) {
        return baseMapper.selectByIdWithUserInfo(id);
    }

    /**
     * 关联用户信息查询列表
     *
     * @param wrapper
     * @return
     */
    @Override
    public List<User> selectListWithUserInfo(Wrapper<User> wrapper) {
        return baseMapper.selectListWithUserInfo(wrapper);
    }

    /**
     * 关联用户信息分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<User> selectPageWithUserInfo(Page<User> page, Wrapper<User> wrapper) {
        wrapper.eq("1", "1");
        return page.setRecords(baseMapper.selectPageWithUserInfo(page, wrapper));
    }
}
