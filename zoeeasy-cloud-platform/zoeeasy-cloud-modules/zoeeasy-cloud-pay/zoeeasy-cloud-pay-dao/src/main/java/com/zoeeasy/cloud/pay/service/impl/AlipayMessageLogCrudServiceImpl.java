package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.pay.domain.AlipayMessageLogEntity;
import com.zoeeasy.cloud.pay.mapper.AlipayMessageLogMapper;
import com.zoeeasy.cloud.pay.service.AlipayMessageLogCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwq
 * @since 2018-09-12
 */
@Service("alipayMessageLogCrudService")
public class AlipayMessageLogCrudServiceImpl extends ServiceImpl<AlipayMessageLogMapper, AlipayMessageLogEntity> implements AlipayMessageLogCrudService {

    /**
     * 通过通知ID和处理状态查找
     *
     * @param notifyId
     * @param status
     * @return
     */
    @Override
    public List<AlipayMessageLogEntity> findByNotifyIdAndStatus(String notifyId, Integer status) {

        EntityWrapper<AlipayMessageLogEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("notifyId", notifyId);
        entityWrapper.eq("status", status);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 获取日志
     *
     * @return
     */
    @Override
    public AlipayMessageLogEntity get(Wrapper<AlipayMessageLogEntity> wrapper) {
        return baseMapper.get(wrapper);
    }

    /**
     * 添加
     *
     * @return
     */
    @Override
    public Integer add(AlipayMessageLogEntity alipayMessageLogEntity) {
        return baseMapper.add(alipayMessageLogEntity);
    }

}
