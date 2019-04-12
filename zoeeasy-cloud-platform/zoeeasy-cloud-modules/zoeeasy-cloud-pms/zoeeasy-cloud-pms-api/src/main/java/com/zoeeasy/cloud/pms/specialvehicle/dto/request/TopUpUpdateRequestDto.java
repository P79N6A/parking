package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TopUpUpdateRequestDto", description = "更改支付状态请求参数")
public class TopUpUpdateRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
