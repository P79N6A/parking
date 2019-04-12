package com.zoeeasy.cloud.ucc.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户角色表
 *
 * @author walkman
 * @since 2018-08-28
 */
@TableName("ucc_user_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleEntity extends CreationAuditedEntity<Long> {

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
     * 用户ID
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "roleId")
    private Long roleId;

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

}
