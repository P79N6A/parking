package com.zoeeasy.cloud.sys.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.sys.domain.DictItemEntity;
import com.zoeeasy.cloud.sys.domain.ParamItemEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典数据表(DictItem)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Repository("dictItemMapper")
public interface DictItemMapper extends BaseMapper<DictItemEntity> {

    @SqlParser(filter = true)
    List<DictItemEntity> findByDictCode(@Param("dictCode") String dictCode);

    /**
     * 通过ID获取参数项
     *
     * @param id
     * @return
     */
    @SqlParser(filter = true)
    DictItemEntity findById(@Param("id") Long id);

    /**
     * 根据entityWrapper搜索
     *
     * @param entityWrapper
     * @return
     */
    @SqlParser(filter = true)
    DictItemEntity findByEntityWrapper(@Param("ew") Wrapper<DictItemEntity> entityWrapper);
}