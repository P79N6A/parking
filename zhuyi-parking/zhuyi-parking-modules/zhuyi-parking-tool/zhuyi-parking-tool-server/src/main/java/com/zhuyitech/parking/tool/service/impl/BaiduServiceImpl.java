package com.zhuyitech.parking.tool.service.impl;


import com.baidu.aip.speech.AipSpeech;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.common.utils.ImageBase64;
import com.zhuyitech.parking.tool.config.BaiduConfig;
import com.zhuyitech.parking.tool.dto.result.speech.SpeechRecognitionResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.api.BaiduService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 百度相关服务
 *
 * @author walkman
 * @date 2018/05/29
 */
@Service("baiduService")
@Slf4j
public class BaiduServiceImpl implements BaiduService {

    private AipSpeech aipSpeech;

    @Autowired
    private BaiduConfig baiduConfig;

    /**
     * 语音识别
     *
     * @param inputStream inputStream
     * @return SpeechRecognitionResultDto
     */
    @Override
    public ObjectResultDto<SpeechRecognitionResultDto> asr(InputStream inputStream) {
        ObjectResultDto<SpeechRecognitionResultDto> resultDto = new ObjectResultDto<>();
        if (null == inputStream) {
            return resultDto.makeResult(ToolResultEnum.SPEECH_FILE_EMPTY.getValue(), ToolResultEnum.SPEECH_FILE_EMPTY.getComment());
        }
        try {
            SpeechRecognitionResultDto speechRecognitionResultDto = new SpeechRecognitionResultDto();
            JSONObject jsonObject = getAipSpeech().asr(ImageBase64.readInputStream(inputStream), "pcm", 16000, null);
            log.debug("Baidu  asr Result[result:{}] Success!", jsonObject.toString());
            Integer errNo = jsonObject.getInt("err_no");

            if (errNo.compareTo(0) == 0) {
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                if (jsonArray != null && jsonArray.length() > 0) {
                    int length = jsonArray.length();
                    List<String> results = new ArrayList<>();
                    for (int i = 0; i < length; i += 1) {
                        results.add(jsonArray.getString(i));
                    }
                    speechRecognitionResultDto.setResults(results);
                }
                resultDto.setData(speechRecognitionResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("百度语音识别出现错误！错误信息：", e);
        }
        return resultDto;
    }

    /**
     * @return
     */
    private AipSpeech getAipSpeech() {
        try {

            if (aipSpeech == null) {

                // 初始化一个AipSpeech
                aipSpeech = new AipSpeech(baiduConfig.getAppId(), baiduConfig.getApikey(),
                        baiduConfig.getSecretekey());
                // 可选：设置网络连接参数
                aipSpeech.setConnectionTimeoutInMillis(60000);
                aipSpeech.setSocketTimeoutInMillis(60000);
                // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//                aipSpeech.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//                aipSpeech.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
                // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
                // 也可以直接通过jvm启动参数设置此环境变量
//                System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
            }
        } catch (Exception e) {
            log.error("创建AipSpeech失败", e);
        }
        return aipSpeech;
    }

}
