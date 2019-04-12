package com.zoeeasy.cloud.axino.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class JssProperty {

    private Boolean sandbox;

    private String identity;
}
