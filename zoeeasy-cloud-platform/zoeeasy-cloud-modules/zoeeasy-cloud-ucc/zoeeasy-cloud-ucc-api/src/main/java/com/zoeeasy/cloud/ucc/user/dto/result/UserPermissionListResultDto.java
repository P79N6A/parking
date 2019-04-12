package com.zoeeasy.cloud.ucc.user.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 用户权限视图模型
 *
 * @author walkman
 */
@ApiModel(value = "UserPermissionListResultDto", description = "用户权限视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPermissionListResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 权限
     */
    private Map<String, String> permissions;

}
