package com.zoeeasy.cloud.ucc.tenant;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.tenant.dto.request.*;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantAccountCheckResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantCheckResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantListResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantResultDto;

/**
 * 租户管理服务
 *
 * @author walkman
 * @since 2018-08-28
 */
public interface TenantService {

    /**
     * 检查租户名称是否可用
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<TenantCheckResultDto> checkTenant(TenantCheckRequestDto requestDto);

    /**
     * 检查租户管理员是否可用
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<TenantAccountCheckResultDto> checkTenantAccount(TenantAccountCheckRequestDto requestDto);

    /**
     * 租户列表
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<TenantResultDto> getTenant(TenantGetRequestDto requestDto);

    /**
     * 租户列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<TenantListResultDto> getTenantList(TenantPagedListRequestDto requestDto);

    /**
     * 创建租户
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto createTenant(TenantCreateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 编辑租户
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto editTenant(TenantEditRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除租户
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto deleteTenant(TenantDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 锁定租户
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto lockTenant(TenantLockRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 解锁租户
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto unlockTenant(TenantUnlockRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 注销租户
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto cancelTenant(TenantCancelRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 重置租户管理员密码
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto resetTenantPassword(TenantPasswordResetRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 根据租户名获取租户列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<TenantListResultDto> getTenantListByName(TenantListRequestDto requestDto);

}
