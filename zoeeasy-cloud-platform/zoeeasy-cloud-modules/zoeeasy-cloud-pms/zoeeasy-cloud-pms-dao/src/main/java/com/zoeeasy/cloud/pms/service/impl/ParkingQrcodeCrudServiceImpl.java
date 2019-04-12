package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingQrcodeEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingQrcodeMapper;
import com.zoeeasy.cloud.pms.service.ParkingQrcodeCrudService;
import org.springframework.stereotype.Service;

/**
 * Created by zwq on 2018/12/21.
 */
@Service("parkingQrcodeCrudService")
public class ParkingQrcodeCrudServiceImpl extends ServiceImpl<ParkingQrcodeMapper, ParkingQrcodeEntity> implements ParkingQrcodeCrudService {

    /**
     * 获取停车场二维码记录
     *
     * @param ew
     * @return
     */
    @Override
    public ParkingQrcodeEntity findParkingQrcode(Wrapper<ParkingQrcodeEntity> ew) {
        return baseMapper.findParkingQrcode(ew);
    }
}


