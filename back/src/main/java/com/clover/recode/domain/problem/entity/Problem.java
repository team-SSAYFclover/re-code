package com.clover.recode.domain.problem.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Problem Entity


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "problem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer problemNo;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @OneToMany(mappedBy = "problem", orphanRemoval = true,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Code> codes;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "problem_tag",
        joinColumns = @JoinColumn(name = "problem_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    List<Tag> tags;
}

