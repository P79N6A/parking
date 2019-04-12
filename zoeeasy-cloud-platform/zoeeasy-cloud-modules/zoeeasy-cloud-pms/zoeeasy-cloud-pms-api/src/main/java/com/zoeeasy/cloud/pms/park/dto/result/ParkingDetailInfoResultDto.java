package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场详情信息
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingDetailInfoResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 停车场ID
     */
    private Long parkingId;

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 区县编码
     */
    private String countyCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 管理单位
     */
    private String managerUnit;

    /**
     * 管理人单位
     */
    private String ownerName;

    /**
     * 运营人单位
     */
    private String operatorUnit;

    /**
     * 收费单位
     */
    private String chargerUnit;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 联系手机
     */
    private String contactPhone;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     * 联系人QQ
     */
    private String contactQQ;

    /**
     * 联系人微信
     */
    private String contactWeixin;

    /**
     * 联系人支付宝
     */
    private String contactAlipay;

}
