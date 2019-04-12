package com.zhuyitech.parking.tool.bean.traffic;

import java.io.Serializable;

/**
 * 限行信息bean
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
public class TrafficRestrictionInfoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 限行时间段
     */
    private String time;

    /**
     * 限行地点
     */
    private String place;

    /**
     * 限行说明
     */
    private String info;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
