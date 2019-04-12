package com.zoeeasy.cloud.notify.notification.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TemplateQueryPagedResultRequestDto", description = "短信模板分页查询参数")
public class TemplateQueryPagedResultRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    private String templateId;

    private String type;

    private Integer status;

}
