package com.zhuyitech.parking.platform.controller.mycar;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.result.license.LicenseRecognitionBackResultDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseRecognitionFaceResultDto;
import com.zhuyitech.parking.tool.dto.result.oss.OssFileUploadResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.api.LicenseRecognitionService;
import com.zhuyitech.parking.tool.service.api.OssService;
import com.zhuyitech.parking.tool.utils.InputStreamReuse;
import com.zhuyitech.parking.ucc.car.UserCarAuthService;
import com.zhuyitech.parking.ucc.car.request.UserCarAuthAddRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户车辆认证api
 *
 * @author AkeemSuper
 */
@Api(value = "用户车辆认证api", description = "用户车辆认证api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/userCarAuth")
@Slf4j
public class UserCarAuthController {

    @Autowired
    private UserCarAuthService userCarAuthService;

    @Autowired
    private LicenseRecognitionService licenseRecognitionService;

    @Autowired
    private OssService ossService;

    /**
     * 行驶证识别正面
     */
    @ApiOperation(value = "行驶证识别正面", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = LicenseRecognitionFaceResultDto.class)
    @RequestMapping(value = "/recognitionface", name = "行驶证识别正面", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<LicenseRecognitionFaceResultDto> licenseRecognitionByFace(HttpServletRequest request, HttpServletResponse response) {
        ObjectResultDto<LicenseRecognitionFaceResultDto> resultDto = new ObjectResultDto<>();
        try {
            response.setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取文件
            Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();
            if (null == multipartFileMap || multipartFileMap.isEmpty()) {
                return resultDto.failed();
            }
            Map.Entry<String, MultipartFile> entry = multipartFileMap.entrySet().iterator().next();
            MultipartFile multipartFile = entry.getValue();
            if (multipartFile.isEmpty()) {
                return resultDto.makeResult(ToolResultEnum.LICENSE_RECOGNITION_FAIL.getValue(),
                        ToolResultEnum.LICENSE_RECOGNITION_FAIL.getComment()
                );
            } else {
                String fileExtName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                String fileName = StringUtils.getUUID();
                if (StringUtils.isNotEmpty(fileExtName)) {
                    fileName = fileName + "." + fileExtName;
                }
                InputStreamReuse inputStreamReuse = new InputStreamReuse(multipartFile.getInputStream());
                ObjectResultDto<OssFileUploadResultDto> uploadResultDtoObjectResultDto = ossService.aliyunOssFileUpload(fileName, inputStreamReuse.getInputStream());
                if (uploadResultDtoObjectResultDto.isFailed() || null == uploadResultDtoObjectResultDto.getData()) {
                    return resultDto.makeResult(ToolResultEnum.LICENSE_RECOGNITION_FAIL.getValue(),
                            ToolResultEnum.LICENSE_RECOGNITION_FAIL.getComment()
                    );
                }
                LicenseRecognitionFaceResultDto licenseRecognitionFaceResultDto = new LicenseRecognitionFaceResultDto();
                for (int i = 0; i < 3; i++) {
                    ObjectResultDto<LicenseRecognitionFaceResultDto> licenseRecognitionObjectResultDto = licenseRecognitionService.licenseRecognitionByFace(inputStreamReuse.getInputStream());
                    if (licenseRecognitionObjectResultDto.isSuccess() && null != licenseRecognitionObjectResultDto.getData()) {
                        licenseRecognitionFaceResultDto = licenseRecognitionObjectResultDto.getData();
                        break;
                    }
                }
                licenseRecognitionFaceResultDto.setImageUrl(uploadResultDtoObjectResultDto.getData().getFileUrl());
                resultDto.setData(licenseRecognitionFaceResultDto);
                resultDto.success();
            }
        } catch (Exception e) {
            log.error("行驶证识别失败:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 行驶证识别背面
     */
    @ApiOperation(value = "行驶证背面识别", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = LicenseRecognitionBackResultDto.class)
    @RequestMapping(value = "/recognitionback", name = "行驶证背面识别", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<LicenseRecognitionBackResultDto> licenseRecognitionByBack(HttpServletRequest request, HttpServletResponse response) {
        ObjectResultDto<LicenseRecognitionBackResultDto> resultDto = new ObjectResultDto<>();
        try {
            response.setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取文件
            Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();
            if (null == multipartFileMap || multipartFileMap.isEmpty()) {
                resultDto.failed();
            }
            Map.Entry<String, MultipartFile> entry = multipartFileMap.entrySet().iterator().next();
            MultipartFile multipartFile = entry.getValue();
            if (multipartFile.isEmpty()) {
                resultDto.failed();
            }
            String fileExtName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = StringUtils.getUUID();
            if (StringUtils.isNotEmpty(fileExtName)) {
                fileName = fileName + "." + fileExtName;
            }
            ObjectResultDto<OssFileUploadResultDto> uploadResultDtoObjectResultDto = ossService.aliyunOssFileUpload(fileName, multipartFile.getInputStream());
            if (uploadResultDtoObjectResultDto.isFailed() || uploadResultDtoObjectResultDto.getData() == null) {
                LicenseRecognitionBackResultDto licenseRecognitionBackResultDto = new LicenseRecognitionBackResultDto();
                licenseRecognitionBackResultDto.setSuccess(Boolean.FALSE);
                resultDto.setData(licenseRecognitionBackResultDto);
            } else {
                LicenseRecognitionBackResultDto licenseRecognitionBackResultDto = new LicenseRecognitionBackResultDto();
                licenseRecognitionBackResultDto.setImageUrl(uploadResultDtoObjectResultDto.getData().getFileUrl());
                licenseRecognitionBackResultDto.setSuccess(Boolean.TRUE);
                resultDto.setData(licenseRecognitionBackResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("行驶证背面识别失败:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户添加车辆认证
     */
    @ApiOperation(value = "用户添加车辆认证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/addUserCarAuth", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto addUserCarAuth(UserCarAuthAddRequestDto requestDto) {
        return userCarAuthService.addUserCarAuth(requestDto);
    }

    /**
     * 用户修改车辆认证
     */
//    @ApiOperation(value = "用户修改车辆认证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @RequestMapping(value = "/updateUserCarAuth", method = RequestMethod.POST)
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
//    public ResultDto updateUserCarAuth(UserCarAuthUpdateRequestDto requestDto) {
//        return userCarAuthService.updateUserCarAuth(requestDto);
//    }
}
