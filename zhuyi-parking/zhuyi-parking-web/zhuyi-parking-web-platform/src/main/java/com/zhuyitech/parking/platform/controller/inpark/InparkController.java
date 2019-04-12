package com.zhuyitech.parking.platform.controller.inpark;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.request.inpark.InParkOrderUrlGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.inpark.InParkParkingUrlGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.inpark.InParkOrderUrlResultDto;
import com.zhuyitech.parking.tool.dto.result.inpark.InParkParkingUrlResultDto;
import com.zhuyitech.parking.tool.service.api.InParkIntegrationService;
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
 * InParkApi
 *
 * @author walkman
 * @since 2019-01-10
 */
@Api(tags = "InPark", value = "InParkApi", description = "InParkApi", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/inpark")
public class InparkController {

    @Autowired
    private InParkIntegrationService inParkIntegrationService;

    /**
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取附近停车场列表URL", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = InParkParkingUrlResultDto.class)
    @RequestMapping(value = "/parkUrl", name = "获取附近停车场列表URL", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<InParkParkingUrlResultDto> getParkingListUrl(InParkParkingUrlGetRequestDto requestDto) {
        return inParkIntegrationService.getParkingListUrl(requestDto);
    }

    /**
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取账单列表URL", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = InParkOrderUrlResultDto.class)
    @RequestMapping(value = "/orderUrl", name = "获取账单列表URL", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<InParkOrderUrlResultDto> getOrderListUrl(InParkOrderUrlGetRequestDto requestDto) {
        return inParkIntegrationService.getOrderListUrl(requestDto);
    }

}