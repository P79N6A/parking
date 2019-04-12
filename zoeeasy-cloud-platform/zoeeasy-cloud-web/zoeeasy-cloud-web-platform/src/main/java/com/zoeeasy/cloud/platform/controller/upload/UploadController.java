package com.zoeeasy.cloud.platform.controller.upload;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.core.utils.ImageBase64Utils;
import com.zoeeasy.cloud.core.utils.UUIDTool;
import com.zoeeasy.cloud.tool.oss.OssService;
import com.zoeeasy.cloud.tool.oss.dto.request.BaseImageUploadRequestDto;
import com.zoeeasy.cloud.tool.oss.dto.result.FileUploadResultDto;
import com.zoeeasy.cloud.tool.oss.dto.result.OssFileUploadResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author walkman
 * @since 2017-12-01
 */
@Api(value = "多文件上传Api", tags = "多文件上传Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/upload")
@Slf4j
@RequiresAuthentication
public class UploadController {

    @Autowired
    private OssService ossService;

    /**
     * 文件上传
     *
     * @param request  request
     * @param response response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "文件上传", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = FileUploadResultDto.class)
    @PostMapping(value = "/fileUpload")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<FileUploadResultDto> fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ListResultDto<FileUploadResultDto> listResultDto = new ListResultDto<>();
        try {
            response.setCharacterEncoding("UTF-8");

            MultipartHttpServletRequest multipartRequest;
            if (request instanceof MultipartHttpServletRequest) {
                multipartRequest = (MultipartHttpServletRequest) request;
            } else {
                CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
                multipartRequest = commonsMultipartResolver.resolveMultipart(request);
            }

            //获取文件列表
            Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();
            if (multipartFileMap == null) {
                listResultDto.failed();
            } else {
                List<FileUploadResultDto> fileUploadResultDtoList = new ArrayList<>();
                //遍历文件列表
                for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                    MultipartFile multipartFile = entry.getValue();
                    if (!multipartFile.isEmpty()) {
                        FileUploadResultDto fileUploadResultDto = new FileUploadResultDto();
                        String fileExtName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                        String fileName = StringUtils.getUUID();
                        if (StringUtils.isNotEmpty(fileExtName)) {
                            fileName = fileName + "." + fileExtName;
                        }
                        ObjectResultDto<OssFileUploadResultDto> fileUploadResult = ossService.aliyunOssFileUpload(fileName, multipartFile.getInputStream());
                        if (fileUploadResult.isSuccess() && fileUploadResult.getData() != null) {
                            fileUploadResultDto.setUrl(fileUploadResult.getData().getFileUrl());
                        }
                        fileUploadResultDtoList.add(fileUploadResultDto);
                    }
                }
                listResultDto.setItems(fileUploadResultDtoList);
                listResultDto.success();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            listResultDto.failed();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 文件上传
     *
     * @param request  request
     * @param response response
     * @return response
     * @throws Exception Exception
     */
    @ApiOperation(value = "文件上传", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = FileUploadResultDto.class)
    @PostMapping(value = "/fileUploadByQiNiu")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<FileUploadResultDto> fileUploadByQiNiu(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ListResultDto<FileUploadResultDto> listResultDto = new ListResultDto<>();
        try {
            response.setCharacterEncoding("UTF-8");

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取文件列表
            Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();
            if (multipartFileMap == null) {
                listResultDto.failed();
            } else {
                List<FileUploadResultDto> fileUploadResultDtoList = new ArrayList<>();
                //遍历文件列表
                for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                    MultipartFile multipartFile = entry.getValue();
                    if (!multipartFile.isEmpty()) {
                        FileUploadResultDto fileUploadResultDto = new FileUploadResultDto();
                        String url = ossService.qiNiuOssFileUpload(multipartFile.getInputStream()).getData().getFileUrl();
                        fileUploadResultDto.setUrl(url);
                        fileUploadResultDtoList.add(fileUploadResultDto);
                    }
                }
                listResultDto.setItems(fileUploadResultDtoList);
                listResultDto.success();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * base64图片上传
     *
     * @return response
     * @throws Exception Exception
     */
    @ApiOperation(value = "base64图片上传", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = FileUploadResultDto.class)
    @PostMapping(value = "/baseImageUpload")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<FileUploadResultDto> baseImageUpload(@RequestBody BaseImageUploadRequestDto requestDto) throws Exception {
        ObjectResultDto<FileUploadResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            String dataPrix = "";
            String data;
            if (StringUtils.isEmpty(requestDto.getBaseData())) {
                objectResultDto.failed();
                return objectResultDto;
            }
            String[] d = requestDto.getBaseData().split("base64,");
            if (d != null && d.length == 2) {
                dataPrix = d[0];
                data = d[1];
            } else {
                data = requestDto.getBaseData();
            }
            String fileName = UUIDTool.getUUID() + ImageBase64Utils.getFileNameSuffix(dataPrix);
            byte[] decode = ImageBase64Utils.base64String2ByteFun(data);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
            ObjectResultDto<OssFileUploadResultDto> fileUploadResult = ossService.aliyunOssFileUpload(fileName, byteArrayInputStream);
            if (fileUploadResult.isFailed() || fileUploadResult.getData() == null) {
                objectResultDto.failed();
            } else {
                FileUploadResultDto fileUploadResultDto = new FileUploadResultDto();
                fileUploadResultDto.setUrl(fileUploadResult.getData().getFileUrl());
                objectResultDto.setData(fileUploadResultDto);
                objectResultDto.success();
            }
            return objectResultDto;
        } catch (Exception e) {
            log.error("base64文件上传失败" + e.getMessage(), e);
            objectResultDto.failed();
        }
        return objectResultDto;
    }

}
