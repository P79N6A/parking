package com.zoeeasy.cloud.core.async;

/**
 * 异步服务
 *
 * @author walkman
 */
public interface AsyncInvokeService {

    void submit(InvokeRunnable runnable);

    void submit(Runnable runnable);
}
