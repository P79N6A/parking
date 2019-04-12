package com.zoeeasy.cloud.collect.msgbody.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2019/1/13
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "QueryPriceResultBody", description = "订单价格查询返回")
public class QueryPriceResultBody extends BaseResultBody {

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
    private String passInTime;

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

    /**
     * 云平台订单号
     */
    @ApiModelProperty("云平台订单号")
    private String orderNo;

}
