package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.tool.domain.Gas;
import com.zhuyitech.parking.tool.mapper.GasMapper;
import com.zhuyitech.parking.tool.service.GasCrudService;
import org.springframework.stereotype.Service;


/**
 * @author zwq
 * @date 2018-05-02
 */
@Service("gesCrudService")
public class GasCrudServiceImpl extends ServiceImpl<GasMapper, Gas> implements GasCrudService {

}

