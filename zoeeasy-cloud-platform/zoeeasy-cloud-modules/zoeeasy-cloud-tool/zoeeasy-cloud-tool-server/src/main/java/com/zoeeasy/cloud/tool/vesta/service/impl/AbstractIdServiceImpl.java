package com.zoeeasy.cloud.tool.vesta.service.impl;

import com.zoeeasy.cloud.tool.vesta.bean.Id;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import com.zoeeasy.cloud.tool.vesta.service.impl.bean.IdMeta;
import com.zoeeasy.cloud.tool.vesta.service.impl.bean.IdMetaFactory;
import com.zoeeasy.cloud.tool.vesta.service.impl.bean.IdType;
import com.zoeeasy.cloud.tool.vesta.service.impl.converter.IdConverter;
import com.zoeeasy.cloud.tool.vesta.service.impl.converter.IdConverterImpl;
import com.zoeeasy.cloud.tool.vesta.service.impl.provider.MachineIdProvider;
import com.zoeeasy.cloud.tool.vesta.util.TimeUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * IdService抽象实现类
 *
 * @author walkman
 */
@Slf4j
public abstract class AbstractIdServiceImpl implements IdService {

    /**
     * 机器ID
     */
    @Setter
    protected long machineId = -1;

    /**
     * 生产方式
     */
    @Setter
    protected long genMethod = 0;

    @Setter
    protected long type = 0;

    /**
     * 版本
     */
    @Setter
    protected long version = 0;

    /**
     * 实现类型
     */
    @Setter
    protected IdType idType;

    /**
     * ID元数据
     */
    @Setter
    protected IdMeta idMeta;

    /**
     * 元数据,长整型转换器
     */
    @Setter
    protected IdConverter idConverter;

    /**
     * 机器ID生成器
     */
    @Setter
    protected MachineIdProvider machineIdProvider;

    public AbstractIdServiceImpl() {
        idType = IdType.MAX_PEAK;
    }

    public AbstractIdServiceImpl(String type) {
        idType = IdType.parse(type);
    }

    public AbstractIdServiceImpl(IdType type) {
        idType = type;
    }

    public void init() {
        this.machineId = machineIdProvider.getMachineId();

        if (machineId < 0) {
            log.error("The machine ID is not configured properly so that Vesta Service refuses to start.");

            throw new IllegalStateException(
                    "The machine ID is not configured properly so that Vesta Service refuses to start.");

        }
        if (this.idMeta == null) {
            setIdMeta(IdMetaFactory.getIdMeta(idType));
            setType(idType.value());
        } else {
            if (this.idMeta.getTimeBits() == 30) {
                setType(0);
            } else if (this.idMeta.getTimeBits() == 40) {
                setType(1);
            } else {
                throw new RuntimeException("Init Error. The time bits in IdMeta should be set to 30 or 40!");
            }
        }
        setIdConverter(new IdConverterImpl(this.idMeta));
    }

    public long genId() {
        Id id = new Id();
        id.setMachine(machineId);
        id.setGenMethod(genMethod);
        id.setType(type);
        id.setVersion(version);
        populateId(id);
        long ret = idConverter.convert(id);
        // Use trace because it cause low performance
        if (log.isTraceEnabled())
            log.trace(String.format("Id: %s => %d", id, ret));
        return ret;
    }

    protected abstract void populateId(Id id);

    public Date transTime(final long time) {
        if (idType == IdType.MAX_PEAK) {
            return new Date(time * 1000 + TimeUtils.EPOCH);
        } else if (idType == IdType.MIN_GRANULARITY) {
            return new Date(time + TimeUtils.EPOCH);
        }
        return null;
    }

    public Id expId(long id) {
        return idConverter.convert(id);
    }

    public long makeId(long time, long seq) {
        return makeId(time, seq, machineId);
    }

    public long makeId(long time, long seq, long machine) {
        return makeId(genMethod, time, seq, machine);
    }

    public long makeId(long genMethod, long time, long seq, long machine) {
        return makeId(type, genMethod, time, seq, machine);
    }

    public long makeId(long type, long genMethod, long time,
                       long seq, long machine) {
        return makeId(version, type, genMethod, time, seq, machine);
    }

    public long makeId(long version, long type, long genMethod,
                       long time, long seq, long machine) {
        IdType idType = IdType.parse(type);
        Id id = new Id(machine, seq, time, genMethod, type, version);
        IdConverter idConverter = new IdConverterImpl(idType);
        return idConverter.convert(id);
    }

}