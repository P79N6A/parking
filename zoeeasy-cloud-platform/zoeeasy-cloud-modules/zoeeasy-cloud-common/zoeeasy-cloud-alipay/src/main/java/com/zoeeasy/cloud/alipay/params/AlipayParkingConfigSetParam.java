package com.zoeeasy.cloud.alipay.params;


import com.alipay.api.domain.InterfaceInfoList;

import java.io.Serializable;
import java.util.List;

/**
 * 支付宝停车ISV系统配置接口参数
 *
 * @author walkman
 * @see https://docs.open.alipay.com/api_19/alipay.eco.mycar.parking.config.set
 */
public class AlipayParkingConfigSetParam implements Serializable {

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
    private List<InterfaceInfoList> interfaceInfoList;

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

    public List<InterfaceInfoList> getInterfaceInfoList() {
        return interfaceInfoList;
    }

    public void setInterfaceInfoList(List<InterfaceInfoList> interfaceInfoList) {
        this.interfaceInfoList = interfaceInfoList;
    }

    public String getMerchantLogo() {
        return merchantLogo;
    }

    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }

    /**
     * 接口信息
     */
    private static class InterfaceInfo implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 传入参数固定值:alipay.eco.mycar.parking.userpage.query
         */
        private String interfaceName;

        /**
         * 传入参数固定值:interface_page
         */
        private String interfaceType;

        /**
         * 必填	2048SPI接口的调用地址url，协议必须为https，对整个url字符串必须进行UrlEncode编码。编码为UTF-8https%3A%2F%2Fwww.parking24.cn%2Frf_carlife_alipay%2FCarLifeAction%21alipayAuth.action
         */
        private String interfaceUrl;

        public String getInterfaceName() {
            return interfaceName;
        }

        public void setInterfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
        }

        public String getInterfaceType() {
            return interfaceType;
        }

        public void setInterfaceType(String interfaceType) {
            this.interfaceType = interfaceType;
        }

        public String getInterfaceUrl() {
            return interfaceUrl;
        }

        public void setInterfaceUrl(String interfaceUrl) {
            this.interfaceUrl = interfaceUrl;
        }
    }
}
