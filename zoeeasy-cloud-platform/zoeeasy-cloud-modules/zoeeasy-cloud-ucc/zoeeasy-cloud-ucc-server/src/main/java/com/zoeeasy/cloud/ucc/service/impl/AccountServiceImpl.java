package com.zoeeasy.cloud.ucc.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.UserIdentifier;
import com.scapegoat.infrastructure.core.account.TenancyAccount;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.enums.ResultStatusCode;
import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import com.zoeeasy.cloud.ucc.account.AccountService;
import com.zoeeasy.cloud.ucc.account.dto.request.AccountGetRequestDto;
import com.zoeeasy.cloud.ucc.account.dto.result.AccountPermissionResultDto;
import com.zoeeasy.cloud.ucc.account.dto.result.AccountResultDto;
import com.zoeeasy.cloud.ucc.account.dto.result.AccountRoleResultDto;
import com.zoeeasy.cloud.ucc.authorization.SecurityManagerService;
import com.zoeeasy.cloud.ucc.domain.PermissionEntity;
import com.zoeeasy.cloud.ucc.domain.RoleEntity;
import com.zoeeasy.cloud.ucc.domain.TenantEntity;
import com.zoeeasy.cloud.ucc.domain.UserEntity;
import com.zoeeasy.cloud.ucc.enums.TenantStatusEnum;
import com.zoeeasy.cloud.ucc.enums.UserStatusEnum;
import com.zoeeasy.cloud.ucc.service.TenantCrudService;
import com.zoeeasy.cloud.ucc.service.UserCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 账号服务
 */
