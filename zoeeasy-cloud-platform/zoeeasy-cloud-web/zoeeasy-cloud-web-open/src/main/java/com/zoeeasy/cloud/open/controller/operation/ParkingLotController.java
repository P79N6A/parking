package com.zoeeasy.cloud.open.controller.operation;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.integration.park.CloudParkingLotService;
import com.zoeeasy.cloud.open.controller.base.BaseController;
import com.zoeeasy.cloud.pms.park.CloudParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.AddParkingLotRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.CloudAddParkingLotRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.UpdateParkingLotRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.AddParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.UpdateParkingLotResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cloud/open")
@Slf4j
@Api(tags = "泊位", value = "泊位Api", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiVersion(1)
@ApiSigned(value = false)
public class ParkingLotController extends BaseController {

    @Autowired
    private CloudParkingLotInfoService parkingLotInfoService;

    @Autowired
    private CloudParkingLotService clientAddParkingLot;

    /**
     * 添加泊位
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加泊位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            AddParkingLotResultDto.class)
    @PostMapping(value = "/addParkLot")
    public AddParkingLotResultDto addParkingLot(@RequestBody AddParkingLotRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return parkingLotInfoService.addParkingLot(requestDto);
    }

    /**
     * 修改泊位
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改泊位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            UpdateParkingLotResultDto.class)
    @PostMapping(value = "/updateParkLot")
    public UpdateParkingLotResultDto updateParkingLot(@RequestBody UpdateParkingLotRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return parkingLotInfoService.updateParkingLot(requestDto);
    }

    /**
     * 添加泊位数据图片
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加泊位数据图片", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            ResultDto.class)
    @PostMapping(value = "/addParkingLotImage")
    public ResultDto clientAddParkingLot(@RequestBody CloudAddParkingLotRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return clientAddParkingLot.clientAddParkingLot(requestDto);
    }

}
