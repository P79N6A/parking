package com.zoeeasy.cloud.sys.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.sys.domain.ParamItemEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 参数数据表(ParamItem)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Repository("paramItemMapper")
public interface ParamItemMapper extends BaseMapper<ParamItemEntity> {

    @SqlParser(filter = true)
    List<ParamItemEntity> findByParamCode(@Param("paramCode") String paramCode);

    /**
     * 通过ID获取参数项
     *
     * @param id
     * @return
     */
    @SqlParser(filter = true)
    ParamItemEntity findById(@Param("id") Long id);

    /**
     * 根据entityWrapper搜索
     *
     * @param entityWrapper
     * @return
     */
    @SqlParser(filter = true)
    ParamItemEntity findByEntityWrapper(@Param("ew") Wrapper<ParamItemEntity> entityWrapper);
}