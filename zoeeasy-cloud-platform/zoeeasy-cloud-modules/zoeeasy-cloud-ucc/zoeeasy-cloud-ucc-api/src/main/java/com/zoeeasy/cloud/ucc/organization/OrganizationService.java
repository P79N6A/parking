package com.zoeeasy.cloud.ucc.organization;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.organization.dto.*;

/**
 * 组织部门服务
 *
 * @author walkman
 */
public interface OrganizationService {

    /**
     * 获取部门列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<OrganizationListResultDto> getOrganizationList(OrganizationPagedRequestDto requestDto);

    /**
     * 获取部门
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<OrganizationResultDto> getOrganization(OrganizationGetRequestDto requestDto);

    /**
     * 创建部门
     *
     * @param requestDto
     * @return
     */
    ResultDto createOrganization(OrganizationCreateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 编辑部门
     *
     * @param requestDto
     * @return
     */
    ResultDto editOrganization(OrganizationEditRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除部门
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteOrganization(OrganizationDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取部门树列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<OrganizationTreeResultDto> getOrganizationTree(OrganizationTreeRequestDto requestDto);

}
