package com.zoeeasy.cloud.pms.floor.cst;

/**
 * Created by song on 2019/3/26 9:52
 */
public final class FloorConstant {

    public static final int FLOOR_CODE_MIN_LENGTH = 2;

    public static final int FLOOR_CODE_MAX_LENGTH = 64;

    public static final String FLOOR_CODE_LENGTH_RANGE = "停车场楼层编号{min}-{max}个字符";

    public static final String FLOOR_CODE_PATTERN = "^[0-9|a-z|A-Z]{2,64}$";

    public static final String FLOOR_CODE_ILLEGAL = "楼层编号不合法";

    public static final int FLOOR_NAME_MIN_LENGTH = 1;

    public static final int FLOOR_NAME_MAX_LENGTH = 128;

    public static final String SPECIAL_CHARACTER_PATTERN = "^((?!V|:|\\*|\\?|'|<|\\||‘|%|>|&|\").)*$";

    public static final String FLOOR_NAME_LENGTH_RANGE = "楼层名称输入有误，请输入1~128个字符！不包含：V:*?\"<|'%>&";

    public static final int FLOOR_NUMBER_MIN_SIZE = 0;

    public static final int FLOOR_NUMBER_MAX_SIZE = 9999;

    public static final String LOT_COUNT_ERROR_MESSAGE = "车位总数输入有误，请输入1~9999的数字";

    public static final String LOT_FIXED_ERROR_MESSAGE = "固定车位数输入有误，请输入1~9999的数字";

    public static final String LOT_AVAILABLE_ERROR_MESSAGE = "剩余车位数输入有误，请输入1~9999的数字";

    private FloorConstant(){}

}
