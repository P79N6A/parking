package com.zoeeasy.cloud.platform.controller.appoint;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.order.appoint.AppointmentOrderManagerService;
import com.zoeeasy.cloud.order.appoint.dto.request.CustomerAppointOrderPagedResultRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户预约停车API
 *
 * @author zwq
 */
@Api(value = "用户预约停车API", tags = "用户预约停车API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/customerappoint")
public class CustomerAppointmentOrderController {

    @Autowired
    private AppointmentOrderManagerService parkingAppointmentOrderService;

    /**
     * 分页获取预约订单列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取预约订单列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingAppointmentOrderResultDto.class)
    @PostMapping(value = "/listpage", name = "分页获取预约订单列表")
    public PagedResultDto<ParkingAppointmentOrderResultDto> getOrderPagedList(CustomerAppointOrderPagedResultRequestDto requestDto) {
        return parkingAppointmentOrderService.getCustomerAppointOrderPagedList(requestDto);
    }
}
