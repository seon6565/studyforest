package org.fullstack4.studyforest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name="sf_member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int member_idx;
    @Column(length = 20, nullable = false, unique = true)
    private String userId;
    @Column(length = 128, nullable = false)
    private String password;
    @Column(length = 10, nullable = false)
    private String name;
    @Column(length = 5, nullable = true)
    private String roles; //user,admin
    @Column(length = 30, nullable = false)
    private String email;
    @Column(length = 13, nullable = false)
    private String phone;
    @Column(length = 10, nullable = false)
    private LocalDate birthday;
    @Column(length = 50, nullable = false)
    private String addr1;
    @Column(length = 50, nullable = false)
    private String addr2;
    @Column(length = 6, nullable = false)
    private String addr_number;

    @Column(length = 128, nullable = true)
    private String temp_password;
    @Column(length = 2, nullable = true)
    private int try_check;
    @Column(length = 1, nullable = true)
    private String state;//Y=정상,N=탈퇴,H=휴면,B=밴,C=비밀번호변경대상

    @CreatedDate
    @Column(name = "reg_date", updatable = false)
    private LocalDateTime reg_date;
    @LastModifiedDate
    @Column(name = "modify_date", nullable = true, insertable = false, updatable = true)
    private LocalDateTime modify_date;
    @CreatedDate
    private LocalDateTime password_changedate;
    private LocalDateTime temp_password_validdate;
    @CreatedDate
    private LocalDateTime login_date;
    private LocalDateTime ban_date;
    private LocalDateTime leave_date;

    public void modify(String email, String phone, LocalDate birthday, String addr1, String addr2, String addr_number){
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr_number = addr_number;
        this.modify_date = LocalDateTime.now();
    }
    public void modifyPassword(String password){
        this.password = password;
        this.password_changedate = LocalDateTime.now();
        this.temp_password = null;
        this.temp_password_validdate = null;
    }

    public void leave(){
        this.state = "N";
        this.leave_date = LocalDateTime.now();
    }
}
