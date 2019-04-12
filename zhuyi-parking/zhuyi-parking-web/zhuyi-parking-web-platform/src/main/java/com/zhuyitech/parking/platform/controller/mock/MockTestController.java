package com.zhuyitech.parking.platform.controller.mock;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.push.PushMessageTestRequestDto;
import com.zhuyitech.parking.tool.service.api.NotificationService;
import com.zhuyitech.parking.tool.service.api.PushNotificationService;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingRecordResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author AkeemSuper
 * @date 2018/11/23 0023
 */

@Api(value = "测试Api", description = "测试Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/mock")
@Slf4j
public class MockTestController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PushNotificationService pushNotificationService;

//    @Autowired
//    private ParkingInfoService parkingInfoService;
//
//    @Autowired
//    private PassingVehicleRecordService passingVehicleRecordService;
//
//    @Autowired
//    private ParkingOrderService parkingOrderService;
//
//    @Autowired
//    private ParkingRecordService parkingRecordService;
//
//    @Autowired
//    private MockService mockService;
//
//    @Autowired
//    private ModelMapper modelMapper;


    /**
     * 消息通知发送测试
     */

    @ApiOperation(value = "消息通知发送测试", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/notificationSendTest", name = "消息通知发送测试", method = RequestMethod.POST)
    public ResultDto notificationSendTest() {
        return notificationService.notificationSendTest();
    }


    /**
     * 消息推送测试
     */

    @ApiOperation(value = "消息推送测试", notes = "消息推送测试", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/pushtest", method = RequestMethod.POST)
    public void testPushMessage(PushMessageTestRequestDto requestDto) {
        pushNotificationService.testPushMessage(requestDto);
    }


/**
 * 分页批量迁移过车数据
 *
 * @return
 */

//    @ApiOperation(value = "分页批量迁移过车数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
//            response = ResultDto.class)
//    @RequestMapping(value = "/passVehicleRecord/cloud/listpage", method = RequestMethod.POST)
//    public ResultDto cloudPassVehicleRecordListPage() {
//
//        PagedResultDto<PassingVehicleRecordResultDto> pagedResultDto = new PagedResultDto<>();
//        PagedResultRequestDto pagedResultRequestDto = new PagedResultRequestDto();
//        pagedResultRequestDto.setPageSize(100);
//        Integer totalPage = 1;
//
//        do {
//            pagedResultRequestDto.setPageNo(totalPage);
//
//            pagedResultDto = passingVehicleRecordService.getCloudPassVehicleRecordListPage(pagedResultRequestDto);
//
//            if (null == pagedResultDto || pagedResultDto.isFailed() || CollectionUtils.isEmpty(pagedResultDto.getItems())) {
//                break;
//            }
//
//            ResultDto dto = mockService.transferOldPassingVehicleRecord(pagedResultDto.getItems());
//
//            totalPage++;
//        }
//        while (totalPage <= Math.ceil(Double.valueOf(pagedResultDto.getTotalCount()) / Double.valueOf(pagedResultDto.getPageSize())));
//        return pagedResultDto.success();
//    }


    /**
     * 数据迁移-分页获取停车记录
     */

    @ApiOperation(value = "停车记录数据迁移", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ResultDto.class)
    @RequestMapping(value = "/parkingRecord/cloud/listpage", method = RequestMethod.POST)
    public ResultDto cloudParkingRecordPageList() {


        PagedResultDto<ParkingRecordResultDto> pagedResultDto = new PagedResultDto<>();
        PagedResultRequestDto pagedResultRequestDto = new PagedResultRequestDto();
        pagedResultRequestDto.setPageSize(100);
        Integer totalPage = 1;

        do {
            pagedResultRequestDto.setPageNo(totalPage);

//            pagedResultDto = parkingRecordService.getParkingRecordPageList(pagedResultRequestDto);
//
//            if (null == pagedResultDto || pagedResultDto.isFailed() || CollectionUtils.isEmpty(pagedResultDto.getItems())) {
//                break;
//            }
//
//            log.info("停车记录:{}", JSON.toJSONString(pagedResultDto), SerializerFeature.PrettyFormat);
//
//            ResultDto dto = mockService.transferOldParkingRecord(pagedResultDto.getItems());

            totalPage++;
        }
        while (totalPage <= Math.ceil(Double.valueOf(pagedResultDto.getTotalCount()) / Double.valueOf(pagedResultDto.getPageSize())));
//        while (totalPage <= 1);
        return pagedResultDto.success();
    }


    /**
     * 停车账单数据迁移
     *
     * @return
     */

    @ApiOperation(value = "停车账单数据迁移", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ResultDto.class)
    @RequestMapping(value = "/parkingOrder/cloud/listpage", method = RequestMethod.POST)
    public ResultDto cloudParkingOrder() {

        PagedResultDto<ParkingOrderResultDto> pagedResultDto = new PagedResultDto<>();
        PagedResultRequestDto pagedResultRequestDto = new PagedResultRequestDto();
        pagedResultRequestDto.setPageSize(100);
        Integer totalPage = 1;

        do {
            pagedResultRequestDto.setPageNo(totalPage);

//            pagedResultDto = parkingOrderService.getCloudParkingOrderPageList(pagedResultRequestDto);
//
//            if (null == pagedResultDto || pagedResultDto.isFailed() || CollectionUtils.isEmpty(pagedResultDto.getItems())) {
//                break;
//            }
//
//            //测试验证
//            ResultDto dto = mockService.transferOldParkingOrder(pagedResultDto.getItems());

            totalPage++;
        }
        while (totalPage <= Math.ceil(Double.valueOf(pagedResultDto.getTotalCount()) / Double.valueOf(pagedResultDto.getPageSize())));
        return pagedResultDto.success();
    }

}

