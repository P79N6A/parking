package com.zhuyitech.parking.platform.controller.trade;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.scapegoat.infrastructure.web.utils.ActionUtils;
import com.zhuyitech.parking.integration.order.dto.request.ParkingOrderPaymentConfirmRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.ParkingOrderPaymentRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.PaymentCheckRequestDto;
import com.zhuyitech.parking.integration.order.dto.result.ParkingOrderPaymentResultDto;
import com.zhuyitech.parking.integration.order.dto.result.PaymentCheckResultDto;
import com.zhuyitech.parking.integration.order.dto.result.PaymentConfirmResultDto;
import com.zhuyitech.parking.integration.trade.TradeIntegrationService;
import com.zhuyitech.parking.ucc.dto.request.account.AccountBalanceAvailableCheckRequestDto;
import com.zhuyitech.parking.ucc.dto.request.assetlog.AssetLogDetailRequestDto;
import com.zhuyitech.parking.ucc.dto.request.assetlog.AssetLogListRequestDto;
import com.zhuyitech.parking.ucc.dto.result.AccountBalanceAvailableCheckResultDto;
import com.zhuyitech.parking.ucc.dto.result.assetlog.AssetLogDetailResultDto;
import com.zhuyitech.parking.ucc.dto.result.assetlog.AssetLogListResultDto;
import com.zhuyitech.parking.ucc.service.api.AccountTransactionService;
import com.zhuyitech.parking.ucc.service.api.UserAssetLogService;
import com.zhuyitech.parking.ucc.trade.PaymentTransactionService;
import com.zhuyitech.parking.ucc.trade.dto.RechargeConfirmRequestDto;
import com.zhuyitech.parking.ucc.trade.dto.RechargeConfirmResultDto;
import com.zhuyitech.parking.ucc.trade.dto.RechargePlaceRequestDto;
import com.zhuyitech.parking.ucc.trade.dto.RechargePlaceResultDto;
import com.zoeeasy.cloud.integration.pay.PayConfigIntegrationService;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayConfigGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.order.ParkingRecordPaymentRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.JsapiConfigGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.WeixinConfigGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.alipay.AlipayConfigResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.weixin.JsapiConfigResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.weixin.WeixinConfigResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 交易API
 *
 * @author walkman
 * @date 2018-01-17
 */
