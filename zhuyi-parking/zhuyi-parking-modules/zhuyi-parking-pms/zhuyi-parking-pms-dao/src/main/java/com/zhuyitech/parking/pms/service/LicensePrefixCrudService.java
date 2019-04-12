package com.zhuyitech.parking.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.pms.domain.LicensePrefix;

import java.util.List;


/**
 * @author zwq
 */
public interface LicensePrefixCrudService extends CrudService<LicensePrefix> {

    /**
     * 通过类型查找
     *
     * @param type
     * @return
     */
    List<LicensePrefix> findByType(int type);

    /**
     * 查找父ID下该类型的记录数
     *
     * @param parentId,type
     * @return
     */
    List<LicensePrefix> findByParentIdAndType(Long parentId, int type);

    /**
     * 查找重复数据
     *
     * @param
     * @return
     */
    List<LicensePrefix> findByLicensePrefix(LicensePrefix licensePrefix);

    /**
     * 查找父ID下的记录数
     *
     * @param parentId
     * @return
     */
    Integer getCountByParentId(Long parentId);

    /**
     * 通过名称查找
     *
     * @param code
     * @return
     */
    LicensePrefix findByName(String code);

}