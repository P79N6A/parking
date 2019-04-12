package com.zoeeasy.cloud.ucc.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.ucc.permission.PermissionService;
import com.zoeeasy.cloud.ucc.permission.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("permissionService")
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Override
    public ResultDto addPermission(PermissionAddRequestDto requestDto) {
        return null;
    }

    @Override
    public ResultDto deletePermission(PermissionDeleteRequestDto requestDto) {
        return null;
    }

    @Override
    public ResultDto updatePermission(PermissionUpdateRequestDto requestDto) {
        return null;
    }

    @Override
    public ObjectResultDto<PermissionResultDto> getPermission(PermissionGetRequestDto requestDto) {
        return null;
    }

    @Override
    public ListResultDto<PermissionResultDto> getPermissionList(PermissionListGetRequestDto requestDto) {
        return null;
    }

    @Override
    public PagedResultDto<PermissionResultDto> getPermissionPagedList(PermissionQueryPagedResultRequestDto requestDto) {
        return null;
    }
}
