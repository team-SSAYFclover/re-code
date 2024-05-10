package com.clover.recode.domain.problem.service;

import static com.clover.recode.global.result.error.ErrorCode.Already_REGIST_CODE;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.problem.dto.*;
import com.clover.recode.domain.problem.entity.*;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.problem.repository.ProblemRepository;
import com.clover.recode.domain.problem.repository.TagRepository;
import com.clover.recode.domain.recode.entity.QRecode;
import com.clover.recode.domain.recode.service.RecodeService;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.global.result.error.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    private final CodeRepository codeCustomRepository;
    private final TagRepository tagRepository;

    private final RecodeService recodeService;
    private final JPAQueryFactory jpaQueryFactory;

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
    @Override
    public Page<ProblemRes> findProblemsByUserId(Authentication authentication, Pageable pageable, Integer start, Integer end, List<String> tags, String keyword) {

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        Page<Problem> problemsPage = codeCustomRepository.findProblemsByUserId(customUserDetails.getId(), pageable, start, end, tags, keyword);

        // Page<Problem>을 Page<ProblemRes>로 변환
        return problemsPage.map(problem -> {
            List<String> tagNames = getTagNames(problem.getId()); // 태그 리스트
            int reviewCount = getReviewCount(problem.getId());   // 복습량

            // 문제 정보를 ProblemRes DTO로 매핑
            return ProblemRes.builder()
                    .problemNo(problem.getProblemNo())
                    .title(problem.getTitle())
                    .level(problem.getLevel())
                    .tags(tagNames)
                    .reviewCount(reviewCount)
                    .build();
        });


    }

    // 복습량 - 레코드 제출 횟수 (정답인 경우)
    public Integer getReviewCount(Long problemId) {
        QCode qCode = QCode.code;
        QRecode qRecode = QRecode.recode;

        Integer totalSubmitCount = jpaQueryFactory
                .select(qRecode.submitCount.sum())
                .from(qRecode)
                .join(qRecode.code, qCode)
                .where(qCode.problem.id.eq(problemId))
                .fetchOne();

        return totalSubmitCount != null ? totalSubmitCount : 0;
    }

    // tagRepository에서 태그를 가져오는 메소드
    public List<String> getTagNames(Long problemId) {

        Problem problem = problemRepository.findById(problemId).orElse(null);
        if (problem == null) {
            return new ArrayList<>();
        }

        List<String> tagNames = new ArrayList<>();
        for (Tag tag : problem.getTags()) {
            tagNames.add(tag.getName());
        }
        return tagNames;
    }


    @Transactional(readOnly = true)
    public List<Code> findCodeByProblemNo(Integer problemNo) {
        QCode qCode = QCode.code;
        QProblem qProblem = QProblem.problem;

        // 조인과 필터 조건을 사용한 쿼리 구성
        List<Code> codes = jpaQueryFactory
                .selectFrom(qCode)
                .join(qCode.problem, qProblem)
                .where(qProblem.problemNo.eq(problemNo))
                .fetch();
        return codes;
    }


//    @Override
    public ProblemDetailRes getProblemDetails(Authentication authentication, Integer problemNo) {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        // 문제 정보 조회해서 DTO(ProblemRes)에 담기
        Problem problem = problemRepository.findProblemByProblemNo(problemNo);
        List<String> tagNames = getTagNames(problem.getId()); //태그 리스트
        int reviewCount = getReviewCount(problem.getId());    // 복습량

        ProblemRes problemRes = ProblemRes.builder()
                .problemNo(problemNo)
                .title(problem.getTitle())
                .level(problem.getLevel())
                .tags(tagNames)
                .reviewCount(reviewCount)
                .build();

        // 코드 정보 조회해서 List로 반환
        List<CodeRes> codesRes = findCodesByProblemNoAndUserId(problemNo, customUserDetails.getId());

        // 문제 정보와 코드 정보 합치기

        ProblemDetailRes problemDetailResult = ProblemDetailRes.builder()
                    .problemRes(problemRes)
                    .codeRes(codesRes)
                    .build();
        return problemDetailResult;
    }

    @Transactional(readOnly = true)
    public List<CodeRes> findCodesByProblemNoAndUserId(Integer problemNo, Long userId) {
        QCode qCode = QCode.code;
        QProblem qProblem = QProblem.problem;


        return jpaQueryFactory
                .select(Projections.constructor(CodeRes.class,
                        qCode.id, qCode.name, qCode.content, qCode.createdTime))
                .from(qCode)
                .join(qCode.problem, qProblem)
                .where(qProblem.problemNo.eq(problemNo)
                        .and(qCode.user.id.eq(userId)))
                .fetch();
    }

}
