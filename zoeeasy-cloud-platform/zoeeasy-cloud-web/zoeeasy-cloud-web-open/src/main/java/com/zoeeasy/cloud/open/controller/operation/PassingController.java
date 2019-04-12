package com.zoeeasy.cloud.open.controller.operation;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.zoeeasy.cloud.integration.open.OpenPassRecordService;
import com.zoeeasy.cloud.integration.open.dto.request.CloudAddParkingImageRequest;
import com.zoeeasy.cloud.integration.open.dto.request.CloudAddPassRecordRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkeemSuper
 * @date 2018/12/1 0001
 */
@RestController
@ApiSigned(value = true)
@Api(tags = "过车记录API", value = "过车记录API", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/cloud/open")
public class PassingController {

    @Autowired
    private OpenPassRecordService openPassRecordService;

    /**
     * 添加过车记录
     * 保存图片
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加过车记录(云平台自己保存图片)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addPassRecord")
    public ResultDto clientAddPassRecord(@RequestBody CloudAddPassRecordRequestDto requestDto) {
        return openPassRecordService.clientAddPassRecord(requestDto);
    }



    /**
     * 添加过车记录
     * 只接收第三方图片地址
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加过车记录(接收第三方图片地址)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/simpleAddPassRecord")
    public ResultDto simpleAddPassRecord(@RequestBody CloudAddPassRecordRequestDto requestDto) {
        return openPassRecordService.clientSimpleAddPassRecord(requestDto,requestDto.getFullImage(),requestDto.getPlateImage());
    }







}
