package com.zoeeasy.cloud.ucc.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.enums.ResultStatusCode;
import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import com.scapegoat.infrastructure.core.session.ISessionIdentity;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.shiro.service.SaltGenerator;
import com.scapegoat.infrastructure.shiro.service.ShiroCryptoService;
import com.zoeeasy.cloud.core.utils.TreeUtils;
import com.zoeeasy.cloud.ucc.authorization.SecurityManagerService;
import com.zoeeasy.cloud.ucc.domain.*;
import com.zoeeasy.cloud.ucc.enums.UccResultEnum;
import com.zoeeasy.cloud.ucc.enums.UserRegisterChannelEnum;
import com.zoeeasy.cloud.ucc.enums.UserStatusEnum;
import com.zoeeasy.cloud.ucc.enums.UserTypeEnum;
import com.zoeeasy.cloud.ucc.service.*;
import com.zoeeasy.cloud.ucc.user.UserService;
import com.zoeeasy.cloud.ucc.user.dto.*;
import com.zoeeasy.cloud.ucc.user.dto.request.*;
import com.zoeeasy.cloud.ucc.user.dto.result.UserDetailResultDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserListResultDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserPermissionListResultDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserProfileResultDto;
import com.zoeeasy.cloud.ucc.user.validator.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private TenantCrudService tenantCrudService;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserRoleCrudService userRoleCrudService;

    @Autowired
    private OrganizationCrudService organizationCrudService;

    @Autowired
    private UserOrganizationCrudService userOrganizationCrudService;

    @Autowired
    private SecurityManagerService securityManagerService;

    @Autowired
    private SaltGenerator saltGenerator;

    @Autowired
    private ShiroCryptoService shiroCryptoService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 创建用户
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto createUser(@FluentValid(value = {UserCreateValidator.class}) UserCreateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserEntity userEntity = this.createUserEntity(requestDto);
            this.userCrudService.insert(userEntity);
            //如果角色列表不为空
            if (CollectionUtils.isNotEmpty(requestDto.getRoleIds())) {
                List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
                for (Long roleId : requestDto.getRoleIds()) {
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setUserId(userEntity.getId());
                    userRoleEntity.setRoleId(roleId);
                    userRoleEntityList.add(userRoleEntity);
                }
                this.userRoleCrudService.insertBatch(userRoleEntityList);
            }
            //如果指定部门
            OrganizationEntity organizationEntity = null;
            if (requestDto.getOrganizationId() != null) {
                organizationEntity = this.organizationCrudService.selectById(requestDto.getOrganizationId());
            }
            if (organizationEntity == null) {
                //默认创建在根部门下
                organizationEntity = this.organizationCrudService.getRoot();
            }
            if (organizationEntity != null) {
                UserOrganizationEntity userOrganizationEntity = new UserOrganizationEntity();
                userOrganizationEntity.setOrganizationId(organizationEntity.getId());
                userOrganizationEntity.setUserId(userEntity.getId());
                this.userOrganizationCrudService.insert(userOrganizationEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("创建用户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 创建用户
     *
     * @param requestDto
     * @return
     */
    private UserEntity createUserEntity(UserCreateRequestDto requestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(StringUtils.getUUID());
        userEntity.setUserName(requestDto.getUserName());
        userEntity.setRealName(requestDto.getRealName());
        userEntity.setEmailAddress(requestDto.getEmailAddress());
        userEntity.setEmailAddressConfirmed(Boolean.FALSE);
        userEntity.setPhoneNumber(requestDto.getPhoneNumber());
        userEntity.setPhoneNumberConfirmed(Boolean.FALSE);
        //生成账号
        ISessionIdentity sessionIdentity = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();
        if (sessionIdentity != null) {
            if (sessionIdentity.getMultiTenancySide().equals(TenancyHostSide.Host)) {
                //如果是平台端用户
                userEntity.setAccount(requestDto.getUserName());
            } else if (sessionIdentity.getMultiTenancySide().equals(TenancyHostSide.Tenant)) {
                //如果是租户端用户
                TenantEntity tenantEntity = this.tenantCrudService.selectById(sessionIdentity.getTenantId());
                if (tenantEntity != null) {
                    userEntity.setAccount(requestDto.getUserName() + "@" + tenantEntity.getCode());
                }
            }
        } else {
            userEntity.setAccount(requestDto.getUserName());
        }
        //新密码处理
        //生产随机密码盐
        String salt = saltGenerator.generate();
        String encryptPassword = shiroCryptoService.password(requestDto.getPassword(), userEntity.getAccount() + salt);
        userEntity.setPassword(encryptPassword);
        userEntity.setSalt(salt);
        userEntity.setUserType(UserTypeEnum.TENANT_USER.getValue());
        userEntity.setAdminUser(Boolean.FALSE);
        userEntity.setRegisterChannel(UserRegisterChannelEnum.TENANT.getValue());
        userEntity.setStatus(UserStatusEnum.ENABLED.getValue());
        return userEntity;
    }

    /**
     * 更新用户
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto editUser(@FluentValid(value = {UserEditValidator.class}) UserEditRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            UserEntity userEntity = new UserEntity();
            userEntity.setRealName(requestDto.getRealName());
            userEntity.setEmailAddress(requestDto.getEmailAddress());
            userEntity.setPhoneNumber(requestDto.getPhoneNumber());
            this.userCrudService.update(userEntity, entityWrapper);
            //删除用户所有角色
            this.userRoleCrudService.deleteByUserId(requestDto.getId());
            //如果角色列表不为空
            if (CollectionUtils.isNotEmpty(requestDto.getRoleIds())) {
                List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
                for (Long roleId : requestDto.getRoleIds()) {
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setRoleId(roleId);
                    userRoleEntity.setUserId(requestDto.getId());
                    userRoleEntityList.add(userRoleEntity);
                }
                this.userRoleCrudService.insertBatch(userRoleEntityList);
            }
            //如果指定部门
            OrganizationEntity organizationEntity = null;
            if (requestDto.getOrganizationId() != null) {
                organizationEntity = this.organizationCrudService.selectById(requestDto.getOrganizationId());
            } else {
                //默认创建在根部门下
                organizationEntity = this.organizationCrudService.getRoot();
            }
            if (organizationEntity != null) {
                //删除原有的部门
                userOrganizationCrudService.deleteByUserId(requestDto.getId());
                UserOrganizationEntity userOrganizationEntity = new UserOrganizationEntity();
                userOrganizationEntity.setOrganizationId(organizationEntity.getId());
                userOrganizationEntity.setUserId(requestDto.getId());
                this.userOrganizationCrudService.insert(userOrganizationEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新用户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 重置用户密码
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto resetUserPassword(@FluentValid(value = {UserPasswordResetValidator.class}) UserPasswordResetRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserEntity oldUserEntity = this.userCrudService.selectById(requestDto.getId());
            if (oldUserEntity == null) {
                return resultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(),
                        UccResultEnum.USER_NOT_FOUND.getComment());
            }
            EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", oldUserEntity.getId());
            //新密码处理
            //生产随机密码盐
            String salt = saltGenerator.generate();
            String encryptPassword = shiroCryptoService.password(requestDto.getPassword(), oldUserEntity.getAccount() + salt);
            UserEntity userEntity = new UserEntity();
            userEntity.setPassword(encryptPassword);
            userEntity.setSalt(salt);
            this.userCrudService.update(userEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改用户密码失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 注销用户
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto closeUser(@FluentValid(value = {UserCloseValidator.class}) UserCloseRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserEntity userEntity = new UserEntity();
            EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            userEntity.setStatus(UserStatusEnum.CANCELED.getValue());
            this.userCrudService.update(userEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("注销用户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 锁定用户
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto lockUser(@FluentValid(value = {UserLockValidator.class}) UserLockRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserEntity userEntity = new UserEntity();
            EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            userEntity.setStatus(UserStatusEnum.LOCKED.getValue());
            this.userCrudService.update(userEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("锁定用户失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 解锁用户
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto unlockUser(@FluentValid(value = {UserUnlockValidator.class}) UserUnlockRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserEntity userEntity = new UserEntity();
            EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            userEntity.setStatus(UserStatusEnum.ENABLED.getValue());
            this.userCrudService.update(userEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("解锁用户失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除用户
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteUser(@FluentValid(value = {UserDeleteValidator.class}) UserDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserEntity userEntity = this.userCrudService.selectById(requestDto.getId());
            if (userEntity == null) {
                resultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(),
                        UccResultEnum.USER_NOT_FOUND.getComment());
            } else {
                //删除用户
                this.userCrudService.deleteById(requestDto.getId());
                //删除用户角色
                this.userRoleCrudService.deleteByUserId(requestDto.getId());
                //删除用户部门
                this.userOrganizationCrudService.deleteByUserId(requestDto.getId());
                resultDto.success();
            }
        } catch (Exception e) {
            log.error("删除用户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<UserDetailResultDto> getUser(UserGetRequestDto requestDto) {
        ObjectResultDto<UserDetailResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            UserEntity userEntity = this.userCrudService.selectById(requestDto.getId());
            if (userEntity == null) {
                objectResultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(), UccResultEnum.USER_NOT_FOUND.getComment());
            } else {
                UserDetailResultDto userDetailResultDto = new UserDetailResultDto();
                userDetailResultDto.setId(userEntity.getId());
                userDetailResultDto.setUuid(userEntity.getUuid());
                userDetailResultDto.setAccount(userEntity.getAccount());
                userDetailResultDto.setUserName(userEntity.getUserName());
                userDetailResultDto.setRealName(userEntity.getRealName());
                userDetailResultDto.setStatus(userEntity.getStatus());
                userDetailResultDto.setUserType(userEntity.getUserType());
                userDetailResultDto.setEmailAddress(userEntity.getEmailAddress());
                userDetailResultDto.setPhoneNumber(userEntity.getPhoneNumber());
                userDetailResultDto.setLastLoginTime(userEntity.getLastLoginTime());
                //用户部门
                OrganizationEntity organizationEntity = this.getUserOrganization(userEntity.getId());
                if (organizationEntity != null) {
                    userDetailResultDto.setOrganizationId(getParentOrganizationIds(organizationEntity.getId()));
                    userDetailResultDto.setOrganizationName(organizationEntity.getName());
                }
                //用户角色
                List<RoleEntity> roleEntities = this.securityManagerService.getUserRoleList(userEntity.getId());
                if (CollectionUtils.isNotEmpty(roleEntities)) {
                    List<UserRoleItemResultDto> userRoleItemResultDtos = new ArrayList<>();
                    for (RoleEntity roleEntity : roleEntities) {
                        UserRoleItemResultDto roleItemResultDto = new UserRoleItemResultDto();
                        roleItemResultDto.setRoleId(roleEntity.getId());
                        roleItemResultDto.setRoleName(roleEntity.getName());
                        userRoleItemResultDtos.add(roleItemResultDto);
                    }
                    userDetailResultDto.setRoles(userRoleItemResultDtos);
                }
                objectResultDto.setData(userDetailResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 修改密码
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto modifyPassword(@FluentValid(value = {UserPasswordModifyValidator.class}) UserPasswordModifyRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SessionIdentity sessionIdentity = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();
            if (sessionIdentity != null) {
                UserEntity user = userCrudService.selectById(sessionIdentity.getUserId());
                if (user == null) {
                    return resultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(),
                            UccResultEnum.USER_NOT_FOUND.getComment());
                }
                //密码处理
                String oldEncryptPassword = shiroCryptoService.password(requestDto.getOldPassword(), user.getAccount() + user.getSalt());
                if (!oldEncryptPassword.equals(user.getPassword())) {
                    return resultDto.makeResult(UccResultEnum.OLD_PASSWORD_ERROR.getValue(),
                            UccResultEnum.OLD_PASSWORD_ERROR.getComment());
                }
                //密码处理
                String salt = saltGenerator.generate();
                String encryptPassword = shiroCryptoService.password(requestDto.getNewPassword(), user.getAccount() + salt);

                //修改用户密码
                EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", user.getId());
                UserEntity userEntityUpdate = new UserEntity();
                userEntityUpdate.setId(user.getId());
                userEntityUpdate.setSalt(salt);
                userEntityUpdate.setPassword(encryptPassword);
                userCrudService.update(userEntityUpdate, entityWrapper);
                //TODO 密码修改记录
                return resultDto.success();
            }
        } catch (Exception e) {
            log.error("修改密码" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页查询用户列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<UserListResultDto> getUserPagedList(UserPagedListRequestDto requestDto) {

        PagedResultDto<UserListResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getUserName())) {
                entityWrapper.like("userName", requestDto.getUserName());
            }
            if (!StringUtils.isEmpty(requestDto.getRealName())) {
                entityWrapper.like("realName", requestDto.getRealName());
            }
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.like("phoneNumber", requestDto.getPhoneNumber());
            }
            if (!StringUtils.isEmpty(requestDto.getEmailAddress())) {
                entityWrapper.like("emailAddress", requestDto.getEmailAddress());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            Page<UserEntity> userEntityPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserEntity> userEntityPageList = this.userCrudService.selectPage(userEntityPage, entityWrapper);
            if (userEntityPageList != null) {
                List<UserListResultDto> userDtoList = modelMapper.map(userEntityPageList.getRecords(), new TypeToken<List<UserListResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(userEntityPageList.getCurrent());
                pagedResultDto.setPageSize(userEntityPageList.getSize());
                pagedResultDto.setTotalCount(userEntityPageList.getTotal());
                pagedResultDto.setItems(userDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取用户角色列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<UserRoleResultDto> getUserRoleList(UserRoleListGetRequestDto requestDto) {
        ListResultDto<UserRoleResultDto> resultDto = new ListResultDto<>();
        try {
            //获取用户角色列表
            List<RoleEntity> roleEntityList = this.securityManagerService.getUserRoleList(requestDto.getUserId());
            if (CollectionUtils.isNotEmpty(roleEntityList)) {
                List<UserRoleResultDto> userRoleResultDtos = new ArrayList<>();
                for (RoleEntity role : roleEntityList) {
                    UserRoleResultDto userRoleResultDto = new UserRoleResultDto();
                    userRoleResultDto.setUserId(requestDto.getUserId());
                    userRoleResultDto.setRoleId(role.getId());
                    userRoleResultDto.setCode(role.getCode());
                    userRoleResultDto.setName(role.getName());
                    userRoleResultDtos.add(userRoleResultDto);
                }
                resultDto.setItems(userRoleResultDtos);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取用户角色失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户权限列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<UserPermissionListResultDto> getUserPermissionList(UserPermissionListGetRequestDto requestDto) {
        ObjectResultDto<UserPermissionListResultDto> resultDto = new ObjectResultDto<>();
        try {
            //获取用户角色列表
            SessionIdentity sessionIdentity = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();
            if (sessionIdentity != null) {

                List<RoleEntity> roleEntityList = this.securityManagerService.getUserRoleList(sessionIdentity.getUserId());
                if (CollectionUtils.isNotEmpty(roleEntityList)) {
                    UserPermissionListResultDto userPermissionListResultDto = new UserPermissionListResultDto();
                    Map<String, String> permissions = new TreeMap<>();
                    for (RoleEntity role : roleEntityList) {
                        List<PermissionEntity> permissionEntityList = this.securityManagerService.getRolePermissionList(role.getId());
                        if (CollectionUtils.isNotEmpty(permissionEntityList)) {
                            for (PermissionEntity permissionEntity : permissionEntityList) {
                                permissions.putIfAbsent(permissionEntity.getCode(), permissionEntity.getName());
                            }
                        }
                    }
                    userPermissionListResultDto.setPermissions(permissions);
                    resultDto.setData(userPermissionListResultDto);
                }
                resultDto.success();
            } else {
                return resultDto.makeResult(ResultStatusCode.NOT_LOGIN_ERROR.getCode(),
                        ResultStatusCode.NOT_LOGIN_ERROR.getMessage());
            }
        } catch (Exception e) {
            log.error("获取用户权限失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>(64);
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 获取用户菜单列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<UserMenuResultDto> getUserMenuList(UserMenuListGetRequestDto requestDto) {
        ListResultDto<UserMenuResultDto> resultDto = new ListResultDto<>();
        try {
            if (requestDto.getUserId() == null) {
                return resultDto.success();
            }
            List<MenuEntity> menuEntityAllList = new ArrayList<>();
            //获取用户角色列表
            List<RoleEntity> roleEntityList = this.securityManagerService.getUserRoleList(requestDto.getUserId());
            if (CollectionUtils.isNotEmpty(roleEntityList)) {
                //根据角色获取菜单列表
                for (RoleEntity role : roleEntityList) {
                    List<MenuEntity> menuEntityList = this.securityManagerService.getRoleMenuList(role.getId());
                    if (CollectionUtils.isNotEmpty(menuEntityList)) {
                        menuEntityAllList.addAll(menuEntityList);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(menuEntityAllList)) {
                //过滤掉重复菜单
                List<MenuEntity> distinctUserMenuResultDtos =
                        menuEntityAllList.stream().distinct()
                                .filter(distinctByKey(MenuEntity::getId))
                                .collect(Collectors.toList());

                if (CollectionUtils.isNotEmpty(distinctUserMenuResultDtos)) {
                    List<UserMenuResultDto> userMenuDtoList = modelMapper.map(distinctUserMenuResultDtos, new TypeToken<List<UserMenuResultDto>>() {
                    }.getType());
                    resultDto.setItems(userMenuDtoList);
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取用户角色失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户所在部门
     *
     * @param userId
     * @return
     */
    private OrganizationEntity getUserOrganization(Long userId) {
        UserOrganizationEntity userOrganizationEntity = this.userOrganizationCrudService.findByUserId(userId);
        if (userOrganizationEntity != null) {
            return this.organizationCrudService.selectById(userOrganizationEntity.getOrganizationId());
        }
        return null;
    }

    /**
     * 获取部门及所有上级部门ID列表
     *
     * @param organizationId
     * @return
     */
    private List<Long> getParentOrganizationIds(Long organizationId) {
        OrganizationEntity organizationEntity = this.organizationCrudService.selectById(organizationId);
        List<Long> result = new ArrayList<>();
        if (organizationEntity != null) {
            String[] strArray = organizationEntity.getPathCode().split("\\.");
            //没有上级
            if (strArray.length == 1) {
                result.add(organizationEntity.getId());
            } else {
                String[] parentCode = TreeUtils.getParentCodePath(organizationEntity.getPathCode());
                if (parentCode != null) {
                    EntityWrapper<OrganizationEntity> entityWrapper = new EntityWrapper<>();
                    entityWrapper.in("pathCode", parentCode);
                    entityWrapper.orderBy("pathCode", true);
                    List<OrganizationEntity> organizationEntities = this.organizationCrudService.selectList(entityWrapper);
                    if (CollectionUtils.isNotEmpty(organizationEntities)) {
                        return organizationEntities.stream().map(OrganizationEntity::getId).collect(Collectors.toList());
                    }
                }
            }
        }
        return result;
    }

    /**
     * 用户更新个人头像
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateUserPortrait(UserPortraitUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SessionIdentity sessionIdentity = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();
            if (sessionIdentity != null) {
                UserEntity user = userCrudService.selectById(sessionIdentity.getUserId());
                if (user == null) {
                    return resultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(),
                            UccResultEnum.USER_NOT_FOUND.getComment());
                }
                UserEntity userUpdate = new UserEntity();
                userUpdate.setPortrait(requestDto.getPortraitUrl());
                EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", user.getId());
                userCrudService.update(userUpdate, entityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("上传头像失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户个人信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<UserProfileResultDto> getUserProfile(UserProfileGetRequestDto requestDto) {
        ObjectResultDto<UserProfileResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            SessionIdentity sessionIdentity = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();
            if (sessionIdentity != null) {
                UserEntity userEntity = userCrudService.selectById(sessionIdentity.getUserId());
                if (userEntity == null) {
                    return objectResultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(),
                            UccResultEnum.USER_NOT_FOUND.getComment());
                }
                UserProfileResultDto userProfileResultDto = new UserProfileResultDto();
                userProfileResultDto.setAccount(userEntity.getAccount());
                userProfileResultDto.setEmailAddress(userEntity.getEmailAddress());
                userProfileResultDto.setNickName(userEntity.getNickName());
                userProfileResultDto.setPhoneNumber(userEntity.getPhoneNumber());
                userProfileResultDto.setPortrait(userEntity.getPortrait());
                userProfileResultDto.setUserName(userEntity.getUserName());
                userProfileResultDto.setRealName(userEntity.getRealName());
                userProfileResultDto.setCreationTime(userEntity.getCreationTime());
                //商户端用户
                if (sessionIdentity.getMultiTenancySide().equals(TenancyHostSide.Tenant)) {
                    TenantEntity tenantEntity = this.tenantCrudService.selectById(sessionIdentity.getTenantId());
                    if (tenantEntity == null) {
                        return objectResultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(),
                                UccResultEnum.USER_NOT_FOUND.getComment());
                    }
                    userProfileResultDto.setTenantName(tenantEntity.getName());
                }
                objectResultDto.setData(userProfileResultDto);
                objectResultDto.success();
            } else {
                return objectResultDto.makeResult(ResultStatusCode.NOT_LOGIN_ERROR.getCode(),
                        ResultStatusCode.NOT_LOGIN_ERROR.getMessage());
            }
        } catch (Exception e) {
            log.error("用户个人信息获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 用户登录时更新
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto loginUpdateUser(UserUpdateLoginRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserEntity existUser = userCrudService.finByUserId(requestDto.getId());
            if (existUser == null) {
                return resultDto.makeResult(UccResultEnum.USER_NOT_FOUND.getValue(),
                        UccResultEnum.USER_NOT_FOUND.getComment());
            }
            existUser.setAccessAttemptCount(requestDto.getAccessAttemptCount());
            existUser.setLastLoginTime(requestDto.getLastLoginTime());
            EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            userCrudService.update(existUser, entityWrapper);
            return resultDto.success();
        } catch (Exception e) {
            log.error("用户登录时更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取审核停车场人员列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<UserListResultDto> getAuditUserList(AuditUserListGetRequestDto requestDto) {
        ListResultDto<UserListResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<UserEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getUsername())){
                entityWrapper.like("userName", requestDto.getUsername());
            }
            entityWrapper.eq("userType", UserTypeEnum.ROOT_USER.getValue());
            List<UserEntity> userEntities = userCrudService.selectList(entityWrapper);
            List<UserListResultDto> list = modelMapper.map(userEntities, new TypeToken<List<UserListResultDto>>() {
            }.getType());
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e){
            log.error("获取审核停车场人员列表失败"+e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }
}
