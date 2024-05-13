package org.fullstack4.studyforest.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="sf_bbsfree")
public class BbsFreeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int bbsIdx;
    @Column(length = 20, nullable = false)
    private String category;
    @Column(length = 50, nullable = false)
    private String category2;
    @Column(length = 50, nullable = false)
    private String hashtag;
    @Column(length = 20, nullable = false)
    private String user_id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String content;
    @Column(length = 1, nullable = false)
    private String display_date_flag;
    @Column(length = 10, nullable = true)
    private String display_date_start;
    @Column(length = 10, nullable = true)
    private String display_date_end;
    @CreatedDate
    @Column(name = "reg_date", updatable = false)
    private LocalDateTime reg_date;
    @LastModifiedDate
    @Column(name = "modify_date", nullable = true, insertable = false, updatable = true)
    private LocalDateTime modify_date;

}
