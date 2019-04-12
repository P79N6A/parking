package com.zoeeasy.cloud.pay.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pay.domain.AlipayMessageLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlipayMessageLogMapper extends BaseMapper<AlipayMessageLogEntity> {

    /**
     * 获取日志
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    AlipayMessageLogEntity get(@Param("ew") Wrapper<AlipayMessageLogEntity> wrapper);

    /**
     * 插入日志
     *
     * @param alipayMessageLogEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer add(AlipayMessageLogEntity alipayMessageLogEntity);

}