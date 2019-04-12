package com.zoeeasy.cloud.pms.passing.cts;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
public final class PassingConstant {

    public static final int PASSING_NUMBER_MIN_LENGTH = 2;
    public static final int PASSING_NUMBER_MAX_LENGTH = 64;
    public static final String NUMBER_LENGTH_RANGE = "编号{min}-{max}个字符";
    public static final String PASSING_NUMBER_NOT_EMPTY = "过车记录编号不能为空";
    public static final String ENTRY_TIME_NOT_EMPTY = "过车时间不能为空";
    public static final String PLATE_COLOR_NOT_EMPTY = "车牌颜色不能为空";
    public static final String CAR_TYPE_NOT_EMPTY = "车辆类型不能为空";
    public static final String PLATE_NUMBER_NOT_EMPTY = "车牌号不能为空";
    public static final String PASSING_ID_NOT_NULL = "过车ID为空";
    public static final String PASSING_REMARK_NOT_EMPTY = "过车备注不能为空";
    public static final String PASSING_TYPE_NOT_EMPTY = "过车类型不能为空";
    public static final String PASSING_TYPE_NOT_STANDARD = "过车类型有误";
    public static final String PASSING_TYPE_LIMIT_ENTER = "只能添加入车记录";
    public static final String CLOUD_PARKING_CODE_NOT_EMPTY = "停车场编号不能为空";

    private PassingConstant() {
    }

}
