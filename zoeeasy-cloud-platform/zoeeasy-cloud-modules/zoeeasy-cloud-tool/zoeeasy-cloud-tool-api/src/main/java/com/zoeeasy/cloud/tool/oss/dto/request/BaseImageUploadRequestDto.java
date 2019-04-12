package com.zoeeasy.cloud.tool.oss.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/12/1 0001
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BaseImageUploadRequestDto", description = "base46图片上传请求参数")
public class BaseImageUploadRequestDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 图片数据
     */
    @ApiModelProperty("baseData")
    private String baseData;
}
