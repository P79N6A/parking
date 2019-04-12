package com.zhuyitech.parking.tool.dto.request.version;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
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
@ApiModel(value = "AppVersionPageListGetRequestDto", description = "获取版本列表请求参数")
public class AppVersionPageListGetRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端类型 1.android 2 ios
     */
    @ApiModelProperty("客户端类型")
    private Integer terminateType;

}
