package com.zhuyitech.parking.platform.controller.order;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.ucc.dto.request.record.UserParkingRecordGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.record.UserParkingRecordListGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.record.UserParkingRecordPagedResultRequestDto;
import com.zhuyitech.parking.ucc.dto.result.record.UserParkingRecordDetailResultDto;
import com.zhuyitech.parking.ucc.dto.result.record.UserParkingRecordViewResultDto;
import com.zhuyitech.parking.ucc.service.api.UserParkingRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户停车记录controller
 * @Date: 2018/1/18 0018
 * @author: AkeemSuper
 */
@Api(value = "用户停车记录API", description = "用户停车记录API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/record")
public class UserParkingRecordController {

    @Autowired
    private UserParkingRecordService userParkingRecordService;

    /**
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户停车记录列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserParkingRecordViewResultDto.class)
    @RequestMapping(value = "/list", name = "获取用户停车记录列表", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<UserParkingRecordViewResultDto> getUserParkingRecordList(UserParkingRecordListGetRequestDto requestDto) {
        return userParkingRecordService.getUserParkingRecordList(requestDto);
    }

    /**
     * 获取用户停车记录列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户停车记录列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserParkingRecordViewResultDto.class)
    @RequestMapping(value = "/listpage", name = "获取用户停车记录列表", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public PagedResultDto<UserParkingRecordViewResultDto> getUserParkingRecordPageList(UserParkingRecordPagedResultRequestDto requestDto) {
        return userParkingRecordService.getUserParkingRecordPageList(requestDto);
    }

    /**
     * 根据停车记录ID获取停车记录详情
     */
    @ApiOperation(value = "根据停车记录ID获取停车记录详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserParkingRecordDetailResultDto.class)
    @RequestMapping(value = "/get", name = "根据停车记录ID获取停车记录详情", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<UserParkingRecordDetailResultDto> getUserParkingRecord(UserParkingRecordGetRequestDto requestDto) {
        return userParkingRecordService.getUserParkingRecordView(requestDto);
    }
}
