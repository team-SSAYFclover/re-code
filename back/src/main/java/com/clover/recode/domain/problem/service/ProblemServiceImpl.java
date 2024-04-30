package com.clover.recode.domain.problem.service;

import com.clover.recode.domain.problem.dto.ProblemCodeDto;
import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.Problem;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.problem.repository.ProblemRepository;
import com.clover.recode.domain.problem.service.ProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository problemRepository;
    private final CodeRepository codeRepository;

    @Override
    public void saveProblemAndCode(ProblemCodeDto dto) {
        Problem problem = problemRepository.findById(dto.getProblemId())
                .orElse(new Problem());
        if (problem.getId() == null) {
            problem.setId(dto.getProblemId());
            problem.setProblemNo(dto.getProblemNo());
            problem.setTitle(dto.getTitle());
            problem.setLevel(dto.getLevel());
            problem.setContent(dto.getContent());
            problemRepository.save(problem);
        }

        Code code = new Code();
        code.setUserId(dto.getUserId());
        code.setProblemId(dto.getProblemId());
        code.setCodeNo(dto.getCodeNo());
        code.setContent(dto.getCodeContent());
        codeRepository.save(code);
    }
}
