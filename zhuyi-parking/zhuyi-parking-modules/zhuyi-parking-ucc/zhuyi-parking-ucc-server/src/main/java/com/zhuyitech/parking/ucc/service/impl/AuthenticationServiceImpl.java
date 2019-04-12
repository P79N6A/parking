package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.config.AuthzConfig;
import com.zhuyitech.parking.ucc.dto.request.token.AccessTokenRequestDto;
import com.zhuyitech.parking.ucc.dto.result.user.AccessTokenResponseDto;
import com.zhuyitech.parking.ucc.service.api.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务
 *
 * @author walkman
 */
@Service("authenticationService")
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String GRANT_TYPE_PASSWORD = "password";

    private static final String GRANT_TYPE_CLIENT_CRENDENTIALS = "client_credentials";

    @Autowired
    private AuthzConfig authzConfig;

    @Override
    public ObjectResultDto<AccessTokenResponseDto> requestToken(AccessTokenRequestDto requestDto) {

        String url = authzConfig.getUrl() + "/oauth/token";

        log.debug("Request Token, Url : {}", url);
        log.debug(" principal: {},credentials: {},grantType: {}", requestDto.getPrincipal(), requestDto.getClientSecret(), requestDto.getGrantType());
        Response<String> response = null;
        ObjectResultDto<AccessTokenResponseDto> objectResultDto = new ObjectResultDto<>();
        if (GRANT_TYPE_PASSWORD.equals(requestDto.getGrantType())) {

            try {

                Map<String, Object> params = new HashMap<>();
                params.put("grant_type", requestDto.getGrantType());
                params.put("client_id", requestDto.getClientId());
                params.put("client_secret", requestDto.getClientSecret());
                params.put("username", requestDto.getPrincipal());
                params.put("password", requestDto.getCredentials());
                params.put("scope", requestDto.getScope());
                response = Requests.post(url).verify(false).followRedirect(false).forms(params).send().toTextResponse();
                if (response == null || response.getStatusCode() != 200) {
                    log.error("Password Grant failed ! Unexpected status code : {} . Response body : {}",
                            response.getStatusCode(), response.getBody());
                    return objectResultDto.failed();
                }
            } catch (Exception e) {
                log.info("Password Grant failed !", e);
                objectResultDto.failed();
            }
        } else if (GRANT_TYPE_CLIENT_CRENDENTIALS.equals(requestDto.getGrantType())) {
            try {

                Map<String, Object> params = new HashMap<>();
                params.put("grant_type", requestDto.getGrantType());
                params.put("client_id", requestDto.getClientId());
                params.put("client_secret", requestDto.getClientSecret());
                params.put("scope", requestDto.getScope());
                response = Requests.post(url).verify(false).followRedirect(false).forms(params).send().toTextResponse();

                if (response == null || response.getStatusCode() != 200) {
                    log.error("Client_credentials Grant failed ! Unexpected status code : {} . Response body : {}",
                            response.getStatusCode(), response.getBody());
                    return objectResultDto.failed();
                }
            } catch (Exception e) {
                log.error("Client_credentials Grant failed !", e);
                objectResultDto.failed();
            }
        }
        try {

            JSONObject jsonObject = new JSONObject(response.getBody());
            if (!jsonObject.has("access_token")) {
                return objectResultDto.failed();
            }
            AccessTokenResponseDto accessTokenResponseDto = new AccessTokenResponseDto();
            accessTokenResponseDto.setAccessToken(jsonObject.getString("access_token"));
            if (jsonObject.has("refresh_token")) {
                accessTokenResponseDto.setRefreshToken(jsonObject.getString("refresh_token"));
            }
            if (jsonObject.has("token_type")) {
                accessTokenResponseDto.setTokenType(jsonObject.getString("token_type"));
            }
            if (jsonObject.has("expires_in")) {
                accessTokenResponseDto.setExpiresIn(jsonObject.getInt("expires_in"));
            }
            objectResultDto.setData(accessTokenResponseDto);
            return objectResultDto.success();
        } catch (JSONException e) {
            log.error("Client_credentials Grant failed ! Error parsing access_token :", e);
            objectResultDto.failed();
        } catch (Exception e) {
            log.error("Client_credentials Grant failed !", e);
            objectResultDto.failed();
        }
        return objectResultDto;
    }


}
