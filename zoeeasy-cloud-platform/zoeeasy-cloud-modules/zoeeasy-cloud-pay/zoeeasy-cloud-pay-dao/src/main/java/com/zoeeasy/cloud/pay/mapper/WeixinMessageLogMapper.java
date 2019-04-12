package com.zoeeasy.cloud.pay.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pay.domain.WeixinMessageLogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface WeixinMessageLogMapper extends BaseMapper<WeixinMessageLogEntity> {

    /**
     * 插入日志
     *
     * @param weixinMessageLogEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer add(WeixinMessageLogEntity weixinMessageLogEntity);

}