package com.zoeeasy.cloud.integration.open.dto.result;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/3 0003
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AnyParkingOrderPlaceResultDto", description = "添加订单返回结果")
public class AnyParkingOrderPlaceResultDto extends ResultDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String billCode;

    /**
     * 入车时间
     */
    @ApiModelProperty("入车时间")
    private Date enterTime;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date beginTime;

    /**
     * 结算时间
     */
    @ApiModelProperty("结算时间")
    private Long costTime;

    /**
     * 应付金额
     */
    @ApiModelProperty("应付金额")
    private Integer payAmount;
}
