package com.ale.solution.service;

import com.ale.solution.timer.JobTimer;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DelayJobService {
    @Autowired
    private RedissonClient client;

    public void submitJob(String job, Long delay, TimeUnit timeUnit) {
        RBlockingQueue blockingQueue = client.getBlockingQueue(JobTimer.jobsTag);
        RDelayedQueue delayedQueue = client.getDelayedQueue(blockingQueue);
        delayedQueue.offer(job, delay, timeUnit);
    }
}
