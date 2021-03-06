package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车场类型获取请求参数
 * <p>
 * Created by song on 2018/9/27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LotTypeGetRequestDto", description = "车场类型获取请求参数")
public class LotTypeGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

}
