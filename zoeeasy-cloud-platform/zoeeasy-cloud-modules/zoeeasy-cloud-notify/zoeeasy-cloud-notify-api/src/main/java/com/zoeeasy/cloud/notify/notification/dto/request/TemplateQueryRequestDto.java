package com.zoeeasy.cloud.notify.notification.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据租户id和业务类型去模板表里查
 *
 * @author wangfeng
 * @date 2018/11/20 16:01
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TemplateQueryRequestDto", description = "模板查询参数")
public class TemplateQueryRequestDto extends BaseDto {

    /**
     * 租户id
     */
    private Long tenantId;
    /**
     * 业务类型
     */
    private Integer type;

}
