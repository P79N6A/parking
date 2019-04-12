package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.ParkingPaymentInfoBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 根据车牌号码获取停车账单的返回结果
 * @Date: 2018/1/15 0015
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingPaymentInfoResult extends HikvisionBaseResult<ParkingPaymentInfoBean> implements Serializable {

    private static final long serialVersionUID = 1L;

}
