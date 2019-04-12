package com.zhuyitech.parking.tool.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.config.AmapConfig;
import com.zhuyitech.parking.tool.constant.AmapConstant;
import com.zhuyitech.parking.tool.domain.WeatherInfo;
import com.zhuyitech.parking.tool.dto.request.AdCodeRequestDto;
import com.zhuyitech.parking.tool.dto.request.region.RegionQueryPagedResultRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.WeatherDeleteRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.WeatherGetByDateRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.WeatherGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.WeatherPutInRequestDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.*;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.enums.WeatherDescribeEnum;
import com.zhuyitech.parking.tool.enums.WeatherReportTypeEnum;
import com.zhuyitech.parking.tool.service.WeatherInfoCrudService;
import com.zhuyitech.parking.tool.service.api.RegionService;
import com.zhuyitech.parking.tool.service.api.WeatherService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.*;

/**
 * WeatherService
 *
 * @author zwq
 */
@Service("weatherService")
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherInfoCrudService weatherInfoCrudService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AmapConfig amapConfig;

    @Override
    public ObjectResultDto<WeatherGetResultDto> weatherGet(WeatherGetRequestDto requestDto) {
        ObjectResultDto<WeatherGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isEmpty(requestDto.getAdcode())) {
                return objectResultDto.makeResult(ToolResultEnum.ADCODE_EMPTY.getValue(), ToolResultEnum.ADCODE_EMPTY.getComment());
            }
            Map<String, Object> params = new HashMap<>();
            params.put("key", amapConfig.getKey());
            params.put("city", requestDto.getAdcode());
            params.put("extensions", requestDto.getExtensions());
            String response = Requests.get(amapConfig.getPrefix() + AmapConstant.WEATHER_INFO_URL).verify(false).followRedirect(false).
                    forms(params).charset(Charset.forName("UTF-8")).send().readToText();
            JSONObject json = JSONObject.parseObject(response);
            String status = json.getString("status");
            if (status.equals("0")) {
                return objectResultDto.makeResult(ToolResultEnum.GET_WEATHER_INFO_ERROR.getValue(), ToolResultEnum.GET_WEATHER_INFO_ERROR.getComment());
            }
            JSONArray resultArray = json.getJSONArray("forecasts");
            List<WeatherInfoForecast> weatherInfoForecastList = new ArrayList();
            Integer count = json.getInteger("count");
            String infocode = json.getString("infocode");
            String info = json.getString("info");
            if (resultArray != null && !resultArray.isEmpty()) {
                for (int i = 0; i < resultArray.size(); i++) {
                    JSONObject obj;
                    try {
                        obj = (JSONObject) resultArray.get(i);
                    } catch (Exception e) {
                        continue;
                    }
                    //JSONObject obj = (JSONObject) resultArray.get(i);
                    String province = obj.getString("province");
                    String adcode = obj.getString("adcode");
                    String city = obj.getString("city");
                    String reporttime = obj.getString("reporttime");
                    JSONArray listArray = obj.getJSONArray("casts");
                    List<WeatherInfoCasts> casts = new ArrayList<>();
                    if (listArray != null && !listArray.isEmpty()) {
                        for (int j = 0; j < listArray.size(); j++) {
                            JSONObject objSub;
                            try {
                                objSub = (JSONObject) listArray.get(j);
                            } catch (Exception e) {
                                continue;
                            }
                            //JSONObject objSub = (JSONObject) listArray.get(j);
                            String date = objSub.getString("date");
                            String week = objSub.getString("week");
                            String dayweather = objSub.getString("dayweather");
                            String nightweather = objSub.getString("nightweather");
                            String daytemp = objSub.getString("daytemp");
                            String nighttemp = objSub.getString("nighttemp");
                            String daywind = objSub.getString("daywind");
                            String nightwind = objSub.getString("nightwind");
                            String daypower = objSub.getString("daypower");
                            String nightpower = objSub.getString("nightpower");
                            WeatherInfoCasts weatherInfoCasts = new WeatherInfoCasts();
                            weatherInfoCasts.setDate(date);
                            weatherInfoCasts.setWeek(week);
                            weatherInfoCasts.setDayweather(dayweather);
                            weatherInfoCasts.setNightweather(nightweather);
                            weatherInfoCasts.setDaytemp(daytemp);
                            weatherInfoCasts.setNighttemp(nighttemp);
                            weatherInfoCasts.setDaywind(daywind);
                            weatherInfoCasts.setNightwind(nightwind);
                            weatherInfoCasts.setDaypower(daypower);
                            weatherInfoCasts.setNightpower(nightpower);
                            casts.add(weatherInfoCasts);
                        }
                        WeatherInfoForecast weatherInfoForecast = new WeatherInfoForecast();
                        weatherInfoForecast.setAdcode(adcode);
                        weatherInfoForecast.setCasts(casts);
                        weatherInfoForecast.setCity(city);
                        weatherInfoForecast.setProvince(province);
                        weatherInfoForecast.setReporttime(reporttime);
                        weatherInfoForecastList.add(weatherInfoForecast);
                    }
                }
            }
            JSONArray livesArray = json.getJSONArray("lives");
            List<WeatherInfoLives> lives = new ArrayList<>();
            if (livesArray != null && !livesArray.isEmpty()) {
                for (int i = 0; i < livesArray.size(); i++) {
                    JSONObject obj;
                    try {
                        obj = (JSONObject) livesArray.get(i);
                    } catch (Exception e) {
                        continue;
                    }
                    String province = obj.getString("province");
                    String adcode = obj.getString("adcode");
                    String city = obj.getString("city");
                    String weather = obj.getString("weather");
                    String temperature = obj.getString("temperature");
                    String winddirection = obj.getString("winddirection");
                    String windpower = obj.getString("windpower");
                    String humidity = obj.getString("humidity");
                    WeatherInfoLives weatherInfoLives = new WeatherInfoLives();
                    weatherInfoLives.setProvince(province);
                    weatherInfoLives.setAdcode(adcode);
                    weatherInfoLives.setCity(city);
                    weatherInfoLives.setWeather(weather);
                    weatherInfoLives.setTemperature(temperature);
                    weatherInfoLives.setWinddirection(winddirection);
                    weatherInfoLives.setWindpower(windpower);
                    weatherInfoLives.setHumidity(humidity);
                    lives.add(weatherInfoLives);
                }
            }
            WeatherGetResultDto weatherGetResultDto = new WeatherGetResultDto();
            weatherGetResultDto.setForecasts(weatherInfoForecastList);
            weatherGetResultDto.setLives(lives);
            weatherGetResultDto.setCount(count);
            weatherGetResultDto.setInfo(info);
            weatherGetResultDto.setStatus(status);
            weatherGetResultDto.setInfocode(infocode);
            objectResultDto.setData(weatherGetResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("天气信息请求失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取用户所在地区的天气信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<CurrentWeatherResultDto> getCurrentWeather(AdCodeRequestDto requestDto) {
        ObjectResultDto<CurrentWeatherResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isEmpty(requestDto.getAdCode())) {
                return objectResultDto.makeResult(ToolResultEnum.ADCODE_EMPTY.getValue(), ToolResultEnum.ADCODE_EMPTY.getComment());
            }
            //实时天气
            WeatherInfo weatherInfoLives = weatherInfoCrudService.selectCurrentWeather(requestDto.getAdCode(), WeatherReportTypeEnum.LIVES.getValue());
            CurrentWeatherResultDto currentWeatherResultDto = new CurrentWeatherResultDto();
            if (null != weatherInfoLives) {
                currentWeatherResultDto.setTemperature(weatherInfoLives.getTemperature());
                currentWeatherResultDto.setWeather(weatherInfoLives.getWeather());
                currentWeatherResultDto.setWeatherCode(WeatherDescribeEnum.parse(weatherInfoLives.getWeather()).getValue());
            }
            //预报天气
            WeatherInfo weatherInfoForecast = weatherInfoCrudService.selectForecastWeather(requestDto.getAdCode(), WeatherReportTypeEnum.FORECAST.getValue(), DateUtils.getDate("yyyy-MM-dd"));
            if (null != weatherInfoForecast) {
                currentWeatherResultDto.setDaytemp(weatherInfoForecast.getDaytemp());
                currentWeatherResultDto.setNighttemp(weatherInfoForecast.getNighttemp());
            }
            objectResultDto.setData(currentWeatherResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户天气信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto weatherLivesPutIn(WeatherPutInRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RegionQueryPagedResultRequestDto regionQueryPagedResultRequestDto = new RegionQueryPagedResultRequestDto();
            Integer totalPage = 1;
            regionQueryPagedResultRequestDto.setPageSize(amapConfig.getPageSize());
            PagedResultDto<RegionResultDto> pagedResultDto;
            WeatherGetRequestDto weatherGetRequestDto = new WeatherGetRequestDto();
            do {
                regionQueryPagedResultRequestDto.setPageNo(totalPage);
                pagedResultDto = regionService.getRegionPagedList(regionQueryPagedResultRequestDto);
                if (null == pagedResultDto) {
                    log.info("第" + (totalPage + 1) + "页region表查询为空");
                    break;
                }
                for (RegionResultDto regionResultDto : pagedResultDto.getItems()) {
                    weatherGetRequestDto.setAdcode(regionResultDto.getAdCode());
                    weatherGetRequestDto.setExtensions("base");
                    ObjectResultDto<WeatherGetResultDto> objectResultDto = weatherGet(weatherGetRequestDto);
                    if (objectResultDto.isFailed() || null == objectResultDto.getData()) {
                        log.info("获取地区天气失败" + regionResultDto.getAdCode());
                        continue;
                    }
                    WeatherGetResultDto weatherGetResultDto = objectResultDto.getData();
                    if (weatherGetResultDto.getStatus().equals("1")) {
                        List<WeatherInfoLives> list = weatherGetResultDto.getLives();
                        if (null == list || list.size() <= 0) {
                            log.info("获取地区天气失败" + regionResultDto.getAdCode());
                            continue;
                        }
                        WeatherInfoLives weatherInfoLives = list.get(0);
                        if (StringUtils.isEmpty(weatherInfoLives.getAdcode())) {
                            log.info("adcode返回为空");
                            continue;
                        }
                        WeatherInfo weatherInfo = modelMapper.map(weatherInfoLives, WeatherInfo.class);
                        weatherInfo.setReportType(WeatherReportTypeEnum.LIVES.getValue());
                        weatherInfo.setReporttime(new Date());
                        weatherInfoCrudService.insert(weatherInfo);
                    } else {
                        log.info("获取地区天气失败" + regionResultDto.getAdCode());
                    }
                }
                totalPage++;
            }
            while (totalPage <= Math.ceil(Double.valueOf(pagedResultDto.getTotalCount()) / Double.valueOf(pagedResultDto.getPageSize())));
            resultDto.success();
        } catch (Exception e) {
            log.error("天气信息插入失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto weatherForecastsPutIn(WeatherPutInRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RegionQueryPagedResultRequestDto regionQueryPagedResultRequestDto = new RegionQueryPagedResultRequestDto();
            Integer totalPage = 1;
            regionQueryPagedResultRequestDto.setPageSize(amapConfig.getPageSize());
            PagedResultDto<RegionResultDto> pagedResultDto;
            WeatherGetRequestDto weatherGetRequestDto = new WeatherGetRequestDto();
            do {
                regionQueryPagedResultRequestDto.setPageNo(totalPage);
                pagedResultDto = regionService.getRegionPagedList(regionQueryPagedResultRequestDto);
                if (pagedResultDto.isFailed() || CollectionUtils.isEmpty(pagedResultDto.getItems())) {
                    log.info("第" + (totalPage + 1) + "页region表查询为空");
                    continue;
                }
                for (RegionResultDto regionResultDto : pagedResultDto.getItems()) {
                    weatherGetRequestDto.setAdcode(regionResultDto.getAdCode());
                    weatherGetRequestDto.setExtensions("all");
                    ObjectResultDto<WeatherGetResultDto> objectResultDto = weatherGet(weatherGetRequestDto);
                    if (objectResultDto.isFailed() || null == objectResultDto.getData()) {
                        log.info("获取地区天气失败" + regionResultDto.getAdCode());
                        continue;
                    }
                    WeatherGetResultDto weatherGetResultDto = objectResultDto.getData();
                    if (weatherGetResultDto.getStatus().equals("1")) {
                        List<WeatherInfoForecast> list = weatherGetResultDto.getForecasts();
                        if (CollectionUtils.isEmpty(list)) {
                            log.info("获取地区天气失败" + regionResultDto.getAdCode());
                            continue;
                        }
                        WeatherInfoForecast weatherInfoForecast = list.get(0);
                        if (StringUtils.isEmpty(weatherInfoForecast.getAdcode())) {
                            log.info("adcode返回为空");
                            continue;
                        }
                        List<WeatherInfoCasts> castsList = weatherInfoForecast.getCasts();
                        if (CollectionUtils.isEmpty(castsList)) {
                            log.info("获取地区天气失败" + regionResultDto.getAdCode());
                            continue;
                        }
                        WeatherInfoCasts weatherInfoCasts = castsList.get(0);
                        WeatherInfo weatherInfo = modelMapper.map(weatherInfoCasts, WeatherInfo.class);
                        weatherInfo.setReportType(WeatherReportTypeEnum.FORECAST.getValue());
                        weatherInfo.setReporttime(new Date());
                        weatherInfo.setForecastTime(weatherInfoCasts.getDate());
                        weatherInfo.setAdcode(weatherInfoForecast.getAdcode());
                        weatherInfoCrudService.insert(weatherInfo);
                    } else {
                        log.info("获取地区{}天气失败", regionResultDto.getAdCode());
                    }
                }
                totalPage++;
            }
            while (totalPage <= Math.ceil(Double.valueOf(pagedResultDto.getTotalCount()) / Double.valueOf(pagedResultDto.getPageSize())));
            resultDto.success();
        } catch (Exception e) {
            log.error("天气信息插入失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto weatherForecasts(WeatherPutInRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RegionQueryPagedResultRequestDto regionQueryPagedResultRequestDto = new RegionQueryPagedResultRequestDto();
            Integer totalPage = 1;
            regionQueryPagedResultRequestDto.setPageSize(amapConfig.getPageSize());
            PagedResultDto<RegionResultDto> pagedResultDto;
            WeatherGetRequestDto weatherGetRequestDto = new WeatherGetRequestDto();
            do {
                regionQueryPagedResultRequestDto.setPageNo(totalPage);
                pagedResultDto = regionService.getRegionPagedList(regionQueryPagedResultRequestDto);
                if (pagedResultDto.isFailed() || CollectionUtils.isEmpty(pagedResultDto.getItems())) {
                    log.info("第" + (totalPage + 1) + "页region表查询为空");
                    continue;
                }

                for (RegionResultDto regionResultDto : pagedResultDto.getItems()) {
                    weatherGetRequestDto.setAdcode(regionResultDto.getAdCode());
                    weatherGetRequestDto.setExtensions("all");
                    ObjectResultDto<WeatherGetResultDto> objectResultDto = weatherGet(weatherGetRequestDto);
                    if (objectResultDto.isFailed() || null == objectResultDto.getData()) {
                        log.info("获取地区天气失败" + regionResultDto.getAdCode());
                        continue;
                    }
                    WeatherGetResultDto weatherGetResultDto = objectResultDto.getData();
                    if (weatherGetResultDto.getStatus().equals("1")) {
                        List<WeatherInfoForecast> list = weatherGetResultDto.getForecasts();
                        if (CollectionUtils.isEmpty(list)) {
                            log.info("获取地区天气失败" + regionResultDto.getAdCode());
                            continue;
                        }
                        WeatherInfoForecast weatherInfoForecast = list.get(0);
                        if (StringUtils.isEmpty(weatherInfoForecast.getAdcode())) {
                            log.info("adcode返回为空");
                            continue;
                        }
                        List<WeatherInfoCasts> castsList = weatherInfoForecast.getCasts();
                        if (CollectionUtils.isEmpty(castsList)) {
                            log.info("获取地区天气失败" + regionResultDto.getAdCode());
                            continue;
                        }
                        for (int i = 1; i < castsList.size(); i++) {
                            WeatherInfoCasts weatherInfoCasts = castsList.get(i);
                            WeatherInfo weatherInfo = modelMapper.map(weatherInfoCasts, WeatherInfo.class);
                            weatherInfo.setReportType(WeatherReportTypeEnum.FORECAST.getValue());
                            weatherInfo.setReporttime(new Date());
                            weatherInfo.setForecastTime(weatherInfoCasts.getDate());
                            weatherInfo.setAdcode(weatherInfoForecast.getAdcode());
                            weatherInfoCrudService.insert(weatherInfo);
                        }
                    } else {
                        log.info("获取地区天气失败" + regionResultDto.getAdCode());
                    }
                }
                totalPage++;
            }
            while (totalPage <= Math.ceil(Double.valueOf(pagedResultDto.getTotalCount()) / Double.valueOf(pagedResultDto.getPageSize())));
            resultDto.success();
        } catch (Exception e) {
            log.error("天气信息插入失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取指定城市指定日期的天气
     *
     * @param requestDto WeatherGetByDateRequestDto
     * @return WeatherResultDto
     */
    @Override
    public ObjectResultDto<WeatherResultDto> getWeatherByDate(WeatherGetByDateRequestDto requestDto) {
        ObjectResultDto<WeatherResultDto> resultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isEmpty(requestDto.getAdCode())) {
                return resultDto.makeResult(ToolResultEnum.ADCODE_EMPTY.getValue(), ToolResultEnum.ADCODE_EMPTY.getComment());
            }
            //实时天气
            WeatherInfo weatherInfoLives = weatherInfoCrudService.selectCurrentWeather(requestDto.getAdCode(), WeatherReportTypeEnum.LIVES.getValue());
            WeatherResultDto weatherResultDto = new WeatherResultDto();
            if (null != weatherInfoLives) {
                weatherResultDto.setTemperature(weatherInfoLives.getTemperature());
                weatherResultDto.setWeather(weatherInfoLives.getWeather());
                weatherResultDto.setWeatherCode(WeatherDescribeEnum.parse(weatherInfoLives.getWeather()).getValue());
            }
            //预报天气
            WeatherInfo weatherInfoForecast = weatherInfoCrudService.selectForecastWeather(requestDto.getAdCode(), WeatherReportTypeEnum.FORECAST.getValue(), DateUtils.formatDate(requestDto.getDate(), "yyyy-MM-dd"));
            if (null != weatherInfoForecast) {
                weatherResultDto.setDaytemp(weatherInfoForecast.getDaytemp());
                weatherResultDto.setNighttemp(weatherInfoForecast.getNighttemp());
                weatherResultDto.setWeather(weatherInfoForecast.getDayweather());
                weatherResultDto.setWeatherCode(WeatherDescribeEnum.parse(weatherInfoForecast.getDayweather()).getValue());
            }
            resultDto.setData(weatherResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取天气信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto deleteWeatherBefore(WeatherDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Date date = DateUtils.sub(new Date(), 7);
            weatherInfoCrudService.deleteWeatherBefore(date);
            resultDto.success();
        } catch (Exception e) {
            log.error("删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
