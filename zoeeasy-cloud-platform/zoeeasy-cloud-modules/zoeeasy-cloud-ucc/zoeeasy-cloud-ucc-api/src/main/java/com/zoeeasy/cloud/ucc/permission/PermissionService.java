package com.zoeeasy.cloud.ucc.permission;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.ucc.permission.dto.*;

/**
 * 权限服务
 *
 * @author walkman
 */
public interface PermissionService {

    /**
     * 新增权限
     *
     * @param requestDto
     * @return
     */
    ResultDto addPermission(PermissionAddRequestDto requestDto);

    /**
     * 删除权限
     *
     * @param requestDto
     * @return
     */
    ResultDto deletePermission(PermissionDeleteRequestDto requestDto);

    /**
     * 更新权限
     *
     * @param requestDto
     * @return
     */
    ResultDto updatePermission(PermissionUpdateRequestDto requestDto);

    /**
     * 获取权限
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PermissionResultDto> getPermission(PermissionGetRequestDto requestDto);

    /**
     * 获取权限列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<PermissionResultDto> getPermissionList(PermissionListGetRequestDto requestDto);

    /**
     * 分页获取权限列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<PermissionResultDto> getPermissionPagedList(PermissionQueryPagedResultRequestDto requestDto);
}
