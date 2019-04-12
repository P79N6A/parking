package com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 根据厂商和设备ID查询对应的地磁检测器请求参数
 *
 * @Date: 2018/9/26
 * @author: zwq
 */
@Data
@ApiModel(value = "MagneticDetectorUpdateLastHeartbeatTimeRequestDto", description = "根据厂商和设备ID查询对应的地磁检测器请求参数")
public class MagneticDetectorUpdateLastHeartbeatTimeRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 最后心跳时间
     */
    @ApiModelProperty("最后心跳时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date lastHeartbeatTime;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer occupyStatus;

    /**
     * 电量
     */
    @ApiModelProperty("电量")
    private Integer electricity;

    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date registerTime;

    /**
     * 版本号
     */
    @ApiModelProperty("版本号")
    private String versionNumber;
}

