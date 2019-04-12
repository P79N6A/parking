package com.zoeeasy.cloud.pds.magneticerrorreport.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 查询地磁误报处理记录列表
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticErrorReportListResultRequestDto", description = "查询地磁误报处理记录列表")
@EqualsAndHashCode(callSuper = false)
public class MagneticErrorReportListResultRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域编号
     */
    @ApiModelProperty(value = "区域编号")
    private String areaCode;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endTime;
}
