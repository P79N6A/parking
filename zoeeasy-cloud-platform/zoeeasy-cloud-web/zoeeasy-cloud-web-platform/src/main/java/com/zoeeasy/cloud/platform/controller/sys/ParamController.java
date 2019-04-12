package com.zoeeasy.cloud.platform.controller.sys;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.sys.parameter.ParameterService;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamItemGetByIdRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamItemGetRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamItemUpdateRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamTypeRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.result.ParamItemResultDto;
import com.zoeeasy.cloud.sys.parameter.dto.result.ParamTypeResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数相关控制器
 *
 * @author walkman
 */
@Api(value = "参数相关Api", tags = "参数相关Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/sys/param")
@ApiSigned
public class ParamController {

    @Autowired
    private ParameterService parameterService;

    /**
     * 获取参数类型列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取参数类型列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParamTypeResultDto.class)
    @PostMapping(value = "/getTypeList")
    public ListResultDto<ParamTypeResultDto> getParamTypeList(@RequestBody ParamTypeRequestDto requestDto) {
        return parameterService.getParamTypeList(requestDto);
    }

    /**
     * 根据参数类型获取参数项列表
     */
    @ApiOperation(value = "根据参数类型获取参数项列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParamItemResultDto.class)
    @PostMapping(value = "/getItemList")
    public ListResultDto<ParamItemResultDto> getParamItemList(@RequestBody ParamItemGetRequestDto requestDto) {
        return parameterService.getParamItemList(requestDto);
    }

    /**
     * 根据参数类型获取参数项列表
     */
    @ApiOperation(value = "根据参数项ID获取参数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParamItemResultDto.class)
    @PostMapping(value = "/getItem")
    public ObjectResultDto<ParamItemResultDto> getParamById(@RequestBody ParamItemGetByIdRequestDto requestDto) {
        return parameterService.getParamItemById(requestDto);
    }

    /**
     * 修改参数
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改参数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/updateItem")
    @AuditLog(title = "参数相关Api", value = "修改参数", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateParamItem(@RequestBody ParamItemUpdateRequestDto requestDto) {
        return parameterService.updateParam(requestDto);
    }

}
