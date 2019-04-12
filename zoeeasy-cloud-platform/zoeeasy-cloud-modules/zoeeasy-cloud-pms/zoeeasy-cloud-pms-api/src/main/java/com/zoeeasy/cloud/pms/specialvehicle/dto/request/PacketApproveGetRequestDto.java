package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取取消包期车信息请求参数
 * Created by song on 2018/10/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketApproveGetRequestDto", description = "获取取消包期车信息请求参数")
public class PacketApproveGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
