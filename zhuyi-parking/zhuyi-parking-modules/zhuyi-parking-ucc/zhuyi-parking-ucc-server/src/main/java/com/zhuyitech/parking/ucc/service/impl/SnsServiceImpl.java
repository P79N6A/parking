package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.pay.alipay.params.AlipayAuthSdkCodeGetParam;
import com.zhuyitech.parking.pay.alipay.params.AlipayGetAccessTokenParam;
import com.zhuyitech.parking.pay.alipay.params.AlipayGetUserInfoParam;
import com.zhuyitech.parking.pay.alipay.result.AlipayAuthSdkCodeGetResult;
import com.zhuyitech.parking.pay.alipay.result.AlipayGetAccessTokenResult;
import com.zhuyitech.parking.pay.alipay.result.AlipayGetUserInfoResult;
import com.zhuyitech.parking.pay.alipay.service.AliPayService;
import com.zhuyitech.parking.pay.wechat.params.WeChatCheckAccessTokenParams;
import com.zhuyitech.parking.pay.wechat.params.WeChatGetAccessTokenParams;
import com.zhuyitech.parking.pay.wechat.params.WeChatGetUserInfoParams;
import com.zhuyitech.parking.pay.wechat.params.WeChatUpdateAccessTokenParams;
import com.zhuyitech.parking.pay.wechat.result.WeChatAccessTokenIsValidResult;
import com.zhuyitech.parking.pay.wechat.result.WeChatGetAccessTokenResult;
import com.zhuyitech.parking.pay.wechat.result.WeChatGetUserInfoResult;
import com.zhuyitech.parking.pay.wechat.result.WeChatUpdateAccessTokenResult;
import com.zhuyitech.parking.pay.wechat.service.WeChatPayService;
import com.zhuyitech.parking.ucc.domain.AlipayAccessToken;
import com.zhuyitech.parking.ucc.domain.WeixinAccessToken;
import com.zhuyitech.parking.ucc.dto.result.user.AlipayGetAccessTokenResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.AlipayGetUserInfoResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.WeixinGetAccessTokenResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.WeixinGetUserInfoResultDto;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.service.AlipayAccessTokenCrudService;
import com.zhuyitech.parking.ucc.service.WeixinAccessTokenCrudService;
import com.zhuyitech.parking.ucc.account.SnsService;
import com.zhuyitech.parking.ucc.account.request.AlipayAuthCodeGetRequestDto;
import com.zhuyitech.parking.ucc.account.request.AlipayGetAccessTokenRequestDto;
import com.zhuyitech.parking.ucc.account.request.AlipayGetUserInfoRequestDto;
import com.zhuyitech.parking.ucc.account.result.AlipayAuthCodeResultDto;
import com.zhuyitech.parking.ucc.account.result.WeixinGetAccessTokenRequestDto;
import com.zhuyitech.parking.ucc.account.result.WeixinGetUserInfoRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("snsService")
@Slf4j
public class SnsServiceImpl implements SnsService {

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private AlipayAccessTokenCrudService alipayAccessTokenCrudService;

    @Autowired
    private WeChatPayService weChatPayService;

