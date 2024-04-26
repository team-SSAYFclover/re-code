package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.recode.dto.EnglishPrompt;
import com.clover.recode.global.redis.RedisController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class RecodeServiceImplTest {

    @Autowired
    RecodeServiceImpl service;

    @Test
    void GPTTest() {
        String recode = service.getRecode(EnglishPrompt.prompt);
    }
}
