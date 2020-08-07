package com.ale.solution.config;

import jodd.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
  *
  * @author alewu
  * @date 2020/8/7
  */
@Configuration
public class ThreadConfig {
    @Bean("custom")
    public ThreadPoolExecutor customThreadPoolExecutor() {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNameFormat("tt").setDaemon(true).get();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 0,
                                                                       TimeUnit.SECONDS,
                                                                       new ArrayBlockingQueue<>(1000), threadFactory,
                                                                       new ThreadPoolExecutor.CallerRunsPolicy());

        return threadPoolExecutor;
    }
}
