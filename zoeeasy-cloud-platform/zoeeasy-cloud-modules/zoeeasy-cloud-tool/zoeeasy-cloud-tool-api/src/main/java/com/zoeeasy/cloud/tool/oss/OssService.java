package com.zoeeasy.cloud.tool.oss;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.tool.oss.dto.result.OssFileUploadResultDto;

import java.io.InputStream;

/**
 * 文件存储oss服务
 *
 * @author AkeemSuper
 * @date 2018/4/11 0011
 */
public interface OssService {

    /**
     * 七牛Oss文件上传服务
     *
     * @param inputStream 输入流
     */
    ObjectResultDto<OssFileUploadResultDto> qiNiuOssFileUpload(InputStream inputStream);

    /**
     * 阿里云Oss文件上传服务
     *
     * @param fileName    fileName
     * @param inputStream 输入流
     */
    ObjectResultDto<OssFileUploadResultDto> aliyunOssFileUpload(String fileName, InputStream inputStream);

}
