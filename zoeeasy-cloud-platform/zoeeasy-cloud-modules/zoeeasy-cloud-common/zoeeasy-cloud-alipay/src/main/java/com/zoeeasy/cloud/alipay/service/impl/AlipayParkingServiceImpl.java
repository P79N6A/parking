package com.zoeeasy.cloud.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.alipay.config.AlipayConfig;
import com.zoeeasy.cloud.alipay.constant.AlipayConstants;
import com.zoeeasy.cloud.alipay.enums.AlipayErrEnum;
import com.zoeeasy.cloud.alipay.params.*;
import com.zoeeasy.cloud.alipay.result.AlipayParkingConfigQueryResult;
import com.zoeeasy.cloud.alipay.result.AlipayParkingCreateParkingLotInfoResult;
import com.zoeeasy.cloud.alipay.result.AlipayParkingVehicleQueryResult;
import com.zoeeasy.cloud.alipay.service.AlipayParkingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *     支付宝车主服务接口实现
 * </pre>
 *
 * @author walkman
 * @date 2018-03-20-13:13
 */
@Service("alipayParkingService")
@Slf4j
public class AlipayParkingServiceImpl implements AlipayParkingService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 停车ISV系统配置
     *
     * @param
     * @return
     */
    @Override
    public ResultDto setConfig(AlipayParkingConfigSetParam params) {
        ResultDto resultDto = new ResultDto();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayEcoMycarParkingConfigSetRequest request = new AlipayEcoMycarParkingConfigSetRequest();
        AlipayEcoMycarParkingConfigSetModel model = new AlipayEcoMycarParkingConfigSetModel();
        if (StringUtils.isEmpty(params.getMerchantName())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setMerchantName(params.getMerchantName());
        if (StringUtils.isEmpty(params.getAccountNo())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setAccountNo(params.getAccountNo());
        if (null == params.getInterfaceInfoList() || CollectionUtils.isEmpty(params.getInterfaceInfoList())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setInterfaceInfoList(params.getInterfaceInfoList());
        if (StringUtils.isEmpty(params.getMerchantServicePhone())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setMerchantServicePhone(params.getMerchantServicePhone());
        model.setMerchantLogo(params.getMerchantLogo());
        request.setBizModel(model);
        try {
            AlipayEcoMycarParkingConfigSetResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                resultDto.success();
            } else {
                resultDto.makeResult(AlipayErrEnum.RESPONSE_FAILED.getValue(), AlipayErrEnum.RESPONSE_FAILED.getComment());
            }
        } catch (AlipayApiException e) {
            log.error("请求失败:{}", e.getMessage());
            resultDto.makeResult(AlipayErrEnum.REQUEST_FAILED.getValue(), AlipayErrEnum.REQUEST_FAILED.getComment());
        }
        return resultDto;
    }

    /**
     * ISV系统配置查询接口
     *
     * @param params
     * @return
     */
    @Override
    public ObjectResultDto<AlipayParkingConfigQueryResult> queryConfig(AlipayParkingConfigQueryParam params) {
        ObjectResultDto<AlipayParkingConfigQueryResult> objectResultDto = new ObjectResultDto<>();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayEcoMycarParkingConfigQueryRequest request = new AlipayEcoMycarParkingConfigQueryRequest();
        AlipayEcoMycarParkingConfigQueryModel model = new AlipayEcoMycarParkingConfigQueryModel();
        model.setInterfaceName(params.getInterfaceName());
        model.setInterfaceType(params.getInterfaceType());
        request.setBizModel(model);
        try {
            AlipayEcoMycarParkingConfigQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                AlipayParkingConfigQueryResult alipayParkingConfigQueryResult = modelMapper.map(response, AlipayParkingConfigQueryResult.class);
                objectResultDto.setData(alipayParkingConfigQueryResult);
                objectResultDto.success();
            } else {
                objectResultDto.makeResult(AlipayErrEnum.RESPONSE_FAILED.getValue(), AlipayErrEnum.RESPONSE_FAILED.getComment());
            }
        } catch (AlipayApiException e) {
            log.error("请求失败:{}", e.getMessage());
            objectResultDto.makeResult(AlipayErrEnum.REQUEST_FAILED.getValue(), AlipayErrEnum.REQUEST_FAILED.getComment());
        }
        return objectResultDto;
    }

    /**
     * 录入停车场信息
     *
     * @param params
     * @return
     */
    @Override
    public ObjectResultDto<AlipayParkingCreateParkingLotInfoResult> createParkingLotInfo(AlipayParkingCreateParkingLotInfoParam params) {
        ObjectResultDto<AlipayParkingCreateParkingLotInfoResult> objectResultDto = new ObjectResultDto<>();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayEcoMycarParkingParkinglotinfoCreateRequest request = new AlipayEcoMycarParkingParkinglotinfoCreateRequest();
        // AlipayEcoMycarParkingParkinglotinfoCreateModel model = new AlipayEcoMycarParkingParkinglotinfoCreateModel();
        if (StringUtils.isEmpty(params.getOutParkingId())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingAddress())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingLotType())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingPoiid())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingMobile())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getPayType())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingName())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        request.setBizContent("{" +
                "\"out_parking_id\":\"" + params.getOutParkingId() + "\"," +
                "\"parking_address\":\"" + params.getParkingAddress() + "\"," +
                "\"parking_lot_type\":\"" + params.getParkingLotType() + "\"," +
                "\"parking_poiid\":\"" + params.getParkingPoiid() + "\"," +
                "\"parking_mobile\":\"" + params.getParkingMobile() + "\"," +
                "\"pay_type\":\"" + params.getPayType() + "\"," +
                "\"shopingmall_id\":\"" + params.getShopingmallId() + "\"," +
                "\"parking_fee_description\":\"" + params.getParkingFeeDescription() + "\"," +
                "\"parking_name\":\"" + params.getParkingName() + "\"," +
                "\"time_out\":\"" + params.getTimeOut() + "\"" +
                "  }");
        try {
            AlipayEcoMycarParkingParkinglotinfoCreateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                AlipayParkingCreateParkingLotInfoResult alipayParkingCreateParkingLotInfoResult = modelMapper.map(response, AlipayParkingCreateParkingLotInfoResult.class);
                objectResultDto.setData(alipayParkingCreateParkingLotInfoResult);
                objectResultDto.success();
            } else {
                objectResultDto.makeResult(AlipayErrEnum.RESPONSE_FAILED.getValue(), AlipayErrEnum.RESPONSE_FAILED.getComment());
            }
        } catch (AlipayApiException e) {
            log.error("请求失败:{}", e.getMessage());
            objectResultDto.makeResult(AlipayErrEnum.REQUEST_FAILED.getValue(), AlipayErrEnum.REQUEST_FAILED.getComment());
        }
        return objectResultDto;
    }

    /**
     * 修改停车场信息
     *
     * @param params
     * @return
     */
    @Override
    public ResultDto updateParkingLotInfo(AlipayParkingUpdateParkingLotInfoParam params) {
        ResultDto resultDto = new ResultDto();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayEcoMycarParkingParkinglotinfoUpdateRequest request = new AlipayEcoMycarParkingParkinglotinfoUpdateRequest();
        // AlipayEcoMycarParkingParkinglotinfoUpdateModel model = new AlipayEcoMycarParkingParkinglotinfoUpdateModel();
        if (StringUtils.isEmpty(params.getParkingId())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingAddress())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingLotType())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingPoiid())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingMobile())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getPayType())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingName())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        request.setBizContent("{" +
                "\"parking_id\":\"" + params.getParkingId() + "\"," +
                "\"parking_address\":\"" + params.getParkingAddress() + "\"," +
                "\"parking_name\":\"" + params.getParkingName() + "\"," +
                "\"time_out\":\"" + params.getTimeOut() + "\"," +
                "\"parking_lot_type\":\"" + params.getParkingLotType() + "\"," +
                "\"pay_type\":\"" + params.getPayType() + "\"," +
                "\"parking_poiid\":\"" + params.getParkingPoiid() + "\"," +
                "\"parking_mobile\":\"" + params.getParkingMobile() + "\"," +
                "\"shopingmall_id\":\"" + params.getShopingmallId() + "\"," +
                "\"parking_fee_description\":\"" + params.getParkingFeeDescription() + "\"" +
                "  }");
        try {
            AlipayEcoMycarParkingParkinglotinfoUpdateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                resultDto.success();
            } else {
                resultDto.makeResult(AlipayErrEnum.RESPONSE_FAILED.getValue(), AlipayErrEnum.RESPONSE_FAILED.getComment());
            }
        } catch (AlipayApiException e) {
            log.error("请求失败:{}", e.getMessage());
            resultDto.makeResult(AlipayErrEnum.REQUEST_FAILED.getValue(), AlipayErrEnum.REQUEST_FAILED.getComment());
        }
        return resultDto;
    }

    /**
     * 车辆驶入接口
     *
     * @param params
     * @return
     */
    @Override
    public ResultDto syncEnterInfo(AlipayParkingSyncEnterInfoParam params) {
        ResultDto resultDto = new ResultDto();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayEcoMycarParkingEnterinfoSyncRequest request = new AlipayEcoMycarParkingEnterinfoSyncRequest();
        AlipayEcoMycarParkingEnterinfoSyncModel model = new AlipayEcoMycarParkingEnterinfoSyncModel();
        if (StringUtils.isEmpty(params.getParkingId())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setParkingId(params.getParkingId());
        if (StringUtils.isEmpty(params.getCarNumber())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setCarNumber(params.getCarNumber());
        if (StringUtils.isEmpty(params.getInTime())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setInTime(params.getInTime());
        request.setBizModel(model);
        try {
            AlipayEcoMycarParkingEnterinfoSyncResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                resultDto.success();
            } else {
                resultDto.makeResult(AlipayErrEnum.RESPONSE_FAILED.getValue(), AlipayErrEnum.RESPONSE_FAILED.getComment());
            }
        } catch (AlipayApiException e) {
            log.error("请求失败:{}", e.getMessage());
            resultDto.makeResult(AlipayErrEnum.REQUEST_FAILED.getValue(), AlipayErrEnum.REQUEST_FAILED.getComment());
        }
        return resultDto;
    }

    /**
     * 车辆驶出接口
     *
     * @param params
     * @return
     */
    @Override
    public ResultDto syncExitInfo(AlipayParkingSyncExitInfoParam params) {
        ResultDto resultDto = new ResultDto();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayEcoMycarParkingExitinfoSyncRequest request = new AlipayEcoMycarParkingExitinfoSyncRequest();
        AlipayEcoMycarParkingExitinfoSyncModel model = new AlipayEcoMycarParkingExitinfoSyncModel();
        if (StringUtils.isEmpty(params.getParkingId())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setParkingId(params.getParkingId());
        if (StringUtils.isEmpty(params.getCarNumber())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setCarNumber(params.getCarNumber());
        if (StringUtils.isEmpty(params.getOutTime())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        model.setOutTime(params.getOutTime());
        request.setBizModel(model);
        try {
            AlipayEcoMycarParkingExitinfoSyncResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                resultDto.success();
            } else {
                resultDto.makeResult(AlipayErrEnum.RESPONSE_FAILED.getValue(), AlipayErrEnum.RESPONSE_FAILED.getComment());
            }
        } catch (AlipayApiException e) {
            log.error("请求失败:{}", e.getMessage());
            resultDto.makeResult(AlipayErrEnum.REQUEST_FAILED.getValue(), AlipayErrEnum.REQUEST_FAILED.getComment());
        }
        return resultDto;
    }

    /**
     * 订单同步接口
     *
     * @param params
     * @return
     */
    @Override
    public ResultDto syncOrder(AlipayParkingSyncOrderParam params) {
        ResultDto resultDto = new ResultDto();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayEcoMycarParkingOrderSyncRequest request = new AlipayEcoMycarParkingOrderSyncRequest();
        if (StringUtils.isEmpty(params.getCardNumber())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getCarNumber())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getInDuration())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getInTime())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getOrderNo())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getOrderStatus())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getOrderTime())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getOutOrderNo())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getOutParkingId())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingId())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getParkingName())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getPayMoney())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getPayType())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getUserId())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getPayTime())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        AlipayEcoMycarParkingOrderSyncModel model = modelMapper.map(params, AlipayEcoMycarParkingOrderSyncModel.class);
        request.setBizModel(model);
        try {
            AlipayEcoMycarParkingOrderSyncResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                resultDto.success();
            } else {
                resultDto.makeResult(AlipayErrEnum.RESPONSE_FAILED.getValue(), AlipayErrEnum.RESPONSE_FAILED.getComment());
            }
        } catch (AlipayApiException e) {
            log.error("请求失败:{}", e.getMessage());
            resultDto.makeResult(AlipayErrEnum.REQUEST_FAILED.getValue(), AlipayErrEnum.REQUEST_FAILED.getComment());
        }
        return resultDto;
    }

    /**
     * 订单更新接口
     *
     * @param params
     * @return
     */
    @Override
    public ResultDto updateOrder(AlipayParkingUpdateOrderParam params) {
        ResultDto resultDto = new ResultDto();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        if (StringUtils.isEmpty(params.getOrderNo())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getOrderStatus())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getUserId())) {
            return resultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        AlipayEcoMycarParkingOrderUpdateRequest request = new AlipayEcoMycarParkingOrderUpdateRequest();
        AlipayEcoMycarParkingOrderUpdateModel model = new AlipayEcoMycarParkingOrderUpdateModel();
        model.setOrderNo(params.getOrderNo());
        model.setOrderStatus(params.getOrderStatus());
        model.setUserId(params.getUserId());
        try {
            AlipayEcoMycarParkingOrderUpdateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                resultDto.success();
            } else {
                resultDto.makeResult(AlipayErrEnum.RESPONSE_FAILED.getValue(), AlipayErrEnum.RESPONSE_FAILED.getComment());
            }
        } catch (AlipayApiException e) {
            log.error("请求失败:{}", e.getMessage());
            resultDto.makeResult(AlipayErrEnum.REQUEST_FAILED.getValue(), AlipayErrEnum.REQUEST_FAILED.getComment());
        }
        return resultDto;
    }

    /**
     * 车牌查询接口
     *
     * @param params
     * @return
     */
    @Override
    public ObjectResultDto<AlipayParkingVehicleQueryResult> queryVehicle(AlipayParkingVehicleQueryParam params) {
        ObjectResultDto<AlipayParkingVehicleQueryResult> objectResultDto = new ObjectResultDto<>();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        if (StringUtils.isEmpty(params.getCarId())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        if (StringUtils.isEmpty(params.getAccessToken())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }
        AlipayEcoMycarParkingVehicleQueryRequest request = new AlipayEcoMycarParkingVehicleQueryRequest();
        AlipayEcoMycarParkingVehicleQueryModel model = new AlipayEcoMycarParkingVehicleQueryModel();
        model.setCarId(params.getCarId());
        try {
            AlipayEcoMycarParkingVehicleQueryResponse response = alipayClient.execute(request, params.getAccessToken());
            if (response.isSuccess()) {
                AlipayParkingVehicleQueryResult result = new AlipayParkingVehicleQueryResult();
                result.setCarNumber(response.getCarNumber());
                objectResultDto.setData(result);
                objectResultDto.success();
            } else {
                objectResultDto.makeResult(Integer.parseInt(response.getCode()), response.getMsg());
            }
        } catch (AlipayApiException e) {
            log.error("请求失败:{}", e.getMessage());
            objectResultDto.makeResult(AlipayErrEnum.REQUEST_FAILED.getValue(), AlipayErrEnum.REQUEST_FAILED.getComment());
        }
        return objectResultDto;
    }

}
