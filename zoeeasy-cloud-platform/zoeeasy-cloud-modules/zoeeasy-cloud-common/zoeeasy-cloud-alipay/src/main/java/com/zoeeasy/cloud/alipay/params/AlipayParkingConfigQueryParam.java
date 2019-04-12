package com.zoeeasy.cloud.alipay.params;


import java.io.Serializable;

/**
 * ISV系统配置查询接口
 *
 * @author walkman
 */
public class AlipayParkingConfigQueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 传入参数固定值:alipay.eco.mycar.parking.userpage.query	alipay.eco.mycar.parking.userpage.query
     */
    private String interfaceName;

    /**
     * 传入参数固定值:interface_page	interface_page
     */
    private String interfaceType;

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
}
