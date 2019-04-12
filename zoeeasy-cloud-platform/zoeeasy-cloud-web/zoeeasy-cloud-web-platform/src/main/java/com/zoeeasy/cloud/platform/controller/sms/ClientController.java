//package com.zoeeasy.cloud.platform.controller.sms;
//
//import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
//import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
//import com.scapegoat.infrastructure.core.dto.result.ResultDto;
//import com.scapegoat.infrastructure.web.base.BaseController;
//import com.zhuyitech.sms.dto.request.*;
//import com.zhuyitech.sms.dto.result.SmsClientResultDto;
//import com.zhuyitech.sms.service.api.SmsClientService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 短信客户端
// */
//@Api(value = "短信客户端", description = "短信客户端", produces = MediaType.APPLICATION_JSON_VALUE)
//@RestController
//@RequestMapping(value = "/platform/sms/client", name = "短信客户端")
//public class ClientController extends BaseController {
//
//    @Autowired
//    private SmsClientService smsClientService;
//
//    @ApiOperation(value = "添加短信客户端", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @RequestMapping(value = "/add", name = "添加短信客户端")
//    public ResultDto addClient(ClientAddRequestDto requestDto) {
//        return smsClientService.addClient(requestDto);
//    }
//
//    @ApiOperation(value = "修改短信客户端", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @RequestMapping(value = "/update", name = "修改短信客户端")
//    public ResultDto updateClient(ClientUpdateRequestDto requestDto) {
//        return smsClientService.updateClient(requestDto);
//    }
//
//    @ApiOperation(value = "删除短信客户端", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @RequestMapping(value = "/delete", name = "删除短信客户端")
//    public ResultDto deleteClient(ClientDeleteRequestDto requestDto) {
//        return smsClientService.deleteClient(requestDto);
//    }
//
//    @ApiOperation(value = "获取短信客户端", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = SmsClientResultDto.class)
//    @RequestMapping(value = "/get", name = "获取短信客户端")
//    public ObjectResultDto<SmsClientResultDto> getClient(ClientGetRequestDto requestDto) {
//        return smsClientService.getClient(requestDto);
//    }
//
//    @ApiOperation(value = "查询短信客户端", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = SmsClientResultDto.class)
//    @RequestMapping(value = "/query", name = "查询短信客户端")
//    public PagedResultDto<SmsClientResultDto> queryClient(ClientQueryPagedResultRequestDto requestDto) {
//        return smsClientService.queryClient(requestDto);
//    }
//}
