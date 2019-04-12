package com.zoeeasy.cloud.charge.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除收费规则关联停车信息表请求参数
 *
 * @author AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingChargeRuleDeleteRequestDto", description = "删除收费规则关联停车信息表请求参数")
public class ParkingChargeRuleDeleteRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
