package com.zoeeasy.cloud.charge.chg.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收费规则条数视图模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleTotalResultDto", description = "收费规则条数视图模型")
public class ChargeRuleTotalResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 数目
     */
    @ApiModelProperty("total")
    private Integer total;

}
