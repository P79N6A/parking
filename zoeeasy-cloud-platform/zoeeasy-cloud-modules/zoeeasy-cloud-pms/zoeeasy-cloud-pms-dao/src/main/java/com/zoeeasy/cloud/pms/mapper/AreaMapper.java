package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.AreaEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by song on 2018/9/14.
 */
@Repository("areaMapper")
public interface AreaMapper extends BaseMapper<AreaEntity> {

    /**
     * 根据code查找(无租户)
     *
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    AreaEntity findAreaByCodeNonTenant(@Param("code") String code);

    /**
     * 根据id查找(无租户)
     *
     * @param id
     * @return
     */
    @SqlParser(filter = true)
    AreaEntity findAreaByIdNonTenant(@Param("id") Long id);

    @SqlParser(filter = true)
    AreaEntity findArea(@Param("ew") Wrapper<AreaEntity> ew);

}
