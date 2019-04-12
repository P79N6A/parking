package com.zoeeasy.cloud.platform.controller.dict;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.sys.dict.DictService;
import com.zoeeasy.cloud.sys.dict.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cloud/dict")
@Api(tags = "字典管理", value = "字典管理", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiSort(101)
@ApiVersion(1)
@ApiSigned
@RequiresAuthentication
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 获取字典类别列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取字典类别列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/types")
   @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_LIST)
    public ListResultDto<DictTypeResultDto> getDictTypeList(@RequestBody DictTypeListRequestDto requestDto) {
        return dictService.getDictTypeList(requestDto);
    }

    /**
     * 获取字典类别
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取字典类别", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/type")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_VIEW)
    public ObjectResultDto<DictTypeResultDto> getDictType(@RequestBody DictTypeGetRequestDto requestDto) {
        return dictService.getDictType(requestDto);
    }

    /**
     * 检验字典类别是否可用
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "检验字典类别是否可用", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/checkType")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_CREATE)
    public ObjectResultDto<DictTypeCheckResultDto> checkDictType(@RequestBody DictTypeCheckRequestDto requestDto) {
        return dictService.checkDictType(requestDto);
    }

    /**
     * 添加字典类型
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加字典类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addType")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_CREATE)
    @AuditLog(title = "字典管理", value = "添加字典类型", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addDictType(@RequestBody DictTypeAddRequestDto requestDto) {
        return dictService.addDictType(requestDto);
    }

    /**
     * 修改字典类型
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改字典类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/updateType")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_EDIT)
    @AuditLog(title = "字典管理", value = "修改字典类型", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateDictType(@RequestBody DictTypeUpdateRequestDto requestDto) {
        return dictService.updateDictType(requestDto);
    }

    /**
     * 删除字典类型
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除字典类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/deleteType")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_DELETE)
    @AuditLog(title = "字典管理", value = "删除字典类型", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteDictType(@RequestBody DictTypeDeleteRequestDto requestDto) {
        return dictService.deleteDictType(requestDto);
    }

    /**
     * 获取字典项列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取字典项列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/items")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_LIST)
    public ListResultDto<DictTypeItemListResultDto> getDictTypeItemList(@RequestBody DictTypeItemListRequestDto requestDto) {
        return dictService.getDictTypeItemList(requestDto);
    }

    /**
     * 获取字典项
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取字典项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/item")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_VIEW)
    public ObjectResultDto<DictItemResultDto> getDictItem(@RequestBody DictItemGetRequestDto requestDto) {
        return dictService.getDictItem(requestDto);
    }

    /**
     * 检验字典项是否可用
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "检验字典项是否可用", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/checkItem")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_CREATE)
    public ObjectResultDto<DictItemCheckResultDto> checkDictItem(@RequestBody DictItemCheckRequestDto requestDto) {
        return dictService.checkDictItem(requestDto);
    }

    /**
     * 添加字典项
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加字典项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addItem")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_CREATE)
    @AuditLog(title = "字典管理", value = "添加字典项", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addDictItem(@RequestBody DictItemAddRequestDto requestDto) {
        return dictService.addDictItem(requestDto);
    }

    /**
     * 修改字典项
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改字典项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/updateItem")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_EDIT)
    @AuditLog(title = "字典管理", value = "修改字典项", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateDictItem(@RequestBody DictItemUpdateRequestDto requestDto) {
        return dictService.updateDictItem(requestDto);
    }

    /**
     * 删除字典项
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除字典项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/deleteItem")
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY_DELETE)
    @AuditLog(title = "字典管理", value = "删除字典项", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteDictItem(@RequestBody DictItemDeleteRequestDto requestDto) {
        return dictService.deleteDictItem(requestDto);
    }
}
