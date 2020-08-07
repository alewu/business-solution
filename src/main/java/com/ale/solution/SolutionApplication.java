package com.ale.solution;

import com.ale.solution.service.DelayJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class SolutionApplication {

    @Autowired
    private DelayJobService delayJobService;
    public static void main(String[] args) {
        SpringApplication.run(SolutionApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {

        return args -> {

            delayJobService.submitJob("delayJob1", 10L, TimeUnit.SECONDS);
            delayJobService.submitJob("delayJob2", 30L, TimeUnit.SECONDS);
            delayJobService.submitJob("delayJob3", 70L, TimeUnit.SECONDS);
        };
    }

    @Scheduled(fixedRate = 5000) //通过@Scheduled声明该方法是计划任务，使用fixedRate属性每隔固定时间执行
    public void reportCurrentTime(){
        for (int i = 0; i < 100; i++) {
            delayJobService.submitJob("delayJob1", 10L, TimeUnit.SECONDS);
        }

    }



}
