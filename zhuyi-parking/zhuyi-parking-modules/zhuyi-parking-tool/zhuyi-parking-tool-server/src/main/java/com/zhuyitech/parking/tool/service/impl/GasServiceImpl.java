package com.zhuyitech.parking.tool.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.tool.config.AliYunConfig;
import com.zhuyitech.parking.tool.domain.Gas;
import com.zhuyitech.parking.tool.dto.request.gas.GasStationGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.gas.GasGetResultDto;
import com.zhuyitech.parking.tool.dto.result.gas.GasResult;
import com.zhuyitech.parking.tool.enums.GasStatusEnum;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.GasCrudService;
import com.zhuyitech.parking.tool.service.api.GasService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询加油站_加气站_充电站相关服务
 *
 * @author zwq
 * @date 2018/06/21
 */
@Service("gasService")
@Slf4j
public class GasServiceImpl implements GasService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GasCrudService gasCrudService;

    @Autowired
    private AliYunConfig aliYunConfig;

    @Override
    public ResultDto getGas(GasStationGetRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            final int pageSize = 50;
            int pageNumber = 1;
            int totalCount;
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", "APPCODE " + aliYunConfig.getAppcode());
            String path = "http://getgas.market.alicloudapi.com/poidata/getGas";

            do {
                Map<String, String> params = new HashMap<>();
                params.put("keyword", requestDto.getKeyword());
                params.put("page_num", String.valueOf(pageNumber));
                params.put("page_size", String.valueOf(pageSize));
                params.put("region", requestDto.getRegion());
                String response = Requests.post(path).headers(header).verify(false).followRedirect(false).
                        forms(params).charset(Charset.forName("UTF-8")).send().readToText();
                if (null == response) {
                    return resultDto.makeResult(ToolResultEnum.GET_GES_INFO_ERROR.getValue(), ToolResultEnum.GET_GES_INFO_ERROR.getComment());
                }
                JSONObject json = JSONObject.parseObject(response);
                String status = json.getString("status");
                if (!status.equals("0")) {
                    return resultDto.makeResult(ToolResultEnum.GET_GES_INFO_ERROR.getValue(), ToolResultEnum.GET_GES_INFO_ERROR.getComment());
                }
                totalCount = json.getInteger("total");
                GasGetResultDto gasGetResultDto = modelMapper.map(json, GasGetResultDto.class);
                List<GasResult> gesResults = gasGetResultDto.getResults();
                if (!(null == gesResults || gesResults.isEmpty())) {
                    for (GasResult obj : gesResults) {
                        EntityWrapper<Gas> entity = new EntityWrapper<>();
                        if (obj.getLocation() != null) {
                            entity.eq("latitude", obj.getLocation().getLat());
                            entity.eq("longitude", obj.getLocation().getLng());
                        }
                        entity.eq("name", obj.getName());
                        entity.eq("address", obj.getAddress());
                        List<Gas> gasList = gasCrudService.selectList(entity);

                        if (null == gasList || gasList.isEmpty()) {
                            Gas gas = new Gas();
                            gas.setAddress(obj.getAddress());
                            if (obj.getLocation() != null) {
                                gas.setLatitude(obj.getLocation().getLat());
                                gas.setLongitude(obj.getLocation().getLng());
                            }
                            gas.setName(obj.getName());
                            gas.setTelephone(obj.getTelephone());
                            if (obj.getName().contains("加油")) {
                                gas.setGasType(GasStatusEnum.GAS_YOU.getValue());
                            } else if (obj.getName().contains("加气")) {
                                gas.setGasType(GasStatusEnum.GAS_QI.getValue());
                            } else if (obj.getName().contains("充电")) {
                                gas.setGasType(GasStatusEnum.GAS_DIAN.getValue());
                            } else {
                                gas.setGasType(0);
                            }
                            gasCrudService.insert(gas);
                        }
                    }
                }
                pageNumber++;
            } while (pageNumber <= Math.ceil(totalCount / pageSize));
            resultDto.success();
        } catch (Exception e) {
            log.error("同步全国加油加气充电站数据" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
