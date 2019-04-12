package com.zoeeasy.cloud.collect.core;

import com.zoeeasy.cloud.collect.packets.CollectPacket;
import org.tio.core.ChannelContext;

/**
 * 业务处理器接口
 * @Date: 2019-03-01
 * @author: wf
 */
public interface BizHandlerInterface {

	/**
	 * Tio处理器
	 *
	 * @param packet
	 * @param channelContext
	 * @return
	 * @throws Exception
	 */
	 Object handler(CollectPacket packet, ChannelContext channelContext) throws Exception;

}
