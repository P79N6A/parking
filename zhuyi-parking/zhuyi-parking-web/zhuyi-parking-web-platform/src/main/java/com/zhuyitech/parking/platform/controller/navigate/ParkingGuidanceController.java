package com.zhuyitech.parking.platform.controller.navigate;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.ucc.dto.result.*;
import com.zhuyitech.parking.ucc.service.api.ParkingGuidanceService;
import com.zoeeasy.cloud.pms.park.ParkingVehicleRecordService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingVehicleRecordLotRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingVehicleRecordLotResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.PlateNumberMyCloudResultDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ParkingGuidanceController
 * @Description 车位引导
 * @Author qhxu
 * @Date 2019/3/18 11:48
 * @Version1.0
 **/

@RestController
@RequestMapping("/platform/guidance")
public class ParkingGuidanceController {

    @Autowired
    private ParkingGuidanceService parkingGuidanceService;

    @Autowired
    private ParkingVehicleRecordService parkingVehicleRecordService;

    /**
     * @Description: 获取我的车牌信息
     * @Author: qhxu
     * @CreateDate: 2019/3/23 17:14
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/23 17:14
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @PostMapping(value = "/getMyPlateNumber", name = "获取我的车牌信息")
    @ApiOperation(value = "获取我的车牌信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PlateNumberMyRequestVo.class)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<PlateNumberMyResultDto> getMyPlateNumber(PlateNumberMyRequestVo plateNumberMyRequestVo) {
        return  parkingGuidanceService.getMyPlateNumber(plateNumberMyRequestVo);
    }


    /**
     * @Description: 根据车位车牌查找信息
     * @Author: qhxu
     * @CreateDate: 2019/3/23 17:14
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/23 17:14
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @PostMapping(value = "/getPlateNumber", name = "根据车位车牌查找信息")
    @ApiOperation(value = "根据车位车牌查找信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingGuidanceQueryRequestVo.class)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<PlateNumberMyCloudResultDto> getPlateNumberList(ParkingGuidanceQueryRequestVo requestDto) {
        return parkingGuidanceService.getPlateNumberList(requestDto);
    }

    /**
     * @Description: 定位导航
     * @Author: qhxu
     * @CreateDate: 2019/3/18 11:58
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/18 11:58
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @PostMapping(value = "/getGuidance", name = "定位导航")
    @ApiOperation(value = "定位导航", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingGuidanceRequestVo.class)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<ParkingGuidanceResultDto> getParkingGuidance(ParkingGuidanceRequestVo requestDto) {
        return  parkingGuidanceService.getParkingGuidance(requestDto);
    }


    /**
     * 分页查询停车场在停车辆
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "根据车牌查询在停车辆的泊位id", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingVehicleRecordLotResultDto.class)
    @PostMapping(value = "/listdetail")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<ParkingVehicleRecordLotResultDto> getLotId(ParkingVehicleRecordLotRequestDto requestDto) {
        return parkingVehicleRecordService.getCodeId(requestDto);
    }
}
