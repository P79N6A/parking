package com.zhuyitech.parking.tool.dto.request.notification;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板删除请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("模板删除请求参数")
public class TemplateDeleteRequestDto extends SessionEntityDto<Long> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

}
