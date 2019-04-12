package com.zhuyitech.parking.constant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/11/29 0029
 */
@Component

@Data
@EqualsAndHashCode(callSuper = false)
public class PropertyKeyConfig {

    private PropertyKeyConfig() {
    }

    @Value("${airjoy.url}")
    private String airJoyUrl;

    @Value("${inmotion.url}")
    private String inmotionUrl;

    @Value("${client.url}")
    private String clientUrl;

}
