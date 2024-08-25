package com.digest.journalApp.service;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("email","gmail@email.com");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a = 1;
        System.out.println(salary);
    }
}