package com.zhuyitech.parking.hikvision;

import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zhuyitech.parking.hikvision.dto.MockHikPassingVehicleRequestDto;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.core.enums.PassingVehicleDataSourceEnum;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.PassingVehiclePayload;
import com.zhuyitech.parking.utils.CarItem;
import com.zhuyitech.parking.utils.FakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 海康过车数据模拟
 */
@Service("hikvisionMockService")
@Slf4j
public class HikvisionMockServiceImpl implements HikvisionMockService {

    @Autowired
    private MessageSendService messageSendService;

    /**
     * 海康过车数据模拟,每10m自动发送一条
     *
     * @return
     */
    @Override
    @Scheduled(fixedDelay = 20 * 1000)
    public ResultDto mockHikPassingVehicle() {

        ResultDto resultDto = new ResultDto();
        Random random = new Random();
        try {

            //不管是否有无车牌,发送过车队列消息
            RocketMessage<PassingVehiclePayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.PASSING_VEHICLE);
            message.setSender(MessageQueueDefinitions.Sender.GATHER);
            message.setMessageId(StringUtils.getUUID());

            int i = 0;
            while (i < 3) {

                PassingVehiclePayload payload = new PassingVehiclePayload();
                payload.setPassingUuid(StringUtils.getUUID());
                payload.setThirdPassingId(StringUtils.getUUID());
                int parkCodeSuffix = random.nextInt(14) + 1;
                int berthCode = random.nextInt(26) + 1;
                payload.setParkCode(getParkCode());
                payload.setParkName("智停测试停车场" + parkCodeSuffix + "号");
                payload.setGateCode(StringUtils.getUUID());
                payload.setGateName("出入口名称测试" + i + 1 + "号");
                String berthCodePrefix = "HIK_A00";
                if (berthCode < 10) {
                    berthCodePrefix = berthCodePrefix + "0";
                }
                payload.setLaneCode(StringUtils.getUUID());
                payload.setLaneName("车道编号测试" + i + 1 + "号");
                payload.setBerthCode(berthCodePrefix + berthCode);
                Date date = new Date();
                int direct = random.nextInt(2) + 1;
                if (direct == 2) {
                    //如果是出车
                    date = new Date(date.getTime() + (1000 * 60 * 60 * 4));
                }
                payload.setPassTime(new Date());
                CarItem carItem = FakeUtils.createPlateNumber();
                payload.setPlateNumber(carItem.getPlateNumber());
                payload.setCarType(carItem.getCarType());
                payload.setPlateColor(carItem.getPlateColor());
                payload.setDirect(direct);
                payload.setDataSource(PassingVehicleDataSourceEnum.HIKVISION.getValue());
                message.setPayload(payload);
                messageSendService.sendAndSaveSync(message);
                i++;
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("海康模拟数据发送失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 模拟海康平台过车数据
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto mockHikvisionVehicleRecord(MockHikPassingVehicleRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        if (requestDto == null) {
            return resultDto.success();
        }

        Date passTime = DateTimeUtils.parseDate(requestDto.getPassTime(), DateTimeUtils.DATE_FORMAT_DATETIME);

        if (passTime != null) {

            PassingVehiclePayload vehicleRecordsBean = new PassingVehiclePayload();
            vehicleRecordsBean.setPassingUuid(StringUtils.getUUID());
            vehicleRecordsBean.setPlateNumber(requestDto.getPlateNo());
            vehicleRecordsBean.setCarType(requestDto.getCarType());
            vehicleRecordsBean.setDirect(requestDto.getDirect());
            vehicleRecordsBean.setPassTime(passTime);
            vehicleRecordsBean.setParkCode(requestDto.getParkCode());
            vehicleRecordsBean.setParkName(requestDto.getParkName());
            vehicleRecordsBean.setLaneCode(requestDto.getLaneNo());
            vehicleRecordsBean.setLaneName(requestDto.getLaneName());
            vehicleRecordsBean.setGateCode(requestDto.getGateCode());
            vehicleRecordsBean.setGateName(requestDto.getGateName());
            vehicleRecordsBean.setBerthCode(requestDto.getBerthCode());
            vehicleRecordsBean.setPlateColor(requestDto.getPlateColor());
            vehicleRecordsBean.setDataSource(PassingVehicleDataSourceEnum.HIKVISION.getValue());
            //不管是否有无车牌,发送过车队列消息
            RocketMessage<PassingVehiclePayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.PASSING_VEHICLE);
            message.setSender(MessageQueueDefinitions.Sender.GATHER);
            message.setMessageId(StringUtils.getUUID());
            message.setPayload(vehicleRecordsBean);
            this.messageSendService.sendAndSaveSync(message);
        }
        return resultDto.success();
    }

    private String getParkCode() {
        List<String> parkList = new ArrayList<>();
        parkList.add("10118081417092901000");
        parkList.add("10118080720020601000");
        parkList.add("10118041617121501000");
        parkList.add("10118060122091501000");
        parkList.add("10118080720034201000");
        parkList.add("10118080720034201111");
        parkList.add("10118041617121501000");
        parkList.add("10118060122091501000");
        parkList.add("10118060122091500251");
        parkList.add("10118060122091501503");
        parkList.add("10118060122091501502");
        parkList.add("10118060122091501504");
        parkList.add("10118060122091501506");
        parkList.add("10118060122091501505");
        parkList.add("10118060122091501508");
        parkList.add("10118060122091501507");
        parkList.add("10118060122091501509");
        parkList.add("10118060122091501510");
        parkList.add("10118060122091501511");
        parkList.add("10118060122091501512");
        parkList.add("10118060122091501513");
        parkList.add("10118060122091501514");
        parkList.add("10118060122091501515");
        parkList.add("10118060122091501516");
        parkList.add("10118060122091501517");
        parkList.add("10118060122091501518");
        parkList.add("10118060122091501519");
        parkList.add("10118060122091501520");
        parkList.add("10118060122091501521");
        parkList.add("10118060122091501522");
        parkList.add("10118060122091501523");
        parkList.add("10118060122091501524");
        parkList.add("10118060122091501525");
        parkList.add("10118060122091501526");
        parkList.add("10118060122091501527");
        parkList.add("10118060122091501528");
        parkList.add("10118060122091501529");
        parkList.add("10118060122091501530");
        parkList.add("10118060122091501531");
        parkList.add("10118060122091501532");
        parkList.add("10118060122091501533");
        parkList.add("10118060122091501534");
        parkList.add("10118060122091501535");
        parkList.add("10118060122091501536");
        parkList.add("10118060122091501537");
        parkList.add("10118060122091501538");
        parkList.add("10118060122091501539");
        parkList.add("10118060122091501540");
        parkList.add("10118060122091501541");
        parkList.add("10118060122091501542");
        parkList.add("10118060122091501543");
        parkList.add("10118060122091501544");
        parkList.add("10118060122091501545");
        parkList.add("10118060122091501546");
        parkList.add("10118060122091501547");
        parkList.add("10118060122091501548");
        parkList.add("10118060122091501549");
        parkList.add("10118060122091501550");
        parkList.add("10118060122091501551");
        parkList.add("10118060122091504549");
        parkList.add("10118060122091504550");
        parkList.add("10118060122091504551");
        parkList.add("10118060122091504552");
        parkList.add("10118060122091504553");
        parkList.add("10118060122091504554");
        parkList.add("10118060122091504555");
        parkList.add("10118060122091504556");
        parkList.add("10118060122091504557");
        parkList.add("10118060122091504558");
        parkList.add("10118060122091504559");
        parkList.add("10118060122091504560");
        parkList.add("10118060122091504561");
        parkList.add("10118060122091504562");
        parkList.add("10118060122091504563");
        parkList.add("10118060122091504564");
        parkList.add("10118060122091504565");
        parkList.add("10118060122091504566");
        parkList.add("10118060122091504567");
        parkList.add("10118060122091504568");
        parkList.add("10118060122091504569");
        parkList.add("10118060122091504570");
        parkList.add("10118060122091504571");
        parkList.add("10118060122091504572");
        parkList.add("10118060122091504573");
        parkList.add("10118060122091504574");
        parkList.add("10118060122091504575");
        parkList.add("10118060122091504576");
        parkList.add("10118060122091504577");
        parkList.add("10118060122091504578");
        parkList.add("10118060122091504579");
        parkList.add("10118060122091504580");
        parkList.add("10118060122091504581");
        parkList.add("10118060122091504582");
        parkList.add("10118060122091504583");
        parkList.add("10118060122091504584");
        parkList.add("10118060122091504585");
        parkList.add("10118060122091504586");
        parkList.add("10118060122091504587");
        parkList.add("10118060122091504588");
        parkList.add("10118060122091504589");
        parkList.add("10118060122091504590");
        parkList.add("10118060122091504591");
        parkList.add("10118060122091504592");
        parkList.add("10118060122091504593");
        parkList.add("10118060122091504594");
        parkList.add("10118060122091504595");
        Random random = new Random();
        int i = random.nextInt(parkList.size());
        return parkList.get(i);
    }

}
