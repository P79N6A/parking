package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseRecognitionBackResultDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseRecognitionFaceResultDto;

import java.io.InputStream;

/**
 * 行驶证识别服务
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
public interface LicenseRecognitionService {

    /**
     * 行驶证正面识别服务
     *
     * @param inputStream inputStream
     * @return LicenseRecognitionFaceResultDto
     */
    ObjectResultDto<LicenseRecognitionFaceResultDto> licenseRecognitionByFace(InputStream inputStream);

    /**
     * 行驶证背面识别服务
     *
     * @param inputStream inputStream
     * @return LicenseRecognitionBackResultDto
     */
    ObjectResultDto<LicenseRecognitionBackResultDto> licenseRecognitionByBack(InputStream inputStream);
}
