package com.zoeeasy.cloud.pms.dock.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AddPassingRecordResultDto", description = "客户端系统注册返回结果")
public class AddPassingRecordResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 云平台编号
     */
    @ApiModelProperty("云平台编号")
    private String cloudCode;

}
