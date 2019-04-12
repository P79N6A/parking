package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2018/1/26
 * @author: Administrator
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChargeRuleGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
