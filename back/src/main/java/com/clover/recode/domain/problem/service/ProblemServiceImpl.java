package com.clover.recode.domain.problem.service;

import static com.clover.recode.global.result.error.ErrorCode.Already_REGIST_CODE;

import com.clover.recode.domain.problem.dto.CodeDto;
import com.clover.recode.domain.problem.dto.ProblemCodeReq;
import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.Problem;
import com.clover.recode.domain.problem.entity.Tag;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.problem.repository.ProblemRepository;
import com.clover.recode.domain.problem.repository.TagRepository;
import com.clover.recode.domain.problem.service.ProblemService;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.global.result.error.ErrorCode;
import com.clover.recode.global.result.error.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final CodeRepository codeRepository;
    private final TagRepository tagRepository;


    @Override
    @Transactional
    public void saveProblemAndCode(ProblemCodeReq problemCodeReq) {

        ProblemDto problemDto = problemCodeReq.getProblem();
        CodeDto codeDto = problemCodeReq.getCode();

        if(codeRepository.existsByCodeNo(codeDto.getCodeNo())) {
            throw new BusinessException(Already_REGIST_CODE);
        }

        Problem problem = problemRepository.findFirstByProblemNo(problemDto.getProblemNo());

        if(problem == null) {
            problem = new Problem();
            problem.setProblemNo(problemDto.getProblemNo());
            problem.setTitle(problemDto.getTitle());
            problem.setContent(problemDto.getContent());
            problem.setLevel(problemDto.getLevel());
            List<Tag> tags = new ArrayList<>();
            for (String tagName : problemDto.getTags()) {
                Tag tag = tagRepository.findByName(tagName);
                if (tag == null) {
                    tag = new Tag();
                    tag.setName(tagName);
                }
                tags.add(tag);
            }
            problem.setTags(tags);
        }


        Code code = Code.builder()
            .codeNo(codeDto.getCodeNo())
            .content(codeDto.getContent())
            .name(codeDto.getName())
            .problem(problem)
            .user(
                User.builder()
                    .id(problemCodeReq.getId())
                    .build()
            )
            .build();

        codeRepository.save(code);
    }
}
