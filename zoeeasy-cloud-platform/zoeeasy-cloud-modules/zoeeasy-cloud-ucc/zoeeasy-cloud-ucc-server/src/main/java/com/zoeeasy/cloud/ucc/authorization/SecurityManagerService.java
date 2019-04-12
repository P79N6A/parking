package com.zoeeasy.cloud.ucc.authorization;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.core.UserIdentifier;
import com.zoeeasy.cloud.ucc.domain.*;
import com.zoeeasy.cloud.ucc.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("securityManagerService")
@Slf4j
public class SecurityManagerService {

    @Autowired
    private RoleCrudService roleCrudService;

    @Autowired
    private UserRoleCrudService userRoleCrudService;

    @Autowired
    private MenuCrudService menuCrudService;

    @Autowired
    private PermissionCrudService permissionCrudService;

    @Autowired
    private RolePermissionCrudService rolePermissionCrudService;

    @Autowired
    private RoleMenuCrudService roleMenuCrudService;

    /**
     * 获取用户所有角色
     *
     * @param userId
     * @return
     */
    public List<RoleEntity> getUserRoleList(Long userId) {
        List<UserRoleEntity> userRoleEntityList = this.userRoleCrudService.findByUserId(userId);
        if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
            List<Long> userRoleIds = userRoleEntityList.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
            return roleCrudService.selectBatchIds(userRoleIds);
        }
        return null;
    }

    /**
     * 获取角色所有菜单列表
     *
     * @param roleId
     * @return
     */
    public List<MenuEntity> getRoleMenuList(Long roleId) {

        List<RoleMenuEntity> roleMenuEntityList = this.roleMenuCrudService.findByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(roleMenuEntityList)) {
            List<Long> menuIds = roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId)
                    .collect(Collectors.toList());
            return this.menuCrudService.selectBatchIds(menuIds);
        }
        return null;
    }

    /**
     * 获取角色所有权限
     *
     * @param roleId
     * @return
     */
    public List<PermissionEntity> getRolePermissionList(Long roleId) {
        List<RolePermissionEntity> userRoleEntityList = this.rolePermissionCrudService.findByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
            List<Long> permissionIds = userRoleEntityList.stream().map(RolePermissionEntity::getPermissionId)
                    .collect(Collectors.toList());
            return permissionCrudService.selectBatchIds(permissionIds);
        }
        return null;
    }

    /**
     * 获取用户所有角色
     *
     * @param userIdentifier
     * @return
     */
    public List<RoleEntity> getUserRoleList(UserIdentifier userIdentifier) {
        EntityWrapper<UserRoleEntity> entityWrapper = new EntityWrapper<>();
        if (userIdentifier.getUserId() != null) {
            entityWrapper.eq("userId", userIdentifier.getUserId());
        }
        if (userIdentifier.getTenantId() != null) {
            entityWrapper.eq("tenantId", userIdentifier.getTenantId());
        }
        List<UserRoleEntity> userRoleEntityList = this.userRoleCrudService.findUserRoleListTenancyLess(entityWrapper);
        if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
            List<Long> userRoleIds = userRoleEntityList.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
            EntityWrapper<RoleEntity> roleEntityWrapper = new EntityWrapper<>();
            if (userIdentifier.getTenantId() != null) {
                roleEntityWrapper.eq("tenantId", userIdentifier.getTenantId());
            }
            if (CollectionUtils.isNotEmpty(userRoleIds)) {
                roleEntityWrapper.in("id", userRoleIds);
            }
            return roleCrudService.findRoleListTenancyLess(roleEntityWrapper);
        }
        return null;
    }

    /**
     * 获取用户所有权限
     *
     * @param userIdentifier
     * @return
     */
    public List<PermissionEntity> getUserPermissionList(UserIdentifier userIdentifier) {
        EntityWrapper<UserRoleEntity> entityWrapper = new EntityWrapper<>();
        if (userIdentifier.getUserId() != null) {
            entityWrapper.eq("userId", userIdentifier.getUserId());
        }
        if (userIdentifier.getTenantId() != null) {
            entityWrapper.eq("tenantId", userIdentifier.getTenantId());
        }
        List<UserRoleEntity> userRoleEntityList = this.userRoleCrudService.findUserRoleListTenancyLess(entityWrapper);
        if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
            //用户角色列表
            List<Long> userRoleIds = userRoleEntityList.stream().map(UserRoleEntity::getRoleId)
                    .collect(Collectors.toList());
            EntityWrapper<RolePermissionEntity> rolePermissionEntityWrapper = new EntityWrapper<>();
            if (userIdentifier.getTenantId() != null) {
                rolePermissionEntityWrapper.eq("tenantId", userIdentifier.getTenantId());
            }
            rolePermissionEntityWrapper.in("roleId", userRoleIds);

            List<RolePermissionEntity> rolePermissionEntityList = this.rolePermissionCrudService.findTenancyless(rolePermissionEntityWrapper);
            if (CollectionUtils.isNotEmpty(rolePermissionEntityList)) {
                //权限ID列表
                List<Long> permissionIds = rolePermissionEntityList.stream().map(RolePermissionEntity::getPermissionId)
                        .collect(Collectors.toList());
                return this.permissionCrudService.selectBatchIds(permissionIds);
            }
        }
        return null;
    }
}
