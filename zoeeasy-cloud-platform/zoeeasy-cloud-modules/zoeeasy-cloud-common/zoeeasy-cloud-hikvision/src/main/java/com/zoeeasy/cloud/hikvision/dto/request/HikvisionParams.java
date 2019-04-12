package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description hikvision 系统请求参数
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * appkey
     */
    @ApiModelProperty(value = "appkey", required = true)
    @NotBlank
    private String appkey;

    /**
     * 时间
     */
    @ApiModelProperty(value = "time", required = true)
    @NotNull
    private Long time;

    /**
     * 随机字符串
     */
    @ApiModelProperty(value = "nonce", required = true)
    @NotBlank
    private String nonce;

}
