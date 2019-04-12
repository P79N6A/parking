package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.PassingVehicleRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@Repository("passingVehicleRecordMapper")
public interface PassingVehicleRecordMapper extends BaseMapper<PassingVehicleRecordEntity> {

    /**
     * 保存过车记录
     *
     * @param recordEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean save(PassingVehicleRecordEntity recordEntity);

    /**
     * 根据过车记录号更新过车记录
     *
     * @param passingVehicleRecordEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateByPassNo(PassingVehicleRecordEntity passingVehicleRecordEntity);

    /**
     * 查询最后一条入场信息
     */
    PassingVehicleRecordEntity findLastEntryRecord(@Param("ew") Wrapper<PassingVehicleRecordEntity> wrapper);

    /**
     * 根据过车记录号查过车记录
     *
     * @param entityWrapper
     * @return
     */
    @SqlParser(filter = true)
    List<PassingVehicleRecordEntity> selectByPassingNo(@Param("ew") Wrapper<PassingVehicleRecordEntity> entityWrapper);

    /**
     * @param entityWrapper
     * @return
     */
    @SqlParser(filter = true)
    PassingVehicleRecordEntity selectPassRecord(@Param("ew") EntityWrapper<PassingVehicleRecordEntity> entityWrapper);
}
