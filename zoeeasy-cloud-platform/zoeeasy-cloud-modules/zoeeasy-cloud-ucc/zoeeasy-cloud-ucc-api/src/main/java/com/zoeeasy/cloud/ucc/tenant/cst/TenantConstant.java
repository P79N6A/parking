package com.zoeeasy.cloud.ucc.tenant.cst;


/**
 * 租户相关常量定义
 *
 * @author walkman
 */
public final class TenantConstant {

    private TenantConstant() {
    }

    /**
     * 租户名称长度最小
     */
    public static final int TENANT_NAME_MINI_LENGTH = 2;

    /**
     * 租户名称长度最大
     */
    public static final int TENANT_NAME_MAX_LENGTH = 256;

    public static final String TENANT_NAME_NOT_EMPTY = "商户名称不能为空";

    public static final String TENANT_NAME_LENGTH_RANGE = "商户名称长度必须{min}-{max}个字符";

    /**
     * 租户名称正则(英文、字母、下划线、汉字、不包括特殊符号)
     */
    public static final String TENANT_NAME_PATTERN = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";

    public static final String TENANT_NAME_ILLEGAL = "商户名称不合法";

    public static final String TENANT_NAME_EXIST = "商户名称已存在";

    public static final String TENANT_NOT_FOUND = "商户不存在";

    public static final String TENANT_LOCKED = "商户已锁定";

    public static final String TENANT_NOT_LOCKED = "商户未锁定，无需解锁";

    public static final String TENANT_CANCELED = "商户已注销";

    public static final String TENANT_STATUS_INVALID = "商户状态无效";

    public static final String TENANT_ID_EMPTY = "商户ID为空";

}
