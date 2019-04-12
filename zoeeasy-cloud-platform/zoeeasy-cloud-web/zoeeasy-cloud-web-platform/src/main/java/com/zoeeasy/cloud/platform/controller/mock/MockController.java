package com.zoeeasy.cloud.platform.controller.mock;

import com.scapegoat.infrastructure.common.utils.LocationUtil;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.gather.hikvision.dto.request.HikVehicleRecordSyncRequestDto;
import com.zoeeasy.cloud.integration.appoint.AppointOrderPlatformIntegrationService;
import com.zoeeasy.cloud.integration.inspect.InspectPushService;
import com.zoeeasy.cloud.integration.mock.MockPushPassRecordService;
import com.zoeeasy.cloud.integration.mock.dto.request.PusHikPassRecordRequestDto;
import com.zoeeasy.cloud.integration.mock.dto.request.PushMagneticPassRecordRequestDto;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;
import com.zoeeasy.cloud.integration.platform.PlatformParkingInfoIntegrationService;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingAroundGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.result.ParkingAroundViewResultDto;
import com.zoeeasy.cloud.message.payload.PassingRecordToInspectPayload;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderCancelCheckRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderCancelCheckResultDto;
import com.zoeeasy.cloud.pms.area.dto.request.AreaTreeRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingDetailInfoPageRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingInfoUpdateRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingDetailInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.tool.amap.dto.request.AroundPositionGetRequest;
import com.zoeeasy.cloud.tool.amap.dto.result.AroundPositionResultDto;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "测试", value = "测试Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/mock")
public class MockController {

    @Autowired
    private IdService idService;

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    @Autowired
    private AppointOrderPlatformIntegrationService appointOrderPlatformIntegrationService;

    @Autowired
    private MockPushPassRecordService mockPushPassRecordService;

    @Autowired
    private InspectPushService inspectPushService;

