package com.clover.recode.domain.problem.entity;

import com.clover.recode.domain.recode.entity.Recode;
import com.clover.recode.domain.user.entity.Setting;
import com.clover.recode.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Code Entity
    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @EntityListeners(value = AuditingEntityListener.class)
    @ToString(of = {"id", "codeNo", "name", "content"})
    public class Code {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @JoinColumn(name = "user_id")
        @ManyToOne(fetch = FetchType.LAZY)
        private User user;

        @JoinColumn(name = "problem_id")
        @ManyToOne(fetch = FetchType.LAZY)
        private Problem problem;

        @Column(unique = true)
        private Long codeNo;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false, columnDefinition = "LONGTEXT")
        private String content;

        @ColumnDefault("false")
        private boolean deleted;

        @ColumnDefault("true")
        private boolean reviewStatus;

        @CreatedDate
        @Column(updatable = false)
        private LocalDateTime createdTime;
        private LocalDateTime updatedTime;

        @OneToOne(mappedBy = "code", orphanRemoval = true, fetch = FetchType.LAZY)
        private Recode recode;

        @PrePersist
        public void prePersist() {
            LocalDateTime now = LocalDateTime.now();
            createdTime = now;
            updatedTime = now;
        }

        @PreUpdate
        public void preUpdate() {
            updatedTime = LocalDateTime.now();
        }
    }
