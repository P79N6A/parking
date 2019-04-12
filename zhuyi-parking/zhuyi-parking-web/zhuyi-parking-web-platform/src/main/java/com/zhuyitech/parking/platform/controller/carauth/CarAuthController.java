package com.zhuyitech.parking.platform.controller.carauth;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.PlateNumberCheckRequestDto;
import com.zhuyitech.parking.tool.dto.result.vehicle.PlateNumberCheckResultDto;
import com.zhuyitech.parking.tool.service.api.VehicleVerifyService;
import com.zhuyitech.parking.ucc.car.request.UserCarAuthApproveRequestDto;
import com.zhuyitech.parking.ucc.car.request.UserCarAuthGetRequestDto;
import com.zhuyitech.parking.ucc.car.request.UserCarAuthQueryPageRequestDto;
import com.zhuyitech.parking.ucc.car.result.UserCarAuthResultDto;
import com.zhuyitech.parking.ucc.car.UserCarAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户车辆认证后台API
 *
 * @author AkeemSuper
 * @Date: 2018/1/13
 */
@Api(value = "用户车辆认证后台API", description = "用户车辆认证后台API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/cars")
public class CarAuthController {

    @Autowired
    private UserCarAuthService userCarAuthService;

    @Autowired
    private VehicleVerifyService vehicleVerifyService;

    /**
     * 分页获取车辆认证申请列表
     */
    @ApiOperation(value = "分页获取车辆认证申请列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarAuthResultDto.class)
    @RequestMapping(value = "/getUserCarAuthPage", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public PagedResultDto<UserCarAuthResultDto> getUserCarAuthPage(UserCarAuthQueryPageRequestDto requestDto) {
        return userCarAuthService.getUserCarAuthPage(requestDto);
    }

    /**
     * 获取车辆认证信息
     */
    @ApiOperation(value = "获取车辆认证信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarAuthResultDto.class)
    @RequestMapping(value = "/getUserCarAuth", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<UserCarAuthResultDto> getUserCarAuth(UserCarAuthGetRequestDto requestDto) {
        return userCarAuthService.getUserCarAuth(requestDto);
    }

    /**
     * 车辆认证申请审核
     */
    @ApiOperation(value = "车辆认证申请审核", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/approveCar", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ResultDto approveCar(UserCarAuthApproveRequestDto requestDto) {
        return userCarAuthService.approveCar(requestDto);
    }

    /**
     * 车牌号第三方认证
     */
    @ApiOperation(value = "车牌号第三方认证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PlateNumberCheckResultDto.class)
    @RequestMapping(value = "/check", name = "车牌号第三方认证", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<PlateNumberCheckResultDto> vehicleVerify(PlateNumberCheckRequestDto requestDto) {
        return vehicleVerifyService.vehicleVerify(requestDto);
    }

    /**
     * 车辆审核意见下拉
     */
    @ApiOperation(value = "车辆审核驳回意见", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/rejectReasons", method = RequestMethod.POST, name = "车辆审核驳回意见")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<ComboboxItemDto> getRejectReason() {
        return userCarAuthService.getRejectReason();
    }
}

