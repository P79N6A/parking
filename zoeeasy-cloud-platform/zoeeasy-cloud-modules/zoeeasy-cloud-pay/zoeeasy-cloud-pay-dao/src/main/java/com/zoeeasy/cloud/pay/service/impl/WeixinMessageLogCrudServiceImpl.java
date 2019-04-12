package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pay.domain.WeixinMessageLogEntity;
import com.zoeeasy.cloud.pay.mapper.WeixinMessageLogMapper;
import com.zoeeasy.cloud.pay.service.WeixinMessageLogCrudService;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 * @since 2018-09-12
 */
@Service("weixinMessageLogCrudService")
public class WeixinMessageLogCrudServiceImpl extends ServiceImpl<WeixinMessageLogMapper, WeixinMessageLogEntity> implements WeixinMessageLogCrudService {

    /**
     * 添加
     *
     * @return
     */
    @Override
    public Integer add(WeixinMessageLogEntity weixinMessageLogEntity) {
        return baseMapper.add(weixinMessageLogEntity);
    }

}