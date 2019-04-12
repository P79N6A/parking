package com.zoeeasy.cloud.collect.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2019/1/15
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "QueryPriceResultDto", description = "账单查询返回参数")
public class QueryPriceResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    private String localCode;

    /**
     * 本地停车场订单流水号
     */
    @ApiModelProperty("本地停车场订单流水号")
    private String parkingOrderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 进场时间
     */
    @ApiModelProperty("进场时间")
    private String inTime;

    /**
     * 停车时长(分钟)
     */
    @ApiModelProperty("停车时长(分钟)")
    private Integer duration;

    /**
     * 应收金额
     */
    @ApiModelProperty("应收金额")
    private String price;

    /**
     * 免费离场时间
     */
    @ApiModelProperty("免费离场时间")
    private Integer freeOutTime;

}