@Slf4j
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private TenantCrudService tenantCrudService;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private SecurityManagerService securityManagerService;

    @Override
    public ObjectResultDto<AccountResultDto> getAccount(AccountGetRequestDto requestDto) {
        ObjectResultDto<AccountResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            TenancyAccount tenancyAccount = TenancyAccount.parse(requestDto.getAccount());
            //如果是租户普通用户
            if (StringUtils.isNotEmpty(tenancyAccount.getTenant())) {
                objectResultDto = loadTenantAccount(tenancyAccount.getTenant(), tenancyAccount.getAccount());
            } else {
                //如果是租户超级管理员用户或者宿主用户
                objectResultDto = loadHostAccount(tenancyAccount.getAccount());
            }
        } catch (Exception e) {
            log.error("获取用户账号失败:{}", e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 获取商户端用户
     *
     * @param tenantCode tenantCode
     * @param account    account
     * @return
     */
    private ObjectResultDto<AccountResultDto> loadTenantAccount(String tenantCode, String account) {
        ObjectResultDto<AccountResultDto> objectResultDto = new ObjectResultDto<>();
        TenantEntity tenantEntity = tenantCrudService.findByTenantCode(tenantCode);
        //租户不存在
        if (tenantEntity == null) {
            objectResultDto.makeResult(ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getCode(), ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getMessage());
        } else {
            //商户状态禁用或者注销
            if (!TenantStatusEnum.ENABLED.getValue().equals(tenantEntity.getStatus())) {
                objectResultDto.makeResult(ResultStatusCode.ACCOUNT_LOCKED_CLOSED_ERROR.getCode(), ResultStatusCode.ACCOUNT_LOCKED_CLOSED_ERROR.getMessage());
            } else {
                UserEntity userEntity = userCrudService.getTenantUserByAccount(tenantEntity.getId(), account);
                if (userEntity != null) {
                    //用户状态禁用或者注销
                    if (!UserStatusEnum.ENABLED.getValue().equals(userEntity.getStatus())) {
                        objectResultDto.makeResult(ResultStatusCode.ACCOUNT_LOCKED_CLOSED_ERROR.getCode(), ResultStatusCode.ACCOUNT_LOCKED_CLOSED_ERROR.getMessage());
                    } else {
                        AccountResultDto accountResultDto = new AccountResultDto();
                        accountResultDto.setUserId(userEntity.getId());
                        accountResultDto.setUuid(userEntity.getUuid());
                        accountResultDto.setAccount(userEntity.getAccount());
                        accountResultDto.setUserName(userEntity.getUserName());
                        accountResultDto.setPassword(userEntity.getPassword());
                        accountResultDto.setSalt(userEntity.getSalt());
                        accountResultDto.setUserType(userEntity.getUserType());
                        accountResultDto.setNickName(userEntity.getNickName());
                        accountResultDto.setPortrait(userEntity.getPortrait());
                        accountResultDto.setEmailAddress(userEntity.getEmailAddress());
                        accountResultDto.setPhoneNumber(userEntity.getPhoneNumber());
                        //租户信息
                        accountResultDto.setMultiTenancySide(TenancyHostSide.Tenant);
                        accountResultDto.setTenantId(tenantEntity.getId());
                        accountResultDto.setTenant(tenantEntity.getCode());
                        accountResultDto.setTenantName(tenantEntity.getName());
                        objectResultDto.setData(accountResultDto);
                        objectResultDto.success();
                    }
                } else {
                    objectResultDto.makeResult(ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getCode(), ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getMessage());
                }
            }
        }
        return objectResultDto;
    }

    /**
     * 获取宿主端用户
     *
     * @param account account
     * @return
     */
    private ObjectResultDto<AccountResultDto> loadHostAccount(String account) {
        ObjectResultDto<AccountResultDto> objectResultDto = new ObjectResultDto<>();
        UserEntity userEntity = userCrudService.getUserByAccount(account);
        if (userEntity != null) {
            //用户状态禁用或者注销
            if (!UserStatusEnum.ENABLED.getValue().equals(userEntity.getStatus())) {
                objectResultDto.makeResult(ResultStatusCode.ACCOUNT_LOCKED_CLOSED_ERROR.getCode(), ResultStatusCode.ACCOUNT_LOCKED_CLOSED_ERROR.getMessage());
            } else {
                AccountResultDto accountResultDto = new AccountResultDto();
                accountResultDto.setUserId(userEntity.getId());
                accountResultDto.setUuid(userEntity.getUuid());
                accountResultDto.setAccount(userEntity.getAccount());
                accountResultDto.setUserName(userEntity.getUserName());
                accountResultDto.setPassword(userEntity.getPassword());
                accountResultDto.setSalt(userEntity.getSalt());
                accountResultDto.setUserType(userEntity.getUserType());
                accountResultDto.setNickName(userEntity.getNickName());
                accountResultDto.setPortrait(userEntity.getPortrait());
                accountResultDto.setEmailAddress(userEntity.getEmailAddress());
                accountResultDto.setPhoneNumber(userEntity.getPhoneNumber());
                //租户信息
                if (userEntity.getTenantId() != null) {
                    TenantEntity tenantEntity = tenantCrudService.selectById(userEntity.getTenantId());
                    if (tenantEntity != null) {
                        //商户状态禁用或者注销
                        if (!TenantStatusEnum.ENABLED.getValue().equals(tenantEntity.getStatus())) {
                            objectResultDto.makeResult(ResultStatusCode.ACCOUNT_LOCKED_CLOSED_ERROR.getCode(), ResultStatusCode.ACCOUNT_LOCKED_CLOSED_ERROR.getMessage());
                        } else {
                            accountResultDto.setMultiTenancySide(TenancyHostSide.Tenant);
                            accountResultDto.setTenantId(tenantEntity.getId());
                            accountResultDto.setTenant(tenantEntity.getCode());
                            accountResultDto.setTenantName(tenantEntity.getName());
                            objectResultDto.setData(accountResultDto);
                            objectResultDto.success();
                        }
                    } else {
                        //租户不存在
                        objectResultDto.makeResult(ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getCode(), ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getMessage());
                    }
                } else {
                    accountResultDto.setMultiTenancySide(TenancyHostSide.Host);
                    objectResultDto.setData(accountResultDto);
                    objectResultDto.success();
                }
            }
        } else {
            objectResultDto.makeResult(ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getCode(), ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 获取账户角色列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<AccountRoleResultDto> getAccountRoleList(AccountGetRequestDto requestDto) {
        ListResultDto<AccountRoleResultDto> listResultDto = new ListResultDto<>();
        try {
            ObjectResultDto<UserIdentifier> userObjectResultDto = getUserIdentifier(requestDto);
            if (userObjectResultDto.isFailed() || userObjectResultDto.getData() == null) {
                return listResultDto.makeResult(userObjectResultDto.getCode(), userObjectResultDto.getMessage());
            }
            List<AccountRoleResultDto> roleResultDtoList = new ArrayList<>();
            UserIdentifier userIdentifier = userObjectResultDto.getData();
            List<RoleEntity> roleEntityList = this.securityManagerService.getUserRoleList(userIdentifier);
            if (CollectionUtils.isNotEmpty(roleEntityList)) {
                for (RoleEntity role : roleEntityList) {
                    AccountRoleResultDto roleResultDto = new AccountRoleResultDto();
                    roleResultDto.setCode(role.getCode());
                    roleResultDtoList.add(roleResultDto);
                }
            }
            listResultDto.setItems(roleResultDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取账号权限列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<AccountPermissionResultDto> getAccountPermissionList(AccountGetRequestDto requestDto) {
        ListResultDto<AccountPermissionResultDto> listResultDto = new ListResultDto<>();
        try {

            ObjectResultDto<UserIdentifier> userObjectResultDto = getUserIdentifier(requestDto);
            if (userObjectResultDto.isFailed() || userObjectResultDto.getData() == null) {
                return listResultDto.makeResult(userObjectResultDto.getCode(), userObjectResultDto.getMessage());
            }
            List<AccountPermissionResultDto> permissionResultDtoList = new ArrayList<>();
            UserIdentifier userIdentifier = userObjectResultDto.getData();
            List<PermissionEntity> roleEntityList = this.securityManagerService.getUserPermissionList(userIdentifier);
            if (CollectionUtils.isNotEmpty(roleEntityList)) {
                for (PermissionEntity role : roleEntityList) {
                    AccountPermissionResultDto roleResultDto = new AccountPermissionResultDto();
                    roleResultDto.setCode(role.getCode());
                    permissionResultDtoList.add(roleResultDto);
                }
            }
            listResultDto.setItems(permissionResultDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取用户标识
     *
     * @param requestDto
     * @return
     */
    private ObjectResultDto<UserIdentifier> getUserIdentifier(AccountGetRequestDto requestDto) {
        ObjectResultDto<UserIdentifier> objectResultDto = new ObjectResultDto<>();
        try {
            TenancyAccount tenancyAccount = TenancyAccount.parse(requestDto.getAccount());
            //如果是租户普通用户
            UserIdentifier userIdentifier = new UserIdentifier();
            if (StringUtils.isNotEmpty(tenancyAccount.getTenant())) {
                TenantEntity tenantEntity = tenantCrudService.findByTenantCode(tenancyAccount.getTenant());
                //租户不存在
                if (tenantEntity == null) {
                    objectResultDto.makeResult(ResultStatusCode.TENANT_NOT_FOUND_ERROR.getCode(), ResultStatusCode.TENANT_NOT_FOUND_ERROR.getMessage());
                } else {
                    userIdentifier.setTenantId(tenantEntity.getId());
                    UserEntity userEntity = userCrudService.getTenantUserByAccount(tenantEntity.getId(), tenancyAccount.getAccount());
                    if (userEntity != null) {
                        userIdentifier.setUserId(userEntity.getId());
                        objectResultDto.setData(userIdentifier);
                        objectResultDto.success();
                    } else {
                        objectResultDto.makeResult(ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getCode(), ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getMessage());
                    }
                }
            } else {
                //如果是租户超级管理员用户或者宿主用户
                UserEntity userEntity = userCrudService.getUserByAccount(tenancyAccount.getAccount());
                if (userEntity != null) {
                    userIdentifier.setUserId(userEntity.getId());
                    if (userEntity.getTenantId() != null) {
                        //租户信息
                        TenantEntity tenantEntity = tenantCrudService.selectById(userEntity.getTenantId());
                        if (tenantEntity != null) {
                            userIdentifier.setTenantId(tenantEntity.getId());
                            objectResultDto.setData(userIdentifier);
                            objectResultDto.success();
                        } else {
                            //租户不存在
                            objectResultDto.makeResult(ResultStatusCode.TENANT_NOT_FOUND_ERROR.getCode(), ResultStatusCode.TENANT_NOT_FOUND_ERROR.getMessage());
                        }
                    } else {
                        objectResultDto.setData(userIdentifier);
                        objectResultDto.success();
                    }
                } else {
                    //账号不存在
                    objectResultDto.makeResult(ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getCode(), ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("获取用户账号信息失败:", e.getMessage());
        }
        return objectResultDto;
    }
}
