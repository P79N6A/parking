package com.zhuyitech.parking.controller;


import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.inmotion.InmotionService;
import com.zhuyitech.parking.inmotion.dto.InMotionPushDataMockRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "易姆迅地磁数据模拟", value = "易姆迅地磁数据模拟", description = "易姆迅地磁数据模拟",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/fake/mock")
public class InmotionMockController {

    @Autowired
    private InmotionService inmotionService;

    /**
     * 模拟Imotion上报数据
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @ApiOperation(value = "模拟海康平台过车数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/inmotion")
    public ResultDto mockInmontionPass(InMotionPushDataMockRequestDto requestDto) {
        return inmotionService.mockInmontionPass(requestDto);
    }

}
