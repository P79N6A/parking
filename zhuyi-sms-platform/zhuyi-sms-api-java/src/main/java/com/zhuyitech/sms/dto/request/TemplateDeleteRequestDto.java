package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("模板删除请求参数")
public class TemplateDeleteRequestDto extends EntityDto<Long> {

}
