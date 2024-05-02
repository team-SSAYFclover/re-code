package com.clover.recode.domain.problem.entity;

import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.recode.entity.Recode;
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

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

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

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "recode_id")
        private Recode recode;


    }
