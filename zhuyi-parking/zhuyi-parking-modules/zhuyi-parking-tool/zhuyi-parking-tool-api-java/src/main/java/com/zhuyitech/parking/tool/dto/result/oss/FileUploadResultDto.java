package com.zhuyitech.parking.tool.dto.result.oss;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;
import com.zhuyitech.parking.common.constant.Const;
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
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("FileUploadResultDto")
public class FileUploadResultDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * url
     */
    @ApiModelProperty(value = "url")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String url;

}
