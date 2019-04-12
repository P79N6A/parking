package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据规则Id修改收费规则关联停车信息表请求参数
 *
 * @author walkman
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointRuleUpdateByRuleIdRequestDto", description = "根据规则Id修改收费规则关联停车信息表请求参数")
public class ParkingAppointRuleUpdateByRuleIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id", required = true)
    private Long parkingId;

    /**
     * 规则ID
     */
    @ApiModelProperty(value = "规则ID", required = true)
    private Long ruleId;

    /**
     * 上线状态
     */
    @ApiModelProperty("上线状态")
    private Integer status;
}
