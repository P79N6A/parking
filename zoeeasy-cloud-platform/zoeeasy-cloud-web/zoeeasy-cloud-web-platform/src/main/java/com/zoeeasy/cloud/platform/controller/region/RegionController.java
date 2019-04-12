package com.zoeeasy.cloud.platform.controller.region;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.tool.region.RegionService;
import com.zoeeasy.cloud.tool.region.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 行政区域控制器
 *
 * @author walkman
 */
@Api(value = "行政区域Api", tags = "区域Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/region")
@ApiSigned
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * 新增区域
     *
     * @param requestDto
     * @return
     */
//    @SystemLog("新增区域")
    @ApiOperation(value = "新增区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @AuditLog(title = "行政区域Api", value = "新增区域", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addRegion(RegionAddRequestDto requestDto) {
        return regionService.addRegion(requestDto);
    }

    /**
     * 删除区域
     *
     * @param requestDto
     * @return
     */
//    @SystemLog("删除区域")
    @ApiOperation(value = "删除区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @AuditLog(title = "行政区域Api", value = "删除区域", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteRegion(RegionDeleteRequestDto requestDto) {
        return regionService.deleteRegion(requestDto);
    }

    /**
     * 更新区域
     *
     * @param requestDto
     * @return
     */
//    @SystemLog("更新区域")
    @ApiOperation(value = "更新区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @AuditLog(title = "行政区域Api", value = "更新区域", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateRegion(RegionUpdateRequestDto requestDto) {
        return regionService.updateRegion(requestDto);
    }

    /**
     * 获取区域
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ObjectResultDto.class)
    @PostMapping(value = "/get")
    public ObjectResultDto<RegionResultDto> getRegion(RegionGetRequestDto requestDto) {
        return regionService.getRegion(requestDto);
    }

    /**
     * 获取区域列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取区域列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @PostMapping(value = "/list")
    public ListResultDto<RegionResultDto> getRegionList(RegionListGetRequestDto requestDto) {
        return regionService.getRegionList(requestDto);
    }

    /**
     * 分页获取区域列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取区域列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PagedResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<RegionResultDto> getRegionPagedList(RegionQueryPagedResultRequestDto requestDto) {
        return regionService.getRegionPagedList(requestDto);
    }

    /**
     * 获取省列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取省列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PagedResultDto.class)
    @PostMapping(value = "/provinceList")
    public ListResultDto<RegionViewResultDto> getProvinceList(ProvinceListGetRequestDto requestDto) {
        return regionService.getProvinceList(requestDto);
    }

    /**
     * 获取市列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取市列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PagedResultDto.class)
    @PostMapping(value = "/cityList")
    public ListResultDto<RegionViewResultDto> getCityList(CityListGetRequestDto requestDto) {
        return regionService.getCityList(requestDto);
    }

    /**
     * 获取区县列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取区县列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PagedResultDto.class)
    @PostMapping(value = "/countyList")
    public ListResultDto<RegionViewResultDto> getCountyList(CountyListGetRequestDto requestDto) {
        return regionService.getCountyList(requestDto);
    }

    /**
     * 获取上级区域列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取上级区域列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = RegionGetUpperListResultDto.class)
    @PostMapping(value = "/getUpperList")
    public ListResultDto<RegionGetUpperListResultDto> getUpperList(RegionGetUpperListRequestDto requestDto) {
        return regionService.getUpperList(requestDto);
    }

    /**
     * 获取所有地区列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取所有地区列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = RegionListTotalGetResultDto.class)
    @PostMapping(value = "/getRegionList")
    public ListResultDto<RegionListTotalGetResultDto> getRegionList(RegionListTotalGetRequestDto requestDto) {
        return regionService.getRegionList(requestDto);
    }

    /**
     * 获取子地区列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取子地区列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = RegionGetChildListResultDto.class)
    @PostMapping(value = "/getRegionChildList")
    public ListResultDto<RegionGetChildListResultDto> getRegionChildList(@RequestBody RegionGetChildListRequestDto requestDto) {
        return regionService.getRegionChildList(requestDto);
    }

    @ApiOperation(value = "获取区域树", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TreeResultDto.class)
    @PostMapping(value = "/getRegionTree")
    public ListResultDto<TreeResultDto> getRegionTree(@RequestBody RegionTreeGetRequestDto requestDto) {
        return regionService.getRegionTree(requestDto);
    }
}
