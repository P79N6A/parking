package com.zoeeasy.cloud.open.controller.operation;

import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.open.controller.base.BaseController;
import com.zoeeasy.cloud.pms.gate.GateInfoService;
import com.zoeeasy.cloud.pms.gate.dto.request.AddGateInfoRequestDto;
import com.zoeeasy.cloud.pms.gate.dto.request.UpdateGateInfoRequestDto;
import com.zoeeasy.cloud.pms.gate.dto.result.AddGateInfoResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.UpdateGateInfoResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/12/1
 * @author: lhj
 */
@Api(value = "出入口API", tags = "出入口PI", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/open")
public class GateInfoController extends BaseController {

    @Autowired
    private GateInfoService gateInfoService;

    /**
     * 添加出入口
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加出入口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            AddGateInfoResultDto.class)
    @PostMapping(value = "/addGate")
    public AddGateInfoResultDto addGateInfo(@RequestBody AddGateInfoRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return gateInfoService.addGateInfo(requestDto);
    }

    /**
     * 修改出入口
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改出入口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            UpdateGateInfoResultDto.class)
    @PostMapping(value = "/updateGate")
    public UpdateGateInfoResultDto updateGateInfo(@RequestBody UpdateGateInfoRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return gateInfoService.updateGateInfo(requestDto);
    }
}
