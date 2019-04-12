package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 取消包期请求参数
 * Created by song on 2018/10/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ApplyCancelPacketRequestDto", description = "取消包期请求参数")
public class ApplyCancelPacketRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
