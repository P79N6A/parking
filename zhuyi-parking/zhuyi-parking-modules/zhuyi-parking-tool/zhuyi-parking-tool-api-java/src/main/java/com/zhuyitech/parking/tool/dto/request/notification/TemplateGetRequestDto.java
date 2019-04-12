package com.zhuyitech.parking.tool.dto.request.notification;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("模板明细请求参数")
public class TemplateGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
