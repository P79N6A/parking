package com.zhuyitech.parking.tool.dto.result.speech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 百度语音识别结果Dto
 *
 * @author walkman
 * @date 2018/05/28
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "SpeechRecognitionResultDto", description = "百度语音识别结果Dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class SpeechRecognitionResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 识别结果数组
     */
    @ApiModelProperty(value = "识别结果数组")
    private List<String> results;

}