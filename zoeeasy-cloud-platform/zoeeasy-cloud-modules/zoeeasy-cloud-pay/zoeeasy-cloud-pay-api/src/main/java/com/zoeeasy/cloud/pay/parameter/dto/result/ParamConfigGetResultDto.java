package com.zoeeasy.cloud.pay.parameter.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取参数列表返回参数
 *
 * @author walkman
 */
@ApiModel(value = "ParamGetResultDto", description = "获取参数列表返回参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamConfigGetResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * aliAppId
     */
    @ApiModelProperty(value = "aliAppId")
    private String aliAppId;

    /**
     * aliAppId
     */
    @ApiModelProperty(value = "wechatPayAppId")
    private String wechatPayAppId;

    /**
     * aliAppId
     */
    @ApiModelProperty(value = "wechatPayAppSecret")
    private String wechatPayAppSecret;

    /**
     * aliAppId
     */
    @ApiModelProperty(value = "wechatJsapiAppId")
    private String wechatJsapiAppId;
}