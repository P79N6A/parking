package com.zoeeasy.cloud.gather.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 海康平台停车账单请求参数
 *
 * @author walkman
 * @date 2018-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingPaymentInfoGetRequestDto", description = "海康平台停车账单请求参数")
public class ParkingPaymentInfoGetRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌颜色 参照车牌颜色枚举
     */
    @ApiModelProperty(value = "车牌号(0 未知,1 蓝色,2 黄色,3 白色,4 黑色,5 绿色,6 新能源,)", required = true, allowableValues = "0,1,2,3,4,5,6")
    @NotNull
    private Integer plateColor;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = "车牌号不能为空")
    private String plateNumber;

}
