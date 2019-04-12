package com.zoeeasy.cloud.pms.platform.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场收费信息视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingChargeInfoResultDto", description = "停车场收费信息视图模型")
public class ParkingChargeInfoResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingId;

    /**
     * 是否收费 0:不收费 1 收费
     */
    @ApiModelProperty("是否收费")
    private Boolean chargeFee;

    /**
     * 收费规则的简短描述
     */
    @ApiModelProperty("收费规则的简短描述")
    private String chargeRule;

    /**
     * 收费描述信息
     */
    @ApiModelProperty("收费描述信息")
    private String chargeDescription;

    /**
     * 是否关联收费规则，  0-否，1-是
     */
    @ApiModelProperty("是否关联收费规则")
    private Boolean relatedRule;

    /**
     * 是否当前有效的收费信息
     */
    @ApiModelProperty("是否当前有效的收费信息")
    private Boolean active;

}
