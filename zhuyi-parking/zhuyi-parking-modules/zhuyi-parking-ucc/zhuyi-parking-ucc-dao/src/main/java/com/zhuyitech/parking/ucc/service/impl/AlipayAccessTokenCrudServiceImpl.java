package com.zhuyitech.parking.ucc.service.impl;


import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.AlipayAccessToken;
import com.zhuyitech.parking.ucc.mapper.AlipayAccessTokenMapper;
import com.zhuyitech.parking.ucc.service.AlipayAccessTokenCrudService;
import org.springframework.stereotype.Service;


@Service("alipayAccessTokenCrudService")
public class AlipayAccessTokenCrudServiceImpl extends CrudServiceImpl<AlipayAccessTokenMapper, AlipayAccessToken> implements AlipayAccessTokenCrudService {

}
