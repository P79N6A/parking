package com.zoeeasy.cloud.tool.region;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.tool.region.dto.*;

/**
 * @author walkman
 */
public interface RegionService {

    /**
     * 新增地区
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto addRegion(RegionAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除地区
     *
     * @param requestDto requestDto requestDto
     * @return ResultDto
     */
    ResultDto deleteRegion(RegionDeleteRequestDto requestDto);

    /**
     * 更新地区
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto updateRegion(RegionUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取地区
     *
     * @param requestDto requestDto
     * @return ObjectResultDto<RegionResultDto>
     */
    ObjectResultDto<RegionResultDto> getRegion(RegionGetRequestDto requestDto);

    /**
     * 获取地区列表
     *
     * @param requestDto requestDto
     * @return ListResultDto<RegionResultDto>
     */
    ListResultDto<RegionResultDto> getRegionList(RegionListGetRequestDto requestDto);

    /**
     * 分页获取地区列表
     *
     * @param requestDto requestDto
     * @return PagedResultDto<RegionResultDto>
     */
    PagedResultDto<RegionResultDto> getRegionPagedList(RegionQueryPagedResultRequestDto requestDto);

    /**
     * 获取省列表
     *
     * @param requestDto requestDto
     * @return ListResultDto<RegionViewResultDto>
     */
    ListResultDto<RegionViewResultDto> getProvinceList(ProvinceListGetRequestDto requestDto);

    /**
     * 获取市列表
     *
     * @param requestDto requestDto
     * @return ListResultDto<RegionViewResultDto>
     */
    ListResultDto<RegionViewResultDto> getCityList(CityListGetRequestDto requestDto);

    /**
     * 获取区县列表
     *
     * @param requestDto requestDto
     * @return ListResultDto<RegionViewResultDto>
     */
    ListResultDto<RegionViewResultDto> getCountyList(CountyListGetRequestDto requestDto);

    /**
     * 获取上级区域列表
     *
     * @param requestDto RegionGetUpperListRequestDto
     * @return ListResultDto<RegionGetUpperListResultDto>
     */
    ListResultDto<RegionGetUpperListResultDto> getUpperList(RegionGetUpperListRequestDto requestDto);

    /**
     * 获取省列表
     *
     * @param requestDto
     * @return ListResultDto<RegionListTotalGetResultDto>
     */
    ListResultDto<RegionListTotalGetResultDto> getRegionList(RegionListTotalGetRequestDto requestDto);

    /**
     * 获取子地区列表
     *
     * @Author Kane
     */
    ListResultDto<RegionGetChildListResultDto> getRegionChildList(RegionGetChildListRequestDto requestDto);

    /**
     * 获取区域地址(无talentId)
     *
     * @param requestDto requestDto
     * @return ObjectResultDto<RegionAddressResultDto>
     */
    ObjectResultDto<RegionAddressResultDto> getRegionAddressApp(RegionAddressGetRequestDto requestDto);

    /**
     * 获取区域树
     *
     * @param requestDto
     * @return
     */
    ListResultDto<TreeResultDto> getRegionTree(RegionTreeGetRequestDto requestDto);

    /**
     * 根据code获取region
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<RegionResultDto> getRegionByCode(RegionRequestDto requestDto);

    /**
     * 获取省市区编码
     * @param requestDto
     * @return
     */
    ObjectResultDto<RegionCodeResultDto> getRegionCode(RegionRequestDto requestDto);

}
