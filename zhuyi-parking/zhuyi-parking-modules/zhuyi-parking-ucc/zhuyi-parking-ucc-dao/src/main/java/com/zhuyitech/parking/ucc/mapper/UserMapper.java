package com.zhuyitech.parking.ucc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhuyitech.parking.ucc.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 * @date 2017-11-21
 */
@Qualifier("sqlSessionFactory")
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过ID关联用户信息查询
     *
     * @param id
     * @return
     */
    User selectByIdWithUserInfo(@Param("id") Long id);

    /**
     * 关联用户信息查询列表
     *
     * @param wrapper
     * @return
     */
    List<User> selectListWithUserInfo(@Param("ew") Wrapper<User> wrapper);

    /**
     * 关联用户信息分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    List<User> selectPageWithUserInfo(Page<User> page, @Param("ew") Wrapper<User> wrapper);

    /**
     * @param wrapper
     * @return
     * @title: selectUserList
     * @description: 查找用户列表
     * @return: List<UpUser>
     */
    Page<User> selectUserList(Pagination page, @Param("ew") Wrapper<User> wrapper);
}