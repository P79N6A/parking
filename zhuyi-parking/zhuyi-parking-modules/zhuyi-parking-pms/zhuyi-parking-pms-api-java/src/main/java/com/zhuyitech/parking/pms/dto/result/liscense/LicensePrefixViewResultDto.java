package com.zhuyitech.parking.pms.dto.result.liscense;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 车牌前缀视图模型
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixViewResultDto", description = "车牌前缀视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

}
