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

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsFileDTO {
    private int bbsFileIdx;
    private int bbsIdx;
    private String category;
    private String directory;
    private String fileName;
    private String userId;
    private LocalDateTime reg_date;
}
