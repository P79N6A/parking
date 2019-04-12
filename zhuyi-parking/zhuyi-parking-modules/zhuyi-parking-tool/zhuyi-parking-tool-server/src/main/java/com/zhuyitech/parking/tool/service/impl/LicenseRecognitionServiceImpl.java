package com.zhuyitech.parking.tool.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.common.utils.ImageBase64;
import com.zhuyitech.parking.tool.bean.ProsAndCons;
import com.zhuyitech.parking.tool.bean.RecognitionBean;
import com.zhuyitech.parking.tool.config.AliYunConfig;
import com.zhuyitech.parking.tool.domain.MessageLog;
import com.zhuyitech.parking.tool.dto.result.license.LicenseRecognitionBackResultDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseRecognitionFaceResultDto;
import com.zhuyitech.parking.tool.enums.MessageLogStatusEnum;
import com.zhuyitech.parking.tool.enums.ProsAndConsEnum;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.MessageLogCrudService;
import com.zhuyitech.parking.tool.service.api.LicenseRecognitionService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 行驶证识别服务
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
@Service("licenseRecognitionService")
@Slf4j
public class LicenseRecognitionServiceImpl implements LicenseRecognitionService {

    @Autowired
    private MessageLogCrudService messageLogCrudService;

    @Autowired
    private AliYunConfig aliYunConfig;

