package com.zhuyitech.parking.platform.controller.order;


import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.scapegoat.infrastructure.web.utils.ActionUtils;
import com.zhuyitech.parking.integration.appointment.AppointmentIntegrationService;
import com.zhuyitech.parking.integration.appointment.dto.request.*;
import com.zhuyitech.parking.ucc.trade.PaymentTransactionService;
import com.zhuyitech.parking.ucc.trade.dto.AppointOrderPaymentResultDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointRefundAmountRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.*;
import com.zoeeasy.cloud.pay.trade.dto.request.order.AppointOrderPayRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.AppointOrderDetailViewResultDto;
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
 * 用户预约停车控制器
 *
 * @author walkman
 */
@Api(value = "预约停车Api", description = "预约停车Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/appoint")
public class UserAppointmentController {

    @Autowired
    private AppointmentIntegrationService appointmentIntegrationService;

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    /**
     * 预约订单下单
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "预约订单下单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderPlaceResultDto.class)
    @RequestMapping(value = "/place", name = "预约订单下单", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AppointOrderPlaceResultDto> placeOrder(UserAppointOrderPlaceRequestDto requestDto) {
        return appointmentIntegrationService.placeOrder(requestDto);
    }

    /**
     * 预约订单支付
     *
     * @param requestDto 请求参数
     * @return
     */
    @ApiOperation(value = "预约订单支付", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderPaymentResultDto.class)
    @RequestMapping(value = "/payment", name = "预约订单支付", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AppointOrderPaymentResultDto> payOrder(HttpServletRequest request, AppointOrderPayRequestDto requestDto) {
        requestDto.setSpbillCreateIp(ActionUtils.getIpAddress(request));
        return paymentTransactionService.appointPayment(requestDto);
    }

    /**
     * 预约订单确认
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "预约订单确认", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderConfirmResultDto.class)
    @RequestMapping(value = "/confirm", name = "预约订单确认", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AppointOrderConfirmResultDto> confirmOrder(AppointOrderConfirmParkingRequestDto requestDto) {
        return appointmentIntegrationService.confirmOrder(requestDto);
    }

    /**
     * 计算预约退还金额
     */
    @ApiOperation(value = "计算预约退还金额", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointRefundAmountResultDto.class)
    @RequestMapping(value = "/refundamount", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AppointRefundAmountResultDto> calculateAppointAmount(AppointRefundAmountRequestDto requestDto) {
        return appointmentIntegrationService.calculateRefundAmount(requestDto);
    }

    /**
     * 预约订单取消
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "预约订单取消", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderCancelResultDto.class)
    @RequestMapping(value = "/cancel", name = "预约订单取消", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AppointOrderCancelResultDto> cancelOrder(AppointOrderCancelParkingRequestDto requestDto) {
        return appointmentIntegrationService.cancelOrder(requestDto);
    }

    /**
     * 获取预约订单
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取预约订单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderDetailViewResultDto.class)
    @RequestMapping(value = "/get", name = "获取预约订单", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AppointOrderDetailViewResultDto> getOrder(AppointOrderGetParkingRequestDto requestDto) {
        return appointmentIntegrationService.getOrderDetail(requestDto);
    }

    /**
     * 获取预约订单列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取预约订单列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderViewResultDto.class)
    @RequestMapping(value = "/list", name = "获取预约订单列表", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<AppointOrderViewResultDto> getOrderList(UserAppointOrderListGetRequestDto requestDto) {
        return appointmentIntegrationService.getOrderList(requestDto);
    }

    /**
     * 分页获取预约订单列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取预约订单列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderViewResultDto.class)
    @RequestMapping(value = "/listpage", name = "分页获取预约订单列表", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public PagedResultDto<AppointOrderViewResultDto> getOrderPagedList(UserAppointOrderPagedResultRequestDto requestDto) {
        return appointmentIntegrationService.getOrderPagedList(requestDto);
    }

    /**
     * 根据月份预约订单列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "根据月份预约订单列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderViewMonthResultDto.class)
    @RequestMapping(value = "/listpageMonth", name = "根据月份预约订单列表", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public PagedResultDto<AppointOrderViewMonthResultDto> getOrderPagedListByMonth(UserAppointOrderPagedMonthRequestDto requestDto) {
        return appointmentIntegrationService.getOrderPagedListByMonth(requestDto);
    }

    /**
     * 预约账单支付判断
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "预约账单支付判断", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderCheckResultDto.class)
    @RequestMapping(value = "/payCheck", name = "预约账单支付判断", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AppointOrderCheckResultDto> payCheck(AppointOrderPayCheckParkingRequestDto requestDto) {
        return appointmentIntegrationService.payCheck(requestDto);
    }

    /**
     * 预约订单取消判断
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "预约订单取消判断", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderCancelCheckResultDto.class)
    @RequestMapping(value = "/cancelCheck", name = "预约订单取消判断", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<AppointOrderCancelCheckResultDto> cancelCheck(AppointOrderCancelCheckParkingRequestDto requestDto) {
        return appointmentIntegrationService.cancelCheck(requestDto);
    }
}
