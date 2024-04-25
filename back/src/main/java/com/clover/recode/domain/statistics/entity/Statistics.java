package com.clover.recode.domain.statistics.entity;

import com.clover.recode.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    private Long id;

    @NotNull
    @ColumnDefault("100")
    private int ranking;

    @NotNull
    @ColumnDefault("1806")
    private int supplementaryNo;

    @ColumnDefault("2178")
    private int randomNo;

    @NotNull
    @ColumnDefault("0")
    private int sequence;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "id")
    private List<WeekReviews> weekReviews= new ArrayList<>();

}
