package com.zoeeasy.cloud.pay.service;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pay.domain.AlipayMessageLogEntity;

import java.util.List;

/**
 * @author zwq
 * @since 2018-09-12
 */
public interface AlipayMessageLogCrudService extends CrudService<AlipayMessageLogEntity> {

    /**
     * 通过通知ID和处理状态查找
     *
     * @param notifyId
     * @param status
     * @return
     */
    List<AlipayMessageLogEntity> findByNotifyIdAndStatus(String notifyId, Integer status);

    /**
     * 获取日志
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    AlipayMessageLogEntity get(Wrapper<AlipayMessageLogEntity> wrapper);

    /**
     * 添加
     *
     * @param alipayMessageLogEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer add(AlipayMessageLogEntity alipayMessageLogEntity);

}
