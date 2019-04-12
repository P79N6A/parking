package com.zoeeasy.cloud.tool.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.tool.domain.RegionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 * @since 2017-11-09
 */
@Repository("regionMapper")
public interface RegionMapper extends BaseMapper<RegionEntity> {

    /**
     * 获取region
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    RegionEntity getRegion(@Param("ew") Wrapper<RegionEntity> wrapper);

}