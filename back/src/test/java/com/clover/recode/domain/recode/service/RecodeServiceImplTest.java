package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.recode.dto.code.ExampleCode1;
import org.springframework.boot.test.context.SpringBootTest;
import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.global.result.error.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class RecodeServiceImplTest {

//    @Autowired
//    RecodeServiceImpl service;

//    @Autowired
//    CodeRepository codeRepository;

//    @Test
//    void GPTTest() {
//        String recode = service.getRecode(EnglishPrompt.prompt);
//    }

//    @Test
//    void createRecodeTest() {
//        service.createRecode(ExampleCode1.code);
//    }

//    @Test
//    void getRecodeTest() {
//        Code code = codeRepository.findById(12L)
//                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));
//
//        service.getRecodeFromCode(code);
//    }
//
//    @Test
//    void recodeSubmitTest() {
//        Code code = codeRepository.findById(1L)
//                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));
//
//        service.recodeSubmit(code.getId());
//    }
}
