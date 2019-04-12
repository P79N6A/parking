package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.pms.domain.ParkingCurrentInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotStatusEntity;
import com.zoeeasy.cloud.pms.enums.ParkingLotStatusEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.ParkingLotStatusService;
import com.zoeeasy.cloud.pms.park.cst.ColumnConstant;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotDecreaseRequestDto;
import com.zoeeasy.cloud.pms.service.ParkingCurrentInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotStatusCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
@Service("parkingLotStatusService")
@Slf4j
public class ParkingLotStatusServiceImpl implements ParkingLotStatusService {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingCurrentInfoCrudService parkingCurrentInfoCrudService;

    @Autowired
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    /**
     * 变更停车场可用车位数及变更车位状态占用
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto decreaseParkingLotAvailable(ParkingLotDecreaseRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingInfoEntity parkingInfoEntity = this.parkingInfoCrudService.selectById(requestDto.getParkingId());
            if (parkingInfoEntity == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment()
                );
            }
            Wrapper<ParkingCurrentInfoEntity> currentInfoEntityWrapper = new EntityWrapper<>();
            currentInfoEntityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectOne(currentInfoEntityWrapper);
            if (parkingCurrentInfoEntity == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment()
                );
            }
            //递减可用车位数
            if (parkingCurrentInfoEntity.getLotAvailable() >= 1) {
                currentInfoEntityWrapper.eq(ColumnConstant.ID, parkingCurrentInfoEntity.getId());
                ParkingCurrentInfoEntity parkingCurrentInfoUpdate = new ParkingCurrentInfoEntity();
                parkingCurrentInfoUpdate.setId(parkingCurrentInfoEntity.getId());
                parkingCurrentInfoUpdate.setLotAvailable(parkingCurrentInfoEntity.getLotAvailable() - 1);
                parkingCurrentInfoCrudService.update(parkingCurrentInfoUpdate, currentInfoEntityWrapper);
            }
            //如果泊位不空，更新泊位为占用状态
            if (requestDto.getParkingLotId() != null) {
                EntityWrapper<ParkingLotStatusEntity> parkingLotStatusEntityWrapper = new EntityWrapper<>();
                parkingLotStatusEntityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
                parkingLotStatusEntityWrapper.eq(ColumnConstant.PARKING_LOT_ID, requestDto.getParkingLotId());
                ParkingLotStatusEntity lotStatusEntity = parkingLotStatusCrudService.selectOne(parkingLotStatusEntityWrapper);
                if (lotStatusEntity == null) {
                    return resultDto.makeResult(PmsResultEnum.PARKING_LOT_NOT_FOUND.getValue(),
                            PmsResultEnum.PARKING_LOT_NOT_FOUND.getComment()
                    );
                }
                EntityWrapper<ParkingLotStatusEntity> statusEntityWrapper = new EntityWrapper<>();
                statusEntityWrapper.eq(ColumnConstant.ID, lotStatusEntity.getId());
                ParkingLotStatusEntity updateStatus = new ParkingLotStatusEntity();
                updateStatus.setStatus(ParkingLotStatusEnum.USED.getValue());
                updateStatus.setOccupyTime(requestDto.getOccupyTime());
                parkingLotStatusCrudService.update(updateStatus, statusEntityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("变更停车场可用车位数及变更车位状态占用失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
