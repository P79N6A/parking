package com.zoeeasy.cloud.ucc.organization.cst;

/**
 * 部门相关常量定义
 *
 * @author walkman
 */
public class OrganizationConstant {

    /**
     * 部门名称长度最小
     */
    public static final int ORGANIZATION_NAME_MINI_LENGTH = 2;

    /**
     * 部门名称长度最大
     */
    public static final int ORGANIZATION_NAME_MAX_LENGTH = 256;

    public static final String ORGANIZATION_NAME_NOT_EMPTY = "部门名称不能为空";

    /**
     * 部门名称正则(英文、字母、下划线、汉字、不包括特殊符号)
     */
    public static final String ORGANIZATION_NAME_PATTERN = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";

    public static final String ORGANIZATION_NAME_LENGTH_RANGE = "部门名称长度必须{min}-{max}个字符";

    public static final String ORGANIZATION_NAME_EXIST = "部门名称已存在";

    public static final String ORGANIZATION_NAME_ILEGAL = "部门名称不合法";

    /**
     * 上级部门不存在
     */
    public static final String ORGANIZATION_PARENT_NOT_EXIST = "上级部门不存在";

    /**
     * 部门不存在
     */
    public static final String ORGANIZATION_NOT_EXIST = "部门不存在";

    /**
     * 部门不空
     */
    public static final String ORGANIZATION_CHILDERE_EXIST = "部门下面存在子部门，请先删除子部门";
    /**
     * 部门不空
     */
    public static final String ORGANIZATION_USER_EXIST = "部门下面存在子用户，不允许删除";

}
