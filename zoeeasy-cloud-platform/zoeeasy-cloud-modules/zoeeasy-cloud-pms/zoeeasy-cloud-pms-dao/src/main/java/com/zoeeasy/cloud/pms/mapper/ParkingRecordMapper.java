package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.ParkingRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@Repository("parkingRecordMapper")
public interface ParkingRecordMapper extends BaseMapper<ParkingRecordEntity> {

    /**
     * 保存停车记录
     *
     * @param map
     * @return
     */
    @SqlParser(filter = true)
    Long save(ParkingRecordEntity map);

    /**
     * 根据入车记录查找
     *
     * @param entityWrapper
     * @return
     */
    @SqlParser(filter = true)
    ParkingRecordEntity selectByIntoRecordNo(@Param("ew") Wrapper<ParkingRecordEntity> entityWrapper);

    /**
     * 更新停车记录
     *
     * @param updateRecordEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingRecordByParkingIdAndId(ParkingRecordEntity updateRecordEntity);

    /**
     * 根据recordNo查找
     *
     * @param recordNo
     * @return
     */
    @SqlParser(filter = true)
    ParkingRecordEntity selectByRecordNo(String recordNo);

    /**
     * 修改停车记录泊位
     *
     * @param updateRecordEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingLot(ParkingRecordEntity updateRecordEntity);

}
