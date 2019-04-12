package com.zhuyitech.parking.tool.bean.violation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 违章记录
 *
 * @author walkman
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleViolationDataResultBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户身份标识
     */
    private String token;

    private BigDecimal totalFine;

    private Integer totalPoints;

    private Integer untreated;

    private Integer amount;

    private List<VehicleViolationItemResultBean> violations;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(BigDecimal totalFine) {
        this.totalFine = totalFine;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getUntreated() {
        return untreated;
    }

    public void setUntreated(Integer untreated) {
        this.untreated = untreated;
    }

    public List<VehicleViolationItemResultBean> getViolations() {
        return violations;
    }

    public void setViolations(List<VehicleViolationItemResultBean> violations) {
        this.violations = violations;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
