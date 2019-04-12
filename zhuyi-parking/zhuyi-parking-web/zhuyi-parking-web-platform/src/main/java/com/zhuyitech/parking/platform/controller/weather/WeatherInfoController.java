package com.zhuyitech.parking.platform.controller.weather;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.request.AdCodeRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficRestrictionGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.CurrentWeatherGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.GeocodeGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.WeatherTrafficGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionPolicyInfoGetResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionViewResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.CurrentWeatherResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.RegeoCodeGetResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.WeatherTrafficResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.api.AmapService;
import com.zhuyitech.parking.tool.service.api.TrafficRestrictionService;
import com.zhuyitech.parking.tool.service.api.WeatherService;
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
 * 天气API
 *
 * @author zwq
 */
@Api(value = "天气API", description = "天气API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/weather")
public class WeatherInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherInfoController.class);

    @Autowired
    private TrafficRestrictionService trafficRestrictionService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private AmapService amapService;

    /**
     * 获取当前天气信息
     */
    @ApiOperation(value = "获取当前天气信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CurrentWeatherResultDto.class)
    @RequestMapping(value = "/current", method = RequestMethod.POST)
    public ObjectResultDto<CurrentWeatherResultDto> weatherUserGet(CurrentWeatherGetRequestDto requestDto) {
        ObjectResultDto<CurrentWeatherResultDto> resultDto = new ObjectResultDto<>();
        if ((null == requestDto.getLatitude() || null == requestDto.getLongitude())
                && StringUtils.isEmpty(requestDto.getAdCode())) {
            return resultDto.makeResult(ToolResultEnum.ADCODE_EMPTY_OR_LOCATION_LONGITUDE_LATITUDE_EMPTY.getValue(), ToolResultEnum.ADCODE_EMPTY_OR_LOCATION_LONGITUDE_LATITUDE_EMPTY.getComment());
        }
        try {
            //获取AdCode
            String adCode = getAdCode(requestDto.getLongitude(), requestDto.getLatitude(), requestDto.getAdCode());
            if (StringUtils.isEmpty(adCode)) {
                return resultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
            }
            AdCodeRequestDto adCodeRequestDto = new AdCodeRequestDto();
            adCodeRequestDto.setAdCode(adCode);
            resultDto = weatherService.getCurrentWeather(adCodeRequestDto);
        } catch (Exception e) {
            LOG.error("获取首页尾号限行和天气信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取首页尾号限行和天气信息
     */
    @ApiOperation(value = "获取首页尾号限行和天气信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WeatherTrafficResultDto.class)
    @RequestMapping(value = "/weathertraffic", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<WeatherTrafficResultDto> getWeatherAndTailNumber(WeatherTrafficGetRequestDto requestDto) {
        ObjectResultDto<WeatherTrafficResultDto> resultDto = new ObjectResultDto<>();
        if ((null == requestDto.getLatitude() || null == requestDto.getLongitude())
                && StringUtils.isEmpty(requestDto.getAdCode())) {
            return resultDto.makeResult(ToolResultEnum.ADCODE_EMPTY_OR_LOCATION_LONGITUDE_LATITUDE_EMPTY.getValue(), ToolResultEnum.ADCODE_EMPTY_OR_LOCATION_LONGITUDE_LATITUDE_EMPTY.getComment());
        }
        try {

            //获取AdCode
            String adCode = getAdCode(requestDto.getLongitude(), requestDto.getLatitude(), requestDto.getAdCode());
            if (StringUtils.isEmpty(adCode)) {
                return resultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
            }
            WeatherTrafficResultDto weatherTrafficResultDto = new WeatherTrafficResultDto();
            AdCodeRequestDto adCodeRequestDto = new AdCodeRequestDto();
            adCodeRequestDto.setAdCode(adCode);
            ObjectResultDto<CurrentWeatherResultDto> currentWeather = weatherService.getCurrentWeather(adCodeRequestDto);
            if (currentWeather.isFailed() || null == currentWeather.getData()) {
                return resultDto.makeResult(currentWeather.getCode(), currentWeather.getMessage());
            }
            weatherTrafficResultDto.setWeather(currentWeather.getData());
            ObjectResultDto<TrafficRestrictionViewResultDto> trafficRestriction = trafficRestrictionService.getTrafficRestrictionInfoForIndex(adCodeRequestDto);
            if (currentWeather.isSuccess() && trafficRestriction.getData() != null) {
                weatherTrafficResultDto.setTrafficRestriction(trafficRestriction.getData());
            }
            resultDto.setData(weatherTrafficResultDto);
            resultDto.success();
        } catch (Exception e) {
            LOG.error("获取首页尾号限行和天气信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @ApiOperation(value = "获取用户当前所在城市的当天的限行信息", notes = "获取用户当前所在城市的当天的限行信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TrafficRestrictionPolicyInfoGetResultDto.class)
    @RequestMapping(value = "/trafficinfo", name = "获取用户当前所在城市的当天的限行信息", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<TrafficRestrictionPolicyInfoGetResultDto> getTrafficRestrictionInfo(TrafficRestrictionGetRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionPolicyInfoGetResultDto> resultDto = new ObjectResultDto<>();
        if ((null == requestDto.getLatitude() || null == requestDto.getLongitude()) &&
                StringUtils.isEmpty(requestDto.getAdCode())) {
            return resultDto.makeResult(ToolResultEnum.ADCODE_EMPTY_OR_LOCATION_LONGITUDE_LATITUDE_EMPTY.getValue(), ToolResultEnum.ADCODE_EMPTY_OR_LOCATION_LONGITUDE_LATITUDE_EMPTY.getComment());
        }
        try {

            //获取AdCode
            String adCode = getAdCode(requestDto.getLongitude(), requestDto.getLatitude(), requestDto.getAdCode());
            if (StringUtils.isEmpty(adCode)) {
                return resultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
            }
            AdCodeRequestDto adCodeRequestDto = new AdCodeRequestDto();
            adCodeRequestDto.setAdCode(adCode);
            resultDto = trafficRestrictionService.getTrafficRestrictionInfo(adCodeRequestDto);
        } catch (Exception e) {
            LOG.error("获取用户当前所在城市的当天的限行信息" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取地址编码
     *
     * @param longitude longitude
     * @param latitude  latitude
     * @param adCode    adCode
     * @return
     */
    private String getAdCode(Double longitude, Double latitude, String adCode) {
        String retVal = adCode;
        if (StringUtils.isEmpty(adCode)) {
            if (latitude == null || longitude == null) {
                return null;
            }
            GeocodeGetRequestDto geocodeGetRequestDto = new GeocodeGetRequestDto();
            geocodeGetRequestDto.setLocation(longitude + "," + latitude);
            ObjectResultDto<RegeoCodeGetResultDto> objectResultDtoGeocode = amapService.geocoderGet(geocodeGetRequestDto);
            if (objectResultDtoGeocode.isFailed() || null == objectResultDtoGeocode.getData()) {
                return null;
            }
            retVal = objectResultDtoGeocode.getData().getAdCode();
        }
        return retVal;
    }

}
