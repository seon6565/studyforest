package org.fullstack4.studyforest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BbsEntity {
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
    private LocalDate display_date_start;
    @Column(length = 10, nullable = true)
    private LocalDate display_date_end;
    @CreatedDate
    @Column(name = "reg_date", updatable = false)
    private LocalDateTime reg_date;
    @LastModifiedDate
    @Column(name = "modify_date", nullable = true, insertable = false, updatable = true)
    private LocalDateTime modify_date;

    public void modify(String category2, String title, String content, String hashtag, String display_date_flag, LocalDate display_date_start, LocalDate display_date_end){
        this.category2 = category2;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.display_date_flag = display_date_flag;
        this.display_date_start = display_date_start;
        this.display_date_end = display_date_end;
        this.modify_date= LocalDateTime.now();
    }
}
