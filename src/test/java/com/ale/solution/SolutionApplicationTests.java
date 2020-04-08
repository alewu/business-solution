package com.ale.solution;

import com.ale.solution.service.LuaScriptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SolutionApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private LuaScriptService luaScriptService;

    @Test
    void contextLoads() {
        String lockKey = "123";
        String uuid = UUID.randomUUID().toString();
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, uuid, 3, TimeUnit.MINUTES);
        if (!success) {
            System.out.println("锁已存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", uuid);

        Boolean result = luaScriptService.redisAddScriptExec(Collections.singletonList(lockKey), map);

        System.out.println(result);
    }

}
