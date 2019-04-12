package com.zoeeasy.cloud.platform.controller.navigate;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.ucc.navigation.NavigationService;
import com.zoeeasy.cloud.ucc.navigation.dto.UserMenu;
import com.zoeeasy.cloud.ucc.navigation.dto.request.NavigationAllGetRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Api(tags = "导航菜单", value = "导航菜单Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/navigate")
@ApiVersion(1)
@ApiSigned
@RequiresAuthentication
public class UserNavigateController {

    @Autowired
    private NavigationService navigationService;

    @ApiOperation(value = "所有菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserMenu.class)
    @PostMapping(value = "/all", name = "所有菜单")
    public ListResultDto<UserMenu> getAllNavigationList(@RequestBody NavigationAllGetRequestDto requestDto) {
        return navigationService.getAllNavigationList(requestDto);
    }

}
