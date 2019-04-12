package com.zoeeasy.cloud.pay.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.core.enums.ParamTypeEnum;
import com.zoeeasy.cloud.pay.config.AlipayProperty;
import com.zoeeasy.cloud.pay.config.WechatProperty;
import com.zoeeasy.cloud.pay.parameter.ParameterConfigService;
import com.zoeeasy.cloud.pay.parameter.dto.request.ParamConfigGetRequestDto;
import com.zoeeasy.cloud.pay.parameter.dto.result.ParamConfigGetResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/12/25 0025
 */
@Service("parameterConfigService")
@Slf4j
public class ParameterConfigServiceImpl implements ParameterConfigService {

    @Autowired
    private WechatProperty wechatProperty;

    @Autowired
    private AlipayProperty alipayProperty;

    /**
     * 获取微信支付宝参数
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParamConfigGetResultDto> paramConfigGet(ParamConfigGetRequestDto requestDto) {
        ObjectResultDto<ParamConfigGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getType()) {
                objectResultDto.failed();
                return objectResultDto;
            }
            ParamTypeEnum paramTypeEnum = ParamTypeEnum.parse(requestDto.getType());
            if (paramTypeEnum == null) {
                objectResultDto.failed();
                return objectResultDto;
            }
            ParamConfigGetResultDto paramConfigGetResultDto = new ParamConfigGetResultDto();
            if (requestDto.getType().equals(ParamTypeEnum.ALIPAY.getValue())) {
                paramConfigGetResultDto.setAliAppId(alipayProperty.getAppId());
            } else if (requestDto.getType().equals(ParamTypeEnum.JSAPIPAY.getValue())) {
                paramConfigGetResultDto.setWechatJsapiAppId(wechatProperty.getJsApiAppId());
            } else {
                paramConfigGetResultDto.setWechatPayAppId(wechatProperty.getAppId());
                paramConfigGetResultDto.setWechatPayAppSecret(wechatProperty.getAppSecret());
            }
            objectResultDto.setData(paramConfigGetResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("获取微信支付宝参数失败" + e.getMessage());
        }
        return objectResultDto;
    }
}
