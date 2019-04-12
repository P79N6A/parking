package com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出车类型请求参数
 *
 * @Date: 2018/10/29
 * @author: lhj
 */
@Data
@ApiModel(value = "ChangeTypeListRequestDto", description = "出车类型请求参数")
@EqualsAndHashCode(callSuper = false)
public class ChangeTypeListRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;
}
