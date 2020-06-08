package org.practice.testcontainers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.org.apache.commons.lang.text.StrBuilder;

/**
 * @author Luo Bao Ding
 * @since 2020/6/8
 */

@ExtendWith(SpringExtension.class)
@DataRedisTest
@ActiveProfiles("integrationtest")
public class RedisTemplateTest {

    @Autowired
    StringRedisTemplate template;

    @Autowired
    Environment environment;

    @Test
    void testSet() {
        String redis_host = environment.getProperty("embedded.redis.host");
        String redis_port = environment.getProperty("embedded.redis.port");
        String redis_password = environment.getProperty("embedded.redis.password");
        System.out.println("redis_host = " + redis_host);
        System.out.println("redis_port = " + redis_port);
        System.out.println("redis_password = " + redis_password);

        template.opsForValue().set("username", "jack");
        String username = template.opsForValue().get("username");
        Assertions.assertThat(username).isEqualTo("jack");

    }





}
