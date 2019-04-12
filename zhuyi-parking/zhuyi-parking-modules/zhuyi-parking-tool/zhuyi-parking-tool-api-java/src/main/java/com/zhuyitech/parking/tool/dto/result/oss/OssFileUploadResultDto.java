package com.zhuyitech.parking.tool.dto.result.oss;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件上传返回结果视图
 *
 * @author AkeemSuper
 * @date 2018/4/11 0011
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OssFileUploadResultDto", description = "文件上传返回结果视图")
public class OssFileUploadResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 文件地址
     */
    private String fileUrl;

}
