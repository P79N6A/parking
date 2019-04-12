package com.zhuyitech.parking.tool.dto.request.version;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
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
@ApiModel(value = "AppVersionAddRequestDto", description = "版本号添加请求参数")
public class AppVersionAddRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端类型
     */
    @ApiModelProperty(value = "客户端类型", required = true)
    private Integer terminateType;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号", required = true)
    private String version;

    /**
     * 版本名称
     */
    @ApiModelProperty(value = "版本名称")
    private String versionName;

    /**
     * 更新说明
     */
    @ApiModelProperty(value = "更新说明")
    private String updateDescription;

    /**
     * 下载链接
     */
    @ApiModelProperty(value = "下载链接", required = true)
    private String downloadUrl;

    /**
     * 服务器链接
     */
    @ApiModelProperty(value = "服务器链接", required = true)
    private String fileUrl;

    /**
     * 是否强制更新
     */
    @ApiModelProperty(value = "是否强制更新")
    private Boolean forceUpdate;

}
