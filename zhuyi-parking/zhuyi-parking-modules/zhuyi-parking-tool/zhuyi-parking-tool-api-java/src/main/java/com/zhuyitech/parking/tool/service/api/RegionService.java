package com.zhuyitech.parking.tool.service.api;


import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.region.*;
import com.zhuyitech.parking.tool.dto.result.region.*;

/**
 * @author walkman
 */
public interface RegionService {

    /**
     * 新增地区
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto addRegion(RegionAddRequestDto requestDto);

    /**
     * 删除地区
     *
     * @param requestDto requestDto requestDto
     * @return
     */
    ResultDto deleteRegion(RegionDeleteRequestDto requestDto);

    /**
     * 更新地区
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updateRegion(RegionUpdateRequestDto requestDto);

    /**
     * 获取地区
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<RegionResultDto> getRegion(RegionGetRequestDto requestDto);

    /**
     * 获取地区列表
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<RegionResultDto> getRegionList(RegionListGetRequestDto requestDto);

    /**
     * 分页获取地区列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<RegionResultDto> getRegionPagedList(RegionQueryPagedResultRequestDto requestDto);

    /**
     * 获取省列表
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<RegionViewResultDto> getProvinceList(ProvinceListGetRequestDto requestDto);

    /**
     * 获取市列表
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<RegionViewResultDto> getCityList(CityListGetRequestDto requestDto);

    /**
     * 获取区县列表
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<RegionViewResultDto> getCountyList(CountyListGetRequestDto requestDto);

    /**
     * 获取省列表
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<RegionViewByAdcodeResultDto> getProvinceByAdcode(ProvinceGetByAdcodeRequestDto requestDto);

    /**
     * 获取区域地址
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<RegionAddressResultDto> getRegionAddress(RegionAddressGetRequestDto requestDto);

    /**
     * 通过adcode获取市级拼音
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<RegionCityPinyinGetResultDto> getRegionCityPingyin(RegionCityPingyinGetRequestDto requestDto);

    /**
     * 通过adcode获取省name
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<RegionProvinceNameGetResultDto> getRegionProvinceName(RegionProvinceNameGetRequestDto requestDto);

    /**
     * 获取上级区域列表
     *
     * @param requestDto RegionGetUpperListRequestDto
     * @return ListResultDto
     */
    ListResultDto<RegionGetUpperListResultDto> getUpperList(RegionGetUpperListRequestDto requestDto);


    /**
     * 通过市name获取adcode
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<RegionProvinceNameGetResultDto> getRegionAdcode(RegionProvinceAdCodeGetRequestDto requestDto);

    /**
     * 获取省列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<RegionListTotalGetResultDto> getRegionList(RegionListTotalGetRequestDto requestDto);

}
