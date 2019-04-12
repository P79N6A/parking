package com.zoeeasy.cloud.sys.dict.dto;


import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.sys.dict.cst.DictConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 字典项返回结果
 *
 * @date: 2019/02/26.
 * @author：zm
 */
@ApiModel(value = "DictItemUpdateRequestDto", description = "修改字典项请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class DictItemUpdateRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签", required = true)
    @NotBlank(message = DictConstant.DICT_LABEL_NOT_EMPTY)
    private String dictLabel;

    /**
     * 字典值
     */
    @ApiModelProperty(value = "字典值", required = true)
    @NotBlank(message = DictConstant.DICT_VALUE_NOT_EMPTY)
    private String dictValue;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @NotNull(message = DictConstant.SORT_NOT_EMPTY)
    private Integer sort;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
