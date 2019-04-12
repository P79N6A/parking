package com.zoeeasy.cloud.pms.area.cst;

public final class AreaConstant {

    public static final String AREA_DEFAULT_ROOT_NODE_CODE = "100000";
    public static final String AREA_NAME_NOT_NULL = "区域名称不能为空";
    public static final int AREA_NAME_MIN_LENGTH = 1;
    public static final int AREA_NAME_MAX_LENGTH = 32;
    public static final String AREA_NAME_LENGTH_RANGE = "区域名称应在{min}-{max}个字符";
    public static final String AREA_CODE_NOT_NULL = "区域代码不能为空";
    public static final String AREA_PARENT_CODE_NOT_NULL = "上级代码不能为空";
    public static final String AREA_LEVEL_NOT_NULL = "层级不能为空";
    public static final String AREA_EXIT = "区域已存在";
    public static final String AREA_CHILD_EXIT = "当前区域存在子级区域";
    public static final String AREA_PARK_EXIT = "当前区域下存在停车场";
    public static final String AREA_NOT_EXIT = "区域不存在";
    public static final String AREA_NAME_EXIT = "区域名称已存在";
    public static final String AREA_LEVEL_OUT = "超出层级范围";
    public static final String AREA_LEVEL_NON_EDITABLE = "当前层级不可修改";
    public static final String AREA_CODE_PATTERN = "^$|^(\\d){6}(_\\d{5})*$";
    public static final String AREA_PARENT_CODE_PATTERN = "^(\\d){6}(_\\d{5})*$";
    public static final String AREA_CODE_ILLEGAL = "区域代码不符合规范";
    public static final String AREA_NAME_PATTERN = "[\\u4e00-\\u9fa5a-zA-Z0-9]{1,32}";
    public static final String AREA_NAME_ILLEGAL = "区域名称不符合规范";
    public static final int AREA_FIRST_CHILD_CODE = 1;
    private AreaConstant() {

    }
}
