package com.zhuyitech.parking.platform.controller.region;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.common.annotation.SystemLog;
import com.zhuyitech.parking.tool.dto.request.region.*;
import com.zhuyitech.parking.tool.dto.result.region.RegionGetUpperListResultDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionListTotalGetResultDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionResultDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionViewResultDto;
import com.zhuyitech.parking.tool.service.api.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 行政区域控制器
 *
 * @author walkman
 */
@Api(value = "行政区域Api", description = "区域Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * 新增区域
     *
     * @param requestDto
     * @return
     */
    @SystemLog("新增区域")
    @ApiOperation(value = "新增区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultDto addRegion(RegionAddRequestDto requestDto) {
        return regionService.addRegion(requestDto);
    }

    /**
     * 删除区域
     *
     * @param requestDto
     * @return
     */
    @SystemLog("删除区域")
    @ApiOperation(value = "删除区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultDto deleteRegion(RegionDeleteRequestDto requestDto) {
        return regionService.deleteRegion(requestDto);
    }

    /**
     * 更新区域
     *
     * @param requestDto
     * @return
     */
    @SystemLog("更新区域")
    @ApiOperation(value = "更新区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
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
    @RequestMapping(value = "/get", method = RequestMethod.POST)
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
    @RequestMapping(value = "/list", method = RequestMethod.POST)
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
    @RequestMapping(value = "/listpage", method = RequestMethod.POST)
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
    @RequestMapping(value = "/provinceList", method = RequestMethod.POST)
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
    @RequestMapping(value = "/cityList", method = RequestMethod.POST)
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
    @RequestMapping(value = "/countyList", method = RequestMethod.POST)
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
    @RequestMapping(value = "/getUpperList", method = RequestMethod.POST)
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
    @RequestMapping(value = "/getRegionList", method = RequestMethod.POST)
    public ListResultDto<RegionListTotalGetResultDto> getRegionList(RegionListTotalGetRequestDto requestDto) {
        return regionService.getRegionList(requestDto);
    }
}
