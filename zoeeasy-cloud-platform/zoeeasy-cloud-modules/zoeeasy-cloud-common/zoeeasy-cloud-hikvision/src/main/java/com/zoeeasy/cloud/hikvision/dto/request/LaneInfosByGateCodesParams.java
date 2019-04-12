package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Description 根据出入口编号集获取出入口车道基本信息的请求参数
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LaneInfosByGateCodesParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出入口编号集  以“，”分割
     */
    @ApiModelProperty(value = "出入口编号集", required = true, notes = "出入口编号集  以“，”分割")
    @NotBlank
    private String gateCodes;

}