    @Autowired
    private WeixinAccessTokenCrudService weixinAccessTokenCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ObjectResultDto<AlipayAuthCodeResultDto> authSdkCodeGet(AlipayAuthCodeGetRequestDto requestDto) {
        ObjectResultDto<AlipayAuthCodeResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            AlipayAuthSdkCodeGetParam param = new AlipayAuthSdkCodeGetParam();
            ObjectResultDto<AlipayAuthSdkCodeGetResult> objResultDto = aliPayService.authSdkCodeGet(param);
            if (objResultDto.isFailed() || objResultDto.getData() == null) {
                return objectResultDto.makeResult(objResultDto.getCode(), objResultDto.getMessage());
            }
            AlipayAuthCodeResultDto resultDto = new AlipayAuthCodeResultDto();
            resultDto.setUrlNeeded(objResultDto.getData().getUrlNeeded());
            objectResultDto.setData(resultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取accessToken失败" + e.getMessage());
            objectResultDto.makeResult(UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getValue(), UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<AlipayGetAccessTokenResultDto> getAccessToken(AlipayGetAccessTokenRequestDto requestDto) {
        ObjectResultDto<AlipayGetAccessTokenResultDto> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getCode())) {
            return objectResultDto.makeResult(UCenterResultEnum.CODE_NOT_VAILD.getValue(), UCenterResultEnum.CODE_NOT_VAILD.getComment());
        }
        AlipayGetAccessTokenParam alipayGetAccessTokenParam = new AlipayGetAccessTokenParam();
        alipayGetAccessTokenParam.setCode(requestDto.getCode());
        try {
            ObjectResultDto<AlipayGetAccessTokenResult> objResultDto = aliPayService.getAccessToken(alipayGetAccessTokenParam);
            if (objResultDto.isFailed()) {
                return objectResultDto.makeResult(objResultDto.getCode(), objResultDto.getMessage());
            }
            AlipayGetAccessTokenResult alipayGetAccessTokenResult = objResultDto.getData();
            AlipayAccessToken alipayAccessToken = new AlipayAccessToken();
            alipayAccessToken.setAccessToken(alipayGetAccessTokenResult.getAccessToken());
            alipayAccessToken.setAlipayUserId(alipayGetAccessTokenResult.getAlipayUserId());
            alipayAccessToken.setRefreshToken(alipayGetAccessTokenResult.getRefreshToken());
            if (StringUtils.isNotEmpty(alipayGetAccessTokenResult.getTokenExpiredSeconds())) {
                alipayAccessToken.setTokenExpiredSeconds(Integer.parseInt(alipayGetAccessTokenResult.getTokenExpiredSeconds()));
            }
            if (StringUtils.isNotEmpty(alipayGetAccessTokenResult.getRefreshTokenExpiredSeconds())) {
                alipayAccessToken.setRefreshTokenExpiredSeconds(Integer.parseInt(alipayGetAccessTokenResult.getRefreshTokenExpiredSeconds()));
            }
            alipayAccessTokenCrudService.insert(alipayAccessToken);
            AlipayGetAccessTokenResultDto alipayGetAccessTokenResultDto = modelMapper.map(alipayAccessToken, AlipayGetAccessTokenResultDto.class);
            objectResultDto.setData(alipayGetAccessTokenResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取accessToken失败" + e.getMessage());
            objectResultDto.makeResult(UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getValue(), UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<AlipayGetUserInfoResultDto> getAlipayInfo(AlipayGetUserInfoRequestDto requestDto) {
        ObjectResultDto<AlipayGetUserInfoResultDto> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getAccessToken())) {
            objectResultDto.makeResult(UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getValue(), UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getComment());
            return objectResultDto;
        }
        AlipayGetUserInfoParam alipayGetUserInfoParam = new AlipayGetUserInfoParam();
        alipayGetUserInfoParam.setAccessToken(requestDto.getAccessToken());
        try {
            ObjectResultDto<AlipayGetUserInfoResult> resultDto = aliPayService.getUserInfo(alipayGetUserInfoParam);
            AlipayGetUserInfoResultDto alipayGetUserInfoResultDto;
            if (resultDto.isFailed()) {
                if ("20001".equals(resultDto.getData())) {
                    if (StringUtils.isEmpty(requestDto.getRefreshToken())) {
                        return objectResultDto.makeResult(UCenterResultEnum.REFRESHTOKEN_NOT_EXISTS.getValue(), UCenterResultEnum.REFRESHTOKEN_NOT_EXISTS.getComment());
                    }
                    AlipayGetAccessTokenParam alipayGetAccessTokenParam = new AlipayGetAccessTokenParam();
                    alipayGetAccessTokenParam.setRefreshToken(requestDto.getRefreshToken());
                    ObjectResultDto<AlipayGetAccessTokenResult> objResultDto = aliPayService.getAccessToken(alipayGetAccessTokenParam);
                    AlipayGetAccessTokenResult alipayGetAccessTokenResult = objResultDto.getData();
                    if (objResultDto.isFailed()) {
                        return objectResultDto.makeResult(objResultDto.getCode(), objResultDto.getMessage());
                    }
                    AlipayAccessToken alipayAccessToken = new AlipayAccessToken();
                    if (StringUtils.isEmpty(alipayGetAccessTokenResult.getAlipayUserId())) {
                        return objectResultDto.makeResult(UCenterResultEnum.ALIPAYUSERID_EMPTY.getValue(), UCenterResultEnum.ALIPAYUSERID_EMPTY.getComment());
                    }
                    alipayAccessToken.setAlipayUserId(alipayGetAccessTokenResult.getAlipayUserId());
                    if (StringUtils.isEmpty(alipayGetAccessTokenResult.getAccessToken())) {
                        return objectResultDto.makeResult(UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getValue(), UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getComment());
                    }
                    alipayAccessToken.setAccessToken(alipayGetAccessTokenResult.getAccessToken());
                    alipayAccessToken.setRefreshToken(alipayGetAccessTokenResult.getRefreshToken());
                    if (StringUtils.isNotEmpty(alipayGetAccessTokenResult.getTokenExpiredSeconds())) {
                        alipayAccessToken.setTokenExpiredSeconds(Integer.parseInt(alipayGetAccessTokenResult.getTokenExpiredSeconds()));
                    }
                    if (StringUtils.isNotEmpty(alipayGetAccessTokenResult.getRefreshTokenExpiredSeconds())) {
                        alipayAccessToken.setRefreshTokenExpiredSeconds(Integer.parseInt(alipayGetAccessTokenResult.getRefreshTokenExpiredSeconds()));
                    }
                    alipayAccessTokenCrudService.insert(alipayAccessToken);
                    alipayGetUserInfoParam.setAccessToken(alipayGetAccessTokenResult.getAccessToken());
                    ObjectResultDto<AlipayGetUserInfoResult> resultDtoReal = aliPayService.getUserInfo(alipayGetUserInfoParam);
                    if (resultDtoReal.isFailed()) {
                        return objectResultDto.makeResult(resultDtoReal.getCode(), resultDtoReal.getMessage());
                    }
                    alipayGetUserInfoResultDto = modelMapper.map(resultDtoReal.getData(), AlipayGetUserInfoResultDto.class);
                    objectResultDto.setData(alipayGetUserInfoResultDto);
                    objectResultDto.success();
                    return objectResultDto;
                } else {
                    return objectResultDto.makeResult(resultDto.getCode(), resultDto.getMessage());
                }
            }
            alipayGetUserInfoResultDto = modelMapper.map(resultDto.getData(), AlipayGetUserInfoResultDto.class);
            objectResultDto.setData(alipayGetUserInfoResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户信息失败" + e.getMessage());
            objectResultDto.makeResult(UCenterResultEnum.GRT_USERINFO_ERR.getValue(), UCenterResultEnum.GRT_USERINFO_ERR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<WeixinGetAccessTokenResultDto> getAccessToken(WeixinGetAccessTokenRequestDto requestDto) {
        ObjectResultDto<WeixinGetAccessTokenResultDto> resultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getCode())) {
            return resultDto.makeResult(UCenterResultEnum.CODE_NOT_VAILD.getValue(), UCenterResultEnum.CODE_NOT_VAILD.getComment());
        }
        WeChatGetAccessTokenParams weChatGetAccessTokenParams = new WeChatGetAccessTokenParams();
        weChatGetAccessTokenParams.setCode(requestDto.getCode());
        weChatGetAccessTokenParams.setGoWay(requestDto.getGoway());
        try {
            ObjectResultDto<WeChatGetAccessTokenResult> result = weChatPayService.getAccessToken(weChatGetAccessTokenParams);
            if (result.isFailed()) {
                return resultDto.makeResult(UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getValue(), UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getComment());
            }
            WeChatGetAccessTokenResult getAccessTokenResult = result.getData();
            WeixinAccessToken weixinAccessToken = new WeixinAccessToken();
            if (StringUtils.isEmpty(getAccessTokenResult.getOpenId())) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            weixinAccessToken.setOpenId(getAccessTokenResult.getOpenId());
            weixinAccessToken.setUnionId(getAccessTokenResult.getUnionid());
            if (StringUtils.isEmpty(getAccessTokenResult.getAccessToken())) {
                return resultDto.makeResult(UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getValue(), UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getComment());
            }
            weixinAccessToken.setAccessToken(getAccessTokenResult.getAccessToken());
            weixinAccessToken.setRefreshToken(getAccessTokenResult.getRefreshToken());
            if (StringUtils.isNotEmpty(getAccessTokenResult.getAccessToken())) {
                weixinAccessToken.setTokenExpiredSeconds(Integer.parseInt(getAccessTokenResult.getExpiresIn()));
            }
            weixinAccessToken.setScope(getAccessTokenResult.getScope());
            weixinAccessTokenCrudService.insert(weixinAccessToken);
            WeixinGetAccessTokenResultDto weixinGetAccessTokenResultDto = modelMapper.map(weixinAccessToken, WeixinGetAccessTokenResultDto.class);
            resultDto.setData(weixinGetAccessTokenResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取accessToken失败" + e.getMessage());
            resultDto.makeResult(UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getValue(), UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getComment());
        }
        return resultDto;
    }

    @Override
    public ObjectResultDto<WeixinGetUserInfoResultDto> getWechatInfo(WeixinGetUserInfoRequestDto requestDto) {
        ObjectResultDto<WeixinGetUserInfoResultDto> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getAccessToken())) {
            objectResultDto.makeResult(UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getValue(), UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getComment());
            return objectResultDto;
        }
        if (StringUtils.isEmpty(requestDto.getOpenId())) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        WeChatCheckAccessTokenParams weChatCheckAccessTokenParams = new WeChatCheckAccessTokenParams();
        weChatCheckAccessTokenParams.setAccessToken(requestDto.getAccessToken());
        weChatCheckAccessTokenParams.setOpenId(requestDto.getOpenId());
        try {
            ObjectResultDto<WeChatAccessTokenIsValidResult> resultDto = weChatPayService.checkAccessToken(weChatCheckAccessTokenParams);
            if (!resultDto.isSuccess()) {
                return objectResultDto.makeResult(resultDto.getCode(), resultDto.getMessage());
            }
            if (!resultDto.getData().getValid()) {
                return objectResultDto.makeResult(UCenterResultEnum.ACCESSTOKEN_NOT_VAILD.getValue(), UCenterResultEnum.ACCESSTOKEN_NOT_VAILD.getComment());
            }
        } catch (Exception e) {
            log.error("检查accessToken失败" + e.getMessage());
            return objectResultDto.makeResult(UCenterResultEnum.CHECK_ACCESSTOKEN_ERR.getValue(), UCenterResultEnum.CHECK_ACCESSTOKEN_ERR.getComment());
        }
        WeChatGetUserInfoParams weChatGetUserInfoParams = new WeChatGetUserInfoParams();
        weChatGetUserInfoParams.setAccessToken(requestDto.getAccessToken());
        weChatGetUserInfoParams.setOpenId(requestDto.getOpenId());
        try {
            ObjectResultDto<WeChatGetUserInfoResult> resultDto = weChatPayService.getUserInfo(weChatGetUserInfoParams);
            if (resultDto.isFailed()) {
                return objectResultDto.makeResult(UCenterResultEnum.GRT_USERINFO_ERR.getValue(), UCenterResultEnum.GRT_USERINFO_ERR.getComment());
            }
            WeixinGetUserInfoResultDto weixinGetUserInfoResultDto = modelMapper.map(resultDto.getData(), WeixinGetUserInfoResultDto.class);
            if ("42001".equals(weixinGetUserInfoResultDto.getErrCode())) {
                if (StringUtils.isEmpty(requestDto.getRefreshToken())) {
                    return objectResultDto.makeResult(UCenterResultEnum.REFRESHTOKEN_NOT_EXISTS.getValue(), UCenterResultEnum.REFRESHTOKEN_NOT_EXISTS.getComment());
                }
                WeChatUpdateAccessTokenParams weChatUpdateAccessTokenParams = new WeChatUpdateAccessTokenParams();
                weChatUpdateAccessTokenParams.setRefreshToken(requestDto.getRefreshToken());
                ObjectResultDto<WeChatUpdateAccessTokenResult> objectResultDto1 = weChatPayService.updateAccessToken(weChatUpdateAccessTokenParams);
                WeChatUpdateAccessTokenResult weChatUpdateAccessTokenResult = objectResultDto1.getData();
                if (StringUtils.isNotEmpty(weChatUpdateAccessTokenResult.getErrCode())) {
                    return objectResultDto.makeResult(UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getValue(), UCenterResultEnum.GRT_ACCESSTOKEN_ERR.getComment());
                }
                WeixinAccessToken weixinAccessToken = new WeixinAccessToken();
                if (StringUtils.isEmpty(weChatUpdateAccessTokenResult.getOpenId())) {
                    return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
                }
                weixinAccessToken.setOpenId(weChatUpdateAccessTokenResult.getOpenId());
                //weixinAccessToken.setUnionId(weChatUpdateAccessTokenResult.getUnionid());
                if (StringUtils.isEmpty(weChatUpdateAccessTokenResult.getAccessToken())) {
                    return objectResultDto.makeResult(UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getValue(), UCenterResultEnum.ACCESSTOKEN_NOT_EXISTS.getComment());
                }
                weixinAccessToken.setAccessToken(weChatUpdateAccessTokenResult.getAccessToken());
                weixinAccessToken.setRefreshToken(weChatUpdateAccessTokenResult.getRefreshToken());
                if (StringUtils.isNotEmpty(weChatUpdateAccessTokenResult.getExpiresIn())) {
                    weixinAccessToken.setTokenExpiredSeconds(Integer.parseInt(weChatUpdateAccessTokenResult.getExpiresIn()));
                }
                weixinAccessToken.setScope(weChatUpdateAccessTokenResult.getScope());
                weixinAccessTokenCrudService.insert(weixinAccessToken);
                weChatGetUserInfoParams.setAccessToken(weChatUpdateAccessTokenResult.getAccessToken());
                weChatGetUserInfoParams.setOpenId(weChatUpdateAccessTokenResult.getOpenId());
                ObjectResultDto<WeChatGetUserInfoResult> resultDtoReal = weChatPayService.getUserInfo(weChatGetUserInfoParams);
                if (resultDtoReal.isFailed()) {
                    return objectResultDto.makeResult(UCenterResultEnum.GRT_USERINFO_ERR.getValue(), UCenterResultEnum.GRT_USERINFO_ERR.getComment());
                }
                weixinGetUserInfoResultDto = modelMapper.map(resultDtoReal.getData(), WeixinGetUserInfoResultDto.class);
            }
            if (StringUtils.isNotEmpty(weixinGetUserInfoResultDto.getErrCode())) {
                return objectResultDto.makeResult(UCenterResultEnum.GRT_USERINFO_ERR.getValue(), UCenterResultEnum.GRT_USERINFO_ERR.getComment());
            }
            objectResultDto.setData(weixinGetUserInfoResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户信息失败" + e.getMessage());
            objectResultDto.makeResult(UCenterResultEnum.GRT_USERINFO_ERR.getValue(), UCenterResultEnum.GRT_USERINFO_ERR.getComment());
        }
        return objectResultDto;
    }
}