    @Autowired
    private PlatformParkingInfoIntegrationService platformParkingInfoIntegrationService;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @ApiOperation(value = "添加停车设备管理", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/genId")
    public long genId() {
        return idService.genId();
    }

    @ApiOperation(value = "地磁过车测试", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/magnetictest")
    public ResultDto pushMagneticPassRecord(@RequestBody PushMagneticPassRecordRequestDto requestDto) {
        return mockPushPassRecordService.pushMagneticPassRecord(requestDto);
    }

    @ApiOperation(value = "海康过车测试", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/hikTest")
    public ResultDto pushHikPassRecord(@RequestBody PusHikPassRecordRequestDto requestDto) {
        return mockPushPassRecordService.pushHikPassRecord(requestDto);
    }

    @ApiOperation(value = "收费测试", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/calculateAmount")
    public ObjectResultDto<CalculateAmountResultDto> calculateAmount(ParkingAmountCalculateRequestDto requestDto) {
        return calculateIntegrationService.calculateAmount(requestDto);
    }

    @ApiOperation(value = "测试专用", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/testOut")
    public ObjectResultDto<AppointOrderCancelCheckResultDto> calculateAmount(AppointOrderCancelCheckRequestDto requestDto) {
        return appointOrderPlatformIntegrationService.cancelCheck(requestDto);
    }

    @ApiOperation(value = "过车消息保存测试", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/passRecord/test")
    public ResultDto passRecordTest(@RequestBody AreaTreeRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            final PassingRecordToInspectPayload passingRecordToInspectPayLoad = new PassingRecordToInspectPayload();
            passingRecordToInspectPayLoad.setParkingLotCode("lotCode");
            passingRecordToInspectPayLoad.setParkingId(16L);
            passingRecordToInspectPayLoad.setDataSource(0);
            passingRecordToInspectPayLoad.setParkingLotNumber("test");
            passingRecordToInspectPayLoad.setPassTime(new Date());
            passingRecordToInspectPayLoad.setPassingType(PassingTypeEnum.ENTRY.getValue());
            resultDto = inspectPushService.sendNotifyPassRecord(passingRecordToInspectPayLoad);
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
        }
        return resultDto;
    }


    @ApiOperation(value = "过车消息保存测试", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/parking/getaround")
    public ResultDto getAround(@RequestBody ParkingAroundGetRequestDto requestDto) {
        ObjectResultDto<ParkingAroundViewResultDto> resultDto = new ObjectResultDto<>();
        try {
            resultDto = platformParkingInfoIntegrationService.getAroundParking(requestDto);
        } catch (Exception e) {
            resultDto.failed();
        }
        return resultDto;
    }

    @ApiOperation(value = "海康平台过车数据同步", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/hikvision/mockSyncHikVehicleRecord")
    public ResultDto mockSyncHikVehicleRecord(@RequestBody HikVehicleRecordSyncRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            resultDto = mockPushPassRecordService.mockSyncHikVehicleRecord(requestDto);
        } catch (Exception e) {
            resultDto.failed();
        }
        return resultDto;
    }

//    @ApiOperation(value = "拉取停车场数据", httpMethod = "POST",
//            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @PostMapping(value = "/fetchNationalParkingInfo")
//    @ApiSigned
//    public ResultDto fetchNationalParkingInfo(@RequestBody ParkingInfoFetchRequestDto requestDto) {
//        return mockService.fetchNationalParkingInfo(requestDto);
//    }

//    @ApiOperation(value = "添加停车场-迁移数据用", httpMethod = "POST",
//            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @PostMapping(value = "/addNonTenantParking")
//    public ResultDto addNonTenantParking(@RequestBody NonTenantParkingAddRequestDto requestDto) {
//        return parkingInfoService.addNonTenantParking(requestDto);
//    }
//
//    @ApiOperation(value = "关闭超时未支付", httpMethod = "POST",
//            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
//    @PostMapping(value = "/close")
//    public ResultDto closeAppointOrder(@RequestBody AppointOrderCancelRequestDto requestDto) {
//        return appointIntegrationService.cancelOrder(requestDto);
//    }

    /**
     * 数据迁移- 停车场详情
     */
    @ApiOperation(value = "数据迁移- 停车场详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ResultDto.class)
    @RequestMapping(value = "/parkingifo/transfer", method = RequestMethod.POST)
    public ResultDto cloudParkingRecordPageList() {

        ParkingDetailInfoPageRequestDto pageRequestDto = new ParkingDetailInfoPageRequestDto();
        pageRequestDto.setPageSize(100);
        PagedResultDto<ParkingDetailInfoResultDto> pagedResultDto = new PagedResultDto<>();
        Integer totalPage = 1;
        do {
            pageRequestDto.setPageNo(totalPage);
            pagedResultDto = platformParkingInfoService.getParkingDetailInfoList(pageRequestDto);
            if (null == pagedResultDto || pagedResultDto.isFailed() || CollectionUtils.isEmpty(pagedResultDto.getItems())) {
                break;
            }
            for (ParkingDetailInfoResultDto parkingDetailInfo : pagedResultDto.getItems()) {
                ParkingInfoUpdateRequestDto parkingInfoUpdateRequestDto = new ParkingInfoUpdateRequestDto();
                parkingInfoUpdateRequestDto.setId(parkingDetailInfo.getParkingId());
                parkingInfoUpdateRequestDto.setProvinceCode(parkingDetailInfo.getProvinceCode());
                parkingInfoUpdateRequestDto.setCityCode(parkingDetailInfo.getCityCode());
                parkingInfoUpdateRequestDto.setCountyCode(parkingDetailInfo.getCountyCode());
                parkingInfoUpdateRequestDto.setAddress(parkingDetailInfo.getAddress());
                ResultDto dto = platformParkingInfoService.updateParkingInfo(parkingInfoUpdateRequestDto);
            }
            totalPage++;
        }
        while (totalPage <= Math.ceil(Double.valueOf(pagedResultDto.getTotalCount()) / Double.valueOf(pagedResultDto.getPageSize())));
//        while (totalPage <= 1);
        return pagedResultDto.success();
    }

    @ApiOperation(value = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ResultDto.class)
    @RequestMapping(value = "/position", method = RequestMethod.POST)
    public ObjectResultDto<AroundPositionResultDto> getAroundPosition(AroundPositionGetRequest requestDto) {

        ObjectResultDto<AroundPositionResultDto> objectResultDto = new ObjectResultDto<>();
        //获取距离范围
        AroundPositionResultDto resultDto = new AroundPositionResultDto();
        Double[] aroundPosition = LocationUtil.getAroundPosition(requestDto.getLongitude(), requestDto.getLatitude(),
                requestDto.getMaxDistance());
        if (aroundPosition != null) {
            resultDto.setMinLongitude(aroundPosition[0]);
            resultDto.setMaxLongitude(aroundPosition[1]);
            resultDto.setMinLatitude(aroundPosition[2]);
            resultDto.setMaxLatitude(aroundPosition[3]);
            objectResultDto.setData(resultDto);
        }
        return objectResultDto.success();
    }
}
