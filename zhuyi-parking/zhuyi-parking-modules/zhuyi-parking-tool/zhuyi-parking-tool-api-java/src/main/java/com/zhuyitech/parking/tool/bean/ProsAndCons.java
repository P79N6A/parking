package com.zhuyitech.parking.tool.bean;

import java.io.Serializable;

/**
 * 身份证识别正反面对象
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
public class ProsAndCons implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 正反面
     */
    private String side;

    public ProsAndCons() {
    }

    public ProsAndCons(String side) {
        this.side = side;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "Configure{" +
                "side='" + side + '\'' +
                '}';
    }
}
