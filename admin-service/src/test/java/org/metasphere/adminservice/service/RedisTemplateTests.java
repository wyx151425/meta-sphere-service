package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-10 15:53
 * @Modified By:
 */
@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testRedisTemplate() {
        redisTemplate.opsForList().rightPushAll("202304101610", "1", "2", "3", "4");
    }
}
