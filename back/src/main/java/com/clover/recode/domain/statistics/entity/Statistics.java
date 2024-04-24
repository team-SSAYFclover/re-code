package com.clover.recode.domain.statistics.entity;

import com.clover.recode.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rank;

    private int supplementaryNo;

    private int randomNo;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "id")
    private List<WeekReviews> weekReviews= new ArrayList<>();

}
