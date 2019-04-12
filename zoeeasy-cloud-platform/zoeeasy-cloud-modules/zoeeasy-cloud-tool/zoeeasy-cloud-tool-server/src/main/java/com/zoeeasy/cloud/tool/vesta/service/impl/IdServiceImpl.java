package com.zoeeasy.cloud.tool.vesta.service.impl;

import com.zoeeasy.cloud.tool.vesta.bean.Id;
import com.zoeeasy.cloud.tool.vesta.service.impl.bean.IdType;
import com.zoeeasy.cloud.tool.vesta.service.impl.populater.AtomicIdPopulator;
import com.zoeeasy.cloud.tool.vesta.service.impl.populater.IdPopulator;
import com.zoeeasy.cloud.tool.vesta.service.impl.populater.LockIdPopulator;
import com.zoeeasy.cloud.tool.vesta.service.impl.populater.SyncIdPopulator;
import com.zoeeasy.cloud.tool.vesta.util.CommonUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * IdService实现类
 *
 * @author walkman
 */
@Slf4j
public class IdServiceImpl extends AbstractIdServiceImpl {

    /**
     * 同步锁
     */
    private static final String SYNC_LOCK_IMPL_KEY = "vesta.sync.lock.impl.key";

    /**
     * 无锁
     */
    private static final String ATOMIC_IMPL_KEY = "vesta.atomic.impl.key";

    @Setter
    protected IdPopulator idPopulator;

    public IdServiceImpl() {
        super();
        initPopulator();
    }

    public IdServiceImpl(String type) {
        super(type);
        initPopulator();
    }

    public IdServiceImpl(IdType type) {
        super(type);
        initPopulator();
    }

    /**
     * 根据不同的方式调用不同的同步机制
     */
    public void initPopulator() {
        if (idPopulator != null) {
            log.info("The " + idPopulator.getClass().getCanonicalName() + " is used.");
        } else if (CommonUtils.isPropKeyOn(SYNC_LOCK_IMPL_KEY)) {
            log.info("The SyncIdPopulator is used.");
            idPopulator = new SyncIdPopulator();
        } else if (CommonUtils.isPropKeyOn(ATOMIC_IMPL_KEY)) {
            log.info("The AtomicIdPopulator is used.");
            idPopulator = new AtomicIdPopulator();
        } else {
            log.info("The default LockIdPopulator is used.");
            idPopulator = new LockIdPopulator();
        }
    }

    protected void populateId(Id id) {
        idPopulator.populateId(id, this.idMeta);
    }

}
