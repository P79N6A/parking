package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 停车账单查询请求参数
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingPaymentInfoParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank
    private String plateNo;

    /**
     * 车牌颜色 参照车牌颜色枚举
     */
    @ApiModelProperty(value = "车牌颜色", required = true)
    @NotNull
    private Integer plateColor;

}