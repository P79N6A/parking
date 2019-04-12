package com.zhuyitech.parking.platform.controller.upload;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.result.oss.FileUploadResultDto;
import com.zhuyitech.parking.tool.dto.result.oss.OssFileUploadResultDto;
import com.zhuyitech.parking.tool.service.api.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author walkman
 * @date 2017-12-01
 */
@Api(value = "多文件上传Api", description = "多文件上传Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/upload")
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

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
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<FileUploadResultDto> fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            LOG.error(e.getMessage(), e);
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
    @RequestMapping(value = "/fileUploadByQiNiu", method = RequestMethod.POST)
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
            LOG.error(e.getMessage(), e);
            listResultDto.failed();
        }
        return listResultDto;
    }

}
