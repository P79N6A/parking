package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取收费规则请求参数
 *
 * @Date: 2018/1/26
 * @author: yuzhihceng
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChargeRuleTimeGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
