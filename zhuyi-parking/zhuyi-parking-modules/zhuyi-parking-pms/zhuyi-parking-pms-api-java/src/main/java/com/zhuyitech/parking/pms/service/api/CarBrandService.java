package com.zhuyitech.parking.pms.service.api;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.pms.dto.request.car.*;
import com.zhuyitech.parking.pms.dto.result.car.CarBrandResultDto;
import com.zhuyitech.parking.pms.dto.result.car.CarBrandSubViewResultDto;
import com.zhuyitech.parking.pms.dto.result.car.CarBrandViewResultDto;

/**
 * @author walkman
 */
public interface CarBrandService {

    /**
     * 新增车型
     *
     * @param requestDto
     * @return
     */
    ResultDto addCarBrand(CarBrandAddRequestDto requestDto);

    /**
     * 删除车型
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteCarBrand(CarBrandDeleteRequestDto requestDto);

    /**
     * 更新车型
     *
     * @param requestDto
     * @return
     */
    ResultDto updateCarBrand(CarBrandUpdateRequestDto requestDto);

    /**
     * 获取车型
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<CarBrandResultDto> getCarBrand(CarBrandGetRequestDto requestDto);

    /**
     * 获取车型列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<CarBrandResultDto> getCarBrandList(CarBrandListGetRequestDto requestDto);

    /**
     * 分页获取车型列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<CarBrandResultDto> getCarBrandPagedList(CarBrandQueryPagedResultRequestDto requestDto);

    /**
     * 获取车品牌
     *
     * @return
     */
    ListResultDto<CarBrandViewResultDto> getCarBrandViewList(CarBrandViewGetRequestDto requestDto);

    /**
     * 获取车品牌及其车型
     *
     * @param requestDto
     * @return
     */
    ListResultDto<CarBrandSubViewResultDto> getCarBrandSubViewList(CarBrandSubViewGetRequestDto requestDto);

    /**
     * 修改logo的url
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getCarBrandListByDepth(CarBrandDepthGetRequestDto requestDto);

}
