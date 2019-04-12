package com.zoeeasy.cloud.charge.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChargePartTime implements Serializable {

    /**
     * 该时段计费开始时间
     */
    private Date partStartTime;

    /**
     * 该时段计费结束时间
     */
    private Date partEndTime;
}
