package com.zhuyitech.parking.tool.service.impl;

import com.google.common.base.Charsets;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.tool.bean.parking.ParkingInfoItemBean;
import com.zhuyitech.parking.tool.bean.parking.ParkingResultBean;
import com.zhuyitech.parking.tool.config.AliYunConfig;
import com.zhuyitech.parking.tool.dto.request.parking.ParkingInfoGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.parking.ParkingInfoBaseResultDto;
import com.zhuyitech.parking.tool.service.api.AmapService;
import com.zhuyitech.parking.tool.service.api.MiscService;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 其他服务
 *
 * @author walkman
 * @date 2018/4/29
 */
@Service("miscService")
public class MiscServiceImpl implements MiscService {

    @Autowired
    private AmapService amapService;

    @Autowired
    private AliYunConfig aliYunConfig;

    /**
     * @return
     */
    @Override
    public PagedResultDto<ParkingInfoBaseResultDto> getParking(ParkingInfoGetRequestDto prams) {

        PagedResultDto<ParkingInfoBaseResultDto> objectResultDto = new PagedResultDto<>();
        try {

            String path = "http://getparking.market.alicloudapi.com/poidata/getParking";
            String appcode = aliYunConfig.getLicenseAppcode();
            Map<String, String> headers = new HashMap<>(16);
            headers.put("Authorization", "APPCODE " + appcode);
            headers.put("Content-Type", "application/json; charset=UTF-8");
            Map<String, String> querys = new HashMap<>();
            querys.put("keyword", prams.getKeyword());
            querys.put("page_num", prams.getPageNum().toString());
            querys.put("page_size", prams.getPageSize().toString());
            querys.put("region", prams.getRegion());

            ParkingResultBean response = Requests.post(path).headers(headers).params(querys).charset(Charsets.UTF_8).send().readToJson(ParkingResultBean.class);
            if (response.getStatus().compareTo(0) == 0) {

                List<ParkingInfoBaseResultDto> parkingInfoBaseResultDtoList = new ArrayList<>();

                if (response.getResults() != null && !response.getResults().isEmpty()) {

                    for (int i = 0; i < response.getResults().size(); i++) {

                        ParkingInfoItemBean itemBean = response.getResults().get(i);
                        ParkingInfoBaseResultDto parkingInoResultDto = new ParkingInfoBaseResultDto();
                        parkingInoResultDto.setName(itemBean.getName());
                        parkingInoResultDto.setTelephone(itemBean.getTelephone());
                        parkingInoResultDto.setAddress(itemBean.getAddress());
                        //坐标转换
                        if (itemBean.getLocation() != null) {
//                            LocationConvertRequestDto locationConvertRequestDto = new LocationConvertRequestDto();
//                            locationConvertRequestDto.setLongitude(itemBean.getLocation().getLng());
//                            locationConvertRequestDto.setLatitude(itemBean.getLocation().getLat());
//                            ObjectResultDto<LocationConvertResultDto> objectResultDto1 = amapService.convertLocations(locationConvertRequestDto);
//                            if (objectResultDto1.isSuccess() && objectResultDto1.getData() != null) {
//                                parkingInoResultDto.setLongitude(objectResultDto1.getData().getLongitude());
//                                parkingInoResultDto.setLatitude(objectResultDto1.getData().getLatitude());
//                            } else {
//                                parkingInoResultDto.setLongitude(itemBean.getLocation().getLng());
//                                parkingInoResultDto.setLatitude(itemBean.getLocation().getLat());
//                            }
                            parkingInoResultDto.setLongitude(itemBean.getLocation().getLng());
                            parkingInoResultDto.setLatitude(itemBean.getLocation().getLat());
                        }
                        parkingInfoBaseResultDtoList.add(parkingInoResultDto);
                    }
                }
                objectResultDto.setPageNo(prams.getPageNum());
                objectResultDto.setPageSize(prams.getPageSize());
                objectResultDto.setTotalCount(Long.valueOf(response.getTotal()));
                objectResultDto.setItems(parkingInfoBaseResultDtoList);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
