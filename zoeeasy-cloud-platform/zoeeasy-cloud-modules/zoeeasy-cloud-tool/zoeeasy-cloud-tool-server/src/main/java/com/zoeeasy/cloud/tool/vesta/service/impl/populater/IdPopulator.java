package com.zoeeasy.cloud.tool.vesta.service.impl.populater;

import com.zoeeasy.cloud.tool.vesta.bean.Id;
import com.zoeeasy.cloud.tool.vesta.service.impl.bean.IdMeta;

public interface IdPopulator {

    /**
     * 同秒内产生流水号
     *
     * @param id
     * @param idMeta
     */
    void populateId(Id id, IdMeta idMeta);

}
