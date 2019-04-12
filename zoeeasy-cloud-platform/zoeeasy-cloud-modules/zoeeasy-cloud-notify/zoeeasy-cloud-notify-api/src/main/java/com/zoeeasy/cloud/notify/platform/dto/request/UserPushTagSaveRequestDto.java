package com.zoeeasy.cloud.notify.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("UserPushTagSaveRequestDto")
public class UserPushTagSaveRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "tenantId", required = true)
    @NotNull(message = NotifyConstant.TENANT_ID_NOT_NULL)
    private Long tenantId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "userId", required = true)
    @NotNull(message = NotifyConstant.USER_ID_NOT_NULL)
    private Long userId;

    /**
     * 标签id
     */
    @ApiModelProperty(value = "tagId", required = true)
    @NotNull(message = NotifyConstant.TAG_ID_NOT_NULL)
    private List<Long> tagId;
}
