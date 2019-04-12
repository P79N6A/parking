package com.zhuyitech.parking.tool.dto.result.oilprice;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/8/3 0003
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OilPriceDetailResultDto", description = "油价曲线图返回结果视图")
public class OilPriceGraphResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 油类型
     */
    @ApiModelProperty(value = "油类型")
    private String oilType;

    /**
     * 该城市油价曲线图
     */
    @ApiModelProperty(value = "该城市油价坐标")
    private List<OilPriceCoordinateResultDto> coordinates;

}
