package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型验证请求参数
 *
 * @date: 2019/02/25.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DictTypeCheckRequestDto", description = "字典类型验证请求参数")
public class DictTypeCheckRequestDto extends SignedRequestDto {

    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String dictCode;
    
}
