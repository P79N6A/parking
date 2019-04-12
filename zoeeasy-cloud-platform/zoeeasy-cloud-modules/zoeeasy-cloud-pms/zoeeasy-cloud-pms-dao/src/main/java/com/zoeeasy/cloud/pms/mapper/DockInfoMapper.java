package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.DockInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 */
@Repository("dockInfoMapper")
public interface DockInfoMapper extends BaseMapper<DockInfoEntity> {

    @SqlParser(filter = true)
    boolean save(DockInfoEntity dockInfoEntity);

    @SqlParser(filter = true)
    DockInfoEntity findById(@Param("id") Long id);

    @SqlParser(filter = true)
    DockInfoEntity findByCloudCode(@Param("cloudCode") String cloudCode, @Param("tenantId") Long tenantId);

    @SqlParser(filter = true)
    List<DockInfoEntity> findOne(@Param("ew") Wrapper<DockInfoEntity> entityWrapper);
}