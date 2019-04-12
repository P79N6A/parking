package com.zhuyitech.parking.tool.dto.result.version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 获取版本列表响应参数
 *
 * @author zwq
 * @date 2018/4/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AppVersionResultDto", description = "获取版本列表响应参数")
public class AppVersionResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端类型 1.android 2 ios
     */
    @ApiModelProperty("客户端类型")
    private Integer terminateType;

    /**
     * 版本号
     */
    @ApiModelProperty("版本号")
    private String version;

    /**
     * 版本名称
     */
    @ApiModelProperty("版本名称")
    private String versionName;

    /**
     * 更新说明
     */
    @ApiModelProperty("更新说明")
    private String updateDescription;

    /**
     * 是否强制更新
     */
    @ApiModelProperty(value = "是否强制更新")
    private Boolean forceUpdate;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 二维码链接
     */
    @ApiModelProperty("二维码链接")
    private String codeUrl;

    /**
     * 下载链接
     */
    @ApiModelProperty("下载链接")
    private String downloadUrl;

    /**
     * 服务器链接
     */
    @ApiModelProperty("服务器链接")
    private String fileUrl;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date publishTime;

    /**
     * 下架时间
     */
    @ApiModelProperty(value = "下架时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date withdrawTime;

}
