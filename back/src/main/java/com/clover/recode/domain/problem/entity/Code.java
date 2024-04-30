package com.clover.recode.domain.problem.entity;

import com.clover.recode.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

    // Code Entity
    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @Table(name = "code")
    public class Code {

        @Id
        private Long id;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

        private Long problemId;

        private Long codeNo;

        //private LocalDateTime submitTime;

        private String name;

        @Column(columnDefinition = "TEXT")
        private String content;

        private Boolean deleted;

        private LocalDateTime createdTime;

        private Boolean reviewStatus;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "problem_id")
        private Problem problem;

    }
