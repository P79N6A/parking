package com.zoeeasy.cloud.sys.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.sys.domain.ParamTypeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 参数类型表(ParamType)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Repository("paramTypeMapper")
public interface ParamTypeMapper extends BaseMapper<ParamTypeEntity> {

    /**
     * 获取系统内置参数配置
     *
     * @param parentCode
     * @return
     */
    @SqlParser(filter = true)
    List<ParamTypeEntity> findAllStatic(@Param("parentCode") String parentCode);
}