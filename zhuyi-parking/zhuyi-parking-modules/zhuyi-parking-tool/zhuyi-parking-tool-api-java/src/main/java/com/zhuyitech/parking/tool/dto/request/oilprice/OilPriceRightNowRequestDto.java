package com.zhuyitech.parking.tool.dto.request.oilprice;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 查询当日油价
 *
 * @author zwq
 * @date 2018/4/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OilPriceRightNowRequestDto", description = "查询当日油价")
public class OilPriceRightNowRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

}
