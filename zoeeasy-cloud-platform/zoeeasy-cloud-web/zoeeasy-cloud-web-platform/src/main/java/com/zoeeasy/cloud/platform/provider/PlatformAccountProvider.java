package com.zoeeasy.cloud.platform.provider;

import com.google.common.collect.Sets;
import com.scapegoat.infrastructure.core.account.IUserAccount;
import com.scapegoat.infrastructure.core.account.TenancyAccount;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.enums.ResultStatusCode;
import com.scapegoat.infrastructure.shiro.service.ShiroAccountProvider;
import com.zoeeasy.cloud.ucc.account.AccountService;
import com.zoeeasy.cloud.ucc.account.dto.request.AccountGetRequestDto;
import com.zoeeasy.cloud.ucc.account.dto.result.AccountPermissionResultDto;
import com.zoeeasy.cloud.ucc.account.dto.result.AccountResultDto;
import com.zoeeasy.cloud.ucc.account.dto.result.AccountRoleResultDto;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 账号信息提供者实现
 *
 * @author walkman
 * @date 2018年10月11日
 */
@Component
public class PlatformAccountProvider implements ShiroAccountProvider {

    @Autowired
    private AccountService accountService;

    @Override
    public IUserAccount loadAccount(String account) throws UnknownAccountException, LockedAccountException {

        AccountGetRequestDto requestDto = new AccountGetRequestDto();
        requestDto.setAccount(account);
        ObjectResultDto<AccountResultDto> accountResultDto = accountService.getAccount(requestDto);
        // 用户不存在
        if (accountResultDto.isFailed() || accountResultDto.getData() == null) {
            if (ResultStatusCode.ACCOUNT_NOT_FOUND_ERROR.getCode().equals(accountResultDto.getCode())) {
                throw new UnknownAccountException(accountResultDto.getMessage());
            }
            if (ResultStatusCode.ACCOUNT_LOCKED_CLOSED_ERROR.getCode().equals(accountResultDto.getCode())) {
                throw new LockedAccountException(accountResultDto.getMessage());
            }
        }
        // 对账号做检查
        AccountResultDto accountResult = accountResultDto.getData();
        TenancyAccount tenancyAccount = new TenancyAccount();
        //租户信息
        tenancyAccount.setTenantId(accountResult.getTenantId());
        tenancyAccount.setTenant(accountResult.getTenant());
        tenancyAccount.setTenancyHostSide(accountResult.getMultiTenancySide());
        //账号信息
        tenancyAccount.setUserId(accountResult.getUserId());
        tenancyAccount.setUuid(accountResult.getUuid());
        tenancyAccount.setAccount(accountResult.getAccount());
        tenancyAccount.setPassword(accountResult.getPassword());
        tenancyAccount.setSalt(accountResult.getSalt());
        tenancyAccount.setUserName(accountResult.getUserName());
        tenancyAccount.setPassword(accountResult.getPassword());
        tenancyAccount.setSalt(accountResult.getSalt());
        tenancyAccount.setUserType(accountResult.getUserType());
        tenancyAccount.setEmailAddress(accountResult.getEmailAddress());
        tenancyAccount.setPhoneNumber(accountResult.getPhoneNumber());
        return tenancyAccount;
    }

    /**
     * 加载用户持有的角色
     */
    @Override
    public Set<String> loadRoles(String account) {
        AccountGetRequestDto requestDto = new AccountGetRequestDto();
        requestDto.setAccount(account);
        ListResultDto<AccountRoleResultDto> accountResultDto = accountService.getAccountRoleList(requestDto);
        if (accountResultDto.isSuccess()) {
            List<AccountRoleResultDto> roleList = accountResultDto.getItems();
            return Sets.newHashSet(roleList.stream().map(AccountRoleResultDto::getCode).collect(Collectors.toList()));
        }
        return Sets.newHashSet();
    }

    /**
     * 系统采用  基于角色的权限访问控制(RBAC)策略
     * 所谓的权限通常可以理解为用户所能操作的资源，如（user:add、user:delete）
     * 此方法未实现
     */
    @Override
    public Set<String> loadPermissions(String account) {
        AccountGetRequestDto requestDto = new AccountGetRequestDto();
        requestDto.setAccount(account);
        ListResultDto<AccountPermissionResultDto> accountResultDto = accountService.getAccountPermissionList(requestDto);
        if (accountResultDto.isSuccess()) {
            List<AccountPermissionResultDto> permissionList = accountResultDto.getItems();
            return Sets.newHashSet(permissionList.stream().map(AccountPermissionResultDto::getCode).collect(Collectors.toList()));
        }
        return Sets.newHashSet();
    }
}
