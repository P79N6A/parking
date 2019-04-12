package com.zhuyitech.sms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.sms.domain.SmsTemplate;
import com.zhuyitech.sms.dto.request.*;
import com.zhuyitech.sms.dto.result.SmsTemplateResultDto;
import com.zhuyitech.sms.serivce.SmsTemplateCrudService;
import com.zhuyitech.sms.service.api.SmsTemplateService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 */
@Service("smsTemplateService")
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Autowired
    private SmsTemplateCrudService smsTemplateCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResultDto addTemplate(TemplateAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SmsTemplate smsTemplate = modelMapper.map(requestDto, SmsTemplate.class);
            if (smsTemplate != null) {
                smsTemplateCrudService.insert(smsTemplate);
            }
            return resultDto.success();
        } catch (Exception e) {
            return resultDto.failed(e.getMessage());
        }
    }

    @Override
    public ObjectResultDto<SmsTemplateResultDto> getTemplate(TemplateGetRequestDto requestDto) {
        ObjectResultDto objectResultDto = new ObjectResultDto();
        try {

            SmsTemplate smsTemplate = smsTemplateCrudService.selectById(requestDto.getId());
            if (smsTemplate != null) {
                SmsTemplateResultDto templateResultDto = modelMapper.map(smsTemplate, SmsTemplateResultDto.class);
                objectResultDto.setData(templateResultDto);
            }
            return objectResultDto.success();
        } catch (Exception e) {
            return objectResultDto.failed(e.getMessage());
        }
    }

    @Override
    public ResultDto updateTemplate(TemplateUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            SmsTemplate smsTemplate = modelMapper.map(requestDto, SmsTemplate.class);
            if (smsTemplate != null) {
                EntityWrapper<SmsTemplate> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("Id", requestDto.getId());
                smsTemplateCrudService.update(smsTemplate, entityWrapper);
            }
            return resultDto.success();
        } catch (Exception e) {
            return resultDto.failed(e.getMessage());
        }
    }

    @Override
    public ResultDto deleteTemplate(TemplateDeleteRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {

            EntityWrapper<SmsTemplate> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("Id", requestDto.getId());
            smsTemplateCrudService.delete(entityWrapper);
            return resultDto.success();
        } catch (Exception e) {
            return resultDto.failed(e.getMessage());
        }
    }

    @Override
    public PagedResultDto<SmsTemplateResultDto> queryTemplate(TemplateQueryPagedResultRequestDto dto) {
        PagedResultDto<SmsTemplateResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<SmsTemplate> entityWrapper = new EntityWrapper<>();

            if (StringUtils.isNotEmpty(dto.getClientId())) {
                entityWrapper.eq("clientId", dto.getClientId());
            }
            if (StringUtils.isNotEmpty(dto.getTemplateId())) {
                entityWrapper.eq("templateId", dto.getTemplateId());
            }
            if (StringUtils.isNotEmpty(dto.getType())) {
                entityWrapper.eq("type", dto.getType());
            }
            if (dto.getStatus() != null) {
                entityWrapper.eq("status", dto.getStatus());
            }
            Page<SmsTemplate> templatePage = new Page<>(dto.getPageNo(), dto.getPageSize());
            Page<SmsTemplate> templateList = smsTemplateCrudService.selectPage(templatePage, entityWrapper);

            if (templateList != null) {

                List<SmsTemplateResultDto> regionDtoList = modelMapper.map(templateList.getRecords(), new TypeToken<List<SmsTemplateResultDto>>() {
                }.getType());

                pagedResultDto.setPageNo(templatePage.getCurrent());
                pagedResultDto.setPageSize(templatePage.getSize());
                pagedResultDto.setTotalCount(templatePage.getTotal());
                pagedResultDto.setItems(regionDtoList);
            }
            return pagedResultDto.success();
        } catch (Exception e) {
            return pagedResultDto.failed(e.getMessage());
        }
    }
}
