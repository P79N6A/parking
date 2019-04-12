package com.zoeeasy.cloud.integration.cts;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
public final class IntegrationConstant {
    private IntegrationConstant() {
    }

    public static final int MIN = 2;
    public static final int MAX = 64;
    public static final int DESCRIBE_LENGTH = 512;
    public static final String LENGTH_RANGE = "请输入{min}-{max}个字符!不包含：V:*?\"<|'%>&";
    public static final String NAME_REGEX = "^((?!V|:|\\*|\\?|'|<|\\||‘|%|>|&).)*$";
    public static final String CODE_REGEX = "^[0-9|a-z|A-Z]{2,255}$";
    public static final String NAME_REGEX_ERROR = "请输入0-32个字符!不包含：V:*?\"<|'%>&";
    public static final String DESCRIBE_REGEX_ERROR = "请输入0-512个字符!不包含：V:*?\"<|'%>&";


    public static final String TERMINAL_ID_NOT_STANDARD = "手持终端号不符合规范";
    public static final String REPOTRT_TYPE_NOT_STANDARD = "地磁报告类型不符合规范";
    public static final String REPORT_REASON_NOT_STANDARD = "误报原因不符合规范";
    public static final String PASS_TIME_NOT_STANDARD = "过车时间不符合规范";
    public static final String PASS_DIRECTION_NOT_STANDARD = "过车方向不符合规范";
    public static final String PLATE_COLOR_NOT_STANDARD = "过车方向不符合规范";
    public static final String SPECIAL_TYPE_NOT_STANDARD = "停车类型不符合规范";
    public static final String PASSING_CODE_NOT_EMPTY = "过车记录编号不能为空";
    public static final String CLOUD_PARKING_NOT_EMPTY = "云平台停车场Code不能为空";
    public static final String PLATE_NUMBER_NOT_EMPTY = "云平台停车场Code不能为空";
    public static final String DIRECTION_NOT_EMPTY = "过车方向不能为空";
    public static final String PASS_TIME_NOT_EMPTY = "过车时间不能为空";
    public static final String PLATE_COLOR_NOT_EMPTY = "车牌颜色不能为空";

    //appoint
    public static final String APPOINT_TIME_NOT_EMPTY = "预约时间不能为空";
    public static final String PARKING_ID_NOT_EMPTY = "停车场ID不能为空";
    public static final String LONGITUDE_NOT_EMPTY = "经度不能为空";
    public static final String LATITUDE_ID_NOT_EMPTY = "纬度不能为空";
    public static final String CUSTOMER_USERID_NOT_EMPTY = "车主用户ID不能为空";
    public static final String SCHEDULETIME_NOT_EMPTY = "预计开始时间不能为空";
    public static final String APPOINT_AMOUNT_NOT_EMPTY = "预约金额不能为空";
    public static final String PLATENUMBER_NOT_EMPTY = "车牌号不能为空";
    public static final String SCHEDULETIME_LESS_THAN_NOW = "入场时间不能小于当前时间";

}
