package com.zoeeasy.cloud.open.controller.operation;

import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.open.controller.base.BaseController;
import com.zoeeasy.cloud.pms.dock.DockInfoService;
import com.zoeeasy.cloud.pms.dock.dto.request.RegisterParkingRequestDto;
import com.zoeeasy.cloud.pms.garage.dto.result.CloudResultDto;
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
 * @date 2018/12/4 0004
 */

@RestController
@ApiVersion(1)
@ApiSigned
@Api(tags = "客户端系统注册API", value = "客户端系统注册API", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/cloud/open")
public class RegisterController extends BaseController {

    @Autowired
    private DockInfoService dockInfoService;

    /**
     * 客户端系统注册
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "客户端系统注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CloudResultDto.class)
    @PostMapping(value = "/register")
    public CloudResultDto registerParkingSystem(@RequestBody RegisterParkingRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return dockInfoService.registerParkingSystem(requestDto);
    }

}
