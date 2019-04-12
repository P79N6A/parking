package com.zoeeasy.cloud.tool.vesta.service.impl.populater;

import com.zoeeasy.cloud.tool.vesta.bean.Id;
import com.zoeeasy.cloud.tool.vesta.service.impl.bean.IdMeta;

public class SyncIdPopulator extends BasePopulator {

    public SyncIdPopulator() {
        super();
    }

    @Override
    public synchronized void populateId(Id id, IdMeta idMeta) {
        super.populateId(id, idMeta);
    }

}
