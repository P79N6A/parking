package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Description 根据停车场的parkCode集获取停车场基本信息的请求参数
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingInfosByParkCodesParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场编号集  以“，”分割
     */
    @ApiModelProperty(value = "停车场编号集 ", required = true, notes = "停车场编号集  以“，”分割")
    @NotBlank
    private String parkCodes;

}
