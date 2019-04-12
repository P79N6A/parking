package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 分页获取停车场某个时间段临时停车缴费记录的请求参数
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TempCarChargeRecordsParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页码 页码大于0
     */
    @ApiModelProperty(value = "当前页码", required = true)
    @NotNull
    @Min(value = 0, message = "页码大于0")
    private Integer pageNo;

    /**
     * 每页数据记录数
     */
    @ApiModelProperty(value = "每页数据记录数", required = true)
    @NotNull
    @Max(value = 1000, message = "建议400，不超过1000")
    private Integer pageSize;

    /**
     * 停车场编号
     */
    private String parkCode;

    /**
     * 车牌号码 支持模糊查询
     */
    private Integer plateNo;

    /**
     * 车牌颜色
     */
    private Integer plateColor;

    /**
     * 支付类型 参照支付类型枚举
     */
    private Integer payType;

    /**
     * 开始查询的缴费时间
     */
    private Long startTime;

    /**
     * 结束查询的缴费时间
     */
    private Long endTime;

}
