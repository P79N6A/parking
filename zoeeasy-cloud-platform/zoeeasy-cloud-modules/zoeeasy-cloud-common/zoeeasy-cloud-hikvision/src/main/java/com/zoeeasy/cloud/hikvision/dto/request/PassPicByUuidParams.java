package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Description:获取过车记录的图片的请求参数
 * @Autor: AkeemSuper
 * @Date: 2018/2/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PassPicByUuidParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录唯一ID
     */
    @ApiModelProperty(value = "过车记录唯一ID", required = true)
    @NotBlank
    private String unid;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号", required = true)
    @NotBlank
    private String parkCode;

}
