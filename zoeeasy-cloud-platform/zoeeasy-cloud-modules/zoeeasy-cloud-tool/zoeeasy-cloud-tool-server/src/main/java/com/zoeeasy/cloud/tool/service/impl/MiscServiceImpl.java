package com.zoeeasy.cloud.tool.service.impl;

import com.google.common.base.Charsets;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.tool.amap.AmapService;
import com.zoeeasy.cloud.tool.amap.dto.request.GeocodeGetRequestDto;
import com.zoeeasy.cloud.tool.amap.dto.result.AllRegeoCodeGetResultDto;
import com.zoeeasy.cloud.tool.config.AliyunOssConfig;
import com.zoeeasy.cloud.tool.misc.MiscService;
import com.zoeeasy.cloud.tool.misc.bean.ParkingInfoItemBean;
import com.zoeeasy.cloud.tool.misc.bean.ParkingResultBean;
import com.zoeeasy.cloud.tool.misc.dto.request.ParkingInfoGetRequestDto;
import com.zoeeasy.cloud.tool.misc.dto.response.ParkingInfoBaseResultDto;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 停车场数据拉取
 *
 * @author wangfeng
 * @date 2018/11/23 9:56
 **/
@Service("miscService")
@Slf4j
public class MiscServiceImpl implements MiscService {

    @Autowired
    private AliyunOssConfig aliyunOssConfig;

    @Autowired
    private AmapService amapService;

    /**
     * 从api-拉取停车场数据
     *
     * @param requestDto MiscParkingInfoGetRequestDto
     * @return MiscParkingInfoBaseResultDto
     */
    @Override
    public PagedResultDto<ParkingInfoBaseResultDto> fetchNationalParkingInfo(ParkingInfoGetRequestDto requestDto) {
        PagedResultDto<ParkingInfoBaseResultDto> objectResultDto = new PagedResultDto<>();
        try {

            String path = "http://getparking.market.alicloudapi.com/poidata/getParking";
            String appCode = aliyunOssConfig.getAppCode();
            Map<String, String> headers = new HashMap<>(16);
            headers.put("Authorization", "APPCODE " + appCode);
            headers.put("Content-Type", "application/json; charset=UTF-8");
            Map<String, String> params = new HashMap<>();
            params.put("keyword", requestDto.getKeyword());
            params.put("page_num", requestDto.getPageNum().toString());
            params.put("page_size", requestDto.getPageSize().toString());
            params.put("region", requestDto.getRegion());
            String text = Requests.post(path).headers(headers).params(params).charset(Charsets.UTF_8).send().readToText();
            log.error("text:" + text);
            ParkingResultBean response = Requests.post(path).headers(headers).params(params).charset(Charsets.UTF_8).send().readToJson(ParkingResultBean.class);
            if (response.getStatus().compareTo(0) == 0) {
                List<ParkingInfoBaseResultDto> parkingInfoBaseResultDtoList = new ArrayList<>();
                if (response.getResults() != null && !response.getResults().isEmpty()) {
                    for (int i = 0; i < response.getResults().size(); i++) {
                        ParkingInfoItemBean itemBean = response.getResults().get(i);
                        ParkingInfoBaseResultDto parkingInoResultDto = new ParkingInfoBaseResultDto();
                        parkingInoResultDto.setName(itemBean.getName());
                        parkingInoResultDto.setTelephone(itemBean.getTelephone());
                        parkingInoResultDto.setAddress(itemBean.getAddress());

                        if (itemBean.getLocation() != null) {
                            parkingInoResultDto.setLongitude(itemBean.getLocation().getLng());
                            parkingInoResultDto.setLatitude(itemBean.getLocation().getLat());
                        }
                        //坐标转换
                        //获取具体位置
                        GeocodeGetRequestDto singleRequestDto = new GeocodeGetRequestDto();
                        singleRequestDto.setLocation(itemBean.getLocation().getLng() + "," + itemBean.getLocation().getLat());
                        ObjectResultDto<AllRegeoCodeGetResultDto>
                                allLocationResultDto = amapService.geocoderGetAllCode(singleRequestDto);
                        if (allLocationResultDto.isSuccess() && allLocationResultDto.getData() != null) {
                            AllRegeoCodeGetResultDto allLocationResult = allLocationResultDto.getData();
                            parkingInoResultDto.setAddress(allLocationResult.getAddress());
                            parkingInoResultDto.setProvinceCode(allLocationResult.getProvinceCode());
                            parkingInoResultDto.setCityCode(allLocationResult.getCityCode());
                            parkingInoResultDto.setCountyCode(allLocationResult.getCountyCode());
                        }
                        parkingInfoBaseResultDtoList.add(parkingInoResultDto);
                    }
                }
                objectResultDto.setPageNo(requestDto.getPageNum());
                objectResultDto.setPageSize(requestDto.getPageSize());
                objectResultDto.setTotalCount(response.getTotal().longValue());
                objectResultDto.setItems(parkingInfoBaseResultDtoList);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("从api-拉取停车场数据失败:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

}
