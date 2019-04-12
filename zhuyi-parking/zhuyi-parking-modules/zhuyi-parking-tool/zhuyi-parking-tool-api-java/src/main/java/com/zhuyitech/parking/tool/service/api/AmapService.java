package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.request.location.LocationConvertRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.GeocodeGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.location.LocationConvertResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.AllRegeoCodeGetResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.RegeoCodeGetResultDto;

/**
 * 高德地图相关服务
 *
 * @author walkman
 * @date 2018/4/29
 */
public interface AmapService {

    /**
     * 逆地理获取adcode
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<RegeoCodeGetResultDto> geocoderGet(GeocodeGetRequestDto requestDto);

    /**
     * 坐标转换
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<LocationConvertResultDto> convertLocations(LocationConvertRequestDto requestDto);


    /**
     * 逆地理获取所有adcode
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AllRegeoCodeGetResultDto> geocoderGetAllCode(GeocodeGetRequestDto requestDto);

    /**
     * 逆地理获取所有adcode
     *
     * @param requestDto
     * @return
     */
    ListResultDto<AllRegeoCodeGetResultDto> geocoderGetAllCodeList(GeocodeGetRequestDto requestDto);
}
