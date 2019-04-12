package com.zhuyitech.parking.ucc.dto.request.visitlog;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取操作日志请求参数
 *
 * @author walkman
 */
@ApiModel(value = "VisitLogGetRequestDto", description = "获取菜单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class VisitLogGetRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;
}