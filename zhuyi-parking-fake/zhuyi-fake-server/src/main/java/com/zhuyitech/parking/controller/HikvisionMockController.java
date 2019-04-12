package com.zhuyitech.parking.controller;


import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.hikvision.HikvisionMockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.zhuyitech.parking.hikvision.dto.MockHikPassingVehicleRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "海康过车数据模拟", value = "海康过车数据模拟", description = "海康过车数据模拟",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/fake/mock")
public class HikvisionMockController {

    @Autowired
    private HikvisionMockService hikvisionMockService;

    /**
     * 模拟海康平台过车数据
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @ApiOperation(value = "模拟海康平台过车数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/hikvision")
    ResultDto mockHikvisionVehicleRecord(MockHikPassingVehicleRequestDto requestDto) {
        return this.hikvisionMockService.mockHikvisionVehicleRecord(requestDto);
    }
}
