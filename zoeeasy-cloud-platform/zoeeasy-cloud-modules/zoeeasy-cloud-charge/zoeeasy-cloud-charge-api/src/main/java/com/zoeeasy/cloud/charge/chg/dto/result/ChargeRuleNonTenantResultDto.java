package com.zoeeasy.cloud.charge.chg.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleNonTenantResultDto", description = "分页获取收费规则视图模型")
public class ChargeRuleNonTenantResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("收费规则名称")
    private String name;

    @ApiModelProperty("上线时间")
    private Date onlineTime;

    @ApiModelProperty("下线时间")
    private Date offlineTime;

}
