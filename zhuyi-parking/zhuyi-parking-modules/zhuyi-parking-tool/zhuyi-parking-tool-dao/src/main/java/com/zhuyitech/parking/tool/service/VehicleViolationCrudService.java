package com.zhuyitech.parking.tool.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.VehicleViolation;

import java.util.List;


/**
 * 车辆违章记录Crud
 *
 * @author walkman
 * @date 2018/4/14
 */
public interface VehicleViolationCrudService extends CrudService<VehicleViolation> {

    /**
     * 通过车辆ID和违章编号查询
     *
     * @param carId 车辆ID
     * @param code  违章唯一编码
     * @return VehicleViolation
     */
    VehicleViolation findByCarIdAndCode(Long carId, String code);

    /**
     * 根据车辆ID
     *
     * @param carId 车辆ID
     * @return
     */
    List<VehicleViolation> findByCarId(Long carId);

}
