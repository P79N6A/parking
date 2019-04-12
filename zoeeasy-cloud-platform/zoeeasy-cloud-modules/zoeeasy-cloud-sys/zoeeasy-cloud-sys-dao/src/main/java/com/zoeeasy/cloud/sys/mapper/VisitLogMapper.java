package com.zoeeasy.cloud.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.sys.domain.VisitLogEntity;
import org.springframework.stereotype.Repository;

/**
 * 用户登录日志表(VisitLog)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Repository("visitLogMapper")
public interface VisitLogMapper extends BaseMapper<VisitLogEntity> {
}