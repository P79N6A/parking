package com.zoeeasy.cloud.core;

import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 静态角色定义
 *
 * @author walkman
 * @since 2018-10-10
 */
@Data
@AllArgsConstructor
public class StaticRoleDefinition {

    private Long id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 是否默认角色
     */
    private Boolean defaultRole;

    private TenancyHostSide tenancyHostSide;

}
