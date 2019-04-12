package com.zoeeasy.cloud.pds.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zoeeasy.cloud.pds.domain.MagneticManagerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Date: 2018/8/23
 * @author: wh
 */
@Repository("magneticManagerMapper")
public interface MagneticManagerMapper extends BaseMapper<MagneticManagerEntity> {

    List<MagneticManagerEntity> selectMagneticManagerPage(Pagination page, @Param("parkingIds") List<Long> parkingIds);

}
