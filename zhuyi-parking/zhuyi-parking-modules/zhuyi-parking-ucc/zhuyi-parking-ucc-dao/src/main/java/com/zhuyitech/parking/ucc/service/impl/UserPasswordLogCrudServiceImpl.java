package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserPasswordLog;
import com.zhuyitech.parking.ucc.mapper.UserPasswordLogMapper;
import com.zhuyitech.parking.ucc.service.UserPasswordLogCrudService;
import org.springframework.stereotype.Service;

/**
 * @Date: 2018/1/4 0004
 * @author: AkeemSuper
 */
@Service("userPasswordLogCrudService")
public class UserPasswordLogCrudServiceImpl extends CrudServiceImpl<UserPasswordLogMapper, UserPasswordLog> implements UserPasswordLogCrudService {
}
