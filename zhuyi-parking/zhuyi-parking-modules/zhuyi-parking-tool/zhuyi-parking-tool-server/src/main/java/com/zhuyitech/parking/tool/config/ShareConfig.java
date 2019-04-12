package com.zhuyitech.parking.tool.config;

import com.zhuyitech.parking.tool.constant.SharePropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 分享配置属性
 *
 * @author walkman
 */
@Configuration
public class ShareConfig {

    @Value("${" + SharePropertyKeys.SHARE_TITLE + ":}")
    @Getter
    @Setter
    private String title;

    @Value("${" + SharePropertyKeys.SHARE_CONTENT + ":}")
    @Getter
    @Setter
    private String content;

    @Value("${" + SharePropertyKeys.SHARE_IMAGE_URL + ":}")
    @Getter
    @Setter
    private String imageUrl;

    @Value("${" + SharePropertyKeys.SHARE_LINK_URL + ":}")
    @Getter
    @Setter
    private String linkUrl;

}
