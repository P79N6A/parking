package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.ucc.domain.UserLoginLogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */
@Repository("userLoginLogEntityMapper")
public interface UserLoginLogMapper extends BaseMapper<UserLoginLogEntity> {

    /**
     * 保存登录记录
     *
     * @param userLoginLogEntity
     */
    @SqlParser(filter = true)
    void save(UserLoginLogEntity userLoginLogEntity);
}
