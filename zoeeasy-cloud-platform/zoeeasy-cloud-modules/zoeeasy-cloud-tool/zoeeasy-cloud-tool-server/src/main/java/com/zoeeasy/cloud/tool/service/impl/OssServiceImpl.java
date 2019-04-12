package com.zoeeasy.cloud.tool.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.tool.config.AliyunOssConfig;
import com.zoeeasy.cloud.tool.constant.QiNiuConstant;
import com.zoeeasy.cloud.tool.oss.OssService;
import com.zoeeasy.cloud.tool.oss.dto.result.OssFileUploadResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/4/11 0011
 */
@Service(value = "ossService")
@Slf4j
public class OssServiceImpl implements OssService {

    @Autowired
    private AliyunOssConfig aliyunOssPropertis;

    /**
     * 阿里云Oss文件上传服务
     *
     * @param fileName    fileName
     * @param inputStream 输入流
     */
    @Override
    public ObjectResultDto<OssFileUploadResultDto> aliyunOssFileUpload(String fileName, InputStream inputStream) {
        ObjectResultDto<OssFileUploadResultDto> resultDto = new ObjectResultDto<>();
        OssFileUploadResultDto ossFileUploadResultDto = new OssFileUploadResultDto();
        if (null == inputStream || StringUtils.isEmpty(aliyunOssPropertis.getBucket())) {
            return resultDto.failed();
        }
        OSSClient ossClient = createOssClient();
        if (ossClient == null) {
            return resultDto.failed();
        }
        try {

            String bucketName = aliyunOssPropertis.getBucket();
            // OSS操作，例如上传文件
            //创建上传Object的Metadata
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentLength(inputStream.available());
//            objectMetadata.setCacheControl("no-cache");
//            objectMetadata.setHeader("Pragma", "no-cache");
//            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
//            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, fileName, inputStream);

            // 设置URL过期时间为50年  3600l* 1000*24*365*10
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 50);
            // 生成URL
            URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
            if (url != null) {
                ossFileUploadResultDto.setFileUrl(url.toString());
                resultDto.setData(ossFileUploadResultDto);
                resultDto.success();
            }
        } catch (OSSException oe) {
            log.error("阿里云文件OSS上传文件失败" + oe.getMessage());
            resultDto.failed();
        } catch (ClientException ce) {
            log.error("阿里云文件OSS上传文件失败" + ce.getMessage());
            resultDto.failed();
        } catch (Exception e){
            log.error("阿里云文件OSS上传文件失败" + e.getMessage());
            resultDto.failed();
        } finally {
            try {
                // 关闭流
                inputStream.close();
            } catch (IOException e) {
                log.debug("inputStream close IOException:" + e.getMessage());
            }
            ossClient.shutdown();
        }
        return resultDto;
    }

    /**
     * 七牛Oss文件上传服务
     */
    @Override
    public ObjectResultDto<OssFileUploadResultDto> qiNiuOssFileUpload(InputStream inputStream) {
        ObjectResultDto<OssFileUploadResultDto> resultDto = new ObjectResultDto<>();
        OssFileUploadResultDto ossFileUploadResultDto = new OssFileUploadResultDto();
        //设置好账号的ACCESS_KEY和SECRET_KEY
        String accesskay = QiNiuConstant.QINIU_ACCESSKAY;
        String secretkay = QiNiuConstant.QINIU_SECRETKAY;
        //要上传的空间
        String bucketname = QiNiuConstant.QINIU_BUCKET;
        //七牛的默认域名
        String url = QiNiuConstant.QINIU_URL;
        try {
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = null;
            //密钥配置
            Auth auth = Auth.create(accesskay, secretkay);
            //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
            Zone z = Zone.autoZone();
            Configuration c = new Configuration(z);
            //创建上传对象
            UploadManager uploadManager = new UploadManager(c);
            String uploadToken = auth.uploadToken(bucketname);
            if (null == inputStream) {
                return resultDto.failed();
            } else {
                Response response = uploadManager.put(inputStream, key, uploadToken, null, null);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                String realUrl = url + "/" + putRet.hash;
                ossFileUploadResultDto.setFileUrl(realUrl);
                resultDto.setData(ossFileUploadResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("七牛上传文件失败" + e.getMessage());
            resultDto.failed();
        } finally {
            try {
                // 关闭流
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.debug("inputStream close IOException:" + e.getMessage());
            }
        }
        return resultDto;
    }


    /**
     * 创建阿里云OSS
     *
     * @return
     */
    private OSSClient createOssClient() {

        if (StringUtils.isEmpty(aliyunOssPropertis.getEndpoint())
                || StringUtils.isEmpty(aliyunOssPropertis.getAccessKeyId())
                || StringUtils.isEmpty(aliyunOssPropertis.getAccessKeySecret())) {
            return null;
        }
        String endpoint = aliyunOssPropertis.getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = aliyunOssPropertis.getAccessKeyId();
        String accessKeySecret = aliyunOssPropertis.getAccessKeySecret();
        // 创建OSSClient实例
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param filenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg")
                || filenameExtension.equalsIgnoreCase(".jpg")
                || filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase(".pptx")
                || filenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase(".docx")
                || filenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

}
