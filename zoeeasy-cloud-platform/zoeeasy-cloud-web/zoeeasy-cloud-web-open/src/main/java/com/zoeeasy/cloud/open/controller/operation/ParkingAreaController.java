package com.zoeeasy.cloud.open.controller.operation;

import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.open.controller.base.BaseController;
import com.zoeeasy.cloud.pms.parkingarea.CloudParkingAreaService;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.AddParkingAreaRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.UpdateParkingAreaRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.AddParkingAreaResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.UpdateParkingAreaResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/12/1
 * @author: lhj
 */
@Api(value = "停车区域API", tags = "停车区域API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/open")
public class ParkingAreaController extends BaseController {

    @Autowired
    private CloudParkingAreaService cloudParkingAreaService;

    /**
     * 添加停车区域
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加停车区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            AddParkingAreaResultDto.class)
    @PostMapping(value = "/addParkArea")
    public AddParkingAreaResultDto addParkingArea(@RequestBody AddParkingAreaRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return cloudParkingAreaService.addParkingArea(requestDto);
    }

    /**
     * 修改停车区域
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改停车区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            UpdateParkingAreaResultDto.class)
    @PostMapping(value = "/updateParkArea")
    public UpdateParkingAreaResultDto updateParkingArea(@RequestBody UpdateParkingAreaRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return cloudParkingAreaService.updateParkingArea(requestDto);
    }
}
