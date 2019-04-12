package com.zhuyitech.sms.service.impl;


import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.sms.domain.SmsClient;
import com.zhuyitech.sms.serivce.SmsClientCrudService;
import com.zhuyitech.sms.service.api.SmsLookupService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 短信公共服务类
 *
 * @author walkman
 */
@Service("smsLookupService")
@Slf4j
public class SmsLookupServiceImpl implements SmsLookupService {

    @Autowired
    private SmsClientCrudService smsClientCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取所有短信客户端选择项
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getClientComboboxList() {

        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<SmsClient> entityWrapper = new EntityWrapper<>();
            List<SmsClient> smsClientList = smsClientCrudService.selectList(entityWrapper);
            if (smsClientList != null && !smsClientList.isEmpty()) {

                List<ComboboxItemDto> comboboxItemDtoList = modelMapper.map(smsClientList, new TypeToken<List<ComboboxItemDto>>() {
                }.getType());
                listResultDto.setItems(comboboxItemDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取客户端列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取模板类型选择项
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getTemplateTypeComboboxList() {

        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("1", "验证码", false));
            itemDtoList.add(new ComboboxItemDto("2", "通知", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取模板类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取验证码类型选择项
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getVerifyCodeTypeComboboxList() {

        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("1", "注册", false));
            itemDtoList.add(new ComboboxItemDto("2", "登录", false));
            itemDtoList.add(new ComboboxItemDto("3", "找回密码", false));
            itemDtoList.add(new ComboboxItemDto("4", "密码设置", false));
            itemDtoList.add(new ComboboxItemDto("5", "支付密码", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取验证码类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }
}
