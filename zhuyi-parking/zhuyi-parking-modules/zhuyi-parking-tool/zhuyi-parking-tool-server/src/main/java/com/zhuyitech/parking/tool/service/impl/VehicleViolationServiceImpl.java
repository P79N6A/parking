package com.zhuyitech.parking.tool.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.common.enums.VehicleLevelEnum;
import com.zhuyitech.parking.tool.bean.violation.VehicleViolationDataResultBean;
import com.zhuyitech.parking.tool.bean.violation.VehicleViolationItemResultBean;
import com.zhuyitech.parking.tool.bean.violation.VehicleViolationResultBean;
import com.zhuyitech.parking.tool.config.AliYunConfig;
import com.zhuyitech.parking.tool.domain.LicenseOrganization;
import com.zhuyitech.parking.tool.domain.VehicleViolation;
import com.zhuyitech.parking.tool.dto.request.violation.VehicleViolationQueryByCarRequestDto;
import com.zhuyitech.parking.tool.dto.request.violation.VehicleViolationQueryRequestDto;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationCategoryResultDto;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationItemResultDto;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.enums.ViolationProcessStatus;
import com.zhuyitech.parking.tool.service.LicenseOrganizationCrudService;
import com.zhuyitech.parking.tool.service.VehicleViolationCrudService;
import com.zhuyitech.parking.tool.service.api.VehicleViolationService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.apache.commons.codec.Charsets;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 车辆违章查询服务
 *
 * @author walkman
 * @Date: 2018/4/14
 */
@Service("vehicleViolationService")
@Slf4j
public class VehicleViolationServiceImpl implements VehicleViolationService {

    @Autowired
    private VehicleViolationCrudService vehicleViolationCrudService;

    @Autowired
    private LicenseOrganizationCrudService licenseOrganizationCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AliYunConfig aliYunConfig;

