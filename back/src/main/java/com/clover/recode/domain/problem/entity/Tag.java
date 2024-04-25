package com.clover.recode.domain.problem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// 알고리즘 분류 테이블
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id; // 알고리즘 분류 Id : 1

    @Column(nullable = false)
    private String name; // 알고리즘 분류 이름 : BFS
}
