package com.ale.solution.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.listener.MessageListener;
/**
  *
  * @author alewu
  * @date 2020/8/7
  */
@Slf4j
public class RedisDelayQueueListener implements MessageListener {
    @Override
    public void onMessage(CharSequence channel, Object msg) {
        log.info("{}", msg.toString());
    }
}
