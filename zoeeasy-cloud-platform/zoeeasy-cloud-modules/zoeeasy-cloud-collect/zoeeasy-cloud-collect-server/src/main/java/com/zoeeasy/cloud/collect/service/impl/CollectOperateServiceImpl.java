package com.zoeeasy.cloud.collect.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.collect.core.CollectOperateService;
import com.zoeeasy.cloud.collect.core.CollectServerStarter;
import com.zoeeasy.cloud.collect.cst.CollectConst;
import com.zoeeasy.cloud.collect.dto.request.OpenStrobeRequestDto;
import com.zoeeasy.cloud.collect.dto.request.PaymentNotifyRequestDto;
import com.zoeeasy.cloud.collect.dto.request.QueryPriceRequestDto;
import com.zoeeasy.cloud.collect.dto.result.PaymentNotifyResultDto;
import com.zoeeasy.cloud.collect.enums.BizEnum;
import com.zoeeasy.cloud.collect.msgbody.request.BizRequestBody;
import com.zoeeasy.cloud.collect.msgbody.request.OpenStrobeBody;
import com.zoeeasy.cloud.collect.msgbody.request.PaymentNotifyBody;
import com.zoeeasy.cloud.collect.msgbody.request.QueryPriceBody;
import com.zoeeasy.cloud.collect.packets.CollectPacket;
import com.zoeeasy.cloud.pms.dock.DockInfoService;
import com.zoeeasy.cloud.pms.dock.dto.request.DockInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.dock.dto.result.DockInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetByLocalCodeRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tio.core.GroupContext;
import org.tio.core.Tio;
import org.tio.utils.json.Json;

import java.nio.charset.Charset;

/**
 * 道闸业务下行接口
 *
 * @author wf
 * @date 2019-03-01
 */
@Slf4j
@Service("collectOperateService")
public class CollectOperateServiceImpl implements CollectOperateService {

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private DockInfoService dockInfoService;

