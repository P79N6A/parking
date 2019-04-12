package com.zoeeasy.cloud.integration.service.impl.pay;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.core.enums.ParamTypeEnum;
import com.zoeeasy.cloud.integration.pay.PayConfigIntegrationService;
import com.zoeeasy.cloud.order.parking.ParkingOrderManagerService;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.pay.parameter.ParameterConfigService;
import com.zoeeasy.cloud.pay.parameter.dto.request.ParamConfigGetRequestDto;
import com.zoeeasy.cloud.pay.parameter.dto.result.ParamConfigGetResultDto;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayConfigGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.JsapiConfigGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.WeixinConfigGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.alipay.AlipayConfigResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.weixin.JsapiConfigResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.weixin.WeixinConfigResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付配置集成服务
 *
 * @author walkman
 */
@Service("payConfigIntegrationService")
@Slf4j
public class PayConfigIntegrationImpl implements PayConfigIntegrationService {

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private ParkingOrderManagerService parkingOrderManagerService;

    @Autowired
    private ParameterConfigService parameterConfigService;

    /**
     * 获取支付宝必要参数
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AlipayConfigResultDto> getAlipayParams(AlipayConfigGetRequestDto requestDto) {
        ObjectResultDto<AlipayConfigResultDto> objectResultDto = new ObjectResultDto<>();
//        Long tenantId = getTenantId(requestDto.getOrderNo());
        ParamConfigGetResultDto paramConfigGetResultDto = getParamValue(ParamTypeEnum.ALIPAY.getValue());
        if (null != paramConfigGetResultDto) {
            AlipayConfigResultDto getAlipayParamsResultDto = new AlipayConfigResultDto();
            getAlipayParamsResultDto.setAppId(paramConfigGetResultDto.getAliAppId());
            objectResultDto.setData(getAlipayParamsResultDto);
        }
        objectResultDto.success();
        return objectResultDto;
    }

    /**
     * 获取微信必要参数
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<WeixinConfigResultDto> getWeixinParams(WeixinConfigGetRequestDto requestDto) {
        ObjectResultDto<WeixinConfigResultDto> objectResultDto = new ObjectResultDto<>();
//        Long tenantId = getTenantId(requestDto.getOrderNo());
        ParamConfigGetResultDto paramConfigGetResultDto = getParamValue(ParamTypeEnum.WEIXINPAY.getValue());
        if (null != paramConfigGetResultDto) {
            WeixinConfigResultDto getWeixinParamsResultDto = new WeixinConfigResultDto();
            getWeixinParamsResultDto.setAppId(paramConfigGetResultDto.getWechatPayAppId());
            getWeixinParamsResultDto.setAppSecrete(paramConfigGetResultDto.getWechatPayAppSecret());
            objectResultDto.setData(getWeixinParamsResultDto);
        }
        objectResultDto.success();
        return objectResultDto;
    }

    /**
     * 获取微信公众号必要参数
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<JsapiConfigResultDto> getJsapiParams(JsapiConfigGetRequestDto requestDto) {
        ObjectResultDto<JsapiConfigResultDto> objectResultDto = new ObjectResultDto<>();
//        Long tenantId = getTenantId(requestDto.getOrderNo());
        ParamConfigGetResultDto wechatJsapiAppId = getParamValue(ParamTypeEnum.JSAPIPAY.getValue());
        if (null != wechatJsapiAppId) {
            JsapiConfigResultDto jsapiConfigResultDto = new JsapiConfigResultDto();
            jsapiConfigResultDto.setAppId(wechatJsapiAppId.getWechatJsapiAppId());
            objectResultDto.setData(jsapiConfigResultDto);
        }
        //jsapiConfigResultDto.setAppSecrete(WechatConfig.WECHAT_JSAPI_APPSECRET);
        objectResultDto.success();
        return objectResultDto;
    }

    private Long getTenantId(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return null;
        }
        ParkingOrderGetRequestDto parkingOrderGetRequestDto = new ParkingOrderGetRequestDto();
        parkingOrderGetRequestDto.setOrderNo(orderNo);
        ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDtoObject = parkingOrderManagerService.getParkingOrder(parkingOrderGetRequestDto);
        if (parkingOrderResultDtoObject.isFailed() || null == parkingOrderResultDtoObject.getData()) {
            return null;
        }
        ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
        parkingInfoGetRequestDto.setParkingId(parkingOrderResultDtoObject.getData().getParkingId());
        ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDtoObject = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
        if (parkingInfoResultDtoObject.isFailed() || null == parkingInfoResultDtoObject.getData()) {
            return null;
        }
        return parkingInfoResultDtoObject.getData().getTenantId();
    }

    private ParamConfigGetResultDto getParamValue(Integer type) {
        ParamConfigGetRequestDto paramGetRequestDto = new ParamConfigGetRequestDto();
        paramGetRequestDto.setType(type);
        ObjectResultDto<ParamConfigGetResultDto> paramGetResultDtoObject = parameterConfigService.paramConfigGet(paramGetRequestDto);
        if (paramGetResultDtoObject.isFailed() || null == paramGetResultDtoObject.getData()) {
            return null;
        }
        return paramGetResultDtoObject.getData();

    }
}
