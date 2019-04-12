package com.zhuyitech.parking.tool.dto.result.oilprice;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询当前油价
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OilPriceDetailResultDto", description = "获取油价详情返回视图")
public class OilPriceDetailResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 当日油价信息
     */
    @ApiModelProperty(value = "当日油价信息")
    private OilPriceCurrentResultDto currentOilPrice;

    /**
     * 该城市油价曲线图
     */
    @ApiModelProperty(value = "该城市油价曲线图")
    private List<OilPriceGraphResultDto> priceGraphs;

}
