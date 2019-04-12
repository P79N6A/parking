package com.zhuyitech.parking.tool.dto.request.version;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取版本列表请求参数
 *
 * @author zwq
 * @date 2018/4/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AppVersionListGetRequestDto", description = "获取版本列表请求参数")
public class AppVersionListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端类型 1.android 2 ios
     */
    @ApiModelProperty("客户端类型")
    private Integer terminateType;

}
