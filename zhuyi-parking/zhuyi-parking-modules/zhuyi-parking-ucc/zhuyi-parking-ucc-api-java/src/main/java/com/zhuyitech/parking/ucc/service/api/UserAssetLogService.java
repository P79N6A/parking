package com.zhuyitech.parking.ucc.service.api;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.dto.request.assetlog.*;
import com.zhuyitech.parking.ucc.dto.result.assetlog.*;

/**
 * 用户收支明细服务
 *
 * @author zwq
 * @date 2018-06-13
 */
public interface UserAssetLogService {

    /**
     * 拉取用户收支明细
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<AssetLogListResultDto> getList(AssetLogListRequestDto requestDto);

    /**
     * 用户收支明细详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AssetLogDetailResultDto> assetLogDetail(AssetLogDetailRequestDto requestDto);

    /**
     * 钱包账单查询
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<AssetLogPageQueryResultDto> pageQuery(AssetLogPageQueryRequestDto requestDto);

    /**
     * 钱包账单详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WalletDetailResultDto> walletDetail(WalletDetailRequestDto requestDto);

    /**
     * 钱包账单详情添加备注
     *
     * @param requestDto
     * @return
     */
    ResultDto addRemark(AssetLogAddRemarkRequestDto requestDto);

    /**
     * 根据订单查询
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AssetLogGetByBizNoResultDto> getByBizNo(GetByBizNoRequestDto requestDto);
}
