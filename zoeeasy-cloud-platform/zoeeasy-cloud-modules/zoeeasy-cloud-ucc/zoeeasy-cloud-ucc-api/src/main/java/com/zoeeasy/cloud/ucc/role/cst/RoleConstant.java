package com.zoeeasy.cloud.ucc.role.cst;

/**
 * 角色相关常量定义
 *
 * @author walkman
 */
public class RoleConstant {

    private RoleConstant() {
    }

    /**
     * 角色编号长度最小
     */
    public static final int ROLE_CODE_MIN_LENGTH = 6;

    /**
     * 角色编号长度最大
     */
    public static final int ROLE_CODE_MAX_LENGTH = 32;

    public static final String ROLE_CODE_EXIST = "角色编号已存在";

    public static final String ROLE_CODE_NOT_EMPTY = "角色编号不能为空";

    public static final String ROLE_CODE_LENGTH_RANGE = "角色编号长度必须{min}-{max}个字符";

    /**
     * 角色编号正则表达式
     */
    public static final String ROLE_CODE_PATTERN = "^[0-9A-Za-z]{6,32}$";

    public static final String ROLE_CODE_ILLEGAL = "角色编码不合法";

    /**
     * 角色名称长度最小
     */
    public static final int ROLE_NAME_MINI_LENGTH = 2;

    /**
     * 角色名称长度最大
     */
    public static final int ROLE_NAME_MAX_LENGTH = 256;

    public static final String ROLE_NAME_NOT_EMPTY = "角色名称不能为空";

    public static final String ROLE_NAME_LENGTH_RANGE = "角色名称长度必须{min}-{max}个字符";

    public static final String ROLE_NAME_EXIST = "角色名称已存在";

    /**
     * 租户名称正则(英文、字母、下划线、汉字、不能以下划线开始和结束)
     */
    public static final String ROLE_NAME_PATTERN = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";

    public static final String ROLE_NAME_ILLEGAL = "角色名称不合法";

    /**
     * 静态角色不允许修改
     */
    public static final String ROLE_STATIC_NOT_TO_EDIT = "静态角色不允许修改";

    /**
     * 静态角色不允许删除
     */
    public static final String ROLE_STATIC_NOT_TO_DELETE = "静态角色不允许删除";

    /**
     * 角色ID不能为空
     */
    public static final String ROLE_ID_EMPTY = "角色ID不能为空";

    /**
     * 角色不存在
     */
    public static final String ROLE_NOT_EXIST = "角色不存在";

    /**
     * 角色不存在
     */
    public static final String ROLE_AUTHORIZED_CANNOT_DELETED = "角色已授权，不允许删除";

    /**
     *
     */
    public static final String ROLE_ADMIN_CAN_NOT_AUTHORIZE = "系统管理员角色不允许授权";
}
