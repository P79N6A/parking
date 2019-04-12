package com.zoeeasy.cloud.gather.hikvision.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 海康平台停车场过车记录同步请求参数
 *
 * @author walkman
 * @date 2018-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SyncHikVehicleRecordRequestDto", description = "海康平台停车场过车记录同步请求参数")
public class HikVehicleRecordSyncRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 开始查询的过车时间
     */
    @ApiModelProperty(value = "开始查询的过车时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date startTime;

    /**
     * 结束查询的过车时间
     */
    @ApiModelProperty(value = "结束查询的过车时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date endTime;

    /**
     *
     */
    private Integer syncType;

}
