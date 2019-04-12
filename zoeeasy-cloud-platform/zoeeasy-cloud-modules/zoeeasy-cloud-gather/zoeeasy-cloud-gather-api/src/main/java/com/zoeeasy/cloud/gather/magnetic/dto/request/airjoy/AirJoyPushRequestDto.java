package com.zoeeasy.cloud.gather.magnetic.dto.request.airjoy;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.gather.magnetic.cst.AirJoyPushConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 艾佳推送
 *
 * @Date: 2018/9/26
 * @author: zwq
 */
@Data
@ApiModel(value = "AirJoyPushRequestDto", description = "艾佳推送")
@EqualsAndHashCode(callSuper = false)
public class AirJoyPushRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地磁设备MAC地址
     */
    @ApiModelProperty(value = "地磁设备MAC地址")
    @NotBlank(message = AirJoyPushConstant.AIRJOY_PUSH_MAC_NOT_NULL)
    private String mac;

    /**
     * 地磁安装的车位编号
     */
    @ApiModelProperty(value = "地磁安装的车位编号")
    private String parkingLotNo;

    /**
     * 车位状态
     */
    @ApiModelProperty(value = "车位状态")
    @NotBlank(message = AirJoyPushConstant.AIRJOY_PUSH_STATUS_NOT_NULL)
    private String status;

    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型")
    @NotBlank(message = AirJoyPushConstant.AIRJOY_PUSH_DATA_TYPE_NOT_NULL)
    private String dataType;

    /**
     * 时间戳字符串
     */
    @ApiModelProperty(value = "时间戳字符串")
    @NotBlank(message = AirJoyPushConstant.AIRJOY_PUSH_UPLOAD_TIME_NOT_NULL)
    private String uploadTime;
}
