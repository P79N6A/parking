package com.zhuyitech.parking.pms.dto.result.liscense;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 车牌前缀表视图模型
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixResultDto", description = "车牌前缀表视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 父ID
     */
    @ApiModelProperty("父ID")
    private Long parentId;

}
