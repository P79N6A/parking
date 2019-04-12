package com.zoeeasy.cloud.platform.controller.sys;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.zoeeasy.cloud.sys.visit.VisitLogService;
import com.zoeeasy.cloud.sys.visit.dto.request.VisitLogQueryPageRequestDto;
import com.zoeeasy.cloud.sys.visit.dto.result.VisitLogResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkeemSuper
 * @date 2019/2/22 0022
 */
@Api(value = "登录日志相关Api", tags = "登录日志相关Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/sys/visitlog")
@ApiSigned
public class VisitLogController {

    @Autowired
    private VisitLogService visitLogService;

    /**
     * 获取参数菜单列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取登录日志", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = VisitLogResultDto.class)
    @PostMapping(value = "/queryPage")
    public PagedResultDto<VisitLogResultDto> queryPage(@RequestBody VisitLogQueryPageRequestDto requestDto) {
        return visitLogService.queryPage(requestDto);
    }
}
