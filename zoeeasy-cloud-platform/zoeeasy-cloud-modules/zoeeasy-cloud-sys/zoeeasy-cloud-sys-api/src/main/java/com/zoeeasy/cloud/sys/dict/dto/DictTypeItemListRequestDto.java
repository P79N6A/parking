package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.sys.dict.cst.DictConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据字典类型获取字典项列表
 *
 * @date: 2019/02/25.
 * @author：zm
 */
@ApiModel(value = "DictTypeItemListRequestDto", description = "根据字典类型获取字典项列表")
@Data
@EqualsAndHashCode(callSuper = true)
public class DictTypeItemListRequestDto  extends SignedSessionRequestDto {

    @ApiModelProperty(value = "字典编码", required = true)
    @NotBlank(message = DictConstant.DICT_CODE_NOT_EMPTY)
    private String dictCode;
}
