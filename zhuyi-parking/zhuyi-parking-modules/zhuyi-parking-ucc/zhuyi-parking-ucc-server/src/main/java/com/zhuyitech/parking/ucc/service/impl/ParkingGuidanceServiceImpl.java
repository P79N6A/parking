package com.zhuyitech.parking.ucc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.car.UserCarInfoService;
import com.zhuyitech.parking.ucc.car.result.UserCarPlateNumberMyResultDto;
import com.zhuyitech.parking.ucc.dto.result.*;
import com.zhuyitech.parking.ucc.service.api.ParkingGuidanceService;
import com.zoeeasy.cloud.pms.park.ParkingVehicleRecordService;
import com.zoeeasy.cloud.pms.park.dto.request.MyPlateNumberParkingGetRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.ParkingAreaService;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.ParkingAreaGetRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingFloorRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingGuidanceParamDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingFloorResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingVehicleRecordViewResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.PlateNumberMyCloudResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * @ClassName ParkingGuidanceServiceImpl
 * @Description TODO
 * @Author qhxu
 * @Date 2019/3/19 19:36
 * @Version1.0
 **/
@Slf4j
@Service("parkingGuidanceService")
public class ParkingGuidanceServiceImpl implements ParkingGuidanceService {

    @Autowired
    ParkingAreaService parkingAreaService;

    @Autowired
    ParkingVehicleRecordService parkingVehicleRecordService;

    @Autowired
    private UserCarInfoService userCarInfoService;

    @Autowired
    private ModelMapper modelMapper;


    private static final String authUrl = "http://115.238.69.53:8666/DH/Navigation";

