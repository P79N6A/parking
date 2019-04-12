package com.zhuyitech.parking.platform.controller.navigate;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.integration.platform.PlatformParkingInfoIntegrationService;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingAroundGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingDetailGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingExtendInfoGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.result.ParkingAroundBaseViewResultDto;
import com.zoeeasy.cloud.integration.platform.dto.result.ParkingAroundItemExtendViewResultDto;
import com.zoeeasy.cloud.integration.platform.dto.result.ParkingAroundViewResultDto;
import com.zoeeasy.cloud.integration.platform.dto.result.ParkingDetailViewResultDto;
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
 * 导航API
 *
 * @author walkman
 */
@Api(value = "导航API", description = "导航API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/navigate")
@ApiVersion(2)
public class NavigateController {

    @Autowired
    private PlatformParkingInfoIntegrationService platformParkingInfoIntegrationService;

    /**
     * 获取附近的停车场
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "获取附近的停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingAroundViewResultDto.class)
    @RequestMapping(value = "/around", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<ParkingAroundViewResultDto> getAroundParking(ParkingAroundGetRequestDto requestDto) {
        return platformParkingInfoIntegrationService.getAroundParking(requestDto);
    }

    /**
     * 获取停车场详情
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "获取停车场详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingDetailViewResultDto.class)
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<ParkingDetailViewResultDto> getParkingDetail(ParkingDetailGetRequestDto requestDto) {
        return platformParkingInfoIntegrationService.getParkingDetail(requestDto);
    }

    /**
     * 获取附近的停车场基本信息列表
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "获取附近的停车场基本信息列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingAroundBaseViewResultDto.class)
    @RequestMapping(value = "/parkings", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<ParkingAroundBaseViewResultDto> getAroundParkingBaseViewList(ParkingAroundGetRequestDto requestDto) {
        return platformParkingInfoIntegrationService.getAroundParkingBaseViewList(requestDto);
    }

    /**
     * 获取附近的停车场的扩展信息
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "获取附近的停车场的扩展信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingAroundItemExtendViewResultDto.class)
    @RequestMapping(value = "/extend", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<ParkingAroundItemExtendViewResultDto> getAroundParkingExtendView(ParkingExtendInfoGetRequestDto requestDto) {
        return platformParkingInfoIntegrationService.getAroundParkingExtendView(requestDto);
    }
}
