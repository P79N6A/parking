package com.zoeeasy.cloud.gather.magnetic.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 分页获取地磁检测器参数返回
 *
 * @Date: 2018/9/30
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticReportRecordQueryPageResultDto", description = "分页获取地磁检测器参数返回")
public class MagneticReportRecordQueryPageResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场名字
     */
    @ApiModelProperty(value = "停车场名字")
    private String parkingName;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String parkingLotNumber;

    /**
     * 入场时间
     */
    @ApiModelProperty(value = "入场时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date beginTime;

    /**
     * 出场时间
     */
    @ApiModelProperty(value = "出场时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date endTime;

    /**
     * 停车时长
     */
    @ApiModelProperty(value = "停车时长")
    private Long stopTime;

    /**
     * 出车类型
     */
    @ApiModelProperty(value = "出车类型")
    private String changeType;

}
