package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("短信模板更新参数")
public class TemplateUpdateRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @ApiModelProperty("客户端ID")
    private String clientId;

    /**
     * 模板编号
     */
    @ApiModelProperty("模板编号")
    private String templateId;

    /**
     * 模板名称
     */
    @ApiModelProperty("模板名称")
    private String templateName;

    /**
     * 模板状态
     */
    @ApiModelProperty("模板状态")
    private Integer status;

    /**
     * 模板类型
     */
    @ApiModelProperty("模板类型")
    private Integer type;

    /**
     * 模板内容
     */
    @ApiModelProperty("模板内容")
    private String content;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

}
