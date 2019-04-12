package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 分页获取停车场过车记录的请求参数
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleRecordsParams extends HikvisionParams implements Serializable {

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
     * 出入口编号
     */
    private String gateCode;

    /**
     * 车牌号码 支持模糊查询
     */
    private String plateNo;

    /**
     * 过车方向 参照过车方向枚举
     */
    private Integer direct;

    /**
     * 车辆类型 参照车辆类型枚举
     */
    private Integer carType;

    /**
     * 查询开始的过车时间
     */
    private Long startTime;

    /**
     * 查询结束的过车时间
     */
    private Long endTime;

}
