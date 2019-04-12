package com.zoeeasy.cloud.alipay.params;

import java.io.Serializable;

/**
 * 支付宝订单更新参数
 *
 * @author zwq
 */
public class AlipayParkingUpdateOrderParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车缴费支付宝用户的ID，请ISV保证用户ID的正确性，以免导致用户在停车平台查询不到相关的订单信息
     */
    private String userId;

    /**
     * 支付宝支付流水号，系统唯一
     */
    private String orderNo;

    /**
     * 用户停车订单状态，0：成功，1：失败
     */
    private String orderStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
