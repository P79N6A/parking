//package com.zoeeasy.cloud.platform.controller.sms;
//
//import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
//import com.scapegoat.infrastructure.web.base.BaseController;
//import com.zhuyitech.sms.dto.request.ContentQueryPagedResultRequestDto;
//import com.zhuyitech.sms.dto.result.SmsContentResultDto;
//import com.zhuyitech.sms.dto.result.VerifyCodeResultDto;
//import com.zhuyitech.sms.service.api.SmsContentService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@Api(value = "短信发送记录", description = "短信发送记录", produces = MediaType.APPLICATION_JSON_VALUE)
//@RestController
//@RequestMapping(value = "/platform/sms/content", name = "短信发送记录")
//public class ContentController extends BaseController {
//
//    @Autowired
//    private SmsContentService smsContentService;
//
//    /**
//     * 查询短信发送日志
//     *
//     * @param requestDto
//     * @return
//     */
//    @ApiOperation(value = "查询短信发送日志", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = VerifyCodeResultDto.class)
//    @RequestMapping(value = "/query")
//    public PagedResultDto<SmsContentResultDto> querySmsContent(ContentQueryPagedResultRequestDto requestDto) {
//        return smsContentService.querySmsContent(requestDto);
//    }
//}
