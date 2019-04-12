package com.zhuyitech.parking.tool.dto.result.oilprice;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/8/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OilPriceCoordinateResultDto", description = "油价坐标")
public class OilPriceCoordinateResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private String oilTime;

    /**
     * 油价
     */
    @ApiModelProperty(value = "油价")
    private String oilPrice;

}
