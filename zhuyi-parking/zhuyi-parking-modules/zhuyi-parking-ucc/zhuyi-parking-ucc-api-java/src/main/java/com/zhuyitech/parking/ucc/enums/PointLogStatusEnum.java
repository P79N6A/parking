package com.zhuyitech.parking.ucc.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户资金流水状态
 *
 * @author walkman
 * @date 2018-02-01
 */
public enum PointLogStatusEnum {

    /**
     * 预处理阶段
     */
    TRYING(1, "处理中"),

    /**
     * 已确认的
     */
    CONFORM(2, "已确认"),

    /**
     * 取消的
     */
    CANCEL(3, "取消");

    /**
     * 值
     */
    private int value;

    /**
     * 注释
     */
    private String comment;

    public int getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }

    PointLogStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    public static List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        PointLogStatusEnum[] val = PointLogStatusEnum.values();
        for (PointLogStatusEnum e : val) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", e.name());
            map.put("comment", e.getComment());
            list.add(map);
        }
        return list;
    }

    public static PointLogStatusEnum getEnum(String name) {
        PointLogStatusEnum resultEnum = null;
        PointLogStatusEnum[] enumAry = PointLogStatusEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (enumAry[i].name().equals(name)) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

}
