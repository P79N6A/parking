package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.request.IdentityCardVerifyRequestDto;
import com.zhuyitech.parking.tool.dto.result.identity.*;

import java.io.InputStream;

/**
 * 身份证服务
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
public interface IdentityCardService {

    /**
     * 身份证识别正面
     *
     * @param inputStream IdentityRecognitionRequestDto
     * @return IdentityRecognitionFaceResultDto
     */
    ObjectResultDto<IdentityRecognitionFaceResultDto> identityRecognitionByFace(InputStream inputStream);

    /**
     * 身份证识别背面
     *
     * @param inputStream IdentityRecognitionRequestDto
     * @return IdentityRecognitionBackResultDto
     */
    ObjectResultDto<IdentityRecognitionBackResultDto> identityRecognitionByBack(InputStream inputStream);

    /**
     * 用户身份信息第三方认证
     *
     * @param requestDto IdentityCardVerifyRequestDto
     * @return IdentityCardVerifyResultDto
     */
    ObjectResultDto<IdentityCardVerifyResultDto> verifyIdentityCard(IdentityCardVerifyRequestDto requestDto);

    /**
     * 阿里云身份证二要素校验一
     *
     * @param requestDto IdentityCardVerifyRequestDto
     * @return IdCardCertResultDto
     */
    ObjectResultDto<IdCardCertResultDto> verityIdCardCert(IdentityCardVerifyRequestDto requestDto);

    /**
     * 阿里云身份证二要素校验二
     *
     * @param requestDto IdentityCardVerifyRequestDto
     * @return IdCardResultDto
     */
    ObjectResultDto<IdCardResultDto> verityIdCard(IdentityCardVerifyRequestDto requestDto);

    /**
     * 阿里云身份证二要素校验方案选择
     *
     * @param requestDto IdentityCardVerifyRequestDto
     * @return IdentityCardVerifyResultDto
     */
    ObjectResultDto<IdentityCardVerifyResultDto> verifyIdCardSelect(IdentityCardVerifyRequestDto requestDto);
}
