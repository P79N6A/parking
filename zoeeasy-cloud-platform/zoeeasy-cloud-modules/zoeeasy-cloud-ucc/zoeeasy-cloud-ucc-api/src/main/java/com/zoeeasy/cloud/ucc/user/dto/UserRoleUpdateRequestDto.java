package com.zoeeasy.cloud.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户角色更新请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserRoleUpdateRequestDto", description = "用户角色更新请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleUpdateRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    /**
     * 角色ID列表
     */
    @ApiModelProperty(value = "角色ID列表", dataType = "List<Long>")
    private List<Long> roleIds;

}
