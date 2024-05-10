package com.clover.recode.domain.recode.dto;

<<<<<<< Updated upstream
import com.clover.recode.domain.problem.dto.ProblemRegistReq;
=======
>>>>>>> Stashed changes
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecodeRes {
<<<<<<< Updated upstream
    private ProblemRegistReq problemRegistReq;
=======
    private Problem problem;
>>>>>>> Stashed changes
    private String recode;
    private List<String> answers;
}
