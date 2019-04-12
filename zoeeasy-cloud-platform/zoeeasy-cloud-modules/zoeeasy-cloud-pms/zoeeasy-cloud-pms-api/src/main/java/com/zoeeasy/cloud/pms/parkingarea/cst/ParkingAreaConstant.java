package com.zoeeasy.cloud.pms.parkingarea.cst;

/**
 * Created by song on 2018/9/11.
 */
public final class ParkingAreaConstant {

    public static final String PARKING_AREA_CODE_NOT_NULL = "泊车区域编号不能为空";
    public static final int PARKING_AREA_CODE_MIN_LENGTH = 2;
    public static final int PARKING_AREA_CODE_MAX_LENGTH = 255;
    public static final String PARKING_AREA_CODE_LENGTH_RANGE = "泊车区域编号{min}-{max}个字符";
    public static final String PARKING_AREA_CODE_LENGTH_RANGE_MAX = "泊车区域编号最多{max}个字符";
    public static final String PARKING_AREA_CODE_PATTERN = "^[0-9|a-z|A-Z]{2,255}$";
    public static final String PARKING_AREA_CODE_PATTERN_CLOUD = "^[0-9|a-z|A-Z]{2,255}$";
    public static final String PARKING_AREA_CODE_ILLEGAL = "泊车区域编号不合法";
    public static final String PARKING_AREA_CODE_USED = "泊车区域编号被占用";
    public static final String PARKING_AREA_NAME_NOT_NULL = "泊车区域名称不能为空";
    public static final int PARKING_AREA_NAME_MIN_LENGTH = 0;
    public static final int PARKING_AREA_NAME_MAX_LENGTH = 32;
    public static final String PARKING_AREA_NAME_LENGTH_RANGE = "请输入0~32个字符！不包含：V:*?\"<|'%>&";
    public static final String PARKING_AREA_NAME_USED = "泊车区域名称被占用";
    public static final String PARKING_AREA_NAME_ILLEGAL = "泊车区域名称不合法";
    public static final String PARKING_AREA_LOTTOTAL_NOT_NULL = "车位总数不能为空";
    public static final String PARKING_AREA_LOTFIXED_NOT_NULL = "固定车位总数不能为空";
    public static final String PARKING_AREA_LOTAVAILABLE_NOT_NULL = "剩余车位数不能为空";
    public static final int PARKING_AREA_REMARK_MAX_LENGTH = 32;
    public static final String PARKING_AREA_REMARK_LENGTH_RANGE = "请输入0~32个字符！不包含：V:*?\"<|'%>&";
    public static final String PARKING_NOT_FOUND_GARAGE = "停车场与车库不匹配";
    public static final String PARKING_AREA_NOT_EXIST = "泊车区域不存在";
    public static final int PARKING_AREA_MIN_LENGTH = 2;
    public static final int PARKING_AREA_MAX_LENGTH = 20;
    public static final String PARKING_AREA_LENGTH_RANGE_LENGTH_RANGE = "停车区域编号{min}-{max}个字符";
    public static final String PARKING_AREA_PATTERN = "[0-9|a-z|A-Z]{2,20}$";
    public static final String PARKING_AREA_ILLEGAL = "停车区域编号不合法";
    public static final String PARKING_AREA_ID_NOT_NULL = "停车区域ID不能为空";
    public static final String FLOOR_CODE_EXIST = "楼层code已存在";

    private ParkingAreaConstant() {
    }

}
