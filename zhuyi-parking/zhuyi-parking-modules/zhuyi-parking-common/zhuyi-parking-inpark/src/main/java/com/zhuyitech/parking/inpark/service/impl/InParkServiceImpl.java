package com.zhuyitech.parking.inpark.service.impl;

import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.inpark.config.InparkConfig;
import com.zhuyitech.parking.inpark.enums.InparkResultEnum;
import com.zhuyitech.parking.inpark.request.InParkOrderListRequest;
import com.zhuyitech.parking.inpark.request.InParkParkingListRequest;
import com.zhuyitech.parking.inpark.result.InParkBaseResult;
import com.zhuyitech.parking.inpark.service.InParkService;
import com.zhuyitech.parking.inpark.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


/**
 * <pre>
 *     支付宝支付接口实现
 * </pre>
 *
 * @author zwq
 * @date 2018-01-11-13:13
 */
@Service("inParkService")
@Slf4j
public class InParkServiceImpl implements InParkService {

    @Autowired
    private InparkConfig inparkConfig;

    /**
     * 获取附近停车场列表
     *
     * @param request
     * @return
     */
    @Override
    public ObjectResultDto<InParkBaseResult> getParkingList(InParkParkingListRequest request) {
        ObjectResultDto<InParkBaseResult> objectResultDto = new ObjectResultDto<>();
        Map<String, String> paramMap = new HashMap<>();
        if (StringUtils.isEmpty(request.getTimePoint())) {
            paramMap.put("timePoint", String.valueOf(DateUtils.now().getTime() / 1000L));
        } else {
            paramMap.put("timePoint", request.getTimePoint());
        }
        paramMap.put("mobile", request.getMobile());
        paramMap.put("lat", request.getLat());
        paramMap.put("lng", request.getLng());
        if (StringUtils.isNotEmpty(request.getCarNum())) {
            paramMap.put("carNum", request.getTimePoint());
        }
        try {
            String params = SignUtils.createLinkString(paramMap);
            String apiSign = SignUtils.createSign(params, inparkConfig.getSignKey());
            paramMap.put("sign", apiSign);
            InParkBaseResult response = Requests.post(inparkConfig.getParkingListUrl()).
                    verify(false).followRedirect(false)
                    .connectTimeout(1000 * 60).socksTimeout(1000 * 60 * 3)
                    .charset(Charset.forName("UTF-8")).params(paramMap)
                    .send().readToJson(InParkBaseResult.class);
            if (response != null && InparkResultEnum.SUCCESS.getValue().equals(response.getCode())) {
                objectResultDto.setData(response);
                objectResultDto.success();
            } else {
                objectResultDto.failed();
            }
        } catch (Exception e) {
            log.error("获取附近停车场列表失败:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取用户订单列表
     *
     * @param request
     * @return
     */
    @Override
    public ObjectResultDto<InParkBaseResult> getOrderList(InParkOrderListRequest request) {
        ObjectResultDto<InParkBaseResult> objectResultDto = new ObjectResultDto<>();
        Map<String, String> paramMap = new HashMap<>();
        if (StringUtils.isEmpty(request.getTimePoint())) {
            paramMap.put("timePoint", String.valueOf(DateUtils.now().getTime() / 1000L));
        } else {
            paramMap.put("timePoint", request.getTimePoint());
        }
        paramMap.put("mobile", request.getMobile());
        try {
            String params = SignUtils.createLinkString(paramMap);
            String apiSign = SignUtils.createSign(params, inparkConfig.getSignKey());
            paramMap.put("sign", apiSign);

            InParkBaseResult response = Requests.post(inparkConfig.getOrderListUrl()).
                    verify(false).followRedirect(false)
                    .connectTimeout(1000 * 60).socksTimeout(1000 * 60 * 3)
                    .charset(Charset.forName("UTF-8")).params(paramMap)
                    .send().readToJson(InParkBaseResult.class);
            if (response != null && InparkResultEnum.SUCCESS.getValue().equals(response.getCode())) {
                objectResultDto.setData(response);
                objectResultDto.success();
            } else {
                objectResultDto.failed();
            }
        } catch (Exception e) {
            log.error("获取用户订单列表失败:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

}