    /**
     * 调第三方接口，请求违章数据,并同步更新本地数据
     *
     * @param requestDto 请求参数
     * @return ObjectResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<VehicleViolationResultDto> queryVehicleViolation(VehicleViolationQueryRequestDto requestDto) {
        ObjectResultDto<VehicleViolationResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            String platePrefix = requestDto.getPlateNumber().substring(0, 2);
            //获取车牌归属地管局信息
            LicenseOrganization licenseOrganization = licenseOrganizationCrudService.findByCityPrefix(platePrefix);
            if (licenseOrganization == null) {
                objectResultDto.makeResult(ToolResultEnum.VEHICLE_VIOLATION_CITY_NOT_SUPPORT.getValue(), ToolResultEnum.VEHICLE_VIOLATION_CITY_NOT_SUPPORT.getComment());
            } else {
                //根据相关管局查询要求，获取发动机或者车架号参数
                String vehicleNumber = requestDto.getVehicleNumber();
                String engineNumber = requestDto.getEngineNo();
                // -1是指输入全部字段，0是指不需要输入，6是指输入后六位，以此类推
                if (licenseOrganization.getVinLength() == 0) {
                    vehicleNumber = "";
                } else if (licenseOrganization.getVinLength() > 0) {
                    vehicleNumber = requestDto.getVehicleNumber().substring(requestDto.getVehicleNumber().length() - licenseOrganization.getVinLength());
                } else if (licenseOrganization.getVinLength() < 0) {
                    vehicleNumber = requestDto.getVehicleNumber();
                }
                //-1是指输入全部字段，0是指不需要输入，6是指输入后六位
                if (licenseOrganization.getEngineNoLength() == 0) {
                    engineNumber = "";
                } else if (licenseOrganization.getEngineNoLength() > 0) {
                    engineNumber = requestDto.getEngineNo().substring(requestDto.getEngineNo().length() - licenseOrganization.getEngineNoLength());
                } else if (licenseOrganization.getEngineNoLength() < 0) {
                    engineNumber = requestDto.getEngineNo();
                }
                Map<String, String> params = new HashMap<>();
                params.put("city", licenseOrganization.getCity());
                params.put("plateNumber", requestDto.getPlateNumber());
                if (StringUtils.isNotEmpty(vehicleNumber)) {
                    params.put("vin", vehicleNumber);
                }
                if (StringUtils.isNotEmpty(engineNumber)) {
                    params.put("engineNo", engineNumber);
                }
                if (requestDto.getCarType() != null && requestDto.getCarType().equals(VehicleLevelEnum.LARGE.getValue())) {
                    params.put("carType", "01");
                } else {
                    params.put("carType", "02");
                }
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "APPCODE " + aliYunConfig.getViolationAppcode());
                VehicleViolationResultBean vehicleViolationResultBean =
                        Requests.post(aliYunConfig.getViolationUrl()).headers(headers)
                                .jsonBody(params).charset(Charsets.UTF_8).send().readToJson(VehicleViolationResultBean.class);
                if (vehicleViolationResultBean != null && vehicleViolationResultBean.getSuccess()) {

                    VehicleViolationDataResultBean data = vehicleViolationResultBean.getData();

                    VehicleViolationResultDto vehicleViolationResultDto = modelMapper.map(data, VehicleViolationResultDto.class);

                    List<VehicleViolationItemResultBean> violations = data.getViolations();
                    if (violations != null) {
                        for (VehicleViolationItemResultBean violationBean : violations) {

                            VehicleViolation vehicleViolation = modelMapper.map(violationBean, VehicleViolation.class);
                            //根据code查询违章表，若存在记录执行update，否则执行insert
                            vehicleViolation.setCarId(requestDto.getCarId());
                            VehicleViolation vehicleViolationExist = vehicleViolationCrudService.findByCarIdAndCode(requestDto.getCarId(), vehicleViolation.getCode());
                            if (vehicleViolationExist != null) {
                                vehicleViolation.setId(vehicleViolationExist.getId());
                                vehicleViolationCrudService.updateById(vehicleViolation);
                            } else {
                                vehicleViolationCrudService.insert(vehicleViolation);
                            }
                        }

                        //违章记录列表
                        List<VehicleViolationItemResultDto> vehicleViolationItemDtoList = modelMapper.map(violations, new TypeToken<List<VehicleViolationItemResultDto>>() {
                        }.getType());

                        List<VehicleViolationItemResultDto> untreatedResultDtoList = vehicleViolationItemDtoList.stream()
                                .filter(item -> item.getProcessStatus().equals(ViolationProcessStatus.UNTREATED.getValue())
                                        || item.getProcessStatus().equals(ViolationProcessStatus.TREADING.getValue())
                                )
                                .sorted(Comparator.comparing(VehicleViolationItemResultDto::getViolationTime).reversed()).
                                        collect(Collectors.toList());
                        if (untreatedResultDtoList != null) {
                            vehicleViolationResultDto.setUntreatedViolations(untreatedResultDtoList);
                        }
                        List<VehicleViolationItemResultDto> treatedResultDtoList = vehicleViolationItemDtoList.stream()
                                .filter(item -> item.getProcessStatus().equals(ViolationProcessStatus.TREATED.getValue()))
                                .sorted(Comparator.comparing(VehicleViolationItemResultDto::getViolationTime).reversed()).
                                        collect(Collectors.toList());
                        if (treatedResultDtoList != null) {
                            vehicleViolationResultDto.setTreatedViolations(treatedResultDtoList);
                        }
                    }
                    objectResultDto.setData(vehicleViolationResultDto);
                }
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("查询车辆违章记录失败,异常信息{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 查询本地车辆违章记录
     *
     * @param requestDto 请求参数
     * @return ObjectResultDto
     */
    @Override
    public ObjectResultDto<VehicleViolationCategoryResultDto> queryLocalVehicleViolation(VehicleViolationQueryByCarRequestDto requestDto) {

        ObjectResultDto<VehicleViolationCategoryResultDto> objectResultDto = new ObjectResultDto<>();

        try {

            VehicleViolationCategoryResultDto vehicleViolationCategoryResultDto = new VehicleViolationCategoryResultDto();

            List<VehicleViolation> violationList = vehicleViolationCrudService.findByCarId(requestDto.getCarId());
            if (violationList != null) {

                List<VehicleViolation> untreatedViolations = violationList.stream()
                        .filter(item -> item.getProcessStatus().equals(ViolationProcessStatus.UNTREATED.getValue())
                                || item.getProcessStatus().equals(ViolationProcessStatus.TREADING.getValue())
                        )
                        .sorted(Comparator.comparing(VehicleViolation::getViolationTime).reversed()).
                                collect(Collectors.toList());
                if (untreatedViolations != null) {

                    List<VehicleViolationItemResultDto> untreatedResultDtoList = modelMapper.map(untreatedViolations, new TypeToken<List<VehicleViolationItemResultDto>>() {
                    }.getType());

                    vehicleViolationCategoryResultDto.setUntreatedViolations(untreatedResultDtoList);
                }

                List<VehicleViolation> treatedViolations = violationList.stream()
                        .filter(item -> item.getProcessStatus().equals(ViolationProcessStatus.TREATED.getValue()))
                        .sorted(Comparator.comparing(VehicleViolation::getViolationTime).reversed()).
                                collect(Collectors.toList());
                if (treatedViolations != null) {

                    List<VehicleViolationItemResultDto> treatedResultDtoList = modelMapper.map(treatedViolations, new TypeToken<List<VehicleViolationItemResultDto>>() {
                    }.getType());

                    vehicleViolationCategoryResultDto.setTreatedViolations(treatedResultDtoList);
                }
                objectResultDto.setData(vehicleViolationCategoryResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询车辆违章记录失败,异常信息{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

}
