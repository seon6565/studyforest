package org.fullstack4.studyforest.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsDTO {
    private int bbsIdx;
    private String category;
    private String category2;
    private String hashtag;
    private String user_id;
    private String title;
    private String content;
    private String display_date_flag;
    private LocalDate display_date_start;
    private LocalDate display_date_end;
    private LocalDateTime reg_date;
    private LocalDateTime modify_date;
}
