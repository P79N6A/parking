package com.zhuyitech.sms.dto.result;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信客户端Dto
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("短信客户端")
public class SmsClientResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @ApiModelProperty("客户端ID")
    private String clientId;

    /**
     * 客户端密钥
     */
    @ApiModelProperty("clientSecrete")
    private String clientSecrete;

    /**
     * 客户端名称
     */
    @ApiModelProperty("客户端名称")
    private String clientName;

    @ApiModelProperty("短信通道")
    private String channelProvider;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("描述")
    private String description;

}
