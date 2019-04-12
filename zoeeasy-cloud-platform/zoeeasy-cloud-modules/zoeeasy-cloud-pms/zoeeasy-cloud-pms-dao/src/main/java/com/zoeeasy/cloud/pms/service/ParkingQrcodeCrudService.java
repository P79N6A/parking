package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingQrcodeEntity;

/**
 * Created by zwq on 2018/12/21.
 */
public interface ParkingQrcodeCrudService extends CrudService<ParkingQrcodeEntity> {


    /**
     * 获取停车场二维码记录
     *
     * @param ew
     * @return
     */
    ParkingQrcodeEntity findParkingQrcode(Wrapper<ParkingQrcodeEntity> ew);
}


