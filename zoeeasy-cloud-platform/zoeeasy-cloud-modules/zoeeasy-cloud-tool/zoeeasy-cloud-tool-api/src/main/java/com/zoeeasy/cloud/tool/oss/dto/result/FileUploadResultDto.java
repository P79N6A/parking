package com.zoeeasy.cloud.tool.oss.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 文件上传结果
 *
 * @author walkman
 */
@ApiModel("FileUploadResultDto")
@Data
@EqualsAndHashCode(callSuper = true)
public class FileUploadResultDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * url
     */
    @ApiModelProperty(value = "url")
    private String url;

}
