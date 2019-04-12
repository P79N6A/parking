package com.zoeeasy.cloud.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.scapegoat.infrastructure.common.utils.IOUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pay.alipay.params.*;
import com.zoeeasy.cloud.pay.alipay.result.*;
import com.zoeeasy.cloud.pay.alipay.service.AliPayService;
import com.zoeeasy.cloud.pay.alipay.utils.ZipEntry;
import com.zoeeasy.cloud.pay.alipay.utils.ZipInputStream;
import com.zoeeasy.cloud.pay.config.AlipayProperty;
import com.zoeeasy.cloud.pay.constant.alipay.AlipayConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     支付宝支付接口实现
 * </pre>
 *
 * @author zwq
 * @date 2018-01-11-13:13
 */
@Service("aliPayService")
@Slf4j
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private AlipayProperty alipayProperty;

    /**
     * 统一收单线下交易预创建
     *
     * @param params
     * @return
     */
    @Override
    public ObjectResultDto<AlipayPrecreateOrderResult> precreateOrder(AlipayPrecreateOrderParams params) {
        ObjectResultDto<AlipayPrecreateOrderResult> resultDto = new ObjectResultDto<>();
        AlipayTradePrecreateModel payModel = new AlipayTradePrecreateModel();
        if (StringUtils.isNotEmpty(params.getSubject())) {
            payModel.setSubject(params.getSubject());
        }
        if (StringUtils.isNotEmpty(params.getBody())) {
            payModel.setBody(params.getBody());
        }
        if (StringUtils.isNotEmpty(params.getOutTradeNo())) {
            payModel.setOutTradeNo(params.getOutTradeNo());
        }
        if (StringUtils.isNotEmpty(params.getTimeoutExpress())) {
            payModel.setTimeoutExpress(params.getTimeoutExpress());
        }
        if (params.getTotalAmount() != null && params.getTotalAmount().compareTo(BigDecimal.ZERO) > 0) {
            payModel.setTotalAmount(String.valueOf(params.getTotalAmount()));
        }
        if (params.getDiscountableAmount() != null && params.getDiscountableAmount().compareTo(BigDecimal.ZERO) > 0) {
            payModel.setTotalAmount(String.valueOf(params.getTotalAmount()));
        }
        if (StringUtils.isNotEmpty(params.getEnablePayChannels())) {
            payModel.setEnablePayChannels(params.getEnablePayChannels());
        }
        if (StringUtils.isNotEmpty(params.getDisablePayChannels())) {
            payModel.setDisablePayChannels(params.getDisablePayChannels());
        }
        if (StringUtils.isNotEmpty(params.getStoreId())) {
            payModel.setStoreId(params.getStoreId());
        }
        try {
            // 实例化客户端
            AlipayClient alipayClient = this.createAlipayClient(params);
            // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            //这里和普通的接口调用不同，使用的是sdkExecute
            request.setBizModel(payModel);
            request.setNotifyUrl(alipayProperty.getNotifyUrl());
            AlipayTradePrecreateResponse response = alipayClient.sdkExecute(request);
            //就是orderString 可以直接给客户端请求，无需再做处理。
            if (response.isSuccess() && StringUtils.isNotEmpty(response.getQrCode())) {
                AlipayPrecreateOrderResult alipayPrecreateOrderResult = new AlipayPrecreateOrderResult();
                alipayPrecreateOrderResult.setOutTradeNo(response.getOutTradeNo());
                alipayPrecreateOrderResult.setQrCode(response.getQrCode());
                resultDto.setData(alipayPrecreateOrderResult);
                resultDto.success();
            } else {
                resultDto.failed();
            }
        } catch (AlipayApiException e) {
            log.error("支付宝下单失败, 异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 支付宝预支付
     *
     * @param params AlipayPrepareOrderParams
     * @return ObjectResultDto<AliPayPrepareOrderResult>
     */
    @Override
    public ObjectResultDto<AliPayPrepareOrderResult> preparePay(AlipayPrepareOrderParams params) {
        ObjectResultDto<AliPayPrepareOrderResult> resultDto = new ObjectResultDto<>();
        AlipayTradeAppPayModel payModel = new AlipayTradeAppPayModel();
        if (StringUtils.isNotEmpty(params.getBody())) {
            payModel.setBody(params.getBody());
        }
        if (StringUtils.isNotEmpty(params.getSubject())) {
            payModel.setSubject(params.getSubject());
        }
        if (StringUtils.isNotEmpty(params.getOutTradeNo())) {
            payModel.setOutTradeNo(params.getOutTradeNo());
        }
        if (StringUtils.isNotEmpty(params.getTimeoutExpress())) {
            payModel.setTimeoutExpress(params.getTimeoutExpress());
        }
        if (params.getTotalAmount() != null && params.getTotalAmount().compareTo(BigDecimal.ZERO) > 0) {
            payModel.setTotalAmount(String.valueOf(params.getTotalAmount()));
        }
        payModel.setProductCode(AlipayConst.QUICK_MSECURITY_PAY);
        if (params.getGoodsType() != null) {
            payModel.setGoodsType(String.valueOf(params.getGoodsType()));
        }
        if (StringUtils.isNotEmpty(params.getPassbackParams())) {
            payModel.setPassbackParams(URLEncoder.encode(params.getPassbackParams()));
        }
//        if (StringUtils.isNotEmpty(params.getExtendParams())) {
//            payModel.setExtendParams(params.getExtendParams());
//        }
        if (StringUtils.isNotEmpty(params.getEnablePayChannels())) {
            payModel.setEnablePayChannels(params.getEnablePayChannels());
        }
        if (StringUtils.isNotEmpty(params.getDisablePayChannels())) {
            payModel.setDisablePayChannels(params.getDisablePayChannels());
        }
        if (StringUtils.isNotEmpty(params.getStoreId())) {
            payModel.setStoreId(params.getStoreId());
        }
        try {
            // 实例化客户端
            AlipayClient alipayClient = this.createAlipayClient(params);
            // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //这里和普通的接口调用不同，使用的是sdkExecute
            request.setBizModel(payModel);
            request.setNotifyUrl(alipayProperty.getNotifyUrl());
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            //就是orderString 可以直接给客户端请求，无需再做处理。
            AliPayPrepareOrderResult payPrepareOrderResult = new AliPayPrepareOrderResult();
            payPrepareOrderResult.setOrderInfo(response.getBody());
            payPrepareOrderResult.setOutTradeNo(response.getOutTradeNo());
            payPrepareOrderResult.setSellerId(response.getSellerId());
            payPrepareOrderResult.setTotalAmount(response.getTotalAmount());
            payPrepareOrderResult.setTradeNo(response.getTradeNo());
            resultDto.setData(payPrepareOrderResult);
            resultDto.success();
        } catch (AlipayApiException e) {
            log.error("支付宝下单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 订单查询
     *
     * @param params AlipayTradeQueryParams
     * @return ObjectResultDto<AlipayPayTradeQueryResult>
     */
    @Override
    public ObjectResultDto<AlipayPayTradeQueryResult> queryOrder(AlipayTradeQueryParams params) {
        ObjectResultDto<AlipayPayTradeQueryResult> resultDto = new ObjectResultDto<>();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        String outTradeNo = params.getOutTradeNo();
        String tradeNo = params.getTradeNo();
        if (StringUtils.isEmpty(outTradeNo) && StringUtils.isEmpty(tradeNo)) {
            resultDto.failed("outTradeNo和tradeNo必须有一个不为空");
            return resultDto;
        }
        if (StringUtils.notEmpty(outTradeNo)) {
            model.setOutTradeNo(outTradeNo);
        }
        if (StringUtils.notEmpty(tradeNo)) {
            model.setOutTradeNo(outTradeNo);
        }
        request.setBizModel(model);
        try {
            AlipayClient alipayClient = createAlipayClient(params);
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                AlipayPayTradeQueryResult responseDto = new AlipayPayTradeQueryResult();
                responseDto.setBuyerLogonId(response.getBuyerLogonId());
                responseDto.setBuyerPayAmount(response.getBuyerPayAmount());
                responseDto.setBuyerUserId(response.getBuyerUserId());
                responseDto.setBuyerUserType(response.getBuyerUserType());
                responseDto.setCode(response.getCode());
                responseDto.setInvoiceAmount(response.getInvoiceAmount());
                responseDto.setMsg(response.getMsg());
                responseDto.setOutTradeNo(response.getOutTradeNo());
                responseDto.setPointAmount(response.getPointAmount());
                responseDto.setReceiptAmount(response.getReceiptAmount());
                responseDto.setSendPayDate(response.getSendPayDate());
                responseDto.setStoreId(response.getStoreId());
                responseDto.setStoreName(response.getStoreName());
                responseDto.setSubCode(response.getSubCode());
                responseDto.setSubMsg(response.getSubMsg());
                responseDto.setTerminalId(response.getTerminalId());
                responseDto.setTotalAmount(response.getTotalAmount());
                responseDto.setTradeNo(response.getTradeNo());
                responseDto.setTradeStatus(response.getTradeStatus());
                responseDto.setFundBillList(response.getFundBillList());
                resultDto.setData(responseDto);
                resultDto.isSuccess();
            } else {
                resultDto.failed("支付宝查询订单接口调用失败");
            }
        } catch (AlipayApiException e) {
            log.error("支付宝查询订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 订单关闭
     *
     * @param params AlipayTradeCloseParam
     * @return ObjectResultDto<AlipayTradeCloseResult>
     */
    @Override
    public ObjectResultDto<AlipayTradeCloseResult> closeOrder(AlipayTradeCloseParam params) {
        ObjectResultDto<AlipayTradeCloseResult> resultDto = new ObjectResultDto<>();

        try {

            AlipayClient alipayClient = createAlipayClient(params);
            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
            AlipayTradeCloseModel model = new AlipayTradeCloseModel();
            String outTradeNo = params.getOutTradeNo();
            String tradeNo = params.getTradeNo();
            if (StringUtils.isEmpty(outTradeNo) && StringUtils.isEmpty(tradeNo)) {
                resultDto.failed("outTradeNo和tradeNo必须有一个不为空");
                return resultDto;
            }
            if (StringUtils.notEmpty(outTradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            if (StringUtils.notEmpty(tradeNo)) {
                model.setTradeNo(tradeNo);
            }
            request.setBizModel(model);

            AlipayTradeCloseResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                AlipayTradeCloseResult responseDto = new AlipayTradeCloseResult();
                responseDto.setCode(response.getCode());
                responseDto.setMsg(response.getMsg());
                responseDto.setSubCode(response.getSubCode());
                responseDto.setSubMsg(response.getSubMsg());
                responseDto.setOutTradeNo(response.getOutTradeNo());
                responseDto.setTradeNo(response.getTradeNo());
                resultDto.setData(responseDto);
                resultDto.success();
            } else {
                resultDto.failed("支付宝关闭订单接口调用失败");
            }
        } catch (AlipayApiException e) {
            log.error("支付宝关闭订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 交易退款
     *
     * @param params AlipayTradeRefundParam
     * @return ObjectResultDto<AlipayTradeRefundResult>
     */
    @Override
    public ObjectResultDto<AlipayTradeRefundResult> refundOrder(AlipayTradeRefundParam params) {
        ObjectResultDto<AlipayTradeRefundResult> resultDto = new ObjectResultDto<>();
        try {

            AlipayClient alipayClient = createAlipayClient(params);
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            String outTradeNo = params.getOutTradeNo();
            String tradeNo = params.getTradeNo();
            if (StringUtils.isEmpty(outTradeNo) && StringUtils.isEmpty(tradeNo)) {
                resultDto.failed("outTradeNo和tradeNo必须有一个不为空");
                return resultDto;
            }
            if (StringUtils.isEmpty(params.getRefundAmount())) {
                resultDto.failed("退款金额不能为空");
                return resultDto;
            }
            model.setRefundAmount(params.getRefundAmount());
            if (StringUtils.notEmpty(outTradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            if (StringUtils.notEmpty(tradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            if (StringUtils.notEmpty(params.getRefundReason())) {
                model.setRefundReason(params.getRefundReason());
            }
            if (StringUtils.notEmpty(params.getOutRequestNo())) {
                model.setOutRequestNo(params.getOutRequestNo());
            }
            if (StringUtils.notEmpty(params.getOperatorId())) {
                model.setOperatorId(params.getOperatorId());
            }
            if (StringUtils.notEmpty(params.getStoreId())) {
                model.setStoreId(params.getStoreId());
            }
            if (StringUtils.notEmpty(params.getTerminalId())) {
                model.setTerminalId(params.getTerminalId());
            }
            if (CollectionUtils.isNotEmpty(params.getGoodsDetail())) {
                model.setGoodsDetail(params.getGoodsDetail());
            }
            request.setBizModel(model);

            AlipayTradeRefundResponse response = alipayClient.execute(request);
            AlipayTradeRefundResult alipayTradeRefundResponseDto = new AlipayTradeRefundResult();
            alipayTradeRefundResponseDto.setCode(response.getCode());
            alipayTradeRefundResponseDto.setMsg(response.getMsg());
            alipayTradeRefundResponseDto.setSubCode(response.getSubCode());
            alipayTradeRefundResponseDto.setSubMsg(response.getSubMsg());
            alipayTradeRefundResponseDto.setTradeNo(response.getTradeNo());
            alipayTradeRefundResponseDto.setOutTradeNo(response.getOutTradeNo());
            alipayTradeRefundResponseDto.setBuyerLogonId(response.getBuyerLogonId());
            alipayTradeRefundResponseDto.setRefundFee(response.getRefundFee());
            alipayTradeRefundResponseDto.setStoreName(response.getStoreName());
            alipayTradeRefundResponseDto.setFundChange(response.getFundChange());
            alipayTradeRefundResponseDto.setGmtRefundPay(response.getGmtRefundPay());
            alipayTradeRefundResponseDto.setRefundDetailItemList(response.getRefundDetailItemList());
            alipayTradeRefundResponseDto.setBuyerUserId(response.getBuyerUserId());
            alipayTradeRefundResponseDto.setPresentRefundBuyerAmount(response.getPresentRefundBuyerAmount());
            alipayTradeRefundResponseDto.setPresentRefundDiscountAmount(response.getPresentRefundDiscountAmount());
            alipayTradeRefundResponseDto.setPresentRefundMdiscountAmount(response.getPresentRefundMdiscountAmount());
            resultDto.setData(alipayTradeRefundResponseDto);
            resultDto.success();
        } catch (AlipayApiException e) {
            log.error("支付宝订单退款失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 交易退款查询
     *
     * @param params AlipayTradeRefundQueryParam
     * @return ObjectResultDto<AlipayTradeRefundQueryResult>
     */
    @Override
    public ObjectResultDto<AlipayTradeRefundQueryResult> queryRefundOrder(AlipayTradeRefundQueryParam params) {
        ObjectResultDto<AlipayTradeRefundQueryResult> resultDto = new ObjectResultDto<>();
        String outTradeNo = params.getOutTradeNo();
        String tradeNo = params.getTradeNo();
        if (StringUtils.isEmpty(params.getOutRequestNo())) {
            resultDto.failed("outRequestNo不能为空");
            return resultDto;
        }
        if (StringUtils.isEmpty(outTradeNo) && StringUtils.isEmpty(tradeNo)) {
            resultDto.failed("outTradeNo和tradeNo必须有一个不为空");
            return resultDto;
        }

        try {

            AlipayClient alipayClient = createAlipayClient(params);
            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

            AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
            model.setOutRequestNo(params.getOutRequestNo());
            if (StringUtils.notEmpty(outTradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            if (StringUtils.notEmpty(tradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            request.setBizModel(model);

            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                AlipayTradeRefundQueryResult responseDto = new AlipayTradeRefundQueryResult();
                responseDto.setCode(response.getCode());
                responseDto.setMsg(response.getMsg());
                responseDto.setSubCode(response.getSubCode());
                responseDto.setSubMsg(response.getSubMsg());
                responseDto.setOutRequestNo(response.getOutRequestNo());
                responseDto.setOutTradeNo(response.getOutTradeNo());
                responseDto.setTradeNo(response.getTradeNo());
                responseDto.setRefundReason(response.getRefundReason());
                if (StringUtils.isNotEmpty(response.getTotalAmount())) {
                    responseDto.setTotalAmount(new BigDecimal(response.getTotalAmount()));
                }
                if (StringUtils.isNotEmpty(response.getRefundAmount())) {
                    responseDto.setRefundAmount(new BigDecimal(response.getRefundAmount()));
                }
                resultDto.setData(responseDto);
                resultDto.success();
            } else {
                resultDto.failed("支付宝订单退款查询接口调用失败");
            }
        } catch (AlipayApiException e) {
            log.error("支付宝查询退款订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取对账单
     *
     * @param params AlipayBillDownloadParam
     * @return ListResultDto<AlipayDownloadBillResult>
     */
    @Override
    public ListResultDto<AlipayDownloadBillResult> downloadBill(AlipayBillDownloadParam params) {
        ListResultDto<AlipayDownloadBillResult> resultDto = new ListResultDto<>();
        if (StringUtils.isEmpty(params.getBillType())) {
            resultDto.failed("billType不能为空");
            return resultDto;
        }
        if (StringUtils.isEmpty(params.getBillDate())) {
            resultDto.failed("billDate不能为空");
            return resultDto;
        }
        try {
            AlipayClient alipayClient = createAlipayClient(params);
            AlipayDataDataserviceBillDownloadurlQueryRequest
                    request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
            AlipayDataDataserviceBillDownloadurlQueryModel model = new AlipayDataDataserviceBillDownloadurlQueryModel();
            model.setBillDate(params.getBillDate());
            model.setBillType(params.getBillType());
            request.setBizModel(model);
            AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
            if (StringUtils.isEmpty(response.getBillDownloadUrl())) {
                resultDto.failed("账单不存在");
                return resultDto;
            }
            String file = getDownloadFileName(response.getBillDownloadUrl());
            String downUrl = alipayProperty.getDownloadUrl() + file + ".zip";
            boolean downSuccess = downLoadZip(response.getBillDownloadUrl(), downUrl);
            if (downSuccess) {

                File billFile = new File(downUrl);

                List<AlipayDownloadBillResult> billResultList = readZipCvsFile(billFile);
                resultDto.setItems(billResultList);
                resultDto.success();
            } else {
                resultDto.failed("文件下载失败");
                return resultDto;
            }
        } catch (Exception e) {
            log.error("支付宝账单下载失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 创建AlipayClient
     *
     * @param param AlipayPayBaseParam
     * @return AlipayClient
     */
    private AlipayClient createAlipayClient(AlipayPayBaseParam param) {
        return new DefaultAlipayClient(AlipayConst.ALIPAY_GATEWAY_URL,
                param.getAppId(), param.getPrivateKey(),
                AlipayConst.FORMAT, AlipayConst.CHARSET,
                param.getPublicKey(), AlipayConst.SIGN_TYPE);
    }

    /**
     * 读取zip文件，不解压缩直接解析，支持文件名中文，解决内容乱码
     *
     * @param file file
     */
    @SuppressWarnings("unchecked")
    private List<AlipayDownloadBillResult> readZipCvsFile(File file) {
        //获得输入流，文件为zip格式，
        //支付宝提供
        //20886126836996110156_20160906.csv.zip内包含
        //20886126836996110156_20160906_业务明细.csv
        //20886126836996110156_20160906_业务明细(汇总).csv
        List<AlipayDownloadBillResult> list = new ArrayList<>();
        ZipInputStream in = null;
        BufferedReader br = null;
        try {
            in = new ZipInputStream(new FileInputStream(file));
            //不解压直接读取,加上gbk解决乱码问题
            br = new BufferedReader(new InputStreamReader(in, "gbk"));
            ZipEntry zipFile;
            //循环读取zip中的cvs文件，无法使用jdk自带，因为文件名中有中文
            while ((zipFile = in.getNextEntry()) != null) {
                if (zipFile.isDirectory()) {
                    //如果是目录，不处理
                    continue;
                }

                //获得cvs名字
                String fileName = zipFile.getName();

                // 检测文件是否存在
                if (fileName != null && fileName.contains(".") && fileName.contains("业务明细")) {

                    String line;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    while ((line = br.readLine()) != null) {

                        //忽略注解行
                        if (StringUtils.isEmpty(line) || line.startsWith("#")) {
                            continue;
                        }
                        //忽略表头
                        if (line.startsWith("支付宝交易号")) {
                            continue;
                        }
                        String[] split = line.split(",", 25);
                        if (split.length == 24 || split.length == 25) {

                            AlipayDownloadBillResult alipayBill = new AlipayDownloadBillResult();
                            if (StringUtils.isNotEmpty(split[0])) {
                                alipayBill.setOrderNo(split[0].trim());
                            }
                            if (StringUtils.isNotEmpty(split[1])) {
                                alipayBill.setOutTradeNo(split[1].trim());
                            }
                            if (StringUtils.isNotEmpty(split[2])) {
                                alipayBill.setPayType(split[2].trim());
                            }
                            if (StringUtils.isNotEmpty(split[3])) {
                                alipayBill.setSubject(split[3].trim());
                            }
                            if (StringUtils.isNotEmpty(split[4])) {
                                alipayBill.setStartTime(sdf.parse(split[4].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[5])) {
                                alipayBill.setEndTime(sdf.parse(split[5].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[6])) {
                                alipayBill.setStoreId(split[6].trim());
                            }
                            if (StringUtils.isNotEmpty(split[7])) {
                                alipayBill.setStoreName(split[7].trim());
                            }
                            if (StringUtils.isNotEmpty(split[8])) {
                                alipayBill.setOperatorId(split[8].trim());
                            }
                            if (StringUtils.isNotEmpty(split[9])) {
                                alipayBill.setTerminalId(split[9].trim());
                            }
                            if (StringUtils.isNotEmpty(split[10])) {
                                alipayBill.setBuyerLogonId(split[10].trim());
                            }
                            if (StringUtils.isNotEmpty(split[11])) {
                                alipayBill.setAmount(new BigDecimal(split[11].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[12])) {
                                alipayBill.setReceiptAmount(new BigDecimal(split[12].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[13])) {
                                alipayBill.setZfbPacket(new BigDecimal(split[13].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[14])) {
                                alipayBill.setSetPointTreasure(new BigDecimal(split[14].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[15])) {
                                alipayBill.setZfbDiscount(new BigDecimal(split[15].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[16])) {
                                alipayBill.setStoreDiscount(new BigDecimal(split[16].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[17])) {
                                alipayBill.setTicketDiscount(new BigDecimal(split[17].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[18])) {
                                alipayBill.setTicketName(split[18].trim());
                            }
                            if (StringUtils.isNotEmpty(split[19])) {
                                alipayBill.setStorePacketAmount(new BigDecimal(split[19].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[20])) {
                                alipayBill.setCardPacketAmount(new BigDecimal(split[20]));
                            }
                            if (StringUtils.isNotEmpty(split[21])) {
                                alipayBill.setOutRequestNo(split[21].trim());
                            }
                            if (StringUtils.isNotEmpty(split[22])) {
                                alipayBill.setServiceCharge(new BigDecimal(split[22].trim()));
                            }
                            if (StringUtils.isNotEmpty(split[23])) {
                                alipayBill.setFeeSplitting(new BigDecimal(split[23]));
                            }
                            if (split.length == 25) {
                                alipayBill.setRemark(split[24]);
                            }
                            list.add(alipayBill);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("读取csv文件处理失败" + e.getMessage());
        } finally {
            IOUtils.close(br);
            IOUtils.close(in);
        }
        return list;
    }

    /**
     * 通过alipay_url获取下载的文件名称
     *
     * @param alipayUrl alipayUrl
     * @return 文件名称
     */
    private String getDownloadFileName(String alipayUrl) {
        String tempStr = alipayUrl.substring(alipayUrl.indexOf("downloadFileName") + 17);
        tempStr = tempStr.substring(0, tempStr.indexOf(".zip"));
        return tempStr;
    }

    /**
     * 通过支付宝查询对账单接口返回的url,下载zip文件
     *
     * @param alipayUrl alipayUrl
     * @param downUrl   downUrl
     * @return 处理结果
     */
    private boolean downLoadZip(String alipayUrl, String downUrl) {
        boolean downSuccess;
        int byteRead;
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            URL url = new URL(alipayUrl);
            URLConnection conn = url.openConnection();
            inStream = conn.getInputStream();
            //自定义文件保存地址
            //判断下载保存路径文件夹
            String unzipFilePath = downUrl.substring(0, downUrl.lastIndexOf("/"));
            File unzipFileDir = new File(unzipFilePath);
            //下载文件存放地址
            if (!unzipFileDir.exists() || !unzipFileDir.isDirectory()) {
                unzipFileDir.mkdirs();
            }
            //解压文件是否已存在
            File entryFile = new File(downUrl);
            if (entryFile.exists()) {
                //删除已存在的目标文件
                Boolean deleted = entryFile.delete();
                if (!deleted) {
                    return downSuccess = false;
                }
            }
            fs = new FileOutputStream(downUrl);
            byte[] buffer = new byte[4028];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            downSuccess = true;
        } catch (Exception e) {
            log.error("支付宝查询退款订单失败" + e.getMessage());
            downSuccess = false;
        } finally {
            IOUtils.close(inStream);
            IOUtils.close(fs);
        }
        return downSuccess;
    }


    /**
     * 支付宝预支付
     *
     * @param params AlipayPrepareOrderParams
     * @return ObjectResultDto<AliPayPrepareOrderResult>
     */
    @Override
    public ObjectResultDto<AliPayPrepareOrderResult> prepareAliPay(AlipayPrepareOrderParams params, String payOrderNo, String productName) {
        AlipayPrepareOrderParams alipayPrepareOrderParams = new AlipayPrepareOrderParams();
        alipayPrepareOrderParams.setSubject(productName);
        alipayPrepareOrderParams.setOutTradeNo(payOrderNo);
        //设置金额2位小数
        alipayPrepareOrderParams.setTotalAmount(params.getTotalAmount());
        alipayPrepareOrderParams.setAppId(alipayProperty.getMiniAppId());
        alipayPrepareOrderParams.setPrivateKey(alipayProperty.getPrivateKey());
        alipayPrepareOrderParams.setPublicKey(alipayProperty.getPublicKey());
        return preparePay(params);
    }
}
