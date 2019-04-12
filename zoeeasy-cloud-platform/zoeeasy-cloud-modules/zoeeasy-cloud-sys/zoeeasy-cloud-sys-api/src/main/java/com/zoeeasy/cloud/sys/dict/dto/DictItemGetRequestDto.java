package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.sys.dict.cst.DictConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取字典数据返回参数
 *
 * @date: 2019/02/25.
 * @author：zm
 */
@ApiModel(value = "DictItemGetRequestDto", description = "根据字典项Id获取字典项")
@Data
@EqualsAndHashCode(callSuper = true)
public class DictItemGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典项Id", required = true)
    @NotNull(message = DictConstant.DICT_ITEM_ID_NOT_EMPTY)
    private Long id;
}
