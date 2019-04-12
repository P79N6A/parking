package com.zoeeasy.cloud.alipay.result;


import com.alipay.api.domain.InterfaceInfoList;

import java.io.Serializable;


/**
 * ISV系统配置查询接口结果
 *
 * @author zwq
 */
public class AlipayParkingConfigQueryResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户简称，由开发者提供
     */
    private String merchantName;

    /**
     * 商户客服电话
     */
    private String merchantServicePhone;

    /**
     * 签约支付宝账号
     */
    private String accountNo;

    /**
     * 接口信息列表，停车业务需要配置的接口列表，该值为JSON数据格式的LIST对象，现阶段只需要配置一个页面接口即可 。每次请将所有的接口配置信息都传入，未传的接口信息将会被置空。
     */
    private InterfaceInfoList interfaceInfoList;

    /**
     * 可选
     * 商户在停车平台首页露出的LOGO；注意：该图片为PNG格式内容为BASE64的字符串，若为空则停车平台首页将不露出商户LOGO。建议图片尺寸27px*27px，
     * 大小不要超过60K
     */
    private String merchantLogo;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantServicePhone() {
        return merchantServicePhone;
    }

    public void setMerchantServicePhone(String merchantServicePhone) {
        this.merchantServicePhone = merchantServicePhone;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public InterfaceInfoList getInterfaceInfoList() {
        return interfaceInfoList;
    }

    public void setInterfaceInfoList(InterfaceInfoList interfaceInfoList) {
        this.interfaceInfoList = interfaceInfoList;
    }

    public String getMerchantLogo() {
        return merchantLogo;
    }

    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }
}
