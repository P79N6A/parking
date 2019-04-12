package com.zoeeasy.cloud.platform.controller.area;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.pms.area.AreaService;
import com.zoeeasy.cloud.pms.area.dto.request.*;
import com.zoeeasy.cloud.pms.area.dto.result.AreaParkingResultDto;
import com.zoeeasy.cloud.pms.area.dto.result.AreaParkingTreeResultDto;
import com.zoeeasy.cloud.pms.area.dto.result.AreaResultDto;
import com.zoeeasy.cloud.pms.area.dto.result.TreeResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 区域控制层
 */
@Api(value = "区域管理", tags = "区域管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/area")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_CREATE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_EDIT,
        PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_DELETE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_VIEW,
}, logical = Logical.OR)
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 新增区域
     *
     * @param requestDto AreaAddRequestDto
     * @return ResultDto
     */
    @ApiOperation(value = "新增区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_CREATE)
    @AuditLog(title = "区域管理", value = "新增区域", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addArea(@RequestBody AreaAddRequestDto requestDto) {
        return areaService.addArea(requestDto);
    }

    /**
     * 删除区域
     *
     * @param requestDto AreaDeleteRequestDto
     * @return 删除结果
     */
    @ApiOperation(value = "删除区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_DELETE)
    @AuditLog(title = "区域管理", value = "删除区域", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteArea(@RequestBody AreaDeleteRequestDto requestDto) {
        return areaService.deleteArea(requestDto);
    }

    /**
     * 修改区域
     *
     * @param requestDto AreaUpdateRequestDto
     * @return 修改结果
     */
    @ApiOperation(value = "修改区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_EDIT)
    @AuditLog(title = "区域管理", value = "修改区域", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto updateArea(@RequestBody AreaUpdateRequestDto requestDto) {
        return areaService.updateArea(requestDto);
    }

    /**
     * 获取区域
     *
     * @param requestDto AreaGetRequestDto
     * @return 区域
     */
    @ApiOperation(value = "获取区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/get")
    @RequiresPermissions(value = {PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_VIEW, PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_EDIT}
            , logical = Logical.OR)
    public ObjectResultDto<AreaResultDto> getArea(@RequestBody AreaGetRequestDto requestDto) {
        return areaService.getArea(requestDto);
    }

    /**
     * 分页获取子区域列表
     *
     * @param requestDto AreaQueryPagedResultRequestDto
     * @return 子区域列表
     */
    @ApiOperation(value = "分页获取子区域列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/pagelist")
    public PagedResultDto<AreaResultDto> getAreaPagedList(@RequestBody AreaQueryPagedResultRequestDto requestDto) {
        return areaService.getAreaPagedList(requestDto);
    }

    /**
     * 获取区域树
     *
     * @return 区域树
     */
    @ApiOperation(value = "获取区域树", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TreeResultDto.class)
    @PostMapping("/areatree")
    public ListResultDto<TreeResultDto> getAreaTree(@RequestBody AreaTreeRequestDto requestDto) {
        return areaService.getAreaTree(requestDto);
    }

    /**
     * 获取不包含全国的区域树
     *
     * @return 区域树
     */
    @ApiOperation(value = "获取不包含全国的区域树", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TreeResultDto.class)
    @PostMapping("/parttree")
    public ListResultDto<TreeResultDto> getAreaTreeNotIncludeCountry(@RequestBody AreaTreeNotIncludeCountryRequestDto requestDto) {
        return areaService.getAreaTreeNotIncludeCountry(requestDto);
    }

    /**
     * 获取停车场区域树
     *
     * @return 区域树
     */
    @ApiOperation(value = "获取停车场区域树", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AreaParkingTreeResultDto.class)
    @PostMapping("/parkingareatree")
    public ListResultDto<AreaParkingTreeResultDto> getAreaParkingTree(@RequestBody AreaParkingTreeRequestDto requestDto) {
        return areaService.getAreaParkingTree(requestDto);
    }

    /**
     * 获取标识的停车场区域树
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取标识的停车场区域树", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AreaParkingResultDto.class)
    @PostMapping("/parkingarea")
    public ListResultDto<AreaParkingResultDto> getAreaParking(@RequestBody AreaParkingRequestDto requestDto) {
        return areaService.getAreaParking(requestDto);
    }

}
