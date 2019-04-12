package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.ParkingQrcodeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zwq on 2018/12/21.
 */
@Repository("parkingQrcodeMapper")
public interface ParkingQrcodeMapper extends BaseMapper<ParkingQrcodeEntity> {

    /**
     * 根据noncestr查找(无租户)
     *
     * @param ew
     * @return
     */
    @SqlParser(filter = true)
    ParkingQrcodeEntity findParkingQrcode(@Param("ew") Wrapper<ParkingQrcodeEntity> ew);

}
