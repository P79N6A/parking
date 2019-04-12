package com.zoeeasy.cloud.ucc.user;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.user.dto.*;
import com.zoeeasy.cloud.ucc.user.dto.request.*;
import com.zoeeasy.cloud.ucc.user.dto.result.UserDetailResultDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserListResultDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserPermissionListResultDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserProfileResultDto;

/**
 * 用户管理服务
 *
 * @author walkman
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param requestDto
     * @return
     */
    ResultDto createUser(UserCreateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 编辑用户
     *
     * @param requestDto
     * @return
     */
    ResultDto editUser(UserEditRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 重置用户密码
     *
     * @param requestDto
     * @return
     */
    ResultDto resetUserPassword(UserPasswordResetRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 注销用户
     *
     * @param requestDto
     * @return
     */
    ResultDto closeUser(UserCloseRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 锁定用户
     *
     * @param requestDto
     * @return
     */
    ResultDto lockUser(UserLockRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 解锁用户
     *
     * @param requestDto
     * @return
     */
    ResultDto unlockUser(UserUnlockRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除用户
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteUser(UserDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改密码
     *
     * @param requestDto
     * @return
     */
    ResultDto modifyPassword(UserPasswordModifyRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取用户
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserDetailResultDto> getUser(UserGetRequestDto requestDto);

    /**
     * 分页查询用户列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<UserListResultDto> getUserPagedList(UserPagedListRequestDto requestDto);

    /**
     * 获取用户角色列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<UserRoleResultDto> getUserRoleList(UserRoleListGetRequestDto requestDto);

    /**
     * 获取用户权限列表
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserPermissionListResultDto> getUserPermissionList(UserPermissionListGetRequestDto requestDto);

    /**
     * 获取用户菜单列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<UserMenuResultDto> getUserMenuList(UserMenuListGetRequestDto requestDto);

    /**
     * 用户更新个人头像
     *
     * @param requestDto
     * @return
     */
    ResultDto updateUserPortrait(UserPortraitUpdateRequestDto requestDto);

    /**
     * 获取用户个人信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserProfileResultDto> getUserProfile(UserProfileGetRequestDto requestDto);

    /**
     * 用户登录时更新
     *
     * @param requestDto
     * @return
     */
    ResultDto loginUpdateUser(UserUpdateLoginRequestDto requestDto);

    /**
     * 获取审核停车场人员列表
     * @param requestDto
     * @return
     */
    ListResultDto<UserListResultDto> getAuditUserList(AuditUserListGetRequestDto requestDto);

}
