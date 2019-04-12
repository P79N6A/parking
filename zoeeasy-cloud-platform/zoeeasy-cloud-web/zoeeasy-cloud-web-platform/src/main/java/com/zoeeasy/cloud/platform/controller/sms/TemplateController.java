//package com.zoeeasy.cloud.platform.controller.sms;
//
//import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
//import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
//import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
//import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
//import com.scapegoat.infrastructure.core.dto.result.ResultDto;
//import com.scapegoat.infrastructure.web.base.BaseController;
//import com.zhuyitech.sms.dto.request.*;
//import com.zhuyitech.sms.dto.result.SmsTemplateResultDto;
//import com.zhuyitech.sms.service.api.SmsLookupService;
//import com.zhuyitech.sms.service.api.SmsTemplateService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@Api(value = "短信模板", description = "短信模板", produces = MediaType.APPLICATION_JSON_VALUE)
//@RestController
//@RequestMapping(value = "/platform/sms/template", name = "短信模板")
//public class TemplateController extends BaseController {
//
//    @Autowired
//    private SmsTemplateService templateService;
//
//    @Autowired
//    private SmsLookupService smsLookupService;
//
//    @ApiOperation(value = "添加短信模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @RequestMapping(value = "/add")
//    public ResultDto addTemplate(TemplateAddRequestDto requestDto) {
//        return templateService.addTemplate(requestDto);
//    }
//
//    @ApiOperation(value = "修改短信模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @RequestMapping(value = "/update")
//    public ResultDto updateTemplate(TemplateUpdateRequestDto requestDto) {
//        return templateService.updateTemplate(requestDto);
//    }
//
//    @ApiOperation(value = "获取短信模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @RequestMapping(value = "/get")
//    public ObjectResultDto<SmsTemplateResultDto> getTemplate(TemplateGetRequestDto requestDto) {
//        return templateService.getTemplate(requestDto);
//    }
//
//    @ApiOperation(value = "删除短信模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @RequestMapping(value = "/delete")
//    public ResultDto deleteTemplate(TemplateDeleteRequestDto requestDto) {
//        return templateService.deleteTemplate(requestDto);
//    }
//
//    @ApiOperation(value = "查询短信模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @RequestMapping(value = "/query")
//    public PagedResultDto<SmsTemplateResultDto> queryTemplate(TemplateQueryPagedResultRequestDto requestDto) {
//        return templateService.queryTemplate(requestDto);
//    }
//
//    @ApiOperation(value = "获取模板类型选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
//    @RequestMapping(value = "/templateType", name = "获取模板类型选择项")
//    public ListResultDto<ComboboxItemDto> queryClient() {
//        return smsLookupService.getTemplateTypeComboboxList();
//    }
//}
