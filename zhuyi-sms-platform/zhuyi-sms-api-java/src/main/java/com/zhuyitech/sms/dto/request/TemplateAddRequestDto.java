package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 添加模板请求Dto
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("添加模板请求")
public class TemplateAddRequestDto extends BaseDto {

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID", required = true)
    @NotNull(message = "客户端ID不能为空")
    private String clientId;

    /**
     * 模板编号
     */
    @ApiModelProperty("模板编号")
    @NotNull(message = "模板编号不能为空")
    private String templateId;

    /**
     * 模板名称
     */
    @ApiModelProperty("模板名称")
    @NotNull(message = "模板名称不能为空")
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
    @NotNull(message = "模板类型不能为空")
    private Integer type;

    /**
     * 模板内容
     */
    @ApiModelProperty("模板内容")
    @NotNull(message = "模板内容不能为空")
    private String content;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

}
