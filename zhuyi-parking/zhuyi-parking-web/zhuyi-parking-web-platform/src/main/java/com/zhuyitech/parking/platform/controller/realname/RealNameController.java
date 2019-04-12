package com.zhuyitech.parking.platform.controller.realname;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.integration.service.api.RealNameIntegrationService;
import com.zhuyitech.parking.tool.dto.result.identity.IdentityRecognitionBackResultDto;
import com.zhuyitech.parking.tool.dto.result.identity.IdentityRecognitionFaceResultDto;
import com.zhuyitech.parking.tool.dto.result.identity.IdentityRecognitionFaceViewResultDto;
import com.zhuyitech.parking.tool.dto.result.oss.OssFileUploadResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.api.IdentityCardService;
import com.zhuyitech.parking.tool.service.api.OssService;
import com.zhuyitech.parking.tool.utils.InputStreamReuse;
import com.zhuyitech.parking.ucc.dto.request.realname.*;
import com.zhuyitech.parking.ucc.dto.result.UserAuthStatusResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserRealNameAuthApplyResultDto;
import com.zhuyitech.parking.ucc.service.api.UserAuthenticationApplyService;
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
 * 用户实名认证API
 *
 * @author walkman
 * @date 2018-01-10
 */
@Api(value = "用户实名认证", description = "用户实名认证", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/realname")
public class RealNameController {

    private static final Logger LOG = LoggerFactory.getLogger(RealNameController.class);

    @Autowired
    private UserAuthenticationApplyService userAuthenticationApplyService;

    @Autowired
    private RealNameIntegrationService realNameIntegrationService;

    @Autowired
    private OssService ossService;

    @Autowired
    private IdentityCardService identityCardService;

    /**
     * 获取用户实名状态
     */
    @ApiOperation(value = "获取用户实名状态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserAuthStatusResultDto.class)
    @RequestMapping(value = "/authStatus", name = "获取用户实名状态", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserAuthStatusResultDto> getCurrentUserAuthStatus(CurrentUserAuthStatusGetRequestDto requestDto) {
        return userAuthenticationApplyService.getCurrentUserAuthStatus(requestDto);
    }

    /**
     * 用户实名认证申请
     */
    @ApiOperation(value = "用户实名认证申请", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/addAuthApply", name = "用户实名认证申请", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto addUserAuthApply(HttpServletRequest request, UserRealNameAuthApplyAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            List<String> facePhotos = new ArrayList<>();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取文件列表
            Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();
            if (multipartFileMap == null) {
                return resultDto.makeResult(ToolResultEnum.IMAGE_EMPTY.getValue(), ToolResultEnum.IMAGE_EMPTY.getComment());
            }
            for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                MultipartFile multipartFile = entry.getValue();
                if (!multipartFile.isEmpty()) {
                    String fileExtName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                    String fileName = StringUtils.getUUID();
                    if (StringUtils.isNotEmpty(fileExtName)) {
                        fileName = fileName + "." + fileExtName;
                    }
                    ObjectResultDto<OssFileUploadResultDto> fileUploadResult = ossService.aliyunOssFileUpload(fileName, multipartFile.getInputStream());
                    if (fileUploadResult.isSuccess() && fileUploadResult.getData() != null) {
                        facePhotos.add(fileUploadResult.getData().getFileUrl());
                    }
                }
            }
            UserAuthApplyAddRequestDto userAuthApplyAddRequestDto = new UserAuthApplyAddRequestDto();
            userAuthApplyAddRequestDto.setRealName(requestDto.getRealName());
            userAuthApplyAddRequestDto.setCardNo(requestDto.getCardNo());
            userAuthApplyAddRequestDto.setCardFront(requestDto.getCardFront());
            userAuthApplyAddRequestDto.setCardContrary(requestDto.getCardContrary());
            userAuthApplyAddRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            if (!facePhotos.isEmpty()) {
                userAuthApplyAddRequestDto.setFacePhotos(StringUtils.join(facePhotos, ","));
            }
            resultDto = realNameIntegrationService.addAuthApply(userAuthApplyAddRequestDto);
        } catch (Exception e) {
            LOG.error("实名认证申请失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户实名认证申请修改
     */
    @ApiOperation(value = "用户实名认证修改", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/updateAuthApply", name = "用户实名认证申请修改", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto updateUserAuthApply(CurrentUserAuthApplyUpdateRequestDto requestDto) {
        return userAuthenticationApplyService.updateAuthApply(requestDto);
    }

    /**
     * 身份证识别正面
     */
    @ApiOperation(value = "身份证识别正面", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = IdentityRecognitionFaceViewResultDto.class)
    @RequestMapping(value = "/recognitionface", name = "身份证识别正面", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<IdentityRecognitionFaceViewResultDto> identityRecognitionByFace(HttpServletRequest request, HttpServletResponse response) {
        ObjectResultDto<IdentityRecognitionFaceViewResultDto> resultDto = new ObjectResultDto<>();
        try {
            response.setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取文件
            Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();
            if (null == multipartFileMap) {
                return resultDto.makeResult(ToolResultEnum.IMAGE_EMPTY.getValue(), ToolResultEnum.IMAGE_EMPTY.getComment());
            }
            Map.Entry<String, MultipartFile> entry = multipartFileMap.entrySet().iterator().next();
            MultipartFile multipartFile = entry.getValue();
            if (multipartFile.isEmpty()) {
                return resultDto.makeResult(ToolResultEnum.IMAGE_EMPTY.getValue(), ToolResultEnum.IMAGE_EMPTY.getComment());
            }
            String fileExtName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = StringUtils.getUUID();
            if (StringUtils.isNotEmpty(fileExtName)) {
                fileName = fileName + "." + fileExtName;
            }
            InputStreamReuse inputStreamReuse = new InputStreamReuse(multipartFile.getInputStream());
            ObjectResultDto<OssFileUploadResultDto> uploadResultDtoObjectResultDto =
                    ossService.aliyunOssFileUpload(fileName, inputStreamReuse.getInputStream());
            IdentityRecognitionFaceResultDto recognitionData = new IdentityRecognitionFaceResultDto();
            for (int i = 0; i < 3; i++) {
                ObjectResultDto<IdentityRecognitionFaceResultDto> recognition = identityCardService.identityRecognitionByFace(inputStreamReuse.getInputStream());
                if (recognition.isSuccess() && null != recognition.getData()) {
                    recognitionData = recognition.getData();
                    break;
                }
            }
            if (uploadResultDtoObjectResultDto.isFailed() || null == uploadResultDtoObjectResultDto.getData()) {
                return resultDto.makeResult(ToolResultEnum.ID_CARD_FACE_VERIFY_FAIL.getValue(), ToolResultEnum.ID_CARD_FACE_VERIFY_FAIL.getComment());
            }
            IdentityRecognitionFaceViewResultDto faceViewResultDto = new IdentityRecognitionFaceViewResultDto();
            faceViewResultDto.setImageUrl(uploadResultDtoObjectResultDto.getData().getFileUrl());
            faceViewResultDto.setName(recognitionData.getName());
            faceViewResultDto.setCardNo(recognitionData.getCardNo());
            resultDto.setData(faceViewResultDto);
            resultDto.success();
        } catch (IOException e) {
            LOG.error("身份证识别正面失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 身份证识别背面
     */
    @ApiOperation(value = "身份证识别背面", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = IdentityRecognitionBackResultDto.class)
    @RequestMapping(value = "/recognitionback", name = "身份证识别背面", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<IdentityRecognitionBackResultDto> identityRecognitionByBack(HttpServletRequest request, HttpServletResponse response) {
        ObjectResultDto<IdentityRecognitionBackResultDto> resultDto = new ObjectResultDto<>();
        try {
            response.setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取文件
            Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();
            if (null == multipartFileMap) {
                return resultDto.makeResult(ToolResultEnum.IMAGE_EMPTY.getValue(), ToolResultEnum.IMAGE_EMPTY.getComment());
            }
            Map.Entry<String, MultipartFile> entry = multipartFileMap.entrySet().iterator().next();
            MultipartFile multipartFile = entry.getValue();
            if (multipartFile.isEmpty()) {
                return resultDto.makeResult(ToolResultEnum.IMAGE_EMPTY.getValue(), ToolResultEnum.IMAGE_EMPTY.getComment());
            }
            String fileExtName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = StringUtils.getUUID();
            if (StringUtils.isNotEmpty(fileExtName)) {
                fileName = fileName + "." + fileExtName;
            }
            InputStreamReuse inputStreamReuse = new InputStreamReuse(multipartFile.getInputStream());
            ObjectResultDto<OssFileUploadResultDto> uploadResultDtoObjectResultDto = ossService.aliyunOssFileUpload(fileName, inputStreamReuse.getInputStream());
            IdentityRecognitionBackResultDto byBackData = new IdentityRecognitionBackResultDto();
            for (int i = 0; i < 3; i++) {
                ObjectResultDto<IdentityRecognitionBackResultDto> recognitionByBack = identityCardService.identityRecognitionByBack(inputStreamReuse.getInputStream());
                if (recognitionByBack.isSuccess() && null != recognitionByBack.getData()) {
                    byBackData = recognitionByBack.getData();
                    break;
                }
            }
            if (uploadResultDtoObjectResultDto.isFailed() || null == uploadResultDtoObjectResultDto.getData()) {
                return resultDto.makeResult(ToolResultEnum.ID_CARD_BACK_VERIFY_FAIL.getValue(), ToolResultEnum.ID_CARD_BACK_VERIFY_FAIL.getComment());
            }
            IdentityRecognitionBackResultDto recognitionBackResultDto = new IdentityRecognitionBackResultDto();
            recognitionBackResultDto.setImageUrl(uploadResultDtoObjectResultDto.getData().getFileUrl());
            recognitionBackResultDto.setEndDate(byBackData.getEndDate());
            recognitionBackResultDto.setStartDate(byBackData.getStartDate());
            recognitionBackResultDto.setIssue(byBackData.getIssue());
            recognitionBackResultDto.setSuccess(byBackData.getSuccess());
            recognitionBackResultDto.setProsAndCons(byBackData.getProsAndCons());
            resultDto.setData(recognitionBackResultDto);
            resultDto.success();
        } catch (IOException e) {
            LOG.error("身份证识别背面失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户实名认证申请查询
     */
    @ApiOperation(value = "用户实名认证查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserRealNameAuthApplyResultDto.class)
    @RequestMapping(value = "/getAuthApply", name = "用户实名认证申请查询", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserRealNameAuthApplyResultDto> getUserAuthApply(CurrentUserAuthApplyGetRequestDto requestDto) {
        return userAuthenticationApplyService.getCurrentUserAuthApply(requestDto);
    }

}
