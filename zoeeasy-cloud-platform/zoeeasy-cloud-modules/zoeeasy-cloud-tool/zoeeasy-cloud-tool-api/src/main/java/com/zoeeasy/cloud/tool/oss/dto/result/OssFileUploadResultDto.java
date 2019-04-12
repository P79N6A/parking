package com.zoeeasy.cloud.tool.oss.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;

/**
 * 文件上传返回结果视图
 *
 * @author AkeemSuper
 * @date 2018/4/11 0011
 */
@ApiModel(value = "OssFileUploadResultDto", description = "文件上传返回结果视图")
public class OssFileUploadResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 文件地址
     */
    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
