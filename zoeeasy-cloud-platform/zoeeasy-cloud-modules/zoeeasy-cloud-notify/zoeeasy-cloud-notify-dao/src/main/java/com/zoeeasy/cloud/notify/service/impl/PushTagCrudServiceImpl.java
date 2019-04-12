package com.zoeeasy.cloud.notify.service.impl;

import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.notify.domain.PushTag;
import com.zoeeasy.cloud.notify.mapper.PushTagMapper;
import com.zoeeasy.cloud.notify.service.PushTagCrudService;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@Service("pushTagCrudService")
public class PushTagCrudServiceImpl extends CrudServiceImpl<PushTagMapper, PushTag> implements PushTagCrudService {
    /**
     * 保存pushtag
     *
     * @param pushTag
     * @return
     */
    @Override
    public Long save(PushTag pushTag) {
        pushTag.setCreationTime(DateUtils.now());
        return baseMapper.save(pushTag);
    }
}
