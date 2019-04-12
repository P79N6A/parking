package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付类型枚举类
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
public enum PayTypeEnum {

    ALI_TEST(10, "ALI_TEST", "支付宝测试"),

    /**
     * 支付宝扫码
     */
    ALI_SCANBAR(11, "ALI_SCANBAR", "扫码"),

    /**
     * 支付宝app支付
     */
    ALI_APP(12, "ALI_APP", "APP支付"),

    /**
     * 支付宝条码支付
     */
    ALI_BARCODE(13, "ALI_BARCODE", "条码支付"),

    /**
     * 支付宝手机网站
     */
    ALI_H5(14, "ALI_H5", "手机网站"),

    /**
     * 支付宝手机网站
     */
    ALI_PC(15, "ALI_PC", "PC网站"),

    ALI_DIRECT(16, "ALI_DIRECT", "即时到账"),

    /**
     * 支付宝小程序支付
     */
    ALI_MINI(17, "ALI_MINI", "支付宝小程序支付"),

    WX_TEST(20, "TEST_PAY", "测试模拟支付"),

    /**
     * 公众号支付
     */
    WX_JSAPI(21, "WX_JSAPI", "公众号支付"),

    /**
     * APP支付
     */
    WX_APP(22, "WX_APP", "微信APP支付"),

    /**
     * 扫码支付
     */
    WX_NATIVE(23, "WX_NATIVE", "扫码支付"),

    /**
     * 刷卡支付
     */
    WX_MICROPAY(24, "WX_MICROPAY", "刷卡支付"),

    WX_H5(25, "WX_H5", "H5支付"),

    /**
     * 微信小程序支付
     */
    WX_MINI(26, "WX_MINI", "微信小程序支付"),

    PACKET_BALANCE(30, "BALANCE", "钱包余额"),

    TEST_PAY_HTTP_CLIENT(90, "TEST_PAY_HTTP_CLIENT", "测试模拟httpclient支付"),

    OTHER(99, "OTHER", "其他");

    @Getter
    private Integer value;

    /**
     * 所属支付方式
     */
    @Getter
    private String way;

    /**
     * 描述
     */
    @Getter
    private String desc;

    public static Map<String, Map<String, Object>> toMap() {
        PayTypeEnum[] ary = PayTypeEnum.values();
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
        PayTypeEnum[] ary = PayTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("desc", ary[i].getDesc());
            map.put("name", ary[i].name());
            list.add(map);
        }
        return list;
    }

    public static PayTypeEnum parse(String way) {
        PayTypeEnum[] arry = PayTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getWay().equalsIgnoreCase(way)) {
                return arry[i];
            }
        }
        return null;
    }

    public static PayTypeEnum valuedOf(Integer value) {
        PayTypeEnum[] arry = PayTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getValue().equals(value)) {
                return arry[i];
            }
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List getWayList(String way) {
        PayTypeEnum[] ary = PayTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            if (ary[i].way.equals(way)) {
                Map<String, String> map = new HashMap<>();
                map.put("desc", ary[i].getDesc());
                map.put("name", ary[i].name());
                list.add(map);
            }
        }
        return list;
    }
}
