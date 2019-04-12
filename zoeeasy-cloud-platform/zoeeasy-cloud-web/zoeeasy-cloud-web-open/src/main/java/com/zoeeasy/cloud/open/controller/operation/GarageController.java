package com.zoeeasy.cloud.open.controller.operation;

import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.open.controller.base.BaseController;
import com.zoeeasy.cloud.pms.garage.GarageService;
import com.zoeeasy.cloud.pms.garage.dto.request.AddGarageInfoRequestDto;
import com.zoeeasy.cloud.pms.garage.dto.request.UpdateGarageInfoRequestDto;
import com.zoeeasy.cloud.pms.garage.dto.result.AddGarageInfoResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.UpdateGarageInfoResultDto;
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
@Api(value = "车库API", tags = "车库PI", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/open")
public class GarageController extends BaseController {

    @Autowired
    private GarageService garageService;

    /**
     * 添加车库
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加车库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            AddGarageInfoResultDto.class)
    @PostMapping(value = "/addGarage")
    public AddGarageInfoResultDto addGarageInfo(@RequestBody AddGarageInfoRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return garageService.addGarageInfo(requestDto);
    }

    /**
     * 修改车库
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改车库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            UpdateGarageInfoResultDto.class)
    @PostMapping(value = "/updateGarage")
    public UpdateGarageInfoResultDto updateGarageInfo(@RequestBody UpdateGarageInfoRequestDto requestDto) {
        requestDto.setTenantId(checkAccessKey(requestDto));
        return garageService.updateGarageInfo(requestDto);
    }
}
