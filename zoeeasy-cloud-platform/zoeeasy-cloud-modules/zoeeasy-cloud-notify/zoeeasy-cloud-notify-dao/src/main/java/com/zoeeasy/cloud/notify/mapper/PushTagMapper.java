package com.zoeeasy.cloud.notify.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.notify.domain.PushTag;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 */
@Repository
public interface PushTagMapper extends BaseMapper<PushTag> {

    Long save(PushTag pushTag);
}