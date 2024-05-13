package com.clover.recode.domain.problem.service;

import static com.clover.recode.global.result.error.ErrorCode.Already_REGIST_CODE;
import static com.clover.recode.global.result.error.ErrorCode.CODE_NOT_EXISTS;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.problem.dto.*;
import com.clover.recode.domain.problem.entity.*;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.problem.repository.ProblemRepository;
import com.clover.recode.domain.problem.repository.TagRepository;
import com.clover.recode.domain.recode.entity.*;
import com.clover.recode.domain.recode.service.RecodeService;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.global.result.error.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

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

    private final RecodeService recodeService;


    @Override
    @Transactional
    public void saveProblemAndCode(ProblemCodeRegistReq problemCodeRegistReq) {

        ProblemRegistReq problemRegistReq = problemCodeRegistReq.getProblem();
        CodeRegistReq codeRegistReq = problemCodeRegistReq.getCode();

        if(codeRepository.existsByCodeNo(codeRegistReq.getCodeNo())) {
            throw new BusinessException(Already_REGIST_CODE);
        }

        Problem problem = problemRepository.findFirstByProblemNo(problemRegistReq.getProblemNo());

        if(problem == null) {
            problem = new Problem();
            problem.setProblemNo(problemRegistReq.getProblemNo());
            problem.setTitle(problemRegistReq.getTitle());
            problem.setContent(problemRegistReq.getContent());
            problem.setLevel(problemRegistReq.getLevel());
            List<Tag> tags = new ArrayList<>();
            for (String tagName : problemRegistReq.getTags()) {
                Tag tag = tagRepository.findByName(tagName);
                if (tag == null) {
                    tag = new Tag();
                    tag.setName(tagName);
                }
                tags.add(tag);
            }
            problem.setTags(tags);

            problem = problemRepository.save(problem);
        }

        Code code = Code.builder()
            .codeNo(codeRegistReq.getCodeNo())
            .content(codeRegistReq.getContent())
            .name(codeRegistReq.getName())
            .problem(problem)
            .user(
                User.builder()
                        .id(problemCodeRegistReq.getId())
                        .build()
            )
                .build();

        codeRepository.save(code);

        recodeService.saveRecode(code);
    }

    //사용자별 문제 조회
    public Page<ProblemResList> findUserProblems(Authentication authentication, Pageable pageable, Integer start, Integer end, List<String> tags, String keyword) {

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        Page<Problem> problemsPage = problemRepository.findProblemsByUserId(customUserDetails.getId(), pageable, start, end, tags, keyword);

        // Page<Problem>을 Page<ProblemRes>로 변환
        return problemsPage.map(problem -> {
            List<String> tagNames = tagRepository.getTagNames(problem.getId()); // 태그 리스트
            int reviewCount = problemRepository.getReviewCount(problem.getId());   // 복습량

            // 문제 정보를 ProblemRes DTO로 매핑
            return ProblemResList.builder()
                    .problemNo(problem.getProblemNo())
                    .title(problem.getTitle())
                    .level(problem.getLevel())
                    .tags(tagNames)
                    .reviewCount(reviewCount)
                    .build();
        });
    }




//    @Override
    public ProblemDetailRes getProblemDetails(Authentication authentication, Integer problemNo) {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        // 문제 정보 조회해서 DTO(ProblemRes)에 담기
        Problem problem = problemRepository.findProblemByProblemNo(problemNo);
        List<String> tagNames = tagRepository.getTagNames(problem.getId()); //태그 리스트
        int reviewCount = problemRepository.getReviewCount(problem.getId());    // 복습량

        ProblemResList problemResList = ProblemResList.builder()
                .problemNo(problemNo)
                .title(problem.getTitle())
                .level(problem.getLevel())
                .tags(tagNames)
                .reviewCount(reviewCount)
                .build();

        // 코드 정보 조회해서 List로 반환
        List<CodeResList> codeResList = codeRepository.findCodesByProblemNoAndUserId(problemNo, customUserDetails.getId());

        // 문제 정보와 코드 정보 합치기

        ProblemDetailRes problemDetailResult = ProblemDetailRes.builder()
                    .problemResList(problemResList)
                    .content(problem.getContent())
                    .codeReLists(codeResList)
                    .build();
        return problemDetailResult;
    }


    @Override
    @Transactional
    public void patchCode(Long codeId, CodePatchReq codePatchReq, Authentication authentication) {

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        Code code = codeRepository.findByIdAndUserId(codeId, customUserDetails.getId())
            .orElseThrow(() -> new BusinessException(CODE_NOT_EXISTS));

        code.updateCode(codePatchReq);
    }

    @Override
    @Transactional
    public void deleteCode(Long codeId, Authentication authentication) {

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        Code code = codeRepository.findByIdAndUserId(codeId, customUserDetails.getId())
            .orElseThrow(() -> new BusinessException(CODE_NOT_EXISTS));

        codeRepository.delete(code);
    }


}
