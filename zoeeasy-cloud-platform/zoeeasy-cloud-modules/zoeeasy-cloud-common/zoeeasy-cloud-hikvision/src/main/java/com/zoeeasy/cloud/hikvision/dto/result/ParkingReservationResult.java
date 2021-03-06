package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.ParkingReservationBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 预约车位返回结果
 * @Date: 2018/1/15 0015
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingReservationResult extends HikvisionBaseResult<ParkingReservationBean> implements Serializable {

    private static final long serialVersionUID = 1L;

}