    /**
     * 行驶证正面识别
     *
     * @param inputStream inputStream
     * @return LicenseRecognitionFaceResultDto
     */
    @Override
    public ObjectResultDto<LicenseRecognitionFaceResultDto> licenseRecognitionByFace(InputStream inputStream) {
        ObjectResultDto<LicenseRecognitionFaceResultDto> resultDto = new ObjectResultDto<>();

        try {
            String path = aliYunConfig.getLicenseUrl();
            String appcode = aliYunConfig.getLicenseAppcode();
            Map<String, String> headers = new HashMap<>(16);
            headers.put("Authorization", "APPCODE " + appcode);
            headers.put("Content-Type", "application/json; charset=UTF-8");
            if (null == inputStream) {
                return resultDto.makeResult(ToolResultEnum.IMAGE_EMPTY.getValue(), ToolResultEnum.IMAGE_EMPTY.getComment());
            }
            RecognitionBean recgnitionBean = new RecognitionBean();
            String imageToBase = ImageBase64.getImageStrFromUrl(inputStream);
            recgnitionBean.setImage(imageToBase);
            recgnitionBean.setConfigure(new ProsAndCons(ProsAndConsEnum.PROS.getValue()));
            String param = JSON.toJSONString(recgnitionBean);
            Date requestTime = new Date();
            String response = Requests.post(path).headers(headers).body(param).charset(Charsets.UTF_8).send().readToText();
            Date responseTime = new Date();
            //接口调用日志
            MessageLog messageLog = new MessageLog();
            messageLog.setRequestTime(requestTime);
            messageLog.setResponseTime(responseTime);
            messageLog.setIp("");
            messageLog.setUrl(path);
            messageLog.setParameter("");
            messageLog.setResult(response);
            if (StringUtils.isBlank(response)) {
                messageLog.setStatus(MessageLogStatusEnum.SUCCESS.getValue());
                messageLogCrudService.insert(messageLog);
                return resultDto.failed();
            }
            JSONObject json = JSONObject.parseObject(response);
            LicenseRecognitionFaceResultDto licenseRecognitionFaceResultDto = packLicenseFaceResultDto(json);
            if (licenseRecognitionFaceResultDto.getSuccess()) {
                resultDto.setData(licenseRecognitionFaceResultDto);
                messageLog.setStatus(MessageLogStatusEnum.SUCCESS.getValue());
                messageLogCrudService.insert(messageLog);
                resultDto.success();
            } else {
                messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                messageLogCrudService.insert(messageLog);
                resultDto.makeResult(ToolResultEnum.LICENSE_RECOGNITION_FAIL.getValue(), ToolResultEnum.LICENSE_RECOGNITION_FAIL.getComment());
            }
            messageLogCrudService.insert(messageLog);

        } catch (Exception e) {
            log.error("行驶证正面识别失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 封装LicenseFaceResultDto
     *
     * @param json json
     * @return LicenseRecognitionFaceResultDto
     */
    private LicenseRecognitionFaceResultDto packLicenseFaceResultDto(JSONObject json) throws Exception {
        LicenseRecognitionFaceResultDto licenseRecognitionFaceResultDto = new LicenseRecognitionFaceResultDto();

        String plateNumber = json.getString("plate_num");
        licenseRecognitionFaceResultDto.setPlateNumber(plateNumber);
        String vehicleType = json.getString("vehicle_type");
        licenseRecognitionFaceResultDto.setVehicleType(vehicleType);
        String ownerName = json.getString("owner");
        licenseRecognitionFaceResultDto.setOwnerName(ownerName);
        String useCharacter = json.getString("use_character");
        licenseRecognitionFaceResultDto.setUseCharacter(useCharacter);
        String address = json.getString("addr");
        licenseRecognitionFaceResultDto.setAddress(address);
        String model = json.getString("model");
        licenseRecognitionFaceResultDto.setModel(model);
        String vin = json.getString("vin");
        licenseRecognitionFaceResultDto.setVin(vin);
        String engineNumber = json.getString("engine_num");
        licenseRecognitionFaceResultDto.setEngineNumber(engineNumber);
        if (StringUtils.isNotEmpty(json.getString("register_date"))) {
            Date parse = DateUtils.parseDate(json.getString("register_date"), "yyyyMMdd");
            licenseRecognitionFaceResultDto.setRegisterDate(parse);
        }
        String issueDate = json.getString("issue_date");
        licenseRecognitionFaceResultDto.setIssueDate(issueDate);
        String requestId = json.getString("request_id");
        licenseRecognitionFaceResultDto.setRequestId(requestId);
        Boolean success = json.getBoolean("success");
        licenseRecognitionFaceResultDto.setSuccess(success);
        if (StringUtils.isEmpty(vehicleType) || StringUtils.isEmpty(plateNumber) || licenseRecognitionFaceResultDto.getRegisterDate() == null || StringUtils.isEmpty(engineNumber)) {
            licenseRecognitionFaceResultDto.setSuccess(false);
        }
        licenseRecognitionFaceResultDto.setProsAndCons(ProsAndConsEnum.PROS.getValue());
        return licenseRecognitionFaceResultDto;
    }

    /**
     * 行驶证背面识别
     *
     * @param inputStream inputStream
     * @return LicenseRecognitionBackResultDto
     */
    @Override
    public ObjectResultDto<LicenseRecognitionBackResultDto> licenseRecognitionByBack(InputStream inputStream) {
        ObjectResultDto<LicenseRecognitionBackResultDto> resultDto = new ObjectResultDto<>();
        try {
            String path = aliYunConfig.getLicenseUrl();
            String appcode = aliYunConfig.getLicenseAppcode();
            Map<String, String> headers = new HashMap<>(16);
            headers.put("Authorization", "APPCODE " + appcode);
            headers.put("Content-Type", "application/json; charset=UTF-8");
            if (null == inputStream) {
                return resultDto.makeResult(ToolResultEnum.IMAGE_EMPTY.getValue(), ToolResultEnum.IMAGE_EMPTY.getComment());
            }
            RecognitionBean recgnitionBean = new RecognitionBean();
            String imageToBase = ImageBase64.getImageStrFromUrl(inputStream);
            recgnitionBean.setImage(imageToBase);
            recgnitionBean.setConfigure(new ProsAndCons(ProsAndConsEnum.CONS.getValue()));
            String param = JSON.toJSONString(recgnitionBean);
            Date requestTime = DateUtils.now();
            String response = Requests.post(path).headers(headers).body(param).charset(Charsets.UTF_8).send().readToText();
            Date responseTime = DateUtils.now();
            //接口调用日志
            MessageLog messageLog = new MessageLog();
            messageLog.setRequestTime(requestTime);
            messageLog.setResponseTime(responseTime);
            messageLog.setIp("");
            messageLog.setUrl(path);
            messageLog.setParameter("");
            messageLog.setResult(response);
            if (StringUtils.isBlank(response)) {
                messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                messageLogCrudService.insert(messageLog);
                return resultDto.failed();
            }
            JSONObject json = JSONObject.parseObject(response);
            LicenseRecognitionBackResultDto licenseRecognitionBackResultDto = packLicenseBackResultDto(json);
            if (licenseRecognitionBackResultDto.getSuccess()) {
                messageLog.setStatus(MessageLogStatusEnum.SUCCESS.getValue());
                messageLogCrudService.insert(messageLog);
                resultDto.setData(licenseRecognitionBackResultDto);
                resultDto.success();
            } else {
                messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                messageLogCrudService.insert(messageLog);
                return resultDto.makeResult(ToolResultEnum.LICENSE_RECOGNITION_FAIL.getValue(), ToolResultEnum.LICENSE_RECOGNITION_FAIL.getComment());
            }
        } catch (Exception e) {
            log.error("行驶证背面识别失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 封装 LicenseBackResultDto
     *
     * @param json json
     * @return LicenseRecognitionBackResultDto
     */
    private LicenseRecognitionBackResultDto packLicenseBackResultDto(JSONObject json) {
        LicenseRecognitionBackResultDto licenseRecognitionBackResultDto = new LicenseRecognitionBackResultDto();
        String approvedPassengerCapacity = json.getString("appproved_passenger_capacity");
        String approvedLoad = json.getString("approved_load");
        String fileNo = json.getString("file_no");
        String grossMass = json.getString("gross_mass");
        String inspectionRecord = json.getString("inspection_record");
        String overallDimension = json.getString("overall_dimension");
        String tractionMass = json.getString("traction_mass");
        String unladenMass = json.getString("unladen_mass");
        String plateNumber = json.getString("plate_num");
        Boolean success = json.getBoolean("success");
        String requestId = json.getString("request_id");
        licenseRecognitionBackResultDto.setSuccess(success);
        if (StringUtils.isBlank(plateNumber)) {
            licenseRecognitionBackResultDto.setSuccess(false);
        }
        licenseRecognitionBackResultDto.setApprovedPassengerCapacity(approvedPassengerCapacity);
        licenseRecognitionBackResultDto.setApprovedLoad(approvedLoad);
        licenseRecognitionBackResultDto.setFileNo(fileNo);
        licenseRecognitionBackResultDto.setGrossMass(grossMass);
        licenseRecognitionBackResultDto.setInspectionRecord(inspectionRecord);
        licenseRecognitionBackResultDto.setOverallDimension(overallDimension);
        licenseRecognitionBackResultDto.setTractionMass(tractionMass);
        licenseRecognitionBackResultDto.setUnladenMass(unladenMass);
        licenseRecognitionBackResultDto.setPlateNumber(plateNumber);
        licenseRecognitionBackResultDto.setRequestId(requestId);
        licenseRecognitionBackResultDto.setProsAndCons(ProsAndConsEnum.CONS.getValue());
        return licenseRecognitionBackResultDto;
    }
}
