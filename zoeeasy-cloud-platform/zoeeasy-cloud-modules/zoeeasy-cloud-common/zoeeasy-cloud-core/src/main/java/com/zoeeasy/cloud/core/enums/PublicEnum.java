package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共枚举. 针对只有“是”，“否”两种状态
 */
@AllArgsConstructor
@NoArgsConstructor
public enum PublicEnum {

    YES(1, "是"),

    NO(0, "否");

    @Getter
    private Integer value;

    @Getter
    private String desc;

    public static PublicEnum getEnum(String name) {
        PublicEnum[] arry = PublicEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(name)) {
                return arry[i];
            }
        }
        return null;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PublicEnum parse(Integer value) {
        if (value != null) {
            PublicEnum[] array = values();
            for (PublicEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    public static Map<String, Map<String, Object>> toMap() {
        PublicEnum[] ary = PublicEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<>();
            String key = ary[num].name();
            map.put("desc", ary[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List toList() {
        PublicEnum[] ary = PublicEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("desc", ary[i].getDesc());
            list.add(map);
        }
        return list;
    }

}
