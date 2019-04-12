package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 分页获取收费规则列表请求参数
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleGetPageListRequestDto", description = "分页获取收费规则列表请求参数")
public class ChargeRuleGetPageListRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    @Length(max = ChargeConstant.MAX, message = ChargeConstant.LENGTH_RANGE)
    private String ruleName;

    /**
     * 规则类型
     */
    @ApiModelProperty("规则类型")
    private Integer ruleType;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    private Integer carType;

    /**
     * 停车场等级
     */
    @ApiModelProperty("停车场等级")
    private Integer parkingLevel;

}
