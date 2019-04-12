package com.zhuyitech.sms.dto.result;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信内容
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "短信内容")
public class SmsContentResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty("客户端ID")
    private String phoneNumber;

    /**
     * 客户端ID
     */
    @ApiModelProperty("客户端ID")
    private String clientId;

    /**
     *
     */
    @ApiModelProperty("客户端ID")
    private String templateId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("描述")
    private String description;

}
