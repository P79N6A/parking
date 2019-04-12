package com.zoeeasy.cloud.pms.platform.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场二维码数据响应参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingQrcodeResultDto", description = "停车场二维码数据响应参数")
public class ParkingQrcodeResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingId;

    /**
     * 随机字符串
     */
    @ApiModelProperty("随机字符串")
    private String noncestr;

    /**
     * 二维码图片存放路径
     */
    @ApiModelProperty("二维码图片存放路径")
    private String codeUrl;
}
