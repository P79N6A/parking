package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.TempCarChargeRecordView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 分页获取停车场某个时间段临时停车缴费记录的返回结果
 * @Date: 2018/1/15 0015
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TempCarChargeRecordsResult extends HikvisionBaseResult<TempCarChargeRecordView> implements Serializable {

    private static final long serialVersionUID = 1L;

}
