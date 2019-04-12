package com.zhuyitech.parking.ucc.service.impl;


import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.WeixinAccessToken;
import com.zhuyitech.parking.ucc.mapper.WeixinAccessTokenMapper;
import com.zhuyitech.parking.ucc.service.WeixinAccessTokenCrudService;
import org.springframework.stereotype.Service;


@Service("weixinAccessTokenCrudService")
public class WeixinAccessTokenCrudServiceImpl extends CrudServiceImpl<WeixinAccessTokenMapper, WeixinAccessToken> implements WeixinAccessTokenCrudService {
}
