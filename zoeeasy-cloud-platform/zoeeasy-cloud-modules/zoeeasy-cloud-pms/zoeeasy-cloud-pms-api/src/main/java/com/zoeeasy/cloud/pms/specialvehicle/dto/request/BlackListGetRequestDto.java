package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BlackListGetRequestDto", description = "获取黑名单详情请求参数")
public class BlackListGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
