package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典项删除请求参数
 *
 * @date: 2019/2/26.
 * @author：zm
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "DictItemDeleteRequestDto", description = "字典项删除请求参数")
public class DictItemDeleteRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
