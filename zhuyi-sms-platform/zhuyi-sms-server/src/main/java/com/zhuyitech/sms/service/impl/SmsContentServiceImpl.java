package com.zhuyitech.sms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.sms.domain.SmsContent;
import com.zhuyitech.sms.domain.SmsVerifyCode;
import com.zhuyitech.sms.dto.request.ContentQueryPagedResultRequestDto;
import com.zhuyitech.sms.dto.result.SmsContentResultDto;
import com.zhuyitech.sms.serivce.SmsContentCrudService;
import com.zhuyitech.sms.service.api.SmsContentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短信内容服务
 *
 * @author walkman
 */
@Service("smsContentService")
public class SmsContentServiceImpl implements SmsContentService {

    @Autowired
    private SmsContentCrudService smsContentCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PagedResultDto<SmsContentResultDto> querySmsContent(ContentQueryPagedResultRequestDto requestDto) {
        PagedResultDto<SmsContentResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<SmsContent> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getClientId())) {
                entityWrapper.eq("clientId", requestDto.getClientId());
            }
            if (StringUtils.isNotEmpty(requestDto.getTemplateId())) {
                entityWrapper.eq("templateId", requestDto.getTemplateId());
            }
            if (StringUtils.isNotEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.eq("phoneNumber", requestDto.getPhoneNumber());
            }

            Page<SmsContent> contentPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<SmsContent> contentList = smsContentCrudService.selectPage(contentPage, entityWrapper);

            if (contentList != null) {

                List<SmsContentResultDto> contentDtoList = modelMapper.map(contentList.getRecords(), new TypeToken<List<SmsContentResultDto>>() {
                }.getType());

                pagedResultDto.setPageNo(contentPage.getCurrent());
                pagedResultDto.setPageSize(contentPage.getSize());
                pagedResultDto.setTotalCount(contentPage.getTotal());
                pagedResultDto.setItems(contentDtoList);
            }
            return pagedResultDto.success();
        } catch (Exception e) {
            return pagedResultDto.failed(e.getMessage());
        }
    }
}
