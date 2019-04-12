package com.zoeeasy.cloud.inspect.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.inspect.domain.ParkInspectorEntity;
import org.springframework.stereotype.Repository;

/**
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@Repository("parkInspectorMapper")
public interface ParkInspectorMapper extends BaseMapper<ParkInspectorEntity> {
}
