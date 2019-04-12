package com.zoeeasy.cloud.inspect.controller.index;

import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.inspect.park.dto.request.ParkingByUserRequestDto;
import com.zoeeasy.cloud.inspect.park.dto.result.ParkingByUserResultDto;
import com.zoeeasy.cloud.inspect.utils.ParkingIdCacheUtils;
import com.zoeeasy.cloud.integration.inspect.InspectParkIntegrationService;
import com.zoeeasy.cloud.integration.platform.PlatformParkingInfoIntegrationService;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingAndNotifyRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.result.ParkingAndNotifyResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotByParkingIdResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
@RestController
@RequestMapping("/cloud/inspect")
@Slf4j
@Api(tags = "巡检首页", value = "巡检首页Api", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiVersion(1)
@ApiSigned
public class InspectIndexController {

    @Autowired
    private PlatformParkingInfoIntegrationService platformParkingInfoIntegrationService;

    @Autowired
    private InspectParkIntegrationService inspectParkIntegrationService;

    /**
     * 通过userId获取停车场
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "通过userId获取停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            ParkingLotByParkingIdResultDto.class)
    @PostMapping(value = "/selectParking")
    public ListResultDto<ParkingByUserResultDto> getParkingByUserId(@RequestBody ParkingByUserRequestDto requestDto) {
        return inspectParkIntegrationService.getParkingByUserId(requestDto);
    }

    /**
     * 获取可用车位和消息条数
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取可用车位和消息条数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            ParkingAndNotifyResultDto.class)
    @PostMapping(value = "/parkingInfo")
    public ObjectResultDto<ParkingAndNotifyResultDto> getParkingNotify(@RequestBody ParkingAndNotifyRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        ObjectResultDto<ParkingAndNotifyResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            String userUuid = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUuid();
            String parkingIdKey = ParkingIdCacheUtils.generateVerifyCodeCacheKey(userUuid);
            ParkingIdCacheUtils.setParkingIdToRedis(parkingIdKey, requestDto.getParkingId());
            response.setHeader(ParkingIdCacheUtils.INSPECT_PARKING, parkingIdKey);
            objectResultDto = platformParkingInfoIntegrationService.getParkingNotify(requestDto);
        } catch (Exception e) {
            log.error("获取可用车位和消息条数失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
