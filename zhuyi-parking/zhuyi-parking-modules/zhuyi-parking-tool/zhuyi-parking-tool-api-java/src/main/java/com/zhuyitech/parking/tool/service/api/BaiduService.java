package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.result.speech.SpeechRecognitionResultDto;

import java.io.InputStream;

/**
 * 百度相关服务
 *
 * @author walkman
 * @date 2018/4/29
 */
public interface BaiduService {

    /**
     * 语音识别
     *
     * @param inputStream inputStream
     * @return SpeechRecognitionResultDto
     */
    ObjectResultDto<SpeechRecognitionResultDto> asr(InputStream inputStream);
}
