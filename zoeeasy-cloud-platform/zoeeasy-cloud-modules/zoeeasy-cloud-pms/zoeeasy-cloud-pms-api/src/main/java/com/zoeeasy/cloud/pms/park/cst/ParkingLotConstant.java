package com.zoeeasy.cloud.pms.park.cst;

/**
 * Created by song on 2018/9/11.
 */
public final class ParkingLotConstant {

    private ParkingLotConstant() {
    }

    public static final String PARKING_LOT_CODE_NOT_NULL = "泊位编号不能为空";
    public static final String PARKING_LOT_ID_NOT_NULL = "泊位ID不能为空";
    public static final String PARKING_LOT_NUMBER_NOT_NULL = "泊位号不能为空";
    public static final String PARKING_LOT_CODE_USED = "泊位编号被占用";
    public static final String PARKING_LOT_LOCAL_CODE_USED = "客户端编号被占用";
    public static final int PARKING_LOT_NUMBER_MIN_LENGTH = 0;

    public static final int PARKING_LOT_NUMBER_MAX_LENGTH = 32;

    public static final String PARKING_LOT_NUMBER_LENGTH_RANGE = "请输入0~32个字符！不包含：V:*?\"<|'%>&";

    public static final String PARKING_LOT_CODE_LENGTH_RANGE = "泊位编号{min}-{max}个字符";

    public static final String PARKING_LOT_CODE_PATTERN = "^[0-9|a-z|A-Z]{2,64}$";

    public static final String PARKING_LOT_CODE_ILLEGAL = "泊位编号不合法";

    public static final String PARKING_LOT_NAME_NOT_NULL = "泊位名称不能为空";

    public static final int PARKING_LOT_NAME_MIN_LENGTH = 1;

    public static final int PARKING_LOT_NAME_MAX_LENGTH = 64;

    public static final String PARKING_LOT_NAME_LENGTH_RANGE = "泊位名称最多{max}个字符";

    public static final String PARKING_LOT_NAME_ILLEGAL = "泊位名称不合法";

    public static final String PARKING_LOT_NUMBER_ILLEGAL = "泊位号不合法";

    public static final String PARKING_LOT_NUMBER_USED = "泊位号被占用";

    public static final int PARKING_LOT_DESCRIPTION_MAX_LENGTH = 32;

    public static final String PARKING_LOT_DESCRIPTION_LENGTH_RANGE = "请输入0~32个字符！不包含：V:*?\"<|'%>&";

    public static final String PARKING_NOT_FOUND_GARAGE = "车库与停车场不匹配";

    public static final String PARKING_NOT_FOUND_PARKINGAREA = "泊位区域与停车场不匹配";

    public static final String GARAGE_NOT_FOUND_PARKINGAREA = "泊位区域与车库不匹配";

    public static final String PARKING_LOT_NOT_FOUND = "泊位不存在";

    public static final String PARKING_LOT_BE_USING = "泊位正在使用，不能修改";
    public static final String PARKING_LOT_BE_USING_NOT_DELETE = "泊位正在使用，不能删除";

    public static final String PARKING_LOT_LOCAL_CODE_NOT_NULL = "管理系统泊位编号不能为空";
    public static final int PARKING_LOT_LOCAL_CODE_MIN_LENGTH = 1;
    public static final int PARKING_LOT_LOCAL_CODE_MAX_LENGTH = 20;
    public static final String PARKING_LOT_LOCAL_CODE_LENGTH_RANGE = "管理系统泊位编号{min}-{max}个字符";
    public static final String PARKING_LOT_LOCAL_CODE_PATTERN = "^[0-9|a-z|A-Z]{1,20}$";
    public static final String PARKING_LOT_LOCAL_CODE_ILLEGAL = "管理系统停车场编号不合法";
    public static final String PARKING_LOT_STATUS_NOT_NULL = "泊位状态不能为空";
    public static final String PARKING_LOT_RELEVANCE = "泊位关联了地磁不能被删除";
    public static final String PARK_VEHICLE_ID_NOT_EMPTY = "在停车辆id不能为空";
    public static final String FLOOR_NOT_FOUND = "楼层不存在";

}
