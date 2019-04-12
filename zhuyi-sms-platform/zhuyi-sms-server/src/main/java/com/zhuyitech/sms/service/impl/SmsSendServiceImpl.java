package com.zhuyitech.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.common.utils.TimeUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.shiro.redis.RedisConnector;
import com.scapegoat.infrastructure.spring.utils.SpringContextUtils;
import com.zhuyitech.sms.cache.CacheKeyGenerator;
import com.zhuyitech.sms.domain.SmsClient;
import com.zhuyitech.sms.domain.SmsContent;
import com.zhuyitech.sms.domain.SmsTemplate;
import com.zhuyitech.sms.domain.SmsVerifyCode;
import com.zhuyitech.sms.dto.request.*;
import com.zhuyitech.sms.enums.SmsResultCodeEnum;
import com.zhuyitech.sms.mapper.SmsContentMapper;
import com.zhuyitech.sms.mapper.SmsVerifyCodeMapper;
import com.zhuyitech.sms.serivce.SmsClientCrudService;
import com.zhuyitech.sms.serivce.SmsTemplateCrudService;
import com.zhuyitech.sms.service.api.SmsSendProxyService;
import com.zhuyitech.sms.service.api.SmsSendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 短信发送服务类
 *
 * @author walkman
 */
@Service("smsSendService")
@Slf4j
public class SmsSendServiceImpl implements SmsSendService {

    @Autowired
    private SmsTemplateCrudService smsTemplateCrudService;

    @Autowired
    private SmsClientCrudService smsClientCrudService;

    @Autowired
    private SmsContentMapper smsContentMapper;

    @Autowired
    private SmsVerifyCodeMapper smsVerifyCodeDao;

