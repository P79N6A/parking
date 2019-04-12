package com.zhuyitech.parking.tool.dto.result.oilprice;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.util.Date;
import java.util.List;

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
@ApiModel(value = "OilPriceListResultDto", description = "油价列表返回结果")
public class OilPriceListResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 更新日期
     */
    @ApiModelProperty(value = "updateTime")
    private Date updateTime;

    /**
     * 各城市油价信息
     */
    @ApiModelProperty(value = "oilPriceList")
    private List<OilPriceCurrentResultDto> oilPriceList;

    /**
     * 是否有默认城市
     */
    @ApiModelProperty(value = "defaultProvince", notes = "1 有默认城市 0无默认城市")
    private Integer defaultProvince;

}
