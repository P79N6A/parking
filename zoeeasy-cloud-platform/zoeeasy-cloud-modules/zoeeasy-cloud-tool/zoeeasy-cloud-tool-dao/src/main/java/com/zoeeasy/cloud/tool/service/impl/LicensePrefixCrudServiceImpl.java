package com.zoeeasy.cloud.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.tool.domain.LicensePrefix;
import com.zoeeasy.cloud.tool.mapper.LicensePrefixMapper;
import com.zoeeasy.cloud.tool.service.LicensePrefixCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwq
 */
@Service("licensePrefixCrudService")
public class LicensePrefixCrudServiceImpl extends ServiceImpl<LicensePrefixMapper, LicensePrefix> implements LicensePrefixCrudService {

    @Override
    public List<LicensePrefix> selectLicensePrefixList(String name) {
        return baseMapper.selectLicensePrefixList(name);
    }
}
