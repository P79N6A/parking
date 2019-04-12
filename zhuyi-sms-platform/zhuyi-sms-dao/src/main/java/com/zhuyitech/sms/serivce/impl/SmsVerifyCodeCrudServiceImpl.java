package com.zhuyitech.sms.serivce.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.sms.domain.SmsVerifyCode;
import com.zhuyitech.sms.mapper.SmsVerifyCodeMapper;
import com.zhuyitech.sms.serivce.SmsVerifyCodeCrudService;
import org.springframework.stereotype.Service;


@Service("smsVerifyCodeCrudService")
public class SmsVerifyCodeCrudServiceImpl extends CrudServiceImpl<SmsVerifyCodeMapper, SmsVerifyCode> implements SmsVerifyCodeCrudService {


}
