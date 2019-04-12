package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("模板明细请求参数")
public class TemplateGetRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
