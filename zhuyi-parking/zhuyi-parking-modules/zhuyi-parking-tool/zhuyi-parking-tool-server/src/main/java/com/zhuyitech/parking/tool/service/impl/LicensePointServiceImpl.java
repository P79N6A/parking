package com.zhuyitech.parking.tool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.config.AuthzConfig;
import com.zhuyitech.parking.tool.dto.request.driver.LicenseScoreQueryRequestDto;
import com.zhuyitech.parking.tool.dto.result.carpoint.LicensePointPenaltyDataResultDto;
import com.zhuyitech.parking.tool.service.api.LicensePointService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 驾驶证扣分查询服务
 *
 * @author: yuzhicheng
 */
@Service("licensePointService")
@Slf4j
public class LicensePointServiceImpl implements LicensePointService {

    @Autowired
    private AuthzConfig authzConfig;

    /**
     * 驾驶证扣分查询
     *
     * @return ObjectResultDto
     */
    @Override
    public ObjectResultDto<LicensePointPenaltyDataResultDto> requestCarPointPenaltyData(LicenseScoreQueryRequestDto requestDto) {
        String appKey = authzConfig.getAppkey();
        String url = authzConfig.getDriverlicenseUrl();
        ObjectResultDto<LicensePointPenaltyDataResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("appkey", appKey);
            params.put("licensenumber", requestDto.getLicenseNumber());
            params.put("licenseid", requestDto.getArchiveNumber());
            String response = Requests.post(url).verify(false).followRedirect(false).forms(params).charset(Charset.forName("UTF-8")).send().readToText();

            if (StringUtils.isEmpty(response)) {
                objectResultDto.failed("驾驶证扣分查询失败");
            } else {
                JSONObject json = JSONObject.parseObject(response);
                if (json.getInteger("status") != 0) {
                    objectResultDto.failed(json.getString("msg"));
                } else {
                    JSONObject resultObject = json.getJSONObject("result");
                    LicensePointPenaltyDataResultDto resultDto = new LicensePointPenaltyDataResultDto();
                    resultDto.setLicenseNumber(resultObject.getString("licensenumber"));
                    resultDto.setLicenseId(resultObject.getString("licenseid"));
                    resultDto.setStartDate(resultObject.getString("startdate"));
                    resultDto.setEndDate(resultObject.getString("enddate"));
                    resultDto.setRealName(resultObject.getString("realname"));
                    resultDto.setType(resultObject.getString("type"));
                    resultDto.setLicenseStatus(resultObject.getString("licensestatus"));
                    resultDto.setScore(resultObject.getInteger("score"));
                    objectResultDto.success();
                }
            }
        } catch (Exception e) {
            log.error("驾驶证扣分查询失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

}
