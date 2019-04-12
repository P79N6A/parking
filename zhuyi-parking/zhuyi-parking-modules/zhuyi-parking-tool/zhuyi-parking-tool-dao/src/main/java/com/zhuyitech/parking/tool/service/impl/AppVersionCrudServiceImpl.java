package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.tool.domain.AppVersion;
import com.zhuyitech.parking.tool.mapper.AppVersionMapper;
import com.zhuyitech.parking.tool.service.AppVersionCrudService;
import org.springframework.stereotype.Service;


/**
 * @author zwq
 * @date 2018-05-02
 */
@Service("appVersionCrudService")
public class AppVersionCrudServiceImpl extends ServiceImpl<AppVersionMapper, AppVersion> implements AppVersionCrudService {

}

