package com.clover.recode.domain.problem.entity;

import jakarta.persistence.*;
import java.util.List;
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

    @Column(nullable = false, unique = true)
    private String name; // 알고리즘 분류 이름 : "구현"

//    @OneToMany(mappedBy = "tag",fetch = FetchType.LAZY)
//    private List<ProblemTag> problems = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "problem_tag",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "problem_id")
    )
    private List<Problem> problems;
}
