package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.scapegoat.infrastructure.jackson.convert.deserializer.ToIntegerFenDeserializer;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

/**
 * 分页获取预约规则列表请求参数
 *
 * @author walkman
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointRuleGetPageListRequestDto", description = "分页获取预约规则列表请求参数")
public class AppointRuleGetPageListRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约规则名称
     */
    @ApiModelProperty(value = "预约规则名称")
    private String ruleName;

    /**
     * 预约规则编号
     */
    @ApiModelProperty(value = "预约规则编号")
    private String ruleCode;

    /**
     * 收费金额
     */
    @ApiModelProperty(value = "收费金额")
    @Range(min = AppointConstant.PRICE_MIN, message = AppointConstant.PRICE_ILLEGAL)
    @JsonDeserialize(using = ToIntegerFenDeserializer.class)
    private Integer chargePrice;

    /**
     * 服务费用
     */
    @ApiModelProperty(value = "服务费用")
    @Range(min = AppointConstant.PRICE_MIN, message = AppointConstant.PRICE_ILLEGAL)
    @JsonDeserialize(using = ToIntegerFenDeserializer.class)
    private Integer fee;

    /**
     * 收费类型
     */
    @ApiModelProperty(value = "收费类型")
    private Integer chargeType;

    /**
     * 是否关联停车场
     */
    @ApiModelProperty(value = "是否关联停车场")
    private Boolean used;
}
