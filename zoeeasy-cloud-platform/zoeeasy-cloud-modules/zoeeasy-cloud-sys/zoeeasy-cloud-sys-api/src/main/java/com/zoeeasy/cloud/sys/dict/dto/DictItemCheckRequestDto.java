package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型验证请求参数
 *
 * @author walkman
 * @since 2018-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DictItemCheckRequestDto", description = "字典类型验证请求参数")
public class DictItemCheckRequestDto extends SignedRequestDto {

    /**
     * 字典编码
     */
    /*@ApiModelProperty(value = "字典编码")
    private String dictCode;*/

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

}