@Api(value = "交易API", description = "交易API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/trade")
public class TradeController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @Autowired
    private TradeIntegrationService tradeIntegrationService;

    @Autowired
    private PayConfigIntegrationService payConfigIntegrationService;

    @Autowired
    private AccountTransactionService accountTransactionService;

    @Autowired
    private UserAssetLogService userAssetLogService;

    /**
     * 获取支付宝配置参数
     */
    @ApiOperation(value = "获取支付宝必要参数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AlipayConfigResultDto.class)
    @RequestMapping(value = "/aliapyconfig", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<AlipayConfigResultDto> getAlipayParams(AlipayConfigGetRequestDto requestDto) {
        return payConfigIntegrationService.getAlipayParams(requestDto);
    }

    /**
     * 获取微信配置参数
     */
    @ApiOperation(value = "获取微信必要参数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WeixinConfigResultDto.class)
    @RequestMapping(value = "/wechatconfig", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<WeixinConfigResultDto> getWeixinParams(WeixinConfigGetRequestDto requestDto) {
        return payConfigIntegrationService.getWeixinParams(requestDto);
    }

    /**
     * 获取微信公众号配置参数
     */
    @ApiOperation(value = "获取微信公众号配置参数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = JsapiConfigResultDto.class)
    @RequestMapping(value = "/jsapiconfig", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<JsapiConfigResultDto> getAlipayParams(JsapiConfigGetRequestDto requestDto) {
        return payConfigIntegrationService.getJsapiParams(requestDto);
    }

    /**
     * @param request
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户充值", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = RechargePlaceResultDto.class)
    @RequestMapping(value = "/recharge", name = "用户充值", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<RechargePlaceResultDto> recharge(HttpServletRequest request, RechargePlaceRequestDto requestDto) {
        requestDto.setSpbillCreateIp(ActionUtils.getIpAddress(request));
        return paymentTransactionService.recharge(requestDto);
    }

    /**
     * @param requestDto 请求参数
     * @return
     */
    @ApiOperation(value = "充值确认", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = RechargeConfirmResultDto.class)
    @RequestMapping(value = "/rechargeconfirm", name = "充值确认", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<RechargeConfirmResultDto> rechargeConfirm(RechargeConfirmRequestDto requestDto) {
        return paymentTransactionService.rechargeConfirm(requestDto);
    }

    /**
     * 停车账单支付
     *
     * @param requestDto 请求参数
     * @return
     */
    @ApiOperation(value = "停车账单支付", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingOrderPaymentResultDto.class)
    @RequestMapping(value = "/recordpayment", name = "停车账单支付", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<ParkingOrderPaymentResultDto> recordPayment(HttpServletRequest request, ParkingRecordPaymentRequestDto requestDto) {

        ParkingOrderPaymentRequestDto parkingOrderPaymentRequestDto = new ParkingOrderPaymentRequestDto();
        parkingOrderPaymentRequestDto.setOrderNo(requestDto.getRecordNo());
        parkingOrderPaymentRequestDto.setPayWay(requestDto.getPayWay());
        parkingOrderPaymentRequestDto.setPaymentAmount(requestDto.getPaymentAmount());
        parkingOrderPaymentRequestDto.setSpbillCreateIp(ActionUtils.getIpAddress(request));
        parkingOrderPaymentRequestDto.setOpenId(requestDto.getOpenId());
        return tradeIntegrationService.payParkingOrder(parkingOrderPaymentRequestDto);
    }

    /**
     * 停车账单支付确认
     *
     * @param requestDto 请求参数
     * @return
     */
    @ApiOperation(value = "停车账单支付确认", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PaymentConfirmResultDto.class)
    @RequestMapping(value = "/recordpayconfirm", name = "停车账单支付确认", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<PaymentConfirmResultDto> recordPaymentConfirm(ParkingOrderPaymentConfirmRequestDto requestDto) {
        return tradeIntegrationService.confirmParkingOrderPayment(requestDto);
    }

    /**
     * 判断余额是否够支付
     */
    @ApiOperation(value = "判断余额是否够支付", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AccountBalanceAvailableCheckResultDto.class)
    @RequestMapping(value = "/judgeBalance", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AccountBalanceAvailableCheckResultDto> payForBalance(AccountBalanceAvailableCheckRequestDto requestDto) {
        return accountTransactionService.checkAccountAvailable(requestDto);
    }

    /**
     * 停车账单支付
     *
     * @param requestDto 请求参数
     * @return
     */
    @ApiOperation(value = "停车账单支付", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingOrderPaymentResultDto.class)
    @RequestMapping(value = "/orderpayment", name = "停车账单支付", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<ParkingOrderPaymentResultDto> payParkingOrder(HttpServletRequest request, ParkingOrderPaymentRequestDto requestDto) {

        requestDto.setSpbillCreateIp(ActionUtils.getIpAddress(request));
        return tradeIntegrationService.payParkingOrder(requestDto);
    }

    /**
     * 停车账单支付确认
     *
     * @param requestDto 请求参数
     * @return
     */
    @ApiOperation(value = "停车账单支付确认", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PaymentConfirmResultDto.class)
    @RequestMapping(value = "/orderpayconfirm", name = "停车账单支付确认", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<PaymentConfirmResultDto> confirmParkingOrderPayment(ParkingOrderPaymentConfirmRequestDto requestDto) {
        return tradeIntegrationService.confirmParkingOrderPayment(requestDto);
    }

    /**
     * 拉取用户收支明细
     */
    @ApiOperation(value = "拉取用户收支明细", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AssetLogListResultDto.class)
    @RequestMapping(value = "/asset/listpage", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public PagedResultDto<AssetLogListResultDto> getList(AssetLogListRequestDto requestDto) {
        return userAssetLogService.getList(requestDto);
    }

    /**
     * 用户收支明细详情
     */
    @ApiOperation(value = "用户收支明细详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AssetLogDetailResultDto.class)
    @RequestMapping(value = "/asset/detail", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AssetLogDetailResultDto> assetLogDetail(AssetLogDetailRequestDto requestDto) {
        return userAssetLogService.assetLogDetail(requestDto);
    }

    /**
     * 停车账单支付判断
     *
     * @param requestDto 请求参数
     * @return
     */
    @ApiOperation(value = "停车账单支付判断", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PaymentCheckResultDto.class)
    @RequestMapping(value = "/payCheck", name = "停车账单支付判断", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<PaymentCheckResultDto> payCheck(PaymentCheckRequestDto requestDto) {
        return tradeIntegrationService.payCheck(requestDto);
    }

}
