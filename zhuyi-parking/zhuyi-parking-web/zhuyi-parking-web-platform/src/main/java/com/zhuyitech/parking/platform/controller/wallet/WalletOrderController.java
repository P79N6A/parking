package com.zhuyitech.parking.platform.controller.wallet;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.dto.request.assetlog.AssetLogAddRemarkRequestDto;
import com.zhuyitech.parking.ucc.dto.request.assetlog.AssetLogPageQueryRequestDto;
import com.zhuyitech.parking.ucc.dto.request.assetlog.WalletDetailRequestDto;
import com.zhuyitech.parking.ucc.dto.result.assetlog.AssetLogPageQueryResultDto;
import com.zhuyitech.parking.ucc.dto.result.assetlog.WalletDetailResultDto;
import com.zhuyitech.parking.ucc.service.api.UserAssetLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 钱包账单API
 *
 * @author zwq
 */
@Api(value = "钱包账单API", description = "钱包账单API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/wallet")
public class WalletOrderController {

    @Autowired
    private UserAssetLogService userAssetLogService;

    /**
     * 钱包账单查询
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "钱包账单查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AssetLogPageQueryResultDto.class)
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public PagedResultDto<AssetLogPageQueryResultDto> pageQuery(AssetLogPageQueryRequestDto requestDto) {
        return userAssetLogService.pageQuery(requestDto);
    }

    /**
     * 钱包账单详情
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "钱包账单详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WalletDetailResultDto.class)
    @RequestMapping(value = "/walletDetail", method = RequestMethod.POST)
    public ObjectResultDto<WalletDetailResultDto> walletDetail(WalletDetailRequestDto requestDto) {
        return userAssetLogService.walletDetail(requestDto);
    }

    /**
     * 钱包账单详情添加备注
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "钱包账单详情添加备注", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/addRemark", method = RequestMethod.POST)
    public ResultDto addRemark(AssetLogAddRemarkRequestDto requestDto) {
        return userAssetLogService.addRemark(requestDto);
    }
}
