package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页获取停车场基本信息的返回视图
 * @author: AkeemSuper
 * @date: 2018/3/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingInfoView implements Serializable {

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
    private List<ParkingInfoBean> list;

}
