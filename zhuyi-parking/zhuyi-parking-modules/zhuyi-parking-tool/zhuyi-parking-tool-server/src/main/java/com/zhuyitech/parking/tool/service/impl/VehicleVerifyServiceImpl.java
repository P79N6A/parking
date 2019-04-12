package com.zhuyitech.parking.tool.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.config.AuthzConfig;
import com.zhuyitech.parking.tool.dto.request.PlateNumberCheckRequestDto;
import com.zhuyitech.parking.tool.dto.result.vehicle.PlateNumberCheckResultDto;
import com.zhuyitech.parking.tool.service.api.VehicleVerifyService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 车辆认证服务
 */
@Service("vehicleVerifyService")
@Slf4j
public class VehicleVerifyServiceImpl implements VehicleVerifyService {

    @Autowired
    private AuthzConfig authzConfig;

    /**
     * 第三方校验车牌信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PlateNumberCheckResultDto> vehicleVerify(PlateNumberCheckRequestDto requestDto) {
        ObjectResultDto<PlateNumberCheckResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            Map<String, Object> params = new HashMap<>();
            params.put("appkey", authzConfig.getAppkey());
            params.put("lsprefix", requestDto.getPlatePrefix());
            params.put("lsnum", requestDto.getPlateNumber());
            params.put("lstype", requestDto.getPlateType());
            params.put("vehicleNumber", requestDto.getVehicleNumber());
            params.put("engineno", requestDto.getEngineNumber());
            String response = Requests.post(authzConfig.getPlateverifyUrl()).verify(false).followRedirect(false).forms(params).charset(Charset.forName("UTF-8")).send().readToText();
            if (StringUtils.isEmpty(response)) {
                objectResultDto.failed();
            } else {
                JSONObject json = JSONObject.parseObject(response);
                if (json.getInteger("status") != 0) {
                    objectResultDto.failed(json.getString("msg"));
                } else {

                    JSONObject resultObject = json.getJSONObject("result");
                    Integer verifyStatus = resultObject.getInteger("verifystatus");
                    String verifyMsg = resultObject.getString("verifymsg");
                    PlateNumberCheckResultDto plateNumberCheckResultDto = new PlateNumberCheckResultDto();
                    plateNumberCheckResultDto.setVerifyStatus(verifyStatus);
                    plateNumberCheckResultDto.setVerifyMsg(verifyMsg);
                    objectResultDto.setData(plateNumberCheckResultDto);
                    objectResultDto.success();
                }
            }
        } catch (Exception e) {
            log.error("车牌号验证失败" + e.getMessage());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }
}
