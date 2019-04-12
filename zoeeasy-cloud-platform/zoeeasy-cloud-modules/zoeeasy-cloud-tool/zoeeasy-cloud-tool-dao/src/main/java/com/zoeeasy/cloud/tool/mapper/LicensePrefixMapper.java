package com.zoeeasy.cloud.tool.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.tool.domain.LicensePrefix;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zwq
 * @since 2018-01-02
 */
@Repository("licensePrefixMapper")
public interface LicensePrefixMapper extends BaseMapper<LicensePrefix> {

    @SqlParser(filter = true)
    List<LicensePrefix> selectLicensePrefixList(@Param("name") String name);

}
