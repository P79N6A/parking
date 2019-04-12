package com.zoeeasy.cloud.open.client.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("ApiClientResultDto")
public class ApiClientResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "tenantId")
    private Long tenantId;

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "accessKey")
    private String accessKey;

    /**
     * 客户端秘钥
     */
    @ApiModelProperty(value = "accessSecrete")
    private String accessSecrete;

    /**
     * scope
     */
    @ApiModelProperty(value = "scope")
    private String scope;

    /**
     * 备注
     */
    @ApiModelProperty(value = "remarks")
    private String remarks;

    /**
     * 有效开始时间
     */
    @ApiModelProperty(value = "beginTime")
    private Date beginTime;

    /**
     * 有效结束时间
     */
    @ApiModelProperty(value = "endTime")
    private Date endTime;
}
