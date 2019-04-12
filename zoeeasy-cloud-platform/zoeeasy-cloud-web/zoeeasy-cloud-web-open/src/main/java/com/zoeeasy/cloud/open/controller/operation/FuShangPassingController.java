package com.zoeeasy.cloud.open.controller.operation;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.core.enums.PassingVehicleDataSourceEnum;
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
 * cckmit
 */
@RestController
@ApiSigned(value = true)
@Api(tags = "过车记录API", value = "过车记录API", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/cloud/open")
public class FuShangPassingController {

    @Autowired
    private OpenPassRecordService openPassRecordService;

    /**
     * 添加过车记录
     * 只接收第三方图片地址
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加过车记录(接收第三方图片地址)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addFuShangPassRecord")
    public ResultDto simpleAddPassRecord(@RequestBody CloudAddPassRecordRequestDto requestDto) {
        return openPassRecordService.clientSimpleAddPassRecord(requestDto, requestDto.getFullImage(), requestDto.getPlateImage(),
                MessageQueueDefinitions.Topic.PASSING_VEHICLE_FUSHANG, PassingVehicleDataSourceEnum.FUSHANG);
    }


    /**
     * 添加停车图片
     * 接收第三方图片地址
     *
     * @param requestDto
     * @return
     */

    @ApiOperation(value = "添加停车图片(接收第三方图片地址)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addParkingImage")
    public ResultDto addParkingImage(@RequestBody CloudAddParkingImageRequest requestDto) {
        return openPassRecordService.addParkingImage(requestDto);
    }


}
