package com.zhuyitech.parking.tool.dto.result.version;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 版本号比对返回结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AppVersionCheckResultDto", description = "版本号比对返回结果")
public class AppVersionCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否有新版本")
    private Boolean newVersion;

    /**
     * url
     */
    @ApiModelProperty(value = "是否强制更新")
    private Boolean forceUpdate;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String version;

    /**
     * 下载链接
     */
    @ApiModelProperty(value = "下载链接")
    private String clientUrl;

    /**
     * 更新说明
     */
    @ApiModelProperty(value = "更新说明")
    private String updateDescription;

}
