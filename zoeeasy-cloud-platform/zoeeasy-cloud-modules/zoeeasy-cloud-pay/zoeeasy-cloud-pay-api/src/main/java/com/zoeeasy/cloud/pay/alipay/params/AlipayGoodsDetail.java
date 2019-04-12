package com.zoeeasy.cloud.pay.alipay.params;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 支付宝订单商品信息列表
 *
 * @author walkman
 */
@Getter
@Setter
public class AlipayGoodsDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 必填	32	商品的编号
     */
    private String goodsId;

    /**
     * 必填	256	商品名称
     */
    private String goodsName;

    /**
     * 必填	10	商品数量	1
     */
    private Integer quantity;

    /**
     * Price	必填	9	商品单价，单位为元
     */
    private BigDecimal price;

    /**
     * 可选	24	商品类目
     */
    private String goodsCategory;

    /**
     * 可选	128	商品类目树，从商品类目根节点到叶子节点的类目id组成，类目id值使用|分割	124868003|126232002|126252004
     */
    private String categoriesTree;

    /**
     * 可选	1000	商品描述信息
     */
    private String body;

    /**
     * 可选	400	商品的展示地址
     */
    private String showUrl;

}
