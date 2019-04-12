package com.zoeeasy.cloud.open.controller.operation;

import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.open.controller.base.BaseController;
import com.zoeeasy.cloud.pms.park.CloudParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.AddParkingInfoRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.UpdateParkingInfoRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.AddParkingInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.UpdateParkingInfoResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/12/1
 * @author: sc
 */
@RestController
@RequestMapping("/cloud/open")
@Slf4j
@Api(tags = "停车场", value = "停车场Api", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiVersion(1)
@ApiSigned
public class ParkingController extends BaseController {

    @Autowired
    private CloudParkingInfoService cloudParkingInfoService;

    /**
     * 添加停车场
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            AddParkingInfoResultDto.class)
    @PostMapping(value = "/addPark")
    public AddParkingInfoResultDto addParkingInfo(@RequestBody AddParkingInfoRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return cloudParkingInfoService.addParkingInfo(requestDto);
    }

    /**
     * 修改停车场
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            UpdateParkingInfoResultDto.class)
    @PostMapping(value = "/updatePark")
    public UpdateParkingInfoResultDto updateParkingInfo(@RequestBody UpdateParkingInfoRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return cloudParkingInfoService.updateParkingInfo(requestDto);
    }
}
