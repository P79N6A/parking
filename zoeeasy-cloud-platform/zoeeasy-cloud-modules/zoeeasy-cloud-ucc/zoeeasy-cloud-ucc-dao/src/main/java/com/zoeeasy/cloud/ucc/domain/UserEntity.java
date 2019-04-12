package com.zoeeasy.cloud.ucc.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户表
 *
 * @author walkman
 * @since 2018-08-28
 */
@TableName("ucc_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends FullAuditedEntity<Long> {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * uuid
     */
    @TableField(value = "uuid")
    private String uuid;

    /**
     * 账户名
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 用户名
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 姓名
     */
    @TableField(value = "realName")
    private String realName;

    /**
     * 昵称
     */
    @TableField(value = "nickName")
    private String nickName;

    /**
     * 用户类型
     */
    @TableField(value = "userType")
    private Integer userType;

    /**
     * 是否是管理员用户
     */
    @TableField(value = "adminUser")
    private Boolean adminUser;

    /**
     * 用户的状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 用户注册渠道
     */
    @TableField(value = "registerChannel")
    private Integer registerChannel;

    /**
     * 手机
     */
    @TableField(value = "phoneNumber")
    private String phoneNumber;

    /**
     * 手机号是否确认
     */
    @TableField(value = "phoneNumberConfirmed")
    private Boolean phoneNumberConfirmed;

    /**
     * 邮箱
     */
    @TableField(value = "emailAddress")
    private String emailAddress;

    /**
     * 邮箱是否确认
     */
    @TableField(value = "emailAddressConfirmed")
    private Boolean emailAddressConfirmed;

    /**
     * 邮箱确认码
     */
    @TableField(value = "emailConfirmationCode")
    private String emailConfirmationCode;

    /**
     * 头像
     */
    @TableField(value = "portrait")
    private String portrait;

    /**
     * 失败登录次数
     */
    @TableField(value = "accessAttemptCount")
    private Integer accessAttemptCount;

    /**
     * 密码重置码
     */
    @TableField(value = "passwordResetCode")
    private String passwordResetCode;

    /**
     * 最后登录时间
     */
    @TableField(value = "lastLoginTime")
    private Date lastLoginTime;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    private Long lastModifierUserId;

    /**
     * 更新日期
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    private Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.UPDATE)
    private Long deleterUserId;

    /**
     * 删除日期
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    private Date deletionTime;

    /**
     * 删除标记（0：正常；1：删除 ）
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;
}