package com.zhuyitech.parking.platform.controller.speech;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseRecognitionFaceResultDto;
import com.zhuyitech.parking.tool.dto.result.speech.SpeechRecognitionResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.api.BaiduService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.Map;

/**
 * 语音API
 *
 * @author walkman
 */
@Api(value = "语音API", description = "语音API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/speech")
public class SpeechController {

    @Autowired
    private BaiduService baiduService;

    /**
     * 语音识别
     *
     * @param request  request
     * @param response response
     * @return response
     */
    @ApiOperation(value = "语音识别", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = LicenseRecognitionFaceResultDto.class)
    @RequestMapping(value = "/asr", name = "语音识别", method = RequestMethod.POST)
    public ObjectResultDto<SpeechRecognitionResultDto> asr(HttpServletRequest request, HttpServletResponse response) {
        ObjectResultDto<SpeechRecognitionResultDto> resultDto = new ObjectResultDto<>();
        try {
            response.setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取文件
            Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();
            if (null == multipartFileMap) {
                return resultDto.failed();
            }
            Map.Entry<String, MultipartFile> entry = multipartFileMap.entrySet().iterator().next();
            MultipartFile multipartFile = entry.getValue();
            if (multipartFile.isEmpty()) {
                return resultDto.makeResult(ToolResultEnum.SPEECH_FILE_EMPTY.getValue(),
                        ToolResultEnum.SPEECH_FILE_EMPTY.getComment()
                );
            } else {
                ObjectResultDto<SpeechRecognitionResultDto> speechRecognitionResultDto = baiduService.asr(multipartFile.getInputStream());
                if (speechRecognitionResultDto.isFailed() || null == speechRecognitionResultDto.getData()) {
                    return resultDto.makeResult(ToolResultEnum.SPEECH_CANNOT_RECOGNITION.getValue(),
                            ToolResultEnum.SPEECH_CANNOT_RECOGNITION.getComment()
                    );
                }
                resultDto = speechRecognitionResultDto;
            }
        } catch (IOException e) {
            resultDto.failed("语音识别失败" + e.getMessage());
        }
        return resultDto;
    }

}
