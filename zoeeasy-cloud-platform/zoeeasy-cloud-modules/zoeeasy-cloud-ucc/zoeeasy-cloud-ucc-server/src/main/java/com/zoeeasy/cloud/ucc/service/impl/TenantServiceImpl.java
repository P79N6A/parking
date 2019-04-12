package com.zoeeasy.cloud.ucc.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.sequence.Sequence;
import com.scapegoat.infrastructure.shiro.service.SaltGenerator;
import com.scapegoat.infrastructure.shiro.service.ShiroCryptoService;
import com.zoeeasy.cloud.core.StaticRoles;
import com.zoeeasy.cloud.core.utils.TreeUtils;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import com.zoeeasy.cloud.ucc.domain.*;
import com.zoeeasy.cloud.ucc.enums.*;
import com.zoeeasy.cloud.ucc.service.*;
import com.zoeeasy.cloud.ucc.tenant.TenantCodeGenerator;
import com.zoeeasy.cloud.ucc.tenant.TenantService;
import com.zoeeasy.cloud.ucc.tenant.cst.TenantConstant;
import com.zoeeasy.cloud.ucc.tenant.dto.request.*;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantAccountCheckResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantCheckResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantListResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantResultDto;
import com.zoeeasy.cloud.ucc.tenant.validator.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 租户管理服务
 *
 * @author walkman
 * @since 2018-08-28
 */
