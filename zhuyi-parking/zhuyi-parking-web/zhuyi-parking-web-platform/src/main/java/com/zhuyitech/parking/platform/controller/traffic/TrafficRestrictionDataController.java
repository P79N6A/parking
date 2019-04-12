package com.zhuyitech.parking.platform.controller.traffic;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.*;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionCityPageResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionPolicyGetResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionPolicyResultDto;
import com.zhuyitech.parking.tool.service.api.TrafficRestrictionCityService;
import com.zhuyitech.parking.tool.service.api.TrafficRestrictionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 限行后台数据
 *
 * @author AkeemSuper
 * @date 2018/5/18 0018
 */
@Api(value = "限行后台数据", description = "限行后台数据api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/trafficRestrictionData")
public class TrafficRestrictionDataController {
    @Autowired
    private TrafficRestrictionService trafficRestrictionService;

    @Autowired
    private TrafficRestrictionCityService trafficRestrictionCityService;

    /**
     * 同步支持限行的城市
     */
    @ApiOperation(value = "同步支持限行的城市", notes = "获取用户当前所在城市的当天的限行信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/syncTrafficRestrictionCity", name = "获取用户当前所在城市的当天的限行信息", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto syncTrafficRestrictionCity() {
        return trafficRestrictionService.syncTrafficRestrictionCity();
    }

    /**
     * 同步限行详情
     */
    @ApiOperation(value = "同步限行详情", notes = "同步限行详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/syncTrafficRestrictionInfo", name = "同步限行详情", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto syncTrafficRestrictionInfo() {
        return trafficRestrictionService.syncTrafficRestrictionInfo();
    }

    /**
     * 添加限行城市
     */
    @ApiOperation(value = "添加限行城市", notes = "添加限行城市", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/add", name = "添加限行城市", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto add(TrafficRestrictionCityAddRequestDto requestDto) {
        return trafficRestrictionCityService.add(requestDto);
    }


    /**
     * 添加限行政策
     */
    @ApiOperation(value = "添加限行政策", notes = "添加限行政策", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/addPolicy", name = "添加限行政策", method = RequestMethod.POST)
    @ApiVersion(2)
    ResultDto addPolicy(TrafficRestrictionPolicyAddRequestDto requestDto) {
        return trafficRestrictionCityService.addPolicy(requestDto);
    }

    /**
     * 删除限行城市
     */
    @ApiOperation(value = "删除限行城市", notes = "删除限行城市", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/deletedCity", name = "删除限行城市", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto deletedCity(TrafficRestrictionCityDeletedRequestDto requestDto) {
        return trafficRestrictionCityService.deletedCity(requestDto);
    }

    /**
     * 删除限行政策
     */
    @ApiOperation(value = "后台删除限行政策", notes = "后台删除限行政策", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/deletedPolicy", name = "后台删除限行政策", method = RequestMethod.POST)
    @ApiVersion(2)
    ResultDto deletedPolicy(TrafficRestrictionPolicyDeletedRequestDto requestDto) {
        return trafficRestrictionCityService.deletedPolicy(requestDto);
    }

    /**
     * 后台修改限行政策
     */
    @ApiOperation(value = "后台修改限行政策", notes = "后台修改限行政策", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/updatePolicy", name = "后台修改限行政策", method = RequestMethod.POST)
    @ApiVersion(2)
    ResultDto updatePolicy(TrafficRestrictionPolicyUpdateRequestDto requestDto) {
        return trafficRestrictionCityService.updatePolicy(requestDto);
    }

    /**
     * 修改限行城市
     */
    @ApiOperation(value = "修改限行城市", notes = "修改限行城市", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/updateCity", name = "修改限行城市", method = RequestMethod.POST)
    @ApiVersion(2)
    ResultDto updateCity(TrafficRestrictionCityUpdateRequestDto requestDto) {
        return trafficRestrictionCityService.updateCity(requestDto);
    }

    /**
     * 分页获取限行城市
     */
    @ApiOperation(value = "分页获取限行城市", notes = "分页获取限行城市", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TrafficRestrictionCityPageResultDto.class)
    @RequestMapping(value = "/page", name = "分页获取限行城市", method = RequestMethod.POST)
    @ApiVersion(2)
    PagedResultDto<TrafficRestrictionCityPageResultDto> page(TrafficRestrictionCityGetPageRequestDto requestDto) {
        return trafficRestrictionCityService.page(requestDto);
    }

    /**
     * 获取限行城市
     */
    @ApiOperation(value = "获取限行城市", notes = "获取限行城市", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TrafficRestrictionPolicyGetResultDto.class)
    @RequestMapping(value = "/getCity", name = "获取限行城市", method = RequestMethod.POST)
    @ApiVersion(2)
    ObjectResultDto<TrafficRestrictionPolicyGetResultDto> getCity(TrafficRestrictionGetCityRequestDto requestDto) {
        return trafficRestrictionCityService.getCity(requestDto);
    }

    /**
     * 后天分页获取限行政策
     */
    @ApiOperation(value = "后天分页获取限行政策", notes = "后天分页获取限行政策", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TrafficRestrictionPolicyResultDto.class)
    @RequestMapping(value = "/getPolicyByPage", name = "后天分页获取限行政策", method = RequestMethod.POST)
    @ApiVersion(2)
    PagedResultDto<TrafficRestrictionPolicyResultDto> getPolicyByPage(TrafficRestrictionGetPolicyByPageRequestDto requestDto) {
        return trafficRestrictionCityService.getPolicyByPage(requestDto);
    }

    /**
     * 后天获取限行政策
     */
    @ApiOperation(value = "后天获取限行政策", notes = "后天获取限行政策", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TrafficRestrictionPolicyResultDto.class)
    @RequestMapping(value = "/getPolicy", name = "后天获取限行政策", method = RequestMethod.POST)
    @ApiVersion(2)
    ObjectResultDto<TrafficRestrictionPolicyResultDto> getPolicy(TrafficRestrictionPolicyGetRequestDto requestDto) {
        return trafficRestrictionCityService.getPolicy(requestDto);
    }

    /**
     * 限行政策车辆类型
     */
    @ApiOperation(value = "限行政策车辆类型", notes = "限行政策车辆类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @RequestMapping(value = "/carType", name = "限行政策车辆类型", method = RequestMethod.POST)
    @ApiVersion(2)
    public ListResultDto<ComboboxItemDto> carType() {
        return trafficRestrictionCityService.carType();
    }

    /**
     * 限行城市车辆类型
     */
    @ApiOperation(value = "限行政策车辆类型", notes = "限行政策车辆类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @RequestMapping(value = "/cityCarType", name = "限行政策车辆类型", method = RequestMethod.POST)
    @ApiVersion(2)
    public ListResultDto<ComboboxItemDto> limitCityCarType() {
        return trafficRestrictionCityService.limitCityCarType();
    }

}
