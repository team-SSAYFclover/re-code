package com.clover.recode.global.redis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class RedisControllerTest {

    @Autowired
    RedisController stringRepository;

    @Test
    void StringSaveTest() {
        String key = "jun";
        String value = "test";
        stringRepository.addRedisKey(key, value);

        ResponseEntity<?> foundValue = stringRepository.getRedisKey(key);
        Assertions.assertThat(foundValue.getBody()).isEqualTo(value);
    }
}
