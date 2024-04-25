package com.clover.recode.problem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Table(name = "problem")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Problem {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer problemNo;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private Integer reviewCount;

    @ElementCollection
    @CollectionTable(name = "problem_tags", joinColumns = @JoinColumn(name = "problem_id"))
    @Column(name = "tag")
    private List<String> tags;

}
