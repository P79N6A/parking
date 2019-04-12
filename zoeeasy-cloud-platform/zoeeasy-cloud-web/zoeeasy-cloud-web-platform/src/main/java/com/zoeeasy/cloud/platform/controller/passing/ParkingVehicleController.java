package com.zoeeasy.cloud.platform.controller.passing;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.pms.park.ParkingVehicleRecordService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingVehicleRecordQueryPageRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingVehicleRecordViewResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkeemSuper
 * @date 2018/9/17 0017
 */
@Api(tags = "在停车辆API", value = "在停车辆Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/parkVehicle")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PARKING)
public class ParkingVehicleController {

    @Autowired
    private ParkingVehicleRecordService parkingVehicleRecordService;

    /**
     * 分页查询停车场在停车辆
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询停车场在停车辆", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingVehicleRecordViewResultDto.class)
    @PostMapping(value = "/listPage")
    public PagedResultDto<ParkingVehicleRecordViewResultDto> getParkingVehicleRecordListPage(@RequestBody ParkingVehicleRecordQueryPageRequestDto requestDto) {
        return parkingVehicleRecordService.getParkingVehicleRecordListPage(requestDto);
    }
}

