package com.zoeeasy.cloud.collect.core.handler;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.collect.cst.CollectConst;
import com.zoeeasy.cloud.collect.enums.ResultCodeEnum;
import com.zoeeasy.cloud.collect.msgbody.request.CheckKeyBody;
import com.zoeeasy.cloud.collect.msgbody.result.BaseResultBody;
import com.zoeeasy.cloud.collect.packets.CollectPacket;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetByLocalCodeRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.json.Json;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

/**
 * @Date: 2019-03-03
 * @author: wf
 */
@Slf4j
@Component
public class CheckKeyHandler extends AbstractBizHandler<CheckKeyBody> {

    private Config config = ConfigService.getConfig(CollectConst.COLLECT_PROPERTIES);

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    private static CheckKeyHandler checkKeyHandler;

    @PostConstruct
    public void init() {
        checkKeyHandler = this;
        checkKeyHandler.platformParkingInfoService = this.platformParkingInfoService;
    }

    public CheckKeyHandler() {
    }

    @Override
    public Class<CheckKeyBody> bodyClass() {
        return CheckKeyBody.class;
    }

    @Override
    public Object handler(CollectPacket packet, CheckKeyBody bsBody, ChannelContext channelContext) throws Exception {

        String str = Json.toJson(bsBody);
        log.info("[CheckKeyReqHandler.handler]收到认证消息：--{}--", str);

        CollectPacket collectPacketBack = new CollectPacket();
        //校验返回参数
        BaseResultBody baseResultBody = new BaseResultBody();

        try {
            //校验停车场Key
            String parkKey = config.getProperty(CollectConst.COLLECT_PARKKEY, "");
            if (StringUtils.isEmpty(parkKey) || !parkKey.equals(bsBody.getParkKey().trim())) {
                baseResultBody.setService(bsBody.getService());
                baseResultBody.setMessage(CollectConst.CHECKKEY_MESSAGE_INVALIDKEY);
                baseResultBody.setCode(ResultCodeEnum.FAILED.getValue());
            } else {
                //根据云平台停车场Code查询停车场信息，获取dockInfo的Id
                ParkingInfoGetByLocalCodeRequestDto parkingInfoGetByCodeRequestDto = new ParkingInfoGetByLocalCodeRequestDto();
                parkingInfoGetByCodeRequestDto.setLocalCode(bsBody.getLocalCode());
                ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDto = checkKeyHandler.platformParkingInfoService.getParkInfoByLocalCode(parkingInfoGetByCodeRequestDto);
                if (parkingInfoResultDto.isSuccess() && parkingInfoResultDto.getData() != null) {
                    baseResultBody.setService(bsBody.getService());
                    baseResultBody.setMessage(CollectConst.CHECKKEY_MESSAGE_SUCCESS);
                    baseResultBody.setCode(ResultCodeEnum.SUCCESS.getValue());
                    //绑定用户
                    Tio.bindUser(channelContext, bsBody.getLocalCode());
                } else {
                    baseResultBody.setService(bsBody.getService());
                    baseResultBody.setMessage(CollectConst.CHECKKEY_MESSAGE_NOPARK);
                    baseResultBody.setCode(ResultCodeEnum.FAILED.getValue());
                }
            }
        } catch (Exception ex) {
            log.error("客户端认证异常:{}", str);
            baseResultBody.setService(bsBody.getService());
            baseResultBody.setMessage(CollectConst.CHECKKEY_MESSAGE_FAIL);
            baseResultBody.setCode(ResultCodeEnum.FAILED.getValue());
        }

        String msg = Json.toJson(baseResultBody);
        collectPacketBack.setBody(msg.getBytes(Charset.forName(CollectConst.CHARSET)));
        Tio.send(channelContext, collectPacketBack);
        return null;
    }
}