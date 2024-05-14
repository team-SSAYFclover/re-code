    package com.clover.recode.domain.problem.dto;

    import lombok.*;

    import java.time.LocalDateTime;
    @Getter
    @Setter
    @Builder(toBuilder = true)
    @ToString
    @AllArgsConstructor
    public class CodeResList {
        private Long id;
        private Long recodeId;
        private String name;    // 코드 제목
        private String content; // 코드 내용
        private LocalDateTime submitTime; // 생성 시간
        private boolean reviewStatus; // 리뷰 여부
    }
