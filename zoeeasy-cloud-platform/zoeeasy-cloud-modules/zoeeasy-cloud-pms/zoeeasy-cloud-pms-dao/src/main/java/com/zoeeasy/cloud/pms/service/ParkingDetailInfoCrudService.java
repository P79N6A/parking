package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingDetailInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkingDetailInfoCrudService extends CrudService<ParkingDetailInfoEntity> {

    ParkingDetailInfoEntity findByParkingId(Long parkingId);

    /**
     * 根据停车场id获取停车场收费信息(无talentId)
     *
     * @param parkingId
     * @return
     */
    ParkingDetailInfoEntity findByParkingIdTenantless(Long parkingId);

    /**
     * 添加ParkingDetailInfo(无租户)
     *
     * @param parkingDetailInfoEntity
     * @return
     */
    boolean insertParkingDetailInfoTenantless(ParkingDetailInfoEntity parkingDetailInfoEntity);

    /**
     * 修改ParkingDetailInfo(无租户)
     *
     * @param parkingDetailInfoEntity
     * @return
     */
    boolean updateParkingDetailInfoTenantless(ParkingDetailInfoEntity parkingDetailInfoEntity);

    /**
     * 删除ParkingDetailInfo(无租户)
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingDetailInfoNonTenant(Long parkingId);

    List<ParkingDetailInfoEntity> findParkingDetailNonTenant(Wrapper<ParkingDetailInfoEntity> ew);

    ParkingDetailInfoEntity selectParkingDetailNonTenant(Long parkingId, Long tenantId);

    /**
     * 获取停车场地址
     *
     * @param parkingId
     * @return
     */
    String selectParkingAddress(Long parkingId);

    /**
     * 分页获取
     *
     * @param page
     * @param wrapper
     * @return
     */
    Page<ParkingDetailInfoEntity> selectListPage(Page<ParkingDetailInfoEntity> page, Wrapper<ParkingDetailInfoEntity> wrapper);

}
