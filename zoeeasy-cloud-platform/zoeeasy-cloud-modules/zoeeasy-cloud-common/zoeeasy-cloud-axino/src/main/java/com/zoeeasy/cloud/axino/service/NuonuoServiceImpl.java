package com.zoeeasy.cloud.axino.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.axino.bean.NnPlaceOrderInvalidRequestBean;
import com.zoeeasy.cloud.axino.bean.NnPlaceOrderRequestBean;
import com.zoeeasy.cloud.axino.bean.NnPlaceOrderResultBean;
import com.zoeeasy.cloud.axino.bean.NnQueryInvoiceOrderRequestBean;
import com.zoeeasy.cloud.axino.bean.NnQueryInvoiceRequestBean;
import com.zoeeasy.cloud.axino.bean.NnQueryOrderResultBean;
import com.zoeeasy.cloud.axino.constant.JssConstant;
import com.zoeeasy.cloud.axino.constant.JssProperty;
import com.zoeeasy.cloud.axino.constant.JssResultConstant;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 诺诺发票服务
 *
 * @author walkman
 * @since 2019-01-16
 */
@Slf4j
public class NuonuoServiceImpl implements NuonuoService {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JssProperty jssProperty;

    /**
     * 开票请求
     *
     * @param params params
     * @return NnPlaceOrderResultBean
     */
    @Override
    public ObjectResultDto<NnPlaceOrderResultBean> placeInvoiceOrder(NnPlaceOrderRequestBean params) {
        ObjectResultDto<NnPlaceOrderResultBean> objectResultDto = new ObjectResultDto<>();
        try {

            NnPlaceOrderResultBean nnPlaceOrderResultBean = null;
            Map<String, String> paramMap = new HashMap<>();
            if (StringUtils.isEmpty(params.getIdentity())) {
                paramMap.put("identity", jssProperty.getIdentity());
            } else {
                paramMap.put("identity", params.getIdentity());
            }
            if (params.getOrder() != null) {
                paramMap.put("order", objectMapper.writeValueAsString(params.getOrder()));
            }
            String postUrl = JssConstant.PLACE_INVOICE_ORDER_URL;
            if (jssProperty.getSandbox()) {
                postUrl = JssConstant.SANDBOX_PLACE_INVOICE_ORDER_URL;
            }
            nnPlaceOrderResultBean = Requests.post(postUrl)
                    .params(paramMap).charset(Charsets.UTF_8)
                    .send()
                    .readToJson(NnPlaceOrderResultBean.class);

            if (nnPlaceOrderResultBean != null && JssResultConstant.SUCCESS.equals(nnPlaceOrderResultBean.getStatus())) {
                objectResultDto.setData(nnPlaceOrderResultBean);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("开票请求失败, 异常信息:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 开票结果查询
     *
     * @param params
     * @return
     */
    @Override
    public ObjectResultDto<NnQueryOrderResultBean> queryInvoiceOrder(NnQueryInvoiceOrderRequestBean params) {
        ObjectResultDto<NnQueryOrderResultBean> objectResultDto = new ObjectResultDto<>();
        try {
            NnQueryOrderResultBean nnQueryOrderResultBean = null;
            Map<String, String> paramMap = new HashMap<>();
            if (StringUtils.isEmpty(params.getIdentity())) {
                paramMap.put("identity", jssProperty.getIdentity());
            } else {
                paramMap.put("identity", params.getIdentity());
            }
            if (params.getFpqqlsh() != null) {
                paramMap.put("fpqqlsh", objectMapper.writeValueAsString(params.getFpqqlsh()));
            }
            String postUrl = JssConstant.QUERY_INVOICE_ORDER_URL;
            if (jssProperty.getSandbox()) {
                postUrl = JssConstant.SANDBOX_QUERY_INVOICE_ORDER_URL;
            }
            nnQueryOrderResultBean = Requests.post(postUrl)
                    .params(paramMap).charset(Charsets.UTF_8)
                    .send()
                    .readToJson(NnQueryOrderResultBean.class);
            if (nnQueryOrderResultBean != null) {
                objectResultDto.setData(nnQueryOrderResultBean);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("开票结果查询失败, 异常信息:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据订单号查询发票请求流水号
     *
     * @param params
     * @return
     */
    @Override
    public ObjectResultDto<NnQueryOrderResultBean> queryInvoiceKaipiao(NnQueryInvoiceRequestBean params) {
        ObjectResultDto<NnQueryOrderResultBean> objectResultDto = new ObjectResultDto<>();
        try {
            NnQueryOrderResultBean nnQueryOrderResultBean = null;
            Map<String, String> paramMap = new HashMap<>();
            if (StringUtils.isEmpty(params.getIdentity())) {
                paramMap.put("identity", jssProperty.getIdentity());
            } else {
                paramMap.put("identity", params.getIdentity());
            }
            if (params.getOrderNo() != null) {
                paramMap.put("fpqqlsh", objectMapper.writeValueAsString(params.getOrderNo()));
            }
            String postUrl = JssConstant.QUERY_INVOICE_KAIPIAO_URL;
            if (jssProperty.getSandbox()) {
                postUrl = JssConstant.SANDBOX_QUERY_KAIPIAO_ORDER_URL;
            }
            nnQueryOrderResultBean = Requests.post(postUrl)
                    .params(paramMap).charset(Charsets.UTF_8)
                    .send()
                    .readToJson(NnQueryOrderResultBean.class);
            if (nnQueryOrderResultBean != null) {
                objectResultDto.setData(nnQueryOrderResultBean);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("请求流水号失败, 异常信息:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 发票作废申请
     *
     * @param params
     * @return
     */
    @Override
    public ObjectResultDto<NnPlaceOrderResultBean> invalidInvoice(NnPlaceOrderInvalidRequestBean params) {

        ObjectResultDto<NnPlaceOrderResultBean> objectResultDto = new ObjectResultDto<>();
        try {
            NnPlaceOrderResultBean nnPlaceOrderResultBean = null;
            Map<String, String> paramMap = new HashMap<>();
            if (StringUtils.isEmpty(params.getIdentity())) {
                paramMap.put("identity", jssProperty.getIdentity());
            } else {
                paramMap.put("identity", params.getIdentity());
            }
            if (params.getOrder() != null) {
                paramMap.put("order", objectMapper.writeValueAsString(params.getOrder()));
            }
            String postUrl = JssConstant.INVALID_INVOICE_URL;
            if (jssProperty.getSandbox()) {
                postUrl = JssConstant.SANDBOX_INVALID_INVOICE_URL;
            }
            nnPlaceOrderResultBean = Requests.post(postUrl)
                    .params(paramMap).charset(Charsets.UTF_8)
                    .send()
                    .readToJson(NnPlaceOrderResultBean.class);

            if (nnPlaceOrderResultBean != null && JssResultConstant.SUCCESS.equals(nnPlaceOrderResultBean.getStatus())) {
                objectResultDto.setData(nnPlaceOrderResultBean);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("发票作废申请失败, 异常信息:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
