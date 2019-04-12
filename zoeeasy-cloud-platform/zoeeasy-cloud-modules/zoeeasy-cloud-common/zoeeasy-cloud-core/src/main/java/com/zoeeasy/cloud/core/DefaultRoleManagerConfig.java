package com.zoeeasy.cloud.core;

import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 默认的角色管理配置
 *
 * @author walkman
 */
public class DefaultRoleManagerConfig implements IRoleManagementConfig {

    @Getter
    @Setter
    private List<StaticRoleDefinition> staticRoles;

    public DefaultRoleManagerConfig() {
        this.staticRoles = new ArrayList<>();
    }

    /**
     * 获取宿主静态角色
     *
     * @return
     */
    @Override
    public List<StaticRoleDefinition> getHostStaticRoles() {
        if (CollectionUtils.isNotEmpty(staticRoles)) {
            return staticRoles.stream().filter(item -> item.getTenancyHostSide().equals(TenancyHostSide.Host)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 获取租户静态角色
     *
     * @return
     */
    @Override
    public List<StaticRoleDefinition> getTenantStaticRoles() {
        return staticRoles.stream().filter(item -> item.getTenancyHostSide().equals(TenancyHostSide.Tenant)).collect(Collectors.toList());
    }

}