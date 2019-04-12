package com.zhuyitech.parking.data.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhuyitech.parking.data.domain.PassingVehicleRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Date: 2018/1/11 0011
 * @author: AkeemSuper
 */
@Repository
public interface PassingVehicleRecordMapper extends BaseMapper<PassingVehicleRecord> {

    /**
     * 查询最后一条入场信息
     */
    PassingVehicleRecord findLastEntryRecord(@Param("ew") Wrapper<PassingVehicleRecord> wrapper);

}
