package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 删除收费规则请求参数
 *
 * @Date: 2018/1/29
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleDeleteRequestDto", description = "修改收费规则请求参数")
public class ChargeRuleDeleteRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 删除收费规则的ID
     */
    @ApiModelProperty(value = "删除收费规则的ID", required = true)
    @NotNull(message = ChargeConstant.CHARGE_DELETE_ID_NOT_EMPTY)
    private Long deleteId;

}
