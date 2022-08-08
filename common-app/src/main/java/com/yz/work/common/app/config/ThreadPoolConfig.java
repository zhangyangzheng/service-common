package com.yz.work.common.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yangzhengzhang
 * @description 线程池参数配置，按照业务情况定制Bean
 * @date 2021-09-22 14:31
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("tableTransferAsyncThreadPool")
    public ThreadPoolTaskExecutor tableTransferAsyncThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(10);
        // 最大线程数
        executor.setMaxPoolSize(10);

        // executor.setQueueCapacity(10000);// 任务队列的大小

        // 线程前缀名
        executor.setThreadNamePrefix("tableTransferAsyncThreadPool");
        // 线程存活时间
        executor.setKeepAliveSeconds(200);

        /**
         * 拒绝处理策略 CallerRunsPolicy()：交由调用方线程运行。 AbortPolicy()：直接抛出异常。 DiscardPolicy()：直接丢弃。
         * DiscardOldestPolicy()：丢弃队列中最老的任务。
         */
        // 可能会发生异步写失败的丢失数据，朱雀会比较和同步增量的sql server数据
        executor.setRejectedExecutionHandler(
                (runnable, threadPoolExecutor) -> {
                    System.out.println("rejectedExecution: table transfer async thread pool is full");
        // LogHelper.logError("rejectedExecution", "table transfer async thread pool is full");
                });
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());// 可能会发生异步写失败的情况，朱雀会比较和同步增量的数据

        executor.initialize();// 线程初始化
        return executor;
    }

    @Bean("autoCloseBatchThreadPool")
    public ThreadPoolTaskExecutor autoCloseBatchThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(10);
        // 最大线程数
        executor.setMaxPoolSize(10);

        // executor.setQueueCapacity(10000);// 任务队列的大小

        executor.setThreadNamePrefix("autoCloseBatchThreadPool");// 线程前缀名
        // 线程存活时间
        executor.setKeepAliveSeconds(200);

        /**
         * 拒绝处理策略 CallerRunsPolicy()：交由调用方线程运行。 AbortPolicy()：直接抛出异常。 DiscardPolicy()：直接丢弃。
         * DiscardOldestPolicy()：丢弃队列中最老的任务。
         */
        // 可能会发生异步写失败的丢失数据，朱雀会比较和同步增量的sql server数据
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());//可能会发生异步写失败的情况，朱雀会比较和同步增量的数据

        executor.initialize();// 线程初始化
        return executor;
    }

    @Bean("hotelRapidSyncToFinanceThreadPool")
    public ExecutorService hotelRapidSyncToFinanceThreadPool() {
        return Executors.newFixedThreadPool(10);
    }

//    @Bean("dbOtherAsyncThreadPool")
//    public ExecutorService dbDifferentAsyncThreadPool() {
//        ExecutorService executorService = new ThreadPoolExecutor(
//                10,
//                10,
//                200,
//                TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<>(),
//                new ThreadPoolExecutor.AbortPolicy()
//        );
//        return TtlExecutors.getTtlExecutorService(executorService);
//    }
}