    /**
     * 价格查询接口
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    @Override
    public ResultDto queryPrice(QueryPriceRequestDto requestDto) throws ValidationException, BusinessException {
        ResultDto resultDto = new ResultDto();
        try {
            CollectPacket collectPacket = new CollectPacket();
            QueryPriceBody queryPriceReqBody = CollectOperateServiceBll.convertQueryPriceRequest(requestDto);
            String queryPriceContent = Json.toJson(queryPriceReqBody);
            //请求业务参数
            BizRequestBody bizRequestBody = new BizRequestBody();
            bizRequestBody.setBizContent(queryPriceContent);
            bizRequestBody.setBizUrl(getBizUrl(requestDto.getLocalCode(), BizEnum.QUERY_PRICE.getValue()));
            byte[] packetBody = Json.toJson(bizRequestBody).getBytes(Charset.forName(CollectConst.CHARSET));
            collectPacket.setBody(packetBody);
            collectPacket.setLen(packetBody.length);

            GroupContext groupContext = CollectServerStarter.serverGroupContext;
            boolean tag = Tio.bSendToUser(groupContext, queryPriceReqBody.getLocalCode(), collectPacket);
            if (tag) {
                log.info("查询道闸系统价格发送成功:" + collectPacket.toString());
                resultDto.success();
            } else {
                log.error("查询道闸系统价格失败：消息发送给TCP助手异常" + collectPacket.toString());
                resultDto.failed();
            }
        } catch (BusinessException be) {
            log.error("查询道闸系统价格失败：" + be.getMessage());
            resultDto.failed();
        } catch (Exception ex) {
            log.error("查询道闸系统价格失败：" + ex.getMessage());
            resultDto.failed();
        }

        return resultDto;
    }

    /**
     * 支付通知接口
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    @Override
    public ResultDto paymentNotify(PaymentNotifyRequestDto requestDto) throws ValidationException, BusinessException {
        ObjectResultDto<PaymentNotifyResultDto> resultDto = new ObjectResultDto<>();
        try {
            CollectPacket collectPacket = new CollectPacket();
            PaymentNotifyBody paymentResultReqBody = CollectOperateServiceBll.convertPaymentAdviceRequest(requestDto);
            String paymentNotifyContent = Json.toJson(paymentResultReqBody);
            //请求业务参数
            BizRequestBody bizRequestBody = new BizRequestBody();
            bizRequestBody.setBizContent(paymentNotifyContent);
            bizRequestBody.setBizUrl(getBizUrl(requestDto.getLocalCode(), BizEnum.PAYMENT_NOTIFY.getValue()));
            byte[] packetBody = Json.toJson(bizRequestBody).getBytes(Charset.forName(CollectConst.CHARSET));
            collectPacket.setBody(packetBody);
            collectPacket.setLen(packetBody.length);

            GroupContext groupContext = CollectServerStarter.serverGroupContext;
            boolean tag = Tio.bSendToUser(groupContext, requestDto.getLocalCode(), collectPacket);
            if (tag) {
                log.info("支付通知道闸系统发送成功:" + collectPacket.toString());
                resultDto.success();
            } else {
                log.error("支付通知道闸系统失败：消息发送给TCP助手异常" + collectPacket.toString());
                resultDto.failed();
            }
        } catch (BusinessException be) {
            log.error("支付通知道闸系统失败：" + be.getMessage());
            resultDto.failed();
        } catch (Exception ex) {
            log.error("支付通知道闸系统失败：" + ex.getMessage());
            resultDto.failed();
        }

        return resultDto;
    }

    /**
     * 远程开闸接口
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    @Override
    public ResultDto openStrobe(OpenStrobeRequestDto requestDto) throws ValidationException, BusinessException {
        ResultDto resultDto = new ResultDto();
        try {
            CollectPacket collectPacket = new CollectPacket();
            OpenStrobeBody setOpenStrobeReqBody = CollectOperateServiceBll.convertOpenStrobeRequest(requestDto);
            String openStrobeContent = Json.toJson(setOpenStrobeReqBody);
            //请求业务参数
            BizRequestBody bizRequestBody = new BizRequestBody();
            bizRequestBody.setBizContent(openStrobeContent);
            bizRequestBody.setBizUrl(getBizUrl(requestDto.getLocalCode(), BizEnum.OPEN_STROBE.getValue()));
            byte[] packetBody = Json.toJson(bizRequestBody).getBytes(Charset.forName(CollectConst.CHARSET));
            collectPacket.setBody(packetBody);
            collectPacket.setLen(packetBody.length);

            GroupContext groupContext = CollectServerStarter.serverGroupContext;
            boolean tag = Tio.bSendToUser(groupContext, setOpenStrobeReqBody.getLocalCode(), collectPacket);
            if (tag) {
                log.info("请求道闸系统远程开闸发送成功:" + collectPacket.toString());
                resultDto.success();
            } else {
                log.error("请求道闸系统远程开闸失败：消息发送给TCP助手异常" + collectPacket.toString());
                resultDto.failed();
            }
        } catch (BusinessException be) {
            log.error("请求道闸系统远程开闸失败" + be.getMessage());
            resultDto.failed();
        } catch (Exception ex) {
            log.error("请求道闸系统远程开闸失败" + ex.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据停车场本地Code获取业务的Url
     */
    public String getBizUrl(String localCode, Integer bizEnumValue) {
        String requestUrl = "";
        //根据第三方停车场Code查询停车场信息，获取dockInfo的Id
        ParkingInfoGetByLocalCodeRequestDto parkingInfoGetByCodeRequestDto = new ParkingInfoGetByLocalCodeRequestDto();
        parkingInfoGetByCodeRequestDto.setLocalCode(localCode);
        ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDto = platformParkingInfoService.getParkInfoByLocalCode(parkingInfoGetByCodeRequestDto);
        Long dockId = new Long(0);
        if (parkingInfoResultDto.isSuccess() && parkingInfoResultDto.getData() != null) {
            //根据dockId查询DockInfo，获取请求的url
            DockInfoGetByIdRequestDto dockInfoGetByIdRequestDto = new DockInfoGetByIdRequestDto();
            dockId = parkingInfoResultDto.getData().getDockId();
            dockInfoGetByIdRequestDto.setId(dockId);
            ObjectResultDto<DockInfoResultDto> dockInfoResultDto = dockInfoService.getDockById(dockInfoGetByIdRequestDto);
            if (dockInfoResultDto.isSuccess() && dockInfoResultDto.getData() != null) {
                if (BizEnum.QUERY_PRICE.getValue().equals(bizEnumValue)) {
                    requestUrl = dockInfoResultDto.getData().getPlaceOrderUrl();
                } else if (BizEnum.PAYMENT_NOTIFY.getValue().equals(bizEnumValue)) {
                    requestUrl = dockInfoResultDto.getData().getNotifyOrderUrl();
                } else if (BizEnum.OPEN_STROBE.getValue().equals(bizEnumValue)) {
                    requestUrl = dockInfoResultDto.getData().getOpenStrobeUrl();
                } else {
                    log.error("getBizUrl业务类型异常" + bizEnumValue);
                    throw new BusinessException("getBizUrl业务类型异常" + bizEnumValue);
                }
            } else {
                log.error("getBizUrl未查询到DockInfo信息" + dockId);
                throw new BusinessException("getBizUrl未查询到DockInfo信息" + dockId);
            }
        } else {
            log.error("getBizUrl未查询到停车场信息" + localCode);
            throw new BusinessException("getBizUrl未查询到停车场信息" + localCode);
        }
        return requestUrl;
    }
}
