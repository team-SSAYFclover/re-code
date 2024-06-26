package com.clover.recode.domain.statistics.entity;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.user.entity.Setting;
import com.clover.recode.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@DynamicInsert
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ranking;

    private Integer supplementaryNo;

    private Integer randomNo;

    private String supplementaryTitle;

    private String randomTitle;

    private Integer sequence;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
