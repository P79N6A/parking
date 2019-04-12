package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 分页获取停车场基本信息的请求参数
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingInfosParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", required = true)
    @NotNull
    private Integer pageNo;

    /**
     * 每页数据记录数
     */
    @ApiModelProperty(value = "每页数据记录数", required = true)
    @NotNull
    private Integer pageSize;

}
