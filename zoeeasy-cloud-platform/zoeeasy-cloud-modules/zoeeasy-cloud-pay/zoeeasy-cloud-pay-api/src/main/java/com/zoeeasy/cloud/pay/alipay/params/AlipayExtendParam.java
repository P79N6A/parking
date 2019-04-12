package com.zoeeasy.cloud.pay.alipay.params;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 支付宝业务扩展参数
 *
 * @author walkman
 */
@Getter
@Setter
public class AlipayExtendParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 可选	64 系统商编号,该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
     */
    private String sysServiceProviderId;

    /**
     * 可选	512	行业数据回流信息, 详见：地铁支付接口参数补充说明
     */
    private String industryRefluxInfo;

    /**
     * 可选	32	卡类型
     */
    private String cardType;
}

