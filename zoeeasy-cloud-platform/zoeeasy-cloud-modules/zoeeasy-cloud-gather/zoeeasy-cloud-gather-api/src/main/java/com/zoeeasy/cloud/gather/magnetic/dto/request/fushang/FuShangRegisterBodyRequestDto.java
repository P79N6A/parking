package com.zoeeasy.cloud.gather.magnetic.dto.request.fushang;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.gather.magnetic.cst.InMotionConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 富尚注册数据推送主体请求参数
 *
 * @Date: 2018/12/5
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FuShangRegisterBodyRequestDto", description = "富尚注册数据推送主体请求参数")
public class FuShangRegisterBodyRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号")
    @NotNull(message = InMotionConstant.PARKING_INFO_PARKING_CODE_NOT_NULL)
    private Integer parkID;

    /**
     * 上报时间
     */
    @ApiModelProperty(value = "上报时间")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_TIME_NOT_NULL)
    private String time;

    /**
     * 设备Id
     */
    @ApiModelProperty("设备Id")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_DEVICE_ID_NOT_NULL)
    private String deviceID;

    /**
     * 版本
     */
    @ApiModelProperty("版本")
    @NotBlank(message = InMotionConstant.VERSION_NOT_NULL)
    private String version;

    /**
     * 校验和
     */
    @ApiModelProperty("校验和")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_TOKEN_NOT_NULL)
    private String token;
}
