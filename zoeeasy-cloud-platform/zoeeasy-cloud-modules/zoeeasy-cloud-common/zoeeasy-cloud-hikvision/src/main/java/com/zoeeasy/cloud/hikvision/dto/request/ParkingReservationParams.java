package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 车位预约请求参数
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingReservationParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号", required = true)
    @NotBlank
    private String parkCode;

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

    /**
     * 支付金额 单位分
     */
    @ApiModelProperty(value = "支付金额", required = true)
    @NotNull
    private Integer payAmount;

    /**
     * 预计开始时间 格式yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(value = "预计开始时间", required = true)
    @NotBlank
    private String payTime;

    /**
     * 预定时长 单位:分
     */
    @ApiModelProperty(value = "预定时长", required = true)
    @NotNull
    private Integer bookDuration;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", required = true)
    @NotBlank
    private String Telephone;

    /**
     * 车位号 如果不为空，表示预约到指定车位
     */
    private String plotNum;

}
