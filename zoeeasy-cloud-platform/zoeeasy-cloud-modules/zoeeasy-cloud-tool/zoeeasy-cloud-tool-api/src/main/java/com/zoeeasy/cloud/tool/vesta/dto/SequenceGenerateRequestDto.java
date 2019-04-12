package com.zoeeasy.cloud.tool.vesta.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 序列号生成器请求参数
 *
 * @author walkman
 */
@Data
@ApiModel(value = "SequenceGenerateRequestDto", description = "序列号生成器请求参数")
@EqualsAndHashCode(callSuper = false)
public class SequenceGenerateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

}