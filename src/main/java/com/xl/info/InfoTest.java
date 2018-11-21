package com.xl.info;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InfoTest {
    @Autowired
    StringRedisTemplate template;
    @Autowired
    CountDownLatch latch;

    @Test
    public void testSendInfo() throws InterruptedException {
        log.info("23234234...");
        template.convertAndSend("chat", "Hellojkdjfksfj!");
        latch.await();
    }
}
