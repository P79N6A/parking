package com.zoeeasy.cloud.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public enum FundInfoTypeEnum {

    PLATFORM_RECEIVES(1, "平台收款"),

    MERCHANT_RECEIVES(2, "商家收款"),

    PLATFORM_REFUNDS(2, "平台付款"),
    ;

    @Getter
    private Integer value;

    /**
     * 描述
     */
    @Getter
    private String desc;

    public static Map<String, Map<String, Object>> toMap() {
        FundInfoTypeEnum[] ary = FundInfoTypeEnum.values();
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
        FundInfoTypeEnum[] ary = FundInfoTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("desc", ary[i].getDesc());
            map.put("name", ary[i].name());
            list.add(map);
        }
        return list;
    }

    public static FundInfoTypeEnum getEnum(String name) {
        FundInfoTypeEnum[] arry = FundInfoTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(name)) {
                return arry[i];
            }
        }
        return null;
    }
}
