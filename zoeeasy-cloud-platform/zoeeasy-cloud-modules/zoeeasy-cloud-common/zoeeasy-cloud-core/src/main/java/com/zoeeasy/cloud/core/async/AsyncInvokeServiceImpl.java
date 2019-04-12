package com.zoeeasy.cloud.core.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步服务
 *
 * @author walkman
 */
public class AsyncInvokeServiceImpl implements AsyncInvokeService {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 300;
    private static final BlockingQueue<Runnable> poolWorkQueue = new LinkedBlockingQueue<>(5);

    /**
     * corePoolSize - 池中所保存的线程数，包括空闲线程。
     * maximumPoolSize-池中允许的最大线程数。
     * keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
     * unit - keepAliveTime 参数的时间单位。
     * workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute方法提交的 Runnable任务。
     * threadFactory - 执行程序创建新线程时使用的工厂。
     * handler - 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。
     */
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 20, TimeUnit.SECONDS, poolWorkQueue);

    @Override
    public void submit(InvokeRunnable runnable) {
        if (runnable != null) {
            executor.submit(runnable);
        }
    }

    @Override
    public void submit(Runnable runnable) {
        if (runnable != null) {
            executor.submit(runnable);
        }
    }
}
