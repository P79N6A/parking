package com.zoeeasy.cloud.tool.vesta.service.impl.populater;

import com.zoeeasy.cloud.tool.vesta.bean.Id;
import com.zoeeasy.cloud.tool.vesta.service.impl.bean.IdMeta;
import com.zoeeasy.cloud.tool.vesta.service.impl.bean.IdType;
import com.zoeeasy.cloud.tool.vesta.util.TimeUtils;

public abstract class BasePopulator implements IdPopulator, ResetPopulator {

    /**
     * 流水号
     */
    protected long sequence = 0;

    /**
     * 上一次的时间
     */
    protected long lastTimestamp = -1;

    public BasePopulator() {
        super();
    }

    /**
     * 产生流水ID的算法
     *
     * @param id
     * @param idMeta
     */
    public void populateId(Id id, IdMeta idMeta) {
        long timestamp = TimeUtils.genTime(IdType.parse(id.getType()));
        TimeUtils.validateTimestamp(lastTimestamp, timestamp);
        if (timestamp == lastTimestamp) {
            sequence++;
            sequence &= idMeta.getSeqBitsMask();
            if (sequence == 0) {
                timestamp = TimeUtils.tillNextTimeUnit(lastTimestamp, IdType.parse(id.getType()));
            }
        } else {
            lastTimestamp = timestamp;
            sequence = 0;
        }
        id.setSeq(sequence);
        id.setTime(timestamp);
    }

    public void reset() {
        this.sequence = 0;
        this.lastTimestamp = -1;
    }
}
