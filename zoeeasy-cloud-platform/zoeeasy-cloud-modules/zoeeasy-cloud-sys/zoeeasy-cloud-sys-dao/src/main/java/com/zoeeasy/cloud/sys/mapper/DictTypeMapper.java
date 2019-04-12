package com.zoeeasy.cloud.sys.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.sys.domain.DictItemEntity;
import com.zoeeasy.cloud.sys.domain.DictTypeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典类型表(DictType)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Repository("dictTypeMapper")
public interface DictTypeMapper extends BaseMapper<DictTypeEntity> {

    /**
     * 获取系统内置字典配置
     *
     * @param parentCode
     * @return
     */
    @SqlParser(filter = true)
    List<DictTypeEntity> findStatic(@Param("parentCode") String parentCode);

    @SqlParser(filter = true)
    DictTypeEntity findByDictCode(@Param("dictCode") String dictCode);
}