package com.zoeeasy.cloud.integration.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 停车场收费信息视图模型
 *
 * @author walkman
 */
@ApiModel(value = "ParkingChargeInfoResultDto", description = "停车场收费信息视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingCurrentChargeInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否限免
     */
    @ApiModelProperty(value = "是否限免(True 是 False 否 )")
    private Boolean nowFree;

    /**
     * 免费开始时间，格式HH:mm:ss
     */
    @ApiModelProperty(value = "免费开始时间")
    private String freeStartTime;

    /**
     * 免费结束时间，格式HH:mm:ss
     */
    @ApiModelProperty(value = "免费结束时间")
    private String freeEndTime;

    /**
     * 免费停车时长
     */
    @ApiModelProperty(value = "免费停车时长")
    private Integer freeTimeLength;

    /**
     * 24小时封顶金额
     */
    @ApiModelProperty(value = "24小时封顶金额")
    private BigDecimal dayTopAmount;

    /**
     * 是否区分大小型车费
     */
    @ApiModelProperty(value = "是否区分大小型车费")
    private Boolean discriminateLargeSmall;

    /**
     * 大型车收费规则
     */
    @ApiModelProperty(value = "大型车收费规则")
    private String largeVehicleRule;

    /**
     * 小型车收费规则
     */
    @ApiModelProperty(value = "小型车收费规则")
    private String smallVehicleRule;

}
