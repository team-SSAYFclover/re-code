package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.entity.Problem;
import com.clover.recode.domain.problem.entity.Tag;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import com.clover.recode.domain.problem.entity.*;

@RequiredArgsConstructor
public class TagCustomRepositoryImpl implements TagCustomRepository {
    private final ProblemRepository problemRepository;
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

}
