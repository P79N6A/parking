package com.zoeeasy.cloud.tool.region.cst;

public final class RegionConstant {

    private RegionConstant() {
    }

    public static final String REGION_PARENT_CODE_NOT_NULL = "上级代码不能为空";
    public static final String REGION_PARENT_LEVEL_NOT_NULL = "上级层级不能为空";
    public static final String REGION_CODE_PATTERN = "^$|^(\\d){6}(_\\d{5})*$";
    public static final String REGION_CODE_ILLEGAL = "区域代码不符合规范";
    public static final int PROVINCE_CODE = 1;
    public static final int CITY_CODE = 2;
    public static final int COUNTY_CODE = 3;

}
