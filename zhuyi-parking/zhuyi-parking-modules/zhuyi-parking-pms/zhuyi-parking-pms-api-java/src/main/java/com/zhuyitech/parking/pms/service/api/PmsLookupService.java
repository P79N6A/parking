package com.zhuyitech.parking.pms.service.api;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;

/**
 * @author walkman
 * @date 2017-12-01
 */
public interface PmsLookupService {

    /**
     * 获取车型深度选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getCarDepthComboboxList();

    /**
     * 获取区域等级选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getRegionLevelComboboxList();

    /**
     * 获取车牌类型选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getPlateTypeComboboxList();

    /**
     * 获取车牌颜色选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getPlateColorComboboxList();

    /**
     * 获取车辆类型等级选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getCarLevelComboboxList();


    /**
     * 获取后台支付方式列表
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getPayWayComboboxList();

    /**
     * 获取后台预约订单类型列表
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getAssetLogTypeComboboxList();

}
