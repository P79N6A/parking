package com.zoeeasy.cloud.charge.chg.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/11/30 0030
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleInfoResultDto", description = "获取收费规则列表返回接口")
public class ChargeRuleInfoResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    private String name;

    /**
     * 规则类型
     */
    @ApiModelProperty("规则类型")
    private String ruleType;

    /**
     * 节假日类型
     */
    @ApiModelProperty("节假日类型")
    private String holidayType;
}
