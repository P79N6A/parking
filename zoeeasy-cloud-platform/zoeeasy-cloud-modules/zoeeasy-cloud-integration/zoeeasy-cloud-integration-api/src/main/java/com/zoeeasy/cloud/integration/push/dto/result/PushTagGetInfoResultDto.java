package com.zoeeasy.cloud.integration.push.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取用户tag的返回结果
 *
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@ApiModel(value = "PushTagGetInfoResultDto", description = "获取用户tag的返回结果")
@Data
@EqualsAndHashCode(callSuper = false)
public class PushTagGetInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * tags
     */
    @ApiModelProperty("用户所在的tag")
    private List<String> tags;

}
