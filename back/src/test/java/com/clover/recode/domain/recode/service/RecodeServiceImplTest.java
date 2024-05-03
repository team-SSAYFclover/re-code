package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.recode.dto.EnglishPrompt;
import com.clover.recode.global.redis.RedisController;
import com.clover.recode.global.result.error.exception.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static com.clover.recode.global.result.error.ErrorCode.USER_NOT_EXISTS;

@SpringBootTest
class RecodeServiceImplTest {

    @Autowired
    RecodeServiceImpl service;

    @Autowired
    CodeRepository codeRepository;

    @Test
    void GPTTest() {
//        String recode = service.getRecode(EnglishPrompt.prompt);
    }

    @Test
    void saveRecodeTest() {
        Code code = codeRepository.findById(1L)
                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));

        service.saveRecode(code);
    }

    @Test
    void getRecodeTest() {
        Code code = codeRepository.findById(1L)
                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));

        service.getRecode(code.getId());
    }

    @Test
    void recodeSubmitTest() {
        Code code = codeRepository.findById(1L)
                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));

        service.recodeSubmit(code.getId());
    }
}
