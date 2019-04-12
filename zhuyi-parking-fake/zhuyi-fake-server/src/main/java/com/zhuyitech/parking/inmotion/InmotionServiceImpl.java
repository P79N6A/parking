package com.zhuyitech.parking.inmotion;

import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.constant.FakeConstant;
import com.zhuyitech.parking.constant.PropertyKeyConfig;
import com.zhuyitech.parking.inmotion.dto.InMotionPushDataBodyRequestDto;
import com.zhuyitech.parking.inmotion.dto.InMotionPushDataMockRequestDto;
import com.zhuyitech.parking.utils.FakeUtils;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author AkeemSuper
 * @date 2018/11/29 0029
 */
@Service("inmotionService")
@Slf4j
public class InmotionServiceImpl implements InmotionService {

    @Autowired
    private PropertyKeyConfig propertyKeyConfig;

    /**
     * 模拟易姆逊数据
     *
     * @return
     */
    @Override
//    @Scheduled(fixedDelay = 10 * 60 * 1000)
    public ResultDto mockInmontionPass() {
        ResultDto resultDto = new ResultDto();
        try {
            Random random = new Random();
            int fatOrDev = random.nextInt(2);
            String deviceId;
            String url = propertyKeyConfig.getInmotionUrl();
            if (fatOrDev == 0) {
                //fat环境
                deviceId = FakeUtils.getDevDeviceId();
            } else {
                //dev环境
                deviceId = FakeUtils.getFatDeviceId();
            }
            InMotionPushDataMockRequestDto inMotionPushDataMockRequestDto = new InMotionPushDataMockRequestDto();
            inMotionPushDataMockRequestDto.setCmd(FakeConstant.INMOTION_CMD);
            InMotionPushDataBodyRequestDto inMotionPushDataBodyRequestDto = new InMotionPushDataBodyRequestDto();
            inMotionPushDataBodyRequestDto.setDeviceID(deviceId);
            int direct = random.nextInt(2);
            inMotionPushDataBodyRequestDto.setParkingStatu(direct);
            String formatDate = DateTimeUtils.formatDate(DateUtils.now(), FakeUtils.DATE_FORMAT);
            inMotionPushDataBodyRequestDto.setPassTime(123);
            inMotionPushDataBodyRequestDto.setSequence(1);
            inMotionPushDataBodyRequestDto.setBattary(60);
            inMotionPushDataBodyRequestDto.setRssi(3);
            inMotionPushDataBodyRequestDto.setTime(formatDate);
            inMotionPushDataBodyRequestDto.setErrcode(1);
            inMotionPushDataMockRequestDto.setBody(inMotionPushDataBodyRequestDto);
            String response = Requests.post(url).socksTimeout(5000 * 60).
                    connectTimeout(30 * 1000).jsonBody(inMotionPushDataMockRequestDto).charset(Charsets.UTF_8).send().readToText();
            if (StringUtils.isNotEmpty(response)) {
                JSONObject jsonObject = JSONObject.parseObject(response);
                Boolean success = jsonObject.getBoolean("success");
                if (success) {
                    return resultDto.success();
                } else {
                    return resultDto.failed();
                }
            }
            resultDto.failed();
        } catch (Exception e) {
            log.error("模拟易姆逊数据失败");
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 模拟Imotion上报数据
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @Override
    public ResultDto mockInmontionPass(InMotionPushDataMockRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Random random = new Random();
            int fatOrDev = random.nextInt(2);
            String deviceId;
            String url = propertyKeyConfig.getInmotionUrl();

            String response = Requests.post(url).socksTimeout(5000 * 60).
                    connectTimeout(30 * 1000).jsonBody(requestDto).charset(Charsets.UTF_8).send().readToText();
            if (StringUtils.isNotEmpty(response)) {
                JSONObject jsonObject = JSONObject.parseObject(response);
                Boolean success = jsonObject.getBoolean("success");
                if (success) {
                    return resultDto.success();
                } else {
                    return resultDto.failed();
                }
            }
            resultDto.failed();
        } catch (Exception e) {
            log.error("模拟易姆逊数据失败");
            resultDto.failed();
        }
        return resultDto;
    }
}
