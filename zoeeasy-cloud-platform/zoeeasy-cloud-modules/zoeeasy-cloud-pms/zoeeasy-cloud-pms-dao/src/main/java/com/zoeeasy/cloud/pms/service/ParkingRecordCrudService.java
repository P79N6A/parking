package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingRecordEntity;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
public interface ParkingRecordCrudService extends CrudService<ParkingRecordEntity> {

    /**
     * 通过第三方平台停车记录ID查找
     *
     * @param thirdParkingRecordId
     * @return
     */
    ParkingRecordEntity findByHikParkingRecordId(String thirdParkingRecordId);

    /**
     * 通过支付宝平台停车记录ID查找
     *
     * @param aliParkingRecordId
     * @return
     */
    ParkingRecordEntity findByAliParkingRecordId(String aliParkingRecordId);

    /**
     * 通过平台记录流水号查找
     *
     * @param recordNo
     * @return
     */
    ParkingRecordEntity findByRecordNo(String recordNo);

    /**
     * 根据入车记录号查找
     *
     * @param entityWrapper
     * @return
     */
    ParkingRecordEntity selectByIntoRecordNo(Wrapper<ParkingRecordEntity> entityWrapper);

    /**
     * 保存停车记录
     *
     * @param map
     * @return
     */
    Long save(ParkingRecordEntity map);

    /**
     * 更新停车记录
     *
     * @param updateRecordEntity
     * @return
     */
    boolean updateParkingRecordByParkingIdAndId(ParkingRecordEntity updateRecordEntity);

    /**
     * 修改停车记录泊位
     *
     * @param updateRecordEntity
     * @return
     */
    boolean updateParkingLot(ParkingRecordEntity updateRecordEntity);
}
