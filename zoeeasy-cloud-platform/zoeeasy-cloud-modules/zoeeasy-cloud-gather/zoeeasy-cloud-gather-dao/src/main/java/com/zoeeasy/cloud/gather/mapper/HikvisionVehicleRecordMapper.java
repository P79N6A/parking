package com.zoeeasy.cloud.gather.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.gather.domain.HikvisionVehicleRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 */
@Repository("hikvisionVehicleRecordMapper")
public interface HikvisionVehicleRecordMapper extends BaseMapper<HikvisionVehicleRecordEntity> {

    /**
     * 更新海康过车记录装态
     *
     * @param passingUuid
     * @param processStatus
     * @param processRemark
     * @return
     */
    @SqlParser(filter = true)
    Integer updateProcessStatus(@Param("passingUuid") String passingUuid, @Param("processStatus") Integer processStatus, @Param("processRemark") String processRemark);


}

