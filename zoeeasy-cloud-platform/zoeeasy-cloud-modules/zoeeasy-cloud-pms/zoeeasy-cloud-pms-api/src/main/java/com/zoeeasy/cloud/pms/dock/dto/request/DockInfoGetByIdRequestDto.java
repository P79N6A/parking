package com.zoeeasy.cloud.pms.dock.dto.request;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "DockInfoResultDto", description = "客户端注册信息")
public class DockInfoGetByIdRequestDto extends EntityDto<Long> {
    private static final long serialVersionUID = 1L;
}