    /**
     * @Description: 获取我的车牌/停车信息
     * @Author: qhxu
     * @CreateDate: 2019/3/23 10:39
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/23 10:39
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @Override
    public ListResultDto<PlateNumberMyResultDto> getMyPlateNumber(PlateNumberMyRequestVo plateNumberMyRequestVo) {
        ListResultDto<PlateNumberMyResultDto> returnResult = new ListResultDto<>();
        List<PlateNumberMyResultDto> plateNumberMyResultDtoList = new ArrayList<>();
        ListResultDto<UserCarPlateNumberMyResultDto> userCarPlateNumberResultDto;
        String userId = String.valueOf(plateNumberMyRequestVo.getSessionIdentity().getUserId());
        try {  //取车牌
            userCarPlateNumberResultDto = userCarInfoService.getPlateNumbers(userId);
            if (userCarPlateNumberResultDto.getItems().size() > 0) {
                for (int i = 0; i < userCarPlateNumberResultDto.getItems().size(); i++) {
                    MyPlateNumberParkingGetRequestDto myPlateNumberParkingGetRequestDto = new MyPlateNumberParkingGetRequestDto();
                    myPlateNumberParkingGetRequestDto.setPlateNumber(userCarPlateNumberResultDto.getItems().get(i).getFullPlateNumber());
                    //在停车辆信息
                    ObjectResultDto<ParkingVehicleRecordViewResultDto> parkingVehicleRecordViewResultDto = parkingVehicleRecordService.getMyPlateNumberParkingVehicleRecord(myPlateNumberParkingGetRequestDto);
                    if (parkingVehicleRecordViewResultDto.isSuccess()) {
                        PlateNumberMyResultDto plateNumberMyResultDto = modelMapper.map(parkingVehicleRecordViewResultDto, PlateNumberMyResultDto.class);
                        //车场名称
                        plateNumberMyResultDto.setParkingId(parkingVehicleRecordViewResultDto.getData().getParkingId());
                        plateNumberMyResultDto.setParkingName(parkingVehicleRecordViewResultDto.getData().getParkingName());
                        plateNumberMyResultDto.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                        plateNumberMyResultDto.setFullPlateNumber(parkingVehicleRecordViewResultDto.getData().getPlateNumber());
                        //泊位编号
                        plateNumberMyResultDto.setParkingName(parkingVehicleRecordViewResultDto.getData().getParkingName());
                        plateNumberMyResultDto.setParkingLotCode(parkingVehicleRecordViewResultDto.getData().getParkingLotNumber());

                        //区域
                        ParkingAreaGetRequestDto parkingAreaGetRequestDto = new ParkingAreaGetRequestDto();
                        parkingAreaGetRequestDto.setId(parkingVehicleRecordViewResultDto.getData().getParkingLotId());
                        ObjectResultDto<ParkingAreaResultDto> parkingArea = parkingAreaService.getParkingArea(parkingAreaGetRequestDto);
                        plateNumberMyResultDto.setAreaName(parkingArea.getData().getName());
                        //楼层
                        parkingArea.getData().getFloorId();
                        ParkingFloorRequestDto parkingFloorRequestDto = new ParkingFloorRequestDto();
                        parkingFloorRequestDto.setId(parkingArea.getData().getFloorId());
                        ObjectResultDto<ParkingFloorResultDto> parkingFloorDto = parkingVehicleRecordService.getParkingFloor(parkingFloorRequestDto);
                        plateNumberMyResultDto.setFloorName(parkingFloorDto.getData().getFloorName());

                        plateNumberMyResultDto.setParkingLotDescription(plateNumberMyResultDto.getFloorName() + plateNumberMyResultDto.getAreaName() + plateNumberMyResultDto.getParkingLotCode());
                        plateNumberMyResultDtoList.add(plateNumberMyResultDto);
                        returnResult.setCode(1);
                        returnResult.setMessage("请求成功");
                        returnResult.setItems(plateNumberMyResultDtoList);
                    }
                    if (parkingVehicleRecordViewResultDto.isFailed()) {
                        returnResult.setCode(1);
                        returnResult.setMessage("请求成功");
                    }
                }
            }
            returnResult.setCode(userCarPlateNumberResultDto.getCode());
            returnResult.setMessage(userCarPlateNumberResultDto.getMessage());
        } catch (Exception e) {
            log.error(e.toString());
            returnResult.setCode(-1);
            returnResult.setMessage("数据异常");
        }
        return returnResult;
    }

    /**
     * @Description: 根据车位车牌查找信息
     * @Author: qhxu
     * @CreateDate: 2019/3/23 16:19
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/23 16:19
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @Override
    public ListResultDto<PlateNumberMyCloudResultDto> getPlateNumberList(ParkingGuidanceQueryRequestVo parkingGuidanceRequestVo) {
        ParkingGuidanceParamDto parkingGuidanceParamDto = new ParkingGuidanceParamDto();
        if (StringUtils.isNotEmpty(parkingGuidanceRequestVo.getPlateNumber())) {
            parkingGuidanceParamDto.setPlateNumber(parkingGuidanceRequestVo.getPlateNumber());
        }
        if (StringUtils.isNotEmpty(parkingGuidanceRequestVo.getParkingLotNumber())) {
            parkingGuidanceParamDto.setParkingLotNumber(parkingGuidanceRequestVo.getParkingLotNumber());
        }
        ListResultDto<PlateNumberMyCloudResultDto> plateNumberMyCloudResultDto = new ListResultDto<>();
        try {
            //取车牌在停车辆信息
            plateNumberMyCloudResultDto = parkingVehicleRecordService.getParkingGuidance(parkingGuidanceParamDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            plateNumberMyCloudResultDto.setCode(-1);
            plateNumberMyCloudResultDto.setMessage("数据异常");
        }
        return plateNumberMyCloudResultDto;
    }

    /**
     * @Description: 定位导航
     * @Author: qhxu
     * @CreateDate: 2019/3/25 9:24
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/25 9:24
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @Override
    public ObjectResultDto<ParkingGuidanceResultDto> getParkingGuidance(ParkingGuidanceRequestVo requestDto) {
        ObjectResultDto<ParkingGuidanceResultDto> oBJparkingGuidanceResultDto = new ObjectResultDto<>();
        ParkingGuidanceParamDto parkingGuidanceParamDto = new ParkingGuidanceParamDto();
        if (StringUtils.isNotEmpty(requestDto.getEndParking())) {
            parkingGuidanceParamDto.setParkingLotNumber(requestDto.getEndParking());
        }
        List list = new ArrayList();
        String jsonString, resultJson;
        try {
            jsonString = "{\"parkId\":" + requestDto.getParkingId() + ",\"bid\":" + requestDto.getBluetoothId() + ",\"end\":" + requestDto.getEndParking() + "}";
            resultJson = Request.Post(authUrl).bodyString(jsonString, ContentType.APPLICATION_JSON).execute().returnContent().asString(Charsets.UTF_8);
            ParkingGuidanceResultDto parkingGuidanceResult = new ParkingGuidanceResultDto();// JSON.parseObject(resultJson, ParkingGuidanceResultDto.class);
            String st = "{\"code\": \"10000\",\"message\": \"成功\",\"data\": {\"length\": \"369.0\",\"path\": [{\"X\": \"488\"},{\"Y\": \"150\"},{\"X\": \"92\"},{\"Y\": \"150\"},{\"X\": \"92\"},{\"Y\": \"313\"},{\"X\": \"140\"},{\"Y\": \"313\"}]}}";
            JSONObject jsonObject = JSON.parseObject(st);
            if ("10000".equals(jsonObject.get("code"))) {
                if (!"".equals(jsonObject.get("data"))) {
                    JSONObject jo = JSON.parseObject(jsonObject.get("data").toString());
                    if (!"".equals(jo.get("path"))) {
                        JSONArray jsonArray = JSON.parseArray(jo.get("path").toString());
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            list.add(object);
                            log.info(list.get(i).toString());
                        }
                        parkingGuidanceResult.setPath(list);
                    }
                }
                parkingGuidanceResult.setBackURL("http://anyparking.oss-cn-hangzhou.aliyuncs.com/11741dd22bec4fe79d66f6c36628df38.jpg?Expires=3128828558&OSSAccessKeyId=LTAIQTzAKXLw0EDe&Signature=QowAQM8p9LJTnQsdZFg6U4YQ21Q%3D");
                parkingGuidanceResult.setPlaneURL("http://anyparking.oss-cn-hangzhou.aliyuncs.com/11741dd22bec4fe79d66f6c36628df38.jpg?Expires=3128828558&OSSAccessKeyId=LTAIQTzAKXLw0EDe&Signature=QowAQM8p9LJTnQsdZFg6U4YQ21Q%3D");
                oBJparkingGuidanceResultDto.setData(parkingGuidanceResult);
                parkingGuidanceResult.setCode(1);
                parkingGuidanceResult.setMessage("请求成功!");
            } else if ("20000".equals(jsonObject.get("code"))) {
                parkingGuidanceResult.setCode(1);
                parkingGuidanceResult.setMessage("异常!");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            oBJparkingGuidanceResultDto.isFailed();
        }
        return oBJparkingGuidanceResultDto;
    }
}
