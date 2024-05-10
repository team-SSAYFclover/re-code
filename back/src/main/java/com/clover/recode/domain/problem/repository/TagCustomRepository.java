package com.clover.recode.domain.problem.repository;

import java.util.List;

public interface TagCustomRepository {
    public List<String> getTagNames(Long problemId);
}
