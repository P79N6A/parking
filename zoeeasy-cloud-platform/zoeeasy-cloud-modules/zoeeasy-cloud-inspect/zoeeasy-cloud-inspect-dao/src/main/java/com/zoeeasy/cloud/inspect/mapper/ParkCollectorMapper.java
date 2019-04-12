package com.zoeeasy.cloud.inspect.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.inspect.domain.ParkCollectorEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@Repository("parkCollectorMapper")
public interface ParkCollectorMapper extends BaseMapper<ParkCollectorEntity> {
    @SqlParser(filter = true)
    List<ParkCollectorEntity> findByParking(@Param("parkingId") Long parkingId, @Param("tenantId") Long tenantId);
}
