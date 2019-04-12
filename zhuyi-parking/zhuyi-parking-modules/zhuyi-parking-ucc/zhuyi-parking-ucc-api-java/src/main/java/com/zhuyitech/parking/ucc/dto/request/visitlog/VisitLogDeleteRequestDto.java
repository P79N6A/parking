package com.zhuyitech.parking.ucc.dto.request.visitlog;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 删除登录日志请求参数
 *
 * @author walkman
 */
@ApiModel(description = "删除登录日志请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class VisitLogDeleteRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

}