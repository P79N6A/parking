package com.zoeeasy.cloud.tool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.tool.amap.AmapService;
import com.zoeeasy.cloud.tool.amap.bean.AddressComponent;
import com.zoeeasy.cloud.tool.amap.bean.RegeoResult;
import com.zoeeasy.cloud.tool.amap.dto.request.GeocodeGetRequestDto;
import com.zoeeasy.cloud.tool.amap.dto.request.LocationConvertRequestDto;
import com.zoeeasy.cloud.tool.amap.dto.result.AllRegeoCodeGetResultDto;
import com.zoeeasy.cloud.tool.amap.dto.result.LocationConvertResultDto;
import com.zoeeasy.cloud.tool.amap.dto.result.RegeoCodeGetResultDto;
import com.zoeeasy.cloud.tool.config.AmapConfig;
import com.zoeeasy.cloud.tool.constant.AmapConstant;
import com.zoeeasy.cloud.tool.domain.RegionEntity;
import com.zoeeasy.cloud.tool.enums.ToolResultEnum;
import com.zoeeasy.cloud.tool.service.RegionCrudService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高德地图相关服务
 *
 * @author walkman
 * @date 2018/4/29
 */
@Service(value = "amapService")
@Slf4j
public class AmapServiceImpl implements AmapService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AmapConfig amapConfig;

    @Autowired
    private RegionCrudService regionCrudService;

    /**
     * 逆地理获取adcode
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<RegeoCodeGetResultDto> geocoderGet(@FluentValid GeocodeGetRequestDto requestDto) {
        ObjectResultDto<RegeoCodeGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("key", AmapConstant.KEY);
            params.put("location", requestDto.getLocation());
            String response = Requests.post(AmapConstant.GEO_CODE_REGEO_URL).verify(false).followRedirect(false).
                    forms(params).charset(Charset.forName("UTF-8")).send().readToText();
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
     * 坐标转换
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<LocationConvertResultDto> convertLocations(@FluentValid LocationConvertRequestDto requestDto) {
        ObjectResultDto<LocationConvertResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("locations", requestDto.getLongitude().toString() + "," + requestDto.getLatitude().toString());
            if (StringUtils.isEmpty(requestDto.getCoordSys())) {
                params.put("coordsys", "gps");
            } else {
                params.put("coordsys", requestDto.getCoordSys());
            }
            params.put("key", AmapConstant.KEY);
            String response = Requests.get(AmapConstant.COORDINATE_CONVERT_URL).verify(false).followRedirect(false)
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
            String response = Requests.post(amapConfig.getPrefix() + "/geocode/regeo").verify(false).followRedirect(false).
                    forms(params).charset(Charset.forName("UTF-8")).send().readToText();
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
     * 从region表获取信息
     *
     * @param allRegeoCodeGetResultDto AllRegeoCodeGetResultDto
     * @param district                 区县名称
     */
    private void getAdCodeByDb(AllRegeoCodeGetResultDto allRegeoCodeGetResultDto, String district) {
        try {
            String countyCode = allRegeoCodeGetResultDto.getCountyCode();
            EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("code", countyCode);
            entityWrapper.orderBy("id");
            RegionEntity regionEntity = null;
            List<RegionEntity> regionEntities = regionCrudService.selectList(entityWrapper);
            if (regionEntities != null && regionEntities.size() == 1) {
                regionEntity = regionEntities.get(0);
            } else if (regionEntities != null && regionEntities.size() > 1) {
                regionEntity = regionEntities.get(regionEntities.size() - 1);
            } else if (regionEntities == null) {
                EntityWrapper<RegionEntity> regionEntityWrapper = new EntityWrapper<>();
                regionEntityWrapper.eq("name", district);
                regionEntities = regionCrudService.selectList(entityWrapper);
                if (regionEntities != null && regionEntities.size() == 1) {
                    regionEntity = regionEntities.get(0);
                } else if (regionEntities != null && regionEntities.size() > 1) {
                    regionEntity = regionEntities.get(regionEntities.size() - 1);
                }
            }
            if (regionEntity == null) {
                return;
            }

            String cityCode = regionEntity.getParentCode();
            RegionEntity city = regionCrudService.findByCode(cityCode);
            if (city == null) {
                return;
            }
            allRegeoCodeGetResultDto.setCityCode(city.getCode());
            //直辖市没有市一级
            if ("市辖区".equals(city.getName())) {
                allRegeoCodeGetResultDto.setCityCode("");
            }
            String provinceCode = regionEntity.getParentCode();
            RegionEntity province = regionCrudService.findByCode(provinceCode);
            if (province == null) {
                return;
            }
            allRegeoCodeGetResultDto.setProvinceCode(province.getCode());
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
