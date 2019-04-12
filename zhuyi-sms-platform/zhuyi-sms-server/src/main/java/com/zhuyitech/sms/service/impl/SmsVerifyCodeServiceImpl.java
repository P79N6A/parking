package com.zhuyitech.sms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.sms.domain.SmsVerifyCode;
import com.zhuyitech.sms.dto.request.VerifyCodeQueryPagedResultRequestDto;
import com.zhuyitech.sms.dto.result.VerifyCodeResultDto;
import com.zhuyitech.sms.serivce.SmsVerifyCodeCrudService;
import com.zhuyitech.sms.service.api.SmsVerifyCodeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 */
@Service("smsVerifyCodeService")
public class SmsVerifyCodeServiceImpl implements SmsVerifyCodeService {

    @Autowired
    private SmsVerifyCodeCrudService smsVerifyCodeCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PagedResultDto<VerifyCodeResultDto> queryVerifyCode(VerifyCodeQueryPagedResultRequestDto requestDto) {

        PagedResultDto<VerifyCodeResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<SmsVerifyCode> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getClientId())) {
                entityWrapper.eq("clientId", requestDto.getClientId());
            }
            if (StringUtils.isNotEmpty(requestDto.getTemplateId())) {
                entityWrapper.eq("templateId", requestDto.getTemplateId());
            }
            if (StringUtils.isNotEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.eq("phoneNumber", requestDto.getPhoneNumber());
            }
            if (requestDto.getVerifyType() != null) {
                entityWrapper.eq("verifyType", requestDto.getVerifyType());
            }
            Page<SmsVerifyCode> verifyCodePage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());

            Page<SmsVerifyCode> verifyCodeList = smsVerifyCodeCrudService.selectPage(verifyCodePage, entityWrapper);

            if (verifyCodeList != null) {

                List<VerifyCodeResultDto> verifyCodeDtoList = modelMapper.map(verifyCodeList.getRecords(), new TypeToken<List<VerifyCodeResultDto>>() {
                }.getType());

                pagedResultDto.setPageNo(verifyCodePage.getCurrent());
                pagedResultDto.setPageSize(verifyCodePage.getSize());
                pagedResultDto.setTotalCount(verifyCodePage.getTotal());
                pagedResultDto.setItems(verifyCodeDtoList);
            }
            return pagedResultDto.success();
        } catch (Exception e) {
            return pagedResultDto.failed(e.getMessage());
        }
    }
}
