package com.zoeeasy.cloud.pds.magneticerrorreport.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 地磁误报处理记录参数返回
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticErrorReportResultDto", description = "地磁误报处理记录参数返回")
@EqualsAndHashCode(callSuper = false)
public class MagneticErrorReportResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 地磁检测器编号
     */
    @ApiModelProperty(value = "地磁检测器编号")
    private String code;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String tenant;

    /**
     * 异常信息
     */
    @ApiModelProperty(value = "异常信息")
    private String processReason;

    /**
     * 异常记录时间
     */
    @ApiModelProperty(value = "异常记录时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date processTime;
}
