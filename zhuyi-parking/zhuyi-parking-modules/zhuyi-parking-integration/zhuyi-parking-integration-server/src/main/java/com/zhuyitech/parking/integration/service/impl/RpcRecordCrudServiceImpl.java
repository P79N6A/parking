package com.zhuyitech.parking.integration.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.integration.domain.RpcRecord;
import com.zhuyitech.parking.integration.mapper.RpcRecordMapper;
import com.zhuyitech.parking.integration.service.RpcRecordCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @date 2017-12-11
 */
@Service("rpcRecordCrudService")
public class RpcRecordCrudServiceImpl extends ServiceImpl<RpcRecordMapper, RpcRecord> implements RpcRecordCrudService {


}

