package com.zoeeasy.cloud.collect.core;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.zoeeasy.cloud.collect.core.listener.CollectServerAioListener;
import com.zoeeasy.cloud.collect.cst.CollectConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tio.server.ServerGroupContext;
import org.tio.server.TioServer;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author wf
 */
@Service(value = CollectConst.COLLECT_SERVER_STARTER_BEAN)
@Slf4j
public class CollectServerStarter {

    private Config config = ConfigService.getConfig(CollectConst.COLLECT_PROPERTIES);

    /**
     * TioServer对象
     */
    public static org.tio.server.TioServer tioServer;
    /**
     * handler, 包括编码、解码、消息处理
     */
    public static ServerAioHandler aioHandler = new CollectServerAioHandler();
    /**
     * 事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解接口
     */
    public static ServerAioListener aioListener = new CollectServerAioListener();
    /**
     * 一组连接共用的上下文对象
     */
    public static ServerGroupContext serverGroupContext = new ServerGroupContext("collectany-tio-server", aioHandler, aioListener);

    /**
     * 服务器IP；若不需要绑定，则设置为null
     */
    private final String serverIp;

    /**
     * 监听的端口
     */
    private final int serverPort;

    public TioServer getTioServer() {
        return tioServer;
    }

    public CollectServerStarter() {
        this(CollectConst.DEFAULT_SERVER, CollectConst.DEFAULT_PORT);
    }

    public CollectServerStarter(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        String collectanyServerPort = config.getProperty(CollectConst.COLLECT_SERVER_PORT, "");
        this.serverPort = StringUtils.isEmpty(collectanyServerPort) ? serverPort : Integer.parseInt(collectanyServerPort);
        serverGroupContext.setHeartbeatTimeout(CollectConst.TIMEOUT);
        tioServer = new org.tio.server.TioServer(serverGroupContext);
    }

    /**
     * 初始化Bean的时候执行的初始化方法
     */
    @PostConstruct
    public void init() {
        try {
            //启动Tio服务
            tioServer.start(serverIp, serverPort);
            log.info("[CollectServerStarter.init]tio启动成功，端口{}", serverPort);
        } catch (Exception e) {
            log.error("[CollectServerStarter.init]", e);
        }
    }

    @PreDestroy
    public void destroy() {
        tioServer.stop();
    }
}
