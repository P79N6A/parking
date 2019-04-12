package com.zhuyitech.sms.serivce.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.sms.domain.SmsContent;
import com.zhuyitech.sms.mapper.SmsContentMapper;
import com.zhuyitech.sms.serivce.SmsContentCrudService;
import org.springframework.stereotype.Service;


@Service("smsContentCrudService")
public class SmsContentCrudServiceImpl extends CrudServiceImpl<SmsContentMapper, SmsContent> implements SmsContentCrudService {


}




