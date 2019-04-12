package com.zhuyitech.parking.controller;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.client.ClientService;
import com.zhuyitech.parking.client.dto.ClientMockRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkeemSuper
 * @date 2018/12/6 0006
 */
@Api(tags = "客户端过车数据模拟", value = "客户端过车数据模拟", description = "客户端过车数据模拟",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/fake/mock")
public class ClientMockController {

    @Autowired
    private ClientService clientService;

    /**
     * 模拟海康平台过车数据
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @ApiOperation(value = "模拟客户端过车数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            ResultDto.class)
    @PostMapping(value = "/client")
    ResultDto mockClientPassRecord(ClientMockRequestDto requestDto) {
        return this.clientService.mockClientPassRecord(requestDto);
    }
}
