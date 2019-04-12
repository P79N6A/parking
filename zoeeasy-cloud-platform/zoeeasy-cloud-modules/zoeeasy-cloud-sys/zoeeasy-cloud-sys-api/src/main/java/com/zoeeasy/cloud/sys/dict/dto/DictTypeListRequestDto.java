package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取字典类型列表请求参数
 *
 * @date: 2019/02/25.
 * @author：zm
 */
@ApiModel(value = "DictTypeListRequestDto", description = "获取字典类型列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class DictTypeListRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

}
