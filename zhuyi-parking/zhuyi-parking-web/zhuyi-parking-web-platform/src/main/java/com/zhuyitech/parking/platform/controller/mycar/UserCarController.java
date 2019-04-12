package com.zhuyitech.parking.platform.controller.mycar;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.pms.dto.request.car.CarBrandSubViewGetRequestDto;
import com.zhuyitech.parking.pms.dto.request.car.CarBrandViewGetRequestDto;
import com.zhuyitech.parking.pms.dto.result.car.CarBrandSubViewResultDto;
import com.zhuyitech.parking.pms.dto.result.car.CarBrandViewResultDto;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixFirstResultDto;
import com.zhuyitech.parking.pms.service.api.CarBrandService;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixFirstGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixListGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseOrganizationPrefixFirstResultDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseOrganizationPrefixResultDto;
import com.zhuyitech.parking.tool.service.api.LicenseOrganizationService;
import com.zhuyitech.parking.ucc.car.UserCarInfoService;
import com.zhuyitech.parking.ucc.car.request.*;
import com.zhuyitech.parking.ucc.car.result.*;
import com.zhuyitech.parking.ucc.dto.request.violation.UserCarViolationQueryRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户车辆绑定api
 *
 * @author yuzhicheng
 */
@Api(value = "用户车辆绑定api", description = "用户车辆绑定api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/mycar")
public class UserCarController {

    @Autowired
    private CarBrandService carBrandService;

    @Autowired
    private UserCarInfoService userCarInfoService;

    @Autowired
    private LicenseOrganizationService licenseOrganizationService;

