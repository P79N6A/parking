package com.zoeeasy.cloud.pay.alipay.params;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 结算描述信息
 *
 * @author walkman
 */
@Getter
@Setter
public class AlipaySettleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 可选 32商户id类型，  alipay:支付宝分配的间连商户编号,merchant:商户端的间连商户编号
     */
    private String merchantType;

    /**
     * 结算详细信息，json数组，目前只支持一条。
     */
    private AlipaySettleDetailInfo settleDetailInfos;

}

