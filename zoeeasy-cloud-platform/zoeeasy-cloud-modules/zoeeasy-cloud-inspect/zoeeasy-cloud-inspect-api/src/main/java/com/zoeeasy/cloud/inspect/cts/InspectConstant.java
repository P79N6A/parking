package com.zoeeasy.cloud.inspect.cts;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
public final class InspectConstant {

    private InspectConstant() {
    }

    public static final int MIN = 2;
    public static final int MAX = 64;
    public static final String LENGTH_RANGE = "字符串应在{min}-{max}个字符";
    public static final String INSPECT_REASON_NOT_STANDARD = "异常原因不符合规范";
    public static final String INSPECT_REASON_NOT_EMPTY = "异常原因不能为空";
    public static final String PARKING_ID_NOT_EMPTY = "停车场id不能为空";
    public static final String PARKING_LOT_ID_NOT_EMPTY = "泊位id不能为空";
    public static final String PARKING_LOT_CODE_NOT_EMPTY = "泊位编号不能为空";
    public static final String TENANT_ID_NOT_EMPTY = "租户id不能为空";
    public static final String DATE_TIME_NOT_BEFORE_NOW = "时间不能为未来时间";
    public static final String PARKING_NOT_INSPECTOR = "停车场暂无巡检人员";
    public static final String TURN_OUT_NOT_EMPTY = "是否出车不能为空";
    public static final String OUT_PASS_TIME_NOT_EMPTY = "出车时间不能为空";
    public static final String USER_ID_NOT_NULL = "用户Id不能为空";
    public static final String PARKING_ID_NONENTITY="停车场不存在";
}
