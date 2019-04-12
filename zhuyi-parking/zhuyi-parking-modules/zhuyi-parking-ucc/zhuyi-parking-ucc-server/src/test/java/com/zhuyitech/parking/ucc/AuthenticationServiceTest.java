package com.zhuyitech.parking.ucc;


import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.dto.request.token.AccessTokenRequestDto;
import com.zhuyitech.parking.ucc.service.api.AuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
//@ScapegoatDubboApplicationConfiguration(QuickDubboBootstrap.class)
//@SpringBootTest(classes = {QuickDubboBootstrap.class})
public class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void accessTokenRequestTest() {

        /**
         * parking-android	0f96f3e84f1445d9cb6f0ec8606f18d0
         * parking-apple	c3a1f331d6b221d9d5c77a8a9bd568ef
         * parking-pc	44edf5f29c2ce30d3e4a18b8ce9bc1eb
         * parking-h5	83c8bde641dee1d4334fdf72d15e47a5
         */
        final String clientId = "parking-pc";
        final String clientSecret = "44edf5f29c2ce30d3e4a18b8ce9bc1eb";
        final String principal = "administrator";
        final String credentials = "123456";
        final String grantType = "password";
        final String scope = "read write";

        AccessTokenRequestDto accessTokenRequestDto = new AccessTokenRequestDto();
        accessTokenRequestDto.setClientId(clientId);
        accessTokenRequestDto.setClientSecret(clientSecret);
        accessTokenRequestDto.setPrincipal(principal);
        accessTokenRequestDto.setCredentials(credentials);
        accessTokenRequestDto.setGrantType(grantType);
        accessTokenRequestDto.setScope(scope);
        ResultDto resultDto = authenticationService.requestToken(accessTokenRequestDto);
        assertTrue(resultDto.isSuccess());
    }
}