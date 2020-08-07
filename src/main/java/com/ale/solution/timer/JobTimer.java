package com.ale.solution.timer;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class JobTimer {
    public static final String jobsTag = "customer_jobtimer_jobs";
    @Autowired
    private RedissonClient client;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ThreadPoolExecutor poolExecutor;


    @PostConstruct
    public void startJobTimer() {
        RBlockingQueue<String> blockingQueue = client.getBlockingQueue(jobsTag);

        poolExecutor.execute(() ->{
            while (true) {
                try {
                    String job = blockingQueue.take();
                    log.info("{}", job);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        TimeUnit.SECONDS.sleep(60);
                    } catch (Exception ex) {
                    }
                }
            }
        });
    }
}
