package com.zhuyitech.parking.client;

import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.BeanUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.client.dto.ClientMockRequestDto;
import com.zhuyitech.parking.constant.PropertyKeyConfig;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author AkeemSuper
 * @date 2018/12/6 0006
 */
@Service("clientService")
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private PropertyKeyConfig propertyKeyConfig;

    /**
     * 模拟客户端数据
     *
     * @param requestDto
     */
    @Override
    public ResultDto mockClientPassRecord(ClientMockRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Map<String, Object> map = BeanUtils.objectToMap(requestDto);
            String response = Requests.post(propertyKeyConfig.getClientUrl()).socksTimeout(5000 * 60).connectTimeout(30 * 1000).jsonBody(map).charset(Charsets.UTF_8).send().readToText();
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
            log.error("模拟客户端数据失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