    /**
     * 发送通知类短信
     */
    @Override
    public ObjectResultDto<SmsSendResultDto> sendSms(MessageSendRequestDto requestDto) {
        // 返回的结果对象
        ObjectResultDto<SmsSendResultDto> resultDto = new ObjectResultDto<>();
        // 判断发送的内容参数是否为空
        if (null == requestDto.getParameters() || requestDto.getParameters().isEmpty()) {
            return resultDto.failed(SmsResultCodeEnum.ATTRIBUTE_IS_NULL.getMessage());
        }
        // 手机号码校验
        ResultDto verifyMobilePhone = verifyMobilePhone(requestDto.getPhoneNumber());
        if (verifyMobilePhone.isFailed()) {
            return resultDto.makeResult(verifyMobilePhone.getCode(), verifyMobilePhone.getMessage());
        }
        //短信客户端
        SmsClient smsClient = getSmsClient(requestDto.getClientId());
        if (null == smsClient) {
            return resultDto.makeResult(SmsResultCodeEnum.CLIENT_NO_NOT_EXIST.getCode(),
                    SmsResultCodeEnum.CLIENT_NO_NOT_EXIST.getMessage());
        }
        // 短信模版
        SmsTemplate smsTemplate = getSmsTemplate(requestDto.getClientId(), requestDto.getTemplateId());
        if (null == smsTemplate) {
            return resultDto.makeResult(SmsResultCodeEnum.TEMPLATE_NOT_EXIST.getCode(),
                    SmsResultCodeEnum.TEMPLATE_NOT_EXIST.getMessage());
        }
        try {
            // 渲染模板引擎参数，转换为的正式SMS。并且校验参数是否完全转换，若无则返回失败
            String smsMessage = StrSubstitutor.replace(smsTemplate.getContent(), requestDto.getParameters());
            if (smsMessage.contains("${")) {
                log.error("短信参数和短信模版中需要替换的参数匹配!");
                return resultDto.failed();
            }
            // 调用公用的短信发送接口
            resultDto = sendBaseSms(
                    smsClient.getChannelProvider(),
                    requestDto.getTemplateId(),
                    requestDto.getPhoneNumber(),
                    smsMessage,
                    smsTemplate.getSignName(),
                    requestDto.getParameters());

            // 插入短信存储对象到数据库中
            SmsContent smsContent = buildSmsContent(requestDto, smsMessage, resultDto.getCode(), resultDto.getMessage());
            smsContentMapper.insert(smsContent);
            // 判断短信发送是否成功还是是失败
            if (resultDto.isFailed()) {
                return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                        SmsResultCodeEnum.SMS_SEND_FAIL.getMessage());
            }
            return resultDto.success();
        } catch (Exception e) {
            log.error("短信发送异常", e);
            return resultDto.failed();
        }
    }

    /**
     * 发送验证码类短信
     *
     * @param requestDto 发送参数
     */
    @Override
    public ResultDto sendVerifySms(MessageSendRequestDto requestDto) {
        // 手机号码校验
        ResultDto resultDto = new ResultDto();
        ResultDto resultEx = this.verifyMobilePhone(requestDto.getPhoneNumber());
        if (resultEx.isFailed()) {
            return resultDto.makeResult(resultEx.getCode(), resultEx.getMessage());
        }
        try {
            // 随机6位验证码
            String verifyCode = createCode();
            // 对验证码对应的Key
            String verifyKey = CacheKeyGenerator.generateVerifyCodeCacheKey(requestDto.getClientId(), requestDto.getPhoneNumber());
            // 用户当天的短信信息Key
            String phoneSmsCacheKey = CacheKeyGenerator.generateVerifyCodeInfoCacheKey(requestDto.getClientId(), requestDto.getPhoneNumber());
            SmsSendInfo info = this.getSmsSendInfoFromRedis(phoneSmsCacheKey);
            SmsCheckInfo smsCheckCode = new SmsCheckInfo();
            if (null == info) {
                // 发送验证码
                ObjectResultDto<SmsSendResultDto> objectResultDto = this.sendBaseVerifyCode(requestDto, verifyCode);
                if (objectResultDto.isFailed()) {
                    return resultDto.failed(objectResultDto.getMessage());
                }
                SmsSendResultDto sendResultDto = objectResultDto.getData();
                // 加一个新的redis的key，值为验证码(有效期10分钟)
                smsCheckCode.setCheckCount(0);
                smsCheckCode.setCode(verifyCode);
                smsCheckCode.setSmsRequestId(sendResultDto.getRequestId());
                this.setSmsCheckCodeToRedis(verifyKey, smsCheckCode);
                // 更新限定机制key
                info = new SmsSendInfo();
                info.setLastTime(System.currentTimeMillis());
                info.setPhoneNumber(requestDto.getPhoneNumber());
                info.setFailedCount(0);
                info.setSendCount(1);
                setSmsSendInfoToRedis(phoneSmsCacheKey, info);
                resultDto = objectResultDto;
            } else {
                // 一分钟内只能调用一次
                if (System.currentTimeMillis() - info.getLastTime() < TimeUtils.MILLIS_OF_MINUTE) {
                    return resultDto.failed("请您一分钟以后再试!");
                }
                // 每天发送总数20次
                if (info.getSendCount() >= 20) {
                    return resultDto.failed(SmsResultCodeEnum.MSG_FAIL_IP_SEND_OUT_RANGE.getMessage());
                }
                // 发送验证码
                ObjectResultDto<SmsSendResultDto> objectResultDto = this.sendBaseVerifyCode(requestDto, verifyCode);
                if (objectResultDto.isFailed()) {
                    return resultDto.failed(objectResultDto.getMessage());
                }
                // 更新验证码
                smsCheckCode.setCheckCount(0);
                smsCheckCode.setCode(verifyCode);
                smsCheckCode.setSmsRequestId(objectResultDto.getData().getRequestId());
                this.setSmsCheckCodeToRedis(verifyKey, smsCheckCode);
                // 上线机制更新
                info.setLastTime(System.currentTimeMillis());
                info.setSendCount(info.getSendCount() + 1);
                info.setFailedCount(0);
                setSmsSendInfoToRedis(phoneSmsCacheKey, info);
                resultDto = objectResultDto;
            }
            return resultDto.success();
        } catch (Exception e) {
            log.error("验证码发送异常", e);
            return resultDto.failed();
        }
    }

    /**
     * 验证手机号码规则是否符合
     */
    public boolean isMobilePhone(String mobilePhone) {

        String phoneNumberReg = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        Pattern pattern = Pattern.compile(phoneNumberReg);
        Matcher matcher = pattern.matcher(mobilePhone);
        return matcher.matches();
    }

    /**
     * 对手机号的校验
     */
    private ResultDto verifyMobilePhone(String mobilePhone) {
        ResultDto resultEx = new ResultDto();
        // step1：校验手机号是否为空
        if (StringUtils.isEmpty(mobilePhone)) {
            return resultEx.failed(SmsResultCodeEnum.MSG_FAIL_PHONE_NULL.getMessage());
        }
        // step2：校验手机号码长度
        if (11 != mobilePhone.length()) {
            return resultEx.failed(SmsResultCodeEnum.MSG_FAIL_LENGTH.getMessage());
        }
        // step3：校验手机号码格式是否符合规则
        if (!isMobilePhone(mobilePhone)) {
            return resultEx.failed(SmsResultCodeEnum.MSG_FAIL_RULE.getMessage());
        }
        return resultEx.success();
    }

    /**
     * 查询短信的模版
     */
    private SmsTemplate getSmsTemplate(String clientId, String templateId) {
        if (StringUtils.isEmpty(clientId)) {
            log.error(SmsResultCodeEnum.CLIENT_NO_UNDEFINED.getMessage());
            return null;
        }
        // 判断模版编号是否指定
        if (StringUtils.isEmpty(templateId)) {
            log.error(SmsResultCodeEnum.TEMPLATE_UNDEFINED.getMessage());
            return null;
        }
        // 查询短信模版
        SmsTemplate smsTemplate = smsTemplateCrudService.getByTemplateId(clientId, templateId);
        if (null == smsTemplate) {
            log.error(SmsResultCodeEnum.TEMPLATE_NOT_EXIST.getMessage());
            return null;
        }
        return smsTemplate;
    }

    /**
     * 查询商户信息，根据不同的商户信息，操作不同的短信服务
     */
    private SmsClient getSmsClient(String clientId) {
        // 判断商户号是否指定
        if (StringUtils.isEmpty(clientId)) {
            log.error(SmsResultCodeEnum.CLIENT_NO_UNDEFINED.getMessage());
            return null;
        }
        SmsClient smsClient = smsClientCrudService.getByClientId(clientId);
        if (null == smsClient) {
            log.error(SmsResultCodeEnum.CLIENT_NO_NOT_EXIST.getMessage());
        }
        return smsClient;
    }

    /**
     * 发送短信的公共方法
     *
     * @param phoneNumber 手机号码
     * @param templateId  商户编号
     * @param message     需要发送的信息(验证码、其他需要发送的信息)
     */
    private ObjectResultDto<SmsSendResultDto> sendBaseSms(String channelProvider, String templateId, String phoneNumber, String message, String signName, Map<String, String> paramMap) {
        ObjectResultDto<SmsSendResultDto> resultDto = new ObjectResultDto<>();
        try {

            // 判断通道信息是否存在
            if (StringUtils.isEmpty(channelProvider)) {
                return resultDto.makeResult(SmsResultCodeEnum.SMS_CHANNEL_NOT_EXIST.getCode(),
                        SmsResultCodeEnum.SMS_CHANNEL_NOT_EXIST.getMessage()
                );
            }
            // 获取发送短信的通道
            SmsSendProxyService smsSendProxyService = SpringContextUtils.getBean(channelProvider, SmsSendProxyService.class);
            if (null == smsSendProxyService) {
                log.error("短信的发送通道获取失败!");
                return resultDto.makeResult(SmsResultCodeEnum.SMS_CHANNEL_GET_FAIL.getCode(),
                        SmsResultCodeEnum.SMS_CHANNEL_GET_FAIL.getMessage()
                );
            }
            SmsSendRequestDto sendParameter = new SmsSendRequestDto();
            sendParameter.setPhoneNumber(phoneNumber);
            sendParameter.setContent(message);
            sendParameter.setSignName(signName);
            sendParameter.setTemplateCode(templateId);
            if (paramMap != null && !paramMap.isEmpty()) {
                sendParameter.setParamString(JSON.toJSONString(paramMap));
            }
            ResultDto validateDto = smsSendProxyService.validateParameter(sendParameter);
            if (validateDto.isFailed()) {
                log.error("短信发送参数错误，发送失败!");
                return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                        SmsResultCodeEnum.SMS_SEND_FAIL.getMessage());
            }
            // 调用第三方的通道
            resultDto = smsSendProxyService.sendMessage(sendParameter);
            return resultDto;
        } catch (Exception e) {
            log.error("短信发送异常", e);
            return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                    SmsResultCodeEnum.SMS_SEND_FAIL.getMessage()
            );
        }
    }

    /**
     * 构建短信存储对象
     */
    private SmsContent buildSmsContent(MessageSendRequestDto parameter, String msg, Integer status, String remark) {
        SmsContent smsContent = new SmsContent();
        // 设置商户编号
        smsContent.setClientId(parameter.getClientId());
        // 设置短信模版编号
        smsContent.setTemplateId(parameter.getTemplateId());
        // 短信发送的手机号码
        smsContent.setPhoneNumber(parameter.getPhoneNumber());
        // 短信发送的内容
        smsContent.setContent(msg);
        // 短信发送状态 (1-发送成功，2-发送失败)
        smsContent.setStatus(status);
        // 发送失败的报文备注
        if (!StringUtils.isEmpty(remark)) {
            smsContent.setDescription(remark);
        }
        return smsContent;
    }

    /**
     * 从缓存中获取短信验证码的信息
     */
    private SmsSendInfo getSmsSendInfoFromRedis(String key) {
        String data = RedisConnector.get(key);
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        return JSON.parseObject(data, SmsSendInfo.class);
    }

    /**
     * 从缓存中获取短信验证码的信息
     *
     * @param key
     * @return
     */
    private SmsCheckInfo getSmsCheckInfoFromRedis(String key) {
        String data = RedisConnector.get(key);
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        return JSON.parseObject(data, SmsCheckInfo.class);
    }

    /**
     * 设置短信验证码信息到缓存中
     */
    private void setSmsSendInfoToRedis(String key, SmsSendInfo info) {
        String data = JSON.toJSONString(info);
        RedisConnector.set(key, TimeUtils.MILLIS_OF_DAY, data);
    }

    /**
     * 设置短信验证码缓存
     */
    private void setSmsCheckCodeToRedis(String key, SmsCheckInfo smsCheckCode) {
        String data = JSON.toJSONString(smsCheckCode);
        RedisConnector.set(key, 10 * TimeUtils.MILLIS_OF_MINUTE, data);
    }

    /**
     * 更新短信验证码缓存
     *
     * @param key
     * @param info
     */
    private void updateSmsSendInfoToRedis(String key, SmsCheckInfo info) {
        ShardedJedis shardedJedis = RedisConnector.getResource();
        try {
            String data = JSON.toJSONString(info);
            if (RedisConnector.exists(key)) {
                Long ttl = shardedJedis.pttl(key);
                RedisConnector.set(key, ttl, data);
            }
        } finally {
            RedisConnector.close(shardedJedis);
        }
    }

    /**
     * 随机生成6位数验证码
     */
    private String createCode() {
        return StringUtils.getRandomCode(6);
    }

    /**
     * 发送验证码
     */
    private ObjectResultDto<SmsSendResultDto> sendBaseVerifyCode(MessageSendRequestDto requestDto, String verifyCode) {
        ObjectResultDto<SmsSendResultDto> resultDto = new ObjectResultDto();
        //短信客户端
        SmsClient smsClient = getSmsClient(requestDto.getClientId());
        if (null == smsClient) {
            return resultDto.failed(SmsResultCodeEnum.CLIENT_NO_NOT_EXIST.getMessage());
        }
        // 短信模版
        SmsTemplate smsTemplate = getSmsTemplate(requestDto.getClientId(), requestDto.getTemplateId());
        if (null == smsTemplate) {
            return resultDto.failed(SmsResultCodeEnum.TEMPLATE_NOT_EXIST.getMessage());
        }
        try {
            // 渲染模板引擎参数，转换为的正式SMS。并且校验参数是否完全转换，若无则返回失败
            // 生成验证码，并渲染模版
            String msg = smsTemplate.getContent().replace("${code}", verifyCode);
            String smsMessage = StrSubstitutor.replace(msg, requestDto.getParameters());
            //对于阿里云短信通道，需要设置{code}参数
            if (requestDto.getParameters() == null) {
                requestDto.setParameters(new HashMap<>());
            }
            if (requestDto.getParameters().isEmpty() || requestDto.getParameters().get("code") == null) {
                Map<String, String> parameter = requestDto.getParameters();
                parameter.put("code", verifyCode);
            }
            if (smsMessage.contains("${")) {
                log.error("短信参数和短信模版中需要替换的参数匹配!");
                return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                        SmsResultCodeEnum.SMS_SEND_FAIL.getMessage()
                );
            }
            // 调用公用的短信发送接口
            resultDto = sendBaseSms(
                    smsClient.getChannelProvider(),
                    requestDto.getTemplateId(),
                    requestDto.getPhoneNumber(),
                    smsMessage,
                    smsTemplate.getSignName(),
                    requestDto.getParameters());

            // 插入短信存储对象到数据库中
            SmsContent smsContent = buildSmsContent(requestDto, smsMessage, resultDto.getCode(), resultDto.getMessage());
            smsContentMapper.insert(smsContent);

            // 插入短信存储对象到数据库中
            SmsVerifyCode smsVerifyCode = this.buildSmsVerifyCode(requestDto, verifyCode, resultDto.getCode(), resultDto.getMessage());
            smsVerifyCodeDao.insert(smsVerifyCode);

            // 判断短信发送是否成功还是是失败
            if (resultDto.isFailed()) {
                return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                        SmsResultCodeEnum.SMS_SEND_FAIL.getMessage()
                );
            }
            return resultDto.success();
        } catch (Exception e) {
            log.error("验证码发送异常", e);
            return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                    SmsResultCodeEnum.SMS_SEND_FAIL.getMessage()
            );
        }
    }

    /**
     * 构建验证码存储对象
     *
     * @param verifyCode 验证码
     * @param remark     响应报文
     */
    private SmsVerifyCode buildSmsVerifyCode(MessageSendRequestDto requestDto, String verifyCode, Integer status, String remark) {
        SmsVerifyCode smsVerifyCode = new SmsVerifyCode();
        smsVerifyCode.setClientId(requestDto.getClientId());
        smsVerifyCode.setTemplateId(requestDto.getTemplateId());
        smsVerifyCode.setPhoneNumber(requestDto.getPhoneNumber());
        smsVerifyCode.setVerifyType(requestDto.getVerifyType());
        smsVerifyCode.setVerifyCode(verifyCode);
        // 短信发送状态 (1-发送成功，2-发送失败)
        smsVerifyCode.setStatus(status);
        // 发送失败的报文备注
        if (!StringUtils.isEmpty(remark)) {
            smsVerifyCode.setDescription(remark);
        }
        return smsVerifyCode;
    }

    /**
     * <pre>
     *   注意：
     *     通过数据库对验证码进行校验的仅用于测试
     * </pre>
     */
    @Override
    public ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode(VerifyCodeCheckRequestDto requestDto) {
        ObjectResultDto<VerifyCodeCheckResultDto> resultDto = new ObjectResultDto<>();
        VerifyCodeCheckResultDto verifyCodeCheckResultDto = new VerifyCodeCheckResultDto();
        // 对参数进行校验
        if (StringUtils.isEmpty(requestDto.getVerifyCode())
                || StringUtils.isEmpty(requestDto.getPhoneNumber())) {

            return resultDto.failed(SmsResultCodeEnum.PARAM_IS_NULL.getMessage());
        }
        //短信客户端
        SmsClient smsClient = getSmsClient(requestDto.getClientId());
        if (null == smsClient) {
            return resultDto.failed(SmsResultCodeEnum.CLIENT_NO_NOT_EXIST.getMessage());
        }
        // 对验证码对应的Key
        String verifyKey = CacheKeyGenerator.generateVerifyCodeCacheKey(requestDto.getClientId(), requestDto.getPhoneNumber());
        // 用户当天的短信信息Key
        String phoneSmsCacheKey = CacheKeyGenerator.generateVerifyCodeInfoCacheKey(requestDto.getClientId(), requestDto.getPhoneNumber());
        // 从redis中获取验证码信息
        SmsSendInfo smsSendInfo = this.getSmsSendInfoFromRedis(phoneSmsCacheKey);
        if (null == smsSendInfo) {
            verifyCodeCheckResultDto.setValid(false);
            resultDto.setData(verifyCodeCheckResultDto);
            return resultDto.makeResult(SmsResultCodeEnum.VERIFY_CODE_EXPIRED_ERROR.getCode(),
                    SmsResultCodeEnum.VERIFY_CODE_EXPIRED_ERROR.getMessage()
            );
        }
        // 判断验证码校验的次数
        if (smsSendInfo.getFailedCount() >= 5) {
            return resultDto.makeResult(SmsResultCodeEnum.VERIFY_CODE_CHECK_COUNT_FAIL.getCode(),
                    SmsResultCodeEnum.VERIFY_CODE_CHECK_COUNT_FAIL.getMessage()
            );
        }
        // 通过缓存进行校验
        // 查询验证码是否失效
        SmsCheckInfo smsCheckCode = this.getSmsCheckInfoFromRedis(verifyKey);
        if (smsCheckCode == null) {
            verifyCodeCheckResultDto.setValid(false);
            resultDto.setData(verifyCodeCheckResultDto);
            return resultDto.makeResult(SmsResultCodeEnum.VERIFY_CODE_EXPIRED_ERROR.getCode(),
                    SmsResultCodeEnum.VERIFY_CODE_EXPIRED_ERROR.getMessage()
            );
        }
        // 判断验证码是否正确
        if (!smsCheckCode.getCode().equals(requestDto.getVerifyCode())) {
            // 设置验证码失败的次数
            smsSendInfo.setFailedCount(smsSendInfo.getFailedCount() + 1);
            this.setSmsSendInfoToRedis(phoneSmsCacheKey, smsSendInfo);
            verifyCodeCheckResultDto.setValid(false);
            resultDto.setData(verifyCodeCheckResultDto);
            return resultDto.makeResult(SmsResultCodeEnum.VERIFY_CODE_CHECK_FAIL.getCode(),
                    SmsResultCodeEnum.VERIFY_CODE_CHECK_FAIL.getMessage()
            );
        }
        if (!StringUtils.isEmpty(requestDto.getSmsRequestId())) {
            //判断短信回执ID是否正确
            if (!smsCheckCode.getSmsRequestId().equals(requestDto.getSmsRequestId())) {
                // 设置验证码失败的次数
                smsSendInfo.setFailedCount(smsSendInfo.getFailedCount() + 1);
                this.setSmsSendInfoToRedis(phoneSmsCacheKey, smsSendInfo);
                verifyCodeCheckResultDto.setValid(false);
                resultDto.setData(verifyCodeCheckResultDto);
                return resultDto.makeResult(SmsResultCodeEnum.SMS_REQUEST_IF_CHECK_FAIL.getCode(),
                        SmsResultCodeEnum.SMS_REQUEST_IF_CHECK_FAIL.getMessage()
                );
            }
        }
        this.setSmsSendInfoToRedis(phoneSmsCacheKey, smsSendInfo);
        //更新校验次数
        smsCheckCode.setCheckCount(smsCheckCode.getCheckCount() + 1);
        //更新短信验证码验证信息
        updateSmsSendInfoToRedis(verifyKey, smsCheckCode);
        verifyCodeCheckResultDto.setValid(true);
        resultDto.setData(verifyCodeCheckResultDto);
        return resultDto.success();
    }

}