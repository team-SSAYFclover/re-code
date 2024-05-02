//package com.clover.recode.domain.problem.service;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//import com.clover.recode.domain.problem.dto.ProblemCodeDto;
//import com.clover.recode.domain.problem.entity.Code;
//import com.clover.recode.domain.problem.entity.Problem;
//import com.clover.recode.domain.problem.repository.CodeRepository;
//import com.clover.recode.domain.problem.repository.ProblemRepository;
//import com.clover.recode.domain.problem.service.ProblemServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//public class ProblemServiceImplTest {
//
//    @Mock
//    private ProblemRepository problemRepository;
//
//    @Mock
//    private CodeRepository codeRepository;
//
//    @InjectMocks
//    private ProblemServiceImpl problemService;
//
//    @Test
//    public void testSaveProblemAndCode_WithNonExistingProblem() {
//        // 준비: DTO 및 기대값 설정
//        ProblemCodeDto dto = new ProblemCodeDto();
//        dto.setProblemId(1L);
//        dto.setProblemNo(123);
//        dto.setTitle("Test Problem");
//        dto.setLevel(1);
//        dto.setContent("Test content");
//        dto.setUserId(1L);
//        dto.setCodeNo(1L);
//        dto.setCodeContent("Test code");
//
//        // 문제가 DB에 존재하지 않을 때 반환 설정
//        when(problemRepository.findById(any(Long.class))).thenReturn(Optional.empty());
//
//        // 실행: 서비스 메소드 호출
//        problemService.saveProblemAndCode(dto);
//
//        // 검증: 문제가 저장되었는지 확인
//        verify(problemRepository, times(1)).save(any(Problem.class));
//        // 검증: 코드가 항상 저장되는지 확인
//        verify(codeRepository, times(1)).save(any(Code.class));
//    }
//
//    @Test
//    public void testSaveProblemAndCode_WithExistingProblem() {
//        // 준비: DTO 및 기존 문제 설정
//        ProblemCodeDto dto = new ProblemCodeDto();
//        dto.setProblemId(1L);
//        dto.setProblemNo(123);
//        dto.setTitle("Test Problem");
//        dto.setLevel(1);
//        dto.setContent("Test content");
//        dto.setUserId(1L);
//        dto.setCodeNo(1L);
//        dto.setCodeContent("Test code");
//
//        Problem existingProblem = new Problem();
//        existingProblem.setId(1L);
//
//        // 문제가 이미 DB에 존재할 때 반환 설정
//        when(problemRepository.findById(any(Long.class))).thenReturn(Optional.of(existingProblem));
//
//        // 실행: 서비스 메소드 호출
//        problemService.saveProblemAndCode(dto);
//
//        // 검증: 문제가 다시 저장되지 않는지 확인
//        verify(problemRepository, never()).save(any(Problem.class));
//        // 검증: 코드가 저장되는지 확인
//        verify(codeRepository, times(1)).save(any(Code.class));
//    }
//}
