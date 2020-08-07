package com.ale.solution;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SolutionApplicationTests {
    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {

    }

}
