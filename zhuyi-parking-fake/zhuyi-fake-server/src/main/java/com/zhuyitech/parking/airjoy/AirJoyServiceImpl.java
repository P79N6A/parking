package com.zhuyitech.parking.airjoy;

import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.constant.PropertyKeyConfig;
import com.zhuyitech.parking.utils.FakeUtils;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * @author AkeemSuper
 * @date 2018/11/29 0029
 */
@Service("airJoyService")
@Slf4j
public class AirJoyServiceImpl implements AirJoyService {

    @Autowired
    private PropertyKeyConfig propertyKeyConfig;

    @Override
    @Scheduled(fixedDelay = 10 * 60 * 1000)
    public ResultDto mockAirJoyPassVehicle() {
        ResultDto resultDto = new ResultDto();
        try {
            Random random = new Random();
            int fatOrDev = random.nextInt(2);
            String macCode;
            String url = propertyKeyConfig.getAirJoyUrl();

            if (fatOrDev == 0) {
                //fat环境
                macCode = FakeUtils.getFatMacCode();
            } else {
                //dev环境
                macCode = FakeUtils.getDevMacCode();
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("mac", macCode);
            Date date = new Date();
            int direct = random.nextInt(2);
            map.put("status", direct);
            map.put("dataType", 1);
            String formatDate = DateTimeUtils.formatDate(date, FakeUtils.DATE_FORMAT);
            map.put("uploadTime", formatDate);
            String response = Requests.post(url).socksTimeout(5000 * 60).
                    connectTimeout(30 * 1000).jsonBody(map).charset(Charsets.UTF_8).send().readToText();
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
            log.error("模拟艾佳数据失败");
            resultDto.failed();
        }
        return resultDto;
    }

}
