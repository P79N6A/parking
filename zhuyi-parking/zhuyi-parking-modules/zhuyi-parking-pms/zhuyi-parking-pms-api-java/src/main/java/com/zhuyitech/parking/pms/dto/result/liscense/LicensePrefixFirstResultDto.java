package com.zhuyitech.parking.pms.dto.result.liscense;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 车牌前缀首字母模型
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixFirstResultDto", description = "车牌前缀首字母模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixFirstResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

}
