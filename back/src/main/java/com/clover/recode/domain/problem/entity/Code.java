package com.clover.recode.domain.problem.entity;

import com.clover.recode.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Code Entity
    @Entity
    @Getter
    @Setter
    @Builder(toBuilder = true)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @EntityListeners(value = AuditingEntityListener.class)
    @ToString(of = {"id", "codeNo", "name", "content"})
    @DynamicInsert
    public class Code {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @JoinColumn(name = "user_id")
        @ManyToOne(fetch = FetchType.LAZY)
        private User user;

        @JoinColumn(name = "problem_id")
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private Problem problem;

        @Column(unique = true, nullable = false)
        private Integer codeNo;

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