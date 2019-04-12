package com.zoeeasy.cloud.platform.controller.common;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.tool.vesta.dto.SequenceGenerateRequestDto;
import com.zoeeasy.cloud.tool.vesta.dto.SequenceNumberRequestDto;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cloud/common")
@Api(tags = "通用接口", value = "通用接口API", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiVersion(1)
@ApiSigned
@RequiresAuthentication
public class CommonController {

    @Autowired
    private IdService idService;

    /**
     * @return
     */
    @ApiOperation(value = "生成序列号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = SequenceNumberRequestDto.class)
    @PostMapping(value = "/sequence")
    @ApiSigned
    public ObjectResultDto<SequenceNumberRequestDto> generateSequence(@RequestBody SequenceGenerateRequestDto requestDto) {
        ObjectResultDto<SequenceNumberRequestDto> objectResultDto = new ObjectResultDto<>();
        SequenceNumberRequestDto sequenceNumberRequestDto = new SequenceNumberRequestDto();
        sequenceNumberRequestDto.setSequence(String.valueOf(idService.genId()));
        objectResultDto.setData(sequenceNumberRequestDto);
        objectResultDto.success();
        return objectResultDto;
    }

}
