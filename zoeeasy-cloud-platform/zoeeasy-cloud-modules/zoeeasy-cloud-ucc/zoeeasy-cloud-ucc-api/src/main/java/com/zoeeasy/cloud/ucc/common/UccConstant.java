package com.zoeeasy.cloud.ucc.common;

/**
 * UCC 模块常量定义
 *
 * @author walkman
 */
public final class UccConstant {

    private UccConstant() {
    }

    /**
     * 名称长度最小
     */
    public static final int ACCOUNT_NAME_MINI_LENGTH = 2;

    /**
     * 名称长度最大
     */
    public static final int ACCOUNT_NAME_MAX_LENGTH = 32;

    public static final String ACCOUNT_NAME_LENGTH_RANGE = "用户名长度必须{min}-{max}个字符";

    /**
     * 用户名正则表达式
     */
    public static final String ACCOUNT_NAME_PATTERN = "^(?![0-9]+$)(?!_)(?!.*?_$)[0-9A-Za-z_]{2,20}$";

    public static final String ACCOUNT_NAME_NOT_EMPTY = "用户名不能为空";

    public static final String ACCOUNT_NAME_ILLEGAL = "用户名不合法";

    public static final String ACCOUNT_NAME_EXIST = "用户名已存在";
    public static final String USER_ID_NOT_EMPTY = "用户ID不能为空";
    public static final String TENANT_ID_NOT_EMPTY = "租户ID不能为空";

    /**
     * 用户密码不能为空
     */
    public static final String ACCOUNT_AUTHENTICATION_NOT_EMPTY = "用户密码不能为空";

    /**
     * 用户密码正则
     */
    public static final String ACCOUNT_AUTHENTICATION_PATTERN = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{16,32}$";

    /**
     * 加密密码长度最小
     */
    public static final int ACCOUNT_AUTHENTICATION_MINI_LENGTH = 16;

    /**
     * 加密密码长度最大
     */
    public static final int ACCOUNT_AUTHENTICATION_MAX_LENGTH = 32;
    public static final String ACCOUNT_AUTHENTICATION_LENGTH_RANGE = "密码长度必须{min}-{max}个字符";

    public static final String ACCOUNT_AUTHENTICATION_ILLEGAL = "密码格式不正确";

    /**
     * 用户确认密码不能为空
     */
    public static final String ACCOUNT_CONFIRM_AUTHENTICATION_NOT_EMPTY = "用户确认密码不能为空";

    /**
     * 两次输入密码不一致
     */
    public static final String ACCOUNT_AUTHENTICATION_UNMATCH = "两次输入密码不一致";

    /**
     * 用户姓名不合法
     */
    public static final String USER_REAL_NAME_ILLEGAL = "用户姓名不合法";

    /**
     * 用户姓名不合法
     */
    public static final String USER_NICKNAME_ILLEGAL = "用户昵称不合法";

    /**
     * 用户ID为空
     */
    public static final String USER_ID_EMPTY = "用户ID为空";

    /**
     * 用户不存在
     */
    public static final String USER_NOT_FOUND = "用户不存在";

    public static final String USER_LOCKED = "用户已锁定";

    public static final String USER_NOT_LOCKED = "用户未锁定，无需解锁";

    public static final String USER_CANCELED = "用户已注销";

    public static final String USER_STATUS_INVALID = "用户状态无效";

    /**
     *
     */
    public static final String USER_OLD_AUTHENTICATION_NOT_EMPTY = "原密码不能为空";

    /**
     *
     */
    public static final String USER_PORTRAIT_URL_NOT_EMPTY = "用户头像不能为空";

    /**
     *
     */
    public static final String TENANT_ADMIN_USER_CANNOT_DELETED = "商户管理员不允许删除";

    /**
     *
     */
    public static final String TENANT_ADMIN_USER_CANNOT_LOCKED = "商户管理员不允许锁定";

    /**
     *
     */
    public static final String TENANT_ADMIN_USER_CANNOT_CLOSED = "商户管理员不允许注销";

    /**
     *
     */
    public static final String TENANT_ADMIN_USER_CANNOT_RESET_AUTHENTICATION = "商户管理员不允许重置密码";

    public static final String ACCOUNT_CANNOT_EMPTY = "账号不能为空";

}