    /**
     * 获取车牌前缀列表
     */
    @ApiOperation(value = "获取车牌前缀列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @RequestMapping(value = "/getPrefixList", method = RequestMethod.POST)
    @ApiVersion(2)
    public ListResultDto<LicenseOrganizationPrefixResultDto> getPrefixList(LicenseOrganizationPrefixListGetRequestDto requestDto) {
        return licenseOrganizationService.getPrefixList(requestDto);
    }

    /**
     * 获取前缀首字母
     */
    @ApiOperation(value = "获取前缀首字母", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = LicensePrefixFirstResultDto.class)
    @RequestMapping(value = "/getPrefixLetter", method = RequestMethod.POST)
    @ApiVersion(2)
    public ListResultDto<LicenseOrganizationPrefixFirstResultDto> getPrefixLetter(LicenseOrganizationPrefixFirstGetRequestDto requestDto) {
        return licenseOrganizationService.getPrefixLetter(requestDto);
    }

    /**
     * 获取车品牌
     */
    @ApiOperation(value = "获取车品牌", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CarBrandViewResultDto.class)
    @RequestMapping(value = "/brands", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<CarBrandViewResultDto> getCarBrandViewList(CarBrandViewGetRequestDto requestDto) {
        return carBrandService.getCarBrandViewList(requestDto);
    }

    /**
     * 获取车品牌及其车型
     */
    @ApiOperation(value = "获取品牌车型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CarBrandSubViewResultDto.class)
    @RequestMapping(value = "/types", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<CarBrandSubViewResultDto> getCarBrandSubViewList(CarBrandSubViewGetRequestDto requestDto) {
        return carBrandService.getCarBrandSubViewList(requestDto);
    }

    /**
     * 新增车辆
     */
    @ApiOperation(value = "新增车辆", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto addUserCar(UserCarInfoAddRequestDto requestDto) {
        return userCarInfoService.addUserCar(requestDto);
    }

    /**
     * 获取车辆信息列表
     */
    @ApiOperation(value = "获取车辆信息列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarViewResultDto.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<UserCarViewResultDto> getUserCar(UserPlateNumberListGetRequestDto requestDto) {
        return userCarInfoService.getUserCarList(requestDto);
    }

    /**
     * 获取车辆信息
     */
    @ApiOperation(value = "获取车辆信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarViewResultDto.class)
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserCarViewResultDto> getUserCar(CurrentUserCarGetRequestDto requestDto) {
        return userCarInfoService.getUserCar(requestDto);
    }

    /**
     * 修改车辆信息
     */
    @ApiOperation(value = "修改车辆信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ResultDto updateUserCar(UserCarInfoUpdateRequestDto requestDto) {
        return userCarInfoService.updateUserCar(requestDto);
    }

    /**
     * 获取当前用户所有的车辆信息
     */
    @ApiOperation(value = "获取当前用户所有的车辆信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserPlateNumberResultDto.class)
    @RequestMapping(value = "/plates", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<UserPlateNumberResultDto> getUserCarInfo(UserPlateNumberListGetRequestDto requestDto) {
        return userCarInfoService.getUserPlateNumberList(requestDto);
    }

    /**
     * 获取当前用户所有的车辆信息
     */
    @ApiOperation(value = "获取当前用户所有的车辆信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserPlateNumberResultDto.class)
    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<UserCarSummaryViewResultDto> getUserCarSummaryList(UserCarSummaryViewListGetRequestDto requestDto) {
        return userCarInfoService.getUserCarSummaryList(requestDto);
    }

    /**
     * 查询用户是否有绑定的车辆
     */
    @ApiOperation(value = "查询用户是否有绑定的车辆", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserHasCarResultDto.class)
    @RequestMapping(value = "/userHasCar", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserHasCarResultDto> userHasCar(UserHasCarRequestDto requestDto) {
        return userCarInfoService.userHasCar(requestDto);
    }

    /**
     * 查询用户车辆个数
     *
     * @param requestDto UserHasCarRequestDto
     * @return UserHasCarResultDto
     */
    @ApiOperation(value = "查询用户是否有绑定的车辆", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserHasCarResultDto.class)
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserCarCountResultDto> userCarCount(UserCarCountRequestDto requestDto) {
        return userCarInfoService.userCarCount(requestDto);
    }

    /**
     * 查询用户绑定的车辆是否超过绑定限制
     */
    @ApiOperation(value = "查询用户绑定的车辆是否超过绑定限制", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarExceedLimitResultDto.class)
    @RequestMapping(value = "/exceedLimit", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<UserCarExceedLimitResultDto> userCarExceedLimit(UserCarExceedLimitRequestDto requestDto) {
        return userCarInfoService.userCarExceedLimit(requestDto);
    }

    /**
     * 用户解绑车辆
     */
    @ApiOperation(value = "用户解绑车辆", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarExceedLimitResultDto.class)
    @RequestMapping(value = "/carUnbind", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto userUnbindCar(UserCarInfoUnBindRequestDto requestDto) {
        return userCarInfoService.userUnbindCar(requestDto);
    }

    /**
     * 用户修改默认车辆
     */
    @ApiOperation(value = "用户修改默认车辆", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarExceedLimitResultDto.class)
    @RequestMapping(value = "/setDefault", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ResultDto userUpdateDefaultCar(UserCarUpdateDefaultCarRequestDto requestDto) {
        return userCarInfoService.userUpdateDefaultCar(requestDto);
    }

    /**
     * 查询用户车辆违章记录
     *
     * @param requestDto requestDto
     */
    @ApiOperation(value = "车辆违章查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarViolationQueryResultDto.class)
    @RequestMapping(value = "/violation", name = "车辆违章查询", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserCarViolationQueryResultDto> queryCarViolations(UserCarViolationQueryRequestDto requestDto) {
        return userCarInfoService.queryCarViolations(requestDto);
    }

    /**
     * 车牌颜色
     */
    @ApiOperation(value = "车牌颜色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/userPlateColor", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<ComboboxItemDto> userPlateColor() {
        return userCarInfoService.userPlateColor();
    }

    /**
     * 获取用户名下所有的车牌
     */
    @ApiOperation(value = "获取用户名下所有的车牌", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarPlateNumberResultDto.class)
    @RequestMapping(value = "/userPlateNumberList", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<UserCarPlateNumberResultDto> userPlateColor(UserPlateNumberListGetRequestDto requestDto) {
        return userCarInfoService.userPlateNumberList(requestDto);
    }

    /**
     * 用户车辆是否被解绑
     */
    @ApiOperation(value = "用户车辆是否被解绑", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserCarUnbindResultDto.class)
    @RequestMapping(value = "/carUnbindStatus", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserCarUnbindResultDto> carUnbindStatus(UserCarUnbindRequestDto requestDto) {
        return userCarInfoService.carUnbindStatus(requestDto);
    }

}
