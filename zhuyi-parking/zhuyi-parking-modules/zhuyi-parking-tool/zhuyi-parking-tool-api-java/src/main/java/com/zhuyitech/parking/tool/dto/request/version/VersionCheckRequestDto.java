package com.zhuyitech.parking.tool.dto.request.version;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 版本号比对请求参数
 *
 * @author zwq
 * @date 2018/4/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VersionCheckRequestDto", description = "版本号比对请求参数")
public class VersionCheckRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号", required = true)
    private String version;

    /**
     * 客户端类型
     */
    @ApiModelProperty(value = "客户端类型", required = true)
    private Integer terminateType;

}
