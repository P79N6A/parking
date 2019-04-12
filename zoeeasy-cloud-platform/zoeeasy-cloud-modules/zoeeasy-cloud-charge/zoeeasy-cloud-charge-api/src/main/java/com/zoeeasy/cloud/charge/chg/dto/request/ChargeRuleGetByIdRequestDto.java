package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleGetByIdRequestDto", description = "根据id获取收费规则列表请求参数")
public class ChargeRuleGetByIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private List<Long> ids;
}
