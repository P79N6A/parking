package com.zoeeasy.cloud.charge.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场当前收费信息视图
 *
 * @author AkeemSuper
 * @since 2018/10/8 0008
 */
@ApiModel(value = "ParkingCurrentChargeInfoResultDto", description = "停车场当前收费信息视图")
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
    private Integer dayTopAmount;

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

    /**
     * 收费标准
     */
    @ApiModelProperty("收费标准")
    private String chargeRule;
}
