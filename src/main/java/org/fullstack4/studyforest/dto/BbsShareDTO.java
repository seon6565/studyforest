package org.fullstack4.studyforest.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsShareDTO {
    private int shareIdx;
    private int bbsIdx;
    private String category;
    private String title;
    private String userId;
    private String toUserId;
    private LocalDateTime reg_date;
}
