package org.fullstack4.studyforest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsGoodDTO {
    private int goodIdx;
    private int bbsIdx;
    private String category;
    private String title;
    private String userId;
    private LocalDateTime reg_date;
}
