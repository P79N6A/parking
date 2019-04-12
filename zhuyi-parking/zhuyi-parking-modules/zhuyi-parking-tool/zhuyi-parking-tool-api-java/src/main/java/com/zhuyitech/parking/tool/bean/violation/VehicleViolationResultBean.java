package com.zhuyitech.parking.tool.bean.violation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 违章查询结果
 *
 * @author walkman
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleViolationResultBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success;

    private VehicleViolationDataResultBean data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public VehicleViolationDataResultBean getData() {
        return data;
    }

    public void setData(VehicleViolationDataResultBean data) {
        this.data = data;
    }
}
