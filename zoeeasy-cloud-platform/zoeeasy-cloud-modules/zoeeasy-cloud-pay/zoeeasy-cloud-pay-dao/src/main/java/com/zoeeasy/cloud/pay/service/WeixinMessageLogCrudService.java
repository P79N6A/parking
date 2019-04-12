package com.zoeeasy.cloud.pay.service;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pay.domain.WeixinMessageLogEntity;

/**
 * @author zwq
 * @since 2018-09-12
 */
public interface WeixinMessageLogCrudService extends CrudService<WeixinMessageLogEntity> {

    /**
     * 添加
     *
     * @param weixinMessageLogEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer add(WeixinMessageLogEntity weixinMessageLogEntity);

}
