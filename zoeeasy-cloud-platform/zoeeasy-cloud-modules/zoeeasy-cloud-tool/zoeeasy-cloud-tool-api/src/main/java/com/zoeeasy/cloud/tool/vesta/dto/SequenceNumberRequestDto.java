package com.zoeeasy.cloud.tool.vesta.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 序列号生成结果
 *
 * @author walkman
 */
@Data
@ApiModel(value = "SequenceNumberRequestDto", description = "序列号生成结果")
@EqualsAndHashCode(callSuper = false)
public class SequenceNumberRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    @ApiModelProperty(value = "序列号")
    private String sequence;

}