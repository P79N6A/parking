package com.zoeeasy.cloud.inspect.controller.inspect;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.integration.inspect.InspectRecordIntegrationService;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectErrorRecordRequestDto;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectIntoRecordRequestDto;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectReasonListGetRequestDto;
import com.zoeeasy.cloud.integration.pass.dto.request.PassVehicleRecordAddRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 巡检操作API
 *
 * @author zwq
 */
@Api(tags = "巡检操作API", value = "巡检操作API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/inspect")
public class InspectOperateController {

    @Autowired
    private InspectRecordIntegrationService inspectRecordIntegrationService;

    /**
     * 入车记录巡检
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "入车记录巡检", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/intoPassRecord")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inspect_parking_id", required = true, paramType = "header",
                            dataType = "string", value = "parkingId header", defaultValue = "inspect_parking_id:")
            }
    )
    public ResultDto inspectIntoPassRecord(@RequestBody InspectIntoRecordRequestDto requestDto) {
        return inspectRecordIntegrationService.inspectIntoPassRecord(requestDto);
    }

    /**
     * 误报巡检
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "误报巡检", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/errorRecord")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inspect_parking_id", required = true, paramType = "header",
                            dataType = "string", value = "parkingId header", defaultValue = "inspect_parking_id:")
            }
    )
    public ResultDto inspectErrorRecord(@RequestBody InspectErrorRecordRequestDto requestDto) {
        return inspectRecordIntegrationService.inspectErrorRecord(requestDto);
    }

    /**
     * 人工添加过车记录
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "人工添加过车记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            ResultDto.class)
    @PostMapping(value = "/addPassRecord")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inspect_parking_id", required = true, paramType = "header",
                            dataType = "string", value = "parkingId header", defaultValue = "inspect_parking_id:")
            }
    )
    public ResultDto addArtificialPassVehicleRecord(@RequestBody PassVehicleRecordAddRequestDto requestDto) {
        return inspectRecordIntegrationService.addPassRecord(requestDto);
    }

    /**
     * 获取巡检异常原因列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取巡检异常原因列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/inspectReason")
    public ListResultDto<ComboboxItemDto> getInspectReason(@RequestBody InspectReasonListGetRequestDto requestDto) {
        return inspectRecordIntegrationService.getInspectReason(requestDto);
    }

}
