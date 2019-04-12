package com.zoeeasy.cloud.ucc.account;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.ucc.account.dto.request.AccountGetRequestDto;
import com.zoeeasy.cloud.ucc.account.dto.result.AccountPermissionResultDto;
import com.zoeeasy.cloud.ucc.account.dto.result.AccountResultDto;
import com.zoeeasy.cloud.ucc.account.dto.result.AccountRoleResultDto;

public interface AccountService {

    /**
     * 获取用户账号
     *
     * @param requestDto requestDto
     * @return AccountResultDto
     */
    ObjectResultDto<AccountResultDto> getAccount(AccountGetRequestDto requestDto);

    /**
     * 获取账户角色列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<AccountRoleResultDto> getAccountRoleList(AccountGetRequestDto requestDto);

    /**
     * 获取账号权限列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<AccountPermissionResultDto> getAccountPermissionList(AccountGetRequestDto requestDto);

}
