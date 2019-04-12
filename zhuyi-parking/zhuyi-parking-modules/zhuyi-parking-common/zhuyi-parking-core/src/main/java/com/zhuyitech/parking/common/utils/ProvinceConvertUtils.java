package com.zhuyitech.parking.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 省份简称全称转换
 *
 * @author AkeemSuper
 * @date 2018/8/16 0016
 */
public final class ProvinceConvertUtils {
    private ProvinceConvertUtils() {
    }

    private static final String[] PROVINCE_FULL_NAME = new String[] {"北京市", "天津市", "重庆市", "上海市", "河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省",
            "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省",
            "青海省", "内蒙古自治区", "广西壮族自治区", "宁夏回族自治区", "新疆维吾尔自治区", "西藏自治区"};

    private static final String[] AD_PROVINCE = {"北京", "天津", "重庆", "上海", "河北", "山西", "辽宁", "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建", "江西", "山东",
            "河南", "湖北", "湖南", "广东", "海南", "四川", "贵州", "云南", "陕西", "甘肃", "青海", "内蒙古", "广西", "宁夏", "新疆", "西藏"};

    /**
     * 省份简写
     */
    public static String abbreviation(String province) {
        List<String> list = new ArrayList<>(Arrays.asList(PROVINCE_FULL_NAME));
        int index = list.indexOf(province);
        return AD_PROVINCE[index];
    }

}
