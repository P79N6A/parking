package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除收费规则时间分段请求参数
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChargeRuleTimeDeleteRequestDto extends SessionEntityDto {

    private static final long serialVersionUID = 1L;
}