@Service("tenantService")
@Slf4j
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantCrudService tenantCrudService;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private OrganizationCrudService organizationCrudService;

    @Autowired
    private UserOrganizationCrudService userOrganizationCrudService;

    @Autowired
    private RoleCrudService roleCrudService;

    @Autowired
    private UserRoleCrudService userRoleCrudService;

    @Autowired
    private MenuCrudService menuCrudService;

    @Autowired
    private RoleMenuCrudService roleMenuCrudService;

    @Autowired
    private PermissionCrudService permissionCrudService;

    @Autowired
    private RolePermissionCrudService rolePermissionCrudService;

    @Autowired
    private TenantCodeGenerator tenantCodeGenerator;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SaltGenerator saltGenerator;

    @Autowired
    private ShiroCryptoService shiroCryptoService;

    private Sequence sequence = new Sequence(0, 0);

    /**
     * 检查租户名称是否可用
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<TenantCheckResultDto> checkTenant(TenantCheckRequestDto requestDto) {
        ObjectResultDto<TenantCheckResultDto> resultDto = new ObjectResultDto<>();
        try {
            TenantCheckResultDto checkResultDto = new TenantCheckResultDto();
            TenantEntity tenantEntity = tenantCrudService.findByTenantName(requestDto.getName());
            if (tenantEntity != null) {
                checkResultDto.setAvailable(Boolean.FALSE);
                checkResultDto.setMessage(TenantConstant.TENANT_NAME_EXIST);
            } else {
                checkResultDto.setAvailable(Boolean.TRUE);
            }
            resultDto.setData(checkResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("商户校验失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 检查租户管理员是否可用
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<TenantAccountCheckResultDto> checkTenantAccount(TenantAccountCheckRequestDto requestDto) {
        ObjectResultDto<TenantAccountCheckResultDto> resultDto = new ObjectResultDto<>();
        try {
            TenantAccountCheckResultDto accountCheckResultDto = new TenantAccountCheckResultDto();
            UserEntity userEntity = userCrudService.getUserByAccount(requestDto.getAdminAccount());
            if (userEntity != null) {
                accountCheckResultDto.setAvailable(Boolean.FALSE);
                accountCheckResultDto.setMessage(UccConstant.ACCOUNT_NAME_EXIST);
            } else {
                accountCheckResultDto.setAvailable(Boolean.TRUE);
            }
            resultDto.setData(accountCheckResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("管理员用户名校验失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 创建租户
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto createTenant(@FluentValid({TenantCreateValidator.class}) TenantCreateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //0.创建租户
            TenantEntity tenantEntity = createTenantEntity(requestDto);
            this.tenantCrudService.insert(tenantEntity);

            TenantEntity newTenantEntity = tenantCrudService.findByTenantCode(tenantEntity.getCode());
            if (newTenantEntity != null) {
                // 修改租户
                TenantEntity updateTenantEntity = new TenantEntity();
                updateTenantEntity.setId(newTenantEntity.getId());

                //1、创建默认组织结构
                OrganizationEntity organizationEntity = createTenantOrganization(requestDto.getName());
                organizationEntity.setTenantId(newTenantEntity.getId());
                this.organizationCrudService.insert(organizationEntity);
                //2、创建租户系统管理员
                UserEntity tenantAdministrator = createTenantAdministrator(requestDto.getAdminAccount(), requestDto.getAdminPassword());
                tenantAdministrator.setTenantId(newTenantEntity.getId());
                this.userCrudService.insert(tenantAdministrator);
                updateTenantEntity.setAdminUserId(tenantAdministrator.getId());
                //6、添加系统管理员至默认组织架构
                UserOrganizationEntity userOrganizationEntity = new UserOrganizationEntity();
                userOrganizationEntity.setOrganizationId(organizationEntity.getId());
                userOrganizationEntity.setTenantId(newTenantEntity.getId());
                userOrganizationEntity.setUserId(tenantAdministrator.getId());
                this.userOrganizationCrudService.insert(userOrganizationEntity);
                //3、租户侧静态角色分配
                List<RoleEntity> tenancyStaticRole = this.roleCrudService.getAllTenancySideStaticRole();
                List<RoleEntity> roleEntityList = new ArrayList<>();
                List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
                //商户管理员角色
                RoleEntity tenantAdminRole = null;
                for (RoleEntity role : tenancyStaticRole) {
                    RoleEntity roleEntity = new RoleEntity();
                    roleEntity.setTenantId(newTenantEntity.getId());
                    roleEntity.setCode(role.getCode());
                    roleEntity.setName(role.getName());
                    roleEntity.setDefaultRole(role.getDefaultRole());
                    roleEntity.setStaticRole(role.getStaticRole());
                    if (StaticRoles.Tenants.ADMIN.equals(role.getCode())) {
                        //如果是超级管理员角色，则分配超级管理员角色
                        roleEntity.setAdminRole(Boolean.TRUE);
                        tenantAdminRole = roleEntity;
                    } else {
                        roleEntityList.add(roleEntity);
                    }
                }
                //默认管理员角色菜单、权限授权
                List<RoleMenuEntity> roleMenuEntityList = new ArrayList<>();
                List<RolePermissionEntity> rolePermissionEntityList = new ArrayList<>();
                if (tenantAdminRole != null) {
                    this.roleCrudService.insert(tenantAdminRole);
                    //如果是超级管理员角色，则分配超级管理员角色
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setTenantId(newTenantEntity.getId());
                    userRoleEntity.setRoleId(tenantAdminRole.getId());
                    userRoleEntity.setUserId(tenantAdministrator.getId());
                    userRoleEntityList.add(userRoleEntity);
                    //所有租户端菜单
                    List<MenuEntity> tenancySideMenus = this.menuCrudService.getAllTenancySideStaticMenu();
                    if (CollectionUtils.isNotEmpty(tenancySideMenus)) {
                        for (MenuEntity menuEntity : tenancySideMenus) {
                            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                            roleMenuEntity.setTenantId(newTenantEntity.getId());
                            roleMenuEntity.setRoleId(tenantAdminRole.getId());
                            roleMenuEntity.setMenuId(menuEntity.getId());
                            roleMenuEntityList.add(roleMenuEntity);
                        }
                    }
                    //所有租户端权限
                    List<PermissionEntity> tenancySidePermissions = this.permissionCrudService.getAllTenancySideStaticPermission();
                    if (CollectionUtils.isNotEmpty(tenancySidePermissions)) {
                        for (PermissionEntity permissionEntity : tenancySidePermissions) {
                            RolePermissionEntity roleMenuEntity = new RolePermissionEntity();
                            roleMenuEntity.setTenantId(newTenantEntity.getId());
                            roleMenuEntity.setRoleId(tenantAdminRole.getId());
                            roleMenuEntity.setPermissionId(permissionEntity.getId());
                            rolePermissionEntityList.add(roleMenuEntity);
                        }
                    }
                }
                //默认租户端角色创建
                if (CollectionUtils.isNotEmpty(roleEntityList)) {
                    this.roleCrudService.insertBatch(roleEntityList);
                }
                //超级管理员角色菜单创建
                if (CollectionUtils.isNotEmpty(roleMenuEntityList)) {
                    this.roleMenuCrudService.insertBatch(roleMenuEntityList);
                }
                //超级管理员角色权限创建
                if (CollectionUtils.isNotEmpty(rolePermissionEntityList)) {
                    this.rolePermissionCrudService.insertBatch(rolePermissionEntityList);
                }
                //7、系统管理员分配角色及权限
                if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
                    this.userRoleCrudService.insertBatch(userRoleEntityList);
                }
                //修改租户
                EntityWrapper<TenantEntity> entityEntityWrapper = new EntityWrapper<>();
                entityEntityWrapper.eq("id", newTenantEntity.getId());
                this.tenantCrudService.update(updateTenantEntity, entityEntityWrapper);
            }
            return resultDto.success();
        } catch (Exception e) {
            log.error("租户创建失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 创建租户
     *
     * @param requestDto
     * @return
     */
    private TenantEntity createTenantEntity(TenantCreateRequestDto requestDto) {
        TenantEntity tenantEntity = new TenantEntity();
        tenantEntity.setCode(tenantCodeGenerator.generateCode(requestDto.getName()));
        tenantEntity.setName(requestDto.getName());
        tenantEntity.setDescription(requestDto.getDescription());
        tenantEntity.setDomain(requestDto.getDomain());
        tenantEntity.setUrl(requestDto.getUrl());
        tenantEntity.setLogo(requestDto.getLogo());
        //管理员用户ID先置为0
        tenantEntity.setAdminUserId(0L);
        //类型
        if (requestDto.getType() == null) {
            tenantEntity.setType(TenantTypeEnum.SINGLE.getValue());
        } else {
            tenantEntity.setType(requestDto.getType());
        }
        //状态
        tenantEntity.setStatus(TenantStatusEnum.ENABLED.getValue());
        tenantEntity.setDeleted(Integer.valueOf(TenantEntity.SOFT_DELETE_FLAG_NORMAL));
        return tenantEntity;
    }

    /**
     * 创建租户默认组织架构
     *
     * @param name
     * @return name
     */
    private OrganizationEntity createTenantOrganization(String name) {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setCode(sequence.nextId().toString());
        organizationEntity.setName(name);
        organizationEntity.setParentId(0L);
        organizationEntity.setPathCode(TreeUtils.createCode(1));
        return organizationEntity;
    }

    /**
     * 创建租户默认管理员
     *
     * @param account  account
     * @param password password
     * @return
     */
    private UserEntity createTenantAdministrator(String account, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(StringUtils.getUUID());
        userEntity.setAccount(account);
        userEntity.setUserName(account);
        userEntity.setAccessAttemptCount(0);
        userEntity.setEmailAddress("");
        userEntity.setEmailAddressConfirmed(Boolean.FALSE);
        //新密码处理
        //生产随机密码盐
        String salt = saltGenerator.generate();
        String encryptPassword = shiroCryptoService.password(password, account + salt);
        userEntity.setPassword(encryptPassword);
        userEntity.setSalt(salt);
        userEntity.setUserType(UserTypeEnum.TENANT_ADMIN.getValue());
        userEntity.setAdminUser(Boolean.TRUE);
        userEntity.setRegisterChannel(UserRegisterChannelEnum.PLATFORM.getValue());
        userEntity.setStatus(UserStatusEnum.ENABLED.getValue());
        return userEntity;
    }

    @Override
    public PagedResultDto<TenantListResultDto> getTenantList(TenantPagedListRequestDto requestDto) {
        PagedResultDto<TenantListResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<TenantEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.like("name", requestDto.getName());
            }
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.like("code", requestDto.getCode());
            }
            //管理员账号
            if (StringUtils.isNotEmpty(requestDto.getAdminAccount())) {
                UserEntity userEntity = this.userCrudService.getUserByAccount(requestDto.getAdminAccount());
                if (userEntity != null) {
                    entityWrapper.eq("adminUserId", userEntity.getId());
                }
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            if (requestDto.getType() != null) {
                entityWrapper.eq("type", requestDto.getType());
            }
            Page<TenantEntity> tenantEntityPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<TenantEntity> tenantEntityPageList = this.tenantCrudService.selectPage(tenantEntityPage, entityWrapper);
            if (tenantEntityPageList != null) {
                List<TenantListResultDto> tenantDtoList = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(tenantEntityPageList.getRecords())) {
                    for (TenantEntity tenantEntity : tenantEntityPageList.getRecords()) {
                        //获取管理员账号信息
                        TenantListResultDto tenantListResultDto = this.modelMapper.map(tenantEntity, TenantListResultDto.class);
                        UserEntity userEntity = this.userCrudService.finByUserId(tenantEntity.getAdminUserId());
                        if (userEntity != null) {
                            tenantListResultDto.setAdminAccount(userEntity.getAccount());
                        }
                        tenantDtoList.add(tenantListResultDto);
                    }
                }
                pagedResultDto.setPageNo(tenantEntityPageList.getCurrent());
                pagedResultDto.setPageSize(tenantEntityPageList.getSize());
                pagedResultDto.setTotalCount(tenantEntityPageList.getTotal());
                pagedResultDto.setItems(tenantDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取租户列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto.success();
    }

    @Override
    public ObjectResultDto<TenantResultDto> getTenant(TenantGetRequestDto requestDto) {
        ObjectResultDto<TenantResultDto> resultDto = new ObjectResultDto<>();
        try {
            TenantEntity tenantEntity = this.tenantCrudService.selectById(requestDto.getId());
            if (tenantEntity != null) {
                TenantResultDto tenantResultDto = this.modelMapper.map(tenantEntity, TenantResultDto.class);
                //获取管理员账号信息
                UserEntity userEntity = this.userCrudService.finByUserId(tenantEntity.getAdminUserId());
                if (userEntity != null) {
                    tenantResultDto.setAdminAccount(userEntity.getAccount());
                }
                resultDto.setData(tenantResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取租户详情失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto editTenant(@FluentValid({TenantEditValidator.class}) TenantEditRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<TenantEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            TenantEntity entity = new TenantEntity();
            entity.setLogo(requestDto.getLogo());
            entity.setDescription(requestDto.getDescription());
            entity.setDomain(requestDto.getDomain());
            entity.setUrl(requestDto.getUrl());
            entity.setType(requestDto.getType());
            this.tenantCrudService.update(entity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("编辑租户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto deleteTenant(@FluentValid({TenantDeleteValidator.class}) TenantDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            this.tenantCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("删除租户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto.success();
    }

    /**
     * 锁定租户
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto lockTenant(@FluentValid({TenantLockValidator.class}) TenantLockRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<TenantEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            TenantEntity entity = new TenantEntity();
            entity.setStatus(TenantStatusEnum.LOCKED.getValue());
            this.tenantCrudService.update(entity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("删除租户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto.success();
    }

    /**
     * 解锁租户
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto unlockTenant(@FluentValid({TenantUnlockValidator.class}) TenantUnlockRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<TenantEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            TenantEntity entity = new TenantEntity();
            entity.setStatus(TenantStatusEnum.ENABLED.getValue());
            this.tenantCrudService.update(entity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("解锁租户失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto.success();
    }

    /**
     * 注销租户
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto cancelTenant(@FluentValid({TenantCancelValidator.class}) TenantCancelRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<TenantEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            TenantEntity entity = new TenantEntity();
            entity.setStatus(TenantStatusEnum.CANCELED.getValue());
            this.tenantCrudService.update(entity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("注销租户失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 重置租户管理员密码
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto resetTenantPassword(@FluentValid({TenantPasswordResetValidator.class}) TenantPasswordResetRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {
            TenantEntity tenantEntity = this.tenantCrudService.selectById(requestDto.getId());
            if (tenantEntity != null) {

                UserEntity userEntity = this.userCrudService.finByUserId(tenantEntity.getAdminUserId());
                //新密码处理
                //生产随机密码盐
                String salt = saltGenerator.generate();
                String encryptPassword = shiroCryptoService.password(requestDto.getAdminPassword(), userEntity.getAccount() + salt);
                this.userCrudService.updatePassword(userEntity.getTenantId(), userEntity.getId(), encryptPassword, salt);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("重置商户管理员密码失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据租户名获取租户列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<TenantListResultDto> getTenantListByName(TenantListRequestDto requestDto) {
        ListResultDto<TenantListResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<TenantEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.like("name", requestDto.getName());
            }
            List<TenantEntity> list = this.tenantCrudService.selectList(entityWrapper);
            List<TenantListResultDto> tenantListResultDtos = modelMapper.map(list, new TypeToken<List<TenantListResultDto>>() {
            }.getType());
            listResultDto.setItems(tenantListResultDtos);
            listResultDto.success();
        } catch (Exception e) {
            log.error("根据租户名获取租户列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }
}
