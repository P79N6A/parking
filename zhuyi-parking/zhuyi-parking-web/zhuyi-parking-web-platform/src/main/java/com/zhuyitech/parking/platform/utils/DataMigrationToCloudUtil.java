package com.zhuyitech.parking.platform.utils;

import com.alibaba.fastjson.JSON;
import com.zhuyitech.parking.pms.dto.result.park.ParkingResultDto;
import net.dongliu.requests.Requests;
import org.modelmapper.ModelMapper;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据迁移-停车场数据
 *
 * @author wangfeng
 * @date 2018/11/15 17:55
 **/
public class DataMigrationToCloudUtil {

    public static String migrationParkingInfoByRequest(ParkingResultDto parkingResultDto) {
        ModelMapper modelMapper = new ModelMapper();
        ParkingAddRequestDto parkingAddRequestDto = modelMapper.map(parkingResultDto, ParkingAddRequestDto.class);
        Map<String, Object> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        headers.put("Accept", "application/json");
        return Requests.post("http://192.168.0.138:10186/cloud/mock/addNonTenantParking").verify(false).followRedirect(false).
                body(JSON.toJSONString(parkingAddRequestDto)).charset(Charset.forName("UTF-8")).headers(headers).send().readToText();
    }
}
