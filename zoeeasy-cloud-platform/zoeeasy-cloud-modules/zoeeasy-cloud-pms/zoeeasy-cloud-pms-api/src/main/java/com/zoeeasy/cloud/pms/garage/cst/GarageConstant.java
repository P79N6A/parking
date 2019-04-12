package com.zoeeasy.cloud.pms.garage.cst;

/**
 * Created by song on 2018/9/11.
 */
public final class GarageConstant {

    private GarageConstant() {
    }

    public static final String PARKING_INFO_PARKING_ID_NOT_NULL = "停车场ID不能为空";

    public static final String TENANTE_ID_IS_NULL = "租户为空";

    public static final String PARKING_GARAGE_GARAGE_CODE_NOT_NULL = "车库编号不能为空";

    public static final int PARKING_GARAGE_GARAGE_CODE_MIN_LENGTH = 2;

    public static final int PARKING_GARAGE_GARAGE_CODE_MAX_LENGTH = 32;

    public static final int GARAGE_CODE_MIN_LENGTH = 2;

    public static final int GARAGE_CODE_MAX_LENGTH = 20;

    public static final String GARAGE_CODE_LENGTH_RANGE_LENGTH_RANGE = "客户端车库编号{min}-{max}个字符";

    public static final String GARAGE_CODE_PATTERN = "[0-9|a-z|A-Z]{2,20}$";

    public static final String PARKING_GARAGE_GARAGE_CODE_USED = "车库编号被占用";

    public static final String PARKING_GARAGE_GARAGE_CODE_LENGTH_RANGE = "车库编号{min}-{max}个字符";

    public static final String PARKING_GARAGE_CODE_PATTERN = "^[0-9|a-z|A-Z]{2,32}$";

    public static final String PARKING_GARAGE_CODE_ILLEGAL = "车库编号不合法";

    public static final String PARKING_GARAGE_GARAGE_NAME_NOT_NULL = "车库名称不能为空";

    public static final int PARKING_GARAGE_GARAGE_NAME_MIN_LENGTH = 0;

    public static final int PARKING_GARAGE_GARAGE_NAME_MAX_LENGTH = 32;

    public static final String PARKING_GARAGE_GARAGE_NAME_LENGTH_RANGE = "请输入0~32个字符！不包含：V:*?\"<|'%>&";

    public static final String PARKING_GARAGE_NAME_ILLEGAL = "车库名称不合法";

    public static final String PARKING_GARAGE_NAME_USED = "车库名称被占用";

    public static final String PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_NULL = "车位总数不能为空";

    public static final String PARKING_PORT_NUMBER_NOT_NULL = "出入口数量不能为空";

    public static final String PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTFIXED = "固定车位数不能大于停车场固定车位数";

    public static final String PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT = "车位总数不能大于停车场车位总数";

    public static final String PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTCOUNT = "固定车位数不能大于车位总数";

    public static final String PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_GT_PARKING_LOTCOUNT = "剩余车位数不能大于车位总数";

    public static final String PARKING_GARAGE_GARAGE_LOTFIXED_NOT_NULL = "固定车位数不能为空";

    public static final String PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_NULL = "剩余车位数不能为空";

    public static final int PARKING_GARAGE_GARAGE_REMARK_MAX_LENGTH = 32;

    public static final String PARKING_GARAGE_GARAGE_REMARK_LENGTH_RANGE = "请输入0~32个字符！不包含：V:*?\"<|'%>&";

    public static final String PARKING_NOT_FOUND = "停车场不存在";

    public static final String PARKING_GARAGE_NOT_FOUND = "车库不存在";

    public static final String PARKING_GARAGE_ID_NOT_NULL = "车库ID不能为空";

    public static final String FLOOR_CODE_NOT_NULL = "楼层编号不能为空";

    public static final String FLOOR_NAME_NOT_NULL = "楼层名称不能为空";

    public static final String ID_NOT_NULL = "ID不能为空";

}
