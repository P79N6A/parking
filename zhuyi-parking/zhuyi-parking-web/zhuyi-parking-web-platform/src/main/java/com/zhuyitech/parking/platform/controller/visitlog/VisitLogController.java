package com.zhuyitech.parking.platform.controller.visitlog;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.common.annotation.SystemLog;
import com.zhuyitech.parking.ucc.dto.request.visitlog.*;
import com.zhuyitech.parking.ucc.dto.result.VisitLogResultDto;
import com.zhuyitech.parking.ucc.service.api.VisitLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 操作日志控制器
 *
 * @author walkman
 */
@Api(value = "登录日志Api", description = "登录日志Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/visitlog")
public class VisitLogController {

    @Autowired
    private VisitLogService visitLogService;

    /**
     * 新增操作日志
     *
     * @param requestDto
     * @return
     */
    @SystemLog("新增操作日志")
    @ApiOperation(value = "新增操作日志", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultDto addVisitLogLog(VisitLogAddRequestDto requestDto) {
        return visitLogService.addVisitLog(requestDto);
    }

    /**
     * 删除操作日志
     *
     * @param requestDto
     * @return
     */
    @SystemLog("删除操作日志")
    @ApiOperation(value = "删除操作日志", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultDto deleteVisitLogLog(VisitLogDeleteRequestDto requestDto) {
        return visitLogService.deleteVisitLog(requestDto);
    }

    /**
     * 更新操作日志
     *
     * @param requestDto
     * @return
     */
    @SystemLog("更新操作日志")
    @ApiOperation(value = "更新操作日志", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultDto updateVisitLogLog(VisitLogUpdateRequestDto requestDto) {
        return visitLogService.updateVisitLog(requestDto);
    }

    /**
     * 获取操作日志
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取操作日志", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ObjectResultDto.class)
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ObjectResultDto<VisitLogResultDto> getVisitLogLog(VisitLogGetRequestDto requestDto) {
        return visitLogService.getVisitLog(requestDto);
    }

    /**
     * 获取操作日志列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取操作日志列表", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ListResultDto<VisitLogResultDto> getVisitLogLogList(VisitLogListGetRequestDto requestDto) {
        return visitLogService.getVisitLogList(requestDto);
    }

    /**
     * 分页获取操作日志列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取操作日志列表", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PagedResultDto.class)
    @RequestMapping(value = "/listpage", method = RequestMethod.POST)
    public PagedResultDto<VisitLogResultDto> getVisitLogLogPagedList(VisitLogQueryPagedResultRequestDto requestDto) {
        return visitLogService.getVisitLogPagedList(requestDto);
    }
}
