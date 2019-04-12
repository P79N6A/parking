package com.zoeeasy.cloud.ucc.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.zoeeasy.cloud.ucc.authorization.SecurityManagerService;
import com.zoeeasy.cloud.ucc.domain.*;
import com.zoeeasy.cloud.ucc.organization.dto.OrganizationResultDto;
import com.zoeeasy.cloud.ucc.role.RoleService;
import com.zoeeasy.cloud.ucc.role.dto.RoleLookupListGetRequestDto;
import com.zoeeasy.cloud.ucc.role.dto.request.*;
import com.zoeeasy.cloud.ucc.role.dto.result.*;
import com.zoeeasy.cloud.ucc.role.validator.RoleAuthorizeValidator;
import com.zoeeasy.cloud.ucc.role.validator.RoleCreateValidator;
import com.zoeeasy.cloud.ucc.role.validator.RoleDeleteValidator;
import com.zoeeasy.cloud.ucc.role.validator.RoleEditValidator;
import com.zoeeasy.cloud.ucc.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("roleService")
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleCrudService roleCrudService;

    @Autowired
    private RoleMenuCrudService roleMenuCrudService;

    @Autowired
    private RolePermissionCrudService rolePermissionCrudService;

    @Autowired
    private MenuCrudService menuCrudService;

    @Autowired
    private PermissionCrudService permissionCrudService;

    @Autowired
    private UserRoleCrudService userRoleCrudService;

    @Autowired
    private UserOrganizationCrudService userOrganizationCrudService;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private SecurityManagerService securityManagerService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取所有静态角色
     *
     * @return
     */
    @Override
    public ListResultDto<RoleResultDto> getStaticRoleList() {
        ListResultDto<RoleResultDto> resultDto = new ListResultDto<>();
        try {
            List<RoleEntity> roleList = this.roleCrudService.getAllStaticRole();
            if (roleList != null) {
                List<RoleResultDto> roleDtoList = modelMapper.map(roleList, new TypeToken<List<RoleResultDto>>() {
                }.getType());
                resultDto.setItems(roleDtoList);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取角色列表失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto createRole(@FluentValid(value = {RoleCreateValidator.class}) RoleCreateRequestDto requestDto) {
        ObjectResultDto<OrganizationResultDto> resultDto = new ObjectResultDto<>();
        try {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setCode(requestDto.getCode());
            roleEntity.setName(requestDto.getName());
            roleEntity.setRemarks(requestDto.getRemarks());
            roleEntity.setStaticRole(Boolean.FALSE);
            if (requestDto.getDefaultRole() != null) {
                roleEntity.setDefaultRole(requestDto.getDefaultRole());
            } else {
                roleEntity.setDefaultRole(Boolean.FALSE);
            }
            this.roleCrudService.insert(roleEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("角色创建失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto editRole(@FluentValid(value = {RoleEditValidator.class}) RoleEditRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(requestDto.getId());
            roleEntity.setName(requestDto.getName());
            roleEntity.setRemarks(requestDto.getRemarks());
            if (requestDto.getDefaultRole() != null) {
                roleEntity.setDefaultRole(requestDto.getDefaultRole());
            } else {
                roleEntity.setDefaultRole(Boolean.FALSE);
            }
            EntityWrapper<RoleEntity> entityEntityWrapper = new EntityWrapper<>();
            entityEntityWrapper.eq("id", requestDto.getId());
            this.roleCrudService.update(roleEntity, entityEntityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("角色编辑失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteRole(@FluentValid(value = {RoleDeleteValidator.class}) RoleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            this.roleCrudService.deleteById(requestDto.getId());
            //删除角色授予菜单
            this.roleMenuCrudService.deleteByRoleId(requestDto.getId());
            //删除角色授予权限
            this.rolePermissionCrudService.deleteByRoleId(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("角色删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ObjectResultDto<RoleResultDto> getRole(RoleGetRequestDto requestDto) {
        ObjectResultDto<RoleResultDto> resultDto = new ObjectResultDto<>();
        try {
            RoleEntity roleEntity = this.roleCrudService.selectById(requestDto.getId());
            if (roleEntity != null) {
                RoleResultDto tenantResultDto = this.modelMapper.map(roleEntity, RoleResultDto.class);
                resultDto.setData(tenantResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取角色详情失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ListResultDto<ComboboxItemDto> getRoleLookupList(RoleLookupListGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> resultDto = new PagedResultDto<>();
        try {
            EntityWrapper<RoleEntity> entityWrapper = new EntityWrapper<>();
            List<RoleEntity> roleList = this.roleCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(roleList)) {
                List<ComboboxItemDto> roleComboboxs = new ArrayList<>();
                for (RoleEntity roleEntity : roleList) {
                    ComboboxItemDto comboboxItemDto = new ComboboxItemDto();
                    comboboxItemDto.setValue(String.valueOf(roleEntity.getId()));
                    comboboxItemDto.setDisplayText(roleEntity.getName());
                    comboboxItemDto.setSelected(Boolean.FALSE);
                    roleComboboxs.add(comboboxItemDto);
                }
                resultDto.setItems(roleComboboxs);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取角色列表失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public PagedResultDto<RoleListResultDto> getRolePagedList(RolePagedRequestDto requestDto) {
        PagedResultDto<RoleListResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<RoleEntity> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getCode())) {
                entityWrapper.like("code", requestDto.getCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getName())) {
                entityWrapper.like("name", requestDto.getName());
            }
            Page<RoleEntity> roleEntityPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<RoleEntity> roleEntityPageList = this.roleCrudService.selectPage(roleEntityPage, entityWrapper);
            if (roleEntityPageList != null) {
                List<RoleListResultDto> roleDtoList = modelMapper.map(roleEntityPageList.getRecords(), new TypeToken<List<RoleListResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(roleEntityPageList.getCurrent());
                pagedResultDto.setPageSize(roleEntityPageList.getSize());
                pagedResultDto.setTotalCount(roleEntityPageList.getTotal());
                pagedResultDto.setItems(roleDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取角色列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取角色授予菜单树状视图
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<RoleMenuTreeItemResultDto> getRoleMenuTreeView(RoleMenuTreeViewGetRequestDto requestDto) {
        ListResultDto<RoleMenuTreeItemResultDto> listResultDto = new ListResultDto<>();
        try {
            List<RoleMenuTreeItemResultDto> roleMenus = new ArrayList<>();
            //获取角色已授予菜单列表
            List<MenuEntity> grantedMenus = this.securityManagerService.getRoleMenuList(requestDto.getRoleId());
            List<MenuEntity> allMenus = new ArrayList<>();
            SessionIdentity sessionIdentity = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();
            if (sessionIdentity != null && sessionIdentity.getMultiTenancySide() != null) {
                if (sessionIdentity.getMultiTenancySide().equals(TenancyHostSide.Host)) {
                    allMenus = menuCrudService.getAllHostSideStaticMenu();
                } else if (sessionIdentity.getMultiTenancySide().equals(TenancyHostSide.Tenant)) {
                    allMenus = menuCrudService.getAllTenancySideStaticMenu();
                }
            }
            if (CollectionUtils.isNotEmpty(allMenus)) {
                MenuEntity rootMenu = null;
                for (MenuEntity menuResultDto : allMenus) {
                    if (menuResultDto.getParentId() == null || menuResultDto.getParentId().equals(0L)) {
                        //找出所有的根节点
                        rootMenu = menuResultDto;
                    }
                }
                if (rootMenu != null) {
                    //为根节点设置子节点，getChild是递归调用的
                    /* 获取根节点下的所有子节点 使用getChild方法*/
                    roleMenus.addAll(getMenuChild(rootMenu.getId(), allMenus, grantedMenus));
                }
                listResultDto.setItems(roleMenus);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取菜单列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取角色授予权限树状视图
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<RolePermissionTreeItemResultDto> getRolePermissionTreeView(RolePermissionTreeViewGetRequestDto requestDto) {
        ListResultDto<RolePermissionTreeItemResultDto> listResultDto = new ListResultDto<>();
        try {
            List<RolePermissionTreeItemResultDto> rolePermissions = new ArrayList<>();
            //获取角色已授予权限列表
            List<PermissionEntity> grantedPermissions = this.securityManagerService.getRolePermissionList(requestDto.getRoleId());
            //所有可授予的权限
            List<PermissionEntity> allPermissions = new ArrayList<>();
            SessionIdentity sessionIdentity = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();
            if (sessionIdentity != null && sessionIdentity.getMultiTenancySide() != null) {
                EntityWrapper<PermissionEntity> entityWrapper = new EntityWrapper<>();
                if (CollectionUtils.isNotEmpty(requestDto.getMenus())) {
                    //添加根权限
                    requestDto.getMenus().add(0L);
                    requestDto.getMenus().add(1L);
                    entityWrapper.in("menuId", requestDto.getMenus());
                }
                if (sessionIdentity.getMultiTenancySide().equals(TenancyHostSide.Host)) {
                    allPermissions = this.permissionCrudService.findHostSideStaticPermission(entityWrapper);
                } else if (sessionIdentity.getMultiTenancySide().equals(TenancyHostSide.Tenant)) {
                    allPermissions = this.permissionCrudService.findTenancySideStaticPermission(entityWrapper);
                }
            }
            if (CollectionUtils.isNotEmpty(allPermissions)) {
                PermissionEntity rootPermission = null;
                for (PermissionEntity permissionEntity : allPermissions) {
                    if (permissionEntity.getParentId() == null || permissionEntity.getParentId().equals(0L)) {
                        //找出所有的根节点
                        rootPermission = permissionEntity;
                    }
                }
                if (rootPermission != null) {
                    //为根节点设置子节点，getChild是递归调用的
                    /* 获取根节点下的所有子节点 使用getChild方法*/
                    rolePermissions.addAll(getPermissionChild(rootPermission.getId(), allPermissions, grantedPermissions));
                }
                listResultDto.setItems(rolePermissions);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取权限列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 角色授权
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto authorize(@FluentValid(value = RoleAuthorizeValidator.class) RoleAuthorizeRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //已授权菜单列表
            List<RoleMenuEntity> roleMenuEntityList = this.roleMenuCrudService.findByRoleId(requestDto.getRoleId());
            //已授权的角色菜单列表
            List<Long> roleMenuIdListGranted = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(roleMenuEntityList)) {
                roleMenuIdListGranted = roleMenuEntityList.stream().
                        map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
            }
            List<Long> roleMenuIdList = requestDto.getMenus();
            //待删除的角色授权列表
            List<Long> roleMenuEntityListToDelete = new ArrayList<>();
            //待新增授权的角色菜单列表
            List<RoleMenuEntity> roleMenuEntityListToAdd = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(roleMenuIdListGranted)) {
                //如果已授权菜单列表不为空
                //如果待授权菜单列表为空
                if (CollectionUtils.isEmpty(roleMenuIdList)) {
                    roleMenuEntityListToDelete = roleMenuIdListGranted;
                } else {
                    //计算待新增和待授权的
                    //遍历已授权的，如果在授权菜单列表中则不做操作，否则移除
                    for (Long menuId : roleMenuIdListGranted) {
                        if (!roleMenuIdList.contains(menuId)) {
                            roleMenuEntityListToDelete.add(menuId);
                        }
                    }
                    //遍历待授权的，如果在已授权菜单列表中则不做操作，否则添加
                    for (Long menuId : roleMenuIdList) {
                        if (!roleMenuIdListGranted.contains(menuId)) {
                            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                            roleMenuEntity.setRoleId(requestDto.getRoleId());
                            roleMenuEntity.setMenuId(menuId);
                            roleMenuEntityListToAdd.add(roleMenuEntity);
                        }
                    }
                }
            } else {
                //如果已授权菜单列表为空
                if (CollectionUtils.isNotEmpty(roleMenuIdList)) {
                    //如果待授权菜单列表非空
                    for (Long menuId : requestDto.getMenus()) {
                        RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                        roleMenuEntity.setRoleId(requestDto.getRoleId());
                        roleMenuEntity.setMenuId(menuId);
                        roleMenuEntityListToAdd.add(roleMenuEntity);
                    }
                }
                //已授权列表和待授权列表均为空,NOP
            }
            if (CollectionUtils.isNotEmpty(roleMenuEntityListToDelete)) {
                this.roleMenuCrudService.deleteByRoleMenuList(requestDto.getRoleId(), roleMenuEntityListToDelete);
            }
            if (CollectionUtils.isNotEmpty(roleMenuEntityListToAdd)) {
                this.roleMenuCrudService.insertBatch(roleMenuEntityListToAdd);
            }

            //已授权权限列表
            List<RolePermissionEntity> rolePermissionEntityList = this.rolePermissionCrudService.findByRoleId(requestDto.getRoleId());
            //已授权的角色权限列表
            List<Long> rolePermissionIdListGranted = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(rolePermissionEntityList)) {
                rolePermissionIdListGranted = rolePermissionEntityList.stream().
                        map(RolePermissionEntity::getPermissionId).collect(Collectors.toList());
            }
            List<Long> rolePermissionIdList = requestDto.getPermissions();
            //待删除的角色授权列表
            List<Long> rolePermissionEntityListToDelete = new ArrayList<>();
            //待新增授权的角色权限列表
            List<RolePermissionEntity> rolePermissionEntityListToAdd = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(rolePermissionIdListGranted)) {
                //如果已授权权限列表不为空
                //如果待授权权限列表为空
                if (CollectionUtils.isEmpty(rolePermissionIdList)) {
                    rolePermissionEntityListToDelete = rolePermissionIdListGranted;
                } else {
                    //计算待新增和待授权的
                    //遍历已授权的，如果在授权权限列表中则不做操作，否则移除
                    for (Long permissionId : rolePermissionIdListGranted) {
                        if (!rolePermissionIdList.contains(permissionId)) {
                            rolePermissionEntityListToDelete.add(permissionId);
                        }
                    }
                    //遍历待授权的，如果在已授权权限列表中则不做操作，否则添加
                    for (Long permissionId : rolePermissionIdList) {
                        if (!rolePermissionIdListGranted.contains(permissionId)) {
                            RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
                            rolePermissionEntity.setRoleId(requestDto.getRoleId());
                            rolePermissionEntity.setPermissionId(permissionId);
                            rolePermissionEntityListToAdd.add(rolePermissionEntity);
                        }
                    }
                }
            } else {
                //如果已授权权限列表为空
                if (CollectionUtils.isNotEmpty(rolePermissionIdList)) {
                    //如果待授权权限列表非空
                    for (Long menuId : requestDto.getPermissions()) {
                        RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
                        rolePermissionEntity.setRoleId(requestDto.getRoleId());
                        rolePermissionEntity.setPermissionId(menuId);
                        rolePermissionEntityListToAdd.add(rolePermissionEntity);
                    }
                }
                //已授权列表和待授权列表均为空,NOP
            }
            if (CollectionUtils.isNotEmpty(rolePermissionEntityListToDelete)) {
                this.rolePermissionCrudService.deleteByRolePermissionList(requestDto.getRoleId(), rolePermissionEntityListToDelete);
            }
            if (CollectionUtils.isNotEmpty(rolePermissionEntityListToAdd)) {
                this.rolePermissionCrudService.insertBatch(rolePermissionEntityListToAdd);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("角色授权失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取角色授予用户列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<RoleMemberListResultDto> getRoleMemberList(RoleMemberListGetRequestDto requestDto) {
        PagedResultDto<RoleMemberListResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            //用户ID列表
            List<Long> userIdList = new ArrayList<>();
            List<UserRoleEntity> userRoleEntityList = userRoleCrudService.findByRoleId(requestDto.getRoleId());
            if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
                userIdList = userRoleEntityList.stream().map(UserRoleEntity::getUserId).collect(Collectors.toList());
            }
            EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getOrganizationId() != null) {
                EntityWrapper<UserOrganizationEntity> userOrganizationEw = new EntityWrapper<>();
                userOrganizationEw.eq("organizationId", requestDto.getOrganizationId());
                if (CollectionUtils.isNotEmpty(userIdList)) {
                    userOrganizationEw.in("userId", userIdList);
                }
                List<UserOrganizationEntity> userOrganizationEntityList = userOrganizationCrudService.selectList(userOrganizationEw);
                if (CollectionUtils.isNotEmpty(userOrganizationEntityList)) {
                    List<Long> userIdListOrganization = userOrganizationEntityList.stream().
                            map(UserOrganizationEntity::getUserId).collect(Collectors.toList());
                    userIdList.retainAll(userIdListOrganization);
                } else {
                    //该部门先没有用户
                    userIdList = new ArrayList<>();
                }
            }
            if (CollectionUtils.isNotEmpty(userIdList)) {

                entityWrapper.in("id", userIdList);
                if (StringUtils.isNotEmpty(requestDto.getUserName())) {
                    entityWrapper.eq("userName", requestDto.getUserName());
                }
                Page<UserEntity> roleEntityPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
                Page<UserEntity> roleEntityPageList = this.userCrudService.selectPage(roleEntityPage, entityWrapper);
                if (roleEntityPageList != null) {
                    List<RoleMemberListResultDto> roleDtoList = modelMapper.map(roleEntityPageList.getRecords(), new TypeToken<List<RoleMemberListResultDto>>() {
                    }.getType());
                    pagedResultDto.setPageNo(roleEntityPageList.getCurrent());
                    pagedResultDto.setPageSize(roleEntityPageList.getSize());
                    pagedResultDto.setTotalCount(roleEntityPageList.getTotal());
                    pagedResultDto.setItems(roleDtoList);
                }
            } else {
                //用户不存在
                pagedResultDto.setPageNo(requestDto.getPageNo());
                pagedResultDto.setPageSize(requestDto.getPageSize());
                pagedResultDto.setTotalCount(0L);
                pagedResultDto.setItems(null);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取角色成员失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取子节点
     *
     * @param parentId     父节点id
     * @param allMenus     所有菜单列表
     * @param grantedMenus 已授权菜单列表
     * @return 每个根节点下，所有菜单列表
     */
    private List<RoleMenuTreeItemResultDto> getMenuChild(Long parentId, List<MenuEntity> allMenus, List<MenuEntity> grantedMenus) {
        //子菜单
        List<RoleMenuTreeItemResultDto> childList = new ArrayList<>();
        for (MenuEntity menuEntity : allMenus) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点
            if (menuEntity.getParentId().equals(parentId)) {
                RoleMenuTreeItemResultDto menuItem = new RoleMenuTreeItemResultDto();
                menuItem.setMenuId(menuEntity.getId());
                menuItem.setCode(menuEntity.getCode());
                menuItem.setName(menuEntity.getName());
                menuItem.setParentId(parentId);
                menuItem.setPathCode(menuEntity.getPathCode());
                //是否授予
                if (CollectionUtils.isNotEmpty(grantedMenus) &&
                        grantedMenus.stream().anyMatch(item ->
                                item.getId().equals(menuEntity.getId())
                        )) {
                    menuItem.setGranted(Boolean.TRUE);
                } else {
                    menuItem.setGranted(Boolean.FALSE);
                }
                childList.add(menuItem);
            }
        }
        //递归
        for (RoleMenuTreeItemResultDto menuItemDefinition : childList) {
            menuItemDefinition.setChildren(getMenuChild(menuItemDefinition.getMenuId(), allMenus, grantedMenus));
        }
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (CollectionUtils.isEmpty(childList)) {
            return new ArrayList<>();
        }
        //排序
        Collections.sort(childList, sortByPathCode());
        return childList;
    }

    /**
     * 获取子节点
     *
     * @param parentId           父节点id
     * @param allPermissions     所有菜单列表
     * @param grantedPermissions 已授权菜单列表
     * @return 每个根节点下，所有菜单列表
     */
    private List<RolePermissionTreeItemResultDto> getPermissionChild(Long parentId, List<PermissionEntity> allPermissions, List<PermissionEntity> grantedPermissions) {
        //子权限
        List<RolePermissionTreeItemResultDto> childList = new ArrayList<>();
        for (PermissionEntity permissionEntity : allPermissions) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点
            if (permissionEntity.getParentId().equals(parentId)) {
                RolePermissionTreeItemResultDto permissionItem = new RolePermissionTreeItemResultDto();
                permissionItem.setPermissionId(permissionEntity.getId());
                permissionItem.setCode(permissionEntity.getCode());
                permissionItem.setName(permissionEntity.getName());
                permissionItem.setParentId(parentId);
                //是否授予
                if (CollectionUtils.isNotEmpty(grantedPermissions) &&
                        grantedPermissions.stream().anyMatch(item ->
                                item.getId().equals(permissionItem.getPermissionId())
                        )) {
                    permissionItem.setGranted(Boolean.TRUE);
                } else {
                    permissionItem.setGranted(Boolean.FALSE);
                }
                childList.add(permissionItem);
            }
        }
        //递归
        for (RolePermissionTreeItemResultDto menuItemDefinition : childList) {
            menuItemDefinition.setChildren(getPermissionChild(menuItemDefinition.getPermissionId(), allPermissions, grantedPermissions));
        }
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (CollectionUtils.isEmpty(childList)) {
            return new ArrayList<>();
        }
        //排序
        return childList;
    }

    /*
     * 排序,根据order排序
     */
    private Comparator<RoleMenuTreeItemResultDto> sortByPathCode() {

        return new Comparator<RoleMenuTreeItemResultDto>() {

            /**
             *
             * @param menuItem1 menuItem1
             * @param menuItem2 menuItem2
             * @return
             */
            @Override
            public int compare(RoleMenuTreeItemResultDto menuItem1, RoleMenuTreeItemResultDto menuItem2) {
                String pathCode1 = menuItem1.getPathCode();
                String pathCode2 = menuItem2.getPathCode();
                int result = 0;
                if (StringUtils.isNotEmpty(pathCode1) && StringUtils.isNotEmpty(pathCode2)) {
                    result = pathCode1.compareTo(pathCode2);
                } else {
                    if (StringUtils.isEmpty(pathCode1) && StringUtils.isNotEmpty(pathCode2)) {
                        result = -1;
                    } else if (StringUtils.isNotEmpty(pathCode2) && StringUtils.isEmpty(pathCode2)) {
                        result = 1;
                    }
                }
                return result;
            }
        };
    }
}
