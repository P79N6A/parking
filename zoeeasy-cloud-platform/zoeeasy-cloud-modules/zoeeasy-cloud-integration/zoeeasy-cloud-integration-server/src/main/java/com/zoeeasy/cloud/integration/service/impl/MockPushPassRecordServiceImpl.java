package com.zoeeasy.cloud.integration.service.impl;

import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.PassingVehicleDataSourceEnum;
import com.zoeeasy.cloud.core.utils.UUIDTool;
import com.zoeeasy.cloud.gather.enums.HikvisionSyncTypeEnum;
import com.zoeeasy.cloud.gather.hikvision.HikvisionParkingService;
import com.zoeeasy.cloud.gather.hikvision.dto.request.HikVehicleRecordSyncRequestDto;
import com.zoeeasy.cloud.integration.mock.MockPushPassRecordService;
import com.zoeeasy.cloud.integration.mock.dto.request.PusHikPassRecordRequestDto;
import com.zoeeasy.cloud.integration.mock.dto.request.PushMagneticPassRecordRequestDto;
import com.zoeeasy.cloud.integration.pass.PassingVehicleIntegrationService;
import com.zoeeasy.cloud.message.payload.MagneticPassingPayload;
import com.zoeeasy.cloud.message.payload.PassingVehiclePayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/11/2 0002
 */
@Service("mockPushPassRecordService")
@Slf4j
public class MockPushPassRecordServiceImpl implements MockPushPassRecordService {

    @Autowired
    private PassingVehicleIntegrationService passIntegrationService;

    @Autowired
    private HikvisionParkingService hikvisionParkingService;

    /**
     * 模拟推送海康过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto pushHikPassRecord(PusHikPassRecordRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            PassingVehiclePayload hikPassingVehiclePayload = new PassingVehiclePayload();
            hikPassingVehiclePayload.setBerthCode(requestDto.getHikParkingLotId());
            hikPassingVehiclePayload.setCarType(requestDto.getCarType());
            hikPassingVehiclePayload.setDirect(requestDto.getPassingType());
            hikPassingVehiclePayload.setParkCode(requestDto.getHikParkId());
            hikPassingVehiclePayload.setPassTime(requestDto.getPassTime());
            hikPassingVehiclePayload.setPassingUuid(UUIDTool.getUUID());
            hikPassingVehiclePayload.setThirdPassingId(UUIDTool.getUUID());
            hikPassingVehiclePayload.setPlateColor(requestDto.getPlateColor());
            hikPassingVehiclePayload.setPlateNumber(requestDto.getPlateNumber());
            hikPassingVehiclePayload.setDataSource(PassingVehicleDataSourceEnum.HIKVISION.getValue());
            resultDto = passIntegrationService.processPassingVehicleRecord(hikPassingVehiclePayload);
        } catch (Exception e) {
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 模拟推送地磁过车数据
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto pushMagneticPassRecord(PushMagneticPassRecordRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticPassingPayload magneticMessagePayLoad = new MagneticPassingPayload();
            magneticMessagePayLoad.setParkingId(requestDto.getParkingId());
            magneticMessagePayLoad.setParkingLotId(requestDto.getParkingLotId());
            magneticMessagePayLoad.setPassTime(requestDto.getPassTime());
            magneticMessagePayLoad.setPassingType(requestDto.getPassingType());
            resultDto = passIntegrationService.processMagneticVehicleRecord(magneticMessagePayLoad);
        } catch (Exception e) {
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 模拟同步海康过车数据
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto mockSyncHikVehicleRecord(HikVehicleRecordSyncRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            long daysCount = DateTimeUtils.getDayDiff(requestDto.getEndTime(), requestDto.getStartTime());
            int day = 0;
            if (daysCount > 1) {
                do {

                    Date startDate = DateTimeUtils.addDay(requestDto.getStartTime(), day);
                    Date endDate = DateTimeUtils.addDay(startDate, 1);

                    if (endDate.after(requestDto.getEndTime())) {
                        endDate = requestDto.getEndTime();
                    }
                    HikVehicleRecordSyncRequestDto hikVehicleRecordSyncRequestDto = new HikVehicleRecordSyncRequestDto();
                    hikVehicleRecordSyncRequestDto.setStartTime(endDate);
                    hikVehicleRecordSyncRequestDto.setSyncType(HikvisionSyncTypeEnum.SYNC.getValue());
                    hikvisionParkingService.syncHikvisionVehicleRecord(hikVehicleRecordSyncRequestDto);
                    log.info("海康逐日过车数据同步:开始时间{},结束时间{}", DateUtils.formatDateTime(startDate), DateUtils.formatDate(endDate));
                    day++;
                } while (day <= daysCount);
            } else {
                HikVehicleRecordSyncRequestDto hikVehicleRecordSyncRequestDto = new HikVehicleRecordSyncRequestDto();
                hikVehicleRecordSyncRequestDto.setStartTime(requestDto.getStartTime());
                hikVehicleRecordSyncRequestDto.setEndTime(requestDto.getEndTime());
                hikVehicleRecordSyncRequestDto.setSyncType(HikvisionSyncTypeEnum.SYNC.getValue());
                hikvisionParkingService.syncHikvisionVehicleRecord(hikVehicleRecordSyncRequestDto);
                log.info("海康过车数据同步:开始时间{},结束时间{}", DateUtils.formatDateTime(requestDto.getStartTime()), DateUtils.formatDate(requestDto.getEndTime()));
            }
        } catch (Exception e) {
            resultDto.failed();
        }
        return resultDto;
    }

}
