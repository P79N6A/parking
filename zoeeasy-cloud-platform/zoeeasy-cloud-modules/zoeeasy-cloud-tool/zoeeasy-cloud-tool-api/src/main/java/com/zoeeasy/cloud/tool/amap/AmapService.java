package com.zoeeasy.cloud.tool.amap;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.tool.amap.dto.request.GeocodeGetRequestDto;
import com.zoeeasy.cloud.tool.amap.dto.request.LocationConvertRequestDto;
import com.zoeeasy.cloud.tool.amap.dto.result.AllRegeoCodeGetResultDto;
import com.zoeeasy.cloud.tool.amap.dto.result.LocationConvertResultDto;
import com.zoeeasy.cloud.tool.amap.dto.result.RegeoCodeGetResultDto;

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
}
