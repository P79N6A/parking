package com.zoeeasy.cloud.notify.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.notify.domain.PushTag;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
public interface PushTagCrudService extends CrudService<PushTag> {
    /**
     * 保存pushtag
     *
     * @param pushTag
     * @return
     */
    Long save(PushTag pushTag);
}
