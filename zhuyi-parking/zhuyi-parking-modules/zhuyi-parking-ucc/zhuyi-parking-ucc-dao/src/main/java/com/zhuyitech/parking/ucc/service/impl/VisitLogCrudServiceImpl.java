package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.VisitLog;
import com.zhuyitech.parking.ucc.mapper.VisitLogMapper;
import com.zhuyitech.parking.ucc.service.VisitLogCrudService;
import org.springframework.stereotype.Service;


/**
 * 访问日志Crud服务
 *
 * @author walkman
 */
@Service("visitLogCrudService")
public class VisitLogCrudServiceImpl extends CrudServiceImpl<VisitLogMapper, VisitLog> implements VisitLogCrudService {

}