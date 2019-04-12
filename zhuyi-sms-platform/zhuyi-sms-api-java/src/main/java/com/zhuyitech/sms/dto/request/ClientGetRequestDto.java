package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("获取模板请求")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
