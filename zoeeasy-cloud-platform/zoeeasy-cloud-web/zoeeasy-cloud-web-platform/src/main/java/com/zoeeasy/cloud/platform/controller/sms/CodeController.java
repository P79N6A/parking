//package com.zoeeasy.cloud.platform.controller.sms;
//
//import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
//import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
//import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
//import com.scapegoat.infrastructure.web.base.BaseController;
//import com.zhuyitech.sms.dto.request.VerifyCodeQueryPagedResultRequestDto;
//import com.zhuyitech.sms.dto.result.VerifyCodeResultDto;
//import com.zhuyitech.sms.service.api.SmsLookupService;
//import com.zhuyitech.sms.service.api.SmsVerifyCodeService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//
///**
// * 短信验证码
// */
//@Api(value = "短信验证码", description = "短信验证码", produces = MediaType.APPLICATION_JSON_VALUE)
//@RestController
//@RequestMapping(value = "/platform/sms/code", name = "验证码查询")
//public class CodeController extends BaseController {
//
//    @Autowired
//    private SmsVerifyCodeService verifyCodeService;
//
//    @Autowired
//    private SmsLookupService smsLookupService;
//
//    /**
//     * 查询短信验证码
//     *
//     * @param param
//     * @return
//     */
//    @ApiOperation(value = "查询短信验证码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = VerifyCodeResultDto.class)
//    @RequestMapping(value = "/query")
//    public PagedResultDto<VerifyCodeResultDto> queryMsgConstant(VerifyCodeQueryPagedResultRequestDto param) {
//        return verifyCodeService.queryVerifyCode(param);
//    }
//
//    /**
//     * 获取验证码类型选择项
//     *
//     * @return
//     */
//    @ApiOperation(value = "获取验证码类型选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
//    @RequestMapping(value = "/verifyCodeType")
//    public ListResultDto<ComboboxItemDto> verifyCodeType() {
//        return smsLookupService.getVerifyCodeTypeComboboxList();
//    }
//}
