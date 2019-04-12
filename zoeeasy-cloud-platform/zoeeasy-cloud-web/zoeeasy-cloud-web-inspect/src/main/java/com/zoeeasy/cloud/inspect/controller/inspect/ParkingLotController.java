package com.zoeeasy.cloud.inspect.controller.inspect;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingInfoByParkingLotCodeQueryPageRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingLotByParkingIdListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoByParkingLotCodeQueryPageResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotByParkingIdResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingGetLotAvailableRequestDto;
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
 * 停车场API
 *
 * @Date: 2018/11/16
 * @author: lhj
 */
@Api(tags = "停车场API", value = "停车场API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/inspect")
public class ParkingLotController {

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    /**
     * 分页查询根据泊位编号查询停车信息
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询根据泊位编号查询停车信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            ParkingInfoByParkingLotCodeQueryPageResultDto.class)
    @PostMapping(value = "/parkLotListPage")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inspect_parking_id", required = true, paramType = "header",
                            dataType = "string", value = "parkingId header", defaultValue = "inspect_parking_id:")
            }
    )
    public PagedResultDto<ParkingInfoByParkingLotCodeQueryPageResultDto> getParkingInfoByParkingLotCode(@RequestBody ParkingInfoByParkingLotCodeQueryPageRequestDto requestDto) {
        return platformParkingInfoService.getParkingInfoByParkingLotCode(requestDto);
    }

    /**
     * 根据停车场Id获取泊位列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "根据停车场Id获取泊位列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            ParkingLotByParkingIdResultDto.class)
    @PostMapping(value = "/parkLotList")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inspect_parking_id", required = true, paramType = "header",
                            dataType = "string", value = "parkingId header", defaultValue = "inspect_parking_id:")
            }
    )
    public ListResultDto<ParkingLotByParkingIdResultDto> getParkingLotByByParkingIdList(@RequestBody ParkingLotByParkingIdListGetRequestDto requestDto) {
        return platformParkingInfoService.getParkingLotByByParkingIdList(requestDto);
    }

    /**
     * 修改可用车位数
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改可用车位数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            ParkingLotByParkingIdResultDto.class)
    @PostMapping(value = "/updateParkLotCount")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inspect_parking_id", required = true, paramType = "header",
                            dataType = "string", value = "parkingId header", defaultValue = "inspect_parking_id:")
            }
    )
    public ResultDto updateParkingLotAvailable(@RequestBody ParkingGetLotAvailableRequestDto requestDto) {
        return platformParkingInfoService.updateParkingLotAvailable(requestDto);
    }

}
