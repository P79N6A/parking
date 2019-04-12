package com.zoeeasy.cloud.pms.garage.dto.result;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2018/11/30
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CloudResultDto extends ResultDto {

    /**
     * 云平台基础数据Code
     */
    private String cloudCode;
}
