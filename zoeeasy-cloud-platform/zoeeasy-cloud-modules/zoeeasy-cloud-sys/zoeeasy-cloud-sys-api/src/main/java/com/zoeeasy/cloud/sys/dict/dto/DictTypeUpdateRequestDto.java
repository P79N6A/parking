package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 字典类型修改请求参数
 *
 * @author walkman
 * @since 2018-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DictTypeUpdateRequestDto", description = "字典类型修改请求参数")
public class DictTypeUpdateRequestDto extends SignedRequestDto {

    /**
     * 字典类型ID
     */
    @ApiModelProperty(value = "字典类型ID")
    private Long dictTypeId;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 状态（1正常 0停用
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
