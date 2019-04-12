package com.zhuyitech.parking.platform.controller.traffic;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficCityGetByAdCodeRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficCityGetListByAdCodeRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficRestrictionCityGetListRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficRestrictionCityGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.GeocodeGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionCityGetResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionCityListResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionCityViewListResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionPolicyInfoViewGetResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.RegeoCodeGetResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.api.AmapService;
import com.zhuyitech.parking.tool.service.api.TrafficRestrictionCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 尾号限行controller
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
@Api(value = "尾号限行Api", description = "尾号限行Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/trafficRestriction")
public class TrafficRestrictionController {

    private static final Logger LOG = LoggerFactory.getLogger(TrafficRestrictionController.class);

    @Autowired
    private AmapService amapService;

    @Autowired
    private TrafficRestrictionCityService trafficRestrictionCityService;

    @ApiOperation(value = "获取限行城市列表", notes = "获取限行城市列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TrafficRestrictionCityListResultDto.class)
    @RequestMapping(value = "/cityList", name = "获取限行城市列表", method = RequestMethod.POST)
    @ApiVersion(2)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<TrafficRestrictionCityViewListResultDto> list(TrafficRestrictionCityGetListRequestDto requestDto) {
        ListResultDto<TrafficRestrictionCityViewListResultDto> resultDto = new ListResultDto<>();
        try {
            TrafficCityGetListByAdCodeRequestDto trafficCityGetListByAdCodeRequestDto = new TrafficCityGetListByAdCodeRequestDto();
            String adCode = null;

            if (StringUtils.isEmpty(requestDto.getAdCode())) {
                if (null != requestDto.getLatitude() || null != requestDto.getLongitude()) {
                    GeocodeGetRequestDto geocodeGetRequestDto = new GeocodeGetRequestDto();
                    geocodeGetRequestDto.setLocation(requestDto.getLongitude() + "," + requestDto.getLatitude());
                    ObjectResultDto<RegeoCodeGetResultDto> objectResultDtoGeocode = amapService.geocoderGet(geocodeGetRequestDto);
                    if (objectResultDtoGeocode.isFailed() || null == objectResultDtoGeocode.getData()) {
                        return resultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
                    }
                    adCode = objectResultDtoGeocode.getData().getAdCode();
                }
            } else {
                adCode = requestDto.getAdCode();
            }
            trafficCityGetListByAdCodeRequestDto.setAdCode(adCode);
            trafficCityGetListByAdCodeRequestDto.setCarType(requestDto.getCarType());
            trafficCityGetListByAdCodeRequestDto.setCityName(requestDto.getCityName());
            trafficCityGetListByAdCodeRequestDto.setInitial(requestDto.getInitial());
            resultDto = trafficRestrictionCityService.list(trafficCityGetListByAdCodeRequestDto);
        } catch (Exception e) {
            LOG.error("获取限行城市政策失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @ApiOperation(value = "获取限行城市政策", notes = "获取限行城市政策", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TrafficRestrictionCityGetResultDto.class)
    @RequestMapping(value = "/getInfo", name = "获取限行城市政策", method = RequestMethod.POST)
    @ApiVersion(2)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<TrafficRestrictionPolicyInfoViewGetResultDto> getPolicyInfo(TrafficRestrictionCityGetRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionPolicyInfoViewGetResultDto> resultDto = new ObjectResultDto<>();
        try {
            TrafficCityGetByAdCodeRequestDto trafficCityGetByAdCodeRequestDto = new TrafficCityGetByAdCodeRequestDto();
            String adCode = null;
            if (StringUtils.isEmpty(requestDto.getAdCode())) {
                if (null != requestDto.getLatitude() || null != requestDto.getLongitude()) {
                    GeocodeGetRequestDto geocodeGetRequestDto = new GeocodeGetRequestDto();
                    geocodeGetRequestDto.setLocation(requestDto.getLongitude() + "," + requestDto.getLatitude());
                    ObjectResultDto<RegeoCodeGetResultDto> objectResultDtoGeocode = amapService.geocoderGet(geocodeGetRequestDto);
                    if (objectResultDtoGeocode.isFailed() || null == objectResultDtoGeocode.getData()) {
                        return resultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
                    }
                    adCode = objectResultDtoGeocode.getData().getAdCode();
                }
            } else {
                adCode = requestDto.getAdCode();
            }
            trafficCityGetByAdCodeRequestDto.setAdCode(adCode);
            trafficCityGetByAdCodeRequestDto.setCityId(requestDto.getCityId());
            trafficCityGetByAdCodeRequestDto.setDate(requestDto.getDate());
            resultDto = trafficRestrictionCityService.getPolicyInfo(trafficCityGetByAdCodeRequestDto);
        } catch (Exception e) {
            LOG.error("获取限行城市政策失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
