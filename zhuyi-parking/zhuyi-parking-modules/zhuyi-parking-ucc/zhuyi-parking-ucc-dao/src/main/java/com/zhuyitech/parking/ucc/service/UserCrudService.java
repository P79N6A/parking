package com.zhuyitech.parking.ucc.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.User;

import java.util.List;

/**
 * @author walkman
 * @date 2017-11-21
 */
public interface UserCrudService extends CrudService<User> {

    /**
     * 修改用户登录密码
     *
     * @param userId
     * @param salt
     * @param password
     * @return
     */
    Integer updatePassword(Long userId, String salt, String password);

    /**
     * 修改支付密码
     *
     * @param userId
     * @param tradeSalt
     * @param tradePassword
     * @return
     */
    Integer updateTradePassword(Long userId, String tradeSalt, String tradePassword);

    /**
     * 通过用户名查找
     *
     * @param username 用户名
     * @return
     */
    User findByUsername(String username);

    /**
     * 通过手机号查找
     *
     * @param phoneNumber
     * @return
     */
    User findByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查找
     *
     * @param emailAddress
     * @return
     */
    User findByEmailAddress(String emailAddress);

    /**
     * 通过ID关联用户信息查询
     *
     * @param id
     * @return
     */
    User selectByIdWithUserInfo(Long id);

    /**
     * 关联用户信息查询列表
     *
     * @param wrapper
     * @return
     */
    List<User> selectListWithUserInfo(Wrapper<User> wrapper);

    /**
     * 关联用户信息分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    Page<User> selectPageWithUserInfo(Page<User> page, Wrapper<User> wrapper);
}
