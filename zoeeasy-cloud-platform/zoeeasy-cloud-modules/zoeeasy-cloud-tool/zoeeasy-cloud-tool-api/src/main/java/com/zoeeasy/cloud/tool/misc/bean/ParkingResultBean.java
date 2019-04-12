package com.zoeeasy.cloud.tool.misc.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * 停车场信息
 *
 * @author walkman
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingResultBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status;

    private Integer total;

    private String message;

    private List<ParkingInfoItemBean> results;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ParkingInfoItemBean> getResults() {
        return results;
    }

    public void setResults(List<ParkingInfoItemBean> results) {
        this.results = results;
    }
}
