package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TemplateQueryPagedResultRequestDto", description = "短信模板分页查询参数")
public class TemplateQueryPagedResultRequestDto extends PagedResultRequestDto {

    private static final long serialVersionUID = 1L;

    private String templateId;

    private String clientId;

    private String type;

    private Integer status;

}
