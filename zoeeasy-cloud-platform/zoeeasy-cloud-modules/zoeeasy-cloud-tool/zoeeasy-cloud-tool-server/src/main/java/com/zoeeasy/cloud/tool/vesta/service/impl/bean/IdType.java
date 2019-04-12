package com.zoeeasy.cloud.tool.vesta.service.impl.bean;

/**
 * ID类型
 *
 * @author walkman
 */
public enum IdType {

    /**
     * 最大峰值型0：采用秒级有序，秒级时间占用30位，序列号占用20位
     */
    MAX_PEAK("max-peak"),

    /**
     * 最小粒度型1：采用毫秒级有序，毫秒级时间占用40位，序列号占用10位
     */
    MIN_GRANULARITY("min-granularity");

    private String name;

    private IdType(String name) {
        this.name = name;
    }

    public long value() {
        switch (this) {
            case MAX_PEAK:
                return 0;
            case MIN_GRANULARITY:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static IdType parse(String name) {
        if ("min-granularity".equals(name))
            return MIN_GRANULARITY;
        else if ("max-peak".equals(name))
            return MAX_PEAK;
        return null;
    }

    public static IdType parse(long type) {
        if (type == 1)
            return MIN_GRANULARITY;
        else if (type == 0)
            return MAX_PEAK;
        return null;
    }
}
