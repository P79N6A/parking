package com.zoeeasy.cloud.open.controller.operation;

import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.open.controller.base.BaseController;
import com.zoeeasy.cloud.pms.lane.LaneInfoService;
import com.zoeeasy.cloud.pms.lane.dto.request.AddParkingLaneRequestDto;
import com.zoeeasy.cloud.pms.lane.dto.request.UpdateParkingLaneRequestDto;
import com.zoeeasy.cloud.pms.lane.dto.result.AddParkingLaneResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.UpdateParkingLaneResultDto;
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
@Api(value = "车道API", tags = "车道API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/open")
public class LaneInfoController extends BaseController {

    @Autowired
    private LaneInfoService laneInfoService;

    /**
     * 添加车道
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加车道", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            AddParkingLaneResultDto.class)
    @PostMapping(value = "/addLane")
    public AddParkingLaneResultDto addParkingLane(@RequestBody AddParkingLaneRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return laneInfoService.addParkingLane(requestDto);
    }

    /**
     * 修改车道
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改车道", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            UpdateParkingLaneResultDto.class)
    @PostMapping(value = "/updateLane")
    public UpdateParkingLaneResultDto updateParkingLane(@RequestBody UpdateParkingLaneRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return laneInfoService.updateParkingLane(requestDto);
    }
}
