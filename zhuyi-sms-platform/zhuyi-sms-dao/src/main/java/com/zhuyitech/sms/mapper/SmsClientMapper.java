package com.zhuyitech.sms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuyitech.sms.domain.SmsClient;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 */
@Repository
public interface SmsClientMapper extends BaseMapper<SmsClient> {

    /**
     * 根据商户编号，查询商户信息
     *
     * @param clientId
     * @return
     */
    SmsClient getByClientId(@Param("clientId") String clientId);
}
