package com.zoeeasy.cloud.sys.parameter.cst;

/**
 * Created by zwq on 2018/12/15.
 */
public final class ParamConstant {

    private ParamConstant() {
    }

    public static final int MIN = 1;
    public static final int MAX = 32;
    public static final String CODE_REGEXP = "^((?!V|:|\\*|\\?|\"|<|\\||'|%|>|&).)*$";
    public static final String PARAM_KEY_ILLEGAL = "参数名输入有误,请输入1~32个字符！不包含：V:*?\"<|'%>&";
    public static final String PARAM_REMARK_ILLEGAL = "备注输入有误,请输入0~32个字符！不包含：V:*?\"<|'%>&";

    public static final String PARAM_CODE_NOT_EMPTY = "参数编码不能为空";
    public static final String PARAM_ITEM_ID_NOT_EMPTY = "参数项ID不能为空";
    public static final String PARAM_KEY_NOT_EMPTY = "参数项KEY不能为空";
    public static final String PARAM_VALUE_NOT_EMPTY = "参数项值不能为空";

}
