package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("分页查询模板请求")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientQueryPagedResultRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @ApiModelProperty("客户端ID")
    private String clientId;

    /**
     * 客户端名称
     */
    @ApiModelProperty("客户端名称")
    private String clientName;

}
