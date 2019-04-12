package com.zoeeasy.cloud.integration.service.impl.pay;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.core.enums.PayTypeEnum;
import com.zoeeasy.cloud.core.utils.QrCodeCreateUtil;
import com.zoeeasy.cloud.integration.pay.TradePaymentIntegrationService;
import com.zoeeasy.cloud.pay.enums.PayResultEnum;
import com.zoeeasy.cloud.pay.trade.TradePaymentManagerService;
import com.zoeeasy.cloud.pay.trade.dto.request.order.PlacePaymentOrderRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.order.PlacePaymentOrderResultDto;
import com.zoeeasy.cloud.pay.trade.validator.PlacePaymentOrderValidator;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetRequestDto;
import com.zoeeasy.cloud.tool.oss.OssService;
import com.zoeeasy.cloud.tool.oss.dto.result.OssFileUploadResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 支付
 *
 * @author walkman
 */
@Service("tradePaymentIntegrationService")
@Slf4j
public class TradePaymentIntegrationServiceImpl implements TradePaymentIntegrationService {

    @Autowired
    private OssService ossService;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private TradePaymentManagerService tradePaymentManagerService;

    /**
     * 支付订单下单
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<PlacePaymentOrderResultDto> placePaymentOrder(@FluentValid(PlacePaymentOrderValidator.class) PlacePaymentOrderRequestDto requestDto) {
        if (null != requestDto.getParkingId()) {
            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(requestDto.getParkingId());
            ObjectResultDto<ParkingInfoResultDto> objectResultDto = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
            if (objectResultDto.isSuccess() && null != objectResultDto.getData()) {
                ParkingInfoResultDto parkingInfoResultDto = objectResultDto.getData();
                requestDto.setTenantId(parkingInfoResultDto.getTenantId());
            }
        }
        ObjectResultDto<PlacePaymentOrderResultDto> placePaymentOrderResultDto = tradePaymentManagerService.placePaymentOrder(requestDto);
        //支付类型
        PlacePaymentOrderResultDto placePaymentOrderResult = placePaymentOrderResultDto.getData();
        PayTypeEnum payTypeEnum = PayTypeEnum.valuedOf(requestDto.getPayType());
        if (payTypeEnum != null && (
                PayTypeEnum.ALI_SCANBAR.getValue().equals(payTypeEnum.getValue()) ||
                        PayTypeEnum.WX_NATIVE.getValue().equals(payTypeEnum.getValue()))) {
            try {
                // 生成二维码
                final String fileName = StringUtils.getUUID() + ".png";
                final ByteArrayOutputStream outputStream =
                        QrCodeCreateUtil.createQrCode(placePaymentOrderResult.getQrCode(), ParkingConstant.QRCODE_IMAGE_SIZE, "png");
                //上传二维码
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
                ObjectResultDto<OssFileUploadResultDto> fileUploadResult = ossService.aliyunOssFileUpload(fileName, byteArrayInputStream);
                if (fileUploadResult.isSuccess() && fileUploadResult.getData() != null) {
                    String codeUrl = fileUploadResult.getData().getFileUrl();
                    placePaymentOrderResult.setQrCodeUrl(codeUrl);
                } else {
                    placePaymentOrderResultDto.makeResult(PayResultEnum.TRADE_PLACE_ORDER_ERROR.getValue(), PayResultEnum.TRADE_PLACE_ORDER_ERROR.getComment());
                }
            } catch (Exception e) {
                placePaymentOrderResultDto.makeResult(PayResultEnum.TRADE_PLACE_ORDER_ERROR.getValue(), PayResultEnum.TRADE_PLACE_ORDER_ERROR.getComment());
            }
        }
        return placePaymentOrderResultDto;
    }
}
