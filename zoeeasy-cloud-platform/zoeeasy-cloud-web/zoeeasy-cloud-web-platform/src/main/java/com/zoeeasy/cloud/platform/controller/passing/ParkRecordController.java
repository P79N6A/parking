package com.zoeeasy.cloud.platform.controller.passing;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.pms.park.ParkingRecordService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingRecordPagedResultRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingRecordViewResultDto;
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
 * @date 2018/11/21 0021
 */
@Api(tags = "停车记录APi", value = "停车记录APi", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/parkRecord")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_RECORD)
public class ParkRecordController {

    @Autowired
    private ParkingRecordService parkingRecordService;

    /**
     * 分页查询停车记录列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询停车记录列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingRecordViewResultDto.class)
    @PostMapping(value = "/listPage")
    public PagedResultDto<ParkingRecordViewResultDto> getParkingRecordPageList(@RequestBody ParkingRecordPagedResultRequestDto requestDto) {
        return parkingRecordService.getParkingRecordPageList(requestDto);
    }
}


