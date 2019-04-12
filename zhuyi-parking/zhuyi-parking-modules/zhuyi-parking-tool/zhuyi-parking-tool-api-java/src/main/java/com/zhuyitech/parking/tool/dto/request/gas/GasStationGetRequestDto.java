package com.zhuyitech.parking.tool.dto.request.gas;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取充电桩信息
 *
 * @author zwq
 * @date 2018/4/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "GasStationGetRequestDto", description = "获取充电桩信息")
public class GasStationGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 必选	关键字，或关键字的首字母、拼音
     */
    @ApiModelProperty(value = "关键字，或关键字的首字母、拼音", required = true)
    private String keyword;

    /**
     * 必选	检索区域名称，可输入城市名或省份名或全国
     */
    @ApiModelProperty(value = "关键字，或关键字的首字母、拼音", required = true)
    private String region;

}
