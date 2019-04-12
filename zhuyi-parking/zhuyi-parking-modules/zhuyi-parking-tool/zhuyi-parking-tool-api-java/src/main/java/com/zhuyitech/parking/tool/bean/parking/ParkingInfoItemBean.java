package com.zhuyitech.parking.tool.bean.parking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * 停车场信息
 *
 * @author walkman
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingInfoItemBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String telephone;

    private String address;

    private ParkingLocationBean location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ParkingLocationBean getLocation() {
        return location;
    }

    public void setLocation(ParkingLocationBean location) {
        this.location = location;
    }
}
