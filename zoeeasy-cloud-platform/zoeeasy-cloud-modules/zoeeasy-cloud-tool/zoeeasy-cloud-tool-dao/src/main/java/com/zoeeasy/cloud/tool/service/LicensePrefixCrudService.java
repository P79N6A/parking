package com.zoeeasy.cloud.tool.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.tool.domain.LicensePrefix;

import java.util.List;

/**
 * @author zwq
 */
public interface LicensePrefixCrudService extends CrudService<LicensePrefix> {

    List<LicensePrefix> selectLicensePrefixList(String name);

}