package com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 分页查询地磁检测记录请求参数
 *
 * @Date: 2018/9/30
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticReportRecordQueryPageRequestDto", description = "页查询地磁检测记录请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticReportRecordQueryPageRequestDto extends SignedSessionPagedRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     *停车场
     */
    @ApiModelProperty(value = "停车场")
    private String parkingName;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "编号")
    private String code;

    /**
     * 区域code
     */
    @ApiModelProperty(value = "区域code")
    private String areaCode;

    /**
     * 停车场类型
     */
    @ApiModelProperty(value = "停车场类型")
    private String lotType;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endTime;
}
