package com.zhuyitech.parking.platform.controller.appointment;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointAmountCalculateRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointmentAroundParkingGetRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointmentParkingDetailRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointAmountCalculateResultDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointmentParkingDetailViewResultDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointmentParkingResultDto;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.platform.PlatformParkingInfoIntegrationService;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderAmountCalculateResultDto;
import com.zoeeasy.cloud.pms.park.dto.request.AppointmentParkingCountRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.AppointmentParkingCountResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 预约API
 *
 * @author zwq
 */
@Api(value = "预约API", description = "预约API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/appointment")
public class AppointmentController {

    @Autowired
    private PlatformParkingInfoIntegrationService platformParkingInfoIntegrationService;

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    /**
     * 可预约停车场数量
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "可预约停车场数量", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointmentParkingCountResultDto.class)
    @RequestMapping(value = "/parkingcount", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<AppointmentParkingCountResultDto> supportAppointmentParkingCount(AppointmentParkingCountRequestDto requestDto) {
        return platformParkingInfoIntegrationService.getAppointmentParkingCount(requestDto);
    }

    /**
     * 分页获取可预约停车场
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取可预约停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointmentParkingResultDto.class)
    @RequestMapping(value = "/parkinglistpage", method = RequestMethod.POST)
    @ApiVersion(2)
    public ListResultDto<AppointmentParkingResultDto> supportAppointmentParkingList(AppointmentAroundParkingGetRequestDto requestDto) {
        return platformParkingInfoIntegrationService.getAppointmentParkingList(requestDto);
    }

    /**
     * 可预约停车场详情
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "可预约停车场详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointmentParkingDetailViewResultDto.class)
    @RequestMapping(value = "/parkinginfo", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<AppointmentParkingDetailViewResultDto> supportAppointmentParkingInfo(AppointmentParkingDetailRequestDto requestDto) {
        return platformParkingInfoIntegrationService.getAppointmentParkingDetail(requestDto);
    }

    /**
     * 计算预约金额
     */
    @ApiOperation(value = "计算预约金额", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderAmountCalculateResultDto.class)
    @RequestMapping(value = "/calculateamount", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<AppointOrderAmountCalculateResultDto> calculateAppointAmount(AppointAmountCalculateRequestDto requestDto) {
        ObjectResultDto<AppointOrderAmountCalculateResultDto> objectResultDto = new ObjectResultDto<>();
        ObjectResultDto<AppointAmountCalculateResultDto> appointObjectResultDto = calculateIntegrationService.calculateAppointAmount(requestDto);
        if (appointObjectResultDto.isSuccess() && null != appointObjectResultDto.getData().getAmount()) {
            String amount = appointObjectResultDto.getData().getAmount().setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
            AppointOrderAmountCalculateResultDto appointOrderAmountCalculateResultDto = new AppointOrderAmountCalculateResultDto();
            appointOrderAmountCalculateResultDto.setAmount(amount);
            objectResultDto.setData(appointOrderAmountCalculateResultDto);
            objectResultDto.success();
        }
        return objectResultDto;
    }

}
