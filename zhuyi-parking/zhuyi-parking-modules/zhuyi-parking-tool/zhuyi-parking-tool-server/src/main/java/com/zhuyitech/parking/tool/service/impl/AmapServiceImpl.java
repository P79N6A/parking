package com.zhuyitech.parking.tool.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.bean.amap.AddressComponent;
import com.zhuyitech.parking.tool.bean.amap.Regecode;
import com.zhuyitech.parking.tool.bean.amap.RegeoResult;
import com.zhuyitech.parking.tool.config.AmapConfig;
import com.zhuyitech.parking.tool.constant.AmapConstant;
import com.zhuyitech.parking.tool.domain.Region;
import com.zhuyitech.parking.tool.dto.request.location.LocationConvertRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.GeocodeGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.location.LocationConvertResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.AllRegeoCodeGetResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.RegeoCodeGetResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.RegionCrudService;
import com.zhuyitech.parking.tool.service.api.AmapService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高德地图相关服务
 *
 * @author walkman
 * @date 2018/4/29
 */
@Service("amapService")
@Slf4j
public class AmapServiceImpl implements AmapService {

    @Autowired
    private RegionCrudService regionCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AmapConfig amapConfig;

    /**
     * 逆地理获取adcode
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<RegeoCodeGetResultDto> geocoderGet(GeocodeGetRequestDto requestDto) {
        ObjectResultDto<RegeoCodeGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isEmpty(requestDto.getLocation())) {
                return objectResultDto.makeResult(ToolResultEnum.LOCATION_EMPTY.getValue(), ToolResultEnum.LOCATION_EMPTY.getComment());
            }
            Map<String, Object> params = new HashMap<>();
            params.put("key", amapConfig.getKey());
            params.put("location", requestDto.getLocation());
            String response = Requests.post(amapConfig.getPrefix() + AmapConstant.GEO_CODE_REGEO_URL)
                    .verify(false).followRedirect(false).forms(params).charset(Charset.forName("UTF-8"))
                    .connectTimeout(1000 * 60)
                    .socksTimeout(1000 * 60 * 3).send().readToText();
            JSONObject json = JSONObject.parseObject(response);
            String status = json.getString("status");
            JSONObject regeocode = json.getJSONObject("regeocode");
            if ("1".equals(status) && regeocode != null) {

                RegeoResult geocodeGetResultDto = modelMapper.map(json, RegeoResult.class);
                if (geocodeGetResultDto != null && geocodeGetResultDto.getRegeocode() != null
                        && geocodeGetResultDto.getRegeocode().getAddressComponent() != null) {
                    RegeoCodeGetResultDto regeoCodeGetResultDto = new RegeoCodeGetResultDto();
                    regeoCodeGetResultDto.setAdCode(geocodeGetResultDto.getRegeocode().getAddressComponent().getAdcode());
                    objectResultDto.setData(regeoCodeGetResultDto);
                    objectResultDto.success();
                } else {
                    return objectResultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
                }
            } else {
                return objectResultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
            }
        } catch (Exception e) {
            log.error("逆地理获取adcode请求失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取数据库所需的code和更新地址
     *
     * @param requestDto GeocodeGetRequestDto
     * @return 结果
     */
    @Override
    public ObjectResultDto<AllRegeoCodeGetResultDto> geocoderGetAllCode(GeocodeGetRequestDto requestDto) {
        String jsonString = null;
        String location = requestDto.getLocation();
        ObjectResultDto<AllRegeoCodeGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isEmpty(requestDto.getLocation())) {
                return objectResultDto.makeResult(ToolResultEnum.LOCATION_EMPTY.getValue(), ToolResultEnum.LOCATION_EMPTY.getComment());
            }
            Map<String, Object> params = new HashMap<>();
            params.put("key", amapConfig.getKey());
            params.put("location", location);
            String response = Requests.post(AmapConstant.GEO_CODE_REGEO_URL).verify(false).followRedirect(false)
                    .connectTimeout(1000 * 60).socksTimeout(1000 * 60 * 3)
                    .forms(params).charset(Charset.forName("UTF-8")).send().readToText();
            JSONObject json = JSONObject.parseObject(response);
            jsonString = json.toJSONString();
            String status = json.getString("status");
            JSONObject regeocode = json.getJSONObject("regeocode");
            if ("1".equals(status) && regeocode != null) {
                AllRegeoCodeGetResultDto allRegeoCodeGetResultDto = new AllRegeoCodeGetResultDto();
                RegeoResult geocodeGetResultDto = modelMapper.map(json, RegeoResult.class);
                if (geocodeGetResultDto != null && geocodeGetResultDto.getRegeocode() != null
                        && geocodeGetResultDto.getRegeocode().getAddressComponent() != null) {
                    AddressComponent addressComponent = geocodeGetResultDto.getRegeocode().getAddressComponent();
                    String address = geocodeGetResultDto.getRegeocode().getFormatted_address();
                    allRegeoCodeGetResultDto.setAddress(address);
                    allRegeoCodeGetResultDto.setCountyCode(addressComponent.getAdcode());
                    getAdCodeByDb(allRegeoCodeGetResultDto, addressComponent.getDistrict());
                    objectResultDto.setData(allRegeoCodeGetResultDto);
                    objectResultDto.success();
                } else {
                    return objectResultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
                }
            } else {
                return objectResultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
            }
        } catch (Exception e) {
            log.error("逆地理失败jsonString:" + jsonString + "\ne:" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 坐标转换
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<LocationConvertResultDto> convertLocations(LocationConvertRequestDto requestDto) {
        ObjectResultDto<LocationConvertResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            if (requestDto.getLatitude() == null || requestDto.getLongitude() == null) {
                return objectResultDto.makeResult(ToolResultEnum.LOCATION_LONGITUDE_LATITUDE_EMPTY.getValue(), ToolResultEnum.LOCATION_LONGITUDE_LATITUDE_EMPTY.getComment());
            }
            Map<String, Object> params = new HashMap<>();

            params.put("locations", requestDto.getLongitude().toString() + "," + requestDto.getLatitude().toString());
            if (StringUtils.isEmpty(requestDto.getCoordSys())) {
                params.put("coordsys", "gps");
            } else {
                params.put("coordsys", requestDto.getCoordSys());
            }
            params.put("key", amapConfig.getKey());
            String response = Requests.get(amapConfig.getPrefix() + AmapConstant.COORDINATE_CONVERT_URL).verify(false).followRedirect(false)
                    .params(params).charset(Charset.forName("UTF-8")).send().readToText();
            JSONObject json = JSONObject.parseObject(response);
            Integer status = json.getInteger("status");
            if (status.equals(0)) {
                return objectResultDto.makeResult(ToolResultEnum.LOCATION_CONVERT_FAILED.getValue(), ToolResultEnum.LOCATION_CONVERT_FAILED.getComment());
            } else {
                String locations = json.getString("locations");
                String[] locationsArray = locations.split(",");
                if (locationsArray != null && locationsArray.length == 2) {
                    LocationConvertResultDto locationConvertResultDto = new LocationConvertResultDto();
                    locationConvertResultDto.setLongitude(Double.valueOf(locationsArray[0]));
                    locationConvertResultDto.setLatitude(Double.valueOf(locationsArray[1]));
                    objectResultDto.setData(locationConvertResultDto);
                }
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("坐标转换请求失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * list获取数据库所需的code和更新地址
     *
     * @param requestDto GeocodeGetRequestDto
     * @return 结果
     */
    @Override
    public ListResultDto<AllRegeoCodeGetResultDto> geocoderGetAllCodeList(GeocodeGetRequestDto requestDto) {
        String jsonString = null;
        String location = requestDto.getLocation();
        ListResultDto<AllRegeoCodeGetResultDto> listResultDto = new ListResultDto<>();
        List<AllRegeoCodeGetResultDto> items = new ArrayList<>();
        try {
            if (StringUtils.isEmpty(requestDto.getLocation())) {
                return listResultDto.makeResult(ToolResultEnum.LOCATION_EMPTY.getValue(), ToolResultEnum.LOCATION_EMPTY.getComment());
            }
            Map<String, Object> params = new HashMap<>(4);
            params.put("key", amapConfig.getKey());
            params.put("location", location);
            params.put("batch", true);
            String response = Requests.post(AmapConstant.GEO_CODE_REGEO_URL).verify(false).followRedirect(false).
                    forms(params).charset(Charset.forName("UTF-8")).send().readToText();
            JSONObject json = JSONObject.parseObject(response);
            jsonString = json.toJSONString();
            String status = json.getString("status");
            JSONArray regeocodes = json.getJSONArray("regeocodes");
            if ("1".equals(status) && regeocodes != null) {
                for (int i = 0; i < regeocodes.size(); i++) {
                    JSONObject jsonObject = regeocodes.getJSONObject(i);
                    AllRegeoCodeGetResultDto allRegeoCodeGetResultDto = new AllRegeoCodeGetResultDto();
                    try {
                        Regecode regecode = modelMapper.map(jsonObject, Regecode.class);
                        if (regecode != null && regecode.getAddressComponent() != null) {
                            AddressComponent addressComponent = regecode.getAddressComponent();
                            String address = regecode.getFormatted_address();
                            allRegeoCodeGetResultDto.setAddress(address);
                            allRegeoCodeGetResultDto.setCountyCode(addressComponent.getAdcode());
                            getAdCodeByDb(allRegeoCodeGetResultDto, addressComponent.getDistrict());
                        }
                    } catch (Exception e) {
                        log.error("modelMapper转换失败jsonObject:" + jsonObject + "\ne:" + e.getMessage());
                    }
                    items.add(allRegeoCodeGetResultDto);
                }
                listResultDto.setItems(items);
                listResultDto.success();
            } else {
                String[] singleLocations = location.split("\\|");
                for (String singleLocation : singleLocations) {
                    GeocodeGetRequestDto singleRequestDto = new GeocodeGetRequestDto();
                    singleRequestDto.setLocation(singleLocation);
                    items.add(geocoderGetAllCode(singleRequestDto).getData());
                }
                listResultDto.setItems(items);
                listResultDto.success();
//                return listResultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
            }
        } catch (Exception e) {
            log.error("逆地理失败jsonString:" + jsonString + "\ne:" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 从region表获取信息
     *
     * @param allRegeoCodeGetResultDto AllRegeoCodeGetResultDto
     * @param district                 区县名称
     */
    private void getAdCodeByDb(AllRegeoCodeGetResultDto allRegeoCodeGetResultDto, String district) {
        try {
            String countyCode = allRegeoCodeGetResultDto.getCountyCode();
            EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("code", countyCode);
            entityWrapper.orderBy("id");
            Region region = null;
            List<Region> regions = regionCrudService.selectList(entityWrapper);
            if (regions != null && regions.size() == 1) {
                region = regions.get(0);
            } else if (regions != null && regions.size() > 1) {
                region = regions.get(regions.size() - 1);
            } else if (regions == null) {
                EntityWrapper<Region> regionEntityWrapper = new EntityWrapper<>();
                regionEntityWrapper.eq("name", district);
                regions = regionCrudService.selectList(entityWrapper);
                if (regions != null && regions.size() == 1) {
                    region = regions.get(0);
                } else if (regions != null && regions.size() > 1) {
                    region = regions.get(regions.size() - 1);
                }
            }
            if (region == null) {
                return;
            }
            Long cityId = region.getParentId();
            Region city = regionCrudService.selectById(cityId);
            if (city == null) {
                return;
            }
//            log.error(district + " " + countyCode + " " + cityId + " " + "city:" + city);
            allRegeoCodeGetResultDto.setCityCode(city.getAdCode());
            //直辖市没有市一级
            if ("市辖区".equals(city.getName())) {
                allRegeoCodeGetResultDto.setCityCode("");
            }
            allRegeoCodeGetResultDto.setProvinceCode(regionCrudService.selectById(city.getParentId()).getAdCode());
            //特殊地级市没有区县一级
            if ("100000".equals(allRegeoCodeGetResultDto.getProvinceCode())) {
                allRegeoCodeGetResultDto.setProvinceCode(allRegeoCodeGetResultDto.getCityCode());
                allRegeoCodeGetResultDto.setCityCode(allRegeoCodeGetResultDto.getCountyCode());
                allRegeoCodeGetResultDto.setCountyCode(null);
            }
        } catch (Exception e) {
            log.error("数据库读取region信息失败" + e.getMessage());
        }
    }
}
