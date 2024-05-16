package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.recode.dto.code.ExampleCode1;
import com.clover.recode.domain.recode.dto.code.ExampleCode3;
import com.clover.recode.domain.recode.dto.code.ExampleCode4;
import com.clover.recode.domain.recode.dto.code.ExampleCode5;
import com.clover.recode.global.result.error.ErrorCode;
import org.springframework.boot.test.context.SpringBootTest;
import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.global.result.error.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.clover.recode.global.result.error.ErrorCode.CODE_NOT_EXISTS;

@SpringBootTest
class RecodeServiceImplTest {

    @Autowired
    RecodeServiceImpl service;

    @Autowired
    CodeRepository codeRepository;

    @Test
    void createRecodeTest() {
        service.createRecode(ExampleCode1.code);
    }

    @Test
    void getRecodeTest() {
        Code code = codeRepository.findById(18L)
                .orElseThrow(() -> new BusinessException(CODE_NOT_EXISTS));

        service.getRecodeFromCode(code);
    }
}
