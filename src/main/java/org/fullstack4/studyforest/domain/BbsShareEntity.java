package org.fullstack4.studyforest.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="sf_bbsshare")
public class BbsShareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int shareIdx;

    private int bbsIdx;
    @Column(length = 20, nullable = false)
    private String category;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 20, nullable = false)
    private String userId;
    @Column(length = 20, nullable = false)
    private String toUserId;
    @CreatedDate
    @Column(name = "reg_date", updatable = false)
    private LocalDateTime reg_date;
}

