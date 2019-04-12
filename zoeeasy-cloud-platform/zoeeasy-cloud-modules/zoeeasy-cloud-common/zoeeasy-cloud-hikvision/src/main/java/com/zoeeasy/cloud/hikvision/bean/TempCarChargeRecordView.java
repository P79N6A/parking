package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页获取停车场某个时间段临时停车缴费记录的返回视图
 * @author: AkeemSuper
 * @date: 2018/3/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TempCarChargeRecordView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 查询记录总数
     */
    private Integer total;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 对象列表
     */
    private List<TempCarChargeRecordsBean> list;

}
