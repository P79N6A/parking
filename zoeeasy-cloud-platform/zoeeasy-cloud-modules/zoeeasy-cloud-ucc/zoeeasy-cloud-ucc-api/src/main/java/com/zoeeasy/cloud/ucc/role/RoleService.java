package com.zoeeasy.cloud.ucc.role;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.role.dto.RoleLookupListGetRequestDto;
import com.zoeeasy.cloud.ucc.role.dto.request.*;
import com.zoeeasy.cloud.ucc.role.dto.result.*;

/**
 * 角色服务
 *
 * @author walkman
 */
public interface RoleService {

    /**
     * 获取所有静态角色
     *
     * @return
     */
    ListResultDto<RoleResultDto> getStaticRoleList();

    /**
     * 新增角色
     *
     * @param requestDto
     * @return
     */
    ResultDto createRole(RoleCreateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 更新角色
     *
     * @param requestDto
     * @return
     */
    ResultDto editRole(RoleEditRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除角色
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteRole(RoleDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取角色
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<RoleResultDto> getRole(RoleGetRequestDto requestDto);

    /**
     * 获取角色选项列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getRoleLookupList(RoleLookupListGetRequestDto requestDto);

    /**
     * 分页获取角色列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<RoleListResultDto> getRolePagedList(RolePagedRequestDto requestDto);

    /**
     * 获取角色授予菜单树状视图
     *
     * @param requestDto
     * @return
     */
    ListResultDto<RoleMenuTreeItemResultDto> getRoleMenuTreeView(RoleMenuTreeViewGetRequestDto requestDto);

    /**
     * 获取角色授予权限树状视图
     *
     * @param requestDto
     * @return
     */
    ListResultDto<RolePermissionTreeItemResultDto> getRolePermissionTreeView(RolePermissionTreeViewGetRequestDto requestDto);

    /**
     * 获取角色授予用户列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<RoleMemberListResultDto> getRoleMemberList(RoleMemberListGetRequestDto requestDto);

    /**
     * 角色授权
     *
     * @param requestDto
     * @return
     */
    ResultDto authorize(RoleAuthorizeRequestDto requestDto) throws ValidationException, BusinessException;

}
