package com.zoeeasy.cloud.pms.area;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.area.dto.request.*;
import com.zoeeasy.cloud.pms.area.dto.result.*;

/**
 * 区域管理服务
 */
public interface AreaService {
    /**
     * 新增区域
     *
     * @param requestDto
     * @return
     */
    ResultDto addArea(AreaAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除区域
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteArea(AreaDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 更新区域
     *
     * @param requestDto
     * @return
     */
    ResultDto updateArea(AreaUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取区域
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AreaResultDto> getArea(AreaGetRequestDto requestDto);

    /**
     * 分页获取子区域列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<AreaResultDto> getAreaPagedList(AreaQueryPagedResultRequestDto requestDto);

    /**
     * 层级获取区域列表
     *
     * @param requestDto
     * @return
     */
//    ListResultDto<AreaListTotalGetResultDto> getAreaTotalList(AreaListTotalGetRequestDto requestDto);

    /**
     * 获取area树
     *
     * @return
     */
    ListResultDto<TreeResultDto> getAreaTree(AreaTreeRequestDto requestDto);

    /**
     * 获取不包含country的area树
     *
     * @return
     */
    ListResultDto<TreeResultDto> getAreaTreeNotIncludeCountry(AreaTreeNotIncludeCountryRequestDto requestDto);

    /**
     * 获取areaparking树
     *
     * @return
     */
    ListResultDto<AreaParkingTreeResultDto> getAreaParkingTree(AreaParkingTreeRequestDto requestDto);

    /**
     * 获取详细地址
     * @param requestDto
     * @return
     */
    ObjectResultDto<DetailAddressResultDto> getDetailAddress(DetailAddressRequestDto requestDto);

    ListResultDto<AreaParkingResultDto> getAreaParking(AreaParkingRequestDto requestDto);

}
