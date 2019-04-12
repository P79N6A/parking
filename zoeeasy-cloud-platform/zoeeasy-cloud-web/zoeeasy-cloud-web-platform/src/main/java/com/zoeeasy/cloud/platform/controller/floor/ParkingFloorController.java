package com.zoeeasy.cloud.platform.controller.floor;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.pms.floor.ParkingFloorService;
import com.zoeeasy.cloud.pms.floor.dto.request.*;
import com.zoeeasy.cloud.pms.floor.dto.result.FloorListResultDto;
import com.zoeeasy.cloud.pms.floor.dto.result.FloorPagedResultDto;
import com.zoeeasy.cloud.pms.floor.dto.result.FloorResultDto;
import com.zoeeasy.cloud.pms.garage.ParkingGarageService;
import com.zoeeasy.cloud.pms.garage.dto.request.*;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageListGetResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageQueryPagedResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 停车场楼层控制器
 * Created by song on 2018/9/20.
 */
@Api(value = "停车场楼层Api", tags = "停车场楼层Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/floor")
@ApiVersion(1)
@ApiSigned
@Slf4j
public class ParkingFloorController {

    @Autowired
    private ParkingFloorService parkingFloorService;

    @ApiOperation(value = "添加楼层", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    public ResultDto addFloor(@RequestBody FloorAddRequestDto requestDto) {
        return parkingFloorService.addFloor(requestDto);
    }

    @ApiOperation(value = "修改楼层", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    public ResultDto updateFloor(@RequestBody FloorUpdateRequestDto requestDto) {
        return parkingFloorService.updateFloor(requestDto);
    }

    @ApiOperation(value = "删除楼层", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    public ResultDto deleteFloor(@RequestBody FloorGetRequestDto requestDto) {
        return parkingFloorService.deleteFloor(requestDto);
    }

    @ApiOperation(value = "获取楼层", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = FloorResultDto.class)
    @PostMapping(value = "/get")
    public ObjectResultDto<FloorResultDto> getFloor(@RequestBody FloorGetRequestDto requestDto) {
        return parkingFloorService.getFloor(requestDto);
    }

    @ApiOperation(value = "分页获取楼层", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = FloorPagedResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<FloorPagedResultDto> getFloorPagedList(@RequestBody FloorPagedRequestDto requestDto) {
        return parkingFloorService.getFloorPagedList(requestDto);
    }

    @ApiOperation(value = "获取楼层列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = FloorListResultDto.class)
    @PostMapping(value = "/list")
    public ListResultDto<FloorListResultDto> getFloorList(@RequestBody FloorListRequestDto requestDto) {
        return parkingFloorService.getFloorList(requestDto);
    }

}
