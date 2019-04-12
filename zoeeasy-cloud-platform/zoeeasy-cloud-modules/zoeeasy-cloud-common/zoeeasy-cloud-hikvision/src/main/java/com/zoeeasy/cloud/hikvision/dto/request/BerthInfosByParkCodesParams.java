package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Description: 分页获取路边停车场的泊位信息的请求参数
 * @Author: AkeemSuper
 * @Date: 2018/2/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BerthInfosByParkCodesParams extends HikvisionParams implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 停车场编号集
     */
    @ApiModelProperty(value = "停车场编号集", required = true, notes = "以逗号分割")
    @NotBlank
    private String parkCodes;

    /**
     * 泊位状态
     */
    private Integer berthState;

}
