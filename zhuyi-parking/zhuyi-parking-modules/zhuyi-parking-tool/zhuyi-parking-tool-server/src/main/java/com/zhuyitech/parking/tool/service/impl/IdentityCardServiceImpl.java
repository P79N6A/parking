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
import com.zhuyitech.parking.tool.config.AuthzConfig;
import com.zhuyitech.parking.tool.domain.MessageLog;
import com.zhuyitech.parking.tool.dto.request.IdentityCardVerifyRequestDto;
import com.zhuyitech.parking.tool.dto.result.identity.*;
import com.zhuyitech.parking.tool.enums.IdCardPlanSelectEnum;
import com.zhuyitech.parking.tool.enums.MessageLogStatusEnum;
import com.zhuyitech.parking.tool.enums.ProsAndConsEnum;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.MessageLogCrudService;
import com.zhuyitech.parking.tool.service.api.IdentityCardService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证照片识别服务实现类
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
@Service("identityCardService")
@Slf4j
public class IdentityCardServiceImpl implements IdentityCardService {

    @Autowired
    private MessageLogCrudService messageLogCrudService;

    @Autowired
    private AliYunConfig aliYunConfig;

    @Autowired
    private AuthzConfig authzConfig;

    /**
     * 身份证识别正面
     *
     * @param inputStream inputStream
     * @return IdentityRecognitionFaceResultDto
     */
    @Override
    public ObjectResultDto<IdentityRecognitionFaceResultDto> identityRecognitionByFace(InputStream inputStream) {
        ObjectResultDto<IdentityRecognitionFaceResultDto> resultDto = new ObjectResultDto<>();
        IdentityRecognitionFaceResultDto identityRecognitionFaceResultDto = new IdentityRecognitionFaceResultDto();
        try {
            String path = aliYunConfig.getIdentityUrl();
            String appcode = aliYunConfig.getAppcode();
            Map<String, String> headers = new HashMap<>(16);
            headers.put("Authorization", "APPCODE " + appcode);
            headers.put("Content-Type", "application/json; charset=UTF-8");
            if (null == inputStream) {
                return resultDto.makeResult(ToolResultEnum.IMAGE_EMPTY.getValue(), ToolResultEnum.IMAGE_EMPTY.getComment());
            }
            RecognitionBean identityRecognitionBean = new RecognitionBean();
            String imageToBase = ImageBase64.getImageStrFromUrl(inputStream);
            identityRecognitionBean.setImage(imageToBase);
            identityRecognitionBean.setConfigure(new ProsAndCons(ProsAndConsEnum.PROS.getValue()));
            String param = JSON.toJSONString(identityRecognitionBean);
            Date requestTime = new Date();
            String response = Requests.post(path).headers(headers).body(param).charset(Charsets.UTF_8).send().readToText();
            Date responseTime = new Date();
            if (StringUtils.isBlank(response)) {
                return resultDto.failed();
            }
            JSONObject json = JSONObject.parseObject(response);
            String address = json.getString("address");
            String name = json.getString("name");
            String nationality = json.getString("nationality");
            String cardNo = json.getString("num");
            String sex = json.getString("sex");
            String birthday = json.getString("birth");
            Boolean success = json.getBoolean("success");
            identityRecognitionFaceResultDto.setAddress(address);
            if (!StringUtils.isEmpty(birthday)) {
                try {
                    birthday = DateUtils.formatDate(DateUtils.parseDate(birthday, "yyyyMMdd"), "yyyy-MM-dd");
                } catch (ParseException e) {
                    log.info("身份证正面识别生日失败" + e.getMessage());
                }
                identityRecognitionFaceResultDto.setBirthday(birthday);
            }
            identityRecognitionFaceResultDto.setCardNo(cardNo);
            identityRecognitionFaceResultDto.setName(name);
            identityRecognitionFaceResultDto.setNationality(nationality);
            identityRecognitionFaceResultDto.setPronsAndCons(ProsAndConsEnum.PROS.getValue());
            identityRecognitionFaceResultDto.setSex(sex);
            identityRecognitionFaceResultDto.setSuccess(success);

            //接口调用日志
            MessageLog messageLog = new MessageLog();
            messageLog.setRequestTime(requestTime);
            messageLog.setResponseTime(responseTime);
            messageLog.setIp("");
            messageLog.setUrl(path);
            messageLog.setParameter("");
            messageLog.setResult(JSON.toJSONString(response));
            if (success) {
                messageLog.setStatus(MessageLogStatusEnum.SUCCESS.getValue());
            } else {
                messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
            }
            messageLogCrudService.insert(messageLog);
            resultDto.setData(identityRecognitionFaceResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("身份证识别正面失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 身份证识别背面
     *
     * @param inputStream inputStream
     * @return IdentityRecognitionBackResultDto
     */
    @Override
    public ObjectResultDto<IdentityRecognitionBackResultDto> identityRecognitionByBack(InputStream inputStream) {

        ObjectResultDto<IdentityRecognitionBackResultDto> resultDto = new ObjectResultDto<>();
        if (null == inputStream) {
            return resultDto.makeResult(ToolResultEnum.IMAGE_EMPTY.getValue(), ToolResultEnum.IMAGE_EMPTY.getComment());
        }
        IdentityRecognitionBackResultDto identityRecognitionBackResultDto = new IdentityRecognitionBackResultDto();
        try {
            String path = aliYunConfig.getIdentityUrl();
            String appcode = aliYunConfig.getAppcode();
            Map<String, String> headers = new HashMap<>(16);
            headers.put("Authorization", "APPCODE " + appcode);
            headers.put("Content-Type", "application/json; charset=UTF-8");

            RecognitionBean identityRecognitionBean = new RecognitionBean();
            String imageToBase = ImageBase64.getImageStrFromUrl(inputStream);
            identityRecognitionBean.setImage(imageToBase);
            identityRecognitionBean.setConfigure(new ProsAndCons(ProsAndConsEnum.CONS.getValue()));
            String param = JSON.toJSONString(identityRecognitionBean);
            Date requestTime = DateUtils.now();
            String response = Requests.post(path).headers(headers).body(param).charset(Charsets.UTF_8).send().readToText();
            Date responseTime = DateUtils.now();
            if (StringUtils.isBlank(response)) {
                return resultDto.failed();
            }
            JSONObject json = JSONObject.parseObject(response);
            String startDate = json.getString("start_date");
            String endDate = json.getString("end_date");
            String issue = json.getString("issue");
            Boolean success = json.getBoolean("success");
            identityRecognitionBackResultDto.setProsAndCons(ProsAndConsEnum.CONS.getValue());
            if (!StringUtils.isEmpty(startDate)) {
                try {
                    startDate = DateUtils.formatDate(DateUtils.parseDate(startDate, "yyyyMMdd"), "yyyy-MM-dd");
                } catch (ParseException e) {
                    log.info("身份证背面识别起效日期失败" + e.getMessage());
                }
                identityRecognitionBackResultDto.setStartDate(startDate);

            }
            if (!StringUtils.isEmpty(endDate)) {
                try {
                    endDate = DateUtils.formatDate(DateUtils.parseDate(endDate, "yyyyMMdd"), "yyyy-MM-dd");
                } catch (ParseException e) {
                    log.info("身份证背面识别截止日期失败" + e.getMessage());
                }
                identityRecognitionBackResultDto.setEndDate(endDate);
            }
            //接口调用日志
            MessageLog messageLog = new MessageLog();
            messageLog.setRequestTime(requestTime);
            messageLog.setResponseTime(responseTime);
            messageLog.setIp("");
            messageLog.setUrl(path);
            messageLog.setParameter("");
            messageLog.setResult(JSON.toJSONString(response));
            if (success) {
                messageLog.setStatus(MessageLogStatusEnum.SUCCESS.getValue());
            } else {
                messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
            }
            messageLogCrudService.insert(messageLog);
            identityRecognitionBackResultDto.setIssue(issue);
            identityRecognitionBackResultDto.setSuccess(success);
            resultDto.setData(identityRecognitionBackResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("身份证识别背面失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户身份信息第三方认证
     */
    @Override
    public ObjectResultDto<IdentityCardVerifyResultDto> verifyIdentityCard(IdentityCardVerifyRequestDto requestDto) {

        ObjectResultDto<IdentityCardVerifyResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            String path = authzConfig.getIdcardverifyUrl();
            Map<String, Object> params = new HashMap<>();
            params.put("appkey", authzConfig.getAppkey());
            params.put("realname", requestDto.getRealName());
            params.put("idcard", requestDto.getCardNo());
            Date requestTime = DateUtils.now();
            String response = Requests.post(path).verify(false).followRedirect(false).
                    forms(params).charset(Charset.forName("UTF-8")).send().readToText();
            Date responseTime = DateUtils.now();
            //接口调用日志
            MessageLog messageLog = new MessageLog();
            messageLog.setRequestTime(requestTime);
            messageLog.setResponseTime(responseTime);
            messageLog.setIp("");
            messageLog.setUrl(path);
            messageLog.setParameter("");
            messageLog.setResult(JSON.toJSONString(response));
            if (StringUtils.isEmpty(response)) {
                messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                messageLogCrudService.insert(messageLog);
                return objectResultDto.failed();
            } else {
                JSONObject json = JSONObject.parseObject(response);
                if (json.getInteger("status") != 0) {
                    messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                    messageLogCrudService.insert(messageLog);
                    return objectResultDto.failed(json.getString("msg"));
                } else {
                    JSONObject resultObject = json.getJSONObject("result");
                    Integer verifystatus = resultObject.getInteger("verifystatus");
                    String verifymsg = resultObject.getString("verifymsg");
                    IdentityCardVerifyResultDto userAuthResultDto = new IdentityCardVerifyResultDto();
                    userAuthResultDto.setVerifyStatus(verifystatus);
                    userAuthResultDto.setVerifyMsg(verifymsg);
                    objectResultDto.setData(userAuthResultDto);
                    objectResultDto.success();
                    messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                    messageLogCrudService.insert(messageLog);
                }
            }

        } catch (Exception e) {
            log.error("用户身份信息第三方认证失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 阿里云身份证二要素校验一
     */
    @Override
    public ObjectResultDto<IdCardCertResultDto> verityIdCardCert(IdentityCardVerifyRequestDto requestDto) {
        ObjectResultDto<IdCardCertResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            String path = aliYunConfig.getIdCardCertUrl();
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "APPCODE " + aliYunConfig.getAppcode());
            headers.put("Content-Type", "application/json; charset=UTF-8");
            Map<String, Object> params = new HashMap<>();
            params.put("idCard", requestDto.getCardNo());
            params.put("name", requestDto.getRealName());
            Date requestTime = DateUtils.now();
            String response = Requests.get(path).headers(headers).params(params).charset(Charsets.UTF_8).send().readToText();
            Date responseTime = DateUtils.now();
            IdCardCertResultDto idCardCertResultDto = new IdCardCertResultDto();
            //接口调用日志
            MessageLog messageLog = new MessageLog();
            messageLog.setRequestTime(requestTime);
            messageLog.setResponseTime(responseTime);
            messageLog.setIp("");
            messageLog.setUrl(path);
            messageLog.setParameter("");
            messageLog.setResult(JSON.toJSONString(response));
            if (StringUtils.isEmpty(response)) {
                messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                messageLogCrudService.insert(messageLog);
                return objectResultDto.failed();
            } else {
                JSONObject jsonObject = JSONObject.parseObject(response);
                String status = jsonObject.getString("status");
                String msg = jsonObject.getString("msg");
                if (!status.equals("01")) {
                    messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                    messageLogCrudService.insert(messageLog);
                    idCardCertResultDto.setStatus(Boolean.FALSE);
                    idCardCertResultDto.setMsg(msg);
                    objectResultDto.setData(idCardCertResultDto);
                    objectResultDto.success();
                    return objectResultDto;
                } else {
                    String idCard = jsonObject.getString("idCard");
                    String name = jsonObject.getString("name");
                    String sex = jsonObject.getString("sex");
                    String area = jsonObject.getString("area");
                    String province = jsonObject.getString("province");
                    String prefecture = jsonObject.getString("prefecture");
                    String birthday = jsonObject.getString("birthday");
                    String addCode = jsonObject.getString("addrCode");
                    String city = jsonObject.getString("city");
                    String lastCode = jsonObject.getString("lastCode");
                    idCardCertResultDto.setStatus(Boolean.TRUE);
                    idCardCertResultDto.setMsg(msg);
                    idCardCertResultDto.setAddCode(addCode);
                    idCardCertResultDto.setArea(area);
                    idCardCertResultDto.setBirthday(birthday);
                    idCardCertResultDto.setCity(city);
                    idCardCertResultDto.setLastCode(lastCode);
                    idCardCertResultDto.setPrefecture(prefecture);
                    idCardCertResultDto.setProvince(province);
                    idCardCertResultDto.setSex(sex);
                    idCardCertResultDto.setName(name);
                    idCardCertResultDto.setIdCard(idCard);
                    objectResultDto.setData(idCardCertResultDto);
                    messageLog.setStatus(MessageLogStatusEnum.SUCCESS.getValue());
                    messageLogCrudService.insert(messageLog);
                }
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("阿里云身份证二要素校验一失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 阿里云身份证二要素校验二
     *
     * @param requestDto IdentityCardVerifyRequestDto
     * @return IdCardResultDto
     */
    @Override
    public ObjectResultDto<IdCardResultDto> verityIdCard(IdentityCardVerifyRequestDto requestDto) {
        ObjectResultDto<IdCardResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            String path = aliYunConfig.getIdCardUrl();
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "APPCODE " + aliYunConfig.getAppcode());
            headers.put("Content-Type", "application/json; charset=UTF-8");
            Map<String, Object> params = new HashMap<>();
            params.put("cardNo", requestDto.getCardNo());
            params.put("realName", requestDto.getRealName());
            Date requestTime = DateUtils.now();
            String response = Requests.post(path).headers(headers).params(params).charset(Charsets.UTF_8).send().readToText();
            Date responseTime = DateUtils.now();
            //接口调用日志
            MessageLog messageLog = new MessageLog();
            messageLog.setRequestTime(requestTime);
            messageLog.setResponseTime(responseTime);
            messageLog.setIp("");
            messageLog.setUrl(path);
            messageLog.setParameter("");
            messageLog.setResult(JSON.toJSONString(response));
            IdCardResultDto idCardResultDto = new IdCardResultDto();
            if (StringUtils.isEmpty(response)) {
                messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                messageLogCrudService.insert(messageLog);
                return objectResultDto.failed();
            } else {
                JSONObject jsonObject = JSONObject.parseObject(response);
                int errorCode = jsonObject.getIntValue("error_code");
                String reason = jsonObject.getString("reason");
                if (errorCode != 0 || !reason.equals("一致")) {
                    messageLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
                    messageLogCrudService.insert(messageLog);
                    return objectResultDto.failed(reason);
                } else {
                    idCardResultDto.setErrorCode(errorCode);
                    idCardResultDto.setReason(reason);
                    JSONObject result = jsonObject.getJSONObject("result");
                    String realName = result.getString("realName");
                    String cardNo = result.getString("cardNo");
                    JSONObject details = result.getJSONObject("details");
                    String birth = details.getString("birth");
                    Integer sex = details.getIntValue("sex");
                    String address = details.getString("address");
                    idCardResultDto.setIdCard(cardNo);
                    idCardResultDto.setBirth(birth);
                    idCardResultDto.setSex(sex);
                    idCardResultDto.setRealName(realName);
                    idCardResultDto.setAddress(address);
                    messageLog.setStatus(MessageLogStatusEnum.SUCCESS.getValue());
                    messageLogCrudService.insert(messageLog);
                }
            }
            objectResultDto.setData(idCardResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("阿里云身份证二要素校验一失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 阿里云身份证二要素校验
     *
     * @param requestDto IdentityCardVerifyRequestDto
     * @return IdentityCardVerifyResultDto
     */
    @Override
    public ObjectResultDto<IdentityCardVerifyResultDto> verifyIdCardSelect(IdentityCardVerifyRequestDto requestDto) {
        ObjectResultDto<IdentityCardVerifyResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (aliYunConfig.getPlanSelect().equals(IdCardPlanSelectEnum.JISHUAPI_IDCARD_VERIFY_URL.getValue())) {
                //极速身份证校验
                objectResultDto = verifyIdentityCard(requestDto);
            } else if (aliYunConfig.getPlanSelect().equals(IdCardPlanSelectEnum.ALIYUN_IDCARD_CERT_URL.getValue())) {
                //阿里云身份证二要素校验一
                ObjectResultDto<IdCardCertResultDto> idCardCertResultDtoObjectResultDto = verityIdCardCert(requestDto);
                IdentityCardVerifyResultDto identityCardVerifyResultDto = new IdentityCardVerifyResultDto();
                if (idCardCertResultDtoObjectResultDto.isFailed() || null == idCardCertResultDtoObjectResultDto.getData()) {
                    objectResultDto.failed(idCardCertResultDtoObjectResultDto.getMessage());
                } else {
                    IdCardCertResultDto data = idCardCertResultDtoObjectResultDto.getData();
                    if (data.getStatus()) {
                        //认证成功
                        identityCardVerifyResultDto.setVerifyStatus(0);
                        identityCardVerifyResultDto.setVerifyMsg(data.getMsg());
                    } else {
                        identityCardVerifyResultDto.setVerifyStatus(1);
                        identityCardVerifyResultDto.setVerifyMsg(data.getMsg());
                    }
                    objectResultDto.setData(identityCardVerifyResultDto);
                    objectResultDto.success();
                }
            } else if (aliYunConfig.getPlanSelect().equals(IdCardPlanSelectEnum.ALIYUN_IDCARD_URL.getValue())) {
                //阿里云身份证二要素校验二
                ObjectResultDto<IdCardResultDto> idCardResultDtoObjectResultDto = verityIdCard(requestDto);
                if (idCardResultDtoObjectResultDto.isFailed() || null == idCardResultDtoObjectResultDto.getData()) {
                    objectResultDto.failed(idCardResultDtoObjectResultDto.getMessage());
                } else {
                    IdentityCardVerifyResultDto identityCardVerifyResultDto = new IdentityCardVerifyResultDto();
                    IdCardResultDto data = idCardResultDtoObjectResultDto.getData();
                    if (data.getErrorCode() != 0) {
                        //认证失败
                        identityCardVerifyResultDto.setVerifyStatus(1);
                        identityCardVerifyResultDto.setVerifyMsg(data.getReason());
                    } else {
                        //认证成功
                        identityCardVerifyResultDto.setVerifyStatus(0);
                        identityCardVerifyResultDto.setVerifyMsg(data.getReason());
                    }
                    objectResultDto.setData(identityCardVerifyResultDto);
                    objectResultDto.success();
                }
            } else {
                return objectResultDto.failed();
            }
        } catch (Exception e) {
            log.error("阿里云身份证二要素校验一失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
