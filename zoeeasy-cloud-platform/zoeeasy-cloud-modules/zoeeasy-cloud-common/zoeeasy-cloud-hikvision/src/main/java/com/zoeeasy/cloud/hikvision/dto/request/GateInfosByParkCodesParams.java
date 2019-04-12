package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Description 根据停车场编号集获取停车场出入口基本信息的请求参数
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GateInfosByParkCodesParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场编号集  以“，”分割
     */
    @ApiModelProperty(value = "停车场编号集", required = true, notes = "以逗号分割")
    @NotBlank
    private String parkCodes;

}
