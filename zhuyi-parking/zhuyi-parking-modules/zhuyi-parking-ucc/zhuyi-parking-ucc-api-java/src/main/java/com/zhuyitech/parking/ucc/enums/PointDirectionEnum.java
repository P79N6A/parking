package com.zhuyitech.parking.ucc.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 积分余额变动方向
 *
 * @author walkman
 * @date 2018-02-01
 */
public enum PointDirectionEnum {

    /**
     * 加款
     */
    ADD(1, "加款"),

    /**
     * 减款
     */
    SUB(2, "减款");

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

    PointDirectionEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PointDirectionEnum parse(Integer value) {
        if (value != null) {
            PointDirectionEnum[] array = values();
            for (PointDirectionEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }

    public static List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        AssetDirectionEnum[] val = AssetDirectionEnum.values();
        for (AssetDirectionEnum e : val) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("comment", e.getComment());
            map.put("name", e.name());
            list.add(map);
        }
        return list;
    }

    public static PointDirectionEnum getEnum(String name) {
        PointDirectionEnum resultEnum = null;
        PointDirectionEnum[] enumAry = PointDirectionEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (enumAry[i].name().equals(name)) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }
}

