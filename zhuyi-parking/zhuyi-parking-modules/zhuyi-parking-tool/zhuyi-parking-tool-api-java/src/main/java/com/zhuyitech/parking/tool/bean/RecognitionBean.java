package com.zhuyitech.parking.tool.bean;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 阿里行驶证,身份认证请求实体类
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
public class RecognitionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片base64编码
     */
    @JSONField(ordinal = 1)
    private String image;

    /**
     * 正反面对象
     */
    @JSONField(ordinal = 2)
    private ProsAndCons configure;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProsAndCons getConfigure() {
        return configure;
    }

    public void setConfigure(ProsAndCons configure) {
        this.configure = configure;
    }
}
